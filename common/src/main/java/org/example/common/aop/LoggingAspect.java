package org.example.common.aop;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 使用 aop 切面记录请求日志信息
 * @author wangyuanrong
 * @Date 2021/6/24 9:40
 */
@ConditionalOnProperty(name = "sys.showRequestLog", havingValue = "true")
@Configuration
@Aspect
@Slf4j
public class LoggingAspect {

    @Autowired
    private Environment environment;

    private static final String START_TIME = "request-start";

    /**
     * 切入点
     */
    @Pointcut("execution(* org.example.*.biz.controller..*.*(..))")
    public void executeResource() {

    }

    /**
     * 前置操作
     *
     * @param point 切入点
     */
    @Before("executeResource()")
    public void beforeLog(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("【IP】：{}, 【URL】：{}", request.getRemoteAddr(), request.getRequestURL());
//        log.info("【Class】：{}，【Method】：{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        if (point.getArgs() != null) {
            final List<Object> args = Arrays.stream(point.getArgs())
                    .filter(s -> !(s instanceof HttpServletRequest))
                    .filter(s -> !(s instanceof HttpServletResponse))
                    .collect(Collectors.toList());
            log.info("【Payload】：{}，", JSON.toJSONString(args));
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!CollectionUtils.isEmpty(parameterMap)) {
            log.info("【Parameters】：{}，", JSON.toJSONString(parameterMap));
        }
        Long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
    }

    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("executeResource()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("【Response】：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 后置操作
     */
    @AfterReturning("executeResource()")
    public void afterReturning() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
//        String userAgent = request.getHeader("User-Agent");
        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("【Time】：{}ms", end - start);
//        log.info("【User-Agent】：{}", userAgent);
    }
}

