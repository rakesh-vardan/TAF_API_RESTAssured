package io.learning.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* io.learning.client.ApiClient.*(..))")
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Calling API method: {}", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info("API method returned: {}", result);
        return result;
    }
}