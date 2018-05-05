package com.nwnu.greencloud.common.aop;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

/**
* @Author zhangqi
* @Desccription: 日志切面
* @Date: 11:53 2018/5/5
*/
@Aspect
@Service
@Log4j2
public class LoggerAdvice {

    @Before("within(com.nwnu..*) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        log.info("执行 " + loggerManage.description() + " 开始");
        log.info(joinPoint.getSignature().toString());
        log.info(parseParames(joinPoint.getArgs()));
    }

    @AfterReturning("within(com.nwnu..*) && @annotation(loggerManage)")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        log.info("执行 " + loggerManage.description() + " 结束");
    }

    @AfterThrowing(pointcut = "within(com.nwnu..*) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
        log.error("执行 " + loggerManage.description() + " 异常", ex);
    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer("传入参数[{}] ");
        for (Object obj : parames) {
            param.append(String.valueOf(obj)).append("  ");
        }
        return param.toString();
    }
}
