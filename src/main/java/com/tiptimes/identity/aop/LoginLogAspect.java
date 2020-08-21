package com.tiptimes.identity.aop;

import com.tiptimes.identity.annotation.LoginLog;
import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.dao.LoginSystemLogMapper;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.entity.LoginSystemLog;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.utils.CurrentUserUtil;
import com.tiptimes.identity.utils.IPUtil;
import com.tiptimes.identity.utils.UUIDUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;

@Aspect
@Component
public class LoginLogAspect {

    @Resource
    private LoginSystemLogMapper loginSystemLogMapper;

    // 定义切点
    // 在注解的位置切入代码
    @Pointcut("@annotation(com.tiptimes.identity.annotation.LoginLog)")
    public void loginPointCut(){

    }


    @After("loginPointCut()")
    public void saveOperation(JoinPoint joinPoint){
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 获取切入点所在的方法
        Method method = signature.getMethod();

        // 获取操作--方法上的CustomLog的值
        LoginLog sysLog = method.getAnnotation(LoginLog.class);

        // 获取http请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = (String)request.getSession().getAttribute("userId");

        LoginSystemLog loginSystemLog = new LoginSystemLog();
        loginSystemLog.setId(UUIDUtil.getUUID());
        loginSystemLog.setUserId(userId);
        loginSystemLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (sysLog != null) {
            loginSystemLog.setOperateType(sysLog.operateType());
            loginSystemLog.setOperateLog(sysLog.operateLog());
        }
        // ip地址
        String ipAddr = IPUtil.getRealClientIpAddr(request);
        loginSystemLog.setIpAddress(ipAddr);
        loginSystemLogMapper.insert(loginSystemLog);
    }
}
