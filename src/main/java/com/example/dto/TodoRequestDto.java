package com.example.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 클라이언트로부터 전달받은 요청 데이터
 * 컨트롤러에서 사용되어 비즈니스 로직에 필요한 데이터를 추출합니다.
 * 요청 타입 = requestDTO
 */
@Getter
public class TodoRequestDto {
    private String task;
    private String author;
    private String pw;
    private LocalDateTime createDate;
}
