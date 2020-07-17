package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.entity.Qo.DepartmentRequest;
import com.tiptimes.identity.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取部门列表
     * @return
     */
    @RequestMapping(value = "/getDepartmentList", method = RequestMethod.POST)
    public ResponseResult getDepartmentList(DepartmentRequest departmentRequest) {
        List<Department> list = departmentService.selectDepartmentList(departmentRequest);
        return ResponseResult.successWithData(list);
    }
}
