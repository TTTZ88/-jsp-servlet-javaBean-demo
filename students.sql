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

 Date: 03/06/2024 13:01:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES (1, 'Alice Smith', 'Math', 85);
INSERT INTO `students` VALUES (2, 'Bob Johnson', 'English', 92);
INSERT INTO `students` VALUES (3, 'Charlie Brown', 'Science', 78);
INSERT INTO `students` VALUES (4, 'Diana Prince', 'History', 88);
INSERT INTO `students` VALUES (5, 'Ethan Hunt', 'Physics', 90);
INSERT INTO `students` VALUES (6, '张三', '语文', 89);
INSERT INTO `students` VALUES (7, 'Yu', 'English', 90);
INSERT INTO `students` VALUES (8, 'Liu', 'Maths', 88);
INSERT INTO `students` VALUES (9, 'Wang', '语文', 87);
INSERT INTO `students` VALUES (10, 'Han', 'Maths', 92);
INSERT INTO `students` VALUES (11, '小明', '语文', 85);
INSERT INTO `students` VALUES (12, '王鹏', '语文', 98);
INSERT INTO `students` VALUES (13, '小黄', '语文', 98);
INSERT INTO `students` VALUES (15, '小韩', '数学', 99);

SET FOREIGN_KEY_CHECKS = 1;
