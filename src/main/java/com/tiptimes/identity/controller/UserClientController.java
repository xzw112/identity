package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.Constants;
import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.ClientRequest;
import com.tiptimes.identity.service.UserClientService;
import com.tiptimes.identity.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userClient")
public class UserClientController {

    @Autowired
    private UserClientService userClientService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/getUserClientList", method = RequestMethod.POST)
    public ResponseResult getUserClientList(@RequestBody ClientRequest clientRequest){
        // 重置redis token的有效时间
        String userId = clientRequest.getUserId();
        if (StringUtils.isNotEmpty(userId)) {
            String token = redisUtil.get(userId);
            if (StringUtils.isNotEmpty(token)) {
                redisUtil.expire(userId, Constants.TIME_COUNT, Constants.UNIT);
            } else {
                return ResponseResult.error(ResponseCodeEnums.LOGINFAIL.getCode(), "登录失效，请重新登录！");
            }
            List<OauthClientDetails> list = userClientService.selectUserClientList(userId);
            return ResponseResult.successWithData(list);
        } else {
            return ResponseResult.error("请求错误！");
        }
    }
}
