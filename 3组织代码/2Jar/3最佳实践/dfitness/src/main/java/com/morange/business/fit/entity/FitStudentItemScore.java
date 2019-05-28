package com.morange.business.fit.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

@Entity
@Table(name = "fit_student_item_score")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery (name = "statistics_student", procedureName = "sp_statistics_student",
                parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "i_title", type = String.class)},
                resultClasses = FitScore.class
        ),
        @NamedStoredProcedureQuery(name = "statistics_item", procedureName = "sp_statistics_item",
                parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "i_title", type = String.class)}
        ),
        @NamedStoredProcedureQuery(name = "statistics_item_col", procedureName = "sp_statistics_item_col",
                parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "i_title", type = String.class)}
        )
})

public class FitStudentItemScore {

  @Id
  @GeneratedValue(generator = "",strategy = GenerationType.IDENTITY)
  @Column(name="fsis_id")
  private Long id;
  private String title;
  private String code;
  private String name;
  private String grade;
  private String specialty;
  @Column(name="fic_id")
  private long ficId;
  private String item;
  private String category;
  private String gender;
  private String unit;
  private String great;
  private String good;
  private String pass;
  private String value;
  private String comment;
  private String sremark;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

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

  public long getFicId() {
    return ficId;
  }

  public void setFicId(long ficId) {
    this.ficId = ficId;
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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getSremark() {
    return sremark;
  }

  public void setSremark(String sremark) {
    this.sremark = sremark;
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
    return "FitStudentItemScore{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", grade='" + grade + '\'' +
            ", specialty='" + specialty + '\'' +
            ", ficId=" + ficId +
            ", item='" + item + '\'' +
            ", category='" + category + '\'' +
            ", gender='" + gender + '\'' +
            ", unit='" + unit + '\'' +
            ", great=" + great +
            ", good=" + good +
            ", pass=" + pass +
            ", value='" + value + '\'' +
            ", comment='" + comment + '\'' +
            ", sremark='" + sremark + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedDate=" + updatedDate +
            '}';
  }
}
