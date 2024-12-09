package com.example.controller;

import com.example.dto.*;
import com.example.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * TodoController : 일정 관리와 관련한 HTTP 요청을 처리하는 역할을 하는 클래스
 * 클라이언트 요청에 따라 일정 생성(C), 일정 조회(R), 일정 수정(U), 일정 삭제(D) 기능을 제공합니다.
 * @RestController : RESTful 웹 서비스 컨트롤러로 작동합니다.
 * @RequestMapping : ("/todos")는 '/todos'로 시작하는 URL에 매핑됩니다.
 */

@RestController
@RequestMapping("/todos")
public class TodoController {
    // TodoService 인터페이스를 사용해 비즈니스 로직을 처리합니다.
    private final TodoService todoService;

    /**
     * TodoController 생성자
     * 의존성 주입 방식으로 TodoService 구현체를 주입받습니다.
     * @param controllTodoService 서비스 계층의 구현체
     */
    public TodoController(TodoService controllTodoService) {
        todoService = controllTodoService;
    }

    /**
     * 일정 생성 요청을 처리합니다.
     * HTTP POST 요청을 수신하며, 요청 본문으로 전달된 JSON 데이터를 TodoResponseDto 객체로 매핑합니다.
     * @param requestDto 일정 생성 요청 데이터 ( task, createDto, password 포함)
     * @return 생성된 일정의 정보를 TodoResponse 형태로 반환합니다.
     */
    @PostMapping
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    /**
     * 전체 일정 조회 또는 특정 날짜의 일정 조회를 처리합니다.
     * HTTP POST 요청을 수신하며, 'date' 파라미터에 따라 동작이 달라집니다.
     * - date 파라미터 X : 전체 일정 조회
     * - date 파라미터 O : 해당 날짜의 일정 조회
     * @param date (선택) 조회하고자 하는 일정의 날짜 (형식 : YYYY-MM-DD )
     * @return 일정 목록을 TodoResponseDto 리스트 형태로 반환합니다.
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

    /**
     * 단일 일정 조회를 처리합니다.
     * HTTP GET 요청에서 {id} 경로 변수를 통해 특정 일정의 ID를 전달받습니다.
     * @param id 수정할 일정의 고유 식별자 (PK)
     * @return 해당 ID의 일정 정보를 TodoResponseDto 형태로 반환합니다.
     */
    @GetMapping("/{id}")
    public TodoResponseDto findTodoById(@PathVariable Long id) {
        return todoService.findTodoById(id);
    }

    /**
     * 일정의 내용을 수정합니다. 수정 시 비밀번호 검증이 필수적입니다.
     * HTTP PATCH 요청을 수신하며, 수정하려는 일정의 ID와 수정 데이터가 포함된 요청 본문을 받습니다.
     * @param id 수정할 일정의 고유 식별자 (PK)
     * @param updateRequestDto 수정 요청 데이터 (password, task 포함)
     * @return 수정된 일정 정보를 TodoResponse 형태로 반환합니다.
     */
    @PatchMapping("/{id}")
    public TodoResponseDto updateTask(@PathVariable Long id ,@RequestBody UpdateRequestDto updateRequestDto) {
        return todoService.updateTask(id, updateRequestDto);
    }

    /**
     * 일정 삭제 요청을 처리합니다. 삭제 시 비밀번호 검증이 필수적입니다.
     * HTTP DELETE 요청을 수신하며, 삭제하려는 일정의 ID와 삭제 데이터 요청 본문을 받습니다.
     * @param id 삭제할 일정의 고유 식별자 (PK)
     * @param deleteRequestDto 삭제 요청 데이터 (password 포함)
     */
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id, @RequestBody DeleteRequestDto deleteRequestDto) {
        todoService.deleteTodo(id, deleteRequestDto);
    }
}
