package com.tiptimes.identity.service;

import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.vo.TpMainAdminUserVO;

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