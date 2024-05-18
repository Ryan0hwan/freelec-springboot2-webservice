package com.jojoldu.book.freelecspringboot2webservice.web.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        // given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);      // 앞이 생성자를 통해 얻은거고(검증하고싶음), 뒤가 우리가 설정한거.  같은지 테스트하는거야
        assertThat(dto.getAmount()).isEqualTo(amount);  // assertThat은 검증하고싶은걸 매서드 인자로 받음.
    }
}
