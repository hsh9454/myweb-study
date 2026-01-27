package com.mycom.myweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/test", produces = "text/plain;charset=UTF-8")
    
    public String sayHello() {
        return " 스프링 6 세팅 완료 !! 01-26 공부 일지 !";
    }
}