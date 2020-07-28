package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.Group;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.vo.GroupVo;


public interface GroupService {

    PageResult<GroupVo> selectGroupList(GroupRequest groupRequest);

    Group selectDetail(Integer id);

    int insert(Group group);

    int updateById(Group group);

    int del(Integer id);
}
