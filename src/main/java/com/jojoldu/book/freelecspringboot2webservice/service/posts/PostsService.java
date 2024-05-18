package com.jojoldu.book.freelecspringboot2webservice.service.posts;

import com.jojoldu.book.freelecspringboot2webservice.domain.posts.Posts;
import com.jojoldu.book.freelecspringboot2webservice.domain.posts.PostsRepository;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsListResponseDto;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsResponseDto;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 등록기능 서비스!!  트랜잭션과 도메인의 순서를 보장.
@RequiredArgsConstructor    // final 필드 모두 생성자 생성
@Service                    // 이 클래스가 서비스 클래스임을 알려주는 역할
public class PostsService {
    private final PostsRepository postsRepository;  // 서비스 클래스에서는 레포지토리 필드를 선언하네.

    @Transactional  // 트랜잭션 관리.  해당 매서드에서 실행되는 과정이 모두 하나의 트랜잭션으로 묶임.
    public Long save(PostsSaveRequestDto requestDto){  // 요청 데이터 받아서, 엔티티로 변환해서 저장후, id반환.
        return postsRepository.save(requestDto.toEntity()).getId();
    }  // 서비스 클래스에선 레포지토리에 저장

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){  // 수정기능
        Posts posts = postsRepository.findById(id)  // 레포에 자동으로 CRUD메서드 만들어지잖아.
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){  // 조회기능
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 전체조회 기능 화면추가. readOnly = true함으로써, 조회기능만 남겨두어 속도 up
    public List<PostsListResponseDto> findAllDesc(){  // 게시물을 내림차순으로 조회한 후, 저 객체로 매핑.
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){ // 삭제기능. 조회 후 존재하면 엔티티 파라미터로 삭제. id로 삭제하는것도 가능.
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);  // JpaRepository에서 delete매서드 제공.
    }
}
