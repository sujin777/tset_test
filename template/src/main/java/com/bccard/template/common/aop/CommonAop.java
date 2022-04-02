package com.bccard.template.common.aop;

import com.bccard.template.common.dto.CommonDTO;
import com.bccard.template.common.utils.LogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CommonAop {

    // 모든 컨트롤러
    @Around("execution(* *..controller.*.*(..))")
    public Object calculateExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        LogUtils.info("before process");

        LogUtils.info("txId test1");

        LogUtils.infoByCommonDTO("txId test2");

        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        CommonDTO commonDTO = (CommonDTO)req.getSession().getAttribute("commonDTO");
        LogUtils.info(commonDTO, "txId test3");

        // 해당 클래스의 메소드 실행
        Object result = pjp.proceed();

        LogUtils.info("after process");

        return result;
    }
}
