package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;

import java.util.List;

public interface DepartmentService {

    List<Department> selectDepartmentList(DepartmentRequest departmentRequest);

    Department selectDetail(Integer id);

    int insert(Department department);

    int updateById(Department department);
}
