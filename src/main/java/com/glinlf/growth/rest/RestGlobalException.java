package com.glinlf.growth.rest;

import com.glinlf.growth.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServlet;


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
        errorInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setMessage("未知错误!");
        LOG.debug("[WebGlobalException] 未知错误！", e);
        e.printStackTrace();
        errorInfo.setData("Exception : " + e.getMessage());
        return errorInfo;
    }

    /**
     * 404 not found 异常拦截
     * 做了请求拦截 理论不会出现404
     *
     * @param e
     * @return
     * @throws
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Result notFoundException(NoHandlerFoundException e) {
        Result errorInfo = new Result();
        LOG.error("[WebGlobalException] 404 NOT FOUND 异常: {} ", e);
        errorInfo.setCode(HttpStatus.NOT_FOUND.value());
        errorInfo.setMessage("PAGE NOT FOUND!");
        return errorInfo;
    }


}
