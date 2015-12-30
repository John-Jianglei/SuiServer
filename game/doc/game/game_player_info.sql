/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : game

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-30 11:13:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `game_player_info`
-- ----------------------------
DROP TABLE IF EXISTS `game_player_info`;
CREATE TABLE `game_player_info` (
  `uid` varchar(50) NOT NULL COMMENT '玩家id',
  `name` varchar(50) NOT NULL COMMENT '玩家昵称',
  `gender` int(2) DEFAULT '1',
  `camp` int(11) NOT NULL DEFAULT '0' COMMENT '1唐2隋3反王',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '玩家等级',
  `current_exp` int(11) NOT NULL DEFAULT '0',
  `create_time` varchar(50) DEFAULT NULL COMMENT '角色创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `vip_level` int(11) NOT NULL DEFAULT '1',
  `silver` bigint(11) NOT NULL DEFAULT '0',
  `fame` int(11) NOT NULL DEFAULT '0',
  `gold` int(11) NOT NULL DEFAULT '0',
  `current_strength` int(11) NOT NULL DEFAULT '0',
  `combatPower` bigint(11) NOT NULL DEFAULT '0' COMMENT '战力',
  `JingjiPos` int(11) NOT NULL DEFAULT '0' COMMENT '竞技场排名',
  `JingjiTitle` varchar(20) NOT NULL DEFAULT '布衣' COMMENT '竞技场称号',
  `abovePos1` int(11) NOT NULL DEFAULT '0' COMMENT '竞技场能看到的最近排名',
  `abovePos2` int(11) NOT NULL DEFAULT '0',
  `abovePos3` int(11) NOT NULL DEFAULT '0',
  `abovePos4` int(11) NOT NULL DEFAULT '0',
  `abovePos5` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家信息表';

-- ----------------------------
-- Records of game_player_info
-- ----------------------------
INSERT INTO `game_player_info` VALUES ('1-1', '我是皇上', '1', '0', '100', '11', '2015-08-29 18:10:00', '2015-12-29 15:13:46', '11', '219903700', '22', '55', '10', '0', '96', '尚书', '95', '94', '93', '92', '91');
INSERT INTO `game_player_info` VALUES ('1-11', 'johnTest1', '0', '0', '1', '0', '2015-12-03 21:36:10', '2015-12-29 15:13:45', '1', '0', '0', '0', '0', '0', '97', '布衣', '0', '0', '0', '0', '0');
INSERT INTO `game_player_info` VALUES ('1-12', 'johnTest2', '0', '0', '1', '0', '2015-12-03 21:39:21', '2015-12-29 11:35:49', '1', '0', '0', '0', '0', '0', '3', '王', '2', '1', '0', '0', '0');
INSERT INTO `game_player_info` VALUES ('1-15', 'johnTest5', '0', '0', '1', '0', '2015-12-03 21:41:30', '2015-12-29 12:01:25', '1', '0', '0', '0', '0', '0', '4', '王', '3', '2', '1', '0', '0');
INSERT INTO `game_player_info` VALUES ('1-16', 'johnTest6', '0', '0', '1', '0', '2015-12-03 21:45:20', '2015-12-03 21:45:22', '1', '0', '0', '0', '0', '0', '0', '布衣', '0', '0', '0', '0', '0');
INSERT INTO `game_player_info` VALUES ('1-17', 'aleleTest1', '0', '0', '1', '0', '2015-12-29 15:18:18', '2015-12-29 15:18:22', '1', '0', '0', '0', '0', '0', '0', '布衣', '0', '0', '0', '0', '0');
INSERT INTO `game_player_info` VALUES ('1-5', 'john', '1', '0', '1', '0', '2015-09-19 14:10:16', '2015-09-19 22:10:17', '1', '0', '0', '0', '0', '0', '0', '布衣', '0', '0', '0', '0', '0');
INSERT INTO `game_player_info` VALUES ('1-7', 'skull', '1', '0', '10', '0', '2015-09-19 15:11:26', '2015-09-19 23:11:26', '1', '0', '0', '0', '0', '0', '0', '布衣', '0', '0', '0', '0', '0');
INSERT INTO `game_player_info` VALUES ('1-8', 'cool', '1', '0', '1', '0', '2015-09-20 11:29:15', '2015-09-20 11:29:15', '1', '0', '0', '0', '0', '0', '0', '布衣', '0', '0', '0', '0', '0');
INSERT INTO `game_player_info` VALUES ('1-9', 'coolgirl', '0', '0', '1', '0', '2015-09-20 11:39:37', '2015-09-20 11:39:37', '1', '0', '0', '0', '0', '0', '0', '布衣', '0', '0', '0', '0', '0');
