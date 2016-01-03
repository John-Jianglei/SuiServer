/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-01-03 14:48:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_vip_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `common_vip_privilege`;
CREATE TABLE `common_vip_privilege` (
  `vipLevel` int(11) NOT NULL,
  `buyStrengthDesc` varchar(255) NOT NULL,
  `buyStrength` int(11) NOT NULL,
  `maxStrengthDesc` varchar(255) NOT NULL,
  `maxStrength` int(11) NOT NULL,
  `resetPassDesc` varchar(255) NOT NULL,
  `resetPassNum` int(11) NOT NULL,
  `zhaoCaiDesc` varchar(255) NOT NULL,
  `zhaoCaiNum` int(11) NOT NULL,
  `libaoDesc` varchar(255) NOT NULL,
  `libao` int(11) NOT NULL,
  `saoDangDesc` varchar(255) NOT NULL,
  `saoDang` int(11) NOT NULL,
  `chongZhiDesc` varchar(255) NOT NULL,
  `chongZhi` int(11) NOT NULL,
  `shenJiangLuDesc` varchar(255) NOT NULL,
  `shenJiangLu` int(11) NOT NULL,
  PRIMARY KEY (`vipLevel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common_vip_privilege
-- ----------------------------
INSERT INTO `common_vip_privilege` VALUES ('0', '购买体力次数', '1', '最大体力', '16', '重置关卡次数', '0', '招财次数', '3', 'VIP礼包', '0', '扫荡功能', '0', '累积充值', '0', '免费神将录次数', '2');
INSERT INTO `common_vip_privilege` VALUES ('1', '购买体力次数', '2', '最大体力', '16', '重置关卡次数', '1', '招财次数', '5', 'VIP礼包', '1', '扫荡功能', '0', '累积充值', '6', '免费神将录次数', '2');
INSERT INTO `common_vip_privilege` VALUES ('2', '购买体力次数', '3', '最大体力', '16', '重置关卡次数', '1', '招财次数', '6', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '30', '免费神将录次数', '2');
INSERT INTO `common_vip_privilege` VALUES ('3', '购买体力次数', '4', '最大体力', '20', '重置关卡次数', '2', '招财次数', '7', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '90', '免费神将录次数', '3');
INSERT INTO `common_vip_privilege` VALUES ('4', '购买体力次数', '5', '最大体力', '20', '重置关卡次数', '2', '招财次数', '8', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '200', '免费神将录次数', '3');
INSERT INTO `common_vip_privilege` VALUES ('5', '购买体力次数', '6', '最大体力', '24', '重置关卡次数', '3', '招财次数', '9', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '350', '免费神将录次数', '3');
INSERT INTO `common_vip_privilege` VALUES ('6', '购买体力次数', '7', '最大体力', '24', '重置关卡次数', '3', '招财次数', '10', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '600', '免费神将录次数', '4');
INSERT INTO `common_vip_privilege` VALUES ('7', '购买体力次数', '8', '最大体力', '28', '重置关卡次数', '4', '招财次数', '11', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '1000', '免费神将录次数', '4');
INSERT INTO `common_vip_privilege` VALUES ('8', '购买体力次数', '9', '最大体力', '28', '重置关卡次数', '4', '招财次数', '12', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '2200', '免费神将录次数', '4');
INSERT INTO `common_vip_privilege` VALUES ('9', '购买体力次数', '10', '最大体力', '32', '重置关卡次数', '4', '招财次数', '13', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '4500', '免费神将录次数', '5');
INSERT INTO `common_vip_privilege` VALUES ('10', '购买体力次数', '11', '最大体力', '32', '重置关卡次数', '5', '招财次数', '14', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '10000', '免费神将录次数', '5');
INSERT INTO `common_vip_privilege` VALUES ('11', '购买体力次数', '12', '最大体力', '36', '重置关卡次数', '5', '招财次数', '15', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '20000', '免费神将录次数', '6');
INSERT INTO `common_vip_privilege` VALUES ('12', '购买体力次数', '13', '最大体力', '36', '重置关卡次数', '6', '招财次数', '16', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '40000', '免费神将录次数', '6');
INSERT INTO `common_vip_privilege` VALUES ('13', '购买体力次数', '14', '最大体力', '40', '重置关卡次数', '6', '招财次数', '17', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '60000', '免费神将录次数', '7');
INSERT INTO `common_vip_privilege` VALUES ('14', '购买体力次数', '15', '最大体力', '40', '重置关卡次数', '7', '招财次数', '18', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '80000', '免费神将录次数', '7');
INSERT INTO `common_vip_privilege` VALUES ('15', '购买体力次数', '16', '最大体力', '44', '重置关卡次数', '7', '招财次数', '19', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '100000', '免费神将录次数', '8');
INSERT INTO `common_vip_privilege` VALUES ('16', '购买体力次数', '17', '最大体力', '44', '重置关卡次数', '8', '招财次数', '20', 'VIP礼包', '1', '扫荡功能', '1', '累积充值', '130000', '免费神将录次数', '8');