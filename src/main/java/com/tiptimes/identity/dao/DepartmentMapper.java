package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    List<Department> selectDepartmentList(DepartmentRequest departmentRequest);

    Department selectDetail(Integer id);

    int del(Integer id);

    int insert(Department department);

    int updateById(Department department);
}