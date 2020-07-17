package com.tiptimes.identity.service;

import com.tiptimes.identity.bo.RoleParam;

/**
 * 角色权限关联关系 Service接口
 */
public interface TpMainRolePermissionService {

    /**
     * 保存角色权限关系
     * @param roleParam
     * @return
     */
    int saveRolePermission(RoleParam roleParam);
}