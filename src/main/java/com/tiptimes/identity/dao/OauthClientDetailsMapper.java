package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.ClientRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OauthClientDetailsMapper {

    // 应用列表--应用管理用
    List<OauthClientDetails> selectClientList(ClientRequest clientRequest);

    // 查询外部用户的应用
    List<OauthClientDetails> selectOutClientList();

    // 应用列表--应用授权用
    List<OauthClientDetails> selectClientListByType(ClientRequest clientRequest);

    OauthClientDetails selectDetail(String clientId);

    int insert(OauthClientDetails oauthClientDetails);

    int del(String[] clientId);

    int updateById(OauthClientDetails oauthClientDetails);

    int selectInClientCount();

    int selectOutClientCount();
}