package com.example.repository;

import com.example.dto.TodoResponseDto;
import com.example.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public JdbcTodoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Todo> todoRowMapper = (rs, rowNum) -> new Todo(
            rs.getLong("id"),
            rs.getString("author"),
            rs.getString("task"),
            rs.getString("pw"),
            rs.getTimestamp("createDate").toLocalDateTime(),
            rs.getTimestamp("modifiedDate").toLocalDateTime()
    );

    @Override
    public Todo saveTodo(Todo todo) {
        String sql = "INSERT INTO todo_table(task, author, pw, createDate, modifiedDate) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, todo.getTask(), todo.getAuthor(), todo.getPw(),
                Timestamp.valueOf(todo.getCreateDate()), Timestamp.valueOf(todo.getModifiedDate()));

        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return todo;
    }

    // 전체 일정 조회
    @Override
    public List<TodoResponseDto> findAllTodos(LocalDate modifiedDate, String author) {
        String sql = "SELECT * FROM todo_table WHERE TRUE"; // 전체 일정 목록을 조회하는 sql
        List<Object> params = new ArrayList<>(); // 리스트 params 초기화 동적인 데이터 관리를 위해 리스트 사용

        if (modifiedDate != null) { // 값이 있을 때
            sql += " AND DATE(modifiedDate) = ?"; // AND를 바로 쓸 수 있는 이유는 WHERE TRUE 때문
            params.add(modifiedDate);
        }
        if (author != null) {
            sql += " AND author = ?";
            params.add(author);
        }
        sql += " ORDER BY modifiedDate desc";
        List<Todo> allTodos = jdbcTemplate.query(sql, todoRowMapper, params.toArray()); // 데이터베이스에서 조회된 결과를 RowMapper를 통해 List<Todo> 형태로 매핑
        return allTodos.stream().map(TodoResponseDto::new).toList(); // 매핑된 리스트를 순회하며 각 항목을 TodoResponseDto 형태로 변환한 뒤 최종적으로 리스트로 반환
    }

    //일정 단건 조회
    @Override
    public Todo findTodoById(Long id) {
        String sql = "SELECT * FROM todo_table WHERE id = ?";
        return jdbcTemplate.query(sql, todoRowMapper, id).stream().findAny().orElse(null);
    }

    // 일정 수정
    @Override
    public void updateTodo(Todo todo) {
        String sql = "UPDATE todo_table SET task = ?, modifiedDate = ? WHERE id =?";
        jdbcTemplate.update(sql,todo.getTask(), Timestamp.valueOf(todo.getModifiedDate()), todo.getId());

    }

    // 일정 삭제
    @Override
    public void deleteTodo(Long id) {
        String sql = "DELETE FROM todo_table WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
