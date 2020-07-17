package com.tiptimes.identity.controller.admin;

import com.tiptimes.identity.utils.ValidateCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin/validateCode")
public class ValidateCodeCtrl extends BaseController{
	/** 
	 * 响应验证码页面 
	 * @return 
	 */  
	@RequestMapping("/getCode")
	public String validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    //设置响应的类型格式为图片格式
		response.setHeader("Content-Type", "image/png");
	    //禁止图像缓存。  
	    response.setHeader("Pragma", "no-cache");  
	    response.setHeader("Cache-Control", "no-cache");  
	    response.setDateHeader("Expires", 0);  
	    HttpSession session = request.getSession();
	    ValidateCodeUtil vCode = new ValidateCodeUtil(90,34,4,50);
	    session.setAttribute("code", vCode.getCode());  
	    vCode.write(response.getOutputStream());  
	    return null;  
	} 
}
