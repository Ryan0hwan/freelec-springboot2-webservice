package com.jojoldu.book.freelecspringboot2webservice.web.dto;

import com.jojoldu.book.freelecspringboot2webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter  // 모든 필드 get함수 생성
@NoArgsConstructor    // 매개변수 없는 기본생성자 생성
public class PostsSaveRequestDto {   // 등록기능!!!!!!!!!!!!!!!!
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

