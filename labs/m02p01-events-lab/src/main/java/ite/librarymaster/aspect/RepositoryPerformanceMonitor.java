package ite.librarymaster.aspect;

import ite.librarymaster.util.monitor.Monitor;
import ite.librarymaster.util.monitor.MonitorFactory;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
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
public class RepositoryPerformanceMonitor {

	final Logger logger = LoggerFactory.getLogger(RepositoryPerformanceMonitor.class);

	private MonitorFactory monitorFactory;

	public RepositoryPerformanceMonitor(MonitorFactory monitorFactory) {
		this.monitorFactory = monitorFactory;
	}

	/**
	 * Times repository method invocations and outputs performance results to a logger.
	 * @param repositoryMethod The join point representing the intercepted repository method
	 * @return The object returned by the target method
	 * @throws Throwable if thrown by the target method
	 */
	//@Around("execution(public * ite.librarymaster.*.*Repository+.*(..))")
	@Around("ite.librarymaster.aspect.Pointcuts.allRepositoriesMethods()")
	public Object monitor(ProceedingJoinPoint repositoryMethod) throws Throwable {
		logger.info("AROUND Advice ***");
		String name = createJoinPointTraceName(repositoryMethod);
		Monitor monitor = monitorFactory.start(name);
		try {
			return repositoryMethod.proceed();
		} finally {
			monitor.stop();
			logger.info(monitor.toString());
		}
	}

	private String createJoinPointTraceName(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		StringBuilder sb = new StringBuilder();
		sb.append(signature.getDeclaringType().getSimpleName());
		sb.append('.').append(signature.getName());
		return sb.toString();
	}
}