package com.morange.demo.repository;

import com.morange.demo.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemoRepository extends JpaRepository<Demo,Integer> {
   public List<Demo> findByUsernameAndPassword(String username, String password);
   public Demo findByUsername(String username);

   @Query(value="select * from aus_user where password=?1 order by id limit ?2 ", nativeQuery = true)
   public List<Demo> findByPassword(String password, Integer rowCount);

   @Query(value="select * from aus_user order by id limit ?1 ", nativeQuery = true)
   public List<Demo> findAllByLimit(Integer rowCount);

}
