package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;

import java.util.List;

public interface DepartmentService {

    PageResult<Department> selectDepartmentList(DepartmentRequest departmentRequest);

    Department selectDetail(Integer id);

    int del(Integer id);

    int insert(Department department);

    int updateById(Department department);
}
