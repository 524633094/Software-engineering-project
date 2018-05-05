package com.nwnu.greencloud.common.anyexception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
* @Author zhangqi
* @Desccription: 全局controller异常处理
* @Date: 12:00 2018/5/5
*/
@Log4j2
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e, HttpServletRequest request) throws Exception {
        log.info("请求地址：" + request.getRequestURL());
        ModelAndView mav = new ModelAndView();
        log.error("异常信息：",e);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
