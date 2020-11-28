/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : electronic_commerce

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-11-28 18:20:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `p_id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `p_create_datetime` datetime DEFAULT NULL COMMENT '数据创建时间',
  `p_name` varchar(1000) DEFAULT NULL COMMENT '商品名称',
  `p_rank` int(11) DEFAULT NULL COMMENT '排序字段',
  `p_remark` longtext COMMENT '备注',
  `p_state` int(2) DEFAULT '0' COMMENT '数据状态（0-正常，1-删除）',
  `p_update_datetime` datetime DEFAULT NULL COMMENT '数据更新时间',
  `p_version` int(11) DEFAULT NULL COMMENT '版本号',
  `goods_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `goods_desc` varchar(1000) DEFAULT NULL COMMENT '商品简介',
  `goods_status` int(255) DEFAULT '0' COMMENT '商品状态（0-正常，1-下架）',
  `goods_stock` int(10) DEFAULT NULL COMMENT '商品库存',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `p_id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `p_create_datetime` datetime DEFAULT NULL COMMENT '数据创建时间',
  `p_name` varchar(1000) DEFAULT NULL COMMENT '订单名称',
  `p_rank` int(11) DEFAULT NULL COMMENT '排序字段',
  `p_remark` longtext COMMENT '备注',
  `p_state` int(2) DEFAULT '0' COMMENT '数据状态（0-正常，1-删除）',
  `p_update_datetime` datetime DEFAULT NULL COMMENT '数据更新时间',
  `p_version` int(11) DEFAULT NULL COMMENT '版本号',
  `order_status` int(2) DEFAULT NULL COMMENT '订单状态（1-下单完成，2-关闭订单，3-订单完成）',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单总价',
  `user_id` int(20) NOT NULL COMMENT '下单用户id',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';

-- ----------------------------
-- Table structure for tt_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `tt_order_goods`;
CREATE TABLE `tt_order_goods` (
  `p_id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `p_create_datetime` datetime DEFAULT NULL COMMENT '数据创建时间',
  `p_name` varchar(1000) DEFAULT NULL COMMENT '名称',
  `p_rank` int(11) DEFAULT NULL COMMENT '排序字段',
  `p_remark` longtext COMMENT '备注',
  `p_state` int(2) DEFAULT '0' COMMENT '数据状态（0-正常，1-删除）',
  `p_update_datetime` datetime DEFAULT NULL COMMENT '数据更新时间',
  `p_version` int(11) DEFAULT NULL COMMENT '版本号',
  `order_id` int(10) NOT NULL COMMENT '订单id',
  `goods_id` int(10) NOT NULL COMMENT '商品id',
  `goods_count` int(10) NOT NULL COMMENT '商品购买数量',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品关系表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `p_id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `p_create_datetime` datetime DEFAULT NULL COMMENT '数据创建时间',
  `p_name` varchar(1000) DEFAULT NULL COMMENT '用户账号',
  `p_rank` int(11) DEFAULT NULL COMMENT '排序字段',
  `p_remark` longtext COMMENT '备注',
  `p_state` int(2) DEFAULT '0' COMMENT '数据状态（0-正常，1-删除）',
  `p_update_datetime` datetime DEFAULT NULL COMMENT '数据更新时间',
  `p_version` int(11) DEFAULT NULL COMMENT '版本号',
  `user_password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `user_cellphone_number` varchar(32) DEFAULT NULL COMMENT '用户手机号码',
  `user_email` varchar(32) DEFAULT NULL COMMENT '用户邮箱',
  `user_status` int(2) DEFAULT '0' COMMENT '用户状态（0-正常，1-锁定）',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
SET FOREIGN_KEY_CHECKS=1;
