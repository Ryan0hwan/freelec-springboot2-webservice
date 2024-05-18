package com.jojoldu.book.freelecspringboot2webservice.domain.posts;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)  // 스프링부트와 jnit5 사이의 연결고리
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach  // jnit에서 단위 테스트가 끝날 때마다 수행되는 메서드 지정. 보통 배포 전 전체테스트 진행시, 테스트간 데이터 침범을 막기 위해 사용.
    public void cleanup(){  // 여러 테스트가 동시에 수행되면, 테스트용 db인 h2에 데이터가 그대로 남아, 다음 테스트 실행 시 테스트가 실패할수도 있음.
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given  테스트 케이스 상황 설정. 주로 객체 초기화.
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()   // 테이블 posts에 id값이 있으면, update/ 없다면, insert 수행.
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        //when  테스트 케이스에서 주로 어떤 동작을 할지.
        List<Posts> postsList = postsRepository.findAll();  // 테이블 posts에 모든 데이터 조회.

        //then  테스트 케이스의 결과 검증. 예상과 실제결과의 비교가 이루어짐.
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);  // 가져온값이 우리가 넣은값이랑 똑같니??
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()
                +",modifiedDate="+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);  // 시간이 현재보다 미래인지 검사.
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
