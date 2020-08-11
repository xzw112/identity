package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;

import com.tiptimes.identity.qo.OutUserRequest;
import com.tiptimes.identity.service.TpMainAdminUserService;
import com.tiptimes.identity.vo.ClientUserVo;
import com.tiptimes.identity.vo.OutUserVo;
import com.tiptimes.identity.vo.TpMainAdminUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult getUserById(@RequestParam String userId){
        ClientUserVo clientUserVo = tpMainAdminUserService.selectUserById(userId);
        return ResponseResult.successWithData(clientUserVo);
    }

    /**
     * 获取人员列表 -- 内部用户
     * @param adminUserParam
     * @return
     */
    @PostMapping("/getList")
    @ApiOperation(value = "获取用户列表--内部用户")
    public PageResult<TpMainAdminUserVO> getList(@RequestBody AdminUserParam adminUserParam) {
        return tpMainAdminUserService.selectPageList(adminUserParam);
    }

    /**
     * 获取人员列表 -- 外部用户
     * @param outUserRequest
     * @return
     */
    @PostMapping("/getOutUserList")
    @ApiOperation(value = "获取人员列表 -- 外部用户")
    public PageResult<OutUserVo> getOutUserList(@RequestBody OutUserRequest outUserRequest) {
        return tpMainAdminUserService.selectOutUserList(outUserRequest);
    }
}
