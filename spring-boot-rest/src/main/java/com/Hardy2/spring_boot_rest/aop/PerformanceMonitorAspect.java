package com.Hardy2.spring_boot_rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

//    @Around("execution(* com.Hardy2.spring_data_rest.service.JobService.getJob(..))")
    //in above line there is a wrong path which is kind of a logical error
    //so take it as a learning....
    @Around("execution(* com.Hardy2.spring_boot_rest.service.JobService.getJob(..))")
    public Object monitorTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("inside the monitor time");
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        LOGGER.info("Time Taken = "+ (end - start));
        return result;
    }
}
