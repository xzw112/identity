package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.common.OperateTypeConstant;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.UserGroup;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.service.UserGroupService;
import com.tiptimes.identity.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer/userGroup")
@Api(description = "用户--组")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    /**
     * 根据分组id查询用户
     * @param groupRequest
     * @return
     */
    @RequestMapping(value = "/getUserByGroupId", method = RequestMethod.POST)
    @ApiOperation(value = "根据分组id查询用户")
    public ResponseResult getUserByGroupId(@RequestBody GroupRequest groupRequest){
        List<UserVo> list = userGroupService.selectUserListByGroupId(groupRequest);
        return ResponseResult.successWithData(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.ADD, operateDetail = "添加用户所属分组", moduleName = "用户-内部用户")
    public ResponseResult add(@RequestBody UserGroup userGroup){
        int num = userGroupService.insert(userGroup);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "编辑用户所属分组", moduleName = "用户-内部用户")
    public ResponseResult edit(@RequestBody UserGroup userGroup){
        int num = userGroupService.updateById(userGroup);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.DELETE, operateDetail = "删除用户所属分组", moduleName = "用户-内部用户")
    public ResponseResult del(Integer id){
        int num = userGroupService.del(id);
        return ResponseResult.successWithData(num);
    }
}
