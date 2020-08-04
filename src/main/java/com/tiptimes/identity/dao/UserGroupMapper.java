package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.UserGroup;
import com.tiptimes.identity.qo.GroupRequest;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupMapper {

    List<UserVo> selectUserListByGroupId(GroupRequest groupRequest);

    UserGroup selectDetail(Integer id);

    int insert(UserGroup userGroup);

    int updateById(UserGroup userGroup);

    int del(Integer id);

    int delByUserId(String userId);
}