package com.jojoldu.book.freelecspringboot2webservice.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter  // 필드들 모두 생성자 만들어주기
@MappedSuperclass // JPA Entity클래스들이 이 클래스를 상속할 경우, 필드들도 칼럼으로 인식.
@EntityListeners(AuditingEntityListener.class) // 이 클래스에 변경 추적기능 포함시킴
public abstract class BaseTimeEntity {
    @CreatedDate // Entity가 생성되어, 저장될 때의 시간 자동 저장.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity값 변경시, 시간 자동 저장.
    private LocalDateTime modifiedDate;
}

// 이 클래스는 모든 클래스의 상위 클래스가 되어, Entity들의 createdDate와 modifiedDate를 자동으로 관리하는 역할.