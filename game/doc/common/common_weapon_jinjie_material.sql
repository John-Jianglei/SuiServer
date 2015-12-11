/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-10 22:54:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_weapon_jinjie_material`
-- ----------------------------
DROP TABLE IF EXISTS `common_weapon_jinjie_material`;
CREATE TABLE `common_weapon_jinjie_material` (
  `id` int(11) NOT NULL,
  `star` int(11) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `isGaoji` int(1) DEFAULT NULL,
  `nextPinjie` int(11) DEFAULT NULL,
  `sliver` bigint(20) DEFAULT NULL,
  `jinhuaStone` int(11) DEFAULT NULL,
  `gaojiAmber` int(11) DEFAULT NULL,
  `refinedXuantie` int(11) DEFAULT NULL,
  `fineCopper` int(11) DEFAULT NULL,
  `immortalMadeng` int(11) DEFAULT NULL,
  `refineedPige` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common_weapon_jinjie_material
-- ----------------------------
INSERT INTO `common_weapon_jinjie_material` VALUES ('1', '5', '0', '0', '1', '360000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('2', '5', '0', '0', '2', '5400000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('3', '5', '0', '0', '3', '8000000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('4', '5', '0', '0', '4', '1080000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('5', '5', '0', '0', '5', '1400000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('6', '5', '0', '0', '6', '1760000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('7', '5', '0', '0', '7', '2160000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('8', '5', '0', '0', '8', '2600000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('9', '5', '0', '0', '9', '3300000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('10', '4', '0', '0', '1', '9000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('11', '4', '0', '0', '2', '18000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('12', '4', '0', '0', '3', '29000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('13', '4', '0', '0', '4', '42000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('14', '4', '0', '0', '5', '57000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('15', '4', '0', '0', '6', '75000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('16', '4', '0', '0', '7', '95000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('17', '4', '0', '0', '8', '117000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('18', '4', '0', '0', '9', '141000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('19', '4', '1', '1', '0', '180000', '15', '20', '20', '20', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('20', '4', '2', '1', '0', '180000', '15', '20', '0', '20', '0', '20');
INSERT INTO `common_weapon_jinjie_material` VALUES ('21', '4', '3', '1', '0', '180000', '15', '20', '0', '0', '20', '20');
INSERT INTO `common_weapon_jinjie_material` VALUES ('22', '4', '0', '1', '1', '225000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('23', '4', '0', '1', '2', '275000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('24', '4', '0', '1', '3', '330000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('25', '4', '0', '1', '4', '385000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('26', '4', '0', '1', '5', '445000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('27', '4', '0', '1', '6', '510000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('28', '4', '0', '1', '7', '580000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('29', '4', '0', '1', '8', '655000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('30', '4', '0', '1', '9', '735000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('31', '3', '0', '0', '1', '6000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('32', '3', '0', '0', '2', '12000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('33', '3', '0', '0', '3', '20000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('34', '3', '0', '0', '4', '30000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('35', '3', '0', '0', '5', '42000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('36', '3', '0', '0', '6', '56000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('37', '3', '0', '0', '7', '72000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('38', '3', '0', '0', '8', '90000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('39', '3', '0', '0', '9', '110000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('40', '3', '1', '1', '0', '140000', '20', '25', '20', '20', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('41', '3', '2', '2', '0', '140000', '20', '25', '0', '20', '0', '20');
INSERT INTO `common_weapon_jinjie_material` VALUES ('42', '3', '3', '3', '0', '140000', '20', '25', '0', '0', '20', '20');
INSERT INTO `common_weapon_jinjie_material` VALUES ('44', '3', '0', '1', '1', '162000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('45', '3', '0', '1', '2', '186000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('46', '3', '0', '1', '3', '212000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('47', '3', '0', '1', '4', '240000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('48', '3', '0', '1', '5', '270000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('49', '3', '0', '1', '6', '302000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('50', '3', '0', '1', '7', '336000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('51', '3', '0', '1', '8', '372000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('52', '3', '0', '1', '9', '412000', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('53', '2', '0', '0', '1', '300', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('54', '2', '0', '0', '2', '600', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('55', '2', '0', '0', '3', '900', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('56', '2', '0', '0', '4', '1300', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('57', '2', '0', '0', '5', '1700', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('58', '2', '0', '0', '6', '2200', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('59', '2', '0', '0', '7', '2800', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('60', '2', '0', '0', '8', '3600', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('61', '2', '0', '0', '9', '4600', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('62', '1', '0', '0', '1', '100', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('63', '1', '0', '0', '2', '200', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('64', '1', '0', '0', '3', '400', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('65', '1', '0', '0', '4', '700', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('66', '1', '0', '0', '5', '1100', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('67', '1', '0', '0', '6', '1600', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('68', '1', '0', '0', '7', '2200', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('69', '1', '0', '0', '8', '2900', '0', '0', '0', '0', '0', '0');
INSERT INTO `common_weapon_jinjie_material` VALUES ('70', '1', '0', '0', '9', '3700', '0', '0', '0', '0', '0', '0');
