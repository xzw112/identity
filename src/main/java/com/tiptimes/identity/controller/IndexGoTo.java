package com.tiptimes.identity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

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
	 * 后台首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		return "index";
	}
}
