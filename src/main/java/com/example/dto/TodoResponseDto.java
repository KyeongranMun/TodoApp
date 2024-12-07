package com.example.dto;

import com.example.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 클라이언트가 필요로 하는 정보를 전달, API 응답을 정의
 */
@Getter
public class TodoResponseDto {
    private Long responseId;
    private String responseTask;
    private String responseAuthor;

    private LocalDateTime responCreateDate;
    private LocalDateTime responModifiedDate;

    public TodoResponseDto(Todo todo) {
        responseId = todo.getId();
        responseTask = todo.getTask();
        responseAuthor = todo.getAuthor();
        responCreateDate = todo.getCreateDate();
        responModifiedDate = todo.getModifiedDate();
    }
}
