/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : pocketbook

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 27/02/2022 01:08:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for url
-- ----------------------------
DROP TABLE IF EXISTS `url`;
CREATE TABLE `url` (
  `url_id` int unsigned NOT NULL AUTO_INCREMENT,
  `method` enum('GET','POST','PUT','DELETE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`url_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='url表，用于记录url';

-- ----------------------------
-- Records of url
-- ----------------------------
BEGIN;
INSERT INTO `url` (`url_id`, `method`, `url`) VALUES (1, 'PUT', '/user');
INSERT INTO `url` (`url_id`, `method`, `url`) VALUES (2, 'POST', '/user/login');
INSERT INTO `url` (`url_id`, `method`, `url`) VALUES (3, 'GET', '/user/authCode');
INSERT INTO `url` (`url_id`, `method`, `url`) VALUES (4, 'POST', '/user/resetPassword');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名20位以内数字，字母组合',
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '明文（6-20位数字密码组合）+userId 进行sha1签名',
  `created_time` bigint NOT NULL DEFAULT '0',
  `last_login_time` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
