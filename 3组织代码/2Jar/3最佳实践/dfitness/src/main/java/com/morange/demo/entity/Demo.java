package com.morange.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : zhenyun.su
 * @since : 2018/8/27
 */

@Entity(name="demo")
@Table(name = "aus_demo", schema = "ng2angle", catalog = "")
public class Demo {
    @Id
    @GeneratedValue(generator = "",strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "username", nullable = true, length = 256,unique=true)
    private String username;
    @Basic
    @Column(name = "password", nullable = true, length = 30)
    private String password;
    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    private String phone;
    @Basic
    @Column(name = "created", nullable = true)
    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
