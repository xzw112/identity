package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.dao.UserClientMapper;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.entity.UserClient;
import com.tiptimes.identity.qo.ClientTopRequest;
import com.tiptimes.identity.qo.ClientUserRequest;
import com.tiptimes.identity.qo.UserClientRequest;
import com.tiptimes.identity.service.UserClientService;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    public List<UserVo> selectUserByClientId(String clientId) {
        return userClientMapper.selectUserByClientId(clientId);
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
    @Transactional
    public int insert(UserClientRequest userClientRequest) {
        int num = 0;
        //List<OauthClientDetails> list = userClientMapper.selectUserClientList(userClientRequest.getUserId());
        // 删除已有的应用
        num += userClientMapper.delByUserId(userClientRequest.getUserId());

        // 重新授权应用
        if (userClientRequest.getClientId().length > 0) {
            for (int i = 0; i < userClientRequest.getClientId().length; i++) {
                UserClient userClient = new UserClient();
                userClient.setUserId(userClientRequest.getUserId());
                userClient.setClientId(userClientRequest.getClientId()[i]);
                num += userClientMapper.insert(userClient);
            }
        }
        return num;
    }

    @Override
    @Transactional
    public int insertByClientId(ClientUserRequest clientUserRequest) {
        int num = userClientMapper.delByClientId(clientUserRequest.getClientId());
        String[] userIds = clientUserRequest.getUserId();
        if (userIds.length > 0) {
            for (int i = 0; i < userIds.length; i++) {
                UserClient userClient = new UserClient();
                userClient.setClientId(clientUserRequest.getClientId());
                userClient.setUserId(userIds[i]);
                num += userClientMapper.insert(userClient);
            }
        }
        return num;
    }

    @Override
    public int updateById(UserClient userClient) {
        return 0;
    }

    @Override
    public int updateClientTop(ClientTopRequest clientTopRequest) {
        clientTopRequest.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return userClientMapper.updateClientTop(clientTopRequest);
    }
}
