package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.bo.RoleParam;
import com.tiptimes.identity.common.ErrorConstants;
import com.tiptimes.identity.common.OperateTypeConstant;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.dao.TpMainRoleMapper;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.entity.TpMainRole;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.exception.BusinessException;
import com.tiptimes.identity.service.TpMainRolePermissionService;
import com.tiptimes.identity.service.TpMainRoleService;
import com.tiptimes.identity.utils.CurrentUserUtil;
import com.tiptimes.identity.utils.UUIDUtil;
import com.tiptimes.identity.vo.TpMainRoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private TpMainRoleService tpMainRoleService;

    @Resource
    private TpMainRoleMapper tpMainRoleMapper;

    @Resource
    private TpMainAdminUserMapper tpMainAdminUserMapper;

    @Autowired
    private TpMainRolePermissionService tpMainRolePermissionService;

    /**
     * 获取角色列表
     * @param roleParam
     * @return
     */
    @PostMapping("/getList")
    public PageResult<TpMainRoleVO> getList(@RequestBody RoleParam roleParam) {
        return tpMainRoleService.selectPageList(roleParam);
    }

    /**
     * 添加
     * @param request
     * @param tpMainRole
     * @return
     */
    @PostMapping(path = "/add")
    @SystemLog(operateType = OperateTypeConstant.ADD, operateDetail = "新建角色", moduleName = "组织架构-角色管理")
    public ResponseResult add(HttpServletRequest request, @RequestBody TpMainRole tpMainRole) {
        //判断是否存在同名角色
        checkRoleData(tpMainRole);
        tpMainRole.setId(UUIDUtil.getUUID());
        tpMainRole.setCreateUser(CurrentUserUtil.getCurrentUserId());
        tpMainRole.setCreateTime(new Date());
        tpMainRole.setStatus(DataStatus.ENABLED.getCode());
        tpMainRole.setIsDelete(DataStatus.NOT_DELETE.getCode());
        int result = tpMainRoleService.add(tpMainRole);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.SAVE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.SAVE_ERROR);
        }
    }

    /**
     * 修改
     * @param request
     * @param tpMainRole
     * @return
     */
    @PostMapping(path = "/update")
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "修改角色", moduleName = "组织架构-角色管理")
    public ResponseResult update(HttpServletRequest request, @RequestBody TpMainRole tpMainRole) {
        //判断是否存在同名角色
        checkRoleData(tpMainRole);
        tpMainRole.setUpdateUser(CurrentUserUtil.getCurrentUserId());
        tpMainRole.setUpdateTime(new Date());
        int result = tpMainRoleService.update(tpMainRole);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.UPDATE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.UPDATE_ERROR);
        }
    }

    /**
     * 查询详细信息
     * @param request
     * @param id
     * @return
     */
    @PostMapping(path = "/showDetail")
    public ResponseResult showDetail(HttpServletRequest request, @RequestBody String id) {
        TpMainRole tpMainRole = tpMainRoleMapper.selectByPrimaryKey(id);
        return ResponseResult.successWithData(tpMainRole);
    }

    /**
     * 批量删除
     * @param request
     * @param ids
     * @return
     */
    @PostMapping(path = "/batchDel")
    @SystemLog(operateType = OperateTypeConstant.DELETE, operateDetail = "删除角色", moduleName = "组织架构-角色管理")
    public ResponseResult batchDel(HttpServletRequest request, @RequestBody String ids) {
        String [] arr = ids.split(",");
        for(String str : arr){
            checkIsUse(str);
        }
        int result = tpMainRoleService.batchDel(arr);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.DELETE_OK);
        }else{
            return ResponseResult.success(ErrorConstants.DELETE_ERROR);
        }
    }

    /**
     * 启用
     * @param request
     * @param id
     * @return
     */
    @PostMapping(path = "/enable")
    @SystemLog(operateType = OperateTypeConstant.ENABLE, operateDetail = "启用角色", moduleName = "组织架构-角色管理")
    public ResponseResult enable(HttpServletRequest request, @RequestBody String id) {
        TpMainRole tpMainRole = new TpMainRole();
        tpMainRole.setId(id);
        tpMainRole.setStatus(DataStatus.ENABLED.getCode());
        int result = tpMainRoleService.update(tpMainRole);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.ENABLE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.ENABLE_ERROR);
        }
    }

    /**
     * 禁用
     * @param request
     * @param id
     * @return
     */
    @PostMapping(path = "/disable")
    @SystemLog(operateType = OperateTypeConstant.DISABLE, operateDetail = "禁用角色", moduleName = "组织架构-角色管理")
    public ResponseResult disable(HttpServletRequest request, @RequestBody String id) {
        //判断该角色下是否有人员
        checkIsUse(id);
        TpMainRole tpMainRole = new TpMainRole();
        tpMainRole.setId(id);
        tpMainRole.setStatus(DataStatus.DISABLED.getCode());
        int result = tpMainRoleService.update(tpMainRole);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.DISABLE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.DISABLE_ERROR);
        }
    }

    /**
     * 判断该角色名称是否已存在
     * @param tpMainRole
     */
    private void checkRoleData(TpMainRole tpMainRole) {
        Example example = new Example(TpMainRole.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(tpMainRole.getId())){
            criteria.andNotEqualTo("id", tpMainRole.getId());
        }
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        criteria.andEqualTo("roleName", tpMainRole.getRoleName());
        List<TpMainRole> list = tpMainRoleMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            throw new BusinessException("操作失败，已存在相同名称的角色");
        }
    }

    /**
     * 判断角色是否处于占用状态
     * @param id
     */
    private void checkIsUse(String id) {
        Example example = new Example(TpMainAdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", id);
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        List<TpMainAdminUser> list = tpMainAdminUserMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            throw new BusinessException("操作失败，角色下已存在人员");
        }
    }

    /**
     * 保存角色权限对应关系
     * @param roleParam
     * @return
     */
    @PostMapping("/saveRolePermission")
    @SystemLog(operateType = OperateTypeConstant.CONFIG, operateDetail = "权限设置", moduleName = "组织架构-角色管理")
    public ResponseResult saveRolePermission(@RequestBody RoleParam roleParam) {
        int result = tpMainRolePermissionService.saveRolePermission(roleParam);
        if (result == 1) {
            return ResponseResult.success(ErrorConstants.SAVE_OK);
        } else {
            return ResponseResult.error(ErrorConstants.SAVE_ERROR);
        }
    }
}
