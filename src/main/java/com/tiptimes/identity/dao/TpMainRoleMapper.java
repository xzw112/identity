package com.tiptimes.identity.dao;

import com.tiptimes.identity.bo.RoleParam;
import com.tiptimes.identity.entity.TpMainRole;
import com.tiptimes.identity.utils.MyMapper;
import com.tiptimes.identity.vo.TpMainRoleVO;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TpMainRoleMapper extends MyMapper<TpMainRole> {

    /**
     * 通过条件查询角色信息
     * @param roleParam
     * @return
     */
    List<TpMainRoleVO> selectList(RoleParam roleParam);
}