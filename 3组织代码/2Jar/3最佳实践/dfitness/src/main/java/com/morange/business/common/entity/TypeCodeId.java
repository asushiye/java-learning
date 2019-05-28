package com.morange.business.common.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 * @Commit: 定义复合主键
 */
@Embeddable
public class TypeCodeId implements Serializable {

    @Column(length=30)
    private String code;
    @Column(length=30)
    private String type;

    public TypeCodeId() {
    }

    public TypeCodeId(String type,String code) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true; };
        if (o == null || getClass() != o.getClass()) { return false;};
        TypeCodeId that = (TypeCodeId) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, type);
    }
}
