package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.entity.UserClient;
import com.tiptimes.identity.qo.ClientTopRequest;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClientMapper {

    List<OauthClientDetails> selectUserClientList(String userId);

    List<UserVo> selectUserByClientId(String clientId);

    UserClient selectDetail(Integer id);

    int del(Integer id);

    int delByUserId(String userId);

    int delByClientId(String clientId);

    int insert(UserClient userClient);

    int updateById(UserClient userClient);

    int updateClientTop(ClientTopRequest clientTopRequest);
}