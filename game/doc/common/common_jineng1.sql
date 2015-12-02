/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-02 12:15:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_jineng`
-- ----------------------------
DROP TABLE IF EXISTS `common_jineng`;
CREATE TABLE `common_jineng` (
  `id` int(11) NOT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `jineng_level` int(11) DEFAULT NULL,
  `need_pinjie` int(11) DEFAULT NULL,
  `init_nuqi` int(11) DEFAULT NULL,
  `need_nuqi` int(11) DEFAULT NULL,
  `consum_nuqi` int(11) DEFAULT NULL,
  `remain_nuqi` int(11) DEFAULT NULL,
  `attack_num` int(11) DEFAULT NULL,
  `mubiao_pos` int(11) DEFAULT NULL,
  `damage_type` int(11) DEFAULT NULL,
  `damage_min` int(11) DEFAULT NULL,
  `damage_max` int(11) DEFAULT NULL,
  `add_health_type` int(11) DEFAULT NULL,
  `add_health` int(11) DEFAULT NULL,
  `shuxing_type` int(11) DEFAULT NULL,
  `add_damage` int(11) DEFAULT NULL,
  `add_tianming` int(11) DEFAULT NULL,
  `is_relive` int(11) DEFAULT NULL,
  `is_wudi` int(11) DEFAULT NULL,
  `add_pojia` int(11) DEFAULT NULL,
  `add_fachuan` int(11) DEFAULT NULL,
  `add_baoji` int(11) DEFAULT NULL,
  `add_mingzhong` int(11) DEFAULT NULL,
  `add_shanbi` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

