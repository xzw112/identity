package com.tiptimes.identity.service;

import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.OutUser;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.qo.*;
import com.tiptimes.identity.vo.*;

import java.util.List;

/**
 * 后台用户模块 Service接口
 */
public interface TpMainAdminUserService {

    /**
     * 通过条件查询数据
      * @param adminUserParam
     * @return
     */
    PageResult<TpMainAdminUserVO> selectPageList(AdminUserParam adminUserParam);

    /**
     * 外部用户人员信息查询
     * @param outUserRequest
     * @return
     */
    PageResult<OutUserVo> selectOutUserList(OutUserRequest outUserRequest);

    /**
     * 根据用户Id获取用户信息
     * @param id
     * @return
     */
    ClientUserVo selectUserById (String id);

    /**
     * 数据插入
     * @param tpMainAdminUser
     * @return
     */
    int add(TpMainAdminUser tpMainAdminUser);

    /**
     * 数据更新
     * @param tpMainAdminUser
     * @return
     */
    int update(TpMainAdminUser tpMainAdminUser);

    /**
     * 批量删除
     * @param idArr
     * @return
     */
    int batchDel(String[] idArr);


    // 批量离职
    int updateUserLeave(String[] id);

    // 批量还原
    int updateUserUnLeave(String[] id);

    // 更新外部用户
    int updateOutUser(OutUser outUser);
    // 新增外部用户
    int insertOutUser(OutUser outUser);

    // 新增外部用户-客户端注册新用户
    int insertClientOutUser(RegisterOutUserRequest registerOutUserRequest);

    int updateUserUse(String id);
    int updateUserUnUse(String id);

    // 更新用户头像
    int updateUserHead(UserHeadRequest userHeadRequest);

    // 获取省市县数据
    List<ProvinceVo> cities();

    // 更新外部用户-客户端完善用户资料
    int updateOutUserInfo(OutUserInfoRequest outUserInfoRequest);

    // 更新用户密码
    int updateUserPwd(UserPwdRequest userPwdRequest);


}