package com.tiptimes.identity.controller;

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
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 获取组列表
     * @param groupRequest
     * @return
     */
    @RequestMapping("/getGroupList")
    public PageResult getClientList(@RequestBody GroupRequest groupRequest){
        PageResult<GroupVo> list = groupService.selectGroupList(groupRequest);
        return list;
    }
    /**
     * 获取组列表--树状
     * @param
     * @return
     */
    @RequestMapping("/getGroupTreeList")
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
    public ResponseResult getDetail(String id){
        Group group = null;
        if (StringUtils.isNotEmpty(id)) {
            group = groupService.selectDetail(Integer.valueOf(id));
        }
        return ResponseResult.successWithData(group);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult add(HttpServletRequest request,@RequestBody Group group){
        String userId = (String) request.getSession().getAttribute("userId");
        group.setCreateUser(userId);
        int num = groupService.insert(group);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseResult edit(HttpServletRequest request, @RequestBody Group group){
        String userId = (String) request.getSession().getAttribute("userId");
        group.setCreateUser(userId);
        group.setUpdateUser(userId);
        group.setUpdateTime(new Date());
        int num = groupService.updateById(group);
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult del(String id){
        int num = groupService.del(Integer.valueOf(id));
        return ResponseResult.successWithData(num);
    }
}
