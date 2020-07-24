package com.tiptimes.identity.service;

import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.vo.ClientUserVo;
import com.tiptimes.identity.vo.TpMainAdminUserVO;
import com.tiptimes.identity.vo.UserDetailsVo;

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

}