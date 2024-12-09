package com.example.repository;

import com.example.dto.TodoResponseDto;
import com.example.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.util.List;

/**
 * JdbcTodoRepository 클래스
 * - TodoRepository 인터페이스를 구현한 클래스 입니다.
 * - JDBC Template을 사용해 데이터베이스와 상호 작용 합니다.
 * - 일정 데이터를 데이터베이스에 저장, 조회, 수정, 삭제하는 기능을 제공합니다.
 */
@Repository
public class JdbcTodoRepository implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    /**
     * 생성자 : DataSource를 주입받아 JdbcTemplate 객체를 초기화합니다.
     * @param dataSource 데이터베이스 연결 정보
     */
    public JdbcTodoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * RowMapper
     * - 데이터베이스 조회 결과를 Todo 객체로 매핑합니다.
     * - 조회 결과의 컬럼 값을 Todo객체의 필드로 변환합니다.
     */
    private final RowMapper<Todo> todoRowMapper = (rs, rowNum) -> new Todo(
            rs.getLong("id"),
            rs.getString("author"),
            rs.getString("task"),
            rs.getString("pw"),
            rs.getTimestamp("createDate").toLocalDateTime(),
            rs.getTimestamp("modifiedDate").toLocalDateTime()
    );

    /**
     * 새로운 일정 생성
     * - todo_table에 새로운 일정을 삽입합니다.
     * - 삽입 후 생성된 id값을 반환받아 Todo객체에 설정합니다.
     * @param todo 저장할 일정 데이터
     * @return 저장된 Todo 객체
     */
    @Override
    public Todo saveTodo(Todo todo) {
        String sql = "INSERT INTO todo_table(task, author, pw, createDate, modifiedDate) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, todo.getTask(), todo.getAuthor(), todo.getPw(),
                Timestamp.valueOf(todo.getCreateDate()), Timestamp.valueOf(todo.getModifiedDate()));

        // 마지막으로 삽입된 ID를 조회하여 반환
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return todo;
    }

    /**
     * 전체 일정 목록 조회
     * - todo_table의 모든 데이터를 조회하여 TodoResponseDto 리스트로 반환합니다.
     * @return 모든 일정의 TodoResponseDto 리스트
     */
    @Override
    public List<TodoResponseDto> findAllTodos() {
        String sql = "SELECT * FROM todo_table";
        List<Todo> allTodos = jdbcTemplate.query(sql, todoRowMapper);
        return allTodos.stream().map(TodoResponseDto::new).toList(); // Todo를 TodoResponseDto로 변환
    }

    /**
     * 일정 단건 조회
     * - id를 기준으로 todo_table에서 일정을 조회합니다.
     * - 조회 결과가 없으면 null을 반환합니다.
     * @param id 조회할 일정의 고유 식별자 (PK)
     * @return 조회된 Todo 객체
     */
    @Override
    public Todo findTodoById(Long id) {
        String sql = "SELECT * FROM todo_table WHERE id = ?";
        return jdbcTemplate.query(sql, todoRowMapper, id).stream().findAny().orElse(null); // 조회하려는 일정이 비었으면 null 반환
    }

    /**
     * 날짜 기준 일정 조회
     * - 특정 날짜를 기준으로 todo_table에서 일정을 조회합니다.
     * - 날짜 비교를 DATE 형식으로 처리합니다.
     * @param createDate 조회 기준 날짜
     * @return 해당 날짜의 Todo 리스트
     */
    @Override
    public List<Todo> findTodoByDate(LocalDate createDate) {
        String sql = "SELECT * FROM todo_table WHERE DATE(createDate) = ?";
        return jdbcTemplate.query(sql, todoRowMapper, createDate);
    }

    /**
     * 일정 내용 수정
     * - id를 기준으로 일정의 내용을 업데이트 합니다.
     * - 수정 시간을 현재 시간으로 갱신합니다.
     * @param todo  수정할 데이터를 포함한 Todo 객체
     */
    @Override
    public void updateTodo(Todo todo) {
        String sql = "UPDATE todo_table SET task = ?, modifiedDate = ? WHERE id =?";
        jdbcTemplate.update(sql,todo.getTask(), Timestamp.valueOf(todo.getModifiedDate()), todo.getId());

    }

    /**
     * 일정 삭제
     * - id를 기준으로 todo_table에서 일정을 삭제합니다.
     * @param id 삭제할 일정의 고유 식별자 (PK)
     */
    @Override
    public void deleteTodo(Long id) {
        String sql = "DELETE FROM todo_table WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
