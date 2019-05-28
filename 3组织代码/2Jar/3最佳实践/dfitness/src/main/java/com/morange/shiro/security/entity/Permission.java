package com.morange.shiro.security.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2019/1/24
 * @Comment: 自定义全局异常
			name  权限名称,
			permission  授权标识,
			text  菜单界面显示内容,
			heading true 目录，false 使用链接的菜单,
			link  内部链接,
			elink  外部链接,
			target  链接打开的页面显示的目标,
			icon  菜单图标,
			alert  警告内容,
			label  警告外观CSS CLASS,
			sort   菜单排序,
			operation_ids  操作,
			enable true 启用， false 为禁止,
			List<Permission> children 子菜单，正常支持3 层就够了
 			List<Operation> operations 根据operation_ids 存放操作权限
 */
@Entity
@Table(name="sys_permission", schema = "fitness", catalog = "")
public class Permission implements Serializable{
	@Id
	@Column(name="permit_id")
	@GeneratedValue(generator = "",strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="parent_id")
	private Long pid;
	private String name;
	private String permission;

	private String text;
	private Boolean heading;
	private String link;
	private String elink;
	private String target;
	private String icon;
	private String alert;
	private String label ;
	private Integer sort ;
	@Column(name="operation_ids")
	private String oids;
	private Boolean enable ;

	@Transient
    private List<Permission> children = new ArrayList<>();
	@Transient
	private List<Operation> operations = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getHeading() {
		return heading;
	}

	public void setHeading(Boolean heading) {
		this.heading = heading;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getElink() {
		return elink;
	}

	public void setElink(String elink) {
		this.elink = elink;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}



	public String getOids() {
		return oids;
	}

	public void setOids(String oids) {
		this.oids = oids;
	}

	public Boolean getEnable() {
		return enable;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "Permission{" +
				"id=" + id +
				", pid=" + pid +
				", name='" + name + '\'' +
				", permission='" + permission + '\'' +
				", text='" + text + '\'' +
				", heading=" + heading +
				", link='" + link + '\'' +
				", elink='" + elink + '\'' +
				", target='" + target + '\'' +
				", icon='" + icon + '\'' +
				", alert='" + alert + '\'' +
				", label='" + label + '\'' +
				", sort=" + sort +
				", oids='" + oids + '\'' +
				", enable=" + enable +
				", children=" + children +
				", operations=" + operations +
				'}';
	}
}
