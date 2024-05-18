package com.jojoldu.book.freelecspringboot2webservice.web.dto;

import com.jojoldu.book.freelecspringboot2webservice.domain.posts.Posts;
import lombok.Getter;

@Getter  // 모든 필드 get함수 생성
public class PostsResponseDto {  // 조회 기능!!!!!!!!!!
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){  // entity객체를 가져와서 생성자 만듬
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
