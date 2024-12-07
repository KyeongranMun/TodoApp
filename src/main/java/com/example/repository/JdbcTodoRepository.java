package com.example.repository;

import com.example.dto.TodoResponseDto;
import com.example.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;

import java.util.List;


@Repository
public class JdbcTodoRepository implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTodoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Todo> todoRowMapper = (rs, rowNum) -> new Todo(
            rs.getLong("id"),
            rs.getString("task"),
            rs.getString("author"),
            rs.getString("pw"),
            rs.getTimestamp("createDate").toLocalDateTime(),
            rs.getTimestamp("modifiedDate").toLocalDateTime()
    );

    // 새로운 일정 생성
    @Override
    public Todo saveTodo(Todo todo) {
        String sql = "INSERT INTO todo_table(task, author, pw, createDate, modifiedDate) VALUES (?, ?, ?, ?, ?)";
        System.out.println("POST");
        jdbcTemplate.update(sql, todo.getTask(), todo.getAuthor(), todo.getPw(),
                Timestamp.valueOf(todo.getCreateDate()), Timestamp.valueOf(todo.getModifiedDate()));

        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return todo;
    }

    @Override
    public List<TodoResponseDto> findAllTodos() {
        String sql = "SELECT * FROM todo_table";
        List<Todo> allTodos = jdbcTemplate.query(sql, todoRowMapper);
        return allTodos.stream().map(TodoResponseDto::new).toList();
    }

    @Override
    public Todo findTodoById(Long id) {
        String sql = "SELECT * FROM todo_table WHERE id = ?";
        return jdbcTemplate.query(sql, todoRowMapper, id).stream().findAny().orElse(null);
    }

    @Override
    public void updateTodo(Todo todo) {

    }

    @Override
    public void deleteTodo(Long id) {

    }
}
