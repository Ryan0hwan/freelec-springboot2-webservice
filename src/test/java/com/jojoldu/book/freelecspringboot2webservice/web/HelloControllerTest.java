package com.jojoldu.book.freelecspringboot2webservice.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)    // 스프링부트와 JUnit 사이의 연결고리 역할
@WebMvcTest(controllers = HelloController.class)   // 이거 선언시 @Controller 사용 가능. @Service 등은 사용불가.
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 빈을 주입받는다.  그러니까 멀리있는 객체를 알아서 찾아다 준다고.
    private MockMvc mvc;    // 웹 api를 테스트할때 주로 사용한다. 특히 컨트롤러.  요청을 보내고 응답을 검증함

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))      // MockMvc를 통해 /hello주소로 http get 요청. 체이닝이 지원되어 여러 검증 이어서 선언가능
                .andExpect(status().isOk())        // mvc.perform의 결과를 검증. 여기선 상태가 200(OK)인지 아닌지 검사한다. 200이면 요청이 성공적으로 반환되었다는 의미
                .andExpect(content().string(hello)); // return값이 hello가 맞게 되었는지 검사
    }

    // 롬복기능 테스트 추가
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)    // api 테스트할때 사용될 요청 파라미터 설정.
                .param("amount", String.valueOf(amount)))  // 값은 String만 허용되서 바꾼거.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //jsonPath는 응닶값을 필드별로 확인하는 매서드
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}