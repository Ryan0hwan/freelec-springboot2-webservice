package com.jojoldu.book.freelecspringboot2webservice.web;

import com.jojoldu.book.freelecspringboot2webservice.domain.posts.Posts;
import com.jojoldu.book.freelecspringboot2webservice.domain.posts.PostsRepository;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsResponseDto;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) // 스프링부트와 junit5사이의 연결고리
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest { // 테스트를 실행할 때 무작위 포트를 사용하여 내장된 서블릿 컨테이너를 시작하는 옵션. 이렇게 하면 테스트 간에 포트 충돌이 발생하지 않습니다.

    @LocalServerPort // 위에 저 randomport랑 같이 쓰이는거. port변수에 그 무작위로 가져온 포트를 저장.
    private int port;

    @Autowired // 스프링부트의 테스트 지원을 위한 클래스. 테스트 시에 HTTP 요청을 보내고 응답을 받는 데 사용됩니다.
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach  // 테스트 끝날때마다, h2데이터베이스에 뭐가 안남도록, 삭제해주겠다.
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test   // 아래 매서드는 junit의 테스트 매서드입니다.  등록 테스트!!!!
    public void Posts_등록된다() throws Exception{
        // given   상황. 주로 변수선언
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts"; // 테스트할 주소 지정.

        // when    그때 뭘하겠다.   게시물을 등록하네.  ResponseEntity : HTTP 통신을 간편하게 처리할 수 있는 클래스
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then   검증
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); // 일단 응답이 200인지
        assertThat(responseEntity.getBody()).isGreaterThan(0L); // 응답 게시물의 id가 0보다 큰지

        List<Posts> all = postsRepository.findAll();  // 모든 게시물 조회.
        assertThat(all.get(0).getTitle()).isEqualTo(title);  // 조회된 첫 게시물의 제목이이 우리가 넣은값과 일치하는지
        assertThat(all.get(0).getContent()).isEqualTo(content); //  내용이 같은지
    }

    @Test
    public void Posts_수정된다() throws Exception{   // 수정기능 테스트
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()    // 수정내용으로 이걸 넣음
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();      // 예상값 변수 선언
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();
        String ur1 = "http://localhost:" + port + "/api/v1/posts/"+updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(ur1, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
