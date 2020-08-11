package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.qo.SignOutRequest;
import com.tiptimes.identity.utils.HttpUtils;
import com.tiptimes.identity.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
            clientLoginOut("http://study2.tiptimes.com/identityLogout");
            return ResponseResult.successWithData("");
        } else {
            return ResponseResult.error("");
        }
    }

    public void clientLoginOut(String url){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity resp = rest.postForEntity(url, entity, null);
    }

}
