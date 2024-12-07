package com.example.service;

import com.example.dto.DeleteRequestDto;
import com.example.dto.TodoRequestDto;
import com.example.dto.TodoResponseDto;
import com.example.dto.UpdateRequestDto;

import java.util.List;

/**
 * 서비스 계층의 계약을 정의하는 인터페이스
 * 모든 서비스 기능은 해당 인터페이스에 의해 규정되며 이를 구현한 클래스는 이 규칙을 따라야 합니다.
 */
public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto requestDto); // TodoRequest타입의 매개변수 requestDto를 createTodo에 담아 -> 메서드 내에서 처리 -> 나온 결과를 TodoResponseDto 형태로 반환
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findTodoById(Long requestId);
    TodoResponseDto updateTask(Long requestId, UpdateRequestDto updateRequestDto);
    void deleteTodo(Long requestId, DeleteRequestDto deleteRequestDto);
}
