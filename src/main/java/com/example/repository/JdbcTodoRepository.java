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

    // 새로운 일정 생성
    @Override
    public Todo saveTodo(Todo todo) {
        String sql = "INSERT INTO todo_table(task, author, pw, createDate, modifiedDate) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, todo.getTask(), todo.getAuthor(), todo.getPw(),
                Timestamp.valueOf(todo.getCreateDate()), Timestamp.valueOf(todo.getModifiedDate()));

        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return todo;
    }

    // 전체 일정 목록 반환
    @Override
    public List<TodoResponseDto> findAllTodos() {
        String sql = "SELECT * FROM todo_table";
        List<Todo> allTodos = jdbcTemplate.query(sql, todoRowMapper);
        return allTodos.stream().map(TodoResponseDto::new).toList();
    }

    // DB에서 일정 단건 조회
    @Override
    public Todo findTodoById(Long id) {
        String sql = "SELECT * FROM todo_table WHERE id = ?";
        return jdbcTemplate.query(sql, todoRowMapper, id).stream().findAny().orElse(null);
    }

    // 날짜를 기준으로 일정 찾기
    @Override
    public List<Todo> findTodoByDate(LocalDate createDate) {
        String sql = "SELECT * FROM todo_table WHERE DATE(createDate) = ?";
        return jdbcTemplate.query(sql, todoRowMapper, createDate);
    }

    // 일정 내용 수정
    @Override
    public void updateTodo(Todo todo) {
        String sql = "UPDATE todo_table SET task = ?, modifiedDate = ? WHERE id =?";
        jdbcTemplate.update(sql,todo.getTask(), Timestamp.valueOf(todo.getModifiedDate()), todo.getId());

    }

    @Override
    public void deleteTodo(Long id) {
        String sql = "DELETE FROM todo_table WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
