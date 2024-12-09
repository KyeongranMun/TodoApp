package com.example.controller;

import com.example.dto.*;
import com.example.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 상태코드는 컨트롤러에서 반환하면 된다. 굳이 다른 계층을 왔다갔다 할 필요가 없다.
 */
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService controllTodoService) {
        todoService = controllTodoService;
    }

    // 일정 생성
    @PostMapping
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    // 전체 일정 조회
    @GetMapping
    public List<TodoResponseDto> findAllTodos() {
        return todoService.findAllTodos();
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public TodoResponseDto findTodoById(@PathVariable Long id) {
        return todoService.findTodoById(id);
    }

    // 일정 조회 - 날짜로 검색
    @GetMapping("/date{createDate}")
    public List<TodoResponseDto> finTodoByDate(@PathVariable String createDate) {
        // 날짜 파라미터 LocalDate로 변환
        LocalDate localDate = LocalDate.parse(createDate);
        return todoService.findTodoByDate(localDate);
    }

    // 일정 내용 수정 ( 비밀번호 검증 필수, task != null )
    @PatchMapping("/{id}")
    public TodoResponseDto updateTask(@PathVariable Long id ,@RequestBody UpdateRequestDto updateRequestDto) {
        return todoService.updateTask(id, updateRequestDto);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id, @RequestBody DeleteRequestDto deleteRequestDto) {
        todoService.deleteTodo(id, deleteRequestDto);
    }
}
