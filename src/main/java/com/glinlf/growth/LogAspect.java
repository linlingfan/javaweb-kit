package com.glinlf.growth;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by g-Linlf on 2018/5/12.
 **/
@Aspect
@Component
public class LogAspect {

    public static boolean IsTest = false;

    private final Logger LOG = LoggerFactory.getLogger(LogAspect.class);
    /*
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */

    /**
     * resource 日志切面
     */
    @Pointcut(value = "execution(* com.glinlf.growth.rest..*.*(..))")
    public void webLogPointCut() {
    }

    @AfterReturning(value = "webLogPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // TODO
    }

    /**
     * web request log
     *
     * @param joinPoint
     */
    @Before("webLogPointCut()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //TODO 在过滤器或拦截器 校验通过后设置
        final String userId = (String) request.getAttribute("userId"); // 当前登录人
        final String args = StringUtils.join(joinPoint.getArgs(), ",");
        // 记录下请求内容
        LOG.info("[WEBLogAspect] operator user.id {} Request_URL : {} ; RequestMethod : {} , args : {} ; RemoteAddr :{}",
                userId,
                String.valueOf(request.getRequestURL()),
                request.getMethod(),
                args,
                request.getRemoteAddr());
    }

    /**
     * 环绕 Resource
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("webLogPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        return webLog(pjp);
    }

    Object webLog(ProceedingJoinPoint pjp) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object retVal = null;
        Throwable ex = null;
        try {
            retVal = pjp.proceed();
        } catch (Throwable ex1) {
            LOG.error("[出错 webLog aspect] {}", ex1.getMessage(), ex1);
            ex = ex1;
            retVal = "exception:" + ex1.getMessage();
        }
        stopWatch.stop();
        final String args = StringUtils.join(pjp.getArgs(), ",");
        final String methodName = pjp.getSignature().getName();
        LOG.debug("[WEBLogAspect] 耗时: {} , {}.{}({}) ; \nreturn :{}", stopWatch.getTotalTimeMillis(), pjp.getTarget().getClass().getCanonicalName(), methodName, args, retVal);
        if (ex != null) {
            throw ex;
        }
        return retVal;
    }

}
