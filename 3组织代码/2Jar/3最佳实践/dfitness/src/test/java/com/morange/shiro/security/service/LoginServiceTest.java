package com.morange.shiro.security.service;

import com.alibaba.fastjson.JSON;
import com.morange.shiro.security.entity.Permission;
import com.morange.shiro.security.repository.PermissionRepository;
import common.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class LoginServiceTest extends SpringBaseTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private LoginService loginService;
    @Test
    public void testGetUserPermits() {
        List<Permission> permissionData = this.loginService.findPermitByUser(1L);
        System.out.println(JSON.toJSONString(permissionData));
        List<Permission> permissionTree = this.loginService.getPermitTree(permissionData);
        System.out.println(JSON.toJSONString(permissionTree));
        List<String> permitCode = this.loginService.getPermitCode(permissionData);
        System.out.println(JSON.toJSONString(permitCode));
    }

    @Test
    public void testFindAll() {
        List<Permission> permissions = loginService.findAll();
        System.out.println(JSON.toJSONString(permissions));
    }

    @Test
    public void testSave() {
        Permission permission =  new Permission();
        permission.setAlert("dd");
        permission.setElink("permission");
        permission.setElink("");
        permission.setEnable(false);
        permission.setHeading(true);
        permission.setIcon("ddd");
        permission.setName("ddd");
        permission.setOids("1");
        permission.setPid(1L);
        permission.setSort(1);
        permissionRepository.save(permission);
    }
}