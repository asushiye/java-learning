package com.morange.demo.service;

import com.alibaba.fastjson.JSON;
import com.morange.demo.entity.Student;
import com.morange.system.page.ResponsePageUtils;
import common.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceTest extends SpringBaseTest {

    @Autowired
    private StudentService studentService;

    @Test(groups = "StudentServiceGroup", priority = 0)
    public void testSave() {
        Student student = new Student();
        student.setCode("123");
        student.setName("asushiye");
        student.setGrade("2018");
        student.setSpecialty("临床五年");
        student.setGender("男");
        student.setCreatedBy("test");
        student.setCreatedDate(LocalDateTime.now());
        student.setUpdatedBy("test");
        student.setUpdatedDate(LocalDateTime.now());
        studentService.Save(student);
    }

    @Test(groups = "StudentServiceGroup", priority = 1)
    public void testSaveAll() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Student student = new Student();
            student.setCode("123"+i);
            student.setName("asushiye");
            student.setGrade("2018");
            student.setSpecialty("临床五年");
            student.setGender("男");
            student.setCreatedBy("test");
            student.setCreatedDate(LocalDateTime.now());
            student.setUpdatedBy("test");
            student.setUpdatedDate(LocalDateTime.now());
            students.add(student);
        }
        int iSuccess = studentService.SaveAll(students);
        System.out.println("success record :" + iSuccess);
    }

    @Test(groups = "StudentServiceGroup", priority = 2)
    public void testFindStudents() {
        Page<Student> students = studentService.findStudents("1234", "", "2018", "床",
                1, 10);
        System.out.println(JSON.toJSONString(ResponsePageUtils.of(students)));
    }

    @Test(groups = "StudentServiceGroup", priority = 3)
    public void testFindAll() {
        Page<Student> students = studentService.findAll(0, 10);
        System.out.println(JSON.toJSONString(ResponsePageUtils.ofEx(students)));
    }
    @Test(groups = "StudentServiceGroup", priority = 3)
    public void testFindAll1() {
        Page<Student> students = studentService.findAll(1, 10);

        System.out.println(JSON.toJSONString(ResponsePageUtils.ofEx(students)));
    }

    @Test(groups = "StudentServiceGroup", priority = 3)
    public void testFindAll2() {
        Page<Student> students = studentService.findAll(20, 10);
        System.out.println(JSON.toJSONString(ResponsePageUtils.of(students)));
    }

    @Test(groups = "StudentServiceGroup", priority = 3)
    public void testFindAll3() {
        Page<Student> students = studentService.findAll(21, 10);
        System.out.println(JSON.toJSONString(ResponsePageUtils.of(students)));
    }


    @Test(groups = "StudentServiceGroup", priority = 4)
    public void testDeleteByCode() {
        studentService.deleteByCode("123");
    }

    @Test(groups = "StudentServiceGroup", priority = 5)
    public void testDeleteBySpecialty() {
        studentService.deleteBySpecialty("临床五年");
    }

    @Test(groups = "StudentServiceGroup", priority = 3)
    public void findByCodeLike() {
        String code="2345";
        List<Student> students = studentService.findByCodeLike(code);
        System.out.println(JSON.toJSONString(students));
    }



}