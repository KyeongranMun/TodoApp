package com.example.dto;

import com.example.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * TodoResponseDto 클래스
 * - 클라이언트가 요청한 데이터의 결과를 반환해 전송할 때 사용됩니다.
 * - 클라이언트가 필요로 하는 데이터만 포함해서 API 응답 형식을 정의합니다.
 */

@Setter
@Getter
public class TodoResponseDto {
    private Long id;
    private String task;
    private String author;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public TodoResponseDto(Todo todo) {
        id = todo.getId();
        task = todo.getTask();
        author = todo.getAuthor();
        createDate = todo.getCreateDate();
        modifiedDate = todo.getModifiedDate();
    }
}
