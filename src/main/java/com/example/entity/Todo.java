package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * TodoEntity 클래스
 * - 데이터 베이스 테이블과 일대일 매핑되는 데이터 모델
 */
@Getter
@AllArgsConstructor
public class Todo {
    private Long id;
    private String author;
    private String task;
    private String pw;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public Todo(String realTask, String realAuthor, String realPw) {
        task = realTask;
        author = realAuthor;
        pw = realPw;
        createDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
    }

    public void updateTask(String modifiedTask) {
        task = modifiedTask;
        modifiedDate = LocalDateTime.now();
    }
}


