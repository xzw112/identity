package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.UserGroup;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.vo.UserVo;

import java.util.List;


public interface UserGroupService {

    List<UserVo> selectUserListByGroupId(GroupRequest groupRequest);

    int insert(UserGroup userGroup);

    int updateById(UserGroup userGroup);

    int del(Integer id);
}
