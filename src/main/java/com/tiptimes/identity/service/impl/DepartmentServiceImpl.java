package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.tiptimes.identity.dao.DepartmentMapper;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.entity.Qo.DepartmentRequest;
import com.tiptimes.identity.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectDepartmentList(DepartmentRequest departmentRequest) {
        PageHelper.startPage(departmentRequest.getPageNumber(), departmentRequest.getPageSize());
        List<Department> list = departmentMapper.selectDepartmentList();
        return list;
    }

    @Override
    public Department selectDetail(Integer id) {
        return null;
    }

    @Override
    public int insert(Department department) {
        int num = departmentMapper.insert(department);
        return num;
    }

    @Override
    public int updateById(Department department) {
        return 0;
    }
}
