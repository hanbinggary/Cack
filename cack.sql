/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : cack

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 16/04/2021 15:24:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `username` varchar(20) NOT NULL COMMENT '登录用户名',
  `password` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  `password_level` tinyint unsigned DEFAULT '0' COMMENT '密码等级，0-弱，1-中等，2-强',
  `nickname` varchar(20) DEFAULT '' COMMENT '昵称',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号',
  `status` tinyint unsigned DEFAULT '1' COMMENT '状态，0-无效，1-有效，默认为1',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `role_id` bigint unsigned DEFAULT NULL COMMENT '关联-角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (1, 0, '2021-04-16 14:00:50', '2021-04-16 14:01:16', 'admin', '$2a$10$cA7KwNATdrFz4kWbwcM55OvAj2v7lPwkI8zAJyH99mCMSZiMvxrrm', 0, '奔跑的梵高', '18889607433', 1, '2021-04-16 14:01:16', 1);
INSERT INTO `admin` VALUES (2, 0, '2021-04-16 15:20:08', '2021-04-16 15:23:15', 'lxl', '$2a$10$LV23EGXXj.Hs4CZu7dnOOeaPZ0nQpaewj1VzTU0drho/FJrBYPpMe', 1, '像鱼<・)))><<', '18889607433', 1, '2021-04-16 15:20:16', 2);
COMMIT;

-- ----------------------------
-- Table structure for booking
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `order_no` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
  `status` tinyint unsigned DEFAULT '1' COMMENT '订单状态：0-强制取消，1-待车检，2-车检中，3-完成，4-用户取消',
  `cancel_reason` varchar(100) DEFAULT '' COMMENT '取消订单理由',
  `start_check_time` datetime DEFAULT NULL COMMENT '开始车检时间',
  `end_check_time` datetime DEFAULT NULL COMMENT '完成车检时间',
  `user_coupon_id` bigint unsigned DEFAULT NULL COMMENT '关联-优惠券ID',
  `booking_time_id` bigint unsigned DEFAULT NULL COMMENT '关联-预约时间ID',
  `vehicle_id` bigint unsigned DEFAULT NULL COMMENT '关联-车辆ID',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '关联-用户ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预约车检表';

-- ----------------------------
-- Records of booking
-- ----------------------------
BEGIN;
INSERT INTO `booking` VALUES (1, 0, '2021-04-16 14:56:37', '2021-04-16 15:16:56', '39589512210416145637', 3, '', '2021-04-16 15:07:39', '2021-04-16 15:16:57', 1, 3, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for booking_time
-- ----------------------------
DROP TABLE IF EXISTS `booking_time`;
CREATE TABLE `booking_time` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `date` date DEFAULT NULL COMMENT '日期',
  `start_time` tinyint unsigned DEFAULT NULL COMMENT '开始时间',
  `end_time` tinyint unsigned DEFAULT NULL COMMENT '结束时间',
  `status` tinyint unsigned DEFAULT '1' COMMENT '状态，0-无效，1-有效，默认为1',
  `total` bigint unsigned DEFAULT '0' COMMENT '预约总数',
  `invalid_reason` varchar(100) DEFAULT '' COMMENT '无效理由',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='可预约时间表';

-- ----------------------------
-- Records of booking_time
-- ----------------------------
BEGIN;
INSERT INTO `booking_time` VALUES (1, 0, '2021-04-16 14:16:09', '2021-04-16 14:16:09', '2021-04-18', 9, 10, 1, 0, '');
INSERT INTO `booking_time` VALUES (2, 0, '2021-04-16 14:16:09', '2021-04-16 14:16:09', '2021-04-18', 10, 11, 1, 0, '');
INSERT INTO `booking_time` VALUES (3, 0, '2021-04-16 14:16:09', '2021-04-16 14:56:37', '2021-04-18', 11, 12, 1, 1, '');
INSERT INTO `booking_time` VALUES (4, 0, '2021-04-16 14:16:09', '2021-04-16 14:16:09', '2021-04-19', 9, 10, 1, 0, '');
INSERT INTO `booking_time` VALUES (5, 0, '2021-04-16 14:16:09', '2021-04-16 14:16:09', '2021-04-19', 10, 11, 1, 0, '');
INSERT INTO `booking_time` VALUES (6, 0, '2021-04-16 14:16:09', '2021-04-16 14:16:09', '2021-04-19', 11, 12, 1, 0, '');
COMMIT;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `money` smallint unsigned NOT NULL DEFAULT '0' COMMENT '优惠券金额',
  `is_expired` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否过期，0-未过期，1-过期',
  `status` tinyint unsigned DEFAULT '1' COMMENT '是否有效，0-无效，1-有效',
  `expired_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='优惠券表';

-- ----------------------------
-- Records of coupon
-- ----------------------------
BEGIN;
INSERT INTO `coupon` VALUES (1, 0, '2021-04-16 14:15:48', '2021-04-16 14:15:48', 20, 0, 1, '2021-04-19 20:59:59');
INSERT INTO `coupon` VALUES (2, 1, '2021-04-16 15:12:59', '2021-04-16 15:16:18', 10, 0, 1, '2021-04-22 22:59:59');
COMMIT;

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '消息标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '消息内容',
  `admin_id` bigint unsigned DEFAULT NULL COMMENT '相关-发布的管理员ID',
  `type` tinyint unsigned DEFAULT '0' COMMENT '消息类型：0-系统消息，1-预约消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='消息通知表';

-- ----------------------------
-- Records of notify
-- ----------------------------
BEGIN;
INSERT INTO `notify` VALUES (1, 0, '2021-04-16 14:56:38', '2021-04-16 14:56:38', '预约成功', '订单号为39589512210416145637成功预约2021-04-18 11:00 ~ 12:00时段进行车检。', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `name` varchar(10) NOT NULL COMMENT '角色名称',
  `desc` varchar(100) DEFAULT '' COMMENT '角色描述',
  `status` tinyint unsigned DEFAULT '1' COMMENT '状态，0-无效，1-有效，默认为1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 0, '2021-04-16 14:01:01', '2021-04-16 14:01:01', 'ADMIN', '所有权限', 1);
INSERT INTO `role` VALUES (2, 0, '2021-04-16 14:01:09', '2021-04-16 14:01:09', 'USER', '部分权限', 1);
COMMIT;

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '验车站名称',
  `addr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '验车站地址',
  `longitude` varchar(10) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(10) DEFAULT NULL COMMENT '纬度',
  `desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '描述',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机号',
  `tel` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '联系电话',
  `opening_time` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '营业时间，09:00@18:00,@分隔',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '图片',
  `max_time_booking_total` tinyint unsigned DEFAULT '0' COMMENT '限制每个时间段预约的人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='验车站信息表';

-- ----------------------------
-- Records of station
-- ----------------------------
BEGIN;
INSERT INTO `station` VALUES (1, 0, '2021-04-16 14:02:47', '2021-04-16 15:22:44', 'Cack验车站', '河北省唐山市曹妃甸华北理工大学', '118.59931', '39.21084', '线上预约车检', '18889808633', '', '09:00@18:00', '', 18);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `openid` varchar(30) NOT NULL COMMENT '小程序用户唯一标识',
  `union_id` varchar(30) NOT NULL DEFAULT '' COMMENT '小程序用户开放平台的唯一标识',
  `session_key` varchar(30) NOT NULL DEFAULT '' COMMENT '小程序会话秘钥',
  `nickname` varchar(20) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar_url` varchar(150) DEFAULT '' COMMENT '用户头像',
  `tel` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '电话号码',
  `gender` tinyint unsigned DEFAULT '0' COMMENT '用户性别，0-未知，1-男性，2-女性',
  `country` varchar(10) DEFAULT '' COMMENT '所在国家',
  `province` varchar(20) DEFAULT '' COMMENT '所在省份',
  `city` varchar(20) DEFAULT '' COMMENT '所在城市',
  `status` tinyint unsigned DEFAULT '1' COMMENT '账户状态，0-禁用，1-启用，默认为1',
  `last_login_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (3, 0, '2021-04-16 14:15:08', '2021-04-16 14:15:08', 'o_k5p5IaT7AmxBUzUweuFXtDAUPw', '', '66RKqemrFsS8FqbnBcNElw==', 'lxl', 'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epbcia23Bvox2KLomLY5jH9icicFEQ0NED1ficR87Xf9tX8R2VVn0AlrwRyR8gicdryjcadsYYTJwuzh3w/132', '', 2, '中国', '海南', '儋州', 1, '2021-04-16 14:15:09');
COMMIT;

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `is_used` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否使用，0-未使用，1-已使用',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '用户ID',
  `coupon_id` bigint unsigned DEFAULT NULL COMMENT '优惠券ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户领取优惠券表';

-- ----------------------------
-- Records of user_coupon
-- ----------------------------
BEGIN;
INSERT INTO `user_coupon` VALUES (1, 0, '2021-04-16 14:15:48', '2021-04-16 14:56:37', 1, 3, 1);
INSERT INTO `user_coupon` VALUES (2, 1, '2021-04-16 15:12:59', '2021-04-16 15:16:18', 0, 3, 2);
COMMIT;

-- ----------------------------
-- Table structure for user_notify
-- ----------------------------
DROP TABLE IF EXISTS `user_notify`;
CREATE TABLE `user_notify` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `is_readed` tinyint unsigned DEFAULT '0' COMMENT '是否已读，0-未读，1-已读',
  `readed_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '相关-用户ID',
  `notify_id` bigint unsigned DEFAULT NULL COMMENT '相关-消息ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户消息表';

-- ----------------------------
-- Records of user_notify
-- ----------------------------
BEGIN;
INSERT INTO `user_notify` VALUES (1, 0, '2021-04-16 14:56:38', '2021-04-16 14:57:27', 1, '2021-04-16 14:57:28', 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `plate_no` varchar(10) DEFAULT '' COMMENT '车牌号',
  `vehicle_type` varchar(10) DEFAULT '' COMMENT '车辆类型',
  `use_character` varchar(10) DEFAULT '' COMMENT '使用性质',
  `vin` char(17) DEFAULT '' COMMENT '车辆识别代码',
  `engine_no` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '发动机号码',
  `odometer` mediumint unsigned DEFAULT '0' COMMENT '总里程数',
  `register_date` date DEFAULT NULL COMMENT '注册日期',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '关联-用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆表';

-- ----------------------------
-- Records of vehicle
-- ----------------------------
BEGIN;
INSERT INTO `vehicle` VALUES (1, 0, '2021-04-16 14:23:08', '2021-04-16 15:19:45', '京F08621', '小型轿车', '非营运', 'LNJ1898Q372748929', '1982736', 8217, '2020-01-08', 3);
COMMIT;

-- ----------------------------
-- Table structure for vehicle_type
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_type`;
CREATE TABLE `vehicle_type` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `name` varchar(10) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆类型表';

-- ----------------------------
-- Records of vehicle_type
-- ----------------------------
BEGIN;
INSERT INTO `vehicle_type` VALUES (1, 0, '2021-04-16 14:16:49', '2021-04-16 14:16:49', '小型轿车');
INSERT INTO `vehicle_type` VALUES (2, 0, '2021-04-16 14:16:50', '2021-04-16 14:16:50', '轿车');
INSERT INTO `vehicle_type` VALUES (3, 0, '2021-04-16 14:16:57', '2021-04-16 14:16:57', '小型载客客车');
COMMIT;

-- ----------------------------
-- Table structure for vehicle_use_character
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_use_character`;
CREATE TABLE `vehicle_use_character` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_delete` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除，0-未删除，1-已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `name` varchar(10) NOT NULL COMMENT '使用性质名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆使用性质表';

-- ----------------------------
-- Records of vehicle_use_character
-- ----------------------------
BEGIN;
INSERT INTO `vehicle_use_character` VALUES (1, 0, '2021-04-16 14:17:04', '2021-04-16 14:17:04', '非营运');
INSERT INTO `vehicle_use_character` VALUES (2, 0, '2021-04-16 14:17:08', '2021-04-16 14:17:08', '营运');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
