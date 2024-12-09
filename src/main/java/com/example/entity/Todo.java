package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Todo Entity 클래스
 * - 데이터 베이스 테이블과 일대일 매핑되는 데이터 모델
 * - 일정 데이터의 구조와 속성을 정의합니다.
 * - 데이터베이스와 상호작용하거나 로직에서 일정 데이터를 처리할 때 사용됩니다.
 */
@Getter
@AllArgsConstructor
public class Todo {
    private Long id; // 고유 식별자 (Primary Key) - 데이터베이스에서 자동 생성 (Auto Incremental)
    private String author; // 작성자
    private String task; // 일정 내용
    private String pw; // 유효성 검증을 위한 비밀번호

    private LocalDateTime createDate; // 최초 작성일
    private LocalDateTime modifiedDate; // 수정일 ( 최초 생성 당시엔 작성일과 동일, 수정 작업 시 갱신 )

    /**
     * 기본 생성자
     * - 새 일정을 생성할 때 사용됩니다.
     * - 작업 시간과 수정 시간의 초기값은 현재 시간으로 설정됩니다.
     * @param realTask 일정 내용
     * @param realAuthor 작성자 이름
     * @param realPw 수정/삭제를 위한 비밀번호. 사용자가 글을 최초로 작성할 때 설정하는 값
     */
    public Todo(String realTask, String realAuthor, String realPw) {
        task = realTask;
        author = realAuthor;
        pw = realPw;
        createDate = LocalDateTime.now(); // 작성 시간 초기화
        modifiedDate = LocalDateTime.now(); // 수정 시간 초기화
    }

    /**
     * 일정 수정 메서드
     * - 일정 내용을 수정하고, 수정된 시간을 갱신합니다.
     * @param modifiedTask 수정된 일정 내용
     */
    public void updateTask(String modifiedTask) {
        task = modifiedTask; // 새로운 내용으로 기존 내용 업데이트
        modifiedDate = LocalDateTime.now(); // 수정 시간 갱신
    }
}


