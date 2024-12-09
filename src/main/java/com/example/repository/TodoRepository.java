package com.example.repository;

import com.example.dto.TodoResponseDto;
import com.example.entity.Todo;

import java.time.LocalDate;
import java.util.List;

/**
 * TodoRepository 인터페이스
 * - 데이터베이스와의 상호 작용을 위한 기본 규약을 정의합니다.
 * - 일정 데이터를 저장, 조회, 수정, 삭제하는 메서드를 선언합니다.
 * - 클라이언트 요청 흐름 : Controller -> Service -> Repository
 * - 요청 데이터는 엔티티(Todo) 형태로 변환되어 처리됩니다.
 */
public interface TodoRepository {

    /**
     * 새로운 일정 저장 : Todo 엔티티를 데이터베이스에 저장합니다.
     * @param todo 저장할 일정 엔티티
     * @return 저장된 일정 엔티티 (자동 생성된 id 포함)
     */
    Todo saveTodo(Todo todo);

    /**
     * 모든 일정 조회 : 데이터베이스에 저장된 모든 일정을 조회합니다.
     * @return 일정 목록을 TodoResponseDto 리스트 형태로 반환
     */
    List<TodoResponseDto> findAllTodos();

    /**
     * 특정 일정 단건 조회 : 고유 식별자 id를 기준으로 데이터를 조회합니다.
     * @param id 조회할 일정의 고유 식별자
     * @return 조회된 일정 엔티티
     */
    Todo findTodoById(Long id);

    /**
     * 특정 날짜 기준 일정 조회 : 요청된 날짜를 기준으로 해당 날짜에 생성된 일정들을 조회합니다.
     * @param createDate 조회 기준 날짜
     * @return 해당 날짜에 생성된 일정 엔티티 리스트
     */
    List<Todo> findTodoByDate(LocalDate createDate);

    /**
     * 일정 수정 : 수정된 일정 데이터를 데이터베이스에 반영합니다.
     * @param todo 수정된 일정 엔티티
     */
    void updateTodo(Todo todo);

    /**
     * 일정 삭제 : 고유 식별자를 기준으로 데이터를 삭제합니다.
     * @param id 삭제할 일정의 고유 식별자
     */
    void deleteTodo(Long id);
}
