/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 31/08/2019 21:59:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(50) NOT NULL,
  `type` int(255) NOT NULL,
  `commentator` int(255) NOT NULL,
  `gmt_create` bigint(255) NOT NULL,
  `gmt_modified` bigint(255) NOT NULL,
  `like_count` int(255) NOT NULL DEFAULT 0,
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (8, 41, 1, 36, 1567249164486, 1567249164486, 0, '测试的回复');
INSERT INTO `comment` VALUES (9, 42, 1, 36, 1567259895585, 1567259895585, 0, '问清楚');
INSERT INTO `comment` VALUES (10, 42, 1, 36, 1567259899340, 1567259899340, 0, '威威v发');

SET FOREIGN_KEY_CHECKS = 1;
