package com.glinlf.growth.rest;

import com.glinlf.growth.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * Created by IntelliJ IDEA
 * Author : g-Linlf
 * Date : 2018/6/01.
 * <p>
 * explanation : 全局具体异常处理
 */

@ControllerAdvice
public class RestGlobalException {

    private final Logger LOG = LoggerFactory.getLogger(RestGlobalException.class);

    /**
     * 全局异常捕获处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Result globalException(Throwable e) {
        Result errorInfo = new Result();
        errorInfo.setCode(500);
        errorInfo.setMessage("未知错误!");
        LOG.debug("[WebGlobalException] 未知错误！", e);
        e.printStackTrace();
        errorInfo.setData("Exception : " + e.getMessage());
        return errorInfo;
    }

    /**
     * 404 not found 异常拦截
     * 做了请求拦截 理论不会出现404
     * 不做日志记录
     *
     * @param e
     * @return
     * @throws ServiceException
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Result notFoundException(NoHandlerFoundException e) {
        Result errorInfo = new Result();
        LOG.error("[WebGlobalException] 404 NOT FOUND 异常: {} ", e);
        errorInfo.setCode(404);
        errorInfo.setMessage("PAGE NOT FOUND!");
        return errorInfo;
    }


}
