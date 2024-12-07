package com.example.dto;

import lombok.Getter;

/**
 * 클라이언트로부터 전달받은 요청 데이터
 * 컨트롤러에서 사용되어 비즈니스 로직에 필요한 데이터 추출
 * 요청 타입 = requestDTO
 */
@Getter
public class TodoRequestDto {
    private String requestTask;
    private String requestAuthor;
    private String requestPw;
}
