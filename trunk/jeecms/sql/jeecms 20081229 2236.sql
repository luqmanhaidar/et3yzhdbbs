-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema jeecms
--

CREATE DATABASE IF NOT EXISTS jeecms;
USE jeecms;

--
-- Definition of table `arti_article`
--

DROP TABLE IF EXISTS `arti_article`;
CREATE TABLE `arti_article` (
  `ARTICLE_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `ARTICTG_ID` bigint(20) NOT NULL,
  `NEXT_ID` bigint(20) default NULL COMMENT '下一篇',
  `PRE_ID` bigint(20) default NULL COMMENT '上一篇',
  `GROUP_ID` bigint(20) default NULL COMMENT '允许浏览会员组',
  `CHANNEL_ID` bigint(20) NOT NULL COMMENT '栏目ID',
  `MEMBER_ID` bigint(20) default NULL COMMENT '录入会员',
  `ADMIN_CHECK` bigint(20) default NULL COMMENT '审核管理员',
  `ADMIN_DISABLE` bigint(20) default NULL COMMENT '禁用管理员',
  `ADMIN_INPUT` bigint(20) default NULL COMMENT '录入管理员',
  `TITLE` varchar(255) default NULL COMMENT '文章标题',
  `SHORT_TITLE` varchar(255) default NULL COMMENT '简短标题（用于栏目或首页索引）',
  `TITLE_COLOR` varchar(10) default NULL COMMENT '标题颜色',
  `TITLE_IMG` varchar(100) default NULL COMMENT '文章标题缩略图',
  `DESCRIPTION` varchar(255) default NULL COMMENT '文章描述',
  `TAGS` varchar(255) default NULL COMMENT '标记，类似关键字',
  `AUTHOR` varchar(100) default NULL COMMENT '作者',
  `ORIGIN` varchar(100) default NULL COMMENT '来源',
  `RELEASE_DATE` datetime default NULL COMMENT '发表日期（可人为设置）',
  `RELEASE_SYS_DATE` datetime default NULL COMMENT '发布日期（系统日期）',
  `CHECK_TIME` datetime default NULL COMMENT '终审通过时间',
  `DISABLE_TIME` datetime default NULL COMMENT '禁用时间',
  `VISIT_TOTAL` bigint(20) default NULL COMMENT '总共访问次数',
  `VISIT_TODAY` bigint(20) default NULL COMMENT '当天访问次数',
  `STAT_DATE` date default NULL COMMENT '统计时间',
  `OUTER_URL` varchar(255) default NULL COMMENT '外部链接',
  `CONTENT_RES_PATH` varchar(200) default NULL COMMENT '文章内容的资源路径',
  `PAGE_COUNT` int(11) default NULL COMMENT '文章总页数',
  `TPL_CONTENT` varchar(100) default NULL COMMENT '指定模板',
  `CHECK_STEP` int(11) default NULL COMMENT '审核级数',
  `CHECK_OPINION` varchar(255) default NULL COMMENT '审核意见',
  `HAS_TITLEIMG` tinyint(1) NOT NULL COMMENT '是否有标题图片',
  `IS_BOLD` tinyint(1) NOT NULL COMMENT '是否加粗',
  `IS_DRAFT` tinyint(1) NOT NULL COMMENT '是否草稿',
  `IS_RECOMMEND` tinyint(1) NOT NULL COMMENT '是否推荐',
  `IS_CHECK` tinyint(1) NOT NULL COMMENT '是否审核',
  `IS_DISABLED` tinyint(1) NOT NULL COMMENT '是否禁用',
  `IS_REJECT` tinyint(1) NOT NULL COMMENT '是否驳回',
  `PARAM1` varchar(255) default NULL COMMENT '文章相关数据1',
  `PARAM2` varchar(255) default NULL COMMENT '文章相关数据2',
  `PARAM3` varchar(255) default NULL COMMENT '文章相关数据3',
  PRIMARY KEY  (`ARTICLE_ID`),
  KEY `FK_ARTICLE_ARTICTG` (`ARTICTG_ID`),
  KEY `FK_ARTICLE_CHANNEL` (`CHANNEL_ID`),
  KEY `FK_ARTICLE_CHECK_CADMIN` (`ADMIN_CHECK`),
  KEY `FK_ARTICLE_CMEMBER` (`MEMBER_ID`),
  KEY `FK_ARTICLE_DISABLE_CADMIN` (`ADMIN_DISABLE`),
  KEY `FK_ARTICLE_INPUT_CADMIN` (`ADMIN_INPUT`),
  KEY `FK_ARTICLE_MGROUP` (`GROUP_ID`),
  KEY `FK_ARTICLE_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_ARTICLE_ARTICTG` FOREIGN KEY (`ARTICTG_ID`) REFERENCES `arti_ctg` (`ARTICTG_ID`),
  CONSTRAINT `FK_ARTICLE_CHANNEL` FOREIGN KEY (`CHANNEL_ID`) REFERENCES `cms_channel` (`CHANNEL_ID`),
  CONSTRAINT `FK_ARTICLE_CHECK_CADMIN` FOREIGN KEY (`ADMIN_CHECK`) REFERENCES `cms_admin` (`ADMIN_ID`),
  CONSTRAINT `FK_ARTICLE_CMEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `cms_member` (`MEMBER_ID`),
  CONSTRAINT `FK_ARTICLE_DISABLE_CADMIN` FOREIGN KEY (`ADMIN_DISABLE`) REFERENCES `cms_admin` (`ADMIN_ID`),
  CONSTRAINT `FK_ARTICLE_INPUT_CADMIN` FOREIGN KEY (`ADMIN_INPUT`) REFERENCES `cms_admin` (`ADMIN_ID`),
  CONSTRAINT `FK_ARTICLE_MGROUP` FOREIGN KEY (`GROUP_ID`) REFERENCES `cms_member_group` (`GROUP_ID`),
  CONSTRAINT `FK_ARTICLE_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `arti_article`
--

/*!40000 ALTER TABLE `arti_article` DISABLE KEYS */;
INSERT INTO `arti_article` (`ARTICLE_ID`,`WEBSITE_ID`,`ARTICTG_ID`,`NEXT_ID`,`PRE_ID`,`GROUP_ID`,`CHANNEL_ID`,`MEMBER_ID`,`ADMIN_CHECK`,`ADMIN_DISABLE`,`ADMIN_INPUT`,`TITLE`,`SHORT_TITLE`,`TITLE_COLOR`,`TITLE_IMG`,`DESCRIPTION`,`TAGS`,`AUTHOR`,`ORIGIN`,`RELEASE_DATE`,`RELEASE_SYS_DATE`,`CHECK_TIME`,`DISABLE_TIME`,`VISIT_TOTAL`,`VISIT_TODAY`,`STAT_DATE`,`OUTER_URL`,`CONTENT_RES_PATH`,`PAGE_COUNT`,`TPL_CONTENT`,`CHECK_STEP`,`CHECK_OPINION`,`HAS_TITLEIMG`,`IS_BOLD`,`IS_DRAFT`,`IS_RECOMMEND`,`IS_CHECK`,`IS_DISABLED`,`IS_REJECT`,`PARAM1`,`PARAM2`,`PARAM3`) VALUES 
 (1,1,1,2,NULL,NULL,3,NULL,NULL,NULL,1,'诺基亚携手美国ETS创考试英语学习新方式','诺基亚携手美国ETS创考试英语学习新方式','','','诺基亚携手美国ETS创考试英语学习新方式','','nc138','','2008-12-24 16:23:29','2008-12-24 15:24:16',NULL,NULL,13,0,'2008-12-24','',NULL,2,NULL,NULL,NULL,0,0,0,0,1,0,0,'','',''),
 (2,1,5,3,1,NULL,2,NULL,NULL,NULL,1,'JEECMS v2.1 beta版发布公告','JEECMS v2.1 beta版发布公告','#FF0000','','在JEECMS网友们的热切期盼和JEECMS开发人员的不懈努力下，JEECMS v2.1 beta版今天正式发布了!','','JEECMS','','2008-12-24 16:34:05','2008-12-24 15:46:19',NULL,NULL,3,0,'2008-12-24','',NULL,1,NULL,NULL,NULL,0,1,0,1,1,0,0,'','',''),
 (3,1,5,4,2,NULL,2,NULL,NULL,NULL,1,'JEECMS全体开发人员祝大家圣诞快乐！','JEECMS全体开发人员祝大家圣诞快乐！','','','JEECMS全体开发人员祝大家圣诞快乐！','','JEECMS','','2008-12-24 15:51:17','2008-12-24 15:50:41',NULL,NULL,3,0,'2008-12-24','',NULL,1,NULL,NULL,NULL,0,0,0,0,1,0,0,'','','');
INSERT INTO `arti_article` (`ARTICLE_ID`,`WEBSITE_ID`,`ARTICTG_ID`,`NEXT_ID`,`PRE_ID`,`GROUP_ID`,`CHANNEL_ID`,`MEMBER_ID`,`ADMIN_CHECK`,`ADMIN_DISABLE`,`ADMIN_INPUT`,`TITLE`,`SHORT_TITLE`,`TITLE_COLOR`,`TITLE_IMG`,`DESCRIPTION`,`TAGS`,`AUTHOR`,`ORIGIN`,`RELEASE_DATE`,`RELEASE_SYS_DATE`,`CHECK_TIME`,`DISABLE_TIME`,`VISIT_TOTAL`,`VISIT_TODAY`,`STAT_DATE`,`OUTER_URL`,`CONTENT_RES_PATH`,`PAGE_COUNT`,`TPL_CONTENT`,`CHECK_STEP`,`CHECK_OPINION`,`HAS_TITLEIMG`,`IS_BOLD`,`IS_DRAFT`,`IS_RECOMMEND`,`IS_CHECK`,`IS_DISABLED`,`IS_REJECT`,`PARAM1`,`PARAM2`,`PARAM3`) VALUES 
 (4,1,2,5,3,NULL,4,NULL,NULL,NULL,1,'阿尔卑斯山脉浓厚的晨雾','阿尔卑斯山','','/article/image/2008_4/12_24/nbaafp3p1f9f.jpg','阿尔卑斯山脉浓厚的晨雾','','163','','2008-12-24 15:58:49','2008-12-24 15:58:49',NULL,NULL,2,0,'2008-12-24','',NULL,1,NULL,NULL,NULL,1,0,0,0,1,0,0,'','',''),
 (5,1,2,6,4,NULL,4,NULL,NULL,NULL,1,'一年一度的Brandenburg圣诞老人游行上的参与者们','圣诞老人游行','','/article/image/2008_4/12_24/dfecfp3p8pyv.jpg','一年一度的Brandenburg圣诞老人游行上的参与者们','','163','','2008-12-24 16:04:25','2008-12-24 16:04:25',NULL,NULL,2,0,'2008-12-24','',NULL,1,NULL,NULL,NULL,1,0,0,0,1,0,0,'','',''),
 (6,1,2,7,5,NULL,6,NULL,NULL,NULL,1,'晒晒你见过的礼仪小姐训练','礼仪小姐训练','','/article/image/2008_4/12_24/z521fp3pe4dl.jpg','晒晒你见过的礼仪小姐训练','','163','','2008-12-24 16:15:29','2008-12-24 16:08:12',NULL,NULL,4,0,'2008-12-24','',NULL,1,NULL,NULL,NULL,1,0,0,0,1,0,0,'','','');
INSERT INTO `arti_article` (`ARTICLE_ID`,`WEBSITE_ID`,`ARTICTG_ID`,`NEXT_ID`,`PRE_ID`,`GROUP_ID`,`CHANNEL_ID`,`MEMBER_ID`,`ADMIN_CHECK`,`ADMIN_DISABLE`,`ADMIN_INPUT`,`TITLE`,`SHORT_TITLE`,`TITLE_COLOR`,`TITLE_IMG`,`DESCRIPTION`,`TAGS`,`AUTHOR`,`ORIGIN`,`RELEASE_DATE`,`RELEASE_SYS_DATE`,`CHECK_TIME`,`DISABLE_TIME`,`VISIT_TOTAL`,`VISIT_TODAY`,`STAT_DATE`,`OUTER_URL`,`CONTENT_RES_PATH`,`PAGE_COUNT`,`TPL_CONTENT`,`CHECK_STEP`,`CHECK_OPINION`,`HAS_TITLEIMG`,`IS_BOLD`,`IS_DRAFT`,`IS_RECOMMEND`,`IS_CHECK`,`IS_DISABLED`,`IS_REJECT`,`PARAM1`,`PARAM2`,`PARAM3`) VALUES 
 (7,1,2,8,6,NULL,5,NULL,NULL,NULL,1,'晒晒你见过的美女交警','美女交警','','/article/image/2008_4/12_24/om60fp3phsh8.jpg','晒晒你见过的美女交警','','网易','','2008-12-24 16:14:59','2008-12-24 16:13:14',NULL,NULL,4,0,'2008-12-24','',NULL,2,NULL,NULL,NULL,1,0,0,0,1,0,0,'','',''),
 (8,1,3,9,7,NULL,4,NULL,NULL,NULL,1,'驻阿英军头戴圣诞帽与塔利班交火','驻阿英军头戴圣诞帽与塔利班交火','','/article/image/2008_4/12_24/fpadfp3pt95z.jpg','驻阿英军头戴圣诞帽与塔利班交火','','网易','','2008-12-24 16:22:12','2008-12-24 16:20:04',NULL,NULL,1,0,'2008-12-24','',NULL,1,NULL,NULL,NULL,1,0,0,0,1,0,0,'','',''),
 (9,1,3,NULL,8,NULL,7,NULL,NULL,NULL,1,'周迅:圣诞跟李大齐雪中漫步','明星圣诞爆料 周迅与男友雪中漫步','','/article/image/2008_4/12_24/6lipfp3q39mz.jpg','明星圣诞爆料 周迅与男友雪中漫步 ','','korvrn','网易','2008-12-24 16:34:28','2008-12-24 16:29:41',NULL,NULL,1,0,'2008-12-24','',NULL,1,NULL,NULL,NULL,1,0,0,1,1,0,0,'','','');
/*!40000 ALTER TABLE `arti_article` ENABLE KEYS */;


--
-- Definition of table `arti_check_log`
--

DROP TABLE IF EXISTS `arti_check_log`;
CREATE TABLE `arti_check_log` (
  `ACLOG_ID` bigint(20) NOT NULL auto_increment,
  `ARTICLE_ID` bigint(20) NOT NULL,
  `ADMIN_ID` bigint(20) default NULL,
  `CHECK_STEP` int(11) default NULL COMMENT '审核级数',
  `CHECK_OPINION` varchar(255) default NULL COMMENT '审核意见',
  `CHECK_TIME` datetime default NULL COMMENT '审核时间',
  `IP` varchar(20) default NULL COMMENT '操作员IP',
  `IS_PASS` tinyint(1) NOT NULL COMMENT '通过或驳回',
  `IS_FINAL` tinyint(1) NOT NULL,
  PRIMARY KEY  (`ACLOG_ID`),
  KEY `FK_ACLOG_ADMIN` (`ADMIN_ID`),
  KEY `FK_ACLOG_ARTICLE` (`ARTICLE_ID`),
  CONSTRAINT `FK_ACLOG_ADMIN` FOREIGN KEY (`ADMIN_ID`) REFERENCES `cms_admin` (`ADMIN_ID`),
  CONSTRAINT `FK_ACLOG_ARTICLE` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `arti_article` (`ARTICLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='审核日志';

--
-- Dumping data for table `arti_check_log`
--

/*!40000 ALTER TABLE `arti_check_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `arti_check_log` ENABLE KEYS */;


--
-- Definition of table `arti_ctg`
--

DROP TABLE IF EXISTS `arti_ctg`;
CREATE TABLE `arti_ctg` (
  `ARTICTG_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `LABEL` varchar(20) NOT NULL COMMENT '标识ID',
  `NAME` varchar(50) default NULL COMMENT '名称',
  `IMG_WIDTH` int(11) default '139' COMMENT '标题图片宽度',
  `IMG_HEIGHT` int(11) default '139' COMMENT '标题图片高度',
  `HAS_TITLEIMG` tinyint(1) NOT NULL default '1' COMMENT '是否有标题图片',
  `IS_DISABLED` tinyint(1) NOT NULL default '0' COMMENT '是否继续使用',
  PRIMARY KEY  (`ARTICTG_ID`),
  KEY `FK_ARTICTG_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_ARTICTG_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='文章的属性';

--
-- Dumping data for table `arti_ctg`
--

/*!40000 ALTER TABLE `arti_ctg` DISABLE KEYS */;
INSERT INTO `arti_ctg` (`ARTICTG_ID`,`WEBSITE_ID`,`LABEL`,`NAME`,`IMG_WIDTH`,`IMG_HEIGHT`,`HAS_TITLEIMG`,`IS_DISABLED`) VALUES 
 (1,1,'1','普通',139,94,1,0),
 (2,1,'2','图文',139,94,1,0),
 (3,1,'3','焦点',296,200,1,0),
 (4,1,'4','头条',100,68,1,0),
 (5,1,'5','滚动',139,94,1,0);
/*!40000 ALTER TABLE `arti_ctg` ENABLE KEYS */;


--
-- Definition of table `auxi_config`
--

DROP TABLE IF EXISTS `auxi_config`;
CREATE TABLE `auxi_config` (
  `CONFIG_ID` bigint(20) NOT NULL,
  `MSG_NEED_CHECK` tinyint(1) default '0' COMMENT '留言板是否需要审核',
  `MSG_IS_OPEN` tinyint(1) default '1' COMMENT '留言板是否开放',
  `MSG_ANONYMITY` varchar(20) default '匿名网友' COMMENT '留言板匿名网友名称',
  `MSG_MAX_SIZE` int(11) default '16384' COMMENT '留言板留言大小(字节)',
  PRIMARY KEY  (`CONFIG_ID`),
  CONSTRAINT `FK_AUXICONFIG_WEBSITE` FOREIGN KEY (`CONFIG_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='辅助系统配置表';

--
-- Dumping data for table `auxi_config`
--

/*!40000 ALTER TABLE `auxi_config` DISABLE KEYS */;
INSERT INTO `auxi_config` (`CONFIG_ID`,`MSG_NEED_CHECK`,`MSG_IS_OPEN`,`MSG_ANONYMITY`,`MSG_MAX_SIZE`) VALUES 
 (1,0,1,'匿名网友',16384);
/*!40000 ALTER TABLE `auxi_config` ENABLE KEYS */;


--
-- Definition of table `auxi_msg`
--

DROP TABLE IF EXISTS `auxi_msg`;
CREATE TABLE `auxi_msg` (
  `MSG_ID` bigint(20) NOT NULL auto_increment,
  `ADMIN_ID` bigint(20) default NULL,
  `MSGCTG_ID` bigint(20) NOT NULL,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `MEMBER_ID` bigint(20) default NULL,
  `CONFIG_ID` bigint(20) NOT NULL,
  `TITLE` varchar(200) default NULL COMMENT '留言标题',
  `CONTENT_MEMBER` longtext COMMENT '留言内容',
  `CONTENT_ADMIN` longtext COMMENT '回复内容',
  `EMAIL` varchar(100) default NULL COMMENT '电子邮箱',
  `PHONE` varchar(100) default NULL COMMENT '联系电话',
  `QQ` varchar(50) default NULL COMMENT 'QQ',
  `IP` varchar(20) default NULL COMMENT '留言IP',
  `CREATE_TIME` datetime default NULL COMMENT '留言时间',
  `REPLY_TIME` datetime default NULL COMMENT '回复时间',
  `IS_CHECK` tinyint(1) NOT NULL COMMENT '是否审核',
  `IS_RECOMMEND` tinyint(1) NOT NULL COMMENT '是否推荐',
  `IS_DISABLED` tinyint(1) NOT NULL COMMENT '是否禁用',
  PRIMARY KEY  (`MSG_ID`),
  KEY `FK_AMSG_ADMIN` (`ADMIN_ID`),
  KEY `FK_AMSG_MEMBER` (`MEMBER_ID`),
  KEY `FK_AMSG_MSGCTG` (`MSGCTG_ID`),
  KEY `FK_AMSG_WEBSITE` (`WEBSITE_ID`),
  KEY `FK_AUXIMSG_CONFIG` (`CONFIG_ID`),
  CONSTRAINT `FK_AMSG_ADMIN` FOREIGN KEY (`ADMIN_ID`) REFERENCES `core_admin` (`ADMIN_ID`),
  CONSTRAINT `FK_AMSG_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `core_member` (`MEMBER_ID`),
  CONSTRAINT `FK_AMSG_MSGCTG` FOREIGN KEY (`MSGCTG_ID`) REFERENCES `auxi_msg_ctg` (`MSGCTG_ID`),
  CONSTRAINT `FK_AMSG_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`),
  CONSTRAINT `FK_AUXIMSG_CONFIG` FOREIGN KEY (`CONFIG_ID`) REFERENCES `auxi_config` (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='留言板留言';

--
-- Dumping data for table `auxi_msg`
--

/*!40000 ALTER TABLE `auxi_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `auxi_msg` ENABLE KEYS */;


--
-- Definition of table `auxi_msg_ctg`
--

DROP TABLE IF EXISTS `auxi_msg_ctg`;
CREATE TABLE `auxi_msg_ctg` (
  `MSGCTG_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `NAME` varchar(50) default NULL,
  `DESCRIPTION` varchar(250) default NULL,
  PRIMARY KEY  (`MSGCTG_ID`),
  KEY `FK_AMSGCTG_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_AMSGCTG_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='留言类别';

--
-- Dumping data for table `auxi_msg_ctg`
--

/*!40000 ALTER TABLE `auxi_msg_ctg` DISABLE KEYS */;
INSERT INTO `auxi_msg_ctg` (`MSGCTG_ID`,`WEBSITE_ID`,`NAME`,`DESCRIPTION`) VALUES 
 (1,1,'普通留言',NULL);
/*!40000 ALTER TABLE `auxi_msg_ctg` ENABLE KEYS */;


--
-- Definition of table `cms_admin`
--

DROP TABLE IF EXISTS `cms_admin`;
CREATE TABLE `cms_admin` (
  `ADMIN_ID` bigint(20) NOT NULL,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `CHECK_RIGHT` int(11) default '0' COMMENT '审核级别（第几审）',
  PRIMARY KEY  (`ADMIN_ID`),
  KEY `FK_CADMIN_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_CADMIN_ADMIN` FOREIGN KEY (`ADMIN_ID`) REFERENCES `core_admin` (`ADMIN_ID`),
  CONSTRAINT `FK_CADMIN_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='jeecms管理员';

--
-- Dumping data for table `cms_admin`
--

/*!40000 ALTER TABLE `cms_admin` DISABLE KEYS */;
INSERT INTO `cms_admin` (`ADMIN_ID`,`WEBSITE_ID`,`CHECK_RIGHT`) VALUES 
 (1,1,0);
/*!40000 ALTER TABLE `cms_admin` ENABLE KEYS */;


--
-- Definition of table `cms_channel`
--

DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `CHANNEL_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `PARENT` bigint(20) default NULL COMMENT '父栏目',
  `MODEL_ID` bigint(20) NOT NULL,
  `SYS_TYPE` varchar(20) NOT NULL COMMENT '系统类型',
  `PATH` varchar(100) default NULL COMMENT '栏目路径',
  `NAME` varchar(100) default NULL COMMENT '栏目名称',
  `TITLE_IMG` varchar(100) default NULL COMMENT '栏目图片',
  `TPL_INDEX` varchar(100) default NULL COMMENT '栏目页模板路径',
  `TPL_CONTENT` varchar(100) default NULL COMMENT '内容页模板路径',
  `TITLE` varchar(255) default NULL COMMENT 'TITLE',
  `KEYWORDS` varchar(255) default NULL COMMENT 'KEYWORDS',
  `DESCRIPTION` varchar(255) default NULL COMMENT 'DESCRIPTION',
  `VISIT_TOTAL` bigint(20) default NULL COMMENT '总共访问次数',
  `VISIT_TODAY` bigint(20) default NULL COMMENT '当天访问次数',
  `STAT_DATE` date default NULL COMMENT '统计日期',
  `OUTER_URL` varchar(255) default NULL COMMENT '外部链接',
  `CONTRIBUTE_LEVEL` int(11) default NULL COMMENT '允许哪一级别会员投稿',
  `PRIORITY` int(11) NOT NULL COMMENT '栏目排列顺序',
  `HAS_CHILD` tinyint(1) NOT NULL COMMENT '是否可以有子节点',
  `IS_DISPLAY` tinyint(1) NOT NULL COMMENT '是否显示',
  `PARAM1` varchar(255) default NULL COMMENT '栏目相关数据1',
  `PARAM2` varchar(255) default NULL COMMENT '栏目相关数据2',
  `PARAM3` varchar(255) default NULL COMMENT '栏目相关数据3',
  PRIMARY KEY  (`CHANNEL_ID`),
  KEY `FK_CCHANNEL_CHNLMODEL` (`MODEL_ID`),
  KEY `FK_CHANNEL_WEBSITE` (`WEBSITE_ID`),
  KEY `FK_CHANNL_PARENT` (`PARENT`),
  CONSTRAINT `FK_CCHANNEL_CHNLMODEL` FOREIGN KEY (`MODEL_ID`) REFERENCES `cms_chnl_model` (`MODEL_ID`),
  CONSTRAINT `FK_CHANNEL_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`),
  CONSTRAINT `FK_CHANNL_PARENT` FOREIGN KEY (`PARENT`) REFERENCES `cms_channel` (`CHANNEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `cms_channel`
--

/*!40000 ALTER TABLE `cms_channel` DISABLE KEYS */;
INSERT INTO `cms_channel` (`CHANNEL_ID`,`WEBSITE_ID`,`PARENT`,`MODEL_ID`,`SYS_TYPE`,`PATH`,`NAME`,`TITLE_IMG`,`TPL_INDEX`,`TPL_CONTENT`,`TITLE`,`KEYWORDS`,`DESCRIPTION`,`VISIT_TOTAL`,`VISIT_TODAY`,`STAT_DATE`,`OUTER_URL`,`CONTRIBUTE_LEVEL`,`PRIORITY`,`HAS_CHILD`,`IS_DISPLAY`,`PARAM1`,`PARAM2`,`PARAM3`) VALUES 
 (1,1,NULL,9,'article','main','首页',NULL,'',NULL,'JEECMS演示站','JEECMS演示站','JEECMS演示站',6,0,'2008-12-24',NULL,2147483647,100,1,1,'','',''),
 (2,1,1,2,'article','notice','最新公告',NULL,'','','最新公告','最新公告','最新公告',0,0,'2008-12-24',NULL,1000,10,1,0,'','',''),
 (3,1,1,2,'article','gnxw','国内新闻',NULL,'/article/default/channel_level2.html','','国内新闻','国内新闻','国内新闻',4,0,'2008-12-24',NULL,1000,10,1,1,'','',''),
 (4,1,1,2,'article','gwxw','国外新闻',NULL,'','','国外新闻','国外新闻','国外新闻',0,0,'2008-12-24',NULL,1000,10,1,1,'','',''),
 (5,1,3,2,'article','shxw','社会新闻',NULL,'','','社会新闻','社会新闻','社会新闻',2,0,'2008-12-24',NULL,1000,10,1,1,'','',''),
 (6,1,3,2,'article','tyxw','体育新闻',NULL,'','','体育新闻','体育新闻','体育新闻',0,0,'2008-12-24',NULL,1000,10,1,1,'','','');
INSERT INTO `cms_channel` (`CHANNEL_ID`,`WEBSITE_ID`,`PARENT`,`MODEL_ID`,`SYS_TYPE`,`PATH`,`NAME`,`TITLE_IMG`,`TPL_INDEX`,`TPL_CONTENT`,`TITLE`,`KEYWORDS`,`DESCRIPTION`,`VISIT_TOTAL`,`VISIT_TODAY`,`STAT_DATE`,`OUTER_URL`,`CONTRIBUTE_LEVEL`,`PRIORITY`,`HAS_CHILD`,`IS_DISPLAY`,`PARAM1`,`PARAM2`,`PARAM3`) VALUES 
 (7,1,3,2,'article','ylxw','娱乐新闻',NULL,'','','娱乐新闻','娱乐新闻','娱乐新闻',0,0,'2008-12-24',NULL,1000,10,1,1,'','',''),
 (8,1,1,7,'article','about','关于我们',NULL,'/article/default/alone_about.html',NULL,'关于我们','关于我们','关于我们',0,0,'2008-12-24',NULL,2147483647,10,0,0,'','',''),
 (9,1,1,8,'article',NULL,'留言板',NULL,NULL,NULL,NULL,NULL,NULL,0,0,'2008-12-24','/jeecms/Guestbook.jspx',2147483647,10,0,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `cms_channel` ENABLE KEYS */;


--
-- Definition of table `cms_chnl_model`
--

DROP TABLE IF EXISTS `cms_chnl_model`;
CREATE TABLE `cms_chnl_model` (
  `MODEL_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `CONFIG_ID` bigint(20) NOT NULL,
  `SYS_TYPE` varchar(20) NOT NULL COMMENT '系统类型',
  `NAME` varchar(100) default NULL COMMENT '模型名称',
  `SHORT_NAME` varchar(20) default NULL COMMENT '简称',
  `TPL_PREFIX_CHANNEL` varchar(20) default NULL COMMENT '栏目模板前缀',
  `TPL_PREFIX_CONTENT` varchar(20) default NULL COMMENT '内容模板前缀',
  `HAS_CHILD` tinyint(1) NOT NULL default '1' COMMENT '是否可以有子栏目',
  PRIMARY KEY  (`MODEL_ID`),
  KEY `FK_CHNLMODEL_CONFIG` (`CONFIG_ID`),
  KEY `FK_CHNLMODEL_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_CHNLMODEL_CONFIG` FOREIGN KEY (`CONFIG_ID`) REFERENCES `cms_config` (`CONFIG_ID`),
  CONSTRAINT `FK_CHNLMODEL_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='栏目模型';

--
-- Dumping data for table `cms_chnl_model`
--

/*!40000 ALTER TABLE `cms_chnl_model` DISABLE KEYS */;
INSERT INTO `cms_chnl_model` (`MODEL_ID`,`WEBSITE_ID`,`CONFIG_ID`,`SYS_TYPE`,`NAME`,`SHORT_NAME`,`TPL_PREFIX_CHANNEL`,`TPL_PREFIX_CONTENT`,`HAS_CHILD`) VALUES 
 (2,1,1,'article','文章模型','文章','channel','content',1),
 (7,1,1,'article','单页模型','单页','alone','',0),
 (8,1,1,'article','跳转模型','跳转','','',0),
 (9,1,1,'article','首页模型','首页','index','',1);
/*!40000 ALTER TABLE `cms_chnl_model` ENABLE KEYS */;


--
-- Definition of table `cms_chnl_model_item`
--

DROP TABLE IF EXISTS `cms_chnl_model_item`;
CREATE TABLE `cms_chnl_model_item` (
  `MITEM_ID` bigint(20) NOT NULL auto_increment,
  `MODEL_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) NOT NULL COMMENT '表单名称',
  `LABEL` varchar(100) default NULL COMMENT 'LABEL名称',
  `HELP` varchar(255) default NULL COMMENT '帮助信息',
  `INPUT_TYPE` varchar(20) default NULL COMMENT '表单类型',
  `PRIORITY` int(11) NOT NULL COMMENT '排列顺序',
  `IS_CHECKED` tinyint(1) NOT NULL COMMENT '是否选中',
  PRIMARY KEY  (`MITEM_ID`),
  KEY `FK_CHNLMODELITEM_MODEL` (`MODEL_ID`),
  CONSTRAINT `FK_CHNLMODELITEM_MODEL` FOREIGN KEY (`MODEL_ID`) REFERENCES `cms_chnl_model` (`MODEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='栏目模型定义';

--
-- Dumping data for table `cms_chnl_model_item`
--

/*!40000 ALTER TABLE `cms_chnl_model_item` DISABLE KEYS */;
INSERT INTO `cms_chnl_model_item` (`MITEM_ID`,`MODEL_ID`,`NAME`,`LABEL`,`HELP`,`INPUT_TYPE`,`PRIORITY`,`IS_CHECKED`) VALUES 
 (1,2,'title','title',NULL,NULL,4,1),
 (2,2,'priority','排列顺序',NULL,NULL,7,1),
 (3,2,'keywords','keywords',NULL,NULL,5,1),
 (4,2,'description','description',NULL,NULL,6,1),
 (15,2,'tplIndex','栏目页模板',NULL,NULL,2,1),
 (16,2,'tplContent','内容页模板',NULL,NULL,3,1),
 (17,2,'contributeLevel','允许投稿级别',NULL,NULL,8,1),
 (18,2,'paramData','栏目数据',NULL,NULL,10,1),
 (43,2,'display','是否显示',NULL,NULL,9,1),
 (45,7,'tplIndex','单页模板',NULL,NULL,2,1),
 (46,7,'tplContent','内容页模板',NULL,NULL,3,0),
 (47,7,'title','title',NULL,NULL,4,1),
 (48,7,'keywords','keywords',NULL,NULL,5,1),
 (49,7,'description','description',NULL,NULL,6,1),
 (50,7,'priority','排列顺序',NULL,NULL,7,1),
 (51,7,'contributeLevel','允许投稿级别',NULL,NULL,8,0),
 (52,7,'display','是否显示',NULL,NULL,9,1),
 (53,7,'paramData','栏目数据',NULL,NULL,10,1),
 (54,8,'tplIndex','栏目页模板',NULL,NULL,3,0);
INSERT INTO `cms_chnl_model_item` (`MITEM_ID`,`MODEL_ID`,`NAME`,`LABEL`,`HELP`,`INPUT_TYPE`,`PRIORITY`,`IS_CHECKED`) VALUES 
 (55,8,'tplContent','内容页模板',NULL,NULL,4,0),
 (56,8,'title','title',NULL,NULL,5,0),
 (57,8,'keywords','keywords',NULL,NULL,6,0),
 (58,8,'description','description',NULL,NULL,7,0),
 (59,8,'priority','排列顺序',NULL,NULL,8,1),
 (60,8,'contributeLevel','允许投稿级别',NULL,NULL,9,0),
 (61,8,'display','是否显示',NULL,NULL,10,1),
 (62,8,'paramData','栏目数据',NULL,NULL,11,0),
 (63,9,'tplIndex','首页模板',NULL,NULL,2,1),
 (64,9,'tplContent','内容页模板',NULL,NULL,3,0),
 (65,9,'title','title',NULL,NULL,4,1),
 (66,9,'keywords','keywords',NULL,NULL,5,1),
 (67,9,'description','description',NULL,NULL,6,1),
 (68,9,'priority','排列顺序',NULL,NULL,7,0),
 (69,9,'contributeLevel','允许投稿级别',NULL,NULL,8,0),
 (70,9,'display','是否显示',NULL,NULL,9,0),
 (71,9,'paramData','栏目数据',NULL,NULL,10,1),
 (72,9,'path','访问路径',NULL,NULL,0,1),
 (73,8,'path','访问路径',NULL,NULL,1,0);
INSERT INTO `cms_chnl_model_item` (`MITEM_ID`,`MODEL_ID`,`NAME`,`LABEL`,`HELP`,`INPUT_TYPE`,`PRIORITY`,`IS_CHECKED`) VALUES 
 (74,7,'path','访问路径',NULL,NULL,0,1),
 (75,2,'path','访问路径',NULL,NULL,0,1),
 (76,8,'outerUrl','外部链接',NULL,NULL,2,1),
 (77,2,'outerUrl','外部链接',NULL,NULL,1,0),
 (78,7,'outerUrl','外部链接',NULL,NULL,1,0),
 (79,9,'outerUrl','外部链接',NULL,NULL,1,0);
/*!40000 ALTER TABLE `cms_chnl_model_item` ENABLE KEYS */;


--
-- Definition of table `cms_comment`
--

DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `COMMENT_ID` bigint(20) NOT NULL auto_increment,
  `ARTICLE_ID` bigint(20) default NULL,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `MEMBER_ID` bigint(20) default NULL,
  `ADMIN_ID` bigint(20) default NULL,
  `TITLE` varchar(200) default NULL COMMENT '标题',
  `CONTENT_GUEST` longtext COMMENT '用户评论内容',
  `CONTENT_ADMIN` longtext COMMENT '管理员回复内容',
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `REPLAY_TIME` datetime default NULL COMMENT '回复时间',
  `IP` varchar(50) default NULL COMMENT '评论者IP',
  `IS_CHECK` tinyint(1) NOT NULL COMMENT '是否审核',
  `IS_RECOMMEND` tinyint(1) NOT NULL COMMENT '是否推荐',
  `IS_DISABLED` tinyint(1) NOT NULL COMMENT '是否禁用',
  PRIMARY KEY  (`COMMENT_ID`),
  KEY `FK_CCOMMENT_ADMIN` (`ADMIN_ID`),
  KEY `FK_CCOMMENT_ARTICLE` (`ARTICLE_ID`),
  KEY `FK_CCOMMENT_MEMBER` (`MEMBER_ID`),
  KEY `FK_CCOMMENT_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_CCOMMENT_ADMIN` FOREIGN KEY (`ADMIN_ID`) REFERENCES `cms_admin` (`ADMIN_ID`),
  CONSTRAINT `FK_CCOMMENT_ARTICLE` FOREIGN KEY (`ARTICLE_ID`) REFERENCES `arti_article` (`ARTICLE_ID`),
  CONSTRAINT `FK_CCOMMENT_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `core_member` (`MEMBER_ID`),
  CONSTRAINT `FK_CCOMMENT_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='jeecms评论表';

--
-- Dumping data for table `cms_comment`
--

/*!40000 ALTER TABLE `cms_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_comment` ENABLE KEYS */;


--
-- Definition of table `cms_config`
--

DROP TABLE IF EXISTS `cms_config`;
CREATE TABLE `cms_config` (
  `CONFIG_ID` bigint(20) NOT NULL,
  `COMMENT_NEED_CHECK` tinyint(1) default '0' COMMENT '评论是否需要审核',
  `CHECK_COUNT` int(11) default '0' COMMENT '内容需要审核的次数',
  `DEFAULT_SYSTEM` varchar(20) default 'article' COMMENT '默认系统',
  PRIMARY KEY  (`CONFIG_ID`),
  CONSTRAINT `FK_CMSCONFIG_WEBSITE` FOREIGN KEY (`CONFIG_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='jeecms配置表';

--
-- Dumping data for table `cms_config`
--

/*!40000 ALTER TABLE `cms_config` DISABLE KEYS */;
INSERT INTO `cms_config` (`CONFIG_ID`,`COMMENT_NEED_CHECK`,`CHECK_COUNT`,`DEFAULT_SYSTEM`) VALUES 
 (1,0,0,'article');
/*!40000 ALTER TABLE `cms_config` ENABLE KEYS */;


--
-- Definition of table `cms_member`
--

DROP TABLE IF EXISTS `cms_member`;
CREATE TABLE `cms_member` (
  `MEMBER_ID` bigint(20) NOT NULL,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `GROUP_ID` bigint(20) NOT NULL,
  `SCORE` int(11) default '0' COMMENT '积分',
  `COUPON` int(11) default '0' COMMENT '点劵',
  `IS_DISABLED` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  PRIMARY KEY  (`MEMBER_ID`),
  KEY `FK_CMEMBER_MGROUP` (`GROUP_ID`),
  KEY `FK_CMEMBER_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_CMEMBER_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `core_member` (`MEMBER_ID`),
  CONSTRAINT `FK_CMEMBER_MGROUP` FOREIGN KEY (`GROUP_ID`) REFERENCES `cms_member_group` (`GROUP_ID`),
  CONSTRAINT `FK_CMEMBER_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='jeecms会员';

--
-- Dumping data for table `cms_member`
--

/*!40000 ALTER TABLE `cms_member` DISABLE KEYS */;
INSERT INTO `cms_member` (`MEMBER_ID`,`WEBSITE_ID`,`GROUP_ID`,`SCORE`,`COUPON`,`IS_DISABLED`) VALUES 
 (1,1,1,0,0,0);
/*!40000 ALTER TABLE `cms_member` ENABLE KEYS */;


--
-- Definition of table `cms_member_group`
--

DROP TABLE IF EXISTS `cms_member_group`;
CREATE TABLE `cms_member_group` (
  `GROUP_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) default NULL COMMENT '组名',
  `GROUP_LEVEL` int(11) default '10' COMMENT '组等级（越小越高）',
  `DESCRIPTION` varchar(255) default NULL COMMENT '描述',
  `UPLOAD_SIZE` int(11) default '0' COMMENT '上传文件的大小，单位KB',
  PRIMARY KEY  (`GROUP_ID`),
  KEY `FK_CMGROUP_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_CMGROUP_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='jeecms用户组';

--
-- Dumping data for table `cms_member_group`
--

/*!40000 ALTER TABLE `cms_member_group` DISABLE KEYS */;
INSERT INTO `cms_member_group` (`GROUP_ID`,`WEBSITE_ID`,`NAME`,`GROUP_LEVEL`,`DESCRIPTION`,`UPLOAD_SIZE`) VALUES 
 (1,1,'普通会员',10,NULL,0);
/*!40000 ALTER TABLE `cms_member_group` ENABLE KEYS */;


--
-- Definition of table `cms_recommend_group`
--

DROP TABLE IF EXISTS `cms_recommend_group`;
CREATE TABLE `cms_recommend_group` (
  `RGROUP_ID` bigint(20) NOT NULL auto_increment,
  `CHANNEL_ID` bigint(20) NOT NULL,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `SYS_TYPE` char(4) NOT NULL COMMENT '系统类型',
  `NAME` varchar(50) default NULL COMMENT '推荐组名',
  `DESCRIPTION` varchar(250) default NULL COMMENT '描述',
  PRIMARY KEY  (`RGROUP_ID`),
  KEY `FK_RGROUP_CHANNEL` (`CHANNEL_ID`),
  KEY `FK_RGROUP_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_RGROUP_CHANNEL` FOREIGN KEY (`CHANNEL_ID`) REFERENCES `cms_channel` (`CHANNEL_ID`),
  CONSTRAINT `FK_RGROUP_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `cms_recommend_group`
--

/*!40000 ALTER TABLE `cms_recommend_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_recommend_group` ENABLE KEYS */;


--
-- Definition of table `cms_recommend_item`
--

DROP TABLE IF EXISTS `cms_recommend_item`;
CREATE TABLE `cms_recommend_item` (
  `RITEM_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `RGROUP_ID` bigint(20) NOT NULL,
  `SYS_TYPE` char(4) NOT NULL COMMENT '系统类型',
  `TITLE` varchar(250) default NULL COMMENT '推荐的标题',
  `DESCRIPTION` varchar(255) default NULL COMMENT '推荐的描述',
  `PIC_PATH` varchar(250) default NULL COMMENT '图片地址，完整url',
  `URL` varchar(250) default NULL COMMENT '推荐链接，完整url',
  `COLOR` varchar(20) default NULL COMMENT '标题的颜色',
  `PRIORITY` int(11) NOT NULL COMMENT '优先级（显示的先后顺序）',
  `IS_CHECK` tinyint(1) NOT NULL COMMENT '是否审核',
  PRIMARY KEY  (`RITEM_ID`),
  KEY `FK_RITEM_RGOUP` (`RGROUP_ID`),
  KEY `FK_RITEM_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_RITEM_RGOUP` FOREIGN KEY (`RGROUP_ID`) REFERENCES `cms_recommend_group` (`RGROUP_ID`),
  CONSTRAINT `FK_RITEM_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `cms_recommend_item`
--

/*!40000 ALTER TABLE `cms_recommend_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_recommend_item` ENABLE KEYS */;


--
-- Definition of table `core_admin`
--

DROP TABLE IF EXISTS `core_admin`;
CREATE TABLE `core_admin` (
  `ADMIN_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `IS_DISABLED` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  PRIMARY KEY  (`ADMIN_ID`),
  KEY `FK_ADMIN_USER` (`USER_ID`),
  KEY `FK_ADMIN_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_ADMIN_USER` FOREIGN KEY (`USER_ID`) REFERENCES `core_user` (`USER_ID`),
  CONSTRAINT `FK_ADMIN_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='核心管理员';

--
-- Dumping data for table `core_admin`
--

/*!40000 ALTER TABLE `core_admin` DISABLE KEYS */;
INSERT INTO `core_admin` (`ADMIN_ID`,`WEBSITE_ID`,`USER_ID`,`CREATE_TIME`,`IS_DISABLED`) VALUES 
 (1,1,1,'2008-08-08 00:00:00',0);
/*!40000 ALTER TABLE `core_admin` ENABLE KEYS */;


--
-- Definition of table `core_admin_function`
--

DROP TABLE IF EXISTS `core_admin_function`;
CREATE TABLE `core_admin_function` (
  `ADMIN_ID` bigint(20) NOT NULL,
  `FUNCTION_ID` bigint(20) NOT NULL,
  PRIMARY KEY  (`ADMIN_ID`,`FUNCTION_ID`),
  KEY `FK_CORE_ADMIN_FUNCTION` (`FUNCTION_ID`),
  CONSTRAINT `FK_CORE_ADMIN_FUNCTION` FOREIGN KEY (`FUNCTION_ID`) REFERENCES `core_function` (`FUNCTION_ID`),
  CONSTRAINT `FK_CORE_FUNCTION_ADMIN` FOREIGN KEY (`ADMIN_ID`) REFERENCES `core_admin` (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `core_admin_function`
--

/*!40000 ALTER TABLE `core_admin_function` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_admin_function` ENABLE KEYS */;


--
-- Definition of table `core_admin_role`
--

DROP TABLE IF EXISTS `core_admin_role`;
CREATE TABLE `core_admin_role` (
  `ROLE_ID` bigint(20) NOT NULL,
  `ADMIN_ID` bigint(20) NOT NULL,
  PRIMARY KEY  (`ROLE_ID`,`ADMIN_ID`),
  KEY `FK_CORE_ROLE_ADMIN` (`ADMIN_ID`),
  CONSTRAINT `FK_CORE_ADMIN_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `core_role` (`ROLE_ID`),
  CONSTRAINT `FK_CORE_ROLE_ADMIN` FOREIGN KEY (`ADMIN_ID`) REFERENCES `core_admin` (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `core_admin_role`
--

/*!40000 ALTER TABLE `core_admin_role` DISABLE KEYS */;
INSERT INTO `core_admin_role` (`ROLE_ID`,`ADMIN_ID`) VALUES 
 (1,1);
/*!40000 ALTER TABLE `core_admin_role` ENABLE KEYS */;


--
-- Definition of table `core_function`
--

DROP TABLE IF EXISTS `core_function`;
CREATE TABLE `core_function` (
  `FUNCTION_ID` bigint(20) NOT NULL auto_increment,
  `PARENT` bigint(20) default NULL COMMENT '父功能',
  `NAME` varchar(100) default NULL,
  `URL` varchar(200) default NULL,
  `FUNCS` longtext COMMENT '功能列表集，用@分割',
  `DESCRIPTION` varchar(250) default NULL,
  `PRIORITY` int(11) NOT NULL COMMENT '功能菜单排列顺序',
  `IS_MENU` tinyint(1) NOT NULL COMMENT '是否为菜单',
  PRIMARY KEY  (`FUNCTION_ID`),
  KEY `FK_PARENT` (`PARENT`),
  CONSTRAINT `FK_PARENT` FOREIGN KEY (`PARENT`) REFERENCES `core_function` (`FUNCTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='功能表';

--
-- Dumping data for table `core_function`
--

/*!40000 ALTER TABLE `core_function` DISABLE KEYS */;
INSERT INTO `core_function` (`FUNCTION_ID`,`PARENT`,`NAME`,`URL`,`FUNCS`,`DESCRIPTION`,`PRIORITY`,`IS_MENU`) VALUES 
 (1,NULL,'功能菜单','','','',100,1),
 (81,1,'核心功能','/admin/cms/frame/core_main','/admin/cms/frame/core_left@/admin/cms/frame/core_right','',30,1),
 (83,81,'用户管理','/admin/core/user/Com_list','/admin/core/user/Com_add@/admin/core/user/Com_edit@/admin/core/user/Com_update@/admin/core/user/Com_save@/admin/core/user/Com_delete','',20,1),
 (87,81,'角色管理','/admin/core/role/Com_list','/admin/core/role/Com_add@/admin/core/role/Com_edit@/admin/core/role/Com_save@/admin/core/role/Com_update@/admin/core/role/Com_delete','',30,1),
 (353,1,'辅助系统','/admin/cms/frame/auxiliary_main','/admin/cms/frame/auxiliary_left@/admin/cms/frame/auxiliary_right','',20,1),
 (369,1,'模板管理','/admin/core/template/Com_main','/admin/core/template/Com_left@/admin/core/template/Com_right@/admin/core/template/Com_list@/admin/core/template/Com_Add@/admin/core/template/Com_edit@/admin/core/template/Com_save@/admin/core/template/Com_update@/admin/core/template/Com_delete@/admin/core/template/Com_createDir@/admin/core/template/ajax/update@/admin/core/template/ajax/rename@/admin/core/template/Com_solutionEdit@/admin/core/template/Com_solutionUpdate@/admin/core/template/Com_import@@/admin/core/template/Com_importSubmit@/admin/core/template/Com_export@/admin/core/template/Com_exportSubmit','',40,1),
 (370,81,'功能菜单','/admin/core/function/Com_main','/admin/core/function/Com_list@/admin/core/function/Com_left@/admin/core/function/Com_add@/admin/core/function/Com_edit@/admin/core/function/Com_save@/admin/core/function/Com_update@/admin/core/function/Com_delete@/admin/core/function/Com_priorityUpdate','',10,1);
INSERT INTO `core_function` (`FUNCTION_ID`,`PARENT`,`NAME`,`URL`,`FUNCS`,`DESCRIPTION`,`PRIORITY`,`IS_MENU`) VALUES 
 (372,81,'站点设置','/admin/core/config/Com_siteEdit','/admin/core/config/Com_siteUpdate','',50,1),
 (373,81,'邮件设置','/admin/core/config/Com_emailEdit','/admin/core/config/Com_emailUpdate','',60,1),
 (374,1,'资源管理','/admin/core/resource/Com_resMain','/admin/core/resource/Com_resLeft@/admin/core/resource/Com_resRight@/admin/core/resource/Com_resList@/admin/core/resource/Com_resAdd@/admin/core/resource/Com_resEdit@/admin/core/resource/Com_resSave@/admin/core/resource/Com_resUpdate@/admin/core/resource/Com_resDeleteres@/admin/core/resource/Com_resCreateDir@/admin/core/resource/ajax/resUpdate@/admin/core/resource/ajax/resRename','',50,1),
 (375,353,'留言类别','/admin/auxiliary/msg_ctg/Com_list','/admin/auxiliary/msg_ctg/Com_add@/admin/auxiliary/msg_ctg/Com_edit@/admin/auxiliary/msg_ctg/Com_save@/admin/auxiliary/msg_ctg/Com_update@/admin/auxiliary/msg_ctg/Com_delete','',100,1),
 (376,353,'留言内容','/admin/auxiliary/msg/Com_list','/admin/auxiliary/msg/Com_add@/admin/auxiliary/msg/Com_edit@/admin/auxiliary/msg/Com_save@/admin/auxiliary/msg/Com_update@/admin/auxiliary/msg/Com_delete','',100,1);
INSERT INTO `core_function` (`FUNCTION_ID`,`PARENT`,`NAME`,`URL`,`FUNCS`,`DESCRIPTION`,`PRIORITY`,`IS_MENU`) VALUES 
 (377,353,'辅助系统设置','/admin/auxiliary/config/Com_configEdit','','',90,1),
 (378,1,'JEECMS','/admin/cms/Com_index','/admin/cms/Com_main@/admin/cms/Com_left@/admin/cms/Com_right','包括jeecms会员、管理员、配置等功能。',10,1),
 (379,378,'管理员管理','/admin/cms/admin/Com_list','/admin/cms/admin/Com_add@/admin/cms/admin/Com_edit@/admin/cms/admin/Com_save@/admin/cms/admin/Com_update@/admin/cms/admin/Com_delete','',50,1),
 (382,378,'文章栏目','/admin/article/channel/Com_main','/admin/article/channel/Com_left@/admin/article/channel/Com_right@/admin/article/channel/Com_list@/admin/article/channel/Com_add@/admin/article/channel/Com_edit@/admin/article/channel/Com_save@/admin/article/channel/Com_update@/admin/article/channel/Com_delete@/admin/article/channel/Com_priorityUpdate','',20,1),
 (383,378,'文章内容','/admin/article/article/Com_main','/admin/article/article/Com_left@/admin/article/article/Com_right@/admin/article/article/Com_list@/admin/article/article/Com_add@/admin/article/article/Com_edit@/admin/article/article/Com_save@/admin/article/article/Com_update@/admin/article/article/Com_delete','',30,1),
 (384,378,'网站配置','/admin/cms/frame/config_main','/admin/cms/frame/config_left@/admin/cms/frame/config_right@/admin/cms/config/Com_configEdit@/admin/cms/config/Com_configUpdate','',40,1);
INSERT INTO `core_function` (`FUNCTION_ID`,`PARENT`,`NAME`,`URL`,`FUNCS`,`DESCRIPTION`,`PRIORITY`,`IS_MENU`) VALUES 
 (385,378,'栏目模型','/admin/article/chnl_model/Com_list','/admin/article/chnl_model/Com_add@/admin/article/chnl_model/Com_edit@/admin/article/chnl_model/Com_save@/admin/article/chnl_model/Com_update@/admin/article/chnl_model/Com_delete@/admin/article/chnl_model/Com_priorityUpdate','',10,1),
 (386,353,'投票管理','/admin/auxiliary/vote_topic/Com_list','/admin/auxiliary/vote_topic/Com_add@/admin/auxiliary/vote_topic/Com_save@/admin/auxiliary/vote_topic/Com_edit@/admin/auxiliary/vote_topic/Com_update@/admin/auxiliary/vote_topic/Com_delete','',100,1),
 (387,378,'文章属性','/admin/article/ctg/Com_list','/admin/article/ctg/Com_add@/admin/article/ctg/Com_edit@/admin/article/ctg/Com_save@/admin/article/ctg/Com_update@/admin/article/ctg/Com_delete','',25,1),
 (388,81,'图片处理','','/admin/core/common/UploadImg@/admin/core/common/ImgAreaSelect@/admin/core/common/ImgCut','图片上传、图片剪裁',100,1);
/*!40000 ALTER TABLE `core_function` ENABLE KEYS */;


--
-- Definition of table `core_member`
--

DROP TABLE IF EXISTS `core_member`;
CREATE TABLE `core_member` (
  `MEMBER_ID` bigint(20) NOT NULL auto_increment,
  `USER_ID` bigint(20) NOT NULL,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `NICK_NAME` varchar(50) default NULL COMMENT '昵称',
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `QQ` varchar(100) default NULL COMMENT 'QQ',
  `MSN` varchar(100) default NULL COMMENT 'MSN',
  `IS_DISABLED` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  PRIMARY KEY  (`MEMBER_ID`),
  KEY `FK_MEMBER_USER` (`USER_ID`),
  KEY `FK_MEMBER_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_MEMBER_USER` FOREIGN KEY (`USER_ID`) REFERENCES `core_user` (`USER_ID`),
  CONSTRAINT `FK_MEMBER_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='会员表';

--
-- Dumping data for table `core_member`
--

/*!40000 ALTER TABLE `core_member` DISABLE KEYS */;
INSERT INTO `core_member` (`MEMBER_ID`,`USER_ID`,`WEBSITE_ID`,`NICK_NAME`,`CREATE_TIME`,`QQ`,`MSN`,`IS_DISABLED`) VALUES 
 (1,1,1,NULL,'2008-08-08 00:00:00',NULL,NULL,0);
/*!40000 ALTER TABLE `core_member` ENABLE KEYS */;


--
-- Definition of table `core_role`
--

DROP TABLE IF EXISTS `core_role`;
CREATE TABLE `core_role` (
  `ROLE_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(50) default NULL,
  `DESCRIPTION` varchar(250) default NULL,
  PRIMARY KEY  (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='角色表';

--
-- Dumping data for table `core_role`
--

/*!40000 ALTER TABLE `core_role` DISABLE KEYS */;
INSERT INTO `core_role` (`ROLE_ID`,`NAME`,`DESCRIPTION`) VALUES 
 (1,'超级管理员','拥有系统最高权限');
/*!40000 ALTER TABLE `core_role` ENABLE KEYS */;


--
-- Definition of table `core_role_function`
--

DROP TABLE IF EXISTS `core_role_function`;
CREATE TABLE `core_role_function` (
  `ROLE_ID` bigint(20) NOT NULL,
  `FUNCTION_ID` bigint(20) NOT NULL,
  PRIMARY KEY  (`ROLE_ID`,`FUNCTION_ID`),
  KEY `FK_CORE_ROLE_FUNCTION` (`FUNCTION_ID`),
  CONSTRAINT `FK_CORE_FUNCTION_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `core_role` (`ROLE_ID`),
  CONSTRAINT `FK_CORE_ROLE_FUNCTION` FOREIGN KEY (`FUNCTION_ID`) REFERENCES `core_function` (`FUNCTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `core_role_function`
--

/*!40000 ALTER TABLE `core_role_function` DISABLE KEYS */;
INSERT INTO `core_role_function` (`ROLE_ID`,`FUNCTION_ID`) VALUES 
 (1,1),
 (1,81),
 (1,83),
 (1,87),
 (1,353),
 (1,369),
 (1,370),
 (1,372),
 (1,373),
 (1,374),
 (1,375),
 (1,376),
 (1,377),
 (1,378),
 (1,379),
 (1,382),
 (1,383),
 (1,384),
 (1,385),
 (1,386),
 (1,387),
 (1,388);
/*!40000 ALTER TABLE `core_role_function` ENABLE KEYS */;


--
-- Definition of table `core_tpl_solution`
--

DROP TABLE IF EXISTS `core_tpl_solution`;
CREATE TABLE `core_tpl_solution` (
  `WEBSITE_ID` bigint(20) NOT NULL,
  `SYSTEM_NAME` varchar(20) NOT NULL,
  `SOLUTION_NAME` varchar(20) default NULL,
  KEY `FK_TPLSOLUTION_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_TPLSOLUTION_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='模板方案表';

--
-- Dumping data for table `core_tpl_solution`
--

/*!40000 ALTER TABLE `core_tpl_solution` DISABLE KEYS */;
INSERT INTO `core_tpl_solution` (`WEBSITE_ID`,`SYSTEM_NAME`,`SOLUTION_NAME`) VALUES 
 (1,'article','default'),
 (1,'auxiliary','default'),
 (1,'cms_common','default'),
 (1,'cms_member','default'),
 (1,'picture','default'),
 (1,'forum','default'),
 (1,'download','default'),
 (1,'shop','default');
/*!40000 ALTER TABLE `core_tpl_solution` ENABLE KEYS */;


--
-- Definition of table `core_user`
--

DROP TABLE IF EXISTS `core_user`;
CREATE TABLE `core_user` (
  `USER_ID` bigint(20) NOT NULL auto_increment,
  `LOGIN_NAME` varchar(50) NOT NULL COMMENT '登录名',
  `REAL_NAME` varchar(50) default NULL COMMENT '真实姓名',
  `PASSWORD` char(32) default NULL COMMENT '密码',
  `EMAIL` varchar(100) default NULL COMMENT '电子邮件',
  `FAX` varchar(250) default NULL COMMENT '传真',
  `TEL` varchar(250) default NULL COMMENT '电话',
  `MOBILE` varchar(250) default NULL COMMENT '手机',
  `ZIP` varchar(20) default NULL COMMENT '邮编',
  `ADDRESS` varchar(250) default NULL COMMENT '联系地址',
  `GENDER` tinyint(1) default NULL COMMENT '性别',
  `BIRTHDAY` date default NULL COMMENT '出生年月',
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `LAST_LOGIN_TIME` datetime default NULL COMMENT '最后登录时间',
  `LAST_LOGIN_IP` varchar(50) default NULL COMMENT '最后登录IP',
  `CURRENT_LOGIN_TIME` datetime default NULL COMMENT '当前登录时间',
  `CURRENT_LOGIN_IP` varchar(50) default NULL COMMENT '当前登录IP',
  `LOGIN_COUNT` bigint(20) default '0' COMMENT '总共登录次数',
  `IS_DISABLED` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  PRIMARY KEY  (`USER_ID`),
  KEY `AK_LOGIN_NAME` (`LOGIN_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='统一用户表。';

--
-- Dumping data for table `core_user`
--

/*!40000 ALTER TABLE `core_user` DISABLE KEYS */;
INSERT INTO `core_user` (`USER_ID`,`LOGIN_NAME`,`REAL_NAME`,`PASSWORD`,`EMAIL`,`FAX`,`TEL`,`MOBILE`,`ZIP`,`ADDRESS`,`GENDER`,`BIRTHDAY`,`CREATE_TIME`,`LAST_LOGIN_TIME`,`LAST_LOGIN_IP`,`CURRENT_LOGIN_TIME`,`CURRENT_LOGIN_IP`,`LOGIN_COUNT`,`IS_DISABLED`) VALUES 
 (1,'admin',NULL,'54f0087a24066298b3f912ae29770134',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2008-08-08 00:00:00',NULL,NULL,'2008-12-27 22:34:14','127.0.0.1',1,0);
/*!40000 ALTER TABLE `core_user` ENABLE KEYS */;


--
-- Definition of table `core_website`
--

DROP TABLE IF EXISTS `core_website`;
CREATE TABLE `core_website` (
  `WEBSITE_ID` bigint(20) NOT NULL auto_increment,
  `PARENT` bigint(20) default NULL COMMENT '父站点',
  `UNITED` bigint(20) default NULL COMMENT '会员联合站点',
  `DOMAIN` varchar(50) NOT NULL COMMENT '域名',
  `RES_PATH` varchar(20) NOT NULL COMMENT '资源路径',
  `RES_DOMAIN` varchar(50) default NULL COMMENT '资源地址',
  `BASE_DOMAIN` varchar(50) default NULL COMMENT '根域名',
  `DOMAIN_ALIAS` varchar(250) default NULL COMMENT '可储存多个别名，用;分割',
  `PORT` int(11) default NULL COMMENT '服务的端口号',
  `CONTEXT_PATH` varchar(20) default NULL COMMENT '应用部署的路径',
  `NAME` varchar(100) default NULL COMMENT '站点名称',
  `SHORT_NAME` varchar(20) default NULL COMMENT '简称',
  `SUFFIX` varchar(20) default 'htm' COMMENT '访问后缀，可以是htm、asp、php等',
  `CURRENT_SYSTEM` varchar(20) default 'jeecms' COMMENT '当前系统（jeecms,jeeshop,jeeforum）',
  `COOKIE_KEY` varchar(20) default '_jeecms' COMMENT '系统cookie识别码',
  `OWNER_NAME` varchar(100) default '' COMMENT '网站拥有者姓名',
  `OWNER_IDENTITY` varchar(100) default '' COMMENT '网站拥有者身份证号',
  `COMPANY` varchar(200) default '' COMMENT '公司名称',
  `COPYRIGHT` varchar(255) default '' COMMENT '版权信息',
  `RECORD_CODE` varchar(255) default '' COMMENT '备案号',
  `EMAIL` varchar(100) default '' COMMENT '网站拥有者电子邮箱',
  `PHONE_CODE` varchar(200) default '' COMMENT '电话号码',
  `MOBILE_CODE` varchar(200) default '' COMMENT '手机号码',
  `CREATE_TIME` datetime default NULL COMMENT '站点创建时间',
  `EMAIL_CHARSET` varchar(20) default 'gbk' COMMENT '邮件发送编码',
  `EMAIL_HOSTNAME` varchar(50) default '' COMMENT '邮件发送服务器',
  `EMAIL_ACCOUNT` varchar(100) default '' COMMENT '网站邮箱账号',
  `EMAIL_USER_NAME` varchar(50) default '' COMMENT '网站邮箱名称',
  `EMAIL_USER_ID` varchar(100) default '' COMMENT '网站邮箱登录名',
  `EMAIL_USER_PWD` varchar(50) default '' COMMENT '网站邮箱密码',
  `EMAIL_SUBJECT` varchar(255) default '' COMMENT '邮件主题（标题）',
  `EMAIL_CONTENT` longtext COMMENT '邮件内容',
  `CLOSE_REASON` varchar(255) default '网站暂时关闭' COMMENT '关闭原因',
  `IS_CLOSE` tinyint(1) NOT NULL default '0' COMMENT '是否关闭网站（后台仍可访问）',
  PRIMARY KEY  (`WEBSITE_ID`),
  KEY `AK_DOMAIN` (`DOMAIN`),
  KEY `AK_RES_PATH` (`RES_PATH`),
  KEY `FK_PARENT_WEBSITE` (`PARENT`),
  KEY `FK_UNITED_MEMBER_WEBISTE` (`UNITED`),
  CONSTRAINT `FK_PARENT_WEBSITE` FOREIGN KEY (`PARENT`) REFERENCES `core_website` (`WEBSITE_ID`),
  CONSTRAINT `FK_UNITED_MEMBER_WEBISTE` FOREIGN KEY (`UNITED`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `core_website`
--

/*!40000 ALTER TABLE `core_website` DISABLE KEYS */;
INSERT INTO `core_website` (`WEBSITE_ID`,`PARENT`,`UNITED`,`DOMAIN`,`RES_PATH`,`RES_DOMAIN`,`BASE_DOMAIN`,`DOMAIN_ALIAS`,`PORT`,`CONTEXT_PATH`,`NAME`,`SHORT_NAME`,`SUFFIX`,`CURRENT_SYSTEM`,`COOKIE_KEY`,`OWNER_NAME`,`OWNER_IDENTITY`,`COMPANY`,`COPYRIGHT`,`RECORD_CODE`,`EMAIL`,`PHONE_CODE`,`MOBILE_CODE`,`CREATE_TIME`,`EMAIL_CHARSET`,`EMAIL_HOSTNAME`,`EMAIL_ACCOUNT`,`EMAIL_USER_NAME`,`EMAIL_USER_ID`,`EMAIL_USER_PWD`,`EMAIL_SUBJECT`,`EMAIL_CONTENT`,`CLOSE_REASON`,`IS_CLOSE`) VALUES 
 (1,NULL,NULL,'localhost','jeecms_com_www','',NULL,NULL,8080,'/jeecms','JEECMS演示网站',NULL,'htm','jeecms','_jeecms','','','','','','','','',NULL,'gbk','','','','','','',NULL,'网站暂时关闭',0);
/*!40000 ALTER TABLE `core_website` ENABLE KEYS */;


--
-- Definition of table `vote_item`
--

DROP TABLE IF EXISTS `vote_item`;
CREATE TABLE `vote_item` (
  `VOTEITEM_ID` bigint(20) NOT NULL auto_increment,
  `VTOPIC_ID` bigint(20) NOT NULL,
  `TITLE` varchar(255) default NULL COMMENT '标题',
  `DESCRIPTION` varchar(255) default NULL COMMENT '描述',
  `VOTE_COUNT` bigint(20) default NULL COMMENT '投票数量',
  `PRIORITY` int(11) NOT NULL COMMENT '显示优先级',
  PRIMARY KEY  (`VOTEITEM_ID`),
  KEY `FK_VITEM_TOPIC` (`VTOPIC_ID`),
  CONSTRAINT `FK_VITEM_TOPIC` FOREIGN KEY (`VTOPIC_ID`) REFERENCES `vote_topic` (`VTOPIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='投票项';

--
-- Dumping data for table `vote_item`
--

/*!40000 ALTER TABLE `vote_item` DISABLE KEYS */;
INSERT INTO `vote_item` (`VOTEITEM_ID`,`VTOPIC_ID`,`TITLE`,`DESCRIPTION`,`VOTE_COUNT`,`PRIORITY`) VALUES 
 (1,1,'朋友介绍',NULL,0,1),
 (2,1,'门户网站的搜索引擎',NULL,0,2),
 (3,1,'Google或百度搜索',NULL,0,3),
 (4,1,'别的网站上的链接',NULL,0,4),
 (5,1,'其它途径',NULL,0,5);
/*!40000 ALTER TABLE `vote_item` ENABLE KEYS */;


--
-- Definition of table `vote_record`
--

DROP TABLE IF EXISTS `vote_record`;
CREATE TABLE `vote_record` (
  `VRECORD_ID` bigint(20) NOT NULL auto_increment,
  `VTOPIC_ID` bigint(20) NOT NULL,
  `MEMBER_ID` bigint(20) default NULL,
  `VOTE_TIME` datetime default NULL COMMENT '投票时间',
  `VOTE_IP` varchar(50) default NULL COMMENT '投票IP',
  `VOTE_COOKIE` char(32) default NULL COMMENT '投票cookie',
  PRIMARY KEY  (`VRECORD_ID`),
  KEY `FK_VRECORD_GUEST` (`MEMBER_ID`),
  KEY `FK_VRECORD_TOPIC` (`VTOPIC_ID`),
  CONSTRAINT `FK_VRECORD_GUEST` FOREIGN KEY (`MEMBER_ID`) REFERENCES `core_member` (`MEMBER_ID`),
  CONSTRAINT `FK_VRECORD_TOPIC` FOREIGN KEY (`VTOPIC_ID`) REFERENCES `vote_topic` (`VTOPIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='投票记录';

--
-- Dumping data for table `vote_record`
--

/*!40000 ALTER TABLE `vote_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `vote_record` ENABLE KEYS */;


--
-- Definition of table `vote_topic`
--

DROP TABLE IF EXISTS `vote_topic`;
CREATE TABLE `vote_topic` (
  `VTOPIC_ID` bigint(20) NOT NULL auto_increment,
  `WEBSITE_ID` bigint(20) NOT NULL,
  `TITLE` varchar(255) default NULL COMMENT '标题',
  `DESCRIPTION` varchar(255) default NULL COMMENT '描述',
  `TOTAL_COUNT` bigint(20) default NULL COMMENT '投票数量',
  `START_TIME` datetime default NULL COMMENT '开始时间',
  `END_TIME` datetime default NULL COMMENT '结束时间',
  `REPEATE_HOUR` int(11) default NULL COMMENT '重复投票限制时间(单位小时)',
  `MULTI_SELECT` int(11) default NULL COMMENT '最多可以选择几项',
  `IS_RESTRICT_MEMBER` tinyint(1) NOT NULL COMMENT '是否限制会员ID',
  `IS_RESTRICT_IP` tinyint(1) NOT NULL COMMENT '是否限制IP',
  `IS_RESTRICT_COOKIE` tinyint(1) NOT NULL COMMENT '是否限制cookie',
  `IS_CURRENT` tinyint(1) NOT NULL COMMENT '是否为最新投票',
  `IS_DISABLED` tinyint(1) NOT NULL COMMENT '是否禁止投票',
  PRIMARY KEY  (`VTOPIC_ID`),
  KEY `FK_VTOPIC_WEBSITE` (`WEBSITE_ID`),
  CONSTRAINT `FK_VTOPIC_WEBSITE` FOREIGN KEY (`WEBSITE_ID`) REFERENCES `core_website` (`WEBSITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='投票主题';

--
-- Dumping data for table `vote_topic`
--

/*!40000 ALTER TABLE `vote_topic` DISABLE KEYS */;
INSERT INTO `vote_topic` (`VTOPIC_ID`,`WEBSITE_ID`,`TITLE`,`DESCRIPTION`,`TOTAL_COUNT`,`START_TIME`,`END_TIME`,`REPEATE_HOUR`,`MULTI_SELECT`,`IS_RESTRICT_MEMBER`,`IS_RESTRICT_IP`,`IS_RESTRICT_COOKIE`,`IS_CURRENT`,`IS_DISABLED`) VALUES 
 (1,1,'你是从哪儿得知本站的？','了解用户的来源',0,NULL,NULL,24,1,0,0,1,1,0);
/*!40000 ALTER TABLE `vote_topic` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
