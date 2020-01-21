package quiz.cosmos.util;

import java.time.Duration;
import java.time.LocalDateTime;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HotelAspect {
	private static final Logger logger = LoggerFactory.getLogger(HotelAspect.class);
	ThreadLocal<LocalDateTime> startTime = new ThreadLocal<LocalDateTime>();
	ThreadLocal<LocalDateTime> endTime = new ThreadLocal<LocalDateTime>();

	// 匹配所有ServiceImpl包下面的所有类的所有方法
	@Pointcut("execution(* quiz.cosmos.service.*.*(..)) && @annotation(quiz.cosmos.util.MyAnnotation)")
	public void addLog() {
	}
	// @Pointcut("execution(public * *(..))")
	// public void skipMethod(){}

	@Before("addLog()")
	public void doSomething() {
		startTime.set(LocalDateTime.now());
		logger.info("start running at " + startTime.get());
	}

	@After("addLog()")
	public void doAfter() {
		endTime.set(LocalDateTime.now());
		logger.info("finished running at " + endTime.get() + ", duration in ms: "
				+ Duration.between(startTime.get(), endTime.get()).toMillis());
	}
}
