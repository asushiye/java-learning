/*
Navicat MySQL Data Transfer
Source Server         : localhost_3306
Source Server Version : 50717
Source Database       : sampledb
author            : zhenyun.su
Date              : 2018-09-30 10:30:38
*/

-- 导出 sampledb 的数据库结构
CREATE DATABASE IF NOT EXISTS `sampledb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sampledb`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `type` varchar(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', null, null, 'menu-icon fa fa-desktop', '系统管理', null, '0', '1', 'category', null);
INSERT INTO `sys_permission` VALUES ('2', 'user:listUI', null, null, '用户管理', '系统管理', '1', '1', 'menu', 'user/listUI');
INSERT INTO `sys_permission` VALUES ('3', 'user:add', 'btn-primary', 'icon-ok', '新增', '用户管理', '2', '2', 'button', null);
INSERT INTO `sys_permission` VALUES ('4', 'user:update', 'btn-warning', 'icon-edit', '编辑', '用户管理', '2', '3', 'button', null);
INSERT INTO `sys_permission` VALUES ('5', 'user:delete', 'btn-danger', 'icon-trash', '删除', '用户管理', '2', '4', 'button', null);
INSERT INTO `sys_permission` VALUES ('6', 'role:listUI', null, null, '角色管理', '系统管理', '1', '2', 'menu', 'role/listUI');
INSERT INTO `sys_permission` VALUES ('7', 'role:add', 'btn-primary', 'icon-ok', '新增', '角色管理', '6', '2', 'button', null);
INSERT INTO `sys_permission` VALUES ('8', 'role:update', 'btn-warning', 'icon-edit', '编辑', '角色管理', '6', '3', 'button', null);
INSERT INTO `sys_permission` VALUES ('9', 'role:delete', 'btn-danger', 'icon-trash', '删除', '角色管理', '6', '4', 'button', null);
INSERT INTO `sys_permission` VALUES ('10', 'permission:listUI', null, null, '权限管理', '系统管理', '1', '3', 'menu', 'permission/listUI');
INSERT INTO `sys_permission` VALUES ('11', 'permission:add', 'btn-primary', 'icon-ok', '新增', '权限管理', '10', '1', 'button', null);
INSERT INTO `sys_permission` VALUES ('12', 'permission:update', 'btn-warning', 'icon-edit', '编辑', '权限管理', '10', '2', 'button', null);
INSERT INTO `sys_permission` VALUES ('13', 'permission:delete', 'btn-danger', 'icon-trash', '删除', '权限管理', '10', '3', 'button', null);
INSERT INTO `sys_permission` VALUES ('14', 'user:setRole', 'btn-success', 'icon-cog', '设置角色', '用户管理', '2', '1', 'button', null);
INSERT INTO `sys_permission` VALUES ('15', 'role:setPermission', 'btn-success', 'icon-cog', '设置权限', '角色管理', '6', '1', 'button', null);
INSERT INTO `sys_permission` VALUES ('16', 'demo:add', 'btn-primary', 'icon-ok', '新增', 'demo管理', '19', '2', 'button', '');
INSERT INTO `sys_permission` VALUES ('17', 'demo:update', 'btn-warning', 'icon-edit', '编辑', 'demo管理', '19', '3', 'button', '');
INSERT INTO `sys_permission` VALUES ('19', 'demo:listUI', '', '', 'demo管理', '系统管理', '1', '1', 'menu', 'demo/listUI');
INSERT INTO `sys_permission` VALUES ('20', 'demo:setDemo', 'btn-success', 'icon-cog', '设置角色1', 'demo管理', '19', '0', 'button', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '最高权限', '超级管理员');
INSERT INTO `sys_role` VALUES ('2', '普通权限', '管理员');
INSERT INTO `sys_role` VALUES ('4', 'demo权限', 'demo');
INSERT INTO `sys_role` VALUES ('5', '具有查看功能', '查看人员');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '3');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '4');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '5');
INSERT INTO `sys_role_permission` VALUES ('6', '1', '6');
INSERT INTO `sys_role_permission` VALUES ('7', '1', '7');
INSERT INTO `sys_role_permission` VALUES ('8', '1', '8');
INSERT INTO `sys_role_permission` VALUES ('9', '1', '9');
INSERT INTO `sys_role_permission` VALUES ('10', '1', '10');
INSERT INTO `sys_role_permission` VALUES ('11', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('12', '1', '12');
INSERT INTO `sys_role_permission` VALUES ('13', '1', '13');
INSERT INTO `sys_role_permission` VALUES ('14', '1', '14');
INSERT INTO `sys_role_permission` VALUES ('15', '1', '15');
INSERT INTO `sys_role_permission` VALUES ('16', '1', '16');
INSERT INTO `sys_role_permission` VALUES ('17', '1', '17');
INSERT INTO `sys_role_permission` VALUES ('18', '1', '18');
INSERT INTO `sys_role_permission` VALUES ('19', '1', '19');
INSERT INTO `sys_role_permission` VALUES ('20', '1', '20');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2018-09-29 02:27:08.894000', 'admin@163.com1', '123456', '12345678909', null, '1', '2018-09-29 01:31:26.095000', 'admin');
INSERT INTO `sys_user` VALUES ('5', '2018-09-29 02:27:08.894000', 'suzhenyun@gmail.com', '123456', '13795467304', null, '1', '2018-09-29 02:27:08.894000', 'asu');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('9', '1', '2');
INSERT INTO `sys_user_role` VALUES ('10', '1', '1');
