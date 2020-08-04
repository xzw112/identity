package com.tiptimes.identity.controller;

import com.alibaba.fastjson.JSON;
import com.tiptimes.identity.common.Constants;
import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Login;
import com.tiptimes.identity.entity.OauthCheck;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.qo.RedirectRequest;
import com.tiptimes.identity.utils.RedisUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = "/customer/server")
public class ServerOauth2Controller {

    @Autowired
    private RedisUtil redisUtil;


    private final String client_id = Constants.CLIENT_ID;
    private final String client_secret = Constants.CLIENT_SECRET;
    private final String redirect_uri = Constants.REDIRECT_URI;
    private  String localIp;
    @Value("${server.port}")
    private long PORT;

    ServerOauth2Controller () {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            localIp = "http://" + addr.getHostAddress()+":";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取授权码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取code的URI
        String CODE_REQUEST_URI = localIp + PORT + "/oauth/authorize?client_id=" + client_id + "&response_type=code&scope=ALL&redirect_uri=" + redirect_uri;
        response.sendRedirect(CODE_REQUEST_URI);
    }

    /**
     * 客户端获取token
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getAccessToken", method = RequestMethod.GET)
    public ResponseResult getAccessToken(String code) {
        ResponseResult result = new ResponseResult();

        String TOKEN_REQUEST_URI = localIp + PORT + "/oauth/token?grant_type=authorization_code&code=" + code + "&redirect_uri=" + redirect_uri;
        // 构建header
        String auth = client_id + ":" + client_secret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<OAuth2AccessToken> resp = rest.postForEntity(TOKEN_REQUEST_URI, entity, OAuth2AccessToken.class);
        if (!resp.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException(resp.toString());
        }
        OAuth2AccessToken oAuth2AccessToken = resp.getBody();
        result.setCode(ResponseCodeEnums.SUCCESS.getCode());
        result.setMessage("成功");
        result.setData(oAuth2AccessToken);
        return result;
    }

    /**
     * 客户端获取token
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/clientLogin", method = RequestMethod.POST)
    public ResponseResult clientLogin(@RequestBody Login login) {
        ResponseResult result = new ResponseResult();
        String TOKEN_REQUEST_URI = localIp + PORT + "/oauth/token?grant_type=password&username=" + login.getUsername() + "&password=" + login.getPassword();
        // 构建header
        String auth = client_id + ":" + client_secret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<OAuth2AccessToken> resp = rest.postForEntity(TOKEN_REQUEST_URI, entity, OAuth2AccessToken.class);
        if (!resp.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException(resp.toString());
        }
        OAuth2AccessToken oAuth2AccessToken = resp.getBody();
        // 将token存入redis库(用户token存入redis 2天)
        Object obj =  oAuth2AccessToken.getAdditionalInformation().get("userInfo");
        Map<String, String> map = JSON.parseObject(JSON.toJSONString(obj),LinkedHashMap.class);
        String userId = map.get("id");
        String token = redisUtil.get(userId);
        if (StringUtils.isEmpty(token)) {
            redisUtil.setEx(userId, oAuth2AccessToken.getValue(), Constants.TIME_COUNT, Constants.UNIT);
        } else {
            redisUtil.expire(userId, Constants.TIME_COUNT, Constants.UNIT);
        }
        result.setCode(ResponseCodeEnums.SUCCESS.getCode());
        result.setMessage("成功");
        result.setData(oAuth2AccessToken);
        return result;
    }


    /**
     * token校验
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    public ResponseResult checkToken(@RequestParam("token") String token, @RequestParam("userId") String userId) {
        ResponseResult result = new ResponseResult();
        String TOKEN_REQUEST_URI = localIp + PORT + "/oauth/check_token";
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("token", token);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,null);
        ResponseEntity<OauthCheck> response = null;
        try {
            response = rest.postForEntity(TOKEN_REQUEST_URI, request , OauthCheck.class );
        } catch (Exception e) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("登录失效");
            return result;
        }

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException(response.toString());
        }
        OauthCheck oauthCheck = response.getBody();
        String redisToken = redisUtil.get(userId);
        if (StringUtils.isEmpty(redisToken)) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("登录失效");
        } else {
            result.setCode(ResponseCodeEnums.SUCCESS.getCode());
            result.setMessage("成功");
        }
        result.setData(token);
        return result;
    }

    /**
     * 跳转至客户端
     */
    @RequestMapping(value = "/toClientIndex", method = RequestMethod.GET)
    public void toClientIndex(RedirectRequest redirectRequest, HttpServletResponse response) throws IOException {
        response.sendRedirect(redirectRequest.getRedirectUri() + "?token=" + redirectRequest.getToken()+"&userId=" + redirectRequest.getUserId());
    }



}
