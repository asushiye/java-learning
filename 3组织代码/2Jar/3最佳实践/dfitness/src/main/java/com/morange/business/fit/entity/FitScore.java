package com.morange.business.fit.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : zhenyun.su
 * @since : 2018/12/29
 */

@Entity
@Table(name = "fit_static_score", schema = "fitness")
public class FitScore implements Serializable {
    @Id
    @GeneratedValue(generator = "fit_static_score_pk")
    @GenericGenerator(name = "fit_static_score_pk", strategy = "assigned")
    private String item;
    private Long total;
    private Long sjoin;
    private Long notjoin;
    private String joinrate;
    private Long pass;
    private String passrate;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getSjoin() {
        return sjoin;
    }

    public void setSjoin(Long sjoin) {
        this.sjoin = sjoin;
    }

    public Long getNotjoin() {
        return notjoin;
    }

    public void setNotjoin(Long notjoin) {
        this.notjoin = notjoin;
    }

    public String getJoinrate() {
        return joinrate;
    }

    public void setJoinrate(String joinrate) {
        this.joinrate = joinrate;
    }

    public Long getPass() {
        return pass;
    }

    public void setPass(Long pass) {
        this.pass = pass;
    }

    public String getPassrate() {
        return passrate;
    }

    public void setPassrate(String passrate) {
        this.passrate = passrate;
    }

    @Override
    public String toString() {
        return "FitScore{" +
                "item='" + item + '\'' +
                ", total=" + total +
                ", sjoin=" + sjoin +
                ", notjoin=" + notjoin +
                ", joinrate='" + joinrate + '\'' +
                ", pass=" + pass +
                ", passrate='" + passrate + '\'' +
                '}';
    }
}
