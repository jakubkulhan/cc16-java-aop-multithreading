package cz.codecamp.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeTrackingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeTrackingAspect.class);


	@Around("@annotation(cz.codecamp.aop.aspect.annotation.TaskExecutionTime)")
	public Object logTaskExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Marker marker = MarkerFactory.getMarker("AUDIT");
		Object[] args = proceedingJoinPoint.getArgs();

		long startTime = System.currentTimeMillis();
		try {
			Object returnValue = proceedingJoinPoint.proceed();
			long endTime = System.currentTimeMillis();
			LOGGER.info("finished processing TaskExecutionTime join point with success: "
					+ proceedingJoinPoint + ", returnValue: " + returnValue
					+ " in " + (endTime - startTime) + " miliseconds."
			);
			return returnValue;
		} catch (Throwable t) {
			long endTime = System.currentTimeMillis();
			LOGGER.info("finished processing TaskExecutionTime join point with throwable: "
					+ proceedingJoinPoint
					+ " in " + (endTime - startTime) + " miliseconds.");
			throw t;
		}
	}

}
