package com.example.dto;

import lombok.Getter;

/**
 * UpdateRequestDto 클래스
 * 클라이언트가 일정 수정 요청을 할 때 필요한 데이터 결과를 반환하고 전송합니다.
 */
@Getter // 모든 필드에 대한 Getter 메서드 자동 생성
public class UpdateRequestDto {
    private String task;
    private String pw;

}
