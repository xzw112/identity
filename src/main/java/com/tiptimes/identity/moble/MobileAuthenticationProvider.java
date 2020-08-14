package com.tiptimes.identity.moble;

import com.tiptimes.identity.component.MobleUserDetailService;
import com.tiptimes.identity.vo.UserDetailsVo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author lengleng
 * @date 2018/1/9
 * 手机号登录校验逻辑
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {
    private MobleUserDetailService mobleUserDetailService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        UserDetailsVo userVo = mobleUserDetailService.loadUserByUsername((String) mobileAuthenticationToken.getPrincipal());

        UserDetailsVo userDetails = buildUserDeatils(userVo);
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("手机号不存在:" + mobileAuthenticationToken.getPrincipal());
        }

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    private UserDetailsVo buildUserDeatils(UserDetailsVo userVo) {
        return userVo;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public MobleUserDetailService getUserService() {
        return mobleUserDetailService;
    }

    public void setUserService(MobleUserDetailService userService) {
        this.mobleUserDetailService = userService;
    }
}
