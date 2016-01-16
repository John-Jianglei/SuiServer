/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : common

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-01-17 00:13:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_prop_info`
-- ----------------------------
DROP TABLE IF EXISTS `common_prop_info`;
CREATE TABLE `common_prop_info` (
  `comId` int(11) NOT NULL AUTO_INCREMENT,
  `eName` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '道具名',
  `nature` int(11) NOT NULL COMMENT '道具功能',
  `val` int(11) NOT NULL DEFAULT '1' COMMENT '道具功能数值',
  `star` int(11) NOT NULL DEFAULT '1' COMMENT '道具星级',
  `desc` varchar(512) DEFAULT NULL COMMENT '道具描述',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0:无效;1:有效',
  PRIMARY KEY (`comId`)
) ENGINE=InnoDB AUTO_INCREMENT=504 DEFAULT CHARSET=utf8 COMMENT='道具信息表';

-- ----------------------------
-- Records of common_prop_info
-- ----------------------------
INSERT INTO `common_prop_info` VALUES ('1', 'smallExpCard', '小经验书', '200', '30', '2', '增加经验', '2015-11-09 21:33:15', '1');
INSERT INTO `common_prop_info` VALUES ('2', 'yijunExpCard', '义军经验书', '200', '50', '2', '增加经验', '2015-11-09 21:53:49', '1');
INSERT INTO `common_prop_info` VALUES ('3', 'tangExpCard', '大唐经验书', '200', '100', '2', '增加经验', '2015-11-14 21:57:07', '1');
INSERT INTO `common_prop_info` VALUES ('4', 'suiExpCard', '大隋经验书', '200', '200', '2', '增加经验', '2015-11-14 21:57:40', '1');
INSERT INTO `common_prop_info` VALUES ('5', 'seniorExpCard', '高级经验书', '200', '1000', '2', '增加经验', '2015-11-14 21:58:22', '1');
INSERT INTO `common_prop_info` VALUES ('6', 'superExpCard', '至尊经验书', '200', '10000', '2', '增加经验', '2015-11-14 21:58:37', '1');
INSERT INTO `common_prop_info` VALUES ('7', 'jinjiedan', '进阶丹', '100', '0', '4', '进阶材料', '2016-01-10 09:45:40', '1');
INSERT INTO `common_prop_info` VALUES ('8', 'fivecolorstone', '五彩石', '100', '0', '3', '进阶材料', '2015-11-18 16:23:59', '1');
INSERT INTO `common_prop_info` VALUES ('9', 'tigertally', '虎符', '100', '0', '3', '进阶材料', '2016-01-10 09:53:35', '1');
INSERT INTO `common_prop_info` VALUES ('10', 'eviltally', '地煞令', '100', '0', '3', '进阶材料', '2015-11-18 16:24:07', '1');
INSERT INTO `common_prop_info` VALUES ('11', 'ploughtally', '天罡令', '100', '0', '4', '进阶材料', '2016-01-10 09:45:37', '1');
INSERT INTO `common_prop_info` VALUES ('12', 'sttally', '圣将令', '100', '0', '5', '进阶材料', '2016-01-10 09:44:19', '1');
INSERT INTO `common_prop_info` VALUES ('13', 'suitangmedal', '隋唐军功章', '100', '0', '5', '进阶材料', '2016-01-10 09:46:20', '1');
INSERT INTO `common_prop_info` VALUES ('200', '', '进化石', '101', '100', '4', '武器进阶材料', '2016-01-16 15:14:54', '1');
INSERT INTO `common_prop_info` VALUES ('201', '', '至尊虎魄', '101', '100', '4', '武器进阶材料', '2016-01-16 15:14:56', '1');
INSERT INTO `common_prop_info` VALUES ('202', '', '精炼玄铁', '101', '100', '3', '武器进阶材料', '2016-01-16 15:14:58', '1');
INSERT INTO `common_prop_info` VALUES ('203', '', '天外精铜', '101', '100', '3', '武器进阶材料', '2016-01-16 15:15:00', '1');
INSERT INTO `common_prop_info` VALUES ('204', '', '不朽马蹬', '101', '100', '3', '武器进阶材料', '2016-01-16 15:15:02', '1');
INSERT INTO `common_prop_info` VALUES ('205', '', '精制皮革', '101', '100', '3', '武器进阶材料', '2016-01-16 15:15:12', '1');
