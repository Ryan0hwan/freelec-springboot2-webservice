package com.jojoldu.book.freelecspringboot2webservice.domain.posts;

import com.jojoldu.book.freelecspringboot2webservice.domain.BaseTimeEntity;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter            // 선언된 모든 필드의 get메서드를 생성한다.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 자동으로 생성   위 2개는 lombok껀데, 필수 어노테이션이 아니고, 별로 중요하진 않음. 그래서 위에 둔거
@Entity        // 테이블과 링크될 클래스임을 나타내는 어노테이션!!!!!!!!!
public class Posts extends BaseTimeEntity {   // 이 클래스가 실제 db의 테이블과 매칭될 클래스이며, 보통 Entity클래스라 한다.
    @Id       // 해당 테이블의 pk필드!!!!
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // pk의 생성규칙. 이거해야만 auto_increment. 즉. 레코드 추가시 해당 열의 값을 1씩 자동으로 늘려줌.
    private Long id;

    @Column(length = 500, nullable = false) // 이거 안붙여도, 클래스의 필드는 모두 Column이 되지만, 변경이 필요한 옵션이 있을때 사용함.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder  // 해당 클래스의 빌더패턴 클래스를 생성. 생성자 상단에 선언시, 생성자에 포함된 필드만 빌드에 포함.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    // 객체 생성을 단순화시키고 가독성 올린. 일종의 디자인패턴. 빌더 인스턴스는 사용자가 원하는 속성을 지정하여 객체를 생성할 수 있습니다.

    public void update(String title, String content){  // 수정기능 관련
        this.title = title;
        this.content = content;
    }
}
