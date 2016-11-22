/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : springmvc_mybatis

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-11-22 14:15:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `NAME` varchar(255) DEFAULT NULL COMMENT '菜单名',
  `LEVEL` tinyint(4) DEFAULT NULL COMMENT '菜单等级',
  `ICON` varchar(255) DEFAULT NULL COMMENT '菜单图标url',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '菜单备注',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父级菜单ID',
  `STATUS` varchar(255) DEFAULT NULL COMMENT '状态: -1删除,  1正常',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', '测试菜单1', '1', '2213132', '这是一个测试用的菜单', null, '1', '2016-11-15 17:54:05', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STATUS` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '田雄', '123456', '2016-11-15 17:39:14', '2016-11-15 17:39:16', '1');
INSERT INTO `user` VALUES ('13', 'tset', null, '2016-11-17 12:01:13', null, null);
INSERT INTO `user` VALUES ('17', 'tset', null, '2016-11-19 09:57:13', null, null);
INSERT INTO `user` VALUES ('19', 'tset', null, '2016-11-19 10:13:39', null, '1');
