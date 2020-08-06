package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.qo.SignOutRequest;
import com.tiptimes.identity.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

/**
 * 退出
 */
@RestController
public class LogoutController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/signOut", method = RequestMethod.POST)
    public ResponseResult signOut(@RequestBody SignOutRequest signOutRequest){
        String token = signOutRequest.getToken();
        String userId = signOutRequest.getUserId();
        if (StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(userId)) {
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
            tokenStore.removeAccessToken(accessToken);
            // 清除redis
            redisUtil.delete(userId);
            return ResponseResult.successWithData("");
        } else {
            return ResponseResult.error("");
        }
    }
}
