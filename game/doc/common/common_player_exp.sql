/*
Navicat MySQL Data Transfer

Source Server         : 120.27.49.196
Source Server Version : 50173
Source Host           : 120.27.49.196:3306
Source Database       : common

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-01-03 16:07:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `common_player_exp`
-- ----------------------------
DROP TABLE IF EXISTS `common_player_exp`;
CREATE TABLE `common_player_exp` (
  `level` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `attackAdd` int(11) NOT NULL COMMENT '增加基础攻击力的百分比，要除以100',
  `healthAdd` int(11) NOT NULL COMMENT '增加基础生命值的百分比，要除以100',
  PRIMARY KEY (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common_player_exp
-- ----------------------------
INSERT INTO `common_player_exp` VALUES ('1', '6', '1', '1');
INSERT INTO `common_player_exp` VALUES ('2', '6', '2', '2');
INSERT INTO `common_player_exp` VALUES ('3', '24', '3', '3');
INSERT INTO `common_player_exp` VALUES ('4', '59', '4', '4');
INSERT INTO `common_player_exp` VALUES ('5', '112', '5', '5');
INSERT INTO `common_player_exp` VALUES ('6', '188', '6', '6');
INSERT INTO `common_player_exp` VALUES ('7', '288', '7', '7');
INSERT INTO `common_player_exp` VALUES ('8', '417', '8', '8');
INSERT INTO `common_player_exp` VALUES ('9', '576', '9', '9');
INSERT INTO `common_player_exp` VALUES ('10', '770', '10', '10');
INSERT INTO `common_player_exp` VALUES ('11', '1000', '11', '11');
INSERT INTO `common_player_exp` VALUES ('12', '1271', '12', '12');
INSERT INTO `common_player_exp` VALUES ('13', '1584', '13', '13');
INSERT INTO `common_player_exp` VALUES ('14', '1944', '14', '14');
INSERT INTO `common_player_exp` VALUES ('15', '2352', '15', '15');
INSERT INTO `common_player_exp` VALUES ('16', '2813', '16', '16');
INSERT INTO `common_player_exp` VALUES ('17', '3328', '17', '17');
INSERT INTO `common_player_exp` VALUES ('18', '3902', '18', '18');
INSERT INTO `common_player_exp` VALUES ('19', '4536', '19', '19');
INSERT INTO `common_player_exp` VALUES ('20', '5235', '20', '20');
INSERT INTO `common_player_exp` VALUES ('21', '6000', '21', '21');
INSERT INTO `common_player_exp` VALUES ('22', '6836', '22', '22');
INSERT INTO `common_player_exp` VALUES ('23', '7744', '23', '23');
INSERT INTO `common_player_exp` VALUES ('24', '8729', '24', '24');
INSERT INTO `common_player_exp` VALUES ('25', '9792', '25', '25');
INSERT INTO `common_player_exp` VALUES ('26', '10938', '26', '26');
INSERT INTO `common_player_exp` VALUES ('27', '12168', '27', '27');
INSERT INTO `common_player_exp` VALUES ('28', '13487', '28', '28');
INSERT INTO `common_player_exp` VALUES ('29', '14896', '29', '29');
INSERT INTO `common_player_exp` VALUES ('30', '16400', '30', '30');
INSERT INTO `common_player_exp` VALUES ('31', '18000', '31', '31');
INSERT INTO `common_player_exp` VALUES ('32', '19701', '32', '32');
INSERT INTO `common_player_exp` VALUES ('33', '21504', '33', '33');
INSERT INTO `common_player_exp` VALUES ('34', '23414', '34', '34');
INSERT INTO `common_player_exp` VALUES ('35', '25432', '35', '35');
INSERT INTO `common_player_exp` VALUES ('36', '27563', '36', '36');
INSERT INTO `common_player_exp` VALUES ('37', '29808', '37', '37');
INSERT INTO `common_player_exp` VALUES ('38', '32172', '38', '38');
INSERT INTO `common_player_exp` VALUES ('39', '34656', '39', '39');
INSERT INTO `common_player_exp` VALUES ('40', '37265', '40', '40');
INSERT INTO `common_player_exp` VALUES ('41', '40000', '41', '41');
INSERT INTO `common_player_exp` VALUES ('42', '42866', '42', '42');
INSERT INTO `common_player_exp` VALUES ('43', '45864', '43', '43');
INSERT INTO `common_player_exp` VALUES ('44', '48999', '44', '44');
INSERT INTO `common_player_exp` VALUES ('45', '52272', '45', '45');
INSERT INTO `common_player_exp` VALUES ('46', '55688', '46', '46');
INSERT INTO `common_player_exp` VALUES ('47', '59248', '47', '47');
INSERT INTO `common_player_exp` VALUES ('48', '62957', '48', '48');
INSERT INTO `common_player_exp` VALUES ('49', '66816', '49', '49');
INSERT INTO `common_player_exp` VALUES ('50', '70830', '50', '50');
INSERT INTO `common_player_exp` VALUES ('51', '75000', '51', '51');
INSERT INTO `common_player_exp` VALUES ('52', '79331', '52', '52');
INSERT INTO `common_player_exp` VALUES ('53', '83824', '53', '53');
INSERT INTO `common_player_exp` VALUES ('54', '88484', '54', '54');
INSERT INTO `common_player_exp` VALUES ('55', '93312', '55', '55');
INSERT INTO `common_player_exp` VALUES ('56', '98313', '56', '56');
INSERT INTO `common_player_exp` VALUES ('57', '103488', '57', '57');
INSERT INTO `common_player_exp` VALUES ('58', '108842', '58', '58');
INSERT INTO `common_player_exp` VALUES ('59', '114376', '59', '59');
INSERT INTO `common_player_exp` VALUES ('60', '120095', '60', '60');
INSERT INTO `common_player_exp` VALUES ('61', '126000', '61', '61');
INSERT INTO `common_player_exp` VALUES ('62', '132096', '62', '62');
INSERT INTO `common_player_exp` VALUES ('63', '138384', '63', '63');
INSERT INTO `common_player_exp` VALUES ('64', '144869', '64', '64');
INSERT INTO `common_player_exp` VALUES ('65', '151552', '65', '65');
INSERT INTO `common_player_exp` VALUES ('66', '158438', '66', '66');
INSERT INTO `common_player_exp` VALUES ('67', '165528', '67', '67');
INSERT INTO `common_player_exp` VALUES ('68', '172827', '68', '68');
INSERT INTO `common_player_exp` VALUES ('69', '180336', '69', '69');
INSERT INTO `common_player_exp` VALUES ('70', '188060', '70', '70');
INSERT INTO `common_player_exp` VALUES ('71', '196000', '71', '71');
INSERT INTO `common_player_exp` VALUES ('72', '204161', '72', '72');
INSERT INTO `common_player_exp` VALUES ('73', '212544', '73', '73');
INSERT INTO `common_player_exp` VALUES ('74', '221154', '74', '74');
INSERT INTO `common_player_exp` VALUES ('75', '229992', '75', '75');
INSERT INTO `common_player_exp` VALUES ('76', '239063', '76', '76');
INSERT INTO `common_player_exp` VALUES ('77', '248368', '77', '77');
INSERT INTO `common_player_exp` VALUES ('78', '257912', '78', '78');
INSERT INTO `common_player_exp` VALUES ('79', '267696', '79', '79');
INSERT INTO `common_player_exp` VALUES ('80', '277725', '80', '80');
INSERT INTO `common_player_exp` VALUES ('81', '288000', '81', '81');
INSERT INTO `common_player_exp` VALUES ('82', '298526', '82', '82');
INSERT INTO `common_player_exp` VALUES ('83', '309304', '83', '83');
INSERT INTO `common_player_exp` VALUES ('84', '320339', '84', '84');
INSERT INTO `common_player_exp` VALUES ('85', '331632', '85', '85');
INSERT INTO `common_player_exp` VALUES ('86', '343188', '86', '86');
INSERT INTO `common_player_exp` VALUES ('87', '355008', '87', '87');
INSERT INTO `common_player_exp` VALUES ('88', '367097', '88', '88');
INSERT INTO `common_player_exp` VALUES ('89', '379456', '89', '89');
INSERT INTO `common_player_exp` VALUES ('90', '392090', '90', '90');
INSERT INTO `common_player_exp` VALUES ('91', '405000', '91', '91');
INSERT INTO `common_player_exp` VALUES ('92', '418191', '92', '92');
INSERT INTO `common_player_exp` VALUES ('93', '431664', '93', '93');
INSERT INTO `common_player_exp` VALUES ('94', '445424', '94', '94');
INSERT INTO `common_player_exp` VALUES ('95', '459472', '95', '95');
INSERT INTO `common_player_exp` VALUES ('96', '473813', '96', '96');
INSERT INTO `common_player_exp` VALUES ('97', '488448', '97', '97');
INSERT INTO `common_player_exp` VALUES ('98', '503382', '98', '98');
INSERT INTO `common_player_exp` VALUES ('99', '518616', '99', '99');
INSERT INTO `common_player_exp` VALUES ('100', '534155', '100', '100');
INSERT INTO `common_player_exp` VALUES ('101', '550000', '101', '101');
INSERT INTO `common_player_exp` VALUES ('102', '566156', '102', '102');
INSERT INTO `common_player_exp` VALUES ('103', '582624', '103', '103');
INSERT INTO `common_player_exp` VALUES ('104', '599409', '104', '104');
INSERT INTO `common_player_exp` VALUES ('105', '616512', '105', '105');
INSERT INTO `common_player_exp` VALUES ('106', '633938', '106', '106');
INSERT INTO `common_player_exp` VALUES ('107', '651688', '107', '107');
INSERT INTO `common_player_exp` VALUES ('108', '669767', '108', '108');
INSERT INTO `common_player_exp` VALUES ('109', '688176', '109', '109');
INSERT INTO `common_player_exp` VALUES ('110', '706920', '110', '110');
INSERT INTO `common_player_exp` VALUES ('111', '726000', '111', '111');
INSERT INTO `common_player_exp` VALUES ('112', '745421', '112', '112');
INSERT INTO `common_player_exp` VALUES ('113', '765184', '113', '113');
INSERT INTO `common_player_exp` VALUES ('114', '785294', '114', '114');
INSERT INTO `common_player_exp` VALUES ('115', '805752', '115', '115');
INSERT INTO `common_player_exp` VALUES ('116', '826563', '116', '116');
INSERT INTO `common_player_exp` VALUES ('117', '847728', '117', '117');
INSERT INTO `common_player_exp` VALUES ('118', '869252', '118', '118');
INSERT INTO `common_player_exp` VALUES ('119', '891136', '119', '119');
INSERT INTO `common_player_exp` VALUES ('120', '913385', '120', '120');
INSERT INTO `common_player_exp` VALUES ('121', '936000', '121', '121');
INSERT INTO `common_player_exp` VALUES ('122', '958986', '122', '122');
INSERT INTO `common_player_exp` VALUES ('123', '982344', '123', '123');
INSERT INTO `common_player_exp` VALUES ('124', '1006079', '124', '124');
INSERT INTO `common_player_exp` VALUES ('125', '1030192', '125', '125');
INSERT INTO `common_player_exp` VALUES ('126', '1054688', '126', '126');
INSERT INTO `common_player_exp` VALUES ('127', '1079568', '127', '127');
INSERT INTO `common_player_exp` VALUES ('128', '1104837', '128', '128');
INSERT INTO `common_player_exp` VALUES ('129', '1130496', '129', '129');
INSERT INTO `common_player_exp` VALUES ('130', '1156550', '130', '130');
INSERT INTO `common_player_exp` VALUES ('131', '1183000', '131', '131');
INSERT INTO `common_player_exp` VALUES ('132', '1209851', '132', '132');
INSERT INTO `common_player_exp` VALUES ('133', '1237104', '133', '133');
INSERT INTO `common_player_exp` VALUES ('134', '1264764', '134', '134');
INSERT INTO `common_player_exp` VALUES ('135', '1292832', '135', '135');
INSERT INTO `common_player_exp` VALUES ('136', '1321313', '136', '136');
INSERT INTO `common_player_exp` VALUES ('137', '1350208', '137', '137');
INSERT INTO `common_player_exp` VALUES ('138', '1379522', '138', '138');
INSERT INTO `common_player_exp` VALUES ('139', '1409256', '139', '139');
INSERT INTO `common_player_exp` VALUES ('140', '1439415', '140', '140');
INSERT INTO `common_player_exp` VALUES ('141', '1470000', '141', '141');
INSERT INTO `common_player_exp` VALUES ('142', '1501016', '142', '142');
INSERT INTO `common_player_exp` VALUES ('143', '1532464', '143', '143');
INSERT INTO `common_player_exp` VALUES ('144', '1564349', '144', '144');
INSERT INTO `common_player_exp` VALUES ('145', '1596672', '145', '145');
INSERT INTO `common_player_exp` VALUES ('146', '1629438', '146', '146');
INSERT INTO `common_player_exp` VALUES ('147', '1662648', '147', '147');
INSERT INTO `common_player_exp` VALUES ('148', '1696307', '148', '148');
INSERT INTO `common_player_exp` VALUES ('149', '1730416', '149', '149');
INSERT INTO `common_player_exp` VALUES ('150', '1764980', '150', '150');
INSERT INTO `common_player_exp` VALUES ('151', '1800000', '151', '151');
INSERT INTO `common_player_exp` VALUES ('152', '1835481', '152', '152');
INSERT INTO `common_player_exp` VALUES ('153', '1871424', '153', '153');
INSERT INTO `common_player_exp` VALUES ('154', '1907834', '154', '154');
INSERT INTO `common_player_exp` VALUES ('155', '1944712', '155', '155');
INSERT INTO `common_player_exp` VALUES ('156', '1982063', '156', '156');
INSERT INTO `common_player_exp` VALUES ('157', '2019888', '157', '157');
INSERT INTO `common_player_exp` VALUES ('158', '2058192', '158', '158');
INSERT INTO `common_player_exp` VALUES ('159', '2096976', '159', '159');
INSERT INTO `common_player_exp` VALUES ('160', '2136245', '160', '160');
INSERT INTO `common_player_exp` VALUES ('161', '2176000', '161', '161');
INSERT INTO `common_player_exp` VALUES ('162', '2216246', '162', '162');
INSERT INTO `common_player_exp` VALUES ('163', '2256984', '163', '163');
INSERT INTO `common_player_exp` VALUES ('164', '2298219', '164', '164');
INSERT INTO `common_player_exp` VALUES ('165', '2339952', '165', '165');
INSERT INTO `common_player_exp` VALUES ('166', '2382188', '166', '166');
INSERT INTO `common_player_exp` VALUES ('167', '2424928', '167', '167');
INSERT INTO `common_player_exp` VALUES ('168', '2468177', '168', '168');
INSERT INTO `common_player_exp` VALUES ('169', '2511936', '169', '169');
INSERT INTO `common_player_exp` VALUES ('170', '2556210', '170', '170');
INSERT INTO `common_player_exp` VALUES ('171', '2601000', '171', '171');
INSERT INTO `common_player_exp` VALUES ('172', '2646311', '172', '172');
INSERT INTO `common_player_exp` VALUES ('173', '2692144', '173', '173');
INSERT INTO `common_player_exp` VALUES ('174', '2738504', '174', '174');
INSERT INTO `common_player_exp` VALUES ('175', '2785392', '175', '175');
INSERT INTO `common_player_exp` VALUES ('176', '2832813', '176', '176');
INSERT INTO `common_player_exp` VALUES ('177', '2880768', '177', '177');
INSERT INTO `common_player_exp` VALUES ('178', '2929262', '178', '178');
INSERT INTO `common_player_exp` VALUES ('179', '2978296', '179', '179');
INSERT INTO `common_player_exp` VALUES ('180', '3027875', '180', '180');
INSERT INTO `common_player_exp` VALUES ('181', '3078000', '181', '181');
INSERT INTO `common_player_exp` VALUES ('182', '3128676', '182', '182');
INSERT INTO `common_player_exp` VALUES ('183', '3179904', '183', '183');
INSERT INTO `common_player_exp` VALUES ('184', '3231689', '184', '184');
INSERT INTO `common_player_exp` VALUES ('185', '3284032', '185', '185');
INSERT INTO `common_player_exp` VALUES ('186', '3336938', '186', '186');
INSERT INTO `common_player_exp` VALUES ('187', '3390408', '187', '187');
INSERT INTO `common_player_exp` VALUES ('188', '3444447', '188', '188');
INSERT INTO `common_player_exp` VALUES ('189', '3499056', '189', '189');
INSERT INTO `common_player_exp` VALUES ('190', '3554240', '190', '190');
INSERT INTO `common_player_exp` VALUES ('191', '3610000', '191', '191');
INSERT INTO `common_player_exp` VALUES ('192', '3666341', '192', '192');
INSERT INTO `common_player_exp` VALUES ('193', '3723264', '193', '193');
INSERT INTO `common_player_exp` VALUES ('194', '3780774', '194', '194');
INSERT INTO `common_player_exp` VALUES ('195', '3838872', '195', '195');
INSERT INTO `common_player_exp` VALUES ('196', '3897563', '196', '196');
INSERT INTO `common_player_exp` VALUES ('197', '3956848', '197', '197');
INSERT INTO `common_player_exp` VALUES ('198', '4016732', '198', '198');
INSERT INTO `common_player_exp` VALUES ('199', '4077216', '199', '199');
INSERT INTO `common_player_exp` VALUES ('200', '4138305', '200', '200');