package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.ResponseResult;

import com.tiptimes.identity.service.TpMainAdminUserService;
import com.tiptimes.identity.vo.ClientUserVo;
import com.tiptimes.identity.vo.UserDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientUser")
public class ClientUserController {

    @Autowired
    private TpMainAdminUserService tpMainAdminUserService;
    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    @PostMapping("/getUserById")
    public ResponseResult getUserById(String userId){
        ClientUserVo clientUserVo = tpMainAdminUserService.selectUserById(userId);
        return ResponseResult.successWithData(clientUserVo);
    }
}
