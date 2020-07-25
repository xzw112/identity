package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.DepartmentMapper;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public PageResult<Department> selectDepartmentList(DepartmentRequest departmentRequest) {
        PageHelper.startPage(departmentRequest.getPageNumber(), departmentRequest.getPageSize());
        List<Department> list = departmentMapper.selectDepartmentList(departmentRequest);
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        PageResult<Department> result = new PageResult<>();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public Department selectDetail(Integer id) {
        return departmentMapper.selectDetail(id);
    }

    @Override
    public int del(Integer id) {
        return departmentMapper.del(id);
    }

    @Override
    public int insert(Department department) {
        int num = departmentMapper.insert(department);
        return num;
    }

    @Override
    public int updateById(Department department) {
        return departmentMapper.updateById(department);
    }
}
