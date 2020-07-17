package com.tiptimes.identity.service;

import com.tiptimes.identity.bo.RoleParam;
import com.tiptimes.identity.common.PageResult;
import com.tiptimes.identity.entity.TpMainRole;
import com.tiptimes.identity.vo.TpMainRoleVO;

/**
 * 角色管理 Service接口
 */
public interface TpMainRoleService {

    /**
     * 通过条件查询数据
      * @param roleParam
     * @return
     */
    PageResult<TpMainRoleVO> selectPageList(RoleParam roleParam);

    /**
     * 数据插入
     * @param tpMainRole
     * @return
     */
    int add(TpMainRole tpMainRole);

    /**
     * 数据更新
     * @param tpMainRole
     * @return
     */
    int update(TpMainRole tpMainRole);

    /**
     * 批量删除
     * @param idArr
     * @return
     */
    int batchDel(String[] idArr);

}