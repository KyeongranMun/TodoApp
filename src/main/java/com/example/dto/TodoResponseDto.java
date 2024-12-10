package com.example.dto;

import com.example.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * TodoResponseDto 클래스
 * - 클라이언트가 요청한 데이터의 결과를 반환해 전송할 때 사용됩니다.
 * - 클라이언트가 필요로 하는 데이터만 포함해서 API 응답 형식을 정의합니다.
 * - Todo 엔티티 객체를 기반으로 필요한 데이터를 추출해 응답 DTO로 변환합니다.
 */

@Setter // 모든 필드에 대한 Setter 메서드 자동 생성
@Getter // 모든 필드에 대한 Getter 메서드 자동 생성
public class TodoResponseDto {
    private Long id;
    private String task;
    private String author;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    /**
     * 생성자
     * - 엔티티 객체(Todo)를 받아 DTO로 변환합니다.
     * - 엔티티의 내부 데이터를 클라이언트가 이해할 수 있는 형식으로 제공합니다.
     * @param todo 엔티티 객체로, 데이터베이스에서 조회된 일정 데이터입니다.
     */
    public TodoResponseDto(Todo todo) {
        id = todo.getId(); // 엔티티의 id값을 DTO에 복사
        task = todo.getTask();
        author = todo.getAuthor();
        createDate = todo.getCreateDate();
        modifiedDate = todo.getModifiedDate();
    }
}
