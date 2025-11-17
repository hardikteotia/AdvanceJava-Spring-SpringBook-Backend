package com.Hardy2.spring_boot_rest.aop;

import com.Hardy2.spring_boot_rest.model.JobPost;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    //this is the advice which we want to execute
    //first thing is return type, then class name
    //then method names and arguments
    //now her first * is return type and then second * is package name and then third * is all the methods
    //and then in bracket we type all the arguments
    //eg: return type, class-name.method-name(args)
    //execution(* *.*(..))
    @Before("execution(* com.Hardy2.spring_boot_rest.service.JobService.getJob(..))")
    //we can use || here after our execution(* com.Hardy2.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.Hardy2.spring_boot_rest.service.JobService.getAllJob(..))
    public void logMethodCall(JoinPoint joinPoint){
        LOGGER.info("Logging Method Call "+joinPoint.getSignature().getName());
    }

    @After("execution(* com.Hardy2.spring_boot_rest.service.JobService.getJob(..))")
    public void logMethodExecuted(JoinPoint joinPoint){
        LOGGER.info("Executed Method Call "+joinPoint.getSignature().getName());
    }
    /*
    that's the output @Before and @After
    * 2025-11-17T20:54:38.880+05:30  INFO 3584 --- [spring-boot-rest] [nio-8080-exec-2] c.H.spring_boot_rest.aop.LoggingAspect   : Logging Method Call getJob
2025-11-17T20:54:38.881+05:30  INFO 3584 --- [spring-boot-rest] [nio-8080-exec-2] c.H.spring_boot_rest.aop.LoggingAspect   : Executed Method Call getJob*/

    /*so basically treat it like [before(execution of(that datatype of method--in that package
    .in that package.at that method))]*/
    @AfterThrowing("execution(* com.Hardy2.spring_boot_rest.service.JobService.getJob(..))")
    public void logMethodCrash(JoinPoint joinPoint){
        LOGGER.info("Executed Method has some issues  "+joinPoint.getSignature().getName());
    }
}
