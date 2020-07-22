package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.Constants;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping(value = "/server")
public class ServerOauth2Controller {

    private final String client_id = Constants.CLIENT_ID;
    private final String client_secret = Constants.CLIENT_SECRET;
    private final String redirect_uri = Constants.REDIRECT_URI;
    @Value("${server.port}")
    private  long PORT;



    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取code的URI
        String CODE_REQUEST_URI = "http://localhost:" + PORT + "/oauth/authorize?client_id=" + client_id + "&response_type=code&scope=ALL&redirect_uri=" + redirect_uri;
        response.sendRedirect(CODE_REQUEST_URI);
    }

    /**
     * 客户端获取token
     * @param
     * @return
     */
    @RequestMapping(value = "/getAccessToken", method = RequestMethod.GET)
    public OAuth2AccessToken getAccessToken(String code){

            String TOKEN_REQUEST_URI = "http://localhost:" + PORT + "/oauth/token?grant_type=authorization_code&code=" + code + "&redirect_uri=" + redirect_uri;
            // 构建header
            String auth = client_id + ":" + client_secret;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
            String authHeader = "Basic " + new String(encodedAuth);
            RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType( MediaType.TEXT_PLAIN );
            headers.add("authorization", authHeader);
            HttpEntity<String> entity = new HttpEntity<String>(null, headers);
            ResponseEntity<OAuth2AccessToken> resp = rest.postForEntity( TOKEN_REQUEST_URI, entity, OAuth2AccessToken.class);
            if( !resp.getStatusCode().equals( HttpStatus.OK )){
                throw new RuntimeException( resp.toString() );
            }
            OAuth2AccessToken oAuth2AccessToken = resp.getBody();
            return oAuth2AccessToken;
        }
}
