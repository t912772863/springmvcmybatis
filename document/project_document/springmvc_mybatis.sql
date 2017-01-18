/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : springmvc_mybatis

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-01-18 18:37:11
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
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统文件ID',
  `DATA_ID` bigint(20) NOT NULL COMMENT '所属记录的主键',
  `TABLE_NAME` varchar(50) NOT NULL COMMENT '属于系统哪张表',
  `FILE_TYPE` int(2) NOT NULL COMMENT '文件类型: 1图片文件, 2文本文件, 3视频文件, 4音频文件, 5其它文件',
  `SUFFIX` varchar(10) DEFAULT NULL COMMENT '文件名的后缀',
  `EXTEND_TYPE` varchar(50) DEFAULT NULL COMMENT '属于表中的哪个字段(当一第记录中只有一类图片时,可以为空.如果一条记录有多种类型图片:活动地点图片,活动宣传图片)',
  `URL` varchar(100) NOT NULL COMMENT '文件的相对路径',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `STATUS` int(11) NOT NULL DEFAULT '1' COMMENT '数据状态:-1删除,   1正常',
  PRIMARY KEY (`ID`),
  KEY `表名_ID联合索引` (`DATA_ID`,`TABLE_NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统文件表';

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
-- Table structure for send_message
-- ----------------------------
DROP TABLE IF EXISTS `send_message`;
CREATE TABLE `send_message` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `PORT_NUMBER` varchar(255) DEFAULT NULL COMMENT '发送消息时显示的端口号',
  `TITLE` varchar(255) DEFAULT NULL COMMENT '消息标题',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '内容',
  `SEND_TIME` datetime DEFAULT NULL COMMENT '设定的发送时间',
  `SEND_STATUS` int(11) DEFAULT NULL COMMENT '发送状态: 1待发送, 2发送中, 3发送完成, 4暂停中, ',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `STATUS` int(11) DEFAULT NULL COMMENT '数据状态: 1正常, -1删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='发送消息表';

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `NAME` varchar(255) DEFAULT NULL COMMENT '菜单名',
  `LEVEL` tinyint(4) DEFAULT NULL COMMENT '菜单等级',
  `ICON` varchar(255) DEFAULT NULL COMMENT '菜单图标url',
  `URL` varchar(255) DEFAULT NULL COMMENT '跳转路径',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '菜单备注',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父级菜单ID',
  `STATUS` varchar(255) DEFAULT NULL COMMENT '状态: -1删除,  1正常',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
