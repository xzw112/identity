package com.tiptimes.identity.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.tiptimes.identity.annotation.LoginLog;
import com.tiptimes.identity.bo.LoginParam;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.enums.DataStatus;
import com.tiptimes.identity.utils.BASE64Util;
import com.tiptimes.identity.vo.UserDetailsVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录
 */
@RestController
@RequestMapping("/admin/login")
public class LoginController {

    @Resource
    private TpMainAdminUserMapper tpMainAdminUserMapper;

    /**
     * 用户登录
     * @param request
     * @param loginParam
     * @return
     */
    @PostMapping("/login")
    @LoginLog(operateType = 1, operateLog = "登录系统")
    public JSONObject login(HttpServletRequest request, @RequestBody LoginParam loginParam) {
        JSONObject map = new JSONObject();
        //String sessionCode = (String) request.getSession().getAttribute("code");
        /*if (!StringUtils.equalsIgnoreCase(loginParam.getCode(), sessionCode)) {  //忽略验证码大小写
            map.put("code", 0);
            map.put("message", "验证码不正确");
            return map;
        }*/
        String username = loginParam.getLoginName();
        String password = loginParam.getLoginPassword();
        String decryptPassword = BASE64Util.getFromBase64(password);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,decryptPassword);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        } catch (IncorrectCredentialsException | UnknownAccountException e) {
            //捕获密码错误异常、未知用户名异常
            map.put("code", 0);
            map.put("message", e.getMessage());
            return map;
        }

        Example example = new Example(TpMainAdminUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", DataStatus.ENABLED.getCode());
        criteria.andEqualTo("isDelete", DataStatus.NOT_DELETE.getCode());
        criteria.andEqualTo("loginName", username);
        criteria.andEqualTo("userType", DataStatus.USER_TYPE_ADMIN.getCode());
        criteria.andEqualTo("isAdmin", DataStatus.IS_ADMIN.getCode());
        List<TpMainAdminUser> list = tpMainAdminUserMapper.selectByExample(example);
        if (list.size() > 0) {
            subject.getSession().setAttribute("userInfo", list.get(0));
            request.getSession().setAttribute("userId", list.get(0).getId());
            map.put("code", 1);
        } else {
            UserDetailsVo  user = tpMainAdminUserMapper.selectUserByName(username);
            if (user.getUserType() == 2) {
                map.put("code", 0);
                map.put("message", "该账号无权限登录");
            } else {
                map.put("code", 0);
                map.put("message", "用户名密码错误");
            }
        }
        return map;
    }

}
