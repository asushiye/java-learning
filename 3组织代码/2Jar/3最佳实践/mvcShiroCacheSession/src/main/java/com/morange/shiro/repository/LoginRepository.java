package com.morange.shiro.repository;

import com.morange.shiro.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Permission, Integer> {

    @Query(value = "SELECT p.* FROM sys_permission p " +
            " INNER JOIN sys_role_permission rp ON p.permission_id = rp.permission_id " +
            " INNER JOIN sys_user_role ur ON rp.role_id = ur.role_id " +
            " WHERE ur.user_id = ?1" +
            " ORDER BY p.pid ASC,p.sort ASC", nativeQuery = true)
    public List<Permission> getPermissionList(Integer userId);

}
