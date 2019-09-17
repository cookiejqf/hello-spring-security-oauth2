package com.jinqf.hello.spring.security.oauth2.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.jinqf.hello.spring.security.oauth2.resource.mapper")
public class HelloSpringSecurityOauth2ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringSecurityOauth2ResourceApplication.class, args);
    }

}
