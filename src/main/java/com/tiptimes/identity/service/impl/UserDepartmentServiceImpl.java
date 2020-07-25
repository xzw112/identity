package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.dao.UserDepartmentMapper;
import com.tiptimes.identity.entity.UserDepartment;
import com.tiptimes.identity.service.UserDepartmentService;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDepartmentServiceImpl implements UserDepartmentService {

    @Autowired
    private UserDepartmentMapper userDepartmentMapper;

    @Override
    public List<UserVo> selectUserByDepartmentId(Integer departmentId) {
        return userDepartmentMapper.selectUserByDepartmentId(departmentId);
    }

    @Override
    public UserDepartment selectDetail(Integer id) {
        return userDepartmentMapper.selectDetail(id);
    }

    @Override
    public int del(Integer id) {
        return userDepartmentMapper.del(id);
    }

    @Override
    public int insert(UserDepartment userDepartment) {
        return userDepartmentMapper.insert(userDepartment);
    }

    @Override
    public int updateById(UserDepartment userDepartment) {
        return userDepartmentMapper.updateById(userDepartment);
    }
}
