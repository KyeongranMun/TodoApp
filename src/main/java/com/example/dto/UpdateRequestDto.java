package com.example.dto;

import lombok.Getter;

/**
 * 일정 수정 요청을 수행하는 DTO 객체
 */
@Getter
public class UpdateRequestDto {
    private String task;
    private String pw;

}
