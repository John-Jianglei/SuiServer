/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-11-17 20:40:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_rpc_jinjie_material`
-- ----------------------------
DROP TABLE IF EXISTS `common_rpc_jinjie_material`;
CREATE TABLE `common_rpc_jinjie_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` int(2) DEFAULT NULL,
  `nextpinjie` int(2) DEFAULT NULL,
  `silver` bigint(20) DEFAULT NULL,
  `jinjiedan` int(11) DEFAULT NULL,
  `fivecolorstone` int(11) DEFAULT NULL,
  `tigertally` int(11) DEFAULT NULL,
  `eviltally` int(11) DEFAULT NULL,
  `ploughtally` int(11) DEFAULT NULL,
  `sttally` int(11) DEFAULT NULL,
  `suitangmedal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8  COMMENT='武将信息表';

-- ----------------------------
-- Records of common_rpc_jinjie_material
-- ----------------------------
INSERT INTO `common_rpc_jinjie_material` VALUES ('1', '0', '1', '20000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('2', '0', '2', '24000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('3', '0', '3', '28800', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('4', '0', '4', '34560', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('5', '0', '5', '41470', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('6', '0', '6', '49760', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('7', '0', '7', '59720', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('8', '0', '8', '71660', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('9', '0', '9', '85990', '0', '0', '0', '0', '0', '0', '8');
INSERT INTO `common_rpc_jinjie_material` VALUES ('10', '1', '0', '103100', '40', '80', '24', '16', '0', '0', '8');
INSERT INTO `common_rpc_jinjie_material` VALUES ('11', '1', '1', '123000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('12', '1', '2', '148600', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('13', '1', '3', '178300', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('14', '1', '4', '213900', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('15', '1', '5', '256700', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('16', '1', '6', '308100', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('17', '1', '7', '369700', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('18', '1', '8', '443700', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('19', '1', '9', '532400', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('20', '2', '0', '638900', '80', '160', '48', '0', '16', '0', '16');
INSERT INTO `common_rpc_jinjie_material` VALUES ('21', '2', '1', '780000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('22', '2', '2', '9200000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('23', '2', '3', '11040000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('24', '2', '4', '13200000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('25', '2', '5', '15890000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('26', '2', '6', '19000000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('27', '2', '7', '22890000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('28', '2', '8', '27470000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('29', '2', '9', '32900000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('30', '2', '0', '39560000', '120', '240', '64', '0', '0', '16', '16');
INSERT INTO `common_rpc_jinjie_material` VALUES ('31', '3', '1', '44880000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('32', '3', '2', '45150000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('33', '3', '3', '45480000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('34', '3', '4', '45870000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('35', '3', '5', '46340000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('36', '3', '6', '46900000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('37', '3', '7', '47580000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('38', '3', '8', '48390000', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_rpc_jinjie_material` VALUES ('39', '3', '9', '49370000', '0', '0', '0', '0', '0', '0', '0');
