package com.jinqf.hello.spring.security.oauth2.server.config.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * 认证用户
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        // 加密方式
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        内存认证用户
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("123456"))
//                .roles("USER")
//                .and().withUser("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN");
        // 使用自定义认证与授权
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 将 check_token 暴露出去，否则资源服务器访问时报 403 错误
        web.ignoring().antMatchers("/oauth/check_token","/swagger-ui.html/**", "/webjars/**",
                "/swagger-resources/**", "/v2/api-docs/**",
                "/swagger-resources/configuration/ui/**", "/swagger-resources/configuration/security/**",
                "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // 必须配置，不然OAuth2的http配置不生效----不明觉厉
                .requestMatchers()
                .antMatchers("/auth/login", "/auth/authorize","/oauth/authorize")
                .and()
                .authorizeRequests()
                // 自定义页面或处理url是，如果不配置全局允许，浏览器会提示服务器将页面转发多次
                .antMatchers("/auth/login", "/auth/authorize")
                .permitAll()
                .anyRequest()
                .authenticated();

        // 表单登录
        http.formLogin()
                // 登录页面
                .loginPage("/auth/login")
                // 登录处理url
                .loginProcessingUrl("/auth/authorize");
        http.httpBasic().disable();
    }
}
