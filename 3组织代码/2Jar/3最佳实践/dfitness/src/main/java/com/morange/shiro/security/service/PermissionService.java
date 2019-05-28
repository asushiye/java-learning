package com.morange.shiro.security.service;

import com.morange.shiro.security.entity.Permission;
import com.morange.shiro.security.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2018/9/17
 */

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Permission> findByPidOrderBySort(Long pid){
        return permissionRepository.findByPidOrderBySort(pid);
    }

    private void addChildren(Permission node){
        List<Permission> permissions = findByPidOrderBySort(node.getId());
        node.setChildren(permissions);
        for (Permission permission : permissions) {
            addChildren(permission);
        }
    }

    public List<Permission> getPermitTree(Long rootId){
        List<Permission> permissionTree = findByPidOrderBySort(rootId);
        for (Permission permission : permissionTree) {
            addChildren(permission);
        }
        return permissionTree;
    }


    public List<Permission> findChildNodes(List<Permission> sources, Long Pid){
        List<Permission> childNodes= new ArrayList<>();
        for (Permission permission : sources) {
            if (Pid.equals(permission.getPid())){
                childNodes.add(permission);
            }
        }
        return childNodes;
    }

    public void addChildren(List<Permission> sources, Permission node){
        List<Permission> childNodes= findChildNodes(sources, node.getId());
        node.setChildren(childNodes);
        for (Permission permission : childNodes) {
            addChildren(sources, permission);
        }
    }

    public List<Permission> getPermitTree(List<Permission> sources, Long rootId){
        List<Permission> rootNodes= findChildNodes(sources,rootId);
        for (Permission permission : rootNodes) {
            addChildren(sources, permission);
        }
        return rootNodes;
    }

    public List<Permission> getPermitByUserId(Long UserId){
        return permissionRepository.getPermitByUserId(UserId);
    }

}
