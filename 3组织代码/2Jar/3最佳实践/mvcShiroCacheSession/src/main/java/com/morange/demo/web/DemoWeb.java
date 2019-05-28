package com.morange.demo.web;

/**
 * @author : zhenyun.su
 * @since : 2018/8/15
 */

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.morange.demo.entity.Demo;
import com.morange.demo.service.DemoService;

@Controller("demoWeb")
@RequestMapping("demo")
public class DemoWeb {

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
    //http://localhost:8081/bbs/web/test.html?username=zhenyun.su
}
