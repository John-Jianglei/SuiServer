/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : game

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-26 11:43:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `game_jingji_amount`
-- ----------------------------
DROP TABLE IF EXISTS `game_jingji_amount`;
CREATE TABLE `game_jingji_amount` (
  `int` int(11) NOT NULL,
  `amount` int(11) NOT NULL COMMENT '当前进入竞技场玩家数量，包括机器人',
  PRIMARY KEY (`int`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_jingji_amount
-- ----------------------------
INSERT INTO `game_jingji_amount` VALUES ('1', '0');
