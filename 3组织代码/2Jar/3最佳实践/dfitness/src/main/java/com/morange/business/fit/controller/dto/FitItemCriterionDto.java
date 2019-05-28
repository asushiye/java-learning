package com.morange.business.fit.controller.dto;

/**
 * @author : zhenyun.su
 * @since : 2019/1/7
 */

public class FitItemCriterionDto {
    private String item;
    private String category;
    private String gender;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
