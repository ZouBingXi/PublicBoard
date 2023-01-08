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
  `headImage` char(40) NOT NULL DEFAULT 'DefaultAvatar.jpg' COMMENT '头像',
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