package com.example.service;

import com.example.dto.*;

import java.time.LocalDate;
import java.util.List;

/**
 * TodoService 인터페이스
 * - 서비스 계층의 계약을 정의하는 인터페이스입니다.
 * - 일정 관련 비즈니스 로직을 처리하는 메서드들을 선언합니다.
 * - 모든 구현체는 이 인터페이스의 메서드를 구현해야 합니다.
 */
public interface TodoService {

    /**
     * 새로운 일정 생성 : 클라이언트로부터 전달받은 요청 데이터를 기반으로 일정을 생성합니다.
     * @param requestDto 클라이언트 요청 데이터 (일정 정보 포함)
     * @return 생성된 일정의 상세 정보를 담은 TodoResponseDto
     */
    TodoResponseDto createTodo(TodoRequestDto requestDto); // TodoRequest타입의 매개변수 requestDto를 createTodo에 담아 -> 메서드 내에서 처리 -> 나온 결과를 TodoResponseDto 형태로 반환

    /**
     * 전체 일정 조회 : 데이터베이스에 저장된 모든 일정을 조회하여 반환합니다.
     * @return 모든 일정의 TodoResponseDto 리스트
     */
    List<TodoResponseDto> findAllTodos();

    /**
     * 특정 일정 단건 조회 : 고유 식별자 id를 기준으로 특정 일정을 조회합니다.
     * @param requestId 조회할 일정의 고유 식별자
     * @return 조회된 일정의 상세 정보를 담은 TodoResponseDto
     */
    TodoResponseDto findTodoById(Long requestId);

    /**
     * 특정 날짜 기준 일정 조회 : 클라이언트가 요청한 날짜를 기준으로 일정을 조회합니다.
     * @param requestCreateDate 조회 기준 날짜
     * @return 해당 날짜에 해당하는 일정의 TodoResponseDto 리스트
     */
    List<TodoResponseDto> findTodoByDate(LocalDate requestCreateDate);

    /**
     * 일정 내용 수정 : 특정 일정의 내용을 수정하며 수정 요청 데이터와 고유 식별자를 기반으로 작업합니다.
     * @param requestId 수정할 일정의 고유 식별자
     * @param updateRequestDto 수정 요청 데이터 (수정할 내용 포함)
     * @return 수정된 일정의 상세 정보를 담은 TodoResponseDto
     */
    TodoResponseDto updateTask(Long requestId, UpdateRequestDto updateRequestDto);

    /**
     * 일정 삭제 : 고유 식별자와 삭제 요청 데이터를 검증하여 해당 일정을 삭제합니다.
     * @param requestId 삭제할 일정의 고유 식별자
     * @param deleteRequestDto 삭제 요청 데이터 (비밀번호 포함)
     */
    void deleteTodo(Long requestId, DeleteRequestDto deleteRequestDto);
}
