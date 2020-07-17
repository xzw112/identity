package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.UserGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupMapper {

    UserGroup selectDetail(Integer id);

    int insert(UserGroup userGroup);

    int updateById(UserGroup userGroup);
}