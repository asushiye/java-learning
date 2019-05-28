package com.morange.shiro.security.controller;

import com.morange.shiro.security.entity.Role;
import com.morange.shiro.security.service.RoleService;
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
import java.util.*;

/**
 * @author : zhenyun.su
 * @since : 2018/9/29
 */

@Controller
@RequestMapping("role")
public class RoleController {
    private final String urlPath = "system/security/";

    @Autowired
    private RoleService roleService;


    @RequiresPermissions("role:listUI")
    @RequestMapping("listUI")
    public String listUI() {

        return urlPath+"role/listUI";
    }

    @RequiresPermissions("role:listUI")
    @RequestMapping("list")
    @ResponseBody
    public Result list(int offset, int limit, String search) {
        Page<Role> pageInfo= this.roleService.findByName(search, offset, limit);
        return ResultUtils.success(pageInfo);
    }

    @RequiresPermissions("user:setRole")
    @RequestMapping("getRoleListByUserId/{userId}")
    @ResponseBody
    public Result getRoleListWithSelected(@PathVariable("userId")int userId) {
        List<Role> roleList = this.roleService.getRoleListByUserId(userId);
        return ResultUtils.success(roleList);
    }


//===================================保存/修改/删除方法=======================================

    @RequestMapping("saveUI")
    public String saveUI(Integer id, HttpServletRequest request) {
        if (id != null) {
            Role role = this.roleService.getById(id);
            if (role != null) {
                request.setAttribute("role", role);
            }
        }
        return urlPath+"role/saveUI";
    }


    @RequiresPermissions(value={"role:add","role:update"})
    @RequestMapping("save")
    public String save(Role role) {
        System.out.println(role.toString());
        this.roleService.save(role);
        return "redirect:/role/listUI";
    }


    @RequiresPermissions("role:delete")
    @RequestMapping("delete/{ids}")
    @ResponseBody
    public Result delete(@PathVariable("ids") String ids) {
        String[] idsStr = ids.split(",");
        if (idsStr.length == 1) {
            this.roleService.deleteById(Integer.parseInt(idsStr[0]));
        } else {
            this.roleService.deleteByIds(idsStr);
        }
        return ResultUtils.success();
    }

//    @RequiresPermissions("role:setPermission")
//    @RequestMapping("setPermission")
//    @ResponseBody
//    public Result setPermission(int roleId,String permissionIds) {
//        this.roleService.saveRolePermission(roleId,permissionIds);
//        return ResultUtils.success();
//    }
}
