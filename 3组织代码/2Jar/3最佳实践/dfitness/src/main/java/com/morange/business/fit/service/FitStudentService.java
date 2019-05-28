package com.morange.business.fit.service;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.business.fit.entity.FitStudent;
import com.morange.business.fit.repository.FitStudentRepository;
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
public class FitStudentService {

   @Autowired
   private FitStudentRepository fitStudentRepository;

   @Transactional(readOnly = true)
   public Page<FitStudent> findStudents(String code, String name, String grade, String specialty, Integer pageNum, Integer pageSize){
      Sort sort = new Sort(Sort.Direction.DESC, "code");
      Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
      return fitStudentRepository.findByCodeLikeAndNameLikeAndGradeLikeAndSpecialtyLike(
              GlobalUtils.percent(code), GlobalUtils.percent(name),
              GlobalUtils.percent(grade), GlobalUtils.percent(specialty), pageable);
   }

   @Transactional(readOnly = true)
   public List<FitStudent> findStudents(String code, String name, String grade, String specialty){
      return fitStudentRepository.findByCodeLikeAndNameLikeAndGradeLikeAndSpecialtyLike(
              GlobalUtils.percent(code), GlobalUtils.percent(name),
              GlobalUtils.percent(grade), GlobalUtils.percent(specialty));
   }


   @Transactional(readOnly = true)
   public Page<FitStudent> findAll(Integer pageNum, Integer pageSize){
      Sort sort = new Sort(Sort.Direction.DESC, "code");
      Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
      return fitStudentRepository.findAll(pageable);
   }

   @Transactional()
   public void deleteByCode(String code){
      fitStudentRepository.deleteByCode(code);
   }

   @Transactional()
   public int deleteByCode(List<String> codes){
      int count=0;
      for (String code:codes) {
         fitStudentRepository.deleteByCode(code);
         count++;
      }
      return count;
   }


   @Transactional()
   public void deleteBySpecialty(String specialty){
      fitStudentRepository.deleteBySpecialty(specialty);
   }


   @Transactional()
   public FitStudent Save(FitStudent fitStudent){
      return fitStudentRepository.save(fitStudent);
   }

   @Transactional()
   public int SaveAll(List<FitStudent> fitStudents){
      int count=0;
      for (FitStudent item: fitStudents) {
         fitStudentRepository.delete(item);
         fitStudentRepository.save(item);
         count++;
      }
      return count;
   }

   public List<FitStudent> findByCodeLike(String code){
      return fitStudentRepository.findByCodeLike(GlobalUtils.percent(code));
   }

}
