package pl.mchaniewski.ps11.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class DaoLogger {
	private static final String CATEGORY_LOGGER_NAME = "CategoryLogger";
	private static final Logger log = LoggerFactory.getLogger(CATEGORY_LOGGER_NAME);

	@Pointcut("execution(* pl.mchaniewski.ps11.controller.CategoryController.*(..))")
	public void categoryControllerPointcut() {
	}

	@Before("categoryControllerPointcut()")
	public void logController(JoinPoint jointPoint) {
		log.debug("[{}] Before2", jointPoint.getSignature().getName());
	}
}
