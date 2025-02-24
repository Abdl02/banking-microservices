package com.bank.account.infra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.bank.*.service.*.*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("➡️ Entering method: {}", joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        log.info("✅ Exiting method: {} (Execution Time: {} ms)", joinPoint.getSignature(), executionTime);

        return result;
    }
}