package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StopWatchAspect {


	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		
		// JoinPoint 수행 전
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// JoinPoint 수행
		Object retObj = joinPoint.proceed();
		
		// JoinPoint 수행 전
		stopWatch.stop();
		log.debug("[shortSummary] = {}", stopWatch.shortSummary());
		log.debug("[prettyPrint] = {}", stopWatch.prettyPrint());
		log.debug("[getTotalTimeMillis] = {} duration {}ms", methodName, stopWatch.getTotalTimeMillis());

		return retObj;
		
	}
}
