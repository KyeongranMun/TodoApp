package com.example.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * TodoRequestDto 클래스
 * - 클라이언트에서 서버로 전송되는 요청 데이터를 담는 객체
 * - 일정 생성 요청에서 필요한 데이터를 포함합니다.
 * - 클라이언트 요청 데이터를 컨트롤러에서 받아 처리합니다.
 * - 비즈니스 로직에서 필요한 데이터만 전달합니다.
 */
@Getter // @Getter 애너테이션 : 클래스 내 모든 필드에 대해 Getter 메서드를 자동으로 생성합니다.
public class TodoRequestDto {
    private String task; // 클라이언트가 입력한 일정 내용
    private String author; // 클라이언트가 입력한 작성자 이름
    private String pw; // 클라이언트가 설정한 비밀번호로, 일정 수정 및 삭제 시 필요
}
