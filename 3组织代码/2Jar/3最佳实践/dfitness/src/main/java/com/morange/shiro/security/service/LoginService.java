package com.morange.shiro.security.service;

import com.morange.shiro.security.entity.Operation;
import com.morange.shiro.security.entity.Permission;
import com.morange.system.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2018/9/17
 */

@Service
public class LoginService {

    @Autowired
    private OperationService operationService;

    @Autowired
    private PermissionService permissionService;

    public List<Permission> findAll() {
        return permissionService.findAll();
    }

    public List<Permission> findPermitByUser(Long userId){
        List<Permission> permissions = this.permissionService.getPermitByUserId(userId);
        for (Permission permission : permissions) {
            if (! "".equals(GlobalUtils.emptyString(permission.getOids()))) {
                if ("all".equals(permission.getOids().toLowerCase())){
                    permission.setOperations(operationService.getAll());
                }else{
                    permission.setOperations(operationService.getOperationsByIds(permission.getOids()));
                }
            }
        }
        return permissions;
    }

    public List<String> getPermitCode(List<Permission> permissions){
        List<String> permitCodes = new ArrayList<>();
        for (Permission permission : permissions) {
            String identity =  GlobalUtils.emptyString(permission.getPermission());
            if (!GlobalUtils.isEmpty(identity)) {
                permitCodes.add(permission.getPermission());
            }
            if (!ObjectUtils.isEmpty(permission.getOperations())){
                for(Operation operation: permission.getOperations()){
                    if (!GlobalUtils.isEmpty(identity)) {
                        permitCodes.add(identity+":"+operation.getPermission());
                    }else{
                        permitCodes.add(operation.getPermission());
                    }
                }
            }
        }
        return permitCodes;
    }

    public List<Permission> getPermitTree(List<Permission> permissions) {
        List<Permission> permissionTree = permissionService.getPermitTree(permissions, 0L);
        return permissionTree;
    }



}
