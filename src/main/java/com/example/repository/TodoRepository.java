package com.example.repository;

import com.example.dto.TodoResponseDto;
import com.example.entity.Todo;

import java.time.LocalDate;
import java.util.List;

/**
 * TodoRepository 인터페이스
 * - 데이터베이스와의 상호 작용을 위한 기본 규약을 정의합니다.
 * - 일정 데이터를 저장, 조회, 수정, 삭제하는 메서드를 선언합니다.
 */
public interface TodoRepository {

    Todo saveTodo(Todo todo);
    List<TodoResponseDto> findAllTodos(LocalDate modifiedDate, String author);
    Todo findTodoById(Long id);
    void updateTodo(Todo todo);
    void deleteTodo(Long id);
}
