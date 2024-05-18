package com.jojoldu.book.freelecspringboot2webservice.web;

import com.jojoldu.book.freelecspringboot2webservice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 컨트롤러를 json반환하는 컨트롤러로 만들어줌
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
     // 롬복기능을 여기다가 구현해보자
    @GetMapping("/hello/dto")     // @RequestParam("name") String name : 외부에서 name이란 이름으로 넘긴 파라미터를 파라미터 name에 저장.
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
