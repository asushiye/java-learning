package com.morange.demo.controller.dto;

import com.morange.system.page.RequestPage;

/**
 * @author : zhenyun.su
 * @since : 2019/1/7
 */

public class StudentDtoEx extends RequestPage {
    private String code;
    private String name;
    private String grade;
    private String specialty;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }


}
