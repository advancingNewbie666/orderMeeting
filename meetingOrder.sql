/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.108
Source Server Version : 80019
Source Host           : 192.168.1.108:3306
Source Database       : meetingOrder

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-06-05 00:56:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `room_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '506');
INSERT INTO `room` VALUES ('2', '507');
INSERT INTO `room` VALUES ('3', '508');

-- ----------------------------
-- Table structure for use_info
-- ----------------------------
DROP TABLE IF EXISTS `use_info`;
CREATE TABLE `use_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `use_date` date NOT NULL,
  `user` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of use_info
-- ----------------------------
INSERT INTO `use_info` VALUES ('1', '1', '09:00:00', '10:00:00', '2020-06-04', 'gf');
INSERT INTO `use_info` VALUES ('2', '1', '11:00:00', '12:00:00', '2020-06-04', 'gf');
INSERT INTO `use_info` VALUES ('3', '1', '13:00:00', '14:00:00', '2020-05-01', 'wty');
INSERT INTO `use_info` VALUES ('4', '1', '13:00:00', '15:00:00', '2020-06-01', 'wty');
INSERT INTO `use_info` VALUES ('5', '1', '05:00:00', '05:30:00', '2020-06-04', '12312312');
INSERT INTO `use_info` VALUES ('6', '1', '05:00:00', '06:00:00', '2020-06-04', 'wty');
INSERT INTO `use_info` VALUES ('7', '1', '04:00:00', '04:30:00', '2020-06-04', '123');
INSERT INTO `use_info` VALUES ('8', '1', '05:00:00', '06:00:00', '2020-06-03', 'qqqqqq');
INSERT INTO `use_info` VALUES ('9', '1', '07:00:00', '07:30:00', '2020-06-04', 'eeeeee');
INSERT INTO `use_info` VALUES ('10', '1', '06:00:00', '06:30:00', '2020-06-04', '');
INSERT INTO `use_info` VALUES ('11', '1', '01:00:00', '02:00:00', '2020-06-04', 'gu-f@neusoft.com');
INSERT INTO `use_info` VALUES ('12', '1', '01:00:00', '01:30:00', '2020-06-06', 'gu-f@neusoft.com');
INSERT INTO `use_info` VALUES ('13', '1', '08:30:00', '10:30:00', '2020-06-04', 'ceshi');
INSERT INTO `use_info` VALUES ('14', '1', '03:00:00', '04:30:00', '2020-06-05', '测试');
