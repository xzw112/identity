package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.common.ResponseCodeEnums;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.qo.OutUserInfoRequest;
import com.tiptimes.identity.qo.RegisterOutUserRequest;
import com.tiptimes.identity.qo.UserHeadRequest;
import com.tiptimes.identity.qo.UserPwdRequest;
import com.tiptimes.identity.service.TpMainAdminUserService;
import com.tiptimes.identity.vo.ProvinceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/outUser")
@Api(description = "外部用户")
public class OutUserController {

    @Autowired
    private TpMainAdminUserService tpMainAdminUserService;

    // 查询用户详情

    // 更新用户头像
    @RequestMapping(value = "/updateUserHead", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户头像")
    public ResponseResult updateUserHead(@RequestBody UserHeadRequest userHeadRequest){
        int num = tpMainAdminUserService.updateUserHead(userHeadRequest);
        return ResponseResult.successWithData(num);
    }

    @GetMapping(value = "/getCityList")
    @ApiOperation(value = "获取城市数据")
    public ResponseResult getCityList(){
        List<ProvinceVo> list = tpMainAdminUserService.cities();
        return ResponseResult.successWithData(list);
    }

    // 更新外部用户-客户端完善用户资料
    @PostMapping(value = "/updateOutUserInfo")
    @ApiOperation(value = "更新外部用户-客户端完善用户资料")
    public ResponseResult updateOutUserInfo(@RequestBody OutUserInfoRequest outUserInfoRequest){
        int num = tpMainAdminUserService.updateOutUserInfo(outUserInfoRequest);
        return ResponseResult.successWithData(num);
    }

    // 修改用户密码
    @PostMapping(value = "/updateUserPwd")
    @ApiOperation(value = "修改用户密码")
    public ResponseResult updateUserPwd(@RequestBody UserPwdRequest userPwdRequest){
        int num = tpMainAdminUserService.updateUserPwd(userPwdRequest);
        if (num == -1) {
            return ResponseResult.error(ResponseCodeEnums.FAILURE.getCode(), "重复密码输入不一致");
        } else if (num == -2) {
            return ResponseResult.error(ResponseCodeEnums.FAILURE.getCode(), "旧密码输入错误！");
        } else if (num > 0){
            return ResponseResult.successWithData(num);
        } else {
            return ResponseResult.error(ResponseCodeEnums.FAILURE.getCode(), "操作失败！");
        }
    }
    // 新增外部用户-客户端注册新用户
    @PostMapping(value = "/insertClientOutUser")
    @ApiOperation(value = "新增外部用户-客户端注册新用户")
    public ResponseResult insertClientOutUser(@RequestBody RegisterOutUserRequest registerOutUserRequest) {

        int num = tpMainAdminUserService.insertClientOutUser(registerOutUserRequest);
        if (num > 0) {
            return ResponseResult.successWithData(num);
        } else {
            return ResponseResult.error("注册失败");
        }

    }






}
