/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : game

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-01-10 17:46:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `game_npc_info`
-- ----------------------------
DROP TABLE IF EXISTS `game_npc_info`;
CREATE TABLE `game_npc_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) NOT NULL COMMENT '武将id',
  `uid` varchar(50) NOT NULL COMMENT '角色id',
  `pinjie` int(11) NOT NULL DEFAULT '1' COMMENT '武将品阶',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '武将等级',
  `experience` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前经验',
  `position` int(2) NOT NULL DEFAULT '0' COMMENT '武将队列中的位置',
  `health` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前生命值',
  `attack` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前攻击力',
  `hujia` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前护甲',
  `pojia` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前破甲',
  `fachuan` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前法穿',
  `fakang` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前法抗',
  `baoji` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前暴击',
  `renxing` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前韧性',
  `mingzhong` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前命中',
  `shanbi` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前闪避',
  `xixue` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前吸血',
  `fantan` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前反弹',
  `jiyun` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前击晕',
  `kangyun` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前抗晕',
  `gedang` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前格挡',
  `gedangPoss` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前格挡概率',
  `reduce` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前伤害减小',
  `healthBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础生命值',
  `attackBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础攻击力',
  `hujiaBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础护甲',
  `pojiaBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础破甲',
  `fachuanBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础法穿',
  `fakangBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础法抗',
  `baojiBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础暴击',
  `renxingBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础韧性',
  `mingzhongBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础命中',
  `shanbiBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础闪避',
  `xixueBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础吸血',
  `fantanBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础反弹',
  `jiyunBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础击晕',
  `kangyunBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础抗晕',
  `gedangBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础格挡',
  `gedangPossBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础格挡概率',
  `reduceBase` int(11) NOT NULL DEFAULT '0' COMMENT '武将当前基础伤害减小',
  `skill1` int(11) NOT NULL DEFAULT '0' COMMENT '技能1',
  `skill2` int(11) NOT NULL DEFAULT '0' COMMENT '技能2',
  `skill3` int(11) NOT NULL DEFAULT '0' COMMENT '技能3',
  `skill4` int(11) NOT NULL DEFAULT '0' COMMENT '技能4',
  `skill5` int(11) NOT NULL DEFAULT '0' COMMENT '技能5',
  `skill6` int(11) NOT NULL DEFAULT '0' COMMENT '技能6',
  `skill7` int(11) NOT NULL DEFAULT '0' COMMENT '技能7',
  `skill8` int(11) NOT NULL DEFAULT '0' COMMENT '技能8',
  `skill9` int(11) NOT NULL DEFAULT '0' COMMENT '技能9',
  `skill10` int(11) NOT NULL DEFAULT '0' COMMENT '技能10',
  `skill11` int(11) NOT NULL DEFAULT '0' COMMENT '技能11',
  `yuanfen1` int(11) NOT NULL DEFAULT '0' COMMENT '缘份1',
  `yuanfen2` int(11) NOT NULL DEFAULT '0' COMMENT '缘份2',
  `yuanfen3` int(11) NOT NULL DEFAULT '0' COMMENT '缘份3',
  `yuanfen4` int(11) NOT NULL DEFAULT '0' COMMENT '缘份4',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='武将信息表';

-- ----------------------------
-- Records of game_npc_info
-- ----------------------------
INSERT INTO `game_npc_info` VALUES ('1', '201', '1-11', '1', '1', '0', '0', '2985', '358', '0', '5', '5', '0', '5', '0', '5', '0', '15', '0', '0', '0', '0', '0', '0', '1990', '358', '0', '0', '0', '0', '0', '0', '0', '0', '15', '0', '0', '0', '0', '0', '0', '-1', '-13', '-51', '-67', '-69', '-71', '-75', '-76', '-77', '-78', '79', '-497', '498', '-499', '-500');
INSERT INTO `game_npc_info` VALUES ('4', '101', '1-11', '1', '1', '0', '3', '1990', '358', '0', '5', '5', '0', '5', '0', '5', '0', '0', '0', '25', '0', '0', '0', '0', '1990', '358', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '25', '0', '0', '0', '0', '-1', '-12', '-51', '-67', '-69', '-71', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
INSERT INTO `game_npc_info` VALUES ('5', '101', '1-1', '1', '1', '0', '3', '1990', '358', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '25', '0', '0', '0', '0', '1990', '358', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '25', '0', '0', '0', '0', '-1', '-12', '-51', '-67', '-69', '-71', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
INSERT INTO `game_npc_info` VALUES ('6', '101', '1-1', '1', '1', '0', '0', '1990', '358', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '25', '0', '0', '0', '0', '1990', '358', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '25', '0', '0', '0', '0', '-1', '-12', '-51', '-67', '-69', '-71', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
INSERT INTO `game_npc_info` VALUES ('7', '102', '1-1', '1', '1', '0', '1', '1860', '328', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '25', '0', '0', '0', '0', '1860', '328', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '25', '0', '0', '0', '0', '-1', '-12', '-51', '-67', '-69', '-71', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
INSERT INTO `game_npc_info` VALUES ('8', '103', '1-1', '1', '1', '0', '5', '2036', '425', '0', '0', '0', '0', '25', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2036', '425', '0', '0', '0', '0', '25', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '-2', '-6', '-52', '-67', '-68', '-72', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
INSERT INTO `game_npc_info` VALUES ('9', '203', '1-1', '1', '1', '0', '4', '1990', '358', '0', '0', '0', '0', '25', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1990', '358', '0', '0', '0', '0', '25', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '-2', '-7', '-53', '-67', '-68', '-72', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
INSERT INTO `game_npc_info` VALUES ('10', '201', '1-1', '1', '5', '70', '2', '2985', '358', '0', '0', '0', '0', '0', '0', '0', '0', '15', '0', '0', '0', '0', '0', '0', '2383', '449', '0', '0', '0', '0', '0', '0', '0', '0', '15', '0', '0', '0', '0', '0', '0', '-1', '-13', '-51', '-67', '-69', '-71', '-75', '-76', '-77', '-78', '-79', '-497', '498', '-499', '-500');
INSERT INTO `game_npc_info` VALUES ('26', '102', '1-18', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1860', '328', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '25', '0', '0', '0', '0', '-1', '-12', '-51', '-67', '-69', '-71', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
INSERT INTO `game_npc_info` VALUES ('27', '122', '1-18', '1', '1', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '608', '108', '0', '0', '0', '40', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '-2', '-6', '-64', '-67', '-68', '-72', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
INSERT INTO `game_npc_info` VALUES ('28', '129', '1-18', '1', '1', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '528', '98', '0', '0', '0', '0', '0', '0', '0', '15', '0', '0', '0', '0', '0', '0', '0', '-5', '-21', '-64', '-67', '-69', '-70', '-75', '-76', '-77', '-78', '-79', '0', '0', '0', '0');
