package com.morange.business.fit.controller;

import com.alibaba.fastjson.JSON;
import com.morange.business.fit.controller.dto.FitItemCriterionDto;
import com.morange.business.fit.entity.FitItemCriterion;
import com.morange.system.dto.BodyPageDto;
import com.morange.system.page.RequestPage;
import com.morange.system.result.Result;
import common.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FitItemCriterionControllerTest extends SpringBaseTest {

    @Autowired
    FitItemCriterionController fitItemCriterionController;

    @Test(groups = "FitItemCriterionControllerGroup", priority = 4)
    public void testGetListDto() {
        FitItemCriterionDto fitItemCriterionDto = new FitItemCriterionDto();
        RequestPage requestPage = new RequestPage();
        BodyPageDto<FitItemCriterionDto, RequestPage> requestDto = new BodyPageDto<>();
        requestDto.setBody(fitItemCriterionDto);
        requestDto.setPage(requestPage);
        try{
            Result<FitItemCriterion> result= fitItemCriterionController.getListDto(requestDto);}
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(groups = "FitItemCriterionControllerGroup", priority = 5)
    public void testGetListPageDto() {
        FitItemCriterionDto fitItemCriterionDto = new FitItemCriterionDto();
        RequestPage requestPage = new RequestPage();
        requestPage.setPage(0);
        requestPage.setPaged(true);
        requestPage.setSize(10);
        BodyPageDto<FitItemCriterionDto, RequestPage> requestDto = new BodyPageDto<>();
        requestDto.setBody(fitItemCriterionDto);
        requestDto.setPage(requestPage);
        try{
            Result<FitItemCriterion> result= fitItemCriterionController.getListDto(requestDto);}
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test(groups = "FitItemCriterionControllerGroup", priority = 1)
    public void testSave() {
        List<FitItemCriterion> fitItemCriterions = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            FitItemCriterion fitItemCriterion = new FitItemCriterion();
            fitItemCriterion.setCategory("空军学员");
            fitItemCriterion.setItem("格斗术1-5组合");
            fitItemCriterion.setUnit("评价");
            fitItemCriterion.setGender("男");
            fitItemCriterion.setGreat("0");
            fitItemCriterion.setGood("1");
            fitItemCriterion.setPass("2");
            fitItemCriterion.setCreatedBy("test");
            fitItemCriterion.setCreatedDate(LocalDateTime.now());
            fitItemCriterion.setUpdatedBy("test");
            fitItemCriterion.setUpdatedDate(LocalDateTime.now());
            fitItemCriterions.add(fitItemCriterion);
        }
        try {
            Result<String> result = fitItemCriterionController.save(fitItemCriterions);
            System.out.println("success record :" + JSON.toJSONString(result));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(groups = "FitItemCriterionControllerGroup", priority = 3)
    public void testSaveAll() {
        List<FitItemCriterion> fitItemCriterions = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            FitItemCriterion fitItemCriterion = new FitItemCriterion();
            fitItemCriterion.setCategory("空军学员");
            fitItemCriterion.setItem("卧推举▲"+i);
            fitItemCriterion.setUnit("次数");
            fitItemCriterion.setGender("男");
            fitItemCriterion.setGreat("105");
            fitItemCriterion.setGood("90");
            fitItemCriterion.setPass("80");
            fitItemCriterion.setCreatedBy("test");
            fitItemCriterion.setCreatedDate(LocalDateTime.now());
            fitItemCriterion.setUpdatedBy("test");
            fitItemCriterion.setUpdatedDate(LocalDateTime.now());
            fitItemCriterions.add(fitItemCriterion);
        }
        try {
            Result<String> result = fitItemCriterionController.save(fitItemCriterions);
            System.out.println("success record :" + JSON.toJSONString(result));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(groups = "FitItemCriterionControllerGroup", priority = 2)
    public void testDelete() {
        try {
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            ids.add(2L);
            ids.add(4L);
            Result<String> result = fitItemCriterionController.delete(ids);
            System.out.println("success record :" + JSON.toJSONString(result));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}