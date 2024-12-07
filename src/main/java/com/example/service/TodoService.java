package com.example.service;

import com.example.dto.TodoRequestDto;
import com.example.dto.TodoResponseDto;
import java.util.List;

/**
 * 서비스 계층의 계약을 정의하는 인터페이스
 * 모든 서비스 기능은 해당 인터페이스에 의해 규정되며 이를 구현한 클래스는 이 규칙을 따라야 합니다.
 */
public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto requestDto); //
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findTodoById(Long id);
    TodoResponseDto updateTask(Long id, String task, String pw);
    void deleteTodo(Long id, String pw);
}
