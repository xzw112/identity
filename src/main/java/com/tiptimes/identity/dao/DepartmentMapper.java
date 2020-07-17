package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    List<Department> selectDepartmentList();

    Department selectDetail(Integer id);

    int insert(Department department);


    int updateById(Department department);
}