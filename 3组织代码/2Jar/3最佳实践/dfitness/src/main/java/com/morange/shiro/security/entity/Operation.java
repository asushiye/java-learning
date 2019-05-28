package com.morange.shiro.security.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2019/1/24
 * @Comment: 资源操作权限
			permission 授权标识，拦截URL的前缀,
			name 页面显示名称,
			description 描述,
			enable true启用，false不启用,
 */

@Entity
@Table(name="sys_operation", schema = "fitness", catalog = "")
public class Operation implements Serializable{
	@Id
	@Column(name="operation_id")
	@GeneratedValue(generator = "",strategy = GenerationType.IDENTITY)
	private Integer id;
	private String operation;
	private String name;
    private String permission;
	private String description;
	private Boolean  enable ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "Operation{" +
				"id=" + id +
				", operation='" + operation + '\'' +
				", name='" + name + '\'' +
				", permission='" + permission + '\'' +
				", description='" + description + '\'' +
				", enable=" + enable +
				'}';
	}
}
