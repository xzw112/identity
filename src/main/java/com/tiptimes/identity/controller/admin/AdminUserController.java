package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.*;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.service.TpMainAdminUserService;
import com.tiptimes.identity.utils.*;
import com.tiptimes.identity.vo.TpMainAdminUserVO;
import com.tiptimes.identity.vo.UserDetailsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 后台用户
 */
@RestController
@RequestMapping("/admin/adminUser")
public class AdminUserController {

    @Resource
    private TpMainAdminUserMapper tpMainAdminUserMapper;

    @Autowired
    private TpMainAdminUserService tpMainAdminUserService;

    /**
     * 获取人员列表
     * @param adminUserParam
     * @return
     */
    @PostMapping("/getList")
    public PageResult<TpMainAdminUserVO> getList(@RequestBody AdminUserParam adminUserParam) {
        return tpMainAdminUserService.selectPageList(adminUserParam);
    }

    /**
     * 添加
     * @param request
     * @param tpMainAdminUser
     * @return
     */
    @PostMapping(path = "/add")
    @SystemLog(operateType = OperateTypeConstant.ADD, operateDetail = "新建人员", moduleName = "组织架构-人员管理")
    public ResponseResult add(HttpServletRequest request, @RequestBody TpMainAdminUser tpMainAdminUser) {
        //判断是否存在同名人员
        if(!checkAdminUserData(tpMainAdminUser)){
            return ResponseResult.error("人员用户名已存在，请确认");
        }
        tpMainAdminUser.setId(UUIDUtil.getUUID());
        tpMainAdminUser.setLoginPassword(MD5Util.encryptByMD5(BASE64Util.getFromBase64(tpMainAdminUser.getLoginPassword())));
        tpMainAdminUser.setCreateUser(CurrentUserUtil.getCurrentUserId());
        tpMainAdminUser.setCreateTime(new Date());
        tpMainAdminUser.setStatus(DataStatus.ENABLED.getCode());
        tpMainAdminUser.setIsDelete(DataStatus.NOT_DELETE.getCode());
        int result = tpMainAdminUserService.add(tpMainAdminUser);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.SAVE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.SAVE_ERROR);
        }
    }

    /**
     * 修改
     * @param request
     * @param tpMainAdminUser
     * @return
     */
    @PostMapping(path = "/update")
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "修改人员", moduleName = "组织架构-人员管理")
    public ResponseResult update(HttpServletRequest request, @RequestBody TpMainAdminUser tpMainAdminUser) {
        //判断是否存在同名人员
        if(!checkAdminUserData(tpMainAdminUser)){
            return ResponseResult.error("人员用户名已存在，请确认");
        }
        if(StringUtils.isNotBlank(tpMainAdminUser.getLoginPassword())){
            tpMainAdminUser.setLoginPassword(MD5Util.encryptByMD5(BASE64Util.getFromBase64(tpMainAdminUser.getLoginPassword())));
        }
        tpMainAdminUser.setUpdateUser(CurrentUserUtil.getCurrentUserId());
        tpMainAdminUser.setUpdateTime(new Date());
        int result = tpMainAdminUserService.update(tpMainAdminUser);
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
        TpMainAdminUser tpMainAdminUser = tpMainAdminUserMapper.selectByPrimaryKey(id);
        // 因密码加密规则不可逆，所以回显到前台的为后台内置的虚拟密码
        tpMainAdminUser.setLoginPassword(Constants.VIRTUAL_PASSWORD);
        return ResponseResult.successWithData(tpMainAdminUser);
    }

    /**
     * 批量删除
     * @param request
     * @param ids
     * @return
     */
    @PostMapping(path = "/batchDel")
    @SystemLog(operateType = OperateTypeConstant.DELETE, operateDetail = "删除人员", moduleName = "组织架构-人员管理")
    public ResponseResult batchDel(HttpServletRequest request, @RequestBody String ids) {
        String [] arr = ids.split(",");
        int result = tpMainAdminUserService.batchDel(arr);
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
    @SystemLog(operateType = OperateTypeConstant.ENABLE, operateDetail = "启用人员", moduleName = "组织架构-人员管理")
    public ResponseResult enable(HttpServletRequest request, @RequestBody String id) {
        TpMainAdminUser tpMainAdminUser = new TpMainAdminUser();
        tpMainAdminUser.setId(id);
        tpMainAdminUser.setStatus(DataStatus.ENABLED.getCode());
        int result = tpMainAdminUserService.update(tpMainAdminUser);
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
    @SystemLog(operateType = OperateTypeConstant.DISABLE, operateDetail = "禁用人员", moduleName = "组织架构-人员管理")
    public ResponseResult disable(HttpServletRequest request, @RequestBody String id) {
        TpMainAdminUser tpMainAdminUser = new TpMainAdminUser();
        tpMainAdminUser.setId(id);
        tpMainAdminUser.setStatus(DataStatus.DISABLED.getCode());
        int result = tpMainAdminUserService.update(tpMainAdminUser);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.DISABLE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.DISABLE_ERROR);
        }
    }

    /**
     * 判断该人员名称是否已存在
     * @param tpMainAdminUser
     */
    private Boolean checkAdminUserData(TpMainAdminUser tpMainAdminUser) {
        Example example = new Example(TpMainAdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(tpMainAdminUser.getId())){
            criteria.andNotEqualTo("id", tpMainAdminUser.getId());
        }
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        criteria.andEqualTo("loginName", tpMainAdminUser.getLoginName());
        List<TpMainAdminUser> list = tpMainAdminUserMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 查询当前用户个人资料
     * @param request
     * @return
     */
    @PostMapping(path = "/showCurrentUserInfo")
    public ResponseResult showCurrentUserInfo(HttpServletRequest request) {
        String userId = CurrentUserUtil.getCurrentUserId();
        TpMainAdminUser tpMainAdminUser = tpMainAdminUserMapper.selectByPrimaryKey(userId);
        TpMainAdminUserVO tpMainAdminUserVO = new TpMainAdminUserVO();
        if(tpMainAdminUser != null){
            BeanUtils.copyProperties(tpMainAdminUser, tpMainAdminUserVO);
        }
        return ResponseResult.successWithData(tpMainAdminUserVO);
    }

    /**
     * 更新个人资料
     * @param request
     * @return
     */
    @PostMapping(path = "/updateAdminUserInfo")
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "修改个人资料", moduleName = "个人中心-个人资料")
    public ResponseResult updateAdminUserInfo(HttpServletRequest request, @RequestBody TpMainAdminUser tpMainAdminUser) {
        tpMainAdminUser.setUpdateUser(CurrentUserUtil.getCurrentUserId());
        tpMainAdminUser.setUpdateTime(new Date());
        int result = tpMainAdminUserService.update(tpMainAdminUser);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.UPDATE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.UPDATE_ERROR);
        }
    }

    @PostMapping(path = "/modifyPassword")
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "修改密码", moduleName = "个人中心-修改密码")
    public ResponseResult modifyPassword(HttpServletRequest request, @RequestBody AdminUserParam adminUserParam) {
        String userId = CurrentUserUtil.getCurrentUserId();
        TpMainAdminUser tpMainAdminUser = tpMainAdminUserMapper.selectByPrimaryKey(userId);
        if(tpMainAdminUser == null){
            return ResponseResult.error("人员信息不存在");
        }
        String pass = MD5Util.encryptByMD5(BASE64Util.getFromBase64(adminUserParam.getLoginPassword()));
        String sqlPass = tpMainAdminUser.getLoginPassword();
        if(!sqlPass.equals(pass)){
            return ResponseResult.error("原始密码不正确");
        }
        String newPass = MD5Util.encryptByMD5(BASE64Util.getFromBase64(adminUserParam.getNewPassword()));
        tpMainAdminUser.setLoginPassword(newPass);
        int result = tpMainAdminUserService.update(tpMainAdminUser);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.UPDATE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.UPDATE_ERROR);
        }
    }
}
