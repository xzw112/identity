package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;

import com.tiptimes.identity.service.TpMainAdminUserService;
import com.tiptimes.identity.vo.ClientUserVo;
import com.tiptimes.identity.vo.TpMainAdminUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/clientUser")
@Api(description = "内部用户")
public class ClientUserController {

    @Autowired
    private TpMainAdminUserService tpMainAdminUserService;
    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据用户id获取用户信息")
    @PostMapping("/getUserById")
    public ResponseResult getUserById(@RequestBody String userId){
        ClientUserVo clientUserVo = tpMainAdminUserService.selectUserById(userId);
        return ResponseResult.successWithData(clientUserVo);
    }

    /**
     * 获取人员列表
     * @param adminUserParam
     * @return
     */
    @PostMapping("/getList")
    @ApiOperation(value = "获取用户列表")
    public PageResult<TpMainAdminUserVO> getList(@RequestBody AdminUserParam adminUserParam) {
        return tpMainAdminUserService.selectPageList(adminUserParam);
    }
}
