package com.morange.business.fit.service;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.alibaba.fastjson.JSON;
import com.morange.business.common.service.ComTypeCodeService;
import com.morange.business.common.utils.FitnessVariable;
import com.morange.business.fit.entity.FitScore;
import com.morange.business.fit.entity.FitStudentItemScore;
import com.morange.business.fit.repository.FitStudentItemScoreRepository;
import com.morange.system.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FitStudentItemScoreService {

   @PersistenceContext
   private EntityManager entityManager;

   @Autowired
   private FitStudentItemScoreRepository fitStudentItemScoreRepository;

   @Autowired
   private ComTypeCodeService comTypeCodeService;

   @Transactional(readOnly = true)
   public Page<FitStudentItemScore> findStudentItemScore(String title, String code, String name, String specialty,String item,String category, int pageNum, int pageSize){
      Sort sort = new Sort(Sort.Direction.DESC, "code");
      Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
      return fitStudentItemScoreRepository.findByTitleAndCodeLikeAndItemLikeAndCategoryLikeAndNameLikeAndSpecialtyLike(
              title, GlobalUtils.percent(code), GlobalUtils.percent(item),
              GlobalUtils.percent(category), GlobalUtils.percent(name), GlobalUtils.percent(specialty), pageable);
   }

   @Transactional(readOnly = true)
   public List<FitStudentItemScore> findStudentItemScore(String title, String code, String name, String specialty, String item,String category){
      return fitStudentItemScoreRepository.findByTitleAndCodeLikeAndItemLikeAndCategoryLikeAndNameLikeAndSpecialtyLike(
              title, GlobalUtils.percent(code), GlobalUtils.percent(item),
              GlobalUtils.percent(category), GlobalUtils.percent(name), GlobalUtils.percent(specialty));
   }

   @Transactional()
   public FitStudentItemScore Save(FitStudentItemScore fitStudentItemScore){
      return fitStudentItemScoreRepository.save(fitStudentItemScore);
   }

   @Transactional()
   public Integer SaveAll(List<FitStudentItemScore> fitStudentItemScores){
      int count= 0;
      boolean IsSuccess=false;
      if (fitStudentItemScores.size()>= 1){
         IsSuccess= comTypeCodeService.save(FitnessVariable.SCORE_TYPE, fitStudentItemScores.get(0).getTitle());
      };

      if (IsSuccess){
         for (FitStudentItemScore item :fitStudentItemScores) {
            fitStudentItemScoreRepository.save(item);
            count++;
         }
      }else{
         count= -1;
      }
      return count;
   }

   @Transactional()
   public Integer updateAll(List<FitStudentItemScore> fitStudentItemScores){
      int count= 0;
      for (FitStudentItemScore item :fitStudentItemScores) {
         fitStudentItemScoreRepository.save(item);
         count++;
      }
      return count;
   }


   @Transactional()
   public void  deleteById(Long id){
      fitStudentItemScoreRepository.deleteById(id);
   }

   @Transactional()
   public int deleteByIds(List<Long> ids){
      int count=0;
      for (Long id:ids) {
         try{
            fitStudentItemScoreRepository.deleteById(id);
         }catch (Exception e){
            //
         }
         count++;
      }
      return count;
   }

   public List<FitScore> getStatisticsStudent(String title){
      StoredProcedureQuery SP= entityManager.createNamedStoredProcedureQuery("statistics_student");
      SP.setParameter("i_title",title);
      List<FitScore> objs =  SP.getResultList();
      System.out.println(JSON.toJSONString(objs));
      return objs;
   }

   public List getStatisticsItem(String title){
      StoredProcedureQuery SP= entityManager.createNamedStoredProcedureQuery("statistics_item");
      SP.setParameter("i_title",title);
      List objs =  SP.getResultList();
      System.out.println(JSON.toJSONString(objs));
      return objs;
   }

   public List<String> getStatisticsItemCol(String title){
      StoredProcedureQuery SP= entityManager.createNamedStoredProcedureQuery("statistics_item_col");
      SP.setParameter("i_title",title);
      List<String> objs =  SP.getResultList();
      System.out.println(JSON.toJSONString(objs));
      return objs;
   }

   @Transactional
   public void deleteByTitle(String title){
      String SQL = "delete  from fit_student_item_score where title = '"+title+"'";
      entityManager.createNativeQuery(SQL).executeUpdate();

      String codeSQL = "delete  from com_type_codes where type = '"+FitnessVariable.SCORE_TYPE+"' and code='"+title+"'";
      entityManager.createNativeQuery(codeSQL).executeUpdate();

   }

   public void szy(){

   }

}
