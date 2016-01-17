/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : game

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-01-17 14:27:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `game_npc_piece`
-- ----------------------------
DROP TABLE IF EXISTS `game_npc_piece`;
CREATE TABLE `game_npc_piece` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) NOT NULL COMMENT 'commonä¸­çš„id',
  `uid` varchar(50) NOT NULL COMMENT 'è§’è‰²id',
  `amount` int(11) NOT NULL DEFAULT '1' COMMENT 'ç¢Žç‰‡æ•°é‡',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'æ›´æ–°æ—¶é—´',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='æ­¦å°†ç¢Žç‰‡ä¿¡æ¯è¡¨';

-- ----------------------------
-- Records of game_npc_piece
-- ----------------------------
INSERT INTO `game_npc_piece` VALUES ('1', '503', '1-1', '200', '2016-01-17 11:36:14');
INSERT INTO `game_npc_piece` VALUES ('2', '103', '1-1', '300', '2016-01-17 12:17:14');
