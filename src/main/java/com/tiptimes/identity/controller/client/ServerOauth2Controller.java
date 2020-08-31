package com.tiptimes.identity.controller.client;

import com.alibaba.fastjson.JSON;
import com.tiptimes.identity.cahe.redis.VcodeManager;
import com.tiptimes.identity.common.Constants;
import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.Login;
import com.tiptimes.identity.entity.OauthCheck;
import com.tiptimes.identity.qo.MobileRequest;
import com.tiptimes.identity.qo.RedirectRequest;
import com.tiptimes.identity.utils.BASE64Util;
import com.tiptimes.identity.utils.RedisUtil;
import com.tiptimes.identity.vo.ClientUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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


@RestController
@RequestMapping(value = "/customer/server")
@Api(description = "登录相关")
public class ServerOauth2Controller {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TpMainAdminUserMapper tpMainAdminUserMapper;
    @Autowired
    private VcodeManager vcodeManager;

    private final String client_id = Constants.CLIENT_ID;
    private final String client_secret = Constants.CLIENT_SECRET;
    private final String redirect_uri = Constants.REDIRECT_URI;
    private String localIp;
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
    @RequestMapping(value = "/getCode", method = RequestMethod.POST)
    @ApiOperation(value = "获取授权码", hidden = true)
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
    @ApiOperation(value = "客户端获取token", hidden = true)
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
    @ApiOperation(value = "登录")
    public ResponseResult clientLogin(@RequestBody Login login) {
        ResponseResult result = new ResponseResult();
        String pwd = BASE64Util.getFromBase64(login.getPassword());
        String TOKEN_REQUEST_URI = localIp + PORT + "/oauth/token?grant_type=password&username=" + login.getUsername() + "&password=" + pwd;
        // 构建header
        String auth = client_id + ":" + client_secret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<OAuth2AccessToken> resp = null;
        try {
           resp = rest.postForEntity(TOKEN_REQUEST_URI, entity, OAuth2AccessToken.class);
        } catch (Exception e) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("登陆失败！");
            return result;
        }
        if (!resp.getStatusCode().equals(HttpStatus.OK)) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("登陆失败！");
            return result;
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
            redisUtil.delete(userId);
            redisUtil.setEx(userId, oAuth2AccessToken.getValue(), Constants.TIME_COUNT, Constants.UNIT);
        }
        result.setCode(ResponseCodeEnums.SUCCESS.getCode());
        result.setMessage("成功");
        result.setData(oAuth2AccessToken);
        return result;
    }

    @GetMapping("/getSmsCode")
    @ApiOperation(value = "获取手机验证码")
    public ResponseResult getSmsCode(String phoneNumber){
        ResponseResult result = new ResponseResult();
        if (StringUtils.isEmpty(phoneNumber)) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("手机号不能为空！");
        } else {
            String smsCode = vcodeManager.generateVcode();
            String redisCode = redisUtil.get(phoneNumber);
            if (!StringUtils.isEmpty(redisCode)) {
                redisUtil.delete(phoneNumber);
            }
            // 将code码存入redis
            try{
                vcodeManager.saveVcode(phoneNumber, smsCode, Constants.SMS_TIME_COUNT, Constants.SMS_UNIT);
                // 发送验证码
                int num = vcodeManager.sendSmsCode(phoneNumber,smsCode);
                if (num == 1) {
                    result.setCode(ResponseCodeEnums.SUCCESS.getCode());
                    result.setMessage("验证码发送成功！");
                } else {
                    result.setCode(ResponseCodeEnums.FAILURE.getCode());
                    result.setMessage("验证码发送失败！");
                }
            }catch (Exception e){
                result.setCode(ResponseCodeEnums.FAILURE.getCode());
                result.setMessage("验证码存储错误！");
                return result;
            }
        }
        return result;
    }

    /**
     * 客户端获取token
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/mobileLogin", method = RequestMethod.POST)
    @ApiOperation(value = "手机号登录")
    public ResponseResult mobileLogin(@RequestBody MobileRequest mobileRequest) {
        ResponseResult result = new ResponseResult();
        // 验证码验证
        // 从redis获取验证码
        String smsCode = redisUtil.get(mobileRequest.getPhoneNumber());
        String requestCode = BASE64Util.getFromBase64(mobileRequest.getCode());
        if (!requestCode.equals(smsCode)) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("验证码填写有误！");
            return result;
        }
        // 验证通过，删除redis存储的验证码
        redisUtil.delete(mobileRequest.getPhoneNumber());
        String TOKEN_REQUEST_URI = localIp + PORT + "/oauth/mobile?grant_type=mobile&mobile=" + mobileRequest.getPhoneNumber();
        // 构建header
        String auth = client_id + ":" + client_secret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<OAuth2AccessToken> resp = null;
        try {
            resp = rest.postForEntity(TOKEN_REQUEST_URI, entity, OAuth2AccessToken.class);
        } catch (Exception e) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("登陆失败！");
            return result;
        }
        if (!resp.getStatusCode().equals(HttpStatus.OK)) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("登陆失败！");
            return result;
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
            redisUtil.delete(userId);
            redisUtil.setEx(userId, oAuth2AccessToken.getValue(), Constants.TIME_COUNT, Constants.UNIT);
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
    @ApiOperation(value = "token校验")
    public ResponseResult checkToken(@RequestParam("token") String token, @RequestParam("userId") String userId) {
        ResponseResult result = new ResponseResult();
        // 验证微信 的token
        if (token.split("@").length > 1) {


        } else { // 验证oauth2 的token
            String TOKEN_REQUEST_URI = localIp + PORT + "/oauth/check_token";
            RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            map.add("token", token.split("@")[0]);
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
        }

        // 验证redis
        //OauthCheck oauthCheck = response.getBody();
        if (StringUtils.isEmpty(userId)) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("参数错误");
            return result;
        }
        String redisToken = redisUtil.get(userId);
        if (StringUtils.isEmpty(redisToken)) {
            result.setCode(ResponseCodeEnums.FAILURE.getCode());
            result.setMessage("登录失效");
        } else {
            // token校验成功返回用户基本信息
            ClientUserVo userVo = tpMainAdminUserMapper.selectUserById(userId);
            result.setCode(ResponseCodeEnums.SUCCESS.getCode());
            result.setMessage("成功");
            result.setData(userVo);
        }
        return result;
    }

    /**
     * 跳转至客户端
     */
    @RequestMapping(value = "/toClientIndex", method = RequestMethod.GET)
    @ApiOperation(value = "跳转至客户端", hidden = true)
    public void toClientIndex(RedirectRequest redirectRequest, HttpServletResponse response) throws IOException {
        response.sendRedirect(redirectRequest.getRedirectUri() + "?token=" + redirectRequest.getToken()+"&userId=" + redirectRequest.getUserId());
    }

}
