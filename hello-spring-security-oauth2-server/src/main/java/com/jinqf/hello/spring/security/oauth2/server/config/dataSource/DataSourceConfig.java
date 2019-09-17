package com.jinqf.hello.spring.security.oauth2.server.config.dataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary // 以此配置为准
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        // 配置数据源
        return DataSourceBuilder.create().build();
    }
}
