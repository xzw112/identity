package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.UserDepartment;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDepartmentMapper {

    UserDepartment selectDetail(Integer id);

    int insert(UserDepartment userDepartment);

    int updateById(UserDepartment userDepartment);
}