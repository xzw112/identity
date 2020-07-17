package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.TpMainPermission;
import com.tiptimes.identity.service.TpMainPermissionService;
import com.tiptimes.identity.utils.TreeEntity;
import com.tiptimes.identity.utils.TreeHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Autowired
    private TpMainPermissionService tpMainPermissionService;

    /**
     * 通过角色获取权限
     * @param roleId
     * @return
     */
    @PostMapping("/listPermissionIds")
    public ResponseResult listPermissionIds(@RequestBody String roleId) {
        List<String> permissionIdList = tpMainPermissionService.selectPermissionIdsByRoleId(roleId);
        String permissioIds = StringUtils.join(permissionIdList.toArray(),",");
        return ResponseResult.successWithData(permissioIds);
    }

    /**
     * 加载权限树
     * param request
     * return
     */
    @PostMapping("/getPermissionTree")
    public ResponseResult getPermissionTree() {
        List<TpMainPermission> permissionList = tpMainPermissionService.selectPermissionList();
        List<TreeEntity> treeVOList = new ArrayList<>();
        for (TpMainPermission permission : permissionList) {
            TreeEntity tvo = new TreeEntity();
            tvo.setId(permission.getId().toString());
            tvo.setParentId(permission.getParentId());
            tvo.setText(permission.getPermissionName());
            treeVOList.add(tvo);
        }
        treeVOList = TreeHelper.generateTree(treeVOList);
        return ResponseResult.successWithData(treeVOList);
    }
}
