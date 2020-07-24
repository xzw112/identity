package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.ClientRequest;


public interface ClientService {

    PageResult<OauthClientDetails> selectClientList(ClientRequest clientRequest);

    OauthClientDetails selectDetail(String clientId);

    int insert(OauthClientDetails oauthClientDetails);

    int del(String[] clientId);

    int updateById(OauthClientDetails oauthClientDetails);
}
