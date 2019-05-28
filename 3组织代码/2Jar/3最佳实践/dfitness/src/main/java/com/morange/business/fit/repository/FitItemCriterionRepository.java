package com.morange.business.fit.repository;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.business.fit.entity.FitItemCriterion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitItemCriterionRepository extends JpaRepository<FitItemCriterion,Long> {

   public Page<FitItemCriterion> findByItemLikeAndCategoryLikeAndGenderLike
           (String item, String category, String gender, Pageable pageable);

   public List<FitItemCriterion> findByItemLikeAndCategoryLikeAndGenderLike
           (String item, String category, String gender);

   public void deleteByItemAndCategoryAndGender(String Item, String category, String gender);

   public void deleteByCategory(String category);

}
