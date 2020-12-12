DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';