package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 데이터베이스 테이블과 일대일로 매핑되는 핵심 클래스
 * 데이터베이스의 테이블에 존재하는 컴럼들을 필드로 갖습니다.
 */
@Getter
@AllArgsConstructor
public class Todo {
    private Long id; // 고유 식별자 (Primary Key)
    private String author; // 작성자
    private String task; // 일정 내용
    private String pw; // 유효성 검증을 위한 비밀번호

    private LocalDateTime createDate; // 최초 작성일
    private LocalDateTime modifiedDate; // 수정일 ( 최초 생성 당시엔 작성일과 동일 )

    // 작성일 초기값을 현재 시간으로 설정
    public Todo(String realTask, String realAuthor, String realPw) {
        task = realTask;
        author = realAuthor;
        pw = realPw;
        createDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
    }

    // task 수정 시 사용할 메서드 - 수정 시간 업데이트
    public void updateTask(String modifiedTask) {
        task = modifiedTask;
        modifiedDate = LocalDateTime.now();
    }
}


