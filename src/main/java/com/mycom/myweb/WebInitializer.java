package com.mycom.myweb;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // 아까 만든 WebConfig 파일을 여기서 연결해주는 겁니다!
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // 모든 요청(/)을 스프링이 처리하게 합니다.
        return new String[] { "/" };
    }
}