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
-- Table structure for `game_pass_log`
-- ----------------------------
DROP TABLE IF EXISTS `game_pass_log`;
CREATE TABLE `game_pass_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `battleId` int(11) DEFAULT NULL,
  `Count` int(11) DEFAULT NULL COMMENT '可扫荡次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_pass_log
-- ----------------------------
INSERT INTO `game_pass_log` VALUES ('1', '1-1', '2015-12-13', '10111', '10');
INSERT INTO `game_pass_log` VALUES ('2', '1-1', '2015-12-13', '10211', '0');
INSERT INTO `game_pass_log` VALUES ('3', '1-1', '2015-12-13', '10311', '0');
INSERT INTO `game_pass_log` VALUES ('4', '1-1', '2015-12-13', '10411', '0');
INSERT INTO `game_pass_log` VALUES ('5', '1-1', '2015-12-13', '10511', '0');
INSERT INTO `game_pass_log` VALUES ('6', '1-1', '2015-12-13', '10611', '0');
INSERT INTO `game_pass_log` VALUES ('7', '1-1', '2015-12-13', '10711', '0');
INSERT INTO `game_pass_log` VALUES ('8', '1-1', '2015-12-13', '10811', '0');
INSERT INTO `game_pass_log` VALUES ('9', '1-1', '2015-12-13', '10911', '0');
INSERT INTO `game_pass_log` VALUES ('10', '1-1', '2015-12-13', '11011', '0');
INSERT INTO `game_pass_log` VALUES ('11', '1-1', '2015-12-13', '11111', '0');
INSERT INTO `game_pass_log` VALUES ('12', '1-1', '2015-12-13', '11211', '0');
INSERT INTO `game_pass_log` VALUES ('13', '1-1', '2015-12-13', '11311', '0');
INSERT INTO `game_pass_log` VALUES ('14', '1-1', '2015-12-13', '11411', '0');
INSERT INTO `game_pass_log` VALUES ('15', '1-1', '2015-12-13', '11511', '0');
INSERT INTO `game_pass_log` VALUES ('17', '1-1', '2015-12-19', '20123', '20');
INSERT INTO `game_pass_log` VALUES ('18', '1-1', '2015-12-22', '10111', '10');
