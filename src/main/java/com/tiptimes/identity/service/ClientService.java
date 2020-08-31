package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.ClientRequest;

import java.util.List;


public interface ClientService {

    // 应用列表--应用管理用
    PageResult<OauthClientDetails> selectClientList(ClientRequest clientRequest);

    // 应用列表--应用授权用
    PageResult<OauthClientDetails> selectClientListByType(ClientRequest clientRequest);

    OauthClientDetails selectDetail(String clientId);

    int insert(OauthClientDetails oauthClientDetails);

    int del(String[] clientId);

    int updateById(OauthClientDetails oauthClientDetails);

    int selectInClientCount();

    int selectOutClientCount();
}
