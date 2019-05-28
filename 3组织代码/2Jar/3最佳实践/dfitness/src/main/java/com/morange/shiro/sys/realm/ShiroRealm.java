package com.morange.shiro.sys.realm;

import com.alibaba.fastjson.JSON;
import com.morange.shiro.security.entity.Permission;
import com.morange.shiro.security.entity.User;
import com.morange.shiro.security.service.LoginService;
import com.morange.shiro.security.service.UserService;
import com.morange.system.utils.GlobalUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author : zhenyun.su
 * @since : 2019/1/31
 */

public class ShiroRealm extends AuthorizingRealm {

	private final static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

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
		List<Permission> permissionData = this.loginService.findPermitByUser(Long.valueOf(user.getId()));
		List<Permission> permissionTree = this.loginService.getPermitTree(permissionData);
		List<String> permitCode = this.loginService.getPermitCode(permissionData);

		logger.debug("permissionData: "+ JSON.toJSONString(permissionData));
		logger.debug("permissionTree: "+ JSON.toJSONString(permissionTree));
		logger.debug("permitCode: "+ JSON.toJSONString(permitCode));

		user.setPermissionList(permissionTree);
		user.setCodeList(permitCode);

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // （目录+菜单+按钮）
        List<String> codeList = user.getCodeList();
        for (String code : codeList) {
        	if (!GlobalUtils.isEmpty(code)) {
        		info.addStringPermission(code);
        	}
        }
		return info;
	}

}
