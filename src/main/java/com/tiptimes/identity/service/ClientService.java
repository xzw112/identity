package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.OauthClientDetails;

public interface ClientService {

    OauthClientDetails selectDetail(String clientId);

    int insert(OauthClientDetails oauthClientDetails);

    int updateById(OauthClientDetails oauthClientDetails);
}
