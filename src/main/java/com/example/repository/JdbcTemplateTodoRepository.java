package com.example.repository;

import com.example.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTemplateTodoRepository implements TodoRepository {
    @Override
    public Todo saveTodo(Todo todo) {
        return null;
    }

    @Override
    public List<Todo> findAllTodos() {
        return List.of();
    }

    @Override
    public Todo findTodoById(Long id) {
        return null;
    }

    @Override
    public void updateTodo(Todo todo) {

    }

    @Override
    public void deleteTodo(Long id) {

    }
}
