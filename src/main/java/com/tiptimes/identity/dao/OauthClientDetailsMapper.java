package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.ClientRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OauthClientDetailsMapper {

    List<OauthClientDetails> selectClientList(ClientRequest clientRequest);

    OauthClientDetails selectDetail(String clientId);

    int insert(OauthClientDetails oauthClientDetails);

    int del(String[] clientId);

    int updateById(OauthClientDetails oauthClientDetails);
}