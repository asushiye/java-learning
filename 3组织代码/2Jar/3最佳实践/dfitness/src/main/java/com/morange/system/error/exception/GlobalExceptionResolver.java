package com.morange.system.error.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : zhenyun.su
 * @since : 2018/9/13
 * @Comment: 不适合在前后端分离的系统架构中，在未来版本被弃用
 */

@Deprecated
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        if(ex instanceof GlobalException){
            GlobalException globalException = (GlobalException)ex;
            modelAndView.setViewName("error");
            modelAndView.addObject("status",false);
            modelAndView.addObject("code", globalException.getCode());
            modelAndView.addObject("msg", globalException.getMessage());
            modelAndView.addObject("data", globalException.getData());
        }else{
            modelAndView.addObject("status",false);
            modelAndView.addObject("code", -1);
            modelAndView.addObject("msg", ex.getMessage());
            modelAndView.addObject("sdata", null);
        }
        return modelAndView;
    }
}
