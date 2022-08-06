package nc.apps.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public void isDaoLayer(){
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer(){

    }

    @Before("(isServiceLayer()||isDaoLayer()) && target(targetedObject)")
    public void addLoggingBefore(JoinPoint joinPoint,Object targetedObject){
        log.info("Invoked {} in {}",joinPoint.getSignature().toShortString(),targetedObject.getClass());
    }

    @After("(isServiceLayer() || isDaoLayer()) && target(targetedObject))")
    public void addLoggingAfter(JoinPoint joinPoint,Object targetedObject){
        log.info("End of invocation of {} in {}",joinPoint.getSignature().toShortString(),targetedObject.getClass());
    }
}
