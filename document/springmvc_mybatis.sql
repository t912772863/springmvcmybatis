/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : springmvc_mybatis

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-12-16 15:47:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '活动ID,唯一标识',
  `NAME` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '活动地点',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '活动的备注信息',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `TIME1` datetime DEFAULT NULL COMMENT '报名截止时间',
  `TIME2` datetime DEFAULT NULL COMMENT '活动开始时间',
  `TIME3` datetime DEFAULT NULL COMMENT '活动结束时间',
  `UPDATE_TIME` datetime DEFAULT NULL,
  `ACTIVITY_STATUS` tinyint(1) DEFAULT '0' COMMENT '0报名中,  1报名截止了,  2活动开始了,  3活动结束了',
  `STATUS` tinyint(1) DEFAULT NULL COMMENT '1:正常,  -1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', 'test1', 'aaaaa', 'aaaaaa', '2016-12-15 19:11:52', '2016-12-16 11:38:57', '2016-12-16 11:40:00', '2016-12-16 15:12:25', '2016-12-16 15:12:24', '3', '1');
INSERT INTO `activity` VALUES ('2', 'test2', 'bbbb', 'bbbbb', '2016-12-15 19:11:52', '2016-12-16 11:48:57', '2016-12-15 11:53:18', '2016-12-16 13:56:25', '2016-12-15 19:13:07', '0', '1');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `THIRD_ORDER_ID` varchar(50) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `TOTAL_AMOUNT` int(11) DEFAULT NULL COMMENT '订单总金额(单位:分)',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `ORDER_STATUS` int(11) DEFAULT NULL COMMENT '订单状态: -1订单已取消, 1新增订单(未支付), 2支付成功, 3已退款',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '321654646', '1', '10000', '手动插入的', '1', '2016-12-03 10:54:20', '2016-12-03 10:54:22', '1');
INSERT INTO `order` VALUES ('2', '345646', '1', '500', '500分...', '2', '2016-12-03 10:55:38', '2016-12-03 10:55:40', '1');

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
INSERT INTO `system_menu` VALUES ('1', '测试菜单1', '1', '2213132', '这是一个测试用的菜单', null, '1', '2016-11-15 17:54:05', '2016-11-22 18:18:47');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `MOBILE` varchar(15) DEFAULT NULL COMMENT '用户手机号,',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STATUS` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'tianxiong', '123456', '13510272496', '2016-11-15 17:39:14', '2016-11-15 17:39:16', '1');
INSERT INTO `user` VALUES ('2', 'tian', '123456', '13510272364', '2016-12-01 16:04:47', '2016-12-01 16:04:50', '1');
