package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.common.Constants;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.OauthClientDetailsMapper;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.ClientRequest;
import com.tiptimes.identity.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public PageResult<OauthClientDetails> selectClientList(ClientRequest clientRequest) {
        PageHelper.startPage(clientRequest.getPageNumber(), clientRequest.getPageSize());
        List<OauthClientDetails> list = oauthClientDetailsMapper.selectClientList(clientRequest);
        PageInfo<OauthClientDetails> pageInfo = new PageInfo<>(list);
        PageResult<OauthClientDetails> result = new PageResult<>();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public OauthClientDetails selectDetail(String clientId) {
        return oauthClientDetailsMapper.selectDetail(clientId);
    }

    @Override
    public int insert(OauthClientDetails oauthClientDetails) {
        oauthClientDetails.setClientId(String.valueOf(UUID.randomUUID()));
        oauthClientDetails.setResourceIds(Constants.RESOURCE_ID);
        oauthClientDetails.setClientSecret(BCrypt.hashpw(Constants.CLIENT_SECRET, BCrypt.gensalt()));
        oauthClientDetails.setScope(Constants.SCOPE);
        oauthClientDetails.setAuthorizedGrantTypes(Constants.AUTHORIZED_GRANT_TYPES);
        oauthClientDetails.setWebServerRedirectUri(Constants.WEB_SERVER_REDIRECT_URI);
        oauthClientDetails.setAccessTokenValidity(Constants.ACCESS_TOKEN_VALIDITY);
        oauthClientDetails.setRefreshTokenValidity(Constants.REFRESH_TOKEN_VALIDITY);
        oauthClientDetails.setCreateTime(new Date());
        byte num = 0;
        oauthClientDetails.setArchived(num);
        oauthClientDetails.setTrusted(num);
        oauthClientDetails.setAutoapprove("true");
        return oauthClientDetailsMapper.insert(oauthClientDetails);
    }

    @Override
    public int del(String[] clientId) {
        return oauthClientDetailsMapper.del(clientId);
    }

    @Override
    public int updateById(OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsMapper.updateById(oauthClientDetails);
    }
}
