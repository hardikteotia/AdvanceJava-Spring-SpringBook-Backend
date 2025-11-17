package com.Hardy2.spring_boot_rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.Hardy2.spring_boot_rest.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint joinPoint, int postId) throws Throwable {

        if(postId < 0){
            LOGGER.info("postId is negative update it");
            postId = -postId;
            LOGGER.info("new value is " + postId);
        }

        Object result = joinPoint.proceed(new  Object[]{postId});

        return result;
    }

}
