package io.learning.aspect;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* io.learning.client.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Calling API method: {}", joinPoint.getSignature().getName());
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Method {} executed in {}ms", joinPoint.getSignature().getName(), endTime - startTime);

        if (result instanceof Response response) {
            log.info("API method returned: {}", response.getBody().prettyPrint());
        }
        return result;
    }
}