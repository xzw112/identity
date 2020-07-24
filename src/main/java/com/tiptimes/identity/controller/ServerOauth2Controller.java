package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.Constants;
import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.OauthCheck;
import com.tiptimes.identity.qo.RedirectRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
@RequestMapping(value = "/server")
public class ServerOauth2Controller {


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
    public ResponseResult clientLogin(String username, String password) {
        ResponseResult result = new ResponseResult();
        String TOKEN_REQUEST_URI = localIp + PORT + "/oauth/token?grant_type=password&username=" + username + "&password=" + password;
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
     * token校验
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    public ResponseResult checkToken(String token) {
        ResponseResult result = new ResponseResult();
        String TOKEN_REQUEST_URI = localIp + PORT + "/oauth/check_token";
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("token", token);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,null);
        ResponseEntity<OauthCheck> response = rest.postForEntity( TOKEN_REQUEST_URI, request , OauthCheck.class );
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException(response.toString());
        }
        OauthCheck oauthCheck = response.getBody();
        result.setCode(ResponseCodeEnums.SUCCESS.getCode());
        result.setMessage("成功");
        result.setData(oauthCheck);
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
