-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 120.78.10.173    Database: publicboard
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- -----------------------------------------------------
-- Schema PublicBoard
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `PublicBoard` ;

-- -----------------------------------------------------
-- Schema PublicBoard
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PublicBoard` DEFAULT CHARACTER SET utf8 ;
USE `PublicBoard` ;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logs` (
  `logsID` int unsigned NOT NULL AUTO_INCREMENT,
  `targetID` int NOT NULL,
  `operateType` varchar(20) NOT NULL,
  `operateInfo` varchar(1200) NOT NULL,
  `operateTime` datetime NOT NULL,
  PRIMARY KEY (`logsID`),
  UNIQUE KEY `Logs_logsID_uindex` (`logsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--


--
-- Table structure for table `news_comment`
--

DROP TABLE IF EXISTS `news_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_comment` (
  `commentID` int unsigned NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `newsID` int unsigned NOT NULL COMMENT '帖子编号',
  `userID` int NOT NULL COMMENT '评论人账号',
  `content` text NOT NULL COMMENT '内容',
  `commentTime` datetime NOT NULL COMMENT '评论时间',
  `likeNum` int NOT NULL DEFAULT '0' COMMENT '点赞量',
  `anonymousState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '匿名状态',
  PRIMARY KEY (`commentID`),
  KEY `fk_news_comment_news_info1_idx` (`newsID`),
  CONSTRAINT `fk_news_comment_news_info1` FOREIGN KEY (`newsID`) REFERENCES `news_info` (`newsID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='帖子评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_comment`
--

INSERT INTO `news_comment` VALUES (1,1,2019010101,'测试帖子评论','2021-11-26 13:35:31',0,0);
INSERT INTO `news_comment` VALUES (2,2,2019020202,'测试帖子评论','2021-11-26 13:36:25',0,0);

--
-- Table structure for table `news_image`
--

DROP TABLE IF EXISTS `news_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_image` (
  `imageUUID` char(40) NOT NULL COMMENT '图像UUID',
  `newsID` int unsigned NOT NULL,
  PRIMARY KEY (`imageUUID`),
  UNIQUE KEY `image_id_UNIQUE` (`imageUUID`),
  KEY `fk_news_image_news_info1_idx` (`newsID`),
  CONSTRAINT `fk_news_image_news_info1` FOREIGN KEY (`newsID`) REFERENCES `news_info` (`newsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='帖子对应的图像';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_image`
--

INSERT INTO `news_image` VALUES ('43109dd1222f4024b2418461a7b34c6d.jpg',3);
INSERT INTO `news_image` VALUES ('9781c78417c0480ca1b2496e57cac03c.jpg',3);
INSERT INTO `news_image` VALUES ('cede03b1bc80418397172b2d680d5292.jpg',3);
INSERT INTO `news_image` VALUES ('546153285bcc489683373b51a92aa0cb.jpg',4);
INSERT INTO `news_image` VALUES ('ff2ecff10d1142b2beda6d9b15fbf765.jpg',4);
INSERT INTO `news_image` VALUES ('6fa7ab2ff94b47328ef1e5cfa110f12a.jpg',5);
INSERT INTO `news_image` VALUES ('d259576a52aa41cda78157d4738d98cc.jpg',5);

--
-- Table structure for table `news_info`
--

DROP TABLE IF EXISTS `news_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_info` (
  `newsID` int unsigned NOT NULL AUTO_INCREMENT COMMENT '帖子编号',
  `userID` int unsigned NOT NULL,
  `newsTitle` varchar(30) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `sendTime` datetime NOT NULL COMMENT '发布时间',
  `viewNum` int NOT NULL DEFAULT '0' COMMENT '浏览量',
  `likeNum` int NOT NULL DEFAULT '0' COMMENT '点赞量',
  `newsTypeID` int unsigned NOT NULL,
  `anonymousState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '匿名状态',
  `topState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '置顶状态',
  `hotState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '热门状态',
  `examineState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核状态',
  PRIMARY KEY (`newsID`),
  UNIQUE KEY `newsID_UNIQUE` (`newsID`),
  KEY `fk_news_info_user_info_idx` (`userID`),
  KEY `fk_news_info_news_type1_idx` (`newsTypeID`),
  CONSTRAINT `fk_news_info_news_type1` FOREIGN KEY (`newsTypeID`) REFERENCES `news_type` (`newsTypeID`),
  CONSTRAINT `fk_news_info_user_info` FOREIGN KEY (`userID`) REFERENCES `user_info` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COMMENT='帖子表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_info`
--

INSERT INTO `news_info` VALUES (1,2019010101,'a测试发帖','测试发帖','2021-11-26 13:31:36',33,0,1,0,0,0,0);
INSERT INTO `news_info` VALUES (2,2019020202,'b测试发帖','测试发帖','2021-11-26 13:32:36',17,0,2,0,0,0,0);
INSERT INTO `news_info` VALUES (3,2019010101,'图片帖子测试','图片帖子测试','2021-12-13 10:54:29',31,0,1,0,0,0,0);
INSERT INTO `news_info` VALUES (4,2019010101,'图片帖子测试2','图片帖子测试2','2021-12-13 12:24:41',7,0,2,0,0,0,0);
INSERT INTO `news_info` VALUES (5,2019020202,'邹秉熙测试','2121123213213213213123123','2021-12-14 05:32:50',10,0,1,0,0,0,0);
INSERT INTO `news_info` VALUES (6,2019010101,'标题：测试帖子','内容：测试帖子','2021-12-14 09:14:21',0,0,3,1,0,0,0);
INSERT INTO `news_info` VALUES (7,2019020202,'asdasdasdsadsa','asdsasadsadsadsadsadsadasd','2021-12-15 19:12:16',0,0,1,0,0,0,0);
INSERT INTO `news_info` VALUES (8,2019010101,'关于大司马是御坂美琴的论证','关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。\n关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。\n关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。','2021-12-15 19:33:11',0,0,3,0,0,0,0);
INSERT INTO `news_info` VALUES (9,2019010101,'关于大司马是御坂美琴的论证','关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。\n关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。\n关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。\n关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。','2021-12-15 19:34:37',0,0,3,0,0,0,0);
INSERT INTO `news_info` VALUES (10,2019010101,'关于大司马是御坂美琴的论证','关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。关于大司马是御坂美琴的论证：\n1. 御坂美琴在面对上条当麻时喜欢嘴硬，大司马在面对观众时也喜欢嘴硬。\n2.御坂美琴傲娇时很可爱，大司马发病时也很可爱。\n3.御坂美琴不轻言放弃，大司马也从不点投降。\n4.御坂美琴和大司马都喜欢教训愣头小青年。\n5.御坂美琴和大司马都被人津津乐道。\n6.御坂美琴深受黑子等同性喜爱，大司马也深受男酮们喜爱。\n7.因为御坂美琴和大司马不在同一次元，所以它们在同一次元，这句话是个哲学。','2021-12-15 19:34:54',1,0,1,0,0,0,0);
INSERT INTO `news_info` VALUES (11,2019010101,'看LOL视频不看马老师，就像四大名著不看红楼梦','看LOL视频不看马老师，就像四大名著不看红楼梦，说明这个人美食鉴赏和自我修养不足，他理解不了这种高雅艺术，他只能看到小鲜肉的堆砌，参不透其中深奥的精神内核，他整个人的层次就卡在这里了，只能度过一个相对失败的人生\n打开了大司马的视频，我的西服自动套到了我的身上，红酒杯也开始自己摇晃，黑胶唱片也发出了声音\n看LOL视频不看马老师，就像四大名著不看红楼梦，说明这个人美食鉴赏和自我修养不足，他理解不了这种高雅艺术，他只能看到小鲜肉的堆砌，参不透其中深奥的精神内核，他整个人的层次就卡在这里了，只能度过一个相对失败的人生\n打开了大司马的视频，我的西服自动套到了我的身上，红酒杯也开始自己摇晃，黑胶唱片也发出了声音','2021-12-15 20:05:10',0,0,1,0,0,0,0);
INSERT INTO `news_info` VALUES (12,2019010101,'马老师语录','家里煤气泄漏，诶，我一点也不慌，\n\n他皮任他皮，先点根烟镇镇场子，哎哟，着火了，跑？不存在的，\n\n我反手躺床上睡他一波，看煤气瓶怎么说，诶，这就很舒服。煤气瓶要炸？问题不大，我先摆个正方形睡姿，边缘OB一波，煤气瓶怎么说。哟，这小伙子很皮，头很铁，很有灵性，好像真的要炸。我头皮发麻了，但我不怕，这时候就要逆向思维，给他一个重锤，你以为呐，我省港澳第一瓜皮会怕你个煤气瓶。……Boom!!!哇，我很难受。','2021-12-15 20:06:07',0,0,1,0,0,0,0);

--
-- Table structure for table `news_reply`
--

--
-- Table structure for table `news_like`
--

DROP TABLE IF EXISTS `news_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_like` (
  `newsID` int unsigned NOT NULL COMMENT '帖子编号',
  `userID` int unsigned NOT NULL COMMENT '账号',
  PRIMARY KEY (`newsID`,`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_like`
--
INSERT INTO `news_like` VALUES (3,2019010101);
INSERT INTO `news_like` VALUES (3,2019020202);

DROP TABLE IF EXISTS `news_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_reply` (
  `replyID` int NOT NULL AUTO_INCREMENT COMMENT '回复编号',
  `targetID` int NOT NULL COMMENT '目标编号',
  `commentID` int unsigned NOT NULL COMMENT '评论编号',
  `userID` int NOT NULL COMMENT '回复人账号',
  `content` text NOT NULL COMMENT '内容',
  `commentTime` datetime NOT NULL COMMENT '评论时间',
  `likeNum` int NOT NULL DEFAULT '0' COMMENT '点赞量',
  `anonymousState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '匿名状态',
  PRIMARY KEY (`replyID`),
  KEY `fk_news_reply_news_comment_idx` (`commentID`),
  CONSTRAINT `fk_news_reply_news_comment` FOREIGN KEY (`commentID`) REFERENCES `news_comment` (`commentID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='帖子回复表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_reply`
--

INSERT INTO `news_reply` VALUES (1,0,1,2019020202,'回复测试1','2021-12-01 21:22:18',0,0);
INSERT INTO `news_reply` VALUES (2,0,1,2019020202,'回复测试2','2021-12-01 21:22:47',0,0);
INSERT INTO `news_reply` VALUES (3,1,1,2019010101,'回复测试3','2021-12-01 21:23:11',0,0);
INSERT INTO `news_reply` VALUES (4,3,1,2019020202,'回复测试4','2021-12-01 21:23:39',0,0);

--
-- Table structure for table `news_type`
--

DROP TABLE IF EXISTS `news_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_type` (
  `newsTypeID` int unsigned NOT NULL AUTO_INCREMENT COMMENT '帖子类型编号',
  `typeName` varchar(20) NOT NULL COMMENT '类型名称',
  `typeState` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`newsTypeID`),
  UNIQUE KEY `newsTypeID_UNIQUE` (`newsTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COMMENT='帖子类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_type`
--

INSERT INTO `news_type` VALUES (1,'校园卡丢失',1);
INSERT INTO `news_type` VALUES (2,'寻主',1);
INSERT INTO `news_type` VALUES (3,'寻物',1);
INSERT INTO `news_type` VALUES (4,'捞人',1);
INSERT INTO `news_type` VALUES (5,'求助',1);
INSERT INTO `news_type` VALUES (6,'避雷',1);
INSERT INTO `news_type` VALUES (7,'树洞',1);
INSERT INTO `news_type` VALUES (8,'指南',1);
INSERT INTO `news_type` VALUES (9,'课程',1);

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `userID` int unsigned NOT NULL COMMENT '账号',
  `userName` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(300) NOT NULL COMMENT '密码',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `headImage` char(40) NOT NULL DEFAULT '05fe71557e164b32b503708271365dfd.png' COMMENT '头像',
  `anonymousMode` tinyint(1) NOT NULL DEFAULT '0' COMMENT '匿名模式',
  `banState` tinyint(1) NOT NULL DEFAULT '0' COMMENT '封禁状态',
  `typeID` int unsigned NOT NULL DEFAULT '1' COMMENT '用户类型',
  `loginTime` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`),
  KEY `fk_user_info_user_type1_idx` (`typeID`),
  CONSTRAINT `fk_user_info_user_type1` FOREIGN KEY (`typeID`) REFERENCES `user_type` (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` VALUES (2018111090,'2018111090','111111','2649070722@qq.com','05fe71557e164b32b503708271365dfd.png',0,0,1,'2021-12-13 19:05:19');
INSERT INTO `user_info` VALUES (2018111092,'2018111092','111111','2663099526@qq.com','05fe71557e164b32b503708271365dfd.png',0,0,1,'2021-12-13 19:07:00');
INSERT INTO `user_info` VALUES (2018111095,'2018111095','111111','2663099526@qq.com','05fe71557e164b32b503708271365dfd.png',0,0,1,'2021-12-13 19:07:41');
INSERT INTO `user_info` VALUES (2018111099,'2018111099','111111','2663099526@qq.com','05fe71557e164b32b503708271365dfd.png',0,0,1,'2021-12-13 19:09:01');
INSERT INTO `user_info` VALUES (2019010101,'test-user','$2a$10$3qoBOSl5gdVckcbH.yr7Au2kaGbbvTAXndo2TMYQwCg.KF2CSmJh6','2019010101@szu.edu.cn','284752abd5054840b212eab5754df7ea.png',0,0,1,'2021-12-16 20:42:42');
INSERT INTO `user_info` VALUES (2019020202,'test-admina','$2a$10$3qoBOSl5gdVckcbH.yr7Au2kaGbbvTAXndo2TMYQwCg.KF2CSmJh6','2019020202@szu.edu.cn','c9fa77ad1fdd4f169e1894a5eb3f72c7.jpg',0,0,2,'2021-12-15 23:19:16');
INSERT INTO `user_info` VALUES (2019030303,'test-visitor','$2a$10$3qoBOSl5gdVckcbH.yr7Au2kaGbbvTAXndo2TMYQwCg.KF2CSmJh6','2019030303@szu.edu.cn','05fe71557e164b32b503708271365dfd.png',0,0,3,'2021-11-26 13:11:37');
INSERT INTO `user_info` VALUES (2019040404,'test-user-baned','$2a$10$3qoBOSl5gdVckcbH.yr7Au2kaGbbvTAXndo2TMYQwCg.KF2CSmJh6','2019040404@szu.edu.cn','05fe71557e164b32b503708271365dfd.png',0,1,1,'2021-11-26 13:11:37');
INSERT INTO `user_info` VALUES (2019050505,'test-user-ano','$2a$10$3qoBOSl5gdVckcbH.yr7Au2kaGbbvTAXndo2TMYQwCg.KF2CSmJh6','2019050505@szu.edu.cn','05fe71557e164b32b503708271365dfd.png',1,0,1,'2021-11-26 13:11:37');
INSERT INTO `user_info` VALUES (2019152010,'2019152010','$2a$10$3qoBOSl5gdVckcbH.yr7Au2kaGbbvTAXndo2TMYQwCg.KF2CSmJh6','123456789@qq.com','05fe71557e164b32b503708271365dfd.png',0,0,1,'2021-12-13 19:08:46');

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `typeID` int unsigned NOT NULL AUTO_INCREMENT COMMENT '类型编号',
  `typeName` varchar(20) NOT NULL COMMENT '类型名',
  PRIMARY KEY (`typeID`),
  UNIQUE KEY `typeID_UNIQUE` (`typeID`),
  UNIQUE KEY `typeName_UNIQUE` (`typeName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='用户类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` VALUES (1,'普通用户');
INSERT INTO `user_type` VALUES (3,'游客');
INSERT INTO `user_type` VALUES (2,'管理员');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-16 22:09:50

--
-- Trigger
--
DELIMITER $$
DROP TRIGGER IF EXISTS delete_news;
CREATE TRIGGER delete_news BEFORE DELETE
    ON news_info FOR EACH ROW
    BEGIN
        DECLARE optime datetime;
        DECLARE logsid int unsigned;
        DECLARE optype,title,content varchar(20);
        DECLARE opinfo varchar(1200);
        select count(*) into logsid from logs;
        SET logsid = logsid + 1;
        SET optime = now();
        SET optype = 'delete';
        SET title = ' title: ';
        SET content = ' content: ';
        SET opinfo = CONCAT(optype,title,old.newsTitle,content, old.content);
        INSERT INTO logs(logsID, targetID, operateType, operateInfo, operateTime)VALUES (logsid,old.newsID,optype,opinfo,optime);
    END $$

    DROP TRIGGER IF EXISTS update_news;
    CREATE TRIGGER update_news AFTER UPDATE
    ON news_info FOR EACH ROW
    BEGIN
        DECLARE optime datetime;
        DECLARE logsid int unsigned;
        DECLARE optype,title,content varchar(20);
        DECLARE opinfo varchar(1200);
        select count(*) into logsid from logs;
        SET logsid = logsid + 1;
        SET optime = now();
        SET optype = 'update';
        SET title = ' old title: ';
        SET content = ' old content: ';
        SET opinfo = CONCAT(optype,title,old.newsTitle,content, old.content);
        INSERT INTO logs(logsID, targetID, operateType, operateInfo, operateTime)VALUES (logsid,old.newsID,optype,opinfo,optime);
    END $$

    DROP TRIGGER IF EXISTS delete_user;
    CREATE TRIGGER delete_user BEFORE DELETE
    ON user_info FOR EACH ROW
    BEGIN
        DECLARE optime datetime;
        DECLARE logsid int unsigned;
        DECLARE optype,userid,username varchar(20);
        DECLARE opinfo varchar(1200);
        select count(*) into logsid from logs;
        SET logsid = logsid + 1;
        SET optime = now();
        SET optype = 'delete';
        SET userid = ' userid: ';
        SET username = ' username: ';
        SET opinfo = CONCAT(optype,userid,old.userID,username,old.userName);
        INSERT INTO logs(logsID, targetID, operateType, operateInfo, operateTime)VALUES (logsid,old.userID,optype,opinfo,optime);
    END $$

    DROP TRIGGER IF EXISTS insert_user;
    CREATE TRIGGER insert_user AFTER INSERT
    ON user_info FOR EACH ROW
    BEGIN
        DECLARE optime datetime;
        DECLARE logsid int unsigned;
        DECLARE optype,userid,username varchar(20);
        DECLARE opinfo varchar(1200);
        select count(*) into logsid from logs;
        SET logsid = logsid + 1;
        SET optime = now();
        SET optype = 'insert';
        SET userid = ' userid: ';
        SET username = ' username: ';
        SET opinfo = CONCAT(optype,userid,new.userID,username,new.userName);
        INSERT INTO logs(logsID, targetID, operateType, operateInfo, operateTime)VALUES (logsid,new.userID,optype,opinfo,optime);
        SET
    END $$
DELIMITER ;