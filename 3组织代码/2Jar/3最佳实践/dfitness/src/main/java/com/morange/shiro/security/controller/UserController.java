package com.morange.shiro.security.controller;

import com.morange.shiro.security.entity.User;
import com.morange.shiro.security.service.UserService;
import com.morange.system.result.Result;
import com.morange.system.result.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("user")
public class UserController {
    private final String urlPath = "system/security/";

    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private UserService userService;

    @RequiresPermissions("user:listUI")
    @RequestMapping("listUI")
    public String listUI(HttpServletRequest request) {
        return urlPath+"user/listUI";
    }


    @RequiresPermissions("user:listUI")
    @RequestMapping("list")
    @ResponseBody
    public Result getList(int offset, int limit, String search) {
        Page<User> pageInfo= this.userService.findByUsername(search, offset, limit);
        return ResultUtils.success(pageInfo);
    }

//===================================保存/修改/删除方法=======================================	

    @RequestMapping("saveUI")
    public String saveUI(Integer id, HttpServletRequest request) {
        if (id != null) {
            User user = this.userService.getById(id);
            if (user != null) {
                request.setAttribute("user", user);
            }
            System.out.println(user.toString());
        }
        return urlPath+"user/saveUI";
    }


    @RequiresPermissions(value = {"user:add", "user:update"})
    @RequestMapping("save")
    public String add(User user) {
        if (user.getId() != null) {
            user.setUpdateTime(new Date());
            userService.update(user);
        } else {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            try {
                user.setPassword("123456");
//                user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes("UTF-8")));
//            } catch (UnsupportedEncodingException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.userService.save(user);
        }
        return "redirect:/user/listUI";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("delete/{ids}")
    @ResponseBody
    public Result delete(@PathVariable("ids") String ids) {
        String[] idsStr = ids.split(",");
        this.userService.deleteByIds(idsStr);
        return ResultUtils.success();
    }

    @RequiresPermissions("user:setRole")
    @RequestMapping("setRole")
    @ResponseBody
    public Result setRole(int userId, String roleIds) {
        this.userService.saveUserRole(userId, roleIds);
        return ResultUtils.success();
    }

}
