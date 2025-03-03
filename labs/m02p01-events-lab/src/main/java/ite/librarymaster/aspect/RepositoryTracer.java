package ite.librarymaster.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * An aspect that monitors the performance of all three repositories used in the application.
 */

@Component
@Aspect
public class RepositoryTracer {

	final Logger logger = LoggerFactory.getLogger(RepositoryTracer.class);

	/**
	 */
	@Around("execution(public * ite.librarymaster.*.*Repository+.*(..))")
	public Object audit(ProceedingJoinPoint repositoryMethod) throws Throwable {
		String name = createJoinPointTraceName(repositoryMethod);
		logger.info("Enterring "+name);
		try {
			return repositoryMethod.proceed();
		} finally {
			logger.info("Exitting "+name);		}
	}

	private String createJoinPointTraceName(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		StringBuilder sb = new StringBuilder();
		sb.append(signature.getDeclaringType().getSimpleName());
		sb.append('.').append(signature.getName());
		return sb.toString();
	}
}