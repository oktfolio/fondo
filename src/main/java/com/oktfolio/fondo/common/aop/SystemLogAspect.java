package com.oktfolio.fondo.common.aop;

import com.oktfolio.fondo.common.annotation.SystemLog;
import com.oktfolio.fondo.model.Log;
import com.oktfolio.fondo.service.LogService;
import io.netty.util.internal.ObjectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.NamedThreadLocal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/01/06
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final ThreadLocal<LocalDateTime> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal " + "startTime");

    /**
     * Controller 层切点,注解方式
     */
    //@Pointcut("execution(* *..controller..*Controller*.*(..))")
    @Pointcut("@annotation(com.oktfolio.fondo.common.annotation.SystemLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 (在方法执行之前返回)用于拦截 Controller 层记录用户的操作的开始时间
     *
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {

        LocalDateTime startTime = LocalDateTime.now();
        startTimeThreadLocal.set(startTime);
    }

    /**
     * 后置通知(在方法执行之后返回) 用于拦截Controller层操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {

        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Map<String, Object> controllerInfo = getControllerInfo(joinPoint);

            controllerInfo.get("name");
            controllerInfo.get("type");

        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = startTimeThreadLocal.get();

        Duration duration = Duration.between(startTime, endTime);
        duration.

    }

    /**
     * 保存日志至数据库
     */
    private static class SaveSystemLogThread implements Runnable {

        private Log log;
        private LogService logService;

        public SaveSystemLogThread(Log log, LogService logService) {
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {

            logService.save(log);
        }
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Map<String, Object> getControllerInfo(JoinPoint joinPoint) throws Exception {

        Map<String, Object> map = new HashMap<>(16);
        // 获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取相关参数
        Object[] arguments = joinPoint.getArgs();
        // 生成类对象
        Class targetClass = Class.forName(targetName);
        // 获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String name;
        Integer type;

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if (clazzs.length != arguments.length) {
                //比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
                continue;
            }
            name = method.getAnnotation(SystemLog.class).name();
            type = method.getAnnotation(SystemLog.class).type().ordinal();
            map.put("name", name);
            map.put("type", type);
        }
        return map;
    }


}
