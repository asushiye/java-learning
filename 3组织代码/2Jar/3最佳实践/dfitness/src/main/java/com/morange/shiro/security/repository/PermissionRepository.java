package com.morange.shiro.security.repository;

import com.morange.shiro.security.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {


    public Page<Permission> findByName(String name, Pageable pageable);

    public Page<Permission> findAll(Pageable pageable);

    @Query(value = "SELECT p.* FROM sys_permission p "+
            " INNER JOIN sys_role_permission rp ON p.permit_id = rp.permission_id " +
            " where rp.role_id = ?1 ORDER BY p.permit_id ASC ", nativeQuery = true)
    public List<Permission> getPermissionListByRoleId(Integer roleId);

    public List<Permission> findByPidOrderBySort(Long pid);

    @Query(value = "SELECT p.* FROM sys_permission p " +
            " INNER JOIN sys_role_permission rp ON p.permit_id = rp.permission_id " +
            " INNER JOIN sys_user_role ur ON rp.role_id = ur.role_id " +
            " WHERE ur.user_id = ?1" +
            " ORDER BY p.parent_id ASC,p.sort ASC", nativeQuery = true)
    public List<Permission> getPermitByUserId(Long userId);

}
