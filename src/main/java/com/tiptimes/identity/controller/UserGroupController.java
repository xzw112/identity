package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.UserGroup;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.service.UserGroupService;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    /**
     * 根据分组id查询用户
     * @param groupRequest
     * @return
     */
    @RequestMapping("/getUserByGroupId")
    public ResponseResult getUserByGroupId(@RequestBody GroupRequest groupRequest){
        List<UserVo> list = userGroupService.selectUserListByGroupId(groupRequest);
        return ResponseResult.successWithData(list);
    }

    @RequestMapping("/add")
    public ResponseResult add(@RequestBody UserGroup userGroup){
        int num = userGroupService.insert(userGroup);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping("/edit")
    public ResponseResult edit(@RequestBody UserGroup userGroup){
        int num = userGroupService.updateById(userGroup);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping("/del")
    public ResponseResult del(Integer id){
        int num = userGroupService.del(id);
        return ResponseResult.successWithData(num);
    }
}
