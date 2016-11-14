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
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("execution(* cz.codecamp.aop.service.*.*(..))")
	public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		LOGGER.info("about to start processing join point: " + proceedingJoinPoint);

		try {
			Object returnValue = proceedingJoinPoint.proceed();
			LOGGER.info("finished processing join point with success: " + proceedingJoinPoint + ", returnValue: " + returnValue);
			return returnValue;
		} catch (Throwable t) {
			LOGGER.info("finished processing join point with throwable: " + proceedingJoinPoint + ", exception: " + t);
			throw t;
		}
	}

	@Around("@annotation(cz.codecamp.aop.aspect.annotation.Auditable)")
	public Object logAuditable(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Marker marker = MarkerFactory.getMarker("AUDIT");
		Object[] args = proceedingJoinPoint.getArgs();

		LOGGER.info(marker, "about to start processing auditable join point: " + proceedingJoinPoint + " with " + args.length + " arguments");
		for (int i = 0; i < args.length; ++i) {
			LOGGER.info(marker, "- args[" + i + "] = " + args[i]);
		}

		try {
			Object returnValue = proceedingJoinPoint.proceed();
			LOGGER.info(
					marker,
					"finished processing auditable join point with success: " + proceedingJoinPoint + ", returnValue: " + returnValue
			);
			return returnValue;
		} catch (Throwable t) {
			LOGGER.info(
					marker,
					"finished processing auditable join point with throwable: " + proceedingJoinPoint + ", exception: " + t
			);
			throw t;
		}
	}


}
