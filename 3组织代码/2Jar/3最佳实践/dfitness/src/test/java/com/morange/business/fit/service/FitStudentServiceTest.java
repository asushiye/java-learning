package com.morange.business.fit.service;

import com.alibaba.fastjson.JSON;
import com.morange.business.fit.entity.FitStudent;
import com.morange.system.page.ResponsePageUtils;
import common.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FitStudentServiceTest extends SpringBaseTest {

    @Autowired
    private FitStudentService fitStudentService;

    @Test(groups = "FitStudentServiceGroup", priority = 0)
    public void testSave() {
        FitStudent fitStudent = new FitStudent();
        fitStudent.setCode("123");
        fitStudent.setName("asushiye");
        fitStudent.setGrade("2018");
        fitStudent.setSpecialty("临床五年");
        fitStudent.setGender("男");
        fitStudent.setCreatedBy("test");
        fitStudent.setCreatedDate(LocalDateTime.now());
        fitStudent.setUpdatedBy("test");
        fitStudent.setUpdatedDate(LocalDateTime.now());
        fitStudentService.Save(fitStudent);
    }

    @Test(groups = "FitStudentServiceGroup", priority = 1)
    public void testSaveAll() {
        List<FitStudent> fitStudents = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            FitStudent fitStudent = new FitStudent();
            fitStudent.setCode("123"+i);
            fitStudent.setName("asushiye");
            fitStudent.setGrade("2018");
            fitStudent.setSpecialty("临床五年");
            fitStudent.setGender("男");
            fitStudent.setCreatedBy("test");
            fitStudent.setCreatedDate(LocalDateTime.now());
            fitStudent.setUpdatedBy("test");
            fitStudent.setUpdatedDate(LocalDateTime.now());
            fitStudents.add(fitStudent);
        }
        int iSuccess = fitStudentService.SaveAll(fitStudents);
        System.out.println("success record :" + iSuccess);
    }

    @Test(groups = "FitStudentServiceGroup", priority = 2)
    public void testFindStudents() {
        Page<FitStudent> fitStudents = fitStudentService.findStudents("1234", "", "2018", "床",
                1, 10);
        System.out.println(JSON.toJSONString(ResponsePageUtils.of(fitStudents)));
    }

    @Test(groups = "FitStudentServiceGroup", priority = 3)
    public void testFindAll() {
        Page<FitStudent> fitStudents = fitStudentService.findAll(0, 10);
        System.out.println(JSON.toJSONString(ResponsePageUtils.ofEx(fitStudents)));
    }
    @Test(groups = "FitStudentServiceGroup", priority = 3)
    public void testFindAll1() {
        Page<FitStudent> fitStudents = fitStudentService.findAll(1, 10);

        System.out.println(JSON.toJSONString(ResponsePageUtils.ofEx(fitStudents)));
    }

    @Test(groups = "FitStudentServiceGroup", priority = 3)
    public void testFindAll2() {
        Page<FitStudent> fitStudents = fitStudentService.findAll(20, 10);
        System.out.println(JSON.toJSONString(ResponsePageUtils.of(fitStudents)));
    }

    @Test(groups = "FitStudentServiceGroup", priority = 3)
    public void testFindAll3() {
        Page<FitStudent> fitStudents = fitStudentService.findAll(21, 10);
        System.out.println(JSON.toJSONString(ResponsePageUtils.of(fitStudents)));
    }


    @Test(groups = "FitStudentServiceGroup", priority = 4)
    public void testDeleteByCode() {
        fitStudentService.deleteByCode("123");
    }

    @Test(groups = "FitStudentServiceGroup", priority = 5)
    public void testDeleteBySpecialty() {
        fitStudentService.deleteBySpecialty("临床五年");
    }

    @Test(groups = "FitStudentServiceGroup", priority = 3)
    public void findByCodeLike() {
        String code="2345";
        List<FitStudent> fitStudents = fitStudentService.findByCodeLike(code);
        System.out.println(JSON.toJSONString(fitStudents));
    }



}