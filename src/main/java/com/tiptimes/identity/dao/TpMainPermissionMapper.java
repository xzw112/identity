package com.tiptimes.identity.dao;

import com.tiptimes.identity.entity.TpMainPermission;
import com.tiptimes.identity.utils.MyMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TpMainPermissionMapper extends MyMapper<TpMainPermission> {

    /**
     * 通过角色获取权限
     * @param roleId
     * @return
     */
    List<String> selectPermissionByRoleId(String roleId);
}