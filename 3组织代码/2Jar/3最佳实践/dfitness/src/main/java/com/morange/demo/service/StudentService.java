package com.morange.demo.service;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.demo.entity.Student;
import com.morange.demo.repository.StudentRepository;
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
public class StudentService {

   @Autowired
   private StudentRepository studentRepository;

   @Transactional(readOnly = true)
   public Page<Student> findStudents(String code, String name, String grade, String specialty, Integer pageNum, Integer pageSize){
      Sort sort = new Sort(Sort.Direction.DESC, "code");
      Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
      return studentRepository.findByCodeLikeAndNameLikeAndGradeLikeAndSpecialtyLike(
              GlobalUtils.percent(code), GlobalUtils.percent(name),
              GlobalUtils.percent(grade), GlobalUtils.percent(specialty), pageable);
   }

   @Transactional(readOnly = true)
   public List<Student> findStudents(String code, String name, String grade, String specialty){
      return studentRepository.findByCodeLikeAndNameLikeAndGradeLikeAndSpecialtyLike(
              GlobalUtils.percent(code), GlobalUtils.percent(name),
              GlobalUtils.percent(grade), GlobalUtils.percent(specialty));
   }


   @Transactional(readOnly = true)
   public Page<Student> findAll(Integer pageNum, Integer pageSize){
      Sort sort = new Sort(Sort.Direction.DESC, "code");
      Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
      return studentRepository.findAll(pageable);
   }

   @Transactional()
   public void deleteByCode(String code){
      studentRepository.deleteByCode(code);
   }

   @Transactional()
   public int deleteByCode(List<String> codes){
      int count=0;
      for (String code:codes) {
         studentRepository.deleteByCode(code);
         count++;
      }
      return count;
   }


   @Transactional()
   public void deleteBySpecialty(String specialty){
      studentRepository.deleteBySpecialty(specialty);
   }


   @Transactional()
   public Student Save(Student student){
      return studentRepository.save(student);
   }

   @Transactional()
   public int SaveAll(List<Student> students){
      int count=0;
      for (Student item: students) {
         studentRepository.delete(item);
         studentRepository.save(item);
         count++;
      }
      return count;
   }

   public List<Student> findByCodeLike(String code){
      return studentRepository.findByCodeLike(GlobalUtils.percent(code));
   }

}
