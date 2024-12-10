package com.example.dto;

import lombok.Getter;

/**
 * TodoRequestDto 클래스
 * - 클라이언트에서 서버로 전송되는 요청 데이터를 담는 객체
 */
@Getter
public class TodoRequestDto {
    private String task;
    private String author;
    private String pw;
}
