package ite.librarymaster.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Detailed Tracer used for Aspect ordering demonstration.
 * Advices utilise named Pointcuts.
 */
@Component
@Aspect
@Order(Integer.MAX_VALUE-100)
public class DetailedTracer {
    final Logger LOG = LoggerFactory.getLogger(DetailedTracer.class);

    @Before("ite.librarymaster.aspect.Pointcuts.servicesAndRepositories()")
    public void methodEntryLogger(JoinPoint joinPoint){
        LOG.info("DETAILED TRACER: Entering method {}", AspectUtils.createJoinPointTraceName(joinPoint));
    }

    @AfterReturning("ite.librarymaster.aspect.Pointcuts.servicesAndRepositories()")
    private void methodExitLogger(JoinPoint joinPoint){
        LOG.info("DETAILED TRACER:  Exiting method {}", AspectUtils.createJoinPointTraceName(joinPoint));
    }

}
