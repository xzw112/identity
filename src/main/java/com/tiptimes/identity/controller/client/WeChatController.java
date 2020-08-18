package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.common.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

@Controller
@RequestMapping("/customer/weChat")
@Api(description = "微信登录")
public class WeChatController {

    @GetMapping("/weChatLogin")
    @ApiOperation(value = "微信登录")
    public String weChatLogin(){
        String wxUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 对回调地址进行编码
        String redirect_url = Constants.WX_REDIRECT_URL;
        try {
            redirect_url = URLEncoder.encode(redirect_url,"utf-8");
        } catch (Exception e){
            e.printStackTrace();
        }
        String url = String.format(wxUrl,Constants.APP_ID, redirect_url,"xzw");
        return "redirect:" + url;
    }


}
