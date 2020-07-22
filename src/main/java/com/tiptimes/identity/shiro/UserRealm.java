package com.tiptimes.identity.shiro;

import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.dao.TpMainPermissionMapper;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.security.crypto.bcrypt.BCrypt;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private TpMainAdminUserMapper tpMainAdminUserMapper;

    @Resource
    private TpMainPermissionMapper tpMainPermissionMapper;

    /**
     * @Description: 授权
     * @Param: [principalCollection]
     * @Return: org.apache.shiro.authz.AuthorizationInfos
     * @Author: user
     * @Date: 2019/8/28
    **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String userName = (String)principalCollection.getPrimaryPrincipal();
        Example example = new Example(TpMainAdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", DataStatus.ENABLED.getCode());
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        criteria.andEqualTo("loginName", userName);
        List<TpMainAdminUser> list = tpMainAdminUserMapper.selectByExample(example);
        if (list == null || list.size() <= 0) {
            throw new UnknownAccountException("账号不存在或已被禁用！");
        }
        String roleId = list.get(0).getRoleId();
        List<String> permList = tpMainPermissionMapper.selectPermissionByRoleId(roleId);

        // 用户权限集合
        Set<String> permsSet = new HashSet<>();
        for (String permissions : permList) {
            if (!StringUtils.isNotBlank(permissions)) {
                continue;
            }
            permsSet.add(permissions);
        }
        // 返回授权信息
        simpleAuthorizationInfo.setStringPermissions(permsSet);
        return simpleAuthorizationInfo;
    }

    /**
     * @Description: 登录验证
     * @Param: [principalCollection]
     * @Return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: user
     * @Date: 2019/8/20
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        Example example = new Example(TpMainAdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", DataStatus.ENABLED.getCode());
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        criteria.andEqualTo("loginName", username);
        List<TpMainAdminUser> list = tpMainAdminUserMapper.selectByExample(example);
        if (list == null || list.size() <= 0) {
            throw new UnknownAccountException("账号不存在或已被禁用！");
        }
        TpMainAdminUser tpMainAdminUser = list.get(0);
        if (tpMainAdminUser != null) {
            if (BCrypt.checkpw(password, tpMainAdminUser.getLoginPassword())) {
                return new SimpleAuthenticationInfo(username,password,getName());
            }else {
                throw new IncorrectCredentialsException("密码错误");
            }
        } else {
            throw new UnknownAccountException("账号错误");
        }
    }
}
