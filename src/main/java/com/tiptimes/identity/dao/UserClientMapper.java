package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.entity.UserClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClientMapper {

    List<OauthClientDetails> selectUserClientList(String userId);

    UserClient selectDetail(Integer id);

    int del(Integer id);

    int delByUserId(String userId);

    int insert(UserClient userClient);

    int updateById(UserClient userClient);
}