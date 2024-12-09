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
 * - 비즈니스 로직을 처리하는 서비스 계층
 * - 요청 데이터를 검증하고, Repository와 상호 작용하여 결과를 반환
 * - 예외 상황에 적절한 HTTP 상태 코드를 반환하도록 처리
 */
@Service // 서비스 계층이라는 것을 알리는 애너테이션
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    /**
     * 생성자 의존성 주입을 통한 TodoRepository 초기화
     * @param todoRepository 일정 데이터를 처리할 저장소 인터페이스
     */
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * 일정 생성
     * - 클라이언트에서 요청한 데이터를 받아 새로운 일정(Todo)를 생성합니다.
     * - 생성된 일정은 데이터베이스에 저장된 후 응답 DTO로 반환됩니다.
     * @param requestDto 클라이언트가 보낸 일정 생성 요청 데이터
     * @return 생성된 일정 데이터를 포함한 응답 DTO
     */
    @Override
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getTask(), requestDto.getAuthor(),requestDto.getPw()); // 요청 데이터를 바탕으로 새 Todo 엔티티 생성
        Todo saveTodo = todoRepository.saveTodo(todo); // 생성된 Todo를 저장소에 저장

        return new TodoResponseDto(saveTodo); // 저장된 엔티티를 응답 DTO로 변환하여 반환
    }

    /**
     * 전체 일정 조회 : 데이터베이스에 저장된 모든 일정을 조회합니다.
     * @return 전체 일정 데이터를 포함한 응답 DTO 리스트
     */
    @Override
    public List<TodoResponseDto> findAllTodos() {
        return todoRepository.findAllTodos();
    }

    /**
     * 일정 단건 조회 : 고유 식별자를 기반으로 특정 일정을 조회하며, 일정이 존재하지 않을 경우 404 NOT_FOUND 상태코드와 함께 메시지를 출력합니다. (예외처리)
     * @param id 조회할 일정의 고유 식별자
     * @return 조회된 일정의 데이터를 포함한 응답 DTO
     */
    @Override
    public TodoResponseDto findTodoById(Long id) {
        Todo todo = todoRepository.findTodoById(id);
        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다.");
        }
        return new TodoResponseDto(todo);
    }

    /**
     * 특정 날짜를 기준 일정 조회 : 특정 날짜를 기준으로 해당 날짜에 생성된 일정을 조회합니다.
     * @param requestCreateDate 조회 기준 날짜
     * @return 조회된 일정 데이터를 포함한 응답 DTO 리스트
     */
    @Override
    public List<TodoResponseDto> findTodoByDate(LocalDate requestCreateDate) {
        List<Todo> allTodos = todoRepository.findTodoByDate(requestCreateDate);
        return allTodos.stream().map(TodoResponseDto::new).toList();
    }

    /**
     * 일정 수정 : 일정의 내용만을 수정하며 수정 요청 시 비밀번호 검증이 필요합니다. 수정할 내용이 빈 칸이거나 일정이 존재하지 않을 경우, 비밀번호가 틀릴 경우 각각 404, 401, 400 상태코드와 함께 메시지를 반환합니다.
     * @param id 수정할 일정의 고유 식별자
     * @param updateRequestDto 수정 요청 데이터를 포함한 DTO
     * @return 수정된 일정 데이터를 포함한 응답 DTO
     */
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
        todo.updateTask(updateRequestDto.getTask()); // 일정 수정 및 데이터베이스 업데이트
        todoRepository.updateTodo(todo);
        return new TodoResponseDto(todo);
    }

    /**
     * 일정 삭제 : 일정 삭제 요청 시 비밀번호 검증이 필요하며, 삭제하려는 일정이 존재하지 않거나 비밀번호가 일치하지 않을 경우 각각 404, 401 상태코드와 함께 메시지가 반환됩니다.
     * @param id 삭제할 일정의 고유 식별자
     * @param deleteRequestDto 삭제 요청 데이터를 포함한 응답 DTO
     */
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
