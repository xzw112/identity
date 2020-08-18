package com.tiptimes.identity.controller.client;

import com.google.gson.Gson;
import com.tiptimes.identity.common.Constants;
import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.utils.HttpUtils;
import com.tiptimes.identity.vo.WXCallBackVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/customer/weChatCallBack")
@Api(description = "微信登录")
public class WeChatCallBackController {

    @GetMapping("/callback")
    @ApiOperation(value = "微信授权回调地址")
    public ResponseResult callback(String code, String state){
        ResponseResult result = new ResponseResult();
        // 拿着code去请求微信的固定地址，得到access_token和openid
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String accessTokenUrl = String.format(
                baseAccessTokenUrl,
                Constants.APP_ID,     // 微信id
                Constants.APP_SECRET, // 微信秘钥
                code
        );
        String accessTokenInfo = HttpUtils.getData(accessTokenUrl);
        Gson gson = new Gson();
        HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
        String access_token = (String)mapAccessToken.get("access_token");
        String open_id = (String)mapAccessToken.get("openid");
        // 此处通过open_id查询用户信息，做业务逻辑处理
        TpMainAdminUser tpMainAdminUser = null;
        if (tpMainAdminUser == null) {
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            // 拼接两个参数
            String userInfoUrl = String.format(
                    baseUserInfoUrl,
                    access_token,
                    open_id
            );
            String userInfo = HttpUtils.getData(userInfoUrl);
            // 转换map对象，并获取昵称、头像等信息
            HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
            String nickName = (String)userInfoMap.get("nickname");
            String headImgUrl = (String)userInfoMap.get("headimgurl");
        }
        // 生成jwt令牌
        WXCallBackVo wxCallBackVo = new WXCallBackVo();
        access_token = access_token + "@wx";
        wxCallBackVo.setAccess_token(access_token);
        result.setCode(ResponseCodeEnums.SUCCESS.getCode());
        result.setMessage("成功");
        result.setData(wxCallBackVo);
        return result;
    }
}
