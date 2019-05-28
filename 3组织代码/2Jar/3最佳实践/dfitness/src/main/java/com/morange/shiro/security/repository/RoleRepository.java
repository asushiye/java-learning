package com.morange.shiro.security.repository;

import com.morange.shiro.security.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    public Page<Role> findByName(String name, Pageable pageable);

    public Page<Role> findAll(Pageable pageable);

    @Query(value = "SELECT p.* FROM sys_role p "+
            " INNER JOIN sys_user_role ur ON p.role_id = ur.role_id  " +
            " where user_id = ?1 ORDER BY p.role_id ASC ", nativeQuery = true)
    public List<Role> getRoleListByUserId(Integer userId);
}
