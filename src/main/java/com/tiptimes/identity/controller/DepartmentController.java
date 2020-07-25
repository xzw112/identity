package com.tiptimes.identity.controller;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public PageResult getDepartmentList(@RequestBody DepartmentRequest departmentRequest) {
        PageResult<Department> list = departmentService.selectDepartmentList(departmentRequest);
        return list;
    }

    /**
     * 获取部门详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDepartmentDetail", method = RequestMethod.POST)
    public ResponseResult getDepartmentDetail(Integer id){
        Department department = departmentService.selectDetail(id);
        return ResponseResult.successWithData(department);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseResult edit(Department department){
        int num = departmentService.updateById(department);
        return ResponseResult.successWithData(num);

    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult del(Integer id){
        int num = departmentService.del(id);
        return ResponseResult.successWithData(num);

    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult add(@RequestBody Department department){
        int num = departmentService.insert(department);
        return ResponseResult.successWithData(num);

    }
}
