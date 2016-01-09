/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : common

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-23 17:56:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_buyStrength`
-- ----------------------------
DROP TABLE IF EXISTS `common_buyStrength`;
CREATE TABLE `common_buyStrength` (
  `seq` int(11) NOT NULL COMMENT '第n次购买',
  `gold` int(11) NOT NULL DEFAULT '300' COMMENT '需要花费元宝',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='购买体力花费表';

-- ----------------------------
-- Records of game_pass_log
-- ----------------------------
INSERT INTO `common_buyStrength` VALUES ('1', '50');
INSERT INTO `common_buyStrength` VALUES ('2', '100');
INSERT INTO `common_buyStrength` VALUES ('3', '150');
INSERT INTO `common_buyStrength` VALUES ('4', '200');
INSERT INTO `common_buyStrength` VALUES ('5', '200');
INSERT INTO `common_buyStrength` VALUES ('6', '300');
INSERT INTO `common_buyStrength` VALUES ('7', '300');
INSERT INTO `common_buyStrength` VALUES ('8', '300');
INSERT INTO `common_buyStrength` VALUES ('9', '300');
INSERT INTO `common_buyStrength` VALUES ('10', '300');
INSERT INTO `common_buyStrength` VALUES ('11', '300');
INSERT INTO `common_buyStrength` VALUES ('12', '300');
INSERT INTO `common_buyStrength` VALUES ('13', '300');
INSERT INTO `common_buyStrength` VALUES ('14', '300');
INSERT INTO `common_buyStrength` VALUES ('15', '300');
INSERT INTO `common_buyStrength` VALUES ('16', '300');
INSERT INTO `common_buyStrength` VALUES ('17', '300');