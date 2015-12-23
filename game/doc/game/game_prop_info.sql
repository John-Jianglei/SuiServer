/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : game

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-11-21 10:10:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `game_prop_info`
-- ----------------------------
DROP TABLE IF EXISTS `game_prop_info`;
CREATE TABLE `game_prop_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) NOT NULL COMMENT '道具common的id',
  `uid` varchar(50) NOT NULL COMMENT '角色id',
  `npcId` int(11) NOT NULL DEFAULT '-1' COMMENT '装备该道具的武将id, -1代表null',
  `position` int(3) NOT NULL DEFAULT '0' COMMENT '道具摆放位置，只有当npcId不为空时有效',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '道具数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='道具信息表';

-- ----------------------------
-- Records of game_prop_info
-- ----------------------------
INSERT INTO `game_prop_info` VALUES ('1', '2', '1-1', '-1', '0', '39');
INSERT INTO `game_prop_info` VALUES ('3', '2', '1-7', '7', '0', '55');
INSERT INTO `game_prop_info` VALUES ('4', '1', '1-5', '-1', '0', '49');
