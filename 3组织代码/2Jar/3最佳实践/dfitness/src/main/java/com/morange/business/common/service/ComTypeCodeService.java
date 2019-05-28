package com.morange.business.common.service;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.business.common.entity.ComTypeCode;
import com.morange.business.common.entity.TypeCodeId;
import com.morange.business.common.repository.ComTypeCodeRepository;
import com.morange.business.fit.entity.FitStudent;
import com.morange.system.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComTypeCodeService {

   @Autowired
   private ComTypeCodeRepository comTypeCodeRepository;

   @Transactional(readOnly = true)
   public List<ComTypeCode> findByType(String type) {
      return comTypeCodeRepository.findByType(type);
   }

   @Transactional()
   public void deleteByType(String type) {
      List<ComTypeCode> comTypeCodes = this.findByType(type);
      if (! ObjectUtils.isEmpty(comTypeCodes)) {
         comTypeCodeRepository.deleteAll(comTypeCodes);
      }
   }

   @Transactional()
   public ComTypeCode save(ComTypeCode comTypeCode) {
      return comTypeCodeRepository.save(comTypeCode);
   }

   public boolean save(String type, String code) {
      TypeCodeId typeCodeId = new TypeCodeId(type, code);
      if (!comTypeCodeRepository.existsById(typeCodeId)){
         ComTypeCode comTypeCode = new ComTypeCode();
         comTypeCode.setId(typeCodeId);
         comTypeCode.setName(code);
         comTypeCode.setDescription(type);
         comTypeCode.setEnabled(true);
         comTypeCode.setCreatedBy("test");
         comTypeCode.setCreatedDate(LocalDateTime.now());
         comTypeCode.setUpdatedBy("test");
         comTypeCode.setUpdatedDate(LocalDateTime.now());
         comTypeCodeRepository.save(comTypeCode);
         return true;
      }else{
         return false;
      }
   }

   @Transactional()
   public int saveAll(List<ComTypeCode> comTypeCodes) {
      int count = 0;
      for (ComTypeCode item : comTypeCodes) {
         comTypeCodeRepository.delete(item);
         comTypeCodeRepository.save(item);
         count++;
      }
      return count;
   }

   @Transactional(readOnly = true)
   public ComTypeCode getById(String type, String code) {
      TypeCodeId typeCodeId = new TypeCodeId(type, code);
      return comTypeCodeRepository.findById(typeCodeId).get();
   }

   @Transactional(readOnly = true)
   public Boolean existsById(String type, String code) {
      TypeCodeId typeCodeId = new TypeCodeId(type, code);
      return comTypeCodeRepository.existsById(typeCodeId);
   }

   @Transactional(readOnly = true)
   public Boolean existsById(TypeCodeId typeCodeId) {
      return comTypeCodeRepository.existsById(typeCodeId);
   }


   @Transactional()
   public void removeById(String type, String code) {
      TypeCodeId typeCodeId = new TypeCodeId(type, code);
      comTypeCodeRepository.deleteById(typeCodeId);
   }

   @Transactional()
   public void deleteById(TypeCodeId typeCodeId ) {
      comTypeCodeRepository.deleteById(typeCodeId);
   }

   @Transactional()
   public void deleteAll(List<ComTypeCode> comTypeCodes) {
      comTypeCodeRepository.deleteAll(comTypeCodes);
   };

   @Transactional()
   public void delete(ComTypeCode comTypeCode) {
      comTypeCodeRepository.delete(comTypeCode);
   };
}