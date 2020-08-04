package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.UserDepartment;
import com.tiptimes.identity.service.UserDepartmentService;
import com.tiptimes.identity.vo.UserVo;
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
public class UserDepartmentController {

    @Autowired
    private UserDepartmentService userDepartmentService;

    /**
     * 根据部门id获取用户
     * @param departmentId
     * @return
     */
    @RequestMapping(value = "/getUserByDepartmentId", method = RequestMethod.POST)
    public ResponseResult getUserByDepartmentId(Integer departmentId){
        List<UserVo> list = null;
        if (departmentId > 0) {
            list = userDepartmentService.selectUserByDepartmentId(departmentId);
        }
        return ResponseResult.successWithData(list);
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    public ResponseResult getDetail(Integer id){
        UserDepartment userDepartment = null;
        if (id > 0) {
           userDepartment = userDepartmentService.selectDetail(id);
        }
         return ResponseResult.successWithData(userDepartment);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseResult edit(@RequestBody UserDepartment userDepartment){
        int num = userDepartmentService.updateById(userDepartment);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult del(Integer id) {
        int num = userDepartmentService.del(id);
        return ResponseResult.successWithData(num);
    }
}
