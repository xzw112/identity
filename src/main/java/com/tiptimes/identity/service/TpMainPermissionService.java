package com.tiptimes.identity.service;

import com.tiptimes.identity.entity.TpMainPermission;

import java.util.List;

/**
 * 权限 Service接口
 */
public interface TpMainPermissionService {

    /**
     * 通过角色id获取权限
     * @param roleId
     * @return
     */
    List<String> selectPermissionIdsByRoleId(String roleId);

    /**
     * 获取所有权限
     * @return
     */
    List<TpMainPermission> selectPermissionList();
}