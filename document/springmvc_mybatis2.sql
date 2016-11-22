/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : springmvc_mybatis2

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-11-22 14:15:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL COMMENT '角色名',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STATUS` int(255) DEFAULT NULL COMMENT '数据状态: 1正常, -1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '测试角色1', '这是一个测试用的角色', '2016-11-17 18:46:37', null, '1');
