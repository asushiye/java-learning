package com.morange.system.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : zhenyun.su
 * @since : 2018-08-03
 */

@Controller
@RequestMapping("error")
public class GlobalExceptionController {
    private final String urlPath = "";

    @RequestMapping(path = "not/found", method = RequestMethod.GET)
    public String notFound() {
        return urlPath+"404";
    }

    @RequestMapping(path = "not/authorize", method = RequestMethod.GET)
    public String notAuthorize() {
        return urlPath+"unauthorized";
    }
}
