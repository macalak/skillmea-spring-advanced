package ite.librarymaster.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Before and AfterReturning advices demonstration on simple Tracing aspect.
 */
@Component
@Aspect
@Order(Integer.MAX_VALUE-200)
public class Tracer {
    final Logger LOG = LoggerFactory.getLogger(Tracer.class);

    @Before("ite.librarymaster.aspect.Pointcuts.servicesAndRepositories()")
    public void methodEntryLogger(JoinPoint joinPoint){
        LOG.info("Entering method {}", AspectUtils.createJoinPointTraceName(joinPoint));
    }

    @AfterReturning("ite.librarymaster.aspect.Pointcuts.servicesAndRepositories()")
    private void methodExitLogger(JoinPoint joinPoint){
        LOG.info("Exiting method {}", AspectUtils.createJoinPointTraceName(joinPoint));
    }

}
