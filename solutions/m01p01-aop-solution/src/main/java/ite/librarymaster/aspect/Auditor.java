package ite.librarymaster.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * After advice demonstration for audit purposes.
 */
@Component
@Aspect
public class Auditor {
    final Logger LOG = LoggerFactory.getLogger(Auditor.class);

    @After("within(ite.librarymaster.service..*) && @annotation(auditable)")
    public void auditLogger(JoinPoint joinPoint, Auditable auditable){
        if(auditable.full()){
            LOG.info("AUDIT: Method {} executed with params {}",
                AspectUtils.createJoinPointTraceName(joinPoint),
                AspectUtils.getDetailedJoinPointInfo(joinPoint));
         } else {
              LOG.info("AUDIT: Method {} executed.", AspectUtils.createJoinPointTraceName(joinPoint));
         }
    }
}
