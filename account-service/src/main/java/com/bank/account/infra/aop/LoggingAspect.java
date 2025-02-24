package com.bank.account.infra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging execution of service methods.
 * <p>
 * Uses Aspect-Oriented Programming (AOP) to automatically log method entry,
 * execution time, and exit for all service layer methods.
 * </p>
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    /**
     * Pointcut to match all service methods in the application.
     */
    @Pointcut("execution(* com.bank.*.service.*.*(..))")
    public void serviceMethods() {}

    /**
     * Logs method execution time for all service methods.
     *
     * @param joinPoint The method join point.
     * @return The result of the method execution.
     * @throws Throwable If an error occurs during method execution.
     */
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