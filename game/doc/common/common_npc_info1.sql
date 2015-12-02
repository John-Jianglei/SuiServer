/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : common

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-02 18:54:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_npc_info`
-- ----------------------------
DROP TABLE IF EXISTS `common_npc_info`;
CREATE TABLE `common_npc_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '武将名',
  `gender` int(2) NOT NULL COMMENT '武将性别:0-女，1-男',
  `star` int(11) NOT NULL DEFAULT '1' COMMENT '武将星级',
  `category` int(2) NOT NULL DEFAULT '0' COMMENT '武将类型：1-君主；2-猛将；3-元帅；4-军师；5-智将；',
  `camp` int(2) NOT NULL DEFAULT '0' COMMENT '武将阵营: 1-唐;2-隋;3-反王;',
  `health` int(11) NOT NULL DEFAULT '1' COMMENT '武将初始生命值',
  `attack` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始攻击力',
  `hujia` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始护甲',
  `pojia` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始破甲',
  `fachuan` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始法穿',
  `fakang` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始法抗',
  `baoji` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始暴击',
  `renxing` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始韧性',
  `mingzhong` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始命中',
  `shanbi` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始闪避',
  `xixue` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始吸血',
  `fantan` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始反弹',
  `jiyun` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始击晕',
  `kangyun` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始抗晕',
  `gedang` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始格挡',
  `gedangPoss` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始格挡概率',
  `reduce` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始伤害减小',
  `talent` varchar(16) DEFAULT NULL COMMENT '武将初始天赋名',
  `talentVal` int(11) NOT NULL DEFAULT '0' COMMENT '武将初始天赋值',
  `attackStep` int(11) NOT NULL DEFAULT '0' COMMENT '攻击力升级step',
  `healthStep` int(11) NOT NULL DEFAULT '0' COMMENT '生命升级Step',
  `levelupRate` int(11) NOT NULL DEFAULT '0' COMMENT '进阶材料需求系数',
  `pieces` int(11) NOT NULL DEFAULT '0' COMMENT '组合需要碎片数',
  `maxPieces` int(11) NOT NULL DEFAULT '0' COMMENT '最大可用公共碎片数',
  `pieceId` int(11) NOT NULL,
  `skill3` int(11) NOT NULL DEFAULT '0' COMMENT 'skill 1',
  `skill2` int(11) NOT NULL DEFAULT '0',
  `desc` varchar(512) DEFAULT NULL COMMENT '武将列传',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0:无效;1:有效',
  `skill1` int(11) NOT NULL DEFAULT '0',
  `skill4` int(11) NOT NULL DEFAULT '0' COMMENT '技能4',
  `skill5` int(11) NOT NULL DEFAULT '0' COMMENT '技能5',
  `skill6` int(11) NOT NULL DEFAULT '0' COMMENT '技能6',
  `skill7` int(11) NOT NULL DEFAULT '0' COMMENT '技能7',
  `skill8` int(11) NOT NULL DEFAULT '0' COMMENT '技能8',
  `skill9` int(11) NOT NULL DEFAULT '0' COMMENT '技能9',
  `skill10` int(11) NOT NULL DEFAULT '0' COMMENT '技能10',
  `skill11` int(11) NOT NULL DEFAULT '0' COMMENT '技能11',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=637 DEFAULT CHARSET=utf8 COMMENT='武将信息表';