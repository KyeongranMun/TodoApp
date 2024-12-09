package com.example.service;

import com.example.dto.*;
import com.example.entity.Todo;
import com.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import java.util.List;

/**
 * TodoService 인터페이스를 구현한 구현체 클래스
 */
@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    // 일정 생성
    @Override
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getTask(), requestDto.getAuthor(),requestDto.getPw());
        Todo saveTodo = todoRepository.saveTodo(todo);

        return new TodoResponseDto(saveTodo);
    }

    // 전체 일정 조회
    @Override
    public List<TodoResponseDto> findAllTodos() {
        return todoRepository.findAllTodos();
    }
    // 일정 단건 조회
    @Override
    public TodoResponseDto findTodoById(Long id) {
        Todo todo = todoRepository.findTodoById(id);
        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다.");
        }
        return new TodoResponseDto(todo);
    }

    // 일정 검색 - 날짜로 검색
    @Override
    public List<TodoResponseDto> findTodoByDate(LocalDate requestCreateDate) {
        List<Todo> allTodos = todoRepository.findTodoByDate(requestCreateDate);
        return allTodos.stream().map(TodoResponseDto::new).toList();
    }

    // 일정 수정
    @Override
    public TodoResponseDto updateTask(Long id, UpdateRequestDto updateRequestDto) {
        Todo todo = todoRepository.findTodoById(id);
        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다.");
        }
        if (!updateRequestDto.getPw().equals(todo.getPw())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        if (updateRequestDto.getTask() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 내용이 빈 칸입니다. 내용을 작성해주세요");
        }
        todo.updateTask(updateRequestDto.getTask());
        todoRepository.updateTodo(todo);
        return new TodoResponseDto(todo);
    }
    // 일정 삭제
    @Override
    public void deleteTodo(Long id, DeleteRequestDto deleteRequestDto) {
        Todo todo = todoRepository.findTodoById(id);
        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " 일정을 찾을 수 없습니다.");
        }
        if (!deleteRequestDto.getPw().equals(todo.getPw())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        todoRepository.deleteTodo(id);
    }
}
