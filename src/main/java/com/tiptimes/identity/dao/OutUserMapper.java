package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.OutUser;
import org.springframework.stereotype.Repository;

@Repository
public interface OutUserMapper {


    OutUser selectDetail(String id);

    int insert(OutUser outUser);

    int updateById(OutUser outUser);
}