package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class MemoAspect {
	
	@Autowired
	private MemoService memoService;
	
	@Pointcut("execution(* com.kh.spring.memo.controller.MemoController.deleteMemo(..))")
	public void deleteMemo() {}

	@Pointcut("execution(* com.kh.spring.memo.controller.MemoController.updateMemo(..))")
	public void updateMemo() {}

	@Around("deleteMemo() || updateMemo()") // 이런식으로 ||, && 연산자 사용 가능
	public Object passwordChecker(ProceedingJoinPoint joinPoint) throws Throwable {

		// 1. 사용자입력값
		Object[] args = joinPoint.getArgs(); // 이렇게 하면, MemoController의 메소드에서 Parameter로 받아온 값들이 배열에 순서대로 담긴다.
		int no = (int) args[0];
		String password = (String) args[1];
		RedirectAttributes redirectAttr = (RedirectAttributes) args[2];
		for(Object o : args)
			log.debug("{}", o);

		// 2. 비밀번호확인(db): 여기서도 db접속이 된다.
		Memo memo = memoService.selectOneMemo(no);
		log.debug("memo = {}", memo);
		if(!password.equals(memo.getPassword())) {
			redirectAttr.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/memo/memo.do";
		}
		
		// 원래 joinPoint.proceed(); 이후에 실행 후 코드가 나오고 return retObj;를 해줬는데 그게 없으니 마치 before처럼 사용하는 꼴이다.
		// 근데 리턴값이 필요해서 이렇게 한다는데 어쨌든 왜 beforeAdvice를 쓰면 안 되는지는 모르겠다.
		return joinPoint.proceed();
	}
}
