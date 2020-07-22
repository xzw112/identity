package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.common.Constants;
import com.tiptimes.identity.dao.OauthClientDetailsMapper;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public OauthClientDetails selectDetail(String clientId) {
        return oauthClientDetailsMapper.selectDetail(clientId);
    }

    @Override
    public int insert(OauthClientDetails oauthClientDetails) {
        oauthClientDetails.setClientId(String.valueOf(UUID.randomUUID()));
        oauthClientDetails.setResourceIds(Constants.RESOURCE_ID);
        oauthClientDetails.setClientSecret(Constants.CLIENT_SECRET);
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
    public int updateById(OauthClientDetails oauthClientDetails) {
        return oauthClientDetailsMapper.updateById(oauthClientDetails);
    }
}
