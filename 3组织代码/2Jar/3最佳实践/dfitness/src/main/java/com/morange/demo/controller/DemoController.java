package com.morange.demo.controller;

/**
 * @author : zhenyun.su
 * @since : 2018/8/15
 */

import com.morange.demo.entity.Demo;
//import com.morange.system.error.exception.GlobalExceptionUtils;
import system.error.exception.GlobalExceptionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
//import com.morange.system.error.exception.GlobalException ;
import system.error.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.morange.demo.service.DemoService;

@Controller("demoWeb")
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequiresPermissions("demo:listUI")
    @RequestMapping(value = "listUI", method = RequestMethod.GET)
    public ModelAndView listUI() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("demo/listUI");
            modelAndView.addObject("title", "demo list UI");
        } catch (Exception e) {
            modelAndView.setViewName("demo/listUI");
            modelAndView.addObject("title", "查找用户报错:" + e.getMessage());
        } finally {
            return modelAndView;
        }
    }

    @RequestMapping(path = "errortest", method = RequestMethod.GET)
    public void throwUserException() throws GlobalException {
        if (2 > 1) {
            throw new GlobalException("throw User Exception");
        }
    }

    @RequestMapping(path = "global", method = RequestMethod.GET)
    @ResponseBody
    public void throwGlobalException() throws GlobalException {
        Demo demo =  new Demo();
        demo.setPassword("123456");
        demo.setPhone("12345678901");
        demo.setUsername("asushiye");
        GlobalExceptionUtils.throwException(200, "internet is broken", demo);
    }

}
