package com.morange.shiro.controller;

import com.morange.shiro.entity.User;
import com.morange.sys.web.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            if (!subject.isAuthenticated()) {
                subject.login(token);
            }
        } catch (UnknownAccountException uae) {
            return Result.fail(403, "username wasn't in the system");
        } catch (IncorrectCredentialsException ice) {
            return Result.fail(403, "password didn't match, try again?");
        } catch (LockedAccountException lae) {
            return Result.fail(403, "account for that username is locked - can't login");
        } catch (AuthenticationException lae) {
            return Result.fail(403, "Other Authentication Exceptionaccount");
        } finally {
            return Result.succeed("/mainUI");
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.succeed("/index.jsp");
    }

    @RequestMapping("mainUI")
    public String mainUI(HttpServletRequest request) {

        //主体
        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();

        request.setAttribute("user", user);
        return "mainUI";
    }
}
