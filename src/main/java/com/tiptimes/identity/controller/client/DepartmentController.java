package com.tiptimes.identity.controller.client;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.common.OperateTypeConstant;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.service.DepartmentService;
import com.tiptimes.identity.utils.TreeEntity;
import com.tiptimes.identity.utils.TreeHelper;
import com.tiptimes.identity.vo.DepartmentTreeVo;
import com.tiptimes.identity.vo.TopDepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/customer/department")
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

    @ApiOperation(value = "获取部门列表vue用--树状")
    @RequestMapping(value = "/getDepartmentClientTreeList", method = RequestMethod.POST)
    public ResponseResult getDepartmentClientTreeList(){
        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentRequest.setPageNumber(1);
        departmentRequest.setPageSize(Integer.MAX_VALUE);
        PageResult<Department> list = departmentService.selectDepartmentList(departmentRequest);
        List<TreeEntity> treeEntityList = new ArrayList<>();
        if (list.getRows() != null){
            List<Department> departmentList = list.getRows();
            if (departmentList.size() > 0) {
                for (int i = 0; i < departmentList.size(); i++) {
                    Department department = departmentList.get(i);
                    TreeEntity treeEntity = new TreeEntity();
                    treeEntity.setId(String.valueOf(department.getId()));
                    treeEntity.setParentId(department.getParentId() == 0?"":String.valueOf(department.getParentId()));
                    treeEntity.setText(department.getName());
                    treeEntityList.add(treeEntity);
                }
            }
        }
        List<TreeEntity> endEntityList = TreeHelper.generateTree(treeEntityList);
        return ResponseResult.successWithData(endEntityList);

    }

    /**
     * 获取部门详情
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户所属不进门的顶级部门")
    @RequestMapping(value = "/getTopDepartment", method = RequestMethod.POST)
    @ApiImplicitParam(name = "userId", value = "用户id", dataType = "String")
    public ResponseResult getTopDepartment(String userId){
        List<TopDepartmentVo> list = departmentService.selectTopDepartmentByUserId(userId);
        return ResponseResult.successWithData(list);
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
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "修改部门", moduleName = "用户-机构与组")
    public ResponseResult edit(@RequestBody Department department){
        int num = departmentService.updateById(department);
        return ResponseResult.successWithData(num);

    }

    @ApiOperation(value = "部门--删除", hidden = true)
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.DELETE, operateDetail = "删除部门", moduleName = "用户-机构与组")
    public ResponseResult del(Integer id){
        int num = departmentService.del(id);
        return ResponseResult.successWithData(num);
    }


    @ApiOperation(value = "部门--添加", hidden = true)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.ADD, operateDetail = "添加部门", moduleName = "用户-机构与组")
    public ResponseResult add(@RequestBody Department department, HttpServletRequest request){
        String userId = (String)request.getSession().getAttribute("userId");
        department.setCreateUser(userId);
        int num = departmentService.insert(department);
        return ResponseResult.successWithData(num);

    }
}
