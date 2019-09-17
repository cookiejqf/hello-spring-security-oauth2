package com.jinqf.hello.spring.security.oauth2.resource.service;

import com.jinqf.hello.spring.security.oauth2.resource.domain.TbContent;

import java.util.List;

public interface TbContentService{

    /**
     * 获取全部数据
     *
     * @return {@link List<TbContent>}
     */
    default List<TbContent> selectAll() {
        return null;
    }
}
