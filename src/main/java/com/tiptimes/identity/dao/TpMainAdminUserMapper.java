package com.tiptimes.identity.dao;

import com.tiptimes.identity.bo.AdminUserParam;
import com.tiptimes.identity.entity.OutUser;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.qo.OutUserRequest;
import com.tiptimes.identity.utils.MyMapper;
import com.tiptimes.identity.vo.ClientUserVo;
import com.tiptimes.identity.vo.OutUserVo;
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

    /**
     * 外部用户人员信息查询
     * @param outUserRequest
     * @return
     */
    List<OutUserVo> selectOutUserList(OutUserRequest outUserRequest);

    UserDetailsVo selectUserByName (String userName);

    UserDetailsVo selectUserByPhone(String phoneNumber);

    ClientUserVo selectUserById (String id);
    // 批量离职
    int updateUserLeave(String[] id);
    // 批量还原
    int updateUserUnLeave(String[] id);
    // 更新外部用户
    int updateOutUser(OutUser outUser);
    // 新增外部用户
    int insertOutUser(OutUser outUser);
    int updateUserUse(String id);
    int updateUserUnUse(String id);
}