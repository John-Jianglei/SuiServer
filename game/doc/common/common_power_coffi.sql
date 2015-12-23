/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : common

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-23 21:32:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_power_coffi`
-- ----------------------------
DROP TABLE IF EXISTS `common_power_coffi`;
CREATE TABLE `common_power_coffi` (
  `id` int(11) NOT NULL,
  `attackC` int(11) NOT NULL COMMENT '攻击系数，需除以100',
  `healthC` int(11) NOT NULL COMMENT '生命系数，需除以100',
  `basePower` int(11) NOT NULL,
  `pojiaC` int(11) NOT NULL COMMENT '系数，需除以100',
  `hujiaC` int(11) NOT NULL COMMENT '系数，需除以100',
  `fachuanC` int(11) NOT NULL COMMENT '系数，需除以100',
  `fakangC` int(11) NOT NULL COMMENT '系数，需除以100',
  `baojiC` int(11) NOT NULL COMMENT '系数，需除以100',
  `renxingC` int(11) NOT NULL COMMENT '系数，需除以100',
  `mingzhongC` int(11) NOT NULL COMMENT '系数，需除以100',
  `shanbiC` int(11) NOT NULL COMMENT '系数，需除以100',
  `xixueC` int(11) NOT NULL COMMENT '系数，需除以100',
  `fantanC` int(11) NOT NULL COMMENT '系数，需除以100',
  `jiyunC` int(11) NOT NULL COMMENT '系数，需除以100',
  `kangyunC` int(11) NOT NULL COMMENT '系数，需除以100',
  `gedangC` int(11) NOT NULL COMMENT '系数，需除以100',
  `reduceC` int(11) NOT NULL COMMENT '系数，需除以100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common_power_coffi
-- ----------------------------
INSERT INTO `common_power_coffi` VALUES ('1', '30', '5', '200', '160', '60', '150', '90', '150', '70', '150', '90', '150', '100', '150', '90', '70', '100');
