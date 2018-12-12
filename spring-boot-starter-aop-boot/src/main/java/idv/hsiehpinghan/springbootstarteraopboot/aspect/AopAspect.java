package idv.hsiehpinghan.springbootstarteraopboot.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AopAspect {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(public * idv.hsiehpinghan.springbootstarteraopboot.controller..*.*(..))")
	public void controllerPointcut() {
	}

	@Before("controllerPointcut()")
	public void controllerBefore(JoinPoint joinPoint) throws Throwable {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
		LOGGER.info("controllerBefore URL : " + httpServletRequest.getRequestURL().toString());
		LOGGER.info("controllerBefore METHOD : " + httpServletRequest.getMethod());
		LOGGER.info("controllerBefore IP : " + httpServletRequest.getRemoteAddr());
		LOGGER.info("controllerBefore CLASS : " + joinPoint.getSignature().getDeclaringTypeName());
		LOGGER.info("controllerBefore HTTP METHOD : " + joinPoint.getSignature().getName());
		LOGGER.info("controllerBefore ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "returning", pointcut = "controllerPointcut()")
	public void controllerAfterReturning(JoinPoint joinPoint, Object returning) throws Throwable {
		LOGGER.info("controllerAfterReturning RESPONSE : " + returning);
		LOGGER.info("controllerAfterReturning CLASS : " + joinPoint.getSignature().getDeclaringTypeName());
		LOGGER.info("controllerAfterReturning METHOD : " + joinPoint.getSignature().getName());
		LOGGER.info("controllerAfterReturning ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterThrowing(pointcut = "controllerPointcut()", throwing = "throwable")
	public void controllerAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
		LOGGER.info("controllerAfterThrowing CLASS : " + joinPoint.getSignature().getDeclaringTypeName());
		LOGGER.info("controllerAfterThrowing METHOD : " + joinPoint.getSignature().getName());
		LOGGER.info("controllerAfterThrowing ARGS : " + Arrays.toString(joinPoint.getArgs()));
		LOGGER.info("controllerAfterThrowing THROWABLE : " + throwable);
	}

	@Around("controllerPointcut()")
	public Object controllerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		LOGGER.info("controllerAround CLASS : " + proceedingJoinPoint.getSignature().getDeclaringTypeName());
		LOGGER.info("controllerAround METHOD : " + proceedingJoinPoint.getSignature().getName());
		LOGGER.info("controllerAround ARGS : " + Arrays.toString(proceedingJoinPoint.getArgs()));
		long startTime = System.currentTimeMillis();
		Object object = proceedingJoinPoint.proceed();
		long endTime = System.currentTimeMillis();
		LOGGER.info("controllerAround TIME USED : " + (endTime - startTime));
		return object;
	}

}
