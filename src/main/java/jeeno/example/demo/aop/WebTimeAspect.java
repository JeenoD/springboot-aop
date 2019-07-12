package jeeno.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: Jeeno
 * @version: 0.0.1
 * @since: 2019/7/12 15:32
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class WebTimeAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * jeeno.example.demo.controller..*.*(..))")
    public void timeLog() {}

    @Before("timeLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("WebTimeAspect...Before.");
        startTime.set(System.currentTimeMillis());
    }

    @After("timeLog()")
    public void after(){
        log.info("WebTimeAspect...After.");
    }

    @AfterReturning("timeLog()")
    public void afterReturning(){
        log.info("WebTimeAspect...AfterReturning.");
        log.info("this request takes " + (System.currentTimeMillis() - startTime.get()) + "ms");
    }

}