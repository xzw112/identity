package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapper {

    Group selectDetail(Integer id);

    int insert(Group group);

    int updateById(Group group);
}