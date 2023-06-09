/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : nas_monitor

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 09/06/2023 18:15:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nas_info
-- ----------------------------
DROP TABLE IF EXISTS `nas_info`;
CREATE TABLE `nas_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nars_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路径',
  `nars_size` decimal(20, 2) NULL DEFAULT NULL COMMENT '总空间',
  `nars_used` decimal(20, 2) NULL DEFAULT NULL COMMENT '已用空间',
  `nars_avail` decimal(20, 2) NULL DEFAULT NULL COMMENT '可用空间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `user_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属组',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nas_info
-- ----------------------------
INSERT INTO `nas_info` VALUES (1, 'E:\\data\\han', 314572800.00, 103967088.00, 210605712.00, '2023-06-09 16:13:00', NULL, '2023-06-09 16:13:00', NULL, NULL);
INSERT INTO `nas_info` VALUES (2, 'E:\\data\\han2', 31457280.00, 29697573.00, 1759707.00, '2023-06-09 15:55:00', NULL, '2023-06-09 15:55:00', NULL, NULL);
INSERT INTO `nas_info` VALUES (3, 'E:\\data\\han3', 31457280.00, 0.00, 31457280.00, '2023-06-09 15:55:00', NULL, '2023-06-09 15:55:00', NULL, NULL);
INSERT INTO `nas_info` VALUES (5, 'E:\\data\\han2', 31457280.00, 29697573.00, 1759707.00, '2023-06-09 15:55:00', NULL, '2023-06-09 15:55:00', NULL, NULL);
INSERT INTO `nas_info` VALUES (6, 'E:\\data\\han2', 31457280.00, 29697573.00, 1759707.00, '2023-06-09 15:55:00', NULL, '2023-06-09 15:55:00', NULL, NULL);
INSERT INTO `nas_info` VALUES (10, 'E:\\data\\han2', 31457280.00, 29697573.00, 1759707.00, '2023-06-09 15:55:00', NULL, '2023-06-09 15:55:00', NULL, NULL);

-- ----------------------------
-- Table structure for send_mail_flow
-- ----------------------------
DROP TABLE IF EXISTS `send_mail_flow`;
CREATE TABLE `send_mail_flow`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `soeid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人soeid',
  `send_to` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人email',
  `send_type` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送告警类型',
  `subject` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '邮件内容',
  `send_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `memo` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 868 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of send_mail_flow
-- ----------------------------
INSERT INTO `send_mail_flow` VALUES (863, 'yh08123', '15998451842@163.com', '1', '空间超出阈值预警', '目录 E:\\data\\han 的空间使用超过阀值，当前大小为: 99.15MB bytes\n超过阀值的文件列表:\n海银移动增加查询客户在基金的投资者类型.7z', '2023-06-09 17:29:04', NULL);
INSERT INTO `send_mail_flow` VALUES (864, 'yh08123', '15998451842@163.com', '1', '空间超出阈值预警', '目录 E:\\data\\han 的空间使用超过阀值，当前大小为: 99.15MB bytes\n超过阀值的文件列表:\n海银移动增加查询客户在基金的投资者类型.7z', '2023-06-09 17:30:00', NULL);
INSERT INTO `send_mail_flow` VALUES (865, 'yh08123', '15998451842@163.com', '1', '空间超出阈值预警', '目录 E:\\data\\han 的空间使用超过阀值，当前大小为: 99.15MB bytes\n超过阀值的文件列表:\n海银移动增加查询客户在基金的投资者类型.7z', '2023-06-09 17:31:00', NULL);
INSERT INTO `send_mail_flow` VALUES (866, 'yh08123', '15998451842@163.com', '1', '空间超出阈值预警', '目录 E:\\data\\han 的空间使用超过阀值，当前大小为: 99.15MB bytes\n超过阀值的文件列表:\n海银移动增加查询客户在基金的投资者类型.7z', '2023-06-09 17:32:00', NULL);
INSERT INTO `send_mail_flow` VALUES (867, 'yh08123', '15998451842@163.com', '1', '空间超出阈值预警', '目录 E:\\data\\han 的空间使用超过阀值，当前大小为: 99.15MB bytes\n超过阀值的文件列表:\n海银移动增加查询客户在基金的投资者类型.7z', '2023-06-09 17:33:01', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `soeid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'soeid工号',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` datetime NULL DEFAULT NULL COMMENT '更新人',
  `user_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属组',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_soeid`(`soeid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (3, 'yh08123', '测试2', '15998451842@163.com', '2023-06-06 18:13:34', '2023-06-06 18:13:34', '', NULL, '1');
INSERT INTO `sys_user` VALUES (4, 'yh08123', '测试4', '15998451842@163.com', '2023-06-06 18:13:38', '2023-06-06 18:13:38', '', NULL, '1');
INSERT INTO `sys_user` VALUES (5, 'yh08123', '测试4', '15998451842@163.com', '2023-06-06 18:13:41', '2023-06-06 18:13:41', NULL, NULL, '1');
INSERT INTO `sys_user` VALUES (6, '1', '1', '1', '2023-06-01 18:30:15', '2023-06-01 18:30:15', NULL, NULL, '1');
INSERT INTO `sys_user` VALUES (7, '2', '1', '3', '2023-06-05 18:25:25', '2023-06-05 18:25:25', NULL, NULL, '4');
INSERT INTO `sys_user` VALUES (8, '3', 'dasds', '3', '2023-06-06 13:41:00', '2023-06-06 13:41:00', NULL, NULL, '3');

-- ----------------------------
-- Table structure for sys_user_nas
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_nas`;
CREATE TABLE `sys_user_nas`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `nas_id` int NULL DEFAULT NULL COMMENT 'nas盘id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userid_nasid`(`user_id` ASC, `nas_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'user & nas关联关系维护表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_nas
-- ----------------------------
INSERT INTO `sys_user_nas` VALUES (1, 3, 10, '2023-06-06 15:42:39', '2023-06-06 15:42:39', NULL, NULL);
INSERT INTO `sys_user_nas` VALUES (2, 5, 1, '2023-06-06 17:52:56', '2023-06-06 17:52:56', NULL, NULL);
INSERT INTO `sys_user_nas` VALUES (3, 5, 2, '2023-06-06 16:53:17', '2023-06-06 16:53:17', NULL, NULL);
INSERT INTO `sys_user_nas` VALUES (4, 4, 3, '2023-06-06 17:05:54', '2023-06-06 17:05:54', NULL, NULL);
INSERT INTO `sys_user_nas` VALUES (5, 8, 5, '2023-06-06 16:53:25', '2023-06-06 16:53:25', NULL, NULL);
INSERT INTO `sys_user_nas` VALUES (6, 5, 6, '2023-06-06 16:53:41', '2023-06-06 16:53:41', NULL, NULL);
INSERT INTO `sys_user_nas` VALUES (7, 6, 7, '2023-06-06 17:06:16', '2023-06-06 17:06:16', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
