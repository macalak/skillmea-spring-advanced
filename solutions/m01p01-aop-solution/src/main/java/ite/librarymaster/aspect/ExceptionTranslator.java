package ite.librarymaster.aspect;

import ite.librarymaster.service.LibraryServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AfterThrowing advice demonstration in Exception translation.
 */
@Component
@Aspect
public class ExceptionTranslator {
    final Logger LOG = LoggerFactory.getLogger(ExceptionTranslator.class);

    @AfterThrowing(value = "ite.librarymaster.aspect.Pointcuts.allServicesMethods()", throwing = "exception")
    private void translateToServiceException(JoinPoint joinPoint, Exception exception){
        LOG.info("Translating Exception {} | thrown from {} ...", exception.toString(), AspectUtils.createJoinPointTraceName(joinPoint));
        throw new LibraryServiceException("Service operation failed", exception);
    }
}
