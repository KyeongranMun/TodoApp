package com.example.dto;

import com.example.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 클라이언트가 필요로 하는 정보를 전달, API 응답을 정의합니다.
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
