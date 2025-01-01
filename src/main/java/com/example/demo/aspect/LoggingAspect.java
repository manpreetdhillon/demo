package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class LoggingAspect
{
    //@Around("execution(* com.example.demo.service.*.*(..))")
    public Object logBeforeMethodExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        System.out.println("Before Executing method...");
        Object result=proceedingJoinPoint.proceed();
        System.out.println("After method Execution"+result);
        return result;
    }

}
