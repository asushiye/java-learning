package com.morange.business.common.service;

import com.alibaba.fastjson.JSON;
import com.morange.business.common.entity.ComTypeCode;
import com.morange.business.common.entity.TypeCodeId;
import common.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ComTypeCodeServiceTest extends SpringBaseTest {

    @Autowired
    private ComTypeCodeService comTypeCodeService;

    @Test(groups = "ComTypeCodeGroup", priority = 0)
    public void testSave() {
        ComTypeCode comTypeCode = new ComTypeCode();
        TypeCodeId id = new TypeCodeId("status", "01");
        comTypeCode.setId(id);
        comTypeCode.setName("审批01");
        comTypeCode.setDescription("状态");
        comTypeCode.setEnabled(true);
        comTypeCode.setCreatedBy("test");
        comTypeCode.setCreatedDate(LocalDateTime.now());
        comTypeCode.setUpdatedBy("test");
        comTypeCode.setUpdatedDate(LocalDateTime.now());
        System.out.println(JSON.toJSONString(comTypeCodeService.save(comTypeCode)));
    }

    @Test(groups = "ComTypeCodeGroup", priority = 1)
    public void testSaveAll() {
        List<ComTypeCode> comTypeCodes = new ArrayList<>();
        for (int i = 2; i<5; i++){
            ComTypeCode comTypeCode = new ComTypeCode();
            TypeCodeId id = new TypeCodeId("status", "0"+i);
            comTypeCode.setId(id);
            comTypeCode.setName("审批"+i);
            comTypeCode.setDescription("状态");
            comTypeCode.setEnabled(true);
            comTypeCode.setCreatedBy("test");
            comTypeCode.setCreatedDate(LocalDateTime.now());
            comTypeCode.setUpdatedBy("test");
            comTypeCode.setUpdatedDate(LocalDateTime.now());
            comTypeCodes.add(comTypeCode);
        };
        comTypeCodeService.saveAll(comTypeCodes);
    }

    @Test(groups = "ComTypeCodeGroup", priority = 2)
    public void testFindById() {
        ComTypeCode comTypeCode = comTypeCodeService.getById("status", "01");
        System.out.println(JSON.toJSONString(comTypeCode));
    }

    @Test(groups = "ComTypeCodeGroup", priority = 3)
    public void testFindByType() {
        List<ComTypeCode> comTypeCodes= comTypeCodeService.findByType("status");
        System.out.println(JSON.toJSONString(comTypeCodes));
    }

    @Test(groups = "ComTypeCodeGroup", priority = 4)
    public void testDeleteById() {
        comTypeCodeService.removeById("status", "01");
    }

    @Test(groups = "ComTypeCodeGroup", priority = 5)
    public void testDeleteByType() {
        comTypeCodeService.deleteByType("status");
    }

}