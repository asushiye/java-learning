package com.morange.business.common.repository;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

import com.morange.business.common.entity.ComTypeCode;
import com.morange.business.common.entity.TypeCodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComTypeCodeRepository extends JpaRepository<ComTypeCode, TypeCodeId> {

   @Query(value = "select * from com_type_codes where type=?1 and enabled=1",nativeQuery = true)
   public List<ComTypeCode> findByType(String type);

}
