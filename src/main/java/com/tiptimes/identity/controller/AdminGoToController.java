package com.tiptimes.identity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 页面跳转
 */
@Controller
@RequestMapping("/admin")
public class AdminGoToController {

    /**
     * 菜单
     * @return
     */
    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping("/home")
    public String home() {
        return "home";
    }


    /**
     * 人员管理
     * @return
     */
    @RequestMapping("/adminUser_index")
    public String adminUser_index(){
        return "adminUser/adminUser_index";
    }

    /**
     * 角色管理
     * @return
     */
    @RequestMapping("/role_index")
    public String role_index(){
        return "role/role_index";
    }

    /**
     * 个人资料
     * @return
     */
    @RequestMapping("/personalInfo_index")
    public String personalInfo_index(){
        return "personalInfo/personalInfo_index";
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping("/modifyPassword_index")
    public String modifyPassword_index(){
        return "modifyPassword/modifyPassword_index";
    }

    /**
     * 操作日志
     * @return
     */
    @RequestMapping("/systemLog_index")
    public String systemLog_index(){
        return "systemLog/systemLog_index";
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();//获取当前session
        if(session != null){
            session.removeAttribute("userInfo");
        }
        return "login";
    }

}
