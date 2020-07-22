package com.tiptimes.identity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基础跳转模块
 * 2017年11月24日14:19:41
 * @author 20170251
 *
 */
@Controller
public class IndexGoTo {
	
	/**
	 * 后台首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
	public String login(HttpServletRequest request){
		return "login";
	}

	/**
	 * oauth2跳转地址--登录页
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientLogin")
	public String clientLogin(HttpServletRequest request, HttpServletResponse response){
		return "client_login";
	}

	/**
	 * oauth2跳转地址--首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex(HttpServletRequest request){
		return "index";
	}
}
