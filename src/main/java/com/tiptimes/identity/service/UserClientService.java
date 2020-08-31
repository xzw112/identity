package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.entity.UserClient;
import com.tiptimes.identity.qo.ClientTopRequest;
import com.tiptimes.identity.qo.ClientUserRequest;
import com.tiptimes.identity.qo.UserClientRequest;
import com.tiptimes.identity.vo.UserVo;

import java.util.List;

public interface UserClientService {

    List<OauthClientDetails> selectUserClientList(String userId);

    List<UserVo> selectUserByClientId(String clientId);

    UserClient selectDetail(Integer id);

    int del(Integer id);

    int insert(UserClientRequest userClientRequest);

    int insertByClientId(ClientUserRequest clientUserRequest);

    int updateById(UserClient userClient);

    int updateClientTop(ClientTopRequest clientTopRequest);

}
