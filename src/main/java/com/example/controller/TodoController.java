package com.example.controller;

import com.example.dto.*;
import com.example.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * TodoController : 일정 관리와 관련한 HTTP 요청을 처리하는 역할을 하는 클래스
 * 클라이언트 요청에 따라 일정 생성(C), 일정 조회(R), 일정 수정(U), 일정 삭제(D) 기능을 제공합니다.
 */
@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService controllTodoService) {
        todoService = controllTodoService;
    }

    /**
     * 일정 생성 처리
     * @param requestDto 일정 생성 요청 데이터 ( task, createDto, password 포함)
     */
    @PostMapping
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    /**
     * 전체 일정 조회 / 특정 날짜의 일정 조회 처리
     * - date 파라미터 X : 전체 일정 조회
     * - date 파라미터 O : 해당 날짜의 일정 조회
     * @param date (선택) 조회하고자 하는 일정의 날짜 (형식 : YYYY-MM-DD )
     * 조회하려는 일정의 목록이 비었을 땐 빈 배열 ( [ ] ) 형태로 반환됩니다.
     */
    @GetMapping
    public List<TodoResponseDto> findAllTodos(@RequestParam(required = false) String date) {
        if (date == null) {
            return todoService.findAllTodos();
        }
        LocalDate localDate = LocalDate.parse(date);
        return todoService.findTodoByDate(localDate);
    }

    // 단일 일정 조회 처리
    @GetMapping("/{id}")
    public TodoResponseDto findTodoById(@PathVariable Long id) {
        return todoService.findTodoById(id);
    }

    /**
     * 일정 내용 수정 처리
     * @param updateRequestDto 수정 요청 데이터 (password, task 포함)
     */
    @PatchMapping("/{id}")
    public TodoResponseDto updateTask(@PathVariable Long id ,@RequestBody UpdateRequestDto updateRequestDto) {
        return todoService.updateTask(id, updateRequestDto);
    }

    // 일정 삭제 요청 처리
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id, @RequestBody DeleteRequestDto deleteRequestDto) {
        todoService.deleteTodo(id, deleteRequestDto);
    }
}
