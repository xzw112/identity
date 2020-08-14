package com.tiptimes.identity.component;

import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.vo.UserDetailsVo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MobleUserDetailService implements UserDetailsService {
    @Resource
    private TpMainAdminUserMapper tpMainAdminUserMapper;


    @Override
    public UserDetailsVo loadUserByUsername(String phoneNumber) {

        UserDetailsVo userDetailsVo = tpMainAdminUserMapper.selectUserByPhone(phoneNumber);

        if (userDetailsVo == null) {
            throw new UsernameNotFoundException("帐号未找到=" + phoneNumber);
        }
        User.withUsername(userDetailsVo.getLoginName()).password(userDetailsVo.getLoginPassword()).authorities("p1").build();
        return userDetailsVo;
    }
}
