package com.morange.shiro.service;

import com.morange.shiro.entity.Permission;
import com.morange.shiro.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zhenyun.su
 * @since : 2018/9/17
 */

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Map<String, List<Permission>> getMenusAndPermissionsByUser(Integer userId) {
        List<Permission> permissionList = this.loginRepository.getPermissionList(userId);

        List<Permission> menuList = new ArrayList<>();
        Map<Integer, Permission> map = new HashMap<>();

        // 仅获取一级目录
        for (Permission permission : permissionList) {
            if (!"button".equals(permission.getType()) && permission.getPid() == 0L) {
                map.put(permission.getId(), permission);
                menuList.add(permission);
            }
        }

        // 仅获取一级目录, 二级菜单
        for (Permission permission : permissionList) {
            if (!"button".equals(permission.getType()) && map.get(permission.getPid()) != null) {
                Permission parent = map.get(permission.getPid());
                parent.getChildren().add(permission);
            }
        }

        Map<String,List<Permission>> resultMap = new HashMap<>(2);
        resultMap.put("menuList", menuList);
        resultMap.put("permissionList", permissionList);

        return resultMap;
    }
}
