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

 Date: 31/08/2019 21:59:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `gmt_create` bigint(255) NOT NULL,
  `gmt_modified` bigint(255) NULL DEFAULT NULL,
  `creator` bigint(255) NULL DEFAULT NULL,
  `comment_count` int(255) NULL DEFAULT 0,
  `view_count` int(255) NULL DEFAULT 0,
  `like_count` int(255) NULL DEFAULT 0,
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (41, '《悲惨世界》', '悲惨的世界内容', 1567249262446, 1567249262446, 36, 4, 32, NULL, '惨');
INSERT INTO `question` VALUES (42, '问题的标题', '问题的内容', 1567249335248, 1567257974370, 36, 3, 38, NULL, '问题的标签');

SET FOREIGN_KEY_CHECKS = 1;
