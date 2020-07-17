package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.dao.TpMainPermissionMapper;
import com.tiptimes.identity.dao.TpMainRolePermissionMapper;
import com.tiptimes.identity.entity.TpMainPermission;
import com.tiptimes.identity.entity.TpMainRolePermission;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.service.TpMainPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限模块 Service层
 */
@Service
@Transactional
public class TpMainPermissionServiceImpl implements TpMainPermissionService {

    @Resource
    private TpMainPermissionMapper tpMainPermissionMapper;

    @Resource
    private TpMainRolePermissionMapper tpMainRolePermissionMapper;

    @Override
    public List<String> selectPermissionIdsByRoleId(String roleId) {
        Example example = new Example(TpMainRolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        criteria.andNotEqualTo("idAttribute", "parent");
        List<TpMainRolePermission> rolePermissions = tpMainRolePermissionMapper.selectByExample(example);
        List<String> permissionIds = new ArrayList<>(rolePermissions.size());
        for (int i = 0; i < rolePermissions.size(); i++) {
            permissionIds.add(rolePermissions.get(i).getPermissionId());
        }
        return permissionIds;
    }

    @Override
    public List<TpMainPermission> selectPermissionList() {
        Example example = new Example(TpMainPermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        return tpMainPermissionMapper.selectByExample(example);
    }
}
