package com.jojoldu.book.freelecspringboot2webservice.web;

import com.jojoldu.book.freelecspringboot2webservice.service.posts.PostsService;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsResponseDto;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 컨트롤러!!  외부로부터 api를 받아옴
@RequiredArgsConstructor  //  선언된 final필드에 대해 생성자를 만들어줌.
@RestController    // 컨트롤러를 json을 반환하는 컨트롤러로 만들어준다. (Restful API)
public class PostsApiController {
    private final PostsService postsService;   // 컨트롤러 클래스에서는 서비스 필드 선언하고

    @PostMapping("/api/v1/posts")  // 이경로로 POSTS요청을 처리   등록기능!!!!!
    public Long save(@RequestBody PostsSaveRequestDto requestDto){  // RequestBody는 요청되어온 본문의 json데이터를 자동으로 옆 객체로 변환하여 받아옴
        return postsService.save(requestDto);  // 저장 후 ID반환.
    }  // 컨트롤러 클래스에선 서비스에 저장.


    @PutMapping("/api/v1/posts/{id}") // 수정 기능!!!!  변경사항 전달할때 많이 쓰임.
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);  // id반환.
    } // @PathVariable을 통해 저 경로 안의 id를 받는거야.

    @GetMapping("/api/v1/posts/{id}") // 조회기능!!!!
    public PostsResponseDto findByID(@PathVariable Long id){
        return postsService.findById(id);  // id에 해당하는 객체로 바꿔서 반환
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
