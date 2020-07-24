package com.tiptimes.identity.config;

import com.tiptimes.identity.vo.UserDetailsVo;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 登录后自定义返回用户信息
 */

@Component
public class CustomAdditionalInformation implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = oAuth2AccessToken.getAdditionalInformation();
        Object obj = oAuth2Authentication.getUserAuthentication().getPrincipal();
        info.put("userInfo", obj);
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
//        UserDetailsVo user = (UserDetailsVo) oAuth2Authentication.getPrincipal();
//        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("username", user.getLoginName());
//        map.put("userId", user.getId());
//        token.setAdditionalInformation(map);
//        return oAuth2AccessToken;
    }
}
