package com.morange.shiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sys_permission", schema = "sampledb", catalog = "")
public class Permission implements Serializable{

	@Id
	@Column(name="permission_id")
	@GeneratedValue(generator = "",strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Integer pid;
	@Column(name="parent_name")
	private String parentName;
	
	private String type;
	
	private String url;
	
	private String code;
	
	private int sort;
	
	private String color;
	
	private String icon;
	
	@Transient
	private Boolean checked;
	
	@Transient
    private List<Permission> children = new ArrayList<>();

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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}
	
	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", pid=" + pid
				+ ", parentName=" + parentName + ", type=" + type + ", url="
				+ url + ", code=" + code + ", sort=" + sort + ", color="
				+ color + ", icon=" + icon + ", checked=" + checked
				+ ", children=" + children + "]";
	}

}
