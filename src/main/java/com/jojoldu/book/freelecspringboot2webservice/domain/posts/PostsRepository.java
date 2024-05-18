package com.jojoldu.book.freelecspringboot2webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc(); // SPringDataJpa에서 제공되지 않는 매소드는 위처럼 쿼리로 작성해도 된다.
}

// Posts클래스로 DB에 접근하기 위해 이 레포지토리를 만들었다.
// JPARepository<Entity 클래스, PK타입>를 상속하면, 기본적인 CRUD매서드가 자동으로 생성됨.
// @Repository추가할 필요도 없음. 주의할점은 Entity클래스와 Entity Repository(이거)는 함께 위치해야 한다.
