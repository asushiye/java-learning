package com.morange.business.fit.repository;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.business.fit.entity.FitStudentItemScore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitStudentItemScoreRepository extends JpaRepository<FitStudentItemScore,Long> {

   public Page<FitStudentItemScore> findByTitleAndCodeLikeAndItemLikeAndCategoryLikeAndNameLikeAndSpecialtyLike
           (String title, String code, String item, String category, String name, String specialty, Pageable pageable);

   public List<FitStudentItemScore> findByTitleAndCodeLikeAndItemLikeAndCategoryLikeAndNameLikeAndSpecialtyLike
           (String title, String code, String item, String category, String name, String specialty);

}
