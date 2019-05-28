package com.morange.shiro.security.repository;

import com.morange.shiro.security.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

   public Page<User> findByUsername(String username, Pageable pageable);

   public Page<User> findAll(Pageable pageable);

   public User findByUsername(String username);

   @Transactional
   @Modifying
   @Query(value="update sys_user set user_name= ?1, email= ?2,phone= ?3 where user_id= ?4 ", nativeQuery = true)
   public void update(String username, String email, String phone, Integer id);

   @Transactional
   @Modifying
   @Query(value="delete from sys_user_role where user_id= ?1 ", nativeQuery = true)
   public void deleteUserRoleByUserId(Integer id);

   @Transactional
   @Modifying
   @Query(value="insert into sys_user_role(user_id, role_id) value (?1, ?2)", nativeQuery = true)
   public void InsertUserRoleByUserId(Integer userId, Integer roleId);


}
