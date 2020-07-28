package com.tiptimes.identity.vo;

import com.tiptimes.identity.entity.Department;
import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentVo extends Department implements Serializable {

    private String parentName; // 父级部门名称

    private String headerName; //部门主管名称

    private String reduceHeaderName; // 部门分管领导名称
}
