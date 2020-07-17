package com.tiptimes.identity.utils;

import com.tiptimes.identity.entity.TpMainAdminUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Description: 获取当前登录用户
 * @Author user
 * @Date 2019/8/28
 * @Version V1.0
 **/
public class CurrentUserUtil {

    /**
     * 获取当前登录用户id
     * @return
     */
    public static String getCurrentUserId() {
        Subject subject = SecurityUtils.getSubject();
        TpMainAdminUser tpMainAdminUser = (TpMainAdminUser)subject.getSession().getAttribute("userInfo");
        return tpMainAdminUser.getId();
    }

    /**
     * 获取当前登录用户名称
     * @return
     */
    public static String getCurrentUserName() {
        Subject subject = SecurityUtils.getSubject();
        TpMainAdminUser tpMainAdminUser = (TpMainAdminUser)subject.getSession().getAttribute("userInfo");
        return tpMainAdminUser.getUserName();
    }

}
