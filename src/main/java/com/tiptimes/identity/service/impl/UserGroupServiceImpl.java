package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.dao.UserGroupMapper;
import com.tiptimes.identity.entity.UserGroup;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.service.UserGroupService;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public List<UserVo> selectUserListByGroupId(GroupRequest groupRequest) {
        return userGroupMapper.selectUserListByGroupId(groupRequest);
    }

    @Override
    public int insert(UserGroup userGroup) {
        return userGroupMapper.insert(userGroup);
    }

    @Override
    public int updateById(UserGroup userGroup) {
        return userGroupMapper.updateById(userGroup);
    }

    @Override
    public int del(Integer id) {
        return userGroupMapper.del(id);
    }
}
