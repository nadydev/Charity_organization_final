package com.example.CharityOrganization.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log {
    @Before("execution(* com.example.CharityOrganization.controller.*.*(..))")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Method " + methodName + " is executed with arguments:");
        for (Object arg : args) {
            System.out.println(arg);
        }
    }
    private long startTime;

    @Before("execution(* com.example.CharityOrganization.controller.*.*(..))")
    public void beforeControllerMethodExecution(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
    }

    @After("execution(* com.example.CharityOrganization.controller.*.*(..))")
    public void logExecutionTime(JoinPoint joinPoint) {
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;

        String methodName = joinPoint.getSignature().getName();

        System.out.println("Method " + methodName + " execution time: " + executionTime + " milliseconds");
    }
}
