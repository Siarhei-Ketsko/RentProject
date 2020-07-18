package com.htp.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
@Aspect
public class LogAspect {

    private static Map<String, Integer> statistics = new ConcurrentHashMap<>();

    public static Map<String, Integer> getStatistics() {
        return statistics;
    }

    public static String showStatistics(){
      return   statistics.entrySet()
                .stream()
                .map(entry -> "Method " + entry.getKey() + " count: " + entry.getValue())
                .collect(Collectors.joining(", "));
    }


    @Pointcut("execution (* com.htp.dao..*(..))")
    public void aroundRepository() {

    }

    @Around("aroundRepository()")
    public Object methodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch startTime = new  StopWatch();
        startTime.start();
        Object proceed = joinPoint.proceed();
        statistics.putIfAbsent(joinPoint.getSignature().getName(), 0);
        statistics.computeIfPresent(joinPoint.getSignature().getName(), (method, cont) -> cont + 1);
        startTime.stop();
        log.info(joinPoint.getSignature().getName() + " execution time: " + startTime.getTotalTimeMillis() + " ms");
        return proceed;
    }
}
