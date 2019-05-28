package com.morange.shiro.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sys_role", schema = "sampledb", catalog = "")
public class Role implements Serializable{

	@Id
	@Column(name="role_id")
	@GeneratedValue(generator = "",strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String descr;
	
	@Transient
	private Boolean selected;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", descr=" + descr
				+ ", selected=" + selected + "]";
	}

}
