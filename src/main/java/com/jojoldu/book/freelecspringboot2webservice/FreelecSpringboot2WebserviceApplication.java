package com.jojoldu.book.freelecspringboot2webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing  // jpa auditing기능 활성화
@SpringBootApplication  // 항상 이게 있는 위치부터 설정을 읽어감. 따라서 이 main클래스는 항상 프로젝트 최상단에 위치해야함.
public class FreelecSpringboot2WebserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreelecSpringboot2WebserviceApplication.class, args);
	}
}
