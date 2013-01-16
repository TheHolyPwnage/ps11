package pl.mchaniewski.ps11.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MainPageViewCounter {
	private static final Logger log = LoggerFactory.getLogger("Counters");
	private int viewCount = 0;

	@Pointcut("execution(* pl.mchaniewski.ps11.controller.CategoryController.categoryList(..))")
	public void mainPagePointcut() {
	}

	@After("mainPagePointcut()")
	public void printViewCount() {
		++viewCount;
		log.debug("Główna strona została odwiedzona {} razy.", viewCount);
	}

}
