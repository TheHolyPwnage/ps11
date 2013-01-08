package pl.mchaniewski.ps11.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class DaoLogger {
	private static final String DAO_LOGGER_NAME = "DaoLogger";
	private static final Logger log = LoggerFactory.getLogger(DAO_LOGGER_NAME);

	@Before("execution(* pl.mchaniewski.ps11.dao.jpa.JpaCategoryDao.getAll())")
	public void logBeforePostSave(JoinPoint jointPoint) {
		log.debug("[{}] Before", jointPoint.getSignature().getName());
	}
}
