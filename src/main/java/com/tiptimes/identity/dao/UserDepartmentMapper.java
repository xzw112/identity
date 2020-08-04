package com.tiptimes.identity.dao;


import com.tiptimes.identity.entity.UserDepartment;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDepartmentMapper {

    // 根据部门id， 获取用户
    List<UserVo> selectUserByDepartmentId(Integer departmentId);

    List<UserDepartment> selectUserDepartmentListByUserId(String userId);

    UserDepartment selectDetail(Integer id);

    int del(Integer id);

    int delByUserId(String userId);

    int insert(UserDepartment userDepartment);

    int updateById(UserDepartment userDepartment);
}