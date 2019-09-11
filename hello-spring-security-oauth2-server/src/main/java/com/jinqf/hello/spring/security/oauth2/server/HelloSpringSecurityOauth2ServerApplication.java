package com.jinqf.hello.spring.security.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.jinqf.hello.spring.security.oauth2.server.mapper")
@EnableDiscoveryClient
public class HelloSpringSecurityOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringSecurityOauth2ServerApplication.class, args);
    }

}
