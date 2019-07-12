package jeeno.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * this aspect would be called after WebTimeAspect, but be released before WebTimeAspect release.
 * @author: Jeeno
 * @version: 0.0.1
 * @since: 2019/7/12 15:25
 */
@Aspect
@Component
@Slf4j
@Order(10)
public class WebAspect {

    @Pointcut("execution(public * jeeno.example.demo.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // get the request content
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("WebAspect...Before.");
        // log the info of request
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("WebAspect...AfterReturning.");
        // print the output after operating the request
        log.info("RESPONSE : " + ret);
    }

    @AfterThrowing("webLog()")
    public void afterThrowing(){
        log.info("WebAspect...AfterThrowing");
    }
}
