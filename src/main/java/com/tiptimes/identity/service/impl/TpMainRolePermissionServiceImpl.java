package com.tiptimes.identity.service.impl;

import com.tiptimes.identity.bo.RoleParam;
import com.tiptimes.identity.dao.TpMainRolePermissionMapper;
import com.tiptimes.identity.entity.TpMainRolePermission;
import com.tiptimes.identity.service.TpMainRolePermissionService;
import com.tiptimes.identity.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限关联关系 Service层
 */
@Service
@Transactional
public class TpMainRolePermissionServiceImpl implements TpMainRolePermissionService {

    @Resource
    private TpMainRolePermissionMapper tpMainRolePermissionMapper;

    private int deleteByRoleId(String roleId) {
        Example example = new Example(TpMainRolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        return tpMainRolePermissionMapper.deleteByExample(example);
    }

    private int insertRolePermission(RoleParam roleParam) {
        List<String> arr = roleParam.getPermissionIds();
        for (String permissionId : arr) {
            // 构造新对象
            TpMainRolePermission tpMainRolePermission = new TpMainRolePermission();
            tpMainRolePermission.setId(UUIDUtil.getUUID());
            tpMainRolePermission.setRoleId(roleParam.getRoleId());
            if (!permissionId.contains("$")) {
                tpMainRolePermission.setPermissionId(permissionId);
                tpMainRolePermission.setIdAttribute("");
            } else {
                tpMainRolePermission.setPermissionId(permissionId.substring(0, permissionId.indexOf("$")));
                tpMainRolePermission.setIdAttribute("parent");
            }
            // 保存
            tpMainRolePermissionMapper.insert(tpMainRolePermission);
        }
        return 1;
    }

    @Override
    public int saveRolePermission(RoleParam roleParam) {
        // 先删除之前的对应关系
        deleteByRoleId(roleParam.getRoleId());
        return insertRolePermission(roleParam);
    }
}
