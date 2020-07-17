package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.OauthClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsMapper {

    OauthClientDetails selectDetail(String clientId);

    int insert(OauthClientDetails oauthClientDetails);

    int updateById(OauthClientDetails oauthClientDetails);
}