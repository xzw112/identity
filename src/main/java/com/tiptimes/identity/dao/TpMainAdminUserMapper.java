package com.tiptimes.identity.dao;

import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.utils.MyMapper;
import com.tiptimes.identity.vo.ClientUserVo;
import com.tiptimes.identity.vo.TpMainAdminUserVO;
import com.tiptimes.identity.vo.UserDetailsVo;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TpMainAdminUserMapper extends MyMapper<TpMainAdminUser> {

    /**
     * 通过条件查询人员信息
     * @param adminUserParam
     * @return
     */
    List<TpMainAdminUserVO> selectList(AdminUserParam adminUserParam);

    UserDetailsVo selectUserByName (String userName);

    ClientUserVo selectUserById (String id);

}