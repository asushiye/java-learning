package com.morange.shiro.realm;

import com.morange.shiro.entity.Permission;
import com.morange.shiro.entity.User;
import com.morange.shiro.service.LoginService;
import com.morange.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map;

public class ShiroAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	//身份认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户名
		String userName = (String) token.getPrincipal();
		// 通过用户名获取用户对象，以后改造从缓存取数据
		User user = this.userService.findByUsername(userName);
		if (user == null) {
			return null;
		}
		//通过 userId 获取该用户拥有的所有权限，返回值根据自己要求设置，并非固定值。
		Map<String,List<Permission>> permissionMap = this.loginService.getMenusAndPermissionsByUser(user.getId());
		// （目录+菜单，分层级）
		user.setMenuList(permissionMap.get("menuList"));
		// （目录+菜单+按钮）
		user.setPermissionList(permissionMap.get("permissionList"));
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // （目录+菜单+按钮）
        List<Permission> permissionList = user.getPermissionList();
        for (Permission permission : permissionList) {
        	if (!StringUtils.isEmpty(permission.getCode())) {
        		info.addStringPermission(permission.getCode());
        	}
        }
		return info;
	}

}
