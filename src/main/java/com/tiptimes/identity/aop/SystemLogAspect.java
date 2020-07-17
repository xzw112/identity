package com.tiptimes.identity.aop;

import com.tiptimes.identity.annotation.SystemLog;
import com.tiptimes.identity.dao.TpMainAdminUserMapper;
import com.tiptimes.identity.dao.TpMainSystemLogMapper;
import com.tiptimes.identity.entity.TpMainAdminUser;
import com.tiptimes.identity.entity.TpMainSystemLog;
import com.tiptimes.identity.utils.CurrentUserUtil;
import com.tiptimes.identity.utils.IPUtil;
import com.tiptimes.identity.utils.IpV4Utils;
import com.tiptimes.identity.utils.UUIDUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * ClassName SystemLogAspect
 * Description: 系统日志切面处理类
 * Author user
 * Date 2020/3/26
 * Version V1.0
 **/
@Aspect
@Component
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Resource
    private TpMainAdminUserMapper tpMainAdminUserMapper;

    @Resource
    private TpMainSystemLogMapper tpMainSystemLogMapper;

    // 定义切点 @Pointcut
    // 在注解的位置切入代码
    @Pointcut("@annotation( com.tiptimes.identity.annotation.SystemLog)")
    public void logPointCut() {

    }

    //切面 配置后置通知
    @After("logPointCut()")
    public void saveOperation(JoinPoint joinPoint) {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作--方法上的CustomLog的值
        SystemLog sysLog = method.getAnnotation(SystemLog.class);
        // 获取http请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = CurrentUserUtil.getCurrentUserId();
        //获取当前人员信息
        TpMainAdminUser tpMainAdminUser = tpMainAdminUserMapper.selectByPrimaryKey(userId);

        //日志记录
        TpMainSystemLog tpMainSystemLog = new TpMainSystemLog();
        tpMainSystemLog.setOperatorName(tpMainAdminUser.getLoginName());
        tpMainSystemLog.setOperateTime(new Timestamp(System.currentTimeMillis()));
        if (sysLog != null) {
            // 模块名称
            String moduleName = sysLog.moduleName();
            tpMainSystemLog.setModules(moduleName);
            // 保存操作类型
            String operateType = sysLog.operateType();
            tpMainSystemLog.setOperateType(operateType);
            // 操作详情
            String operateDetail = sysLog.operateDetail();
            tpMainSystemLog.setOperateDetail(operateDetail);
        }

        // 获取浏览器信息
        String ua = request.getHeader("User-Agent");
        // 转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        // 获取浏览器信息
        Browser browser = userAgent.getBrowser();
        // 获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
        // 系统名称
        String system = os.getName();
        // 浏览器名称
        String browserName = browser.getName();
        // 终端信息
        tpMainSystemLog.setOperateTerminal(browserName);
        // ip地址
        String ipAddr = IPUtil.getRealClientIpAddr(request);
        tpMainSystemLog.setIpAddress(IpV4Utils.toInt(ipAddr));
        tpMainSystemLog.setId(UUIDUtil.getUUID());
        tpMainSystemLogMapper.insert(tpMainSystemLog);
    }
}
