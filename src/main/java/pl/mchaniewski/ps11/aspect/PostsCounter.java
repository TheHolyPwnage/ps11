package pl.mchaniewski.ps11.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PostsCounter {
	private static final Logger log = LoggerFactory.getLogger("Counters");
	private int postCount = 0;

	@Pointcut("execution(* pl.mchaniewski.ps11.controller.PostController.postForm(..))")
	public void postAddedPointcut() {
	}

	@AfterReturning(pointcut = "postAddedPointcut()", returning = "retVal")
	public void printPostCount(String retVal) {
		if (StringUtils.contains(retVal, "redirect:/post/")) {
			++postCount;
			log.debug("Do tej pory utworzono łącznie {} postów.", postCount);
		}
	}
}
