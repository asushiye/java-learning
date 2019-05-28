package com.morange.shiro.security.controller;

import com.morange.shiro.security.controller.dto.UserDto;
import com.morange.shiro.security.entity.User;
import com.morange.shiro.security.service.UserService;
import com.morange.system.error.exception.GlobalException;
import com.morange.system.error.exception.GlobalExceptionUtils;
import com.morange.system.result.Result;
import com.morange.system.result.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("api")
public class LoginController {

    private final String urlPath = "system/login/";

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody UserDto user) throws GlobalException {
        if (ObjectUtils.isEmpty(user)||ObjectUtils.isEmpty(user.getUsername())||ObjectUtils.isEmpty(user.getPassword())){
            GlobalExceptionUtils.throwException(404,"please input username or password!");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), user.getRememberMe());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResultUtils.success(subject.getPrincipal());
        } catch (UnknownAccountException uae) {
            return ResultUtils.failure(403, "username wasn't in the system", uae.getMessage());
        } catch (IncorrectCredentialsException ice) {
            return ResultUtils.failure(403, "password didn't match, try again?", ice.getMessage());
        } catch (LockedAccountException lae) {
            return ResultUtils.failure(403, "account for that username is locked - can't login", lae.getMessage());
        } catch (AuthenticationException ae) {
            return ResultUtils.failure(403, "Other Authentication Exceptionaccount", ae.getMessage());
        }catch (Exception e){
            return ResultUtils.failure(403, "Other Exceptionaccount", e.getMessage());
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext.toString());

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultUtils.success();
    }

    @RequestMapping("user")
    @ResponseBody
    public Result mainUI(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return ResultUtils.success(user);
    }
}
