package com.example.service;

import com.example.dto.*;

import java.time.LocalDate;
import java.util.List;

/**
 * TodoService 인터페이스
 * - 서비스 계층의 계약을 정의하는 인터페이스입니다.
 */
public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto requestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findTodoById(Long requestId);
    List<TodoResponseDto> findTodoByDate(LocalDate requestCreateDate);
    TodoResponseDto updateTask(Long requestId, UpdateRequestDto updateRequestDto);
    void deleteTodo(Long requestId, DeleteRequestDto deleteRequestDto);
}
