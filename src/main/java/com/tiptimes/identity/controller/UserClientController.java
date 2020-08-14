package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.Constants;
import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.dao.OauthClientDetailsMapper;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.entity.UserClient;
import com.tiptimes.identity.qo.ClientRequest;
import com.tiptimes.identity.qo.ClientUserRequest;
import com.tiptimes.identity.qo.UserClientRequest;
import com.tiptimes.identity.service.UserClientService;
import com.tiptimes.identity.utils.DateUtil;
import com.tiptimes.identity.utils.RedisUtil;
import com.tiptimes.identity.vo.ClientUserVo;
import com.tiptimes.identity.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/userClient")
public class UserClientController {

    @Autowired
    private UserClientService userClientService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TpMainAdminUserMapper tpMainAdminUserMapper;
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    /**
     * 获取登录人员的应用列表--前端
     * @param clientRequest
     * @return
     */
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
            ClientUserVo userVo = tpMainAdminUserMapper.selectUserById(userId);
            List<OauthClientDetails> list = null;
            if (userVo.getUserType() == 1) { // 内部用户
                list  = userClientService.selectUserClientList(userId);
            } else {
                // 外部应用
                list = oauthClientDetailsMapper.selectOutClientList();
            }
            return ResponseResult.successWithData(list);
        } else {
            return ResponseResult.error("请求错误！");
        }
    }

    /**
     * 获取登录人员的应用列表--后台
     * @param clientRequest
     * @return
     */
    @RequestMapping(value = "/getAdminUserClientList", method = RequestMethod.POST)
    public ResponseResult getAdminUserClientList(@RequestBody ClientRequest clientRequest){
        String userId = clientRequest.getUserId();
        if (StringUtils.isNotEmpty(userId)) {
            List<OauthClientDetails> list = userClientService.selectUserClientList(userId);
            return ResponseResult.successWithData(list);
        } else {
            return ResponseResult.error("请求错误！");
        }
    }

    /**
     * 根据应用id获取用户
     * @param clientId
     * @return
     */
    @RequestMapping(value = "/getUserByClientId", method = RequestMethod.POST)
    public ResponseResult getUserByClientId(String clientId){
        if (StringUtils.isNotEmpty(clientId)) {
            List<UserVo> list = userClientService.selectUserByClientId(clientId);
            return ResponseResult.successWithData(list);
        } else {
            return ResponseResult.error("参数有误");
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody UserClientRequest userClientRequest){
        int num = userClientService.insert(userClientRequest);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/insertByClientId", method = RequestMethod.POST)
    public ResponseResult insertByClientId(@RequestBody ClientUserRequest clientUserRequest){
        int num = userClientService.insertByClientId(clientUserRequest);
        return ResponseResult.successWithData(num);
    }
}
