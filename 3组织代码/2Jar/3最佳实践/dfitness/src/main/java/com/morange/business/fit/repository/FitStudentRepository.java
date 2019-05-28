package com.morange.business.fit.repository;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.business.fit.entity.FitStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitStudentRepository extends JpaRepository<FitStudent,Integer> {

   public FitStudent findByName(String name);

   public Page<FitStudent> findByCodeLikeAndNameLikeAndGradeLikeAndSpecialtyLike(String code, String name, String grade, String specialty, Pageable pageable);

   public List<FitStudent> findByCodeLikeAndNameLikeAndGradeLikeAndSpecialtyLike(String code, String name, String grade, String specialty);

   public void deleteByCode(String code);

   public void deleteBySpecialty(String specialty);

   public List<FitStudent> findByCodeLike(String code);
}
