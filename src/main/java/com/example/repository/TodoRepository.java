package com.example.repository;

import com.example.dto.TodoResponseDto;
import com.example.entity.Todo;

import java.time.LocalDate;
import java.util.List;

/**
 * 클라이언트의 요청의 형태가 JSON(객체를 풀어놓은 것) -> 그걸 다른 계층으로 넘길 떄 RequestDto, ResponseDto 형태로 왔다갔다 함
 */
public interface TodoRepository {
    Todo saveTodo(Todo todo);
    List<TodoResponseDto> findAllTodos();
    Todo findTodoById(Long id);
    List<Todo> findTodoByDate(LocalDate createDate);
    void updateTodo(Todo todo);
    void deleteTodo(Long id);
}
