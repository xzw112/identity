package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.entity.UserClient;

import java.util.List;

public interface UserClientService {

    List<OauthClientDetails> selectUserClientList(String userId);

    UserClient selectDetail(Integer id);

    int del(Integer id);

    int insert(UserClient userClient);

    int updateById(UserClient userClient);

}
