package com.tiptimes.identity.service;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.vo.TopDepartmentVo;

import java.util.List;

public interface DepartmentService {

    PageResult<Department> selectDepartmentList(DepartmentRequest departmentRequest);

    Department selectDetail(Integer id);

    List<TopDepartmentVo> selectTopDepartmentByUserId(String userId);

    int del(Integer id);

    int insert(Department department);

    int updateById(Department department);
}
