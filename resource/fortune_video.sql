/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : fortune_video

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 22/11/2024 09:13:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`          bigint(20)                                              NOT NULL COMMENT '分类id',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
    `desc`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
    `is_del`      bit(1)                                                  NULL DEFAULT b'0' COMMENT '是否删除',
    `create_time` datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category`
VALUES (1, '电影', '包含各类电影内容', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (2, '电视剧', '包含各类电视连续剧', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (3, '动漫', '包含动画、动漫相关内容', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (4, '纪录片', '记录片与纪实类视频', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (5, '生活日常', '日常生活、生活方式分享', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (6, '美食', '美食制作、美食探店等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (7, '旅游', '旅游攻略、景点介绍等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (8, '时尚', '穿搭、美妆等时尚内容', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (9, '教育', '教学视频、在线课程等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (10, '科技', '科技资讯、数码产品等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (11, '知识', '知识科普、百科知识分享', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (12, '游戏', '游戏实况、攻略等内容', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (13, '音乐', '音乐视频、MV、演唱会等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (14, '体育', '体育赛事、运动教学等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (15, '搞笑', '搞笑视频、段子等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (16, '资讯', '新闻资讯、热点话题等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (17, '宠物', '宠物相关的视频内容', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');
INSERT INTO `category`
VALUES (18, '汽车', '汽车评测、驾驶技巧等', b'0', '2024-11-21 17:53:03', '2024-11-21 17:53:03');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint(20)                                               NOT NULL COMMENT '用户id',
    `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '用户名',
    `avatar`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '头像url',
    `account`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '账号',
    `password`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '密码',
    `desc`        varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
    `status`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '状态',
    `is_del`      bit(1)                                                   NULL DEFAULT NULL COMMENT '是否删除',
    `create_time` datetime                                                 NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                 NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, '测试1号', NULL, 'admin', 'admin', '测试', '1', b'0', '2024-11-20 11:26:55', '2024-11-20 11:26:57');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`
(
    `id`            bigint(20)                                              NOT NULL COMMENT 'id',
    `user_id`       bigint(20)                                              NULL DEFAULT NULL COMMENT '用户id',
    `category_id`   bigint(20)                                              NULL DEFAULT NULL COMMENT '分类id',
    `title`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
    `desc`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
    `video_url`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频地址',
    `thumbnail_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图地址',
    `state`         tinyint(4)                                              NULL DEFAULT NULL COMMENT '状态',
    `is_del`        bit(1)                                                  NULL DEFAULT b'0' COMMENT '是否删除',
    `create_time`   datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime                                                NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video`
VALUES (1, 1, 1, '视频标题1', '视频备注', 'http://localhost:80/v1/file/1732154245247_1e83f266-5500-47db-b0b2-8a120074616b.mp4',
        'http://localhost:80/v1/file/1732154171491_1728984652599.jpg', 1, b'0', '2024-11-20 11:28:41',
        '2024-11-20 11:28:41');
INSERT INTO `video`
VALUES (2, 1, 1, '视频标题2', '视频备注', 'http://localhost:80/v1/file/1732154245247_1e83f266-5500-47db-b0b2-8a120074616b.mp4',
        'http://localhost:80/v1/file/1732154171491_1728984652599.jpg', 1, b'0', '2024-11-20 11:28:41',
        '2024-11-20 11:28:41');
INSERT INTO `video`
VALUES (3, 1, 1, '视频标题3', '视频备注', 'http://localhost:80/v1/file/1732154245247_1e83f266-5500-47db-b0b2-8a120074616b.mp4',
        'http://localhost:80/v1/file/1732154171491_1728984652599.jpg', 1, b'0', '2024-11-20 11:28:41',
        '2024-11-20 11:28:41');
INSERT INTO `video`
VALUES (4, 1, 1, '视频标题4', '视频备注', 'http://localhost:80/v1/file/1732154245247_1e83f266-5500-47db-b0b2-8a120074616b.mp4',
        'http://localhost:80/v1/file/1732154171491_1728984652599.jpg', 1, b'0', '2024-11-20 11:28:41',
        '2024-11-20 11:28:41');
INSERT INTO `video`
VALUES (5, 1, 1, '视频标题5', '视频备注', 'http://localhost:80/v1/file/1732154245247_1e83f266-5500-47db-b0b2-8a120074616b.mp4',
        'http://localhost:80/v1/file/1732154171491_1728984652599.jpg', 1, b'0', '2024-11-20 11:28:41',
        '2024-11-20 11:28:41');
INSERT INTO `video`
VALUES (6, 1, 1, '视频标题6', '视频备注', 'http://localhost:80/v1/file/1732154245247_1e83f266-5500-47db-b0b2-8a120074616b.mp4',
        'http://localhost:80/v1/file/1732154171491_1728984652599.jpg', 1, b'0', '2024-11-20 11:28:41',
        '2024-11-20 11:28:41');
INSERT INTO `video`
VALUES (1859521683086487554, 1, 1, '11', '22',
        'http://localhost:80/v1/file/1732179492082_b42fcade-26bc-4de7-8f02-2c21b40d9536.mp4',
        'http://localhost:80/v1/file/1732179492099_avatar-AI.jpg', 1, b'0', '2024-11-21 16:58:12',
        '2024-11-21 16:58:12');
INSERT INTO `video`
VALUES (1859529991579049986, 1, 1, '123', '123', 'http://localhost:80/v1/file/1732181472782_下载+1.mp4', NULL, 1, b'0',
        '2024-11-21 17:31:13', '2024-11-21 17:31:13');

SET FOREIGN_KEY_CHECKS = 1;
