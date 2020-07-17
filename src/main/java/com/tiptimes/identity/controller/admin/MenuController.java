package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.common.ResponseResult;
import com.tiptimes.identity.entity.TpMainAdminUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 菜单
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @PostMapping("/getUserInfo")
    public ResponseResult getUserInfo(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        TpMainAdminUser tpMainAdminUser = (TpMainAdminUser) subject.getSession().getAttribute("userInfo");
        return ResponseResult.successWithData(tpMainAdminUser);
    }
}
