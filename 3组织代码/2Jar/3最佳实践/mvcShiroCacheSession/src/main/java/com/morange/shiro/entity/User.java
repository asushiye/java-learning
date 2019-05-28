package com.morange.shiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="sys_user", schema = "sampledb", catalog = "")
public class User implements Serializable{

	@Id
	@Column(name="user_id")
	@GeneratedValue(generator = "",strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="user_name")
	private String username;
	
	private String password;
	
	private String email;
	
	private String phone;
	
	private String salt;
	
	private Integer status;
	@Column(name="create_time")
	private Date createTime;
	@Column(name="update_time")
	private Date updateTime;
	
	/**
	 * 封装目录+菜单
	 */
	@Transient
	private List<Permission> menuList;
	
	/**
	 * 封装目录+菜单+按钮
	 */
	@Transient
	private List<Permission> permissionList;

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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public List<Permission> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Permission> menuList) {
		this.menuList = menuList;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	@Override
	public String toString() {
		return "Demo [id=" + id + ", userName=" + username + ", password="
				+ password + ", email=" + email + ", phone=" + phone
				+ ", salt=" + salt + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", menuList="
				+ menuList + ", permissionList=" + permissionList + "]";
	}

}
