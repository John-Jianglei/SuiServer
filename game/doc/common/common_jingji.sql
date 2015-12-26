

-- ----------------------------
-- Table structure for `common_jingji`
-- ----------------------------
DROP TABLE IF EXISTS `common_jingji`;
CREATE TABLE `common_jingji` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '称号',
  `posTop` int(11) DEFAULT NULL COMMENT '竞技排名区间的大值,0表示无上限',
  `posBot` int(11) DEFAULT NULL COMMENT '竞技排名区间的小值',
  `interval` int(11) DEFAULT NULL COMMENT '最近一名武将排名的间隔数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common_jingji
-- ----------------------------
INSERT INTO `common_jingji` VALUES ('1', '王', '5', '1', '1');
INSERT INTO `common_jingji` VALUES ('2', '师', '10', '6', '1');
INSERT INTO `common_jingji` VALUES ('3', '公', '20', '11', '1');
INSERT INTO `common_jingji` VALUES ('4', '省', '40', '21', '1');
INSERT INTO `common_jingji` VALUES ('5', '大都督', '80', '41', '1');
INSERT INTO `common_jingji` VALUES ('6', '尚书', '160', '81', '1');
INSERT INTO `common_jingji` VALUES ('7', '长史', '320', '161', '1');
INSERT INTO `common_jingji` VALUES ('8', '中丞', '640', '321', '1');
INSERT INTO `common_jingji` VALUES ('9', '县令', '1200', '641', '2');
INSERT INTO `common_jingji` VALUES ('10', '布衣', '2400', '1201', '5');
INSERT INTO `common_jingji` VALUES ('11', '布衣', '5000', '2401', '10');
INSERT INTO `common_jingji` VALUES ('12', '布衣', '10000', '5001', '15');
INSERT INTO `common_jingji` VALUES ('13', '布衣', '0', '10001', '20');
