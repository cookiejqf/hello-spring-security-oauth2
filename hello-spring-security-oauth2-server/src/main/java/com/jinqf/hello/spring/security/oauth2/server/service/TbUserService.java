package com.jinqf.hello.spring.security.oauth2.server.service;

import com.jinqf.hello.spring.security.oauth2.server.domain.TbUser;

public interface TbUserService{
    TbUser getByUsername(String username);
}
