SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Create database
-- ----------------------------
create database if not exists PublicBoard;

-- ----------------------------
-- Table structure for user_type
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`user_type` (
  `typeID` INT NOT NULL COMMENT '类型编号',
  `typeName` VARCHAR(20) NOT NULL COMMENT '类型名',
  PRIMARY KEY (`typeID`),
  UNIQUE INDEX `typeID_UNIQUE` (`typeID` ASC) ,
  UNIQUE INDEX `typeName_UNIQUE` (`typeName` ASC) )
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '用户类型表';

-- ----------------------------
-- Table structure for users_info
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`user_info` (
  `userID` INT UNSIGNED NOT NULL COMMENT '账号',
  `userName` VARCHAR(20) NOT NULL COMMENT '用户名',
  `password` VARCHAR(16) NOT NULL COMMENT '密码',
  `headImage` MEDIUMBLOB NOT NULL COMMENT '头像',
  `anonymousState` TINYINT NOT NULL DEFAULT '0' COMMENT '匿名状态',
  `banState` TINYINT NOT NULL DEFAULT '0' COMMENT '封禁状态',
  `userType` INT NOT NULL DEFAULT '1' COMMENT '用户类型',
  `loginTime` DATETIME NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`userID`),
  CONSTRAINT fk_userType_typeID FOREIGN KEY(userType) REFERENCES user_type(typeID),
  UNIQUE INDEX `userID_UNIQUE` (`userID` ASC))
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '用户信息表';

-- ----------------------------
-- Table structure for news_type
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`news_type` (
  `newsTypeID` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '帖子类型编号',
  `typeName` VARCHAR(20) NOT NULL COMMENT '类型名称',
  `typeState` TINYINT NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`newsTypeID`),
  UNIQUE INDEX `newsTypeID_UNIQUE` (`newsTypeID` ASC) )
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '帖子类型表';

-- ----------------------------
-- Table structure for news_info
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`news_info` (
  `newsID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '帖子编号',
  `userID` INT UNSIGNED NOT NULL COMMENT '发帖人账号',
  `newsType` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '帖子类型',
  `newsTitle` VARCHAR(30) NOT NULL COMMENT '标题',
  `content` TEXT(1000) NOT NULL COMMENT '内容',
  `sendTime` DATETIME NOT NULL COMMENT '发布时间',
  `viewNum` INT NOT NULL DEFAULT 0 COMMENT '浏览量',
  `likeNum` INT NOT NULL DEFAULT 0 COMMENT '点赞量',
  PRIMARY KEY (`newsID`),
  CONSTRAINT fk_news_userID FOREIGN KEY(userID) REFERENCES user_info(userID),
  CONSTRAINT fk_newsType FOREIGN KEY(newsType) REFERENCES news_type(newsTypeID),
  UNIQUE INDEX `newsID_UNIQUE` (`newsID` ASC))
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '帖子表';

-- ----------------------------
-- Table structure for news_state
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`news_state` (
  `newsID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '帖子编号',
  `topState` TINYINT NOT NULL DEFAULT 0 COMMENT '置顶状态',
  `hotState` TINYINT NOT NULL DEFAULT 0 COMMENT '热门状态',
  `examineState` TINYINT NOT NULL DEFAULT 0 COMMENT '审核状态',
  PRIMARY KEY (`newsID`),
  CONSTRAINT fk_newsID FOREIGN KEY(newsID) REFERENCES news_info(newsID),
  UNIQUE INDEX `newsID_UNIQUE` (`newsID` ASC))
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '帖子状态表';

-- ----------------------------
-- Table structure for news_comment
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`news_comment` (
  `commentID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `layerID` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '楼层编号',
  `newsID` INT UNSIGNED NOT NULL COMMENT '评论帖子编号',
  `userID` INT UNSIGNED NOT NULL COMMENT '评论人账号',
  `content` TEXT(1000) NOT NULL COMMENT '内容',
  `commentTime` DATETIME NOT NULL COMMENT '时间',
  `likeNum` INT NOT NULL DEFAULT 0 COMMENT '点赞量',
  PRIMARY KEY (`commentID`, `layerID`),
  CONSTRAINT fk_comment_news_userID FOREIGN KEY(userID) REFERENCES user_info(userID),
  CONSTRAINT fk_comment_newsID FOREIGN KEY(newsID) REFERENCES news_info(newsID))
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '帖子评论表';

-- ----------------------------
-- Table structure for subject_type
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`subject_type` (
  `subjectTypeID` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '话题类型编号',
  `typeName` VARCHAR(20) NOT NULL COMMENT '类型名称',
  `typeState` TINYINT NOT NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`subjectTypeID`),
  UNIQUE INDEX `newsTypeID_UNIQUE` (`subjectTypeID` ASC))
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '话题类型表';

-- ----------------------------
-- Table structure for subject_info
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`subject_info` (
  `subjectID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '话题编号',
  `userID` INT UNSIGNED NOT NULL COMMENT '发帖人账号',
  `subjectType` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '话题类型',
  `subjectTitle` VARCHAR(30) NOT NULL COMMENT '标题',
  `content` TEXT(1000) NOT NULL COMMENT '内容',
  `sendTime` DATETIME NOT NULL COMMENT '发布时间',
  `viewNum` INT NOT NULL DEFAULT 0 COMMENT '浏览量',
  `likeNum` INT NOT NULL DEFAULT 0 COMMENT '点赞量',
  PRIMARY KEY (`subjectID`),
  CONSTRAINT fk_subject_userID FOREIGN KEY(userID) REFERENCES user_info(userID),
  CONSTRAINT fk_subjectType FOREIGN KEY(subjectType) REFERENCES subject_type(subjectTypeID),
  UNIQUE INDEX `newsID_UNIQUE` (`subjectID` ASC))
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '话题表';

-- ----------------------------
-- Table structure for subject_state
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`subject_state` (
  `subjectID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '话题编号',
  `topState` TINYINT NOT NULL DEFAULT 0 COMMENT '置顶状态',
  `hotState` TINYINT NOT NULL DEFAULT 0 COMMENT '热门状态',
  `examineState` TINYINT NOT NULL DEFAULT 0 COMMENT '审核状态',
  PRIMARY KEY (`subjectID`),
  CONSTRAINT fk_subjectID FOREIGN KEY(subjectID) REFERENCES subject_info(subjectID),
  UNIQUE INDEX `newsID_UNIQUE` (`subjectID` ASC))
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '话题状态表';

-- ----------------------------
-- Table structure for subject_comment
-- ----------------------------
CREATE TABLE IF NOT EXISTS `PublicBoard`.`subject_comment` (
  `commentID` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `layerID` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '楼层编号',
  `subjectID` INT UNSIGNED NOT NULL COMMENT '话题帖子编号',
  `userID` INT UNSIGNED NOT NULL COMMENT '评论人账号',
  `content` TEXT(1000) NOT NULL COMMENT '内容',
  `commentTime` DATETIME NOT NULL COMMENT '时间',
  `likeNum` INT NOT NULL DEFAULT 0 COMMENT '点赞量',
  PRIMARY KEY (`commentID`, `layerID`),
  CONSTRAINT fk_comment_subject_userID FOREIGN KEY(userID) REFERENCES user_info(userID),
  CONSTRAINT fk_comment_subjectID FOREIGN KEY(subjectID) REFERENCES subject_info(subjectID))
ENGINE = InnoDB DEFAULT CHARACTER SET utf8
COMMENT = '话题评论表';

-- ----------------------------
-- initialize database data
-- ----------------------------
INSERT INTO `PublicBoard`.`user_type` (`typeID`, `typeName`) VALUES ('1', '管理员'), ('2', '普通用户');

INSERT INTO `PublicBoard`.`user_info` (`userID`, `userName`, `password`, `headImage`, `anonymousState`, `banState`, `userType`, `loginTime`)
VALUES ('2019010101', 'admin1', '1234', 0x3c3f786d6c2076657273696f6e3d22312e3022207374616e64616c6f6e653d226e6f223f3e3c21444f435459504520737667205055424c494320222d2f2f5733432f2f4454442053564720312e312f2f454e222022687474703a2f2f7777772e77332e6f72672f47726170686963732f5356472f312e312f4454442f73766731312e647464223e3c73766720636c6173733d2269636f6e222077696474683d22323030707822206865696768743d223230302e30307078222076696577426f783d2230203020313032342031303234222076657273696f6e3d22312e312220786d6c6e733d22687474703a2f2f7777772e77332e6f72672f323030302f737667223e3c706174682066696c6c3d22233333333333332220643d224d3531322037342e363636363637433237302e3933333333332037342e3636363636372037342e363636363637203237302e3933333333332037342e36363636363720353132533237302e393333333333203934392e33333333333320353132203934392e333333333333203934392e333333333333203735332e303636363637203934392e33333333333320353132203735332e3036363636372037342e363636363637203531322037342e3636363636377a4d323838203831302e36363636363763302d3132332e373333333333203130302e3236363636372d323234203232342d32323453373336203638362e39333333333320373336203831302e363636363637632d36312e3836363636372034362e3933333333332d3134302e382037342e3636363636372d3232342037342e363636363636732d3136322e3133333333332d32372e3733333333332d3232342d37342e3636363636367a206d3132382d33383463302d35332e3333333333332034322e3636363636372d39362039362d39367339362034322e3636363636372039362039362d34322e3636363636372039362d39362039362d39362d34322e3636363636372d39362d39367a206d3337372e36203332382e353333333333632d31392e322d39362d38352e3333333333332d3137342e3933333333332d3137342e3933333333332d3231312e322033322d32392e3836363636372035312e322d37302e342035312e322d3131372e33333333333320302d38372e3436363636372d37322e3533333333332d3136302d3136302d313630732d3136302037322e3533333333332d3136302031363063302034362e3933333333332031392e322038372e3436363636372035312e32203131372e3333333333332d38392e362033362e3236363636372d3135352e373333333333203131352e322d3137342e393333333334203231312e322d35352e3436363636372d36362e3133333333332d39312e3733333333332d3134392e3333333333332d39312e3733333333332d3234332e3220302d3230342e38203136382e3533333333332d3337332e333333333333203337332e3333333333332d3337332e333333333333533838352e333333333333203330372e32203838352e3333333333332035313263302039332e3836363636372d33342e313333333333203137372e3036363636372d39312e373333333333203234332e327a22202f3e3c2f7376673e, '0', '0', '1', '2021-11-13 11:21:08');

INSERT INTO `PublicBoard`.`news_type` (`newsTypeID`, `typeName`, `typeState`)
VALUES ('0', '校园卡丢失', '1'), ('1', '寻物', '1'), ('2', '寻主', '1'), ('3', '捞人', '1'), ('4', '求助', '1');

INSERT INTO `PublicBoard`.`news_info` (`newsID`, `userID`, `newsType`, `newsTitle`, `content`, `sendTime`, `viewNum`, `likeNum`)
VALUES ('1', '2019010101', '0', '测试帖子', '内容：测试帖子功能', '2021-11-13 14:00:12', '0', '0');

INSERT INTO `PublicBoard`.`news_state` (`newsID`, `topState`, `hotState`, `examineState`)
VALUES ('1', '0', '0', '0');

INSERT INTO `PublicBoard`.`news_comment` (`commentID`, `layerID`, `newsID`, `userID`, `content`, `commentTime`, `likeNum`)
VALUES ('1', '0', '1', '2019010101', '测试帖子评论功能', '2021-11-13 11:00:00', '0');

INSERT INTO `PublicBoard`.`subject_type` (`subjectTypeID`, `typeName`, `typeState`)
VALUES ('0', '避雷', '1'), ('1', '树洞', '1'), ('2', '指南', '1'), ('3', '课程', '1');

INSERT INTO `PublicBoard`.`subject_info` (`subjectID`, `userID`, `subjectType`, `subjectTitle`, `content`, `sendTime`, `viewNum`, `likeNum`)
VALUES ('1', '2019010101', '0', '测试话题', '内容：测试话题功能', '2021-11-13 14:00:12', '0', '0');

INSERT INTO `PublicBoard`.`subject_state` (`subjectID`, `topState`, `hotState`, `examineState`)
VALUES ('1', '0', '0', '0');

INSERT INTO `PublicBoard`.`subject_comment` (`commentID`, `layerID`, `subjectID`, `userID`, `content`, `commentTime`, `likeNum`)
VALUES ('1', '0', '1', '2019010101', '测试话题评论功能', '2021-11-13 11:00:00', '0');


