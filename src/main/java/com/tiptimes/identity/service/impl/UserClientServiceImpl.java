package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.dao.UserClientMapper;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.entity.UserClient;
import com.tiptimes.identity.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserClientServiceImpl implements UserClientService {

    @Autowired
    private UserClientMapper userClientMapper;
    @Override
    public List<OauthClientDetails> selectUserClientList(String userId) {
        return userClientMapper.selectUserClientList(userId);
    }

    @Override
    public UserClient selectDetail(Integer id) {
        return null;
    }

    @Override
    public int del(Integer id) {
        return 0;
    }

    @Override
    public int insert(UserClient userClient) {
        return 0;
    }

    @Override
    public int updateById(UserClient userClient) {
        return 0;
    }
}
