/*
 Navicat Premium Data Transfer

 Source Server         : mysql数据库
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : blockchain

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 01/09/2022 10:48:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for helplist
-- ----------------------------
DROP TABLE IF EXISTS `helplist`;
CREATE TABLE `helplist`  (
  `listId` int(11) NOT NULL AUTO_INCREMENT,
  `donationState` int(11) NULL DEFAULT -1 COMMENT '求助状态  默认-1待审核 0未被帮助 1已处理',
  `recipientId` int(11) NOT NULL COMMENT '受捐者',
  `giverId` int(11) NULL DEFAULT NULL COMMENT '捐赠者',
  `donationDemand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求',
  `projectOrganizer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主办单位',
  `createDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建日期',
  `finishDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '完成日期',
  `logisticsInformation` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流信息',
  `recipientResponse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '受捐者反馈',
  PRIMARY KEY (`listId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 152 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of helplist
-- ----------------------------
INSERT INTO `helplist` VALUES (1, 0, 2, 5, '需要一些食物为山区小孩提供早餐', '四川红十字会总会', '2022-07-07', NULL, NULL, NULL);
INSERT INTO `helplist` VALUES (2, 0, 4, 3, '上海市中心医院急需一批口罩', '上海疾控', '2022-07-14', NULL, NULL, NULL);
INSERT INTO `helplist` VALUES (3, 1, 4, 5, '云南白族自治区开展科技进校园计划需要15套多媒体设备', '云南心连心公益小组', '2022-07-13', '2022-07-20 00:00:00.000000', '武汉集散中心/长沙集散中心/昆明转配站/昆明白族自治区', '这套区块链系统非常好用');
INSERT INTO `helplist` VALUES (150, -1, 1, NULL, '时间测试', '慈溯自营', '2022-08-29 10:01:24', NULL, NULL, NULL);
INSERT INTO `helplist` VALUES (151, -1, 2, NULL, '测试9.1', '慈溯自营', '2022-09-01 09:34:50', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userGender` int(11) NULL DEFAULT NULL COMMENT '用户性别 0女 1男',
  `userAge` int(11) NULL DEFAULT NULL,
  `userRoleId` int(11) NOT NULL COMMENT '用户角色 0管理员 1求助人 2帮助人',
  `userPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userEmail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张沐', 1, 19, 0, 'zhangmu', '7678676@qq.com', '18082737098');
INSERT INTO `user` VALUES (2, '老李', 1, 35, 1, 'laoli', 'laoli@163.com', '18767772801');
INSERT INTO `user` VALUES (3, '刘富贵', 1, 25, 2, 'liufugui', 'liufuguo@qq.com', '13267384322');
INSERT INTO `user` VALUES (4, '梁姐', 0, 29, 1, 'liangjie', 'null', '17689310412');
INSERT INTO `user` VALUES (5, '房哥', 1, 33, 2, 'fangge', 'null', '18980992144');

SET FOREIGN_KEY_CHECKS = 1;
