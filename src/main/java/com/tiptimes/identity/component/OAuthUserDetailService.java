package com.tiptimes.identity.component;

import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.OauthClientDetails;
import com.tiptimes.identity.vo.UserDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OAuthUserDetailService implements UserDetailsService {

    @Autowired
    private TpMainAdminUserMapper userMapper;

    @Override
    public UserDetailsVo loadUserByUsername(String account) throws UsernameNotFoundException {
        UserDetailsVo user = userMapper.selectUserByName(account);
        // TODO 后期读取数据库，目前先写固定
        List<String> authorities =  new ArrayList<>();
        authorities.add("p1");
        authorities.add("p2");
        user.setAuthorities(authorities);
        List<OauthClientDetails> clientDetailsList = new ArrayList<>();
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setClientId("c1");
        oauthClientDetails.setWebServerRedirectUri("http://192.168.1.120/admin/menu");
        clientDetailsList.add(oauthClientDetails);
        user.setClientList(clientDetailsList);
        User.withUsername(user.getLoginName()).password(user.getLoginPassword()).authorities("p1").build();
        return user;
    }
}
