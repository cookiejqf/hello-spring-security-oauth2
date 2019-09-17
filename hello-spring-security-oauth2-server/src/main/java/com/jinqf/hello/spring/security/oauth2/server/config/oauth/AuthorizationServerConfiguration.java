package com.jinqf.hello.spring.security.oauth2.server.config.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 授权客户端
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private DataSource dataSource;
//    @Resource
//    private BCryptPasswordEncoder passwordEncoder;



    @Bean
    public TokenStore tokenStore(){
        // 基于 JDBC 实现，令牌保存到数据
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService(){
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置令牌
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                // 最后一个参数为替换之后授权页面的url
                .pathMapping("/oauth/confirm_access","/custom/confirm_access");

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        内存注册
//        clients.inMemory()
//                .withClient("client")
//                .secret(passwordEncoder.encode("secret"))
//                .authorizedGrantTypes("authorization_code")
//                .scopes("app")
//                .redirectUris("https://www.baidu.com");// 异步
        clients.withClientDetails(jdbcClientDetailsService());
    }
}
