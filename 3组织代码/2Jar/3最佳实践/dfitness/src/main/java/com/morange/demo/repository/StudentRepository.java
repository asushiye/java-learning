package com.morange.demo.repository;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

   public Student findByName(String name);

   public Page<Student> findByCodeLikeAndNameLikeAndGradeLikeAndSpecialtyLike(String code, String name, String grade, String specialty, Pageable pageable);

   public List<Student> findByCodeLikeAndNameLikeAndGradeLikeAndSpecialtyLike(String code, String name, String grade, String specialty);

   public void deleteByCode(String code);

   public void deleteBySpecialty(String specialty);

   public List<Student> findByCodeLike(String code);
}
