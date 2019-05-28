package com.morange.business.fit.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.morange.system.json.StringToJsonSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

@Entity
@Table(name = "fit_item_criterion")
public class FitItemCriterion {
  @Id
  @GeneratedValue(generator = "",strategy = GenerationType.IDENTITY)
  @Column(name="fic_id")
  private Long id;
  private String item;
  private String category;
  private String gender;
  private String unit;
  private String great;
  private String good;
  private String pass;
  @Column(name="created_by")
  private String createdBy;
  @Column(name="created_date")
  private LocalDateTime createdDate;
  @Column(name="updated_by")
  private String updatedBy;
  @Column(name="updated_date")
  private LocalDateTime updatedDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getGreat() {
    return great;
  }

  public void setGreat(String great) {
    this.great = great;
  }

  public String getGood() {
    return good;
  }

  public void setGood(String good) {
    this.good = good;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  @Override
  public String toString() {
    return "FitItemCriterion{" +
            "id=" + id +
            ", item='" + item + '\'' +
            ", category='" + category + '\'' +
            ", gender='" + gender + '\'' +
            ", unit='" + unit + '\'' +
            ", great=" + great +
            ", good=" + good +
            ", pass=" + pass +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedDate=" + updatedDate +
            '}';
  }
}
