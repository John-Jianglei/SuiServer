/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : game

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-23 17:56:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `game_buyStrength_log`
-- ----------------------------
DROP TABLE IF EXISTS `game_buyStrength_log`;
CREATE TABLE `game_buyStrength_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `Count` int(11) DEFAULT NULL COMMENT '当天购买体力次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='购买体力日志表';

-- ----------------------------
-- Records of game_buyStrength_log
-- ----------------------------
INSERT INTO `game_buyStrength_log` VALUES ('1', '1-1', '2016-1-9', '0');
