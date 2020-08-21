package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.common.OperateTypeConstant;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.entity.Group;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.service.GroupService;
import com.tiptimes.identity.vo.DepartmentTreeVo;
import com.tiptimes.identity.vo.GroupTreeVo;
import com.tiptimes.identity.vo.GroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/customer/group")
@Api(description = "组")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 获取组列表
     * @param groupRequest
     * @return
     */
    @RequestMapping(value = "/getGroupList", method = RequestMethod.POST)
    @ApiOperation(value = "获取组列表--分页")
    public PageResult getClientList(@RequestBody GroupRequest groupRequest){
        PageResult<GroupVo> list = groupService.selectGroupList(groupRequest);
        return list;
    }
    /**
     * 获取组列表--树状
     * @param
     * @return
     */
    @RequestMapping(value = "/getGroupTreeList", method = RequestMethod.POST)
    @ApiOperation(value = "获取组列表--树状")
    public ResponseResult getGroupTreeList(){
        GroupRequest groupRequest = new GroupRequest();
        groupRequest.setPageNumber(1);
        groupRequest.setPageSize(Integer.MAX_VALUE);
        PageResult<GroupVo> list = groupService.selectGroupList(groupRequest);
        List<GroupTreeVo> treeList = new ArrayList<>();
        List<GroupVo> groupList = list.getRows();
        for (int i = 0; i < groupList.size(); i++) {
            GroupVo groupVo = groupList.get(i);
            GroupTreeVo groupTreeVo = new GroupTreeVo();
            groupTreeVo.setId(groupVo.getId());
            if (groupVo.getParentId() == 0) {
                groupTreeVo.setParent("#");
            } else {
                groupTreeVo.setParent(groupVo.getParentId().toString());
            }
            groupTreeVo.setText(groupVo.getGroupName());
            treeList.add(groupTreeVo);
        }
        return ResponseResult.successWithData(treeList);
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    @ApiOperation(value = "组详情", hidden = true)
    public ResponseResult getDetail(String id){
        Group group = null;
        if (StringUtils.isNotEmpty(id)) {
            group = groupService.selectDetail(Integer.valueOf(id));
        }
        return ResponseResult.successWithData(group);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.ADD, operateDetail = "添加分组", moduleName = "用户-机构与组")
    public ResponseResult add(HttpServletRequest request,@RequestBody Group group){
        String userId = (String) request.getSession().getAttribute("userId");
        group.setCreateUser(userId);
        int num = groupService.insert(group);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "编辑分组", moduleName = "用户-机构与组")
    public ResponseResult edit(HttpServletRequest request, @RequestBody Group group){
        String userId = (String) request.getSession().getAttribute("userId");
        group.setCreateUser(userId);
        group.setUpdateUser(userId);
        group.setUpdateTime(new Date());
        int num = groupService.updateById(group);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除", hidden = true)
    @SystemLog(operateType = OperateTypeConstant.DELETE, operateDetail = "删除分组", moduleName = "用户-机构与组")
    public ResponseResult del(String id){
        int num = groupService.del(Integer.valueOf(id));
        return ResponseResult.successWithData(num);
    }
}
