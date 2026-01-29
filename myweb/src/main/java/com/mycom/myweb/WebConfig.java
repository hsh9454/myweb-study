package com.mycom.myweb;

import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mycom.myweb")
@ImportResource("/WEB-INF/spring/root-context.xml")
public class WebConfig {
   
}