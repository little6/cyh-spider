/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : spider

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 06/11/2018 09:47:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `url`     bigint(20)    NOT NULL,
  `bookUrl` varchar(50) CHARACTER SET utf8
  COLLATE utf8_general_ci NOT NULL,
  `title`   varchar(255) CHARACTER SET utf8
  COLLATE utf8_general_ci NOT NULL,
  `content` mediumtext CHARACTER SET utf8
  COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`url`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookUrl`            varchar(20) CHARACTER SET utf8
  COLLATE utf8_general_ci NOT NULL,
  `author`             varchar(255) CHARACTER SET utf8
  COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title`              varchar(255) CHARACTER SET utf8
  COLLATE utf8_general_ci NOT NULL,
  `updateTime`         varchar(50) CHARACTER SET utf8
  COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intro`              varchar(500) CHARACTER SET utf8
  COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latestChapterTitle` varchar(255) CHARACTER SET utf8
  COLLATE utf8_general_ci NULL DEFAULT NULL,
  `latestChapterUrl`   varchar(11) CHARACTER SET utf8
  COLLATE utf8_general_ci NULL DEFAULT NULL,
  `titlePageUrl`       varchar(255) CHARACTER SET utf8
  COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sourceUrl`          varchar(255) CHARACTER SET utf8
  COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chapterPage`        mediumtext CHARACTER SET utf8
  COLLATE utf8_general_ci NULL,
  `views`              mediumtext CHARACTER SET utf8
  COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`bookUrl`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
