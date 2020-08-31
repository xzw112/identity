package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.*;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.OutUser;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.qo.OutUserRequest;
import com.tiptimes.identity.qo.UserRequest;
import com.tiptimes.identity.service.TpMainAdminUserService;
import com.tiptimes.identity.utils.*;
import com.tiptimes.identity.vo.OutUserVo;
import com.tiptimes.identity.vo.TpMainAdminUserVO;
import com.tiptimes.identity.vo.UserDetailsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * 获取外部用户列表
     * @param outUserRequest
     * @return
     */
    @PostMapping("/getOutUserList")
    public PageResult<OutUserVo> getOutUserList(@RequestBody OutUserRequest outUserRequest) {
        return tpMainAdminUserService.selectOutUserList(outUserRequest);
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
        tpMainAdminUser.setLoginPassword(BCrypt.hashpw(BASE64Util.getFromBase64(tpMainAdminUser.getLoginPassword()), BCrypt.gensalt()));
        tpMainAdminUser.setCreateUser(CurrentUserUtil.getCurrentUserId());
        tpMainAdminUser.setCreateTime(new Date());
        tpMainAdminUser.setStatus(DataStatus.ENABLED.getCode());
        tpMainAdminUser.setIsDelete(DataStatus.NOT_DELETE.getCode());
        tpMainAdminUser.setUserType(DataStatus.USER_TYPE_ADMIN.getCode());
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
     * @param userRequest
     * @return
     */
    @PostMapping(path = "/showDetail")
    public ResponseResult showDetail(HttpServletRequest request, @RequestBody UserRequest userRequest) {
        //TpMainAdminUser tpMainAdminUser = tpMainAdminUserMapper.selectByPrimaryKey(id);
        AdminUserParam adminUserParam = new AdminUserParam();
        adminUserParam.setId(userRequest.getUserId());
        adminUserParam.setUserType(userRequest.getUserType());
        adminUserParam.setIsLeave(userRequest.getIsLeave());
        List<TpMainAdminUserVO> list = tpMainAdminUserMapper.selectList(adminUserParam);
        TpMainAdminUserVO tpMainAdminUser = null;
        if (list.size() > 0) {
            tpMainAdminUser = list.get(0);
            // 因密码加密规则不可逆，所以回显到前台的为后台内置的虚拟密码
            tpMainAdminUser.setLoginPassword(Constants.VIRTUAL_PASSWORD);
        }
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
     * 启用
     * @param
     * @param id
     * @return
     */
    @PostMapping(path = "/updateUserUse")
    @SystemLog(operateType = OperateTypeConstant.ENABLE, operateDetail = "启用人员", moduleName = "组织架构-人员管理")
    public ResponseResult updateUserUse(@RequestParam String id) {
        int result = tpMainAdminUserService.updateUserUse(id);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.ENABLE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.ENABLE_ERROR);
        }
    }

    /**
     * 禁用
     * @param
     * @param id
     * @return
     */
    @PostMapping(path = "/updateUserUnUse")
    @SystemLog(operateType = OperateTypeConstant.ENABLE, operateDetail = "禁用人员", moduleName = "组织架构-人员管理")
    public ResponseResult updateUserUnUse(@RequestParam String id) {
        int result = tpMainAdminUserService.updateUserUnUse(id);
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
        String sqlPass = tpMainAdminUser.getLoginPassword();
        String password = BASE64Util.getFromBase64(adminUserParam.getLoginPassword());
        boolean flag = BCrypt.checkpw(password, sqlPass);
        if(!flag){
            return ResponseResult.error("原始密码不正确");
        }
        String newPass = BCrypt.hashpw(BASE64Util.getFromBase64(adminUserParam.getNewPassword()), BCrypt.gensalt());
        tpMainAdminUser.setLoginPassword(newPass);
        int result = tpMainAdminUserService.update(tpMainAdminUser);
        if(result > 0){
            return ResponseResult.success(ErrorConstants.UPDATE_OK);
        }else{
            return ResponseResult.error(ErrorConstants.UPDATE_ERROR);
        }
    }

    // 批量离职
    @RequestMapping(value = "/updateUserLeave", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "批量离职", moduleName = "用户-内部用户")
    public ResponseResult updateUserLeave(@RequestBody String[] id){
        int num = 0;
        if (id.length > 0) {
            num = tpMainAdminUserService.updateUserLeave(id);
        }
        return ResponseResult.successWithData(num);
    }

    // 批量还原
    @RequestMapping(value = "/updateUserUnLeave", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "批量还原", moduleName = "用户-内部用户")
    public ResponseResult updateUserUnLeave(@RequestBody String[] id){
        int num = 0;
        if (id.length > 0) {
            num = tpMainAdminUserService.updateUserUnLeave(id);
        }
        return ResponseResult.successWithData(num);
    }

    @RequestMapping(value = "/insertOutUser", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.ADD, operateDetail = "新增外部用户", moduleName = "用户-外部用户")
    public ResponseResult insertOutUser(@RequestBody OutUser outUser){
        TpMainAdminUser adminUser = new TpMainAdminUser();
        adminUser.setLoginName(outUser.getLoginName());
        boolean flag = checkAdminUserData(adminUser);
        // 获取http请求
        if (flag) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String ipAddr = IPUtil.getRealClientIpAddr(request);
            outUser.setIpAddr(ipAddr);
            int num = tpMainAdminUserService.insertOutUser(outUser);
            return ResponseResult.successWithData(num);
        } else {
            return ResponseResult.error(0, "保存失败！账号已存在");
        }
    }

    @RequestMapping(value = "/updateOutUser", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "修改外部用户", moduleName = "用户-外部用户")
    public ResponseResult updateOutUser(@RequestBody OutUser outUser){
        int num = tpMainAdminUserService.updateOutUser(outUser);
        if (num > 0) {
            return ResponseResult.successWithData(num);
        } else {
            return ResponseResult.successWithData(num);
        }
    }

    /**
     *
     * 批量重置密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    @SystemLog(operateType = OperateTypeConstant.MODIFY, operateDetail = "批量重置密码", moduleName = "用户-内部用户")
    public ResponseResult resetPwd(@RequestBody String[] id){
        int num = 0;
        if (id.length > 0) {
            num = tpMainAdminUserService.resetPwd(id);
        }
        return ResponseResult.successWithData(num);
    }

    /**
     * 查询内部用户数量
     * @return
     */
    @GetMapping("/selectInUserCount")
    public ResponseResult selectInUserCount(){
        int num = tpMainAdminUserService.selectInUserCount();
        return ResponseResult.successWithData(num);
    }

    /**
     * 查询外部用户数量
     * @return
     */
    @GetMapping("/selectOutUserCount")
    public ResponseResult selectOutUserCount(){
        int num = tpMainAdminUserService.selectOutUserCount();
        return ResponseResult.successWithData(num);
    }

}
