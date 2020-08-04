package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.service.DepartmentService;
import com.tiptimes.identity.vo.DepartmentTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("//customer/department")
@Api(description = "组织架构")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取部门列表
     * @return
     */
    @ApiOperation(value = "获取部门列表--分页")
    @RequestMapping(value = "/getDepartmentList", method = RequestMethod.POST)
    public PageResult getDepartmentList(@RequestBody DepartmentRequest departmentRequest) {
        PageResult<Department> list = departmentService.selectDepartmentList(departmentRequest);
        return list;
    }

    /**
     * 获取部门列表--树状
     * @return
     */
    @ApiOperation(value = "获取部门列表--树状")
    @RequestMapping(value = "/getDepartmentTreeList", method = RequestMethod.POST)
    public ResponseResult getDepartmentTreeList() {
        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentRequest.setPageNumber(1);
        departmentRequest.setPageSize(Integer.MAX_VALUE);
        List<DepartmentTreeVo> treeList = new ArrayList<>();
        PageResult<Department> list = departmentService.selectDepartmentList(departmentRequest);
        List<Department> departmentList = list.getRows();
        for (int i = 0; i < departmentList.size(); i++) {
            Department department = departmentList.get(i);
            DepartmentTreeVo departmentTreeVo = new DepartmentTreeVo();
            departmentTreeVo.setId(department.getId());
            if (department.getParentId() == 0) {
                departmentTreeVo.setParent("#");
            } else {
                departmentTreeVo.setParent(department.getParentId().toString());
            }
            departmentTreeVo.setText(department.getName());
            treeList.add(departmentTreeVo);
        }
        return ResponseResult.successWithData(treeList);
    }

    /**
     * 获取部门详情
     * @param id
     * @return
     */
    @ApiOperation(value = "获取部门详情")
    @RequestMapping(value = "/getDepartmentDetail", method = RequestMethod.POST)
    public ResponseResult getDepartmentDetail(@RequestBody Integer id){
        Department department = departmentService.selectDetail(id);
        return ResponseResult.successWithData(department);

    }

    @ApiOperation(value = "部门--编辑", hidden = true)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseResult edit(@RequestBody Department department){
        int num = departmentService.updateById(department);
        return ResponseResult.successWithData(num);

    }

    @ApiOperation(value = "部门--删除", hidden = true)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseResult del(Integer id){
        int num = departmentService.del(id);
        return ResponseResult.successWithData(num);

    }


    @ApiOperation(value = "部门--添加", hidden = true)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult add(@RequestBody Department department, HttpServletRequest request){
        String userId = (String)request.getSession().getAttribute("userId");
        department.setCreateUser(userId);
        int num = departmentService.insert(department);
        return ResponseResult.successWithData(num);

    }
}
