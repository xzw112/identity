package com.tiptimes.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.dao.DepartmentMapper;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.Department;
import com.tiptimes.identity.qo.DepartmentRequest;
import com.tiptimes.identity.service.DepartmentService;
import com.tiptimes.identity.utils.TreeEntity;
import com.tiptimes.identity.utils.TreeHelper;
import com.tiptimes.identity.vo.TopDepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private TpMainAdminUserMapper tpMainAdminUserMapper;

    @Override
    public PageResult<Department> selectDepartmentList(DepartmentRequest departmentRequest) {
        PageHelper.startPage(departmentRequest.getPageNumber(), departmentRequest.getPageSize());
        List<Department> list = departmentMapper.selectDepartmentList(departmentRequest);
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        PageResult<Department> result = new PageResult<>();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public Department selectDetail(Integer id) {
        return departmentMapper.selectDetail(id);
    }

    @Override
    public List<TopDepartmentVo> selectTopDepartmentByUserId(String userId) {
        List<TopDepartmentVo> resultList = new ArrayList<>();
        // 获取用户的部门
        String str = tpMainAdminUserMapper.selectDepartmentByUserId(userId);
        String[] departmentId = str.split(",");
        DepartmentRequest departmentRequest = new DepartmentRequest();
        List<Department> list = departmentMapper.selectDepartmentList(departmentRequest);
        for (int i = 0; i < departmentId.length; i++) {
            String id = departmentId[i];
            for (int j = 0; j < list.size(); j++) {
                Department department = list.get(j);
                if (department.getId().toString().equals(id)){
                    if (department.getParentId().equals(0)) {
                        TopDepartmentVo topDepartmentVo = new TopDepartmentVo();
                        topDepartmentVo.setId(department.getId().toString());
                        topDepartmentVo.setDepartmentName(department.getName());
                        if (!resultList.contains(topDepartmentVo)){
                            resultList.add(topDepartmentVo);
                        }
                    } else {
                        /**
                         * 因为if中的父节点id不是根节点（0），
                         * 所以需要通过父节点id一级一级向上查询知道父节点id等于0为止
                         */

                        Integer parnetId = department.getParentId();
                        id = parnetId.toString();
                        // 重新遍历查询
                        j = 0;
                    }
                }
            }
        }
        return resultList;
    }


    @Override
    public int del(Integer id) {
        return departmentMapper.del(id);
    }

    @Override
    public int insert(Department department) {
        department.setIsDel(0);
        department.setCreateTime(new Date());
        int num = departmentMapper.insert(department);
        return num;
    }

    @Override
    public int updateById(Department department) {
        return departmentMapper.updateById(department);
    }
}
