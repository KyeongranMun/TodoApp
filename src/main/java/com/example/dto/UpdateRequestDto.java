package com.example.dto;

import lombok.Getter;

/**
 * UpdateRequestDto 클래스
 * 클라이언트가 일정 수정 요청을 할 때 필요한 데이터 결과를 반환하고 전송합니다.
 */
@Getter
public class UpdateRequestDto {
    private String task;
    private String pw;

}
