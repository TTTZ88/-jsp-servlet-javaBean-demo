/*
 Navicat Premium Data Transfer

 Source Server         : test001
 Source Server Type    : MySQL
 Source Server Version : 50722 (5.7.22)
 Source Host           : localhost:3306
 Source Schema         : mpdemo

 Target Server Type    : MySQL
 Target Server Version : 50722 (5.7.22)
 File Encoding         : 65001

 Date: 03/06/2024 13:01:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'user1', '123', '1990-01-01');
INSERT INTO `users` VALUES (2, 'user2', '147', '1995-05-15');
INSERT INTO `users` VALUES (3, 'user3', '258', '1988-11-30');
INSERT INTO `users` VALUES (4, 'user4', '369', '2000-07-20');
INSERT INTO `users` VALUES (5, 'user5', '666', '1992-03-10');
INSERT INTO `users` VALUES (6, 'admin', '123', '1999-02-09');

SET FOREIGN_KEY_CHECKS = 1;
