package com.morange.business.fit.service;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.business.fit.entity.FitItemCriterion;
import com.morange.business.fit.repository.FitItemCriterionRepository;
import com.morange.system.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FitItemCriterionService{
   @Autowired
   private FitItemCriterionRepository fitItemCriterionRepository;

   @Transactional(readOnly = true)
   public Page<FitItemCriterion> findItemCriterion(String item, String category, String gender, int pageNum, int pageSize){
      Sort sort = new Sort(Sort.Direction.DESC, "item");
      Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
      return fitItemCriterionRepository.findByItemLikeAndCategoryLikeAndGenderLike(GlobalUtils.percent(item), GlobalUtils.percent(category), GlobalUtils.percent(gender), pageable);
   }

   @Transactional(readOnly = true)
   public List<FitItemCriterion> findItemCriterion(String item, String category, String gender){
      return fitItemCriterionRepository.findByItemLikeAndCategoryLikeAndGenderLike(GlobalUtils.percent(item), GlobalUtils.percent(category), GlobalUtils.percent(gender));
   }

   @Transactional()
   public void deleteByCategory(String category){
      fitItemCriterionRepository.deleteByCategory(category);
   }

   @Transactional()
   public void  deleteByItemAndCategoryAndGender(String Item, String category, String gender){
      fitItemCriterionRepository.deleteByItemAndCategoryAndGender(Item,category, gender);
   }

   @Transactional()
   public void  deleteById(Long id){
      fitItemCriterionRepository.deleteById(id);
   }

//   @Transactional(readOnly = false)
   public int deleteByIds(List<Long> ids){
      int count=0;
      for (Long id:ids) {
         try{
             fitItemCriterionRepository.deleteById(id);
         }catch (Exception e){
            //
         }
         count++;
      }
      return count;
   }

   @Transactional()
   public FitItemCriterion Save(FitItemCriterion fitItemCriterion){
      return fitItemCriterionRepository.save(fitItemCriterion);
   }

   @Transactional()
   public int SaveAll(List<FitItemCriterion> fitItemCriterions){
      int count=0;
      for (FitItemCriterion item: fitItemCriterions) {
         fitItemCriterionRepository.deleteByItemAndCategoryAndGender(item.getItem(), item.getCategory(),item.getGender());
         fitItemCriterionRepository.save(item);
         count++;
      }
      return count;
   }

}
