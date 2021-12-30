package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class StopWatchAspect {

	@Around("execution(* com.kh.spring.memo.controller.MemoController.insertMemo(..))") // (LogAspect와 대비하여) 이렇게 바로 할 수도 있다.
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
