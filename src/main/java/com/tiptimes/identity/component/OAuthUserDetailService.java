package com.tiptimes.identity.component;

import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.vo.UserDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class OAuthUserDetailService implements UserDetailsService {

    @Autowired
    private TpMainAdminUserMapper userMapper;

    @Override
    public UserDetailsVo loadUserByUsername(String account) throws UsernameNotFoundException {

        UserDetailsVo user = userMapper.selectUserByName(account);
        User.withUsername(user.getLoginName()).password(user.getLoginPassword()).authorities("p1,p2,p3").build();
        return user;
    }
}
