package ite.librarymaster.aspect;

import ite.librarymaster.util.monitor.Monitor;
import ite.librarymaster.util.monitor.MonitorFactory;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * An aspect that monitors the performance of executed methods of Spring beans.
 * It utilizes simple monitoring library jamon which provides factory for
 * monitors creation.
 */

@Component
@Aspect
public class PerformanceMonitor {

	final Logger logger = LoggerFactory.getLogger(PerformanceMonitor.class);

	private MonitorFactory monitorFactory;

	public PerformanceMonitor(MonitorFactory monitorFactory) {
		this.monitorFactory = monitorFactory;
	}

	/**
	 * Times repository method invocations and outputs performance results to a logger.
	 * @param repositoryMethod The join point representing the intercepted repository method
	 * @return The object returned by the target method
	 * @throws Throwable if thrown by the target method
	 */

	@Around("within(ite.librarymaster.*.*Repository)")
	public Object monitor(ProceedingJoinPoint repositoryMethod) throws Throwable {
		String name = AspectUtils.createJoinPointTraceName(repositoryMethod);
		Monitor monitor = monitorFactory.start(name);
		try {
			return repositoryMethod.proceed();
		} finally {
			monitor.stop();
			logger.info("MONITOR: {}",monitor.toString());
		}
	}
}



