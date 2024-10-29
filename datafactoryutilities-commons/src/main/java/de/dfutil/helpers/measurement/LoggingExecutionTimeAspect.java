package de.dfutil.helpers.measurement;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingExecutionTimeAspect {

    private final Logger log = LoggerFactory.getLogger(LoggingExecutionTimeAspect.class);


    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("<<<<<<<<<<<<<<< {} >>>>>>>>>>>>>>> executed in {}ms", joinPoint.getSignature(), endTime - startTime);
        return proceed;
    }
}