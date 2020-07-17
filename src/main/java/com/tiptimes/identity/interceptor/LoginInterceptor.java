package com.tiptimes.identity.interceptor;

import com.tiptimes.identity.entity.TpMainAdminUser;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        HttpSession session = request.getSession();
        TpMainAdminUser tpMainAdminUser = (TpMainAdminUser)session.getAttribute("userInfo");
        if(tpMainAdminUser == null) {
            System.out.println(request.getRequestURL());
            System.out.println("被拦截了");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script language=javascript>" +
                    "alert('登录超时，请重新登录');" +
                    "window.location='/'</script>"
            );
            return false;
        }
        return true;
    }
}
