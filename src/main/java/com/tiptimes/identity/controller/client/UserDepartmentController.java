package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.UserDepartment;
import com.tiptimes.identity.service.UserDepartmentService;
import com.tiptimes.identity.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户部门
 */
@RestController
@RequestMapping("/customer/userDepartment")
@Api(description = "用户部门")
public class UserDepartmentController {

    @Autowired
    private UserDepartmentService userDepartmentService;

    /**
     * 根据部门id获取用户
     * @param departmentId
     * @return
     */
    @RequestMapping(value = "/getUserByDepartmentId", method = RequestMethod.POST)
    @ApiOperation(value = "根据部门id获取用户")
    public ResponseResult getUserByDepartmentId(Integer departmentId){
        List<UserVo> list = null;
        if (departmentId > 0) {
            list = userDepartmentService.selectUserByDepartmentId(departmentId);
        }
        return ResponseResult.successWithData(list);
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    @ApiOperation(value = "详情", hidden = true)
    public ResponseResult getDetail(Integer id){
        UserDepartment userDepartment = null;
        if (id > 0) {
           userDepartment = userDepartmentService.selectDetail(id);
        }
         return ResponseResult.successWithData(userDepartment);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑", hidden = true)
    public ResponseResult edit(@RequestBody UserDepartment userDepartment){
        int num = userDepartmentService.updateById(userDepartment);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除", hidden = true)
    public ResponseResult del(Integer id) {
        int num = userDepartmentService.del(id);
        return ResponseResult.successWithData(num);
    }
}
