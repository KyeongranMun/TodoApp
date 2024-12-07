package com.example.service;

import com.example.dto.TodoRequestDto;
import com.example.dto.TodoResponseDto;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * TodoService 인터페이스를 구현한 구현체 클래스
 */
@Service
public class TodoServiceImpl implements TodoService{
    // 일정 생성
    @Override
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        return null;
    }
    // 전체 일정 조회
    @Override
    public List<TodoResponseDto> findAllTodos() {
        return List.of();
    }
    // 일정 단건 조회
    @Override
    public TodoResponseDto findTodoById(Long id) {
        return null;
    }
    // 일정 생성
    @Override
    public TodoResponseDto updateTask(Long id, String task, String pw) {
        return null;
    }
    // 일정 삭제
    @Override
    public void deleteTodo(Long id, String pw) {

    }
}
