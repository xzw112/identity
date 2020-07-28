package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.GroupMapper;
import com.tiptimes.identity.entity.Group;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.service.GroupService;
import com.tiptimes.identity.vo.GroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;


    @Override
    public PageResult<GroupVo> selectGroupList(GroupRequest groupRequest) {
        PageHelper.startPage(groupRequest.getPageNumber(), groupRequest.getPageSize());
        List<GroupVo> list = groupMapper.selectGroupList(groupRequest);
        PageInfo<GroupVo> pageInfo = new PageInfo<>(list);
        PageResult result = new PageResult();
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Group selectDetail(Integer id) {
        return groupMapper.selectDetail(id);
    }

    @Override
    public int insert(Group group) {
        group.setIsDel(0);
        group.setCreateTime(new Date());
        return groupMapper.insert(group);
    }

    @Override
    public int updateById(Group group) {
        return groupMapper.updateById(group);
    }

    @Override
    public int del(Integer id) {
        // 查询该岗位下是否有人员

        return groupMapper.delGroup(id);
    }
}
