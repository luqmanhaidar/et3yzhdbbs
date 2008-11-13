-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ntsky_bbs
--

CREATE DATABASE IF NOT EXISTS ntsky_bbs;
USE ntsky_bbs;

--
-- Definition of table `ntsky_t_act_log`
--

DROP TABLE IF EXISTS `ntsky_t_act_log`;
CREATE TABLE `ntsky_t_act_log` (
  `act_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `ip` varchar(20) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY  (`act_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

--
-- Dumping data for table `ntsky_t_act_log`
--

/*!40000 ALTER TABLE `ntsky_t_act_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntsky_t_act_log` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_admin`
--

DROP TABLE IF EXISTS `ntsky_t_admin`;
CREATE TABLE `ntsky_t_admin` (
  `id` smallint(6) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` char(32) NOT NULL,
  `permissions` text NOT NULL,
  `last_login_time` char(19) default NULL,
  `last_login_ip` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员登陆表';

--
-- Dumping data for table `ntsky_t_admin`
--

/*!40000 ALTER TABLE `ntsky_t_admin` DISABLE KEYS */;
INSERT INTO `ntsky_t_admin` (`id`,`username`,`password`,`permissions`,`last_login_time`,`last_login_ip`) VALUES 
 (1,'admin','e10adc3949ba59abbe56e057f20f883e','0','2008-11-03 15:37:43','127.0.0.1'),
 (2,'kak','8440b94795262d6fe87a07ae7ed69ceb','4_1,4_3','2008-11-03 14:48:02','127.0.0.1');
/*!40000 ALTER TABLE `ntsky_t_admin` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_announcement`
--

DROP TABLE IF EXISTS `ntsky_t_announcement`;
CREATE TABLE `ntsky_t_announcement` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text,
  `author` varchar(20) default NULL,
  `forum_id` smallint(6) NOT NULL,
  `date_created` char(19) NOT NULL,
  `link` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告表';

--
-- Dumping data for table `ntsky_t_announcement`
--

/*!40000 ALTER TABLE `ntsky_t_announcement` DISABLE KEYS */;
INSERT INTO `ntsky_t_announcement` (`id`,`title`,`content`,`author`,`forum_id`,`date_created`,`link`) VALUES 
 (4,'论坛公告','<p>论坛刚刚开张</p>','admin',0,'2008-10-13 23:52:45',NULL),
 (5,'育儿心经','公告：公告：公告：公告：','admin',7,'2008-10-21 10:44:58','http://www.google.com');
/*!40000 ALTER TABLE `ntsky_t_announcement` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_attachment`
--

DROP TABLE IF EXISTS `ntsky_t_attachment`;
CREATE TABLE `ntsky_t_attachment` (
  `id` int(11) NOT NULL,
  `origin_filename` varchar(255) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `filesize` int(11) NOT NULL,
  `filetype` varchar(50) NOT NULL,
  `folder` varchar(50) NOT NULL default '',
  `comments` varchar(200) default NULL,
  `download_times` int(11) default '0',
  `date_created` datetime NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

--
-- Dumping data for table `ntsky_t_attachment`
--

/*!40000 ALTER TABLE `ntsky_t_attachment` DISABLE KEYS */;
INSERT INTO `ntsky_t_attachment` (`id`,`origin_filename`,`filename`,`filesize`,`filetype`,`folder`,`comments`,`download_times`,`date_created`,`user_id`) VALUES 
 (1,'adview.gif','adview.gif',36,'Image','/ntsky_bbs/UserFiles/1/Image',' ',0,'2008-10-09 23:16:36','1'),
 (2,'adview(3).jpg','adview(3).jpg',1,'Image','/UserFiles/1/Image/',' ',0,'2008-10-09 23:21:58','1'),
 (3,'wjsy_001_zcool_com_cn.jpg','wjsy_001_zcool_com_cn.jpg',47,'Image','/yzhd_bbs/UserFiles/1/Image',' ',0,'2008-10-19 13:16:33','1'),
 (4,'news_20080229153853107.gif','news_20080229153853107.gif',192,'Image','/yzhd_bbs/UserFiles/4/Image',' ',0,'2008-10-24 11:23:37','4'),
 (5,'wjsy_001_zcool_com_cn.jpg','wjsy_001_zcool_com_cn.jpg',47,'Image','/yzhd_bbs/UserFiles/4/Image',' ',0,'2008-10-24 12:37:13','4'),
 (6,'4e81370a-4bc1-4e13-8b63-69804239eb4e.jpg','4e81370a-4bc1-4e13-8b63-69804239eb4e.jpg',129,'Image','/yzhd_bbs/UserFiles/4/Image',' ',0,'2008-10-26 03:22:44','4');
/*!40000 ALTER TABLE `ntsky_t_attachment` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_category`
--

DROP TABLE IF EXISTS `ntsky_t_category`;
CREATE TABLE `ntsky_t_category` (
  `id` smallint(6) NOT NULL,
  `forum_id` smallint(6) NOT NULL,
  `name` varchar(100) default NULL,
  `display_order` smallint(6) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题表';

--
-- Dumping data for table `ntsky_t_category`
--

/*!40000 ALTER TABLE `ntsky_t_category` DISABLE KEYS */;
INSERT INTO `ntsky_t_category` (`id`,`forum_id`,`name`,`display_order`) VALUES 
 (3,0,'普通',0),
 (4,0,'原创',0),
 (5,0,'转帖',0);
/*!40000 ALTER TABLE `ntsky_t_category` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_common`
--

DROP TABLE IF EXISTS `ntsky_t_common`;
CREATE TABLE `ntsky_t_common` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` tinyint(2) NOT NULL,
  `ordering` tinyint(2) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ntsky_t_common`
--

/*!40000 ALTER TABLE `ntsky_t_common` DISABLE KEYS */;
INSERT INTO `ntsky_t_common` (`id`,`name`,`type`,`ordering`) VALUES 
 (1,'动物',1,0),
 (2,'卡通',1,0),
 (3,'景点',1,0),
 (4,'自然',1,0),
 (5,'浪漫',1,0),
 (6,'科幻',1,0),
 (7,'笑脸',1,0),
 (8,'运动',1,0),
 (9,'其它',1,0),
 (10,'登录和注册',2,0),
 (11,'用户设置',2,0),
 (12,'论坛使用',2,0),
 (13,'关于 NTsky',2,0),
 (14,'其它',2,0);
/*!40000 ALTER TABLE `ntsky_t_common` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_favorite`
--

DROP TABLE IF EXISTS `ntsky_t_favorite`;
CREATE TABLE `ntsky_t_favorite` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `url` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `date_created` char(19) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人收藏表';

--
-- Dumping data for table `ntsky_t_favorite`
--

/*!40000 ALTER TABLE `ntsky_t_favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntsky_t_favorite` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_forum`
--

DROP TABLE IF EXISTS `ntsky_t_forum`;
CREATE TABLE `ntsky_t_forum` (
  `id` smallint(6) NOT NULL,
  `name` varchar(100) NOT NULL,
  `parent_id` smallint(6) NOT NULL default '0',
  `parent_enum` varchar(255) default '',
  `depth` smallint(6) NOT NULL default '0',
  `display_order` smallint(6) NOT NULL default '0',
  `branch_id` smallint(6) NOT NULL default '0',
  `masters` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `rules` text,
  `date_created` datetime NOT NULL,
  `sign_image` varchar(255) default NULL,
  `is_top` tinyint(1) unsigned default '0',
  `is_masters` tinyint(1) unsigned NOT NULL default '0',
  `is_admin` tinyint(1) unsigned NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛表';

--
-- Dumping data for table `ntsky_t_forum`
--

/*!40000 ALTER TABLE `ntsky_t_forum` DISABLE KEYS */;
INSERT INTO `ntsky_t_forum` (`id`,`name`,`parent_id`,`parent_enum`,`depth`,`display_order`,`branch_id`,`masters`,`description`,`rules`,`date_created`,`sign_image`,`is_top`,`is_masters`,`is_admin`) VALUES 
 (2,'站务中心',0,'0',1,1,2,'','站务中心','','2008-10-09 23:01:43','',0,0,0),
 (3,'主题论坛',0,'0',1,1,3,'','主题论坛','','2008-10-09 23:02:08','',0,0,0),
 (4,'城市论坛',0,'0',1,1,4,'','城市论坛','','2008-10-09 23:02:17','',0,0,0),
 (5,'论坛公告',2,'2,',2,2,2,'noside','论坛公告','','2008-10-09 23:03:03','skins/default/news_20080229153853107.gif',0,0,1),
 (6,'活动中心',2,'2,',2,3,2,'','活动中心','','2008-10-09 23:03:16','',0,0,0),
 (7,'育儿心经',3,'3,',2,2,3,'noside','育儿心经','','2008-10-09 23:03:37','http://image.bbs.redbaby.com.cn/upfile/boardpic/boardhbb2.jpg',1,0,0),
 (8,'宝宝贴图',3,'3,',2,3,3,'noside','宝宝贴图','','2008-10-09 23:03:56','http://image.bbs.redbaby.com.cn/upfile/boardpic/boardhbb3.jpg',1,0,0),
 (9,'广州论坛',4,'4,',2,2,4,'','广州论坛','','2008-10-09 23:04:23','http://bbs.redbaby.com.cn/bbs/upload/head/2008/9/26//afc7f7c3-795a-47a1-81fe-e33faad10ec1.jpg',0,0,0),
 (10,'北京论坛',4,'4,',2,3,4,'','北京论坛','','2008-10-09 23:04:34','http://bbs.redbaby.com.cn/bbs/upload/head/2008/9/26//afc7f7c3-795a-47a1-81fe-e33faad10ec1.jpg',0,0,0);
INSERT INTO `ntsky_t_forum` (`id`,`name`,`parent_id`,`parent_enum`,`depth`,`display_order`,`branch_id`,`masters`,`description`,`rules`,`date_created`,`sign_image`,`is_top`,`is_masters`,`is_admin`) VALUES 
 (12,'站务交流',2,'2,',2,4,2,'','站务交流','','2008-10-20 09:47:10','',0,1,0),
 (14,'长沙论坛',4,'4,',2,4,4,'','长沙','','2008-10-23 16:49:37','',0,0,0),
 (15,'未准妈妈 ',3,'3,',2,1,3,'','未准妈妈','','2008-10-25 23:45:11','http://image.bbs.redbaby.com.cn/upfile/boardpic/boardhbb1.jpg',1,0,0),
 (17,'宝宝学艺',3,'3,',2,4,3,'','宝宝学艺','','2008-10-25 23:47:59','http://image.bbs.redbaby.com.cn/upfile/boardpic/boardhbb4.jpg',1,0,0),
 (19,'漂亮妈妈',3,'3,',2,5,3,'','漂亮妈妈','','2008-10-25 23:49:25','http://image.bbs.redbaby.com.cn/upfile/boardpic/boardhbb5.jpg',1,0,0),
 (20,'天津论坛',4,'4,',2,5,4,'','天津论坛','','2008-10-25 23:50:09','',0,0,0),
 (21,'南京论坛',4,'4,',2,6,4,'','南京论坛','','2008-10-25 23:50:23','',0,0,0),
 (23,'杭州论坛',4,'4,',2,7,4,'','杭州论坛','','2008-10-25 23:50:54','',0,0,0),
 (24,'西安论坛',4,'4,',2,8,4,'','西安论坛','','2008-10-25 23:51:49','http://war3.replays.net/images/play_bg_2.gif',0,0,0);
INSERT INTO `ntsky_t_forum` (`id`,`name`,`parent_id`,`parent_enum`,`depth`,`display_order`,`branch_id`,`masters`,`description`,`rules`,`date_created`,`sign_image`,`is_top`,`is_masters`,`is_admin`) VALUES 
 (25,'婆媳关系',3,'3,',2,6,3,'','婆媳关系','','2008-10-26 02:08:05','http://image.bbs.redbaby.com.cn/upfile/boardpic/boardhbb6.jpg',1,0,0),
 (27,'单亲家庭',3,'3,',2,7,3,'','单亲家庭','','2008-10-26 02:11:40','http://image.bbs.redbaby.com.cn/upfile/boardpic/boardhbb7.jpg',1,0,0),
 (28,'休闲旅游',3,'3,',2,8,3,'','休闲旅游','','2008-10-26 02:12:42','http://image.bbs.redbaby.com.cn/upfile/boardpic/boardhbb12.jpg',1,0,0),
 (29,'跳蚤市场',3,'3,',2,9,3,'','跳蚤市场','','2008-10-26 02:13:23','',0,0,0),
 (30,'团购天地',3,'3,',2,10,3,'','<p>团购天地</p>','','2008-10-26 02:13:39','',0,0,0),
 (31,'产品评价',3,'3,',2,11,3,'','产品评价','','2008-10-26 02:15:41','',0,0,0),
 (32,'美食厨房',3,'3,',2,12,3,'','美食厨房','','2008-10-26 02:16:33','',0,0,0);
/*!40000 ALTER TABLE `ntsky_t_forum` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_group`
--

DROP TABLE IF EXISTS `ntsky_t_group`;
CREATE TABLE `ntsky_t_group` (
  `id` int(11) NOT NULL default '0',
  `creator` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组信息表';

--
-- Dumping data for table `ntsky_t_group`
--

/*!40000 ALTER TABLE `ntsky_t_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntsky_t_group` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_help`
--

DROP TABLE IF EXISTS `ntsky_t_help`;
CREATE TABLE `ntsky_t_help` (
  `id` int(11) NOT NULL COMMENT '帮助编号',
  `title` varchar(255) NOT NULL COMMENT '帮助标题',
  `content` text NOT NULL COMMENT '帮助内容',
  `type` tinyint(4) NOT NULL COMMENT '类别',
  `date_created` datetime NOT NULL COMMENT '创建时间',
  `is_hand` tinyint(1) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛帮助表';

--
-- Dumping data for table `ntsky_t_help`
--

/*!40000 ALTER TABLE `ntsky_t_help` DISABLE KEYS */;
INSERT INTO `ntsky_t_help` (`id`,`title`,`content`,`type`,`date_created`,`is_hand`) VALUES 
 (1,'我为什么要注册？','发帖、阅读其他人的帖子前是否需要登录，这取决于论坛管理员是否设置了论坛的游客权限。大多数设置允许不需要注册登录就可以阅读信息。通过注册您可以享受到所有的附加功能，例如：设置您的头像、 收藏感兴趣的帖子、给用户发送邮件、私人留言、访问一些受保护的论坛等等，注册步骤也非常简单，一分钟就能完成。',10,'2006-01-01 00:00:01',0),
 (2,'怎样才能注册？','要注册一个新帐号，你需要访问注册并且按照要求填写注册表单。您必须提供一个用户名和一个有效的电子信箱地址。管理员可能要求你指定密码，如果不需要指定密码，那么在注册完成后您将收到确认邮件。 ',10,'2006-01-01 00:00:01',0),
 (3,'我忘记了密码. ','假如您忘记了密码，您可以访问 \"找回密码\" 页输入您注册时的电子信箱地址，会有一份有新密码的邮件发送到您的注册信箱。因为密码是不可逆加密存储，所以我们无法找回您的原始密码。一旦您收到您的新密码，您可以登录并修改您的密码。',10,'2006-01-01 00:00:01',0),
 (4,'已注册，但是现在不能登录？ ','请先检查你的用户名和密码是否正确。如果仍然不能登录，你的帐号可能由于长期未登录已被删除。请与论坛管理员或者版主联系。 ',10,'2006-01-01 00:00:01',0);
INSERT INTO `ntsky_t_help` (`id`,`title`,`content`,`type`,`date_created`,`is_hand`) VALUES 
 (5,'如何在帖子里添加签名？','签名就是附加在你发表于论坛上的每一篇帖子后面的信息. 你可以在个人资料页面编辑你的签名. 此签名将显示在你所发表的任何信息的尾部。 ',11,'2006-01-01 00:00:01',0),
 (6,'我如何设置头像？','如果管理员已启用头像，当你查看个人资料时可以看见头像区。在这里你可以选择一个你喜欢的头像或是上传或是输入一个图片的网址来作为你的头像 ',11,'2006-01-01 00:00:01',0),
 (7,'如何发布新帖子？','在论坛的各个版块中，点“（发表贴子）”即可进入功能齐全的发帖界面。当然您也可以使用板块下面的“发表”发表新帖(如果此选项打开)。注意，一般论坛都设置为需要登录后才能发帖。',12,'2006-01-01 00:00:01',0),
 (8,'如何回复帖子？','在浏览帖子时，点“回复帖子”即可进入功能齐全的回复界面。当然您也可以使用浏览帖子时下面的“快速回复”发表回复(如果此选项打开)。注意，一般论坛都设置为需要登录后才能回复。',12,'2006-01-01 00:00:01',0),
 (9,'该怎样发起一个投票？','您可以像发帖一样在板块中发起投票。每行输入一个可能的选项，您可以通过阅读这个投票贴选出自己的答案，每人只能投票一次，发起之后将不能再对您的选择做出更改。',12,'2006-01-01 00:00:01',0);
INSERT INTO `ntsky_t_help` (`id`,`title`,`content`,`type`,`date_created`,`is_hand`) VALUES 
 (10,'新技术bbs免费吗?','是的,本软件是个开源产品,您可以从本站下载后任意使用.',13,'2006-01-01 00:00:01',0),
 (11,'新技术bbs采用架构?','新技术采用Java编写,页面使用了xhtml1.1+css2进行重构,前端使用freemarker+sitemesh+webwork,业务层使用spring,持久层使用hibernate,支持目前流行的所有数据库.',13,'2006-01-01 00:00:01',0),
 (12,'如何获得新技术bbs?','您可以在http://www.ntsky.com获得下载,如果您是技术人员,可以使用cvs进行checkout.',13,'2006-01-01 00:00:01',0),
 (13,'什么是RSS?','RSS是在线共享内容的一种简易方式（也叫聚合内容，Really Simple Syndication）。通常在时效性比较强的内容上使用RSS订阅能更快速获取信息，网站提供RSS输出，有利于让用户获取网站内容的最新更新。网络用户可以在客户端借助于支持RSS的聚合工具软件（例如SharpReader,NewzCrawler、FeedDemon），在不打开网站内容页面的情况下阅读支持RSS输出的网站内容。',14,'2006-01-01 00:00:01',0);
/*!40000 ALTER TABLE `ntsky_t_help` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_link`
--

DROP TABLE IF EXISTS `ntsky_t_link`;
CREATE TABLE `ntsky_t_link` (
  `id` int(11) NOT NULL,
  `name` varchar(50) default NULL,
  `url` varchar(100) NOT NULL,
  `is_logo` tinyint(1) unsigned NOT NULL default '0',
  `logo` varchar(100) default NULL,
  `description` varchar(200) default NULL,
  `display_order` int(11) default '0',
  `adverType` tinyint(1) unsigned default '5',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友情链接表';

--
-- Dumping data for table `ntsky_t_link`
--

/*!40000 ALTER TABLE `ntsky_t_link` DISABLE KEYS */;
INSERT INTO `ntsky_t_link` (`id`,`name`,`url`,`is_logo`,`logo`,`description`,`display_order`,`adverType`) VALUES 
 (2,'文字连接','http://replays.net',0,NULL,'文字连接',0,5),
 (4,' ','http://ponlinc.com',1,'http://zi.csdn.net/but(560x90).gif','http://zi.csdn.net/but(560x90).gif',0,3),
 (7,' ','http://zi.csdn.net/banner760x90_5.gif',1,'http://zi.csdn.net/banner760x90_5.gif','http://zi.csdn.net/banner760x90_5.gif',0,1),
 (8,NULL,'http://ad.redbaby.com.cn/openads/adimage.php?filename=toy.jpg&contenttype=jpeg',1,'http://ad.redbaby.com.cn/openads/adimage.php?filename=toy.jpg&contenttype=jpeg','http://ad.redbaby.com.cn/openads/adimage.php?filename=toy.jpg&contenttype=jpeg',0,2);
/*!40000 ALTER TABLE `ntsky_t_link` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_message`
--

DROP TABLE IF EXISTS `ntsky_t_message`;
CREATE TABLE `ntsky_t_message` (
  `id` bigint(20) NOT NULL,
  `sender` varchar(30) NOT NULL,
  `receiver` text NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text,
  `send_time` datetime default NULL,
  `flag` tinyint(1) NOT NULL default '0',
  `is_read` tinyint(1) default '0',
  `is_del_sender` tinyint(1) default '0',
  `is_del_receiver` tinyint(1) default '0',
  `status` tinyint(4) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

--
-- Dumping data for table `ntsky_t_message`
--

/*!40000 ALTER TABLE `ntsky_t_message` DISABLE KEYS */;
INSERT INTO `ntsky_t_message` (`id`,`sender`,`receiver`,`title`,`content`,`send_time`,`flag`,`is_read`,`is_del_sender`,`is_del_receiver`,`status`) VALUES 
 (1,'ntsky','ntsky','ntsky 1.0 complent','ntskyq','2006-01-01 00:00:00',1,1,0,0,1),
 (2,'admin','nicer','欢迎信息!','\n			欢迎光临网络天空论坛系统!\n		','2008-10-15 01:04:16',0,1,0,0,1),
 (3,'admin','laxinicer','欢迎信息!','\n			欢迎光临网络天空论坛系统!\n		','2008-10-15 01:04:53',0,1,0,1,1),
 (4,'admin','nicer','asfdsfd','asfdsdf','2008-10-19 15:20:17',0,1,0,0,1),
 (5,'admin','fsdf','sdfsdfsdf','sdfsdf','2008-10-19 15:20:48',0,0,1,0,1),
 (6,'admin','noside','欢迎信息!','\n			欢迎光临网络天空论坛系统!\n		','2008-10-19 15:57:50',0,1,0,0,1),
 (7,'admin','智超','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-20 09:13:05',0,1,0,0,1),
 (8,'laxinicer','laxinicer','dfas','fasd','2008-10-22 09:17:12',0,1,1,0,1),
 (9,'laxinicer','laxinicer','RE:dfas','<br />\r\n<br />\r\n<div style=\"BORDER-TOP: #000 1px solid; PADDING-TOP: 4px\">laxinicer在2008-10-22 9:17:12的消息中写道 : <br />\r\nfasd</div>','2008-10-22 09:17:26',0,1,1,1,1),
 (10,'laxinicer','laxinicer','发信息','发信息','2008-10-22 14:32:22',0,1,0,0,1);
INSERT INTO `ntsky_t_message` (`id`,`sender`,`receiver`,`title`,`content`,`send_time`,`flag`,`is_read`,`is_del_sender`,`is_del_receiver`,`status`) VALUES 
 (11,'laxinicer','laxinicer','RE:发信息','<br />\r\n<br />\r\n<div style=\"BORDER-TOP: #000 1px solid; PADDING-TOP: 4px\">laxinicer在2008-10-22 14:32:22的消息中写道 : <br />\r\n发信息</div>','2008-10-22 14:32:58',0,1,0,1,1),
 (12,'admin','ttt11','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-24 15:48:52',0,1,0,0,1),
 (13,'laxinicer','laxinicer','fa','fsadf','2008-10-27 09:38:45',0,1,0,0,1),
 (14,'laxinicer','laxinicer','RE:fa','<p>laxinicer在2008-10-27 9:38:45的消息中写道 : <br />\r\nfsadf</p>\r\n<p>&nbsp;</p>\r\n<p>fsdfdfsadf</p>','2008-10-27 09:39:27',0,1,0,0,1),
 (15,'admin','test','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-27 14:23:59',0,0,0,0,1),
 (16,'nicer','test','哈哈','哈哈，好玩吗？','2008-10-27 14:33:02',0,1,0,0,1),
 (17,'admin','test1','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-30 15:07:43',0,1,0,0,1),
 (18,'admin','lala','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-11-03 14:25:12',0,0,0,0,1);
/*!40000 ALTER TABLE `ntsky_t_message` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_poll`
--

DROP TABLE IF EXISTS `ntsky_t_poll`;
CREATE TABLE `ntsky_t_poll` (
  `id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `time_out` int(11) default '0',
  `date_created` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投票信息';

--
-- Dumping data for table `ntsky_t_poll`
--

/*!40000 ALTER TABLE `ntsky_t_poll` DISABLE KEYS */;
INSERT INTO `ntsky_t_poll` (`id`,`topic_id`,`content`,`time_out`,`date_created`) VALUES 
 (1,11,'5234',0,'2008-10-21 14:12:19'),
 (2,12,'投票',0,'2008-10-21 17:50:47'),
 (3,17,'喜欢投票吗',0,'2008-10-24 13:09:19'),
 (4,18,'asfsdf',0,'2008-10-24 13:26:12'),
 (5,19,'sfsdfdsf',0,'2008-10-24 13:39:22'),
 (6,24,'1',0,'2008-10-27 09:20:47');
/*!40000 ALTER TABLE `ntsky_t_poll` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_poll_result`
--

DROP TABLE IF EXISTS `ntsky_t_poll_result`;
CREATE TABLE `ntsky_t_poll_result` (
  `id` int(11) NOT NULL,
  `poll_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  `option_text` varchar(255) NOT NULL,
  `votes` int(11) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='答案列表';

--
-- Dumping data for table `ntsky_t_poll_result`
--

/*!40000 ALTER TABLE `ntsky_t_poll_result` DISABLE KEYS */;
INSERT INTO `ntsky_t_poll_result` (`id`,`poll_id`,`option_id`,`option_text`,`votes`) VALUES 
 (1,1,1,'',0),
 (2,2,5,'',1),
 (3,2,4,'',1),
 (4,2,2,'投票2',0),
 (5,2,3,'',0),
 (6,2,1,'投票1',0),
 (7,3,1,'喜欢',3),
 (8,3,2,'不喜欢',1),
 (9,4,1,'aaa',1),
 (10,4,2,'bbb',0),
 (11,5,1,'sdfsdfdf',1),
 (12,6,4,'5',0),
 (13,6,2,'3',0),
 (14,6,1,'2',0),
 (15,6,5,'',1),
 (16,6,3,'4',0);
/*!40000 ALTER TABLE `ntsky_t_poll_result` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_poll_voter`
--

DROP TABLE IF EXISTS `ntsky_t_poll_voter`;
CREATE TABLE `ntsky_t_poll_voter` (
  `id` bigint(20) NOT NULL,
  `poll_id` int(11) NOT NULL,
  `poll_result_id` int(11) NOT NULL,
  `poll_user` varchar(20) default NULL,
  `poll_ip` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投票用户表';

--
-- Dumping data for table `ntsky_t_poll_voter`
--

/*!40000 ALTER TABLE `ntsky_t_poll_voter` DISABLE KEYS */;
INSERT INTO `ntsky_t_poll_voter` (`id`,`poll_id`,`poll_result_id`,`poll_user`,`poll_ip`) VALUES 
 (11,3,7,'noside','127.0.0.1'),
 (12,4,9,'noside','127.0.0.1'),
 (13,3,8,'admin','127.0.0.1'),
 (14,2,2,'admin','127.0.0.1'),
 (15,2,3,'智超','127.0.0.1'),
 (16,3,7,'智超','127.0.0.1'),
 (17,5,11,'admin','127.0.0.1'),
 (5,3,7,'ttt11','127.0.0.1'),
 (18,6,15,'laxinicer','127.0.0.1');
/*!40000 ALTER TABLE `ntsky_t_poll_voter` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_post`
--

DROP TABLE IF EXISTS `ntsky_t_post`;
CREATE TABLE `ntsky_t_post` (
  `id` bigint(20) NOT NULL,
  `forum_id` smallint(6) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `title` varchar(255) default '',
  `content` mediumtext NOT NULL,
  `user_id` varchar(20) NOT NULL default '',
  `username` varchar(20) NOT NULL default '',
  `ip` varchar(20) NOT NULL default '',
  `date_created` datetime NOT NULL,
  `date_edited` datetime default NULL,
  `edit_count` smallint(3) default '0',
  PRIMARY KEY  (`id`),
  KEY `FK_ntsky_t_post_1` (`username`),
  CONSTRAINT `FK_ntsky_t_post_1` FOREIGN KEY (`username`) REFERENCES `ntsky_t_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题回复表';

--
-- Dumping data for table `ntsky_t_post`
--

/*!40000 ALTER TABLE `ntsky_t_post` DISABLE KEYS */;
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (1,7,1,'test1','<p>aaasdfdfd</p><p><img height=\"100\" width=\"750\" alt=\"\" src=\"/ntsky_bbs/UserFiles/1/Image/adview.gif\" /></p><p>dsdfasdf</p>','1','admin','127.0.0.1','2008-10-09 23:16:51',NULL,0),
 (2,7,1,' ','asdf','1','admin','127.0.0.1','2008-10-12 01:12:02',NULL,0),
 (3,5,2,'aaaaa','****','1','admin','127.0.0.1','2008-10-12 03:58:57',NULL,0),
 (5,5,4,'阿塞的发送到非','阿塞的发送到阿四大幅','1','admin','127.0.0.1','2008-10-12 16:43:38',NULL,0),
 (6,7,5,'发贴发贴发贴发贴发贴发贴','发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴','3','laxinicer','127.0.0.1','2008-10-15 02:16:58',NULL,0),
 (7,7,6,'sdfsf','sdfsdfsdf','1','admin','127.0.0.1','2008-10-21 10:01:59',NULL,0);
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (8,7,7,'sdfsf','sdfsdfsdf','1','admin','127.0.0.1','2008-10-21 10:02:24',NULL,0),
 (9,7,8,'fsdfasdf','asfasfsdf','1','admin','127.0.0.1','2008-10-21 10:12:19',NULL,0),
 (10,8,9,'宝宝贴图','按时发射点','2','nicer','127.0.0.1','2008-10-21 14:11:39',NULL,0),
 (11,7,10,'犯得上','翻跟斗感','2','nicer','127.0.0.1','2008-10-21 14:11:59',NULL,0),
 (12,7,11,'34 ','543','2','nicer','127.0.0.1','2008-10-21 14:12:19',NULL,0),
 (13,7,5,' ','gsdgsdfgsd','2','nicer','127.0.0.1','2008-10-21 14:31:58',NULL,0),
 (14,7,12,'发表投票','发表啊~~','2','nicer','127.0.0.1','2008-10-21 17:50:47',NULL,0),
 (15,7,13,'dfgf','sgdffdsg','3','laxinicer','127.0.0.1','2008-10-22 09:21:21',NULL,0),
 (16,7,12,'fas','fasdfdas','3','laxinicer','127.0.0.1','2008-10-22 14:35:17',NULL,0),
 (17,7,14,'发表','hahahaaha','1','admin','127.0.0.1','2008-10-24 11:05:24',NULL,0),
 (18,7,15,'asfsf','asdfsdf<img height=\"422\" width=\"600\" alt=\"\" src=\"/yzhd_bbs/UserFiles/4/Image/news_20080229153853107.gif\" />','4','noside','127.0.0.1','2008-10-24 11:23:47',NULL,0);
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (19,7,16,'aaaaaaaaaaaa','<img height=\"375\" width=\"500\" alt=\"\" src=\"/yzhd_bbs/UserFiles/4/Image/wjsy_001_zcool_com_cn.jpg\" />','4','noside','127.0.0.1','2008-10-24 12:37:23',NULL,0),
 (20,7,17,'投票测试','投票测试','4','noside','127.0.0.1','2008-10-24 13:09:19',NULL,0),
 (21,7,18,'asfdasfd','safsdf','4','noside','127.0.0.1','2008-10-24 13:26:12',NULL,0),
 (22,7,19,'dfsdfsafsdf','sdfsdf','1','admin','127.0.0.1','2008-10-24 13:39:22',NULL,0),
 (23,8,20,'施恩公司2008宝宝摄影大赛','<div>施恩公司2008宝宝摄影大赛结果怎没出来,公布时间为10月10日这算数吗?延期也要通知吧?信用度为多少?</div>','4','noside','127.0.0.1','2008-10-26 03:08:59',NULL,0),
 (24,15,21,'测试','ceshi','4','noside','127.0.0.1','2008-10-26 03:12:32',NULL,0),
 (25,8,22,'宝宝百天“丑”照 ','<p>我丑吗？</p><p><img height=\"452\" width=\"640\" alt=\"\" src=\"/yzhd_bbs/UserFiles/4/Image/4e81370a-4bc1-4e13-8b63-69804239eb4e.jpg\" /></p>','4','noside','127.0.0.1','2008-10-26 03:24:08',NULL,0),
 (26,8,22,' ','呵呵','4','noside','127.0.0.1','2008-10-26 03:28:10',NULL,0);
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (27,8,22,' ','<div class=\"quote\"><span style=\"COLOR: blue\">noside的原贴: </span><br />呵呵 </div><br />无聊','4','noside','127.0.0.1','2008-10-26 03:31:46',NULL,0),
 (28,7,23,'如何和老人沟通教育孩子？？','<font size=\"3\">看过很多姐妹都说老人带孩子跟自己有诸多不同意见，至于对孩子的溺爱，那更是公认的头疼问题！！</font><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我家姗姗从4个月我上班开始，就交给了奶奶，直到现在，还都是奶奶一个人在带！！</span></div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 从姗姗的吃喝拉撒到教育问题，我觉得奶奶做的都不错，我们几乎没发生过什么冲突，姗姗也不是那么骄横无理被人溺爱成性的刁蛮孩子<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-15.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" />呵呵，自夸了哦！</span></div><div>&nbsp;</div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 我想这其中，与我和奶奶的充分沟通有很大的关系。</span></div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;首先说吃的，姗姗从小就不喝奶粉，所以自然担心营养方面的缺失！所以在她小时候我就加大了各种菜粉、蛋黄、肝类的补充，有很多饮食习惯与奶奶的看法自然不一致的，可是奶奶很尊重我的选择，其实喂姗姗的任务基本都是她来完成的。但是她严格按我的交代每餐搭配着喂养姗姗，所以姗姗虽然不喝奶粉，可是各种营养什么也不缺，个子在同龄人中也属于偏高的，最主要的是小姑娘不胖也不瘦，是我理想的标准<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-08.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" />，现在孩子大了，和我们一起每天吃三餐了，但是中午我们不在家的时候，也是叮嘱奶奶一定要肉类蔬菜类一起给孩子吃，千万不能偏食！奶奶基本做到了！！<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-37.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" />至于零食，我和姗爸都是坚决反对孩子吃零食的！所以姗姗很少吃什么零食，对于那些所谓的垃圾食品，更是尝都未尝过！但是这些的坚持更多的是需要奶奶来协助完成的！好在奶奶也明白这些道理，在姗姗经受着外界小朋友零食考验的时候，她总是耐心的对姗姗重复着我们的话语，告诉她那些东西不能吃，吃了会肚肚疼，会牙疼！<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-15.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" />所以姗姗可以不吃棒棒糖，不吃冰激凌，不吃薯片，不吃一切不该吃的零食！而这些是姗姗都能主动做到的，面对诱惑，她也能不哭不闹的！她的零食除了水果和奶类，甚至连饼干都没有，当然她也不爱吃饼干！还有最重要的，奶奶坚持着我们的做法：不能当着孩子的面给孩子买零食！！不能养成她想要什么就买什么的习惯！所以，傻傻的姗姗至今还不知道闹着要买什么东西<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-28.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" /></span></div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 再来说喝的，很多孩子夏天都喜欢喝饮料，还有那些酸奶饮料，娃哈哈乐百氏什么的！奶奶对这些不懂，以为这些也是好东西，有时回来就试探的说人家孩子喝什么什么了。我就告诉她，那些其实都没什么营养，喝了只会伤害孩子的胃口，导致孩子不想吃饭！我可以给她买最好的酸奶和鲜奶，但是不会给她买这些饮料喝的！！所以姗姗从小到大喝的都是白开水！<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-15.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" />但是在我们的言传身教下，她认为白开水是最好喝的东东！！<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-23.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" />还有就是矿泉水！她爸有时会给她买一点农夫山泉的矿泉水！她抱着矿泉水，觉得自己也可以喝饮料了，美的乐滋滋的！！<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-15.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" />这个时候我的心里有欣慰，也有点不忍！！可是我必须这样做，我希望她能有个良好的开端！！</span></div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 还有穿的，一般我都会接受奶奶的建议，我相信老人的感觉还是对的！毕竟她经历过的春秋比我要多的多吧？有时我贪凉，会给姗姗穿的比较少，可是姗姗一咳嗽流鼻涕，我就知道自己错了<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-08.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" />但是奶奶也接受我的观点：春要捂秋要冻，孩子不要穿的太多！所以我们姗姗即使在大冬天也很少象这边的孩子裹的像个大粽子似的！也很少因为穿着而感冒过！</span></div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 下面最重要的就是教育问题了！这是个大问题，也是个令大家都头疼的问题！</span></div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 可是在这一点上，我特别感谢奶奶，她没有愚昧的固执己见，关于孩子的教育方法方式，她一切都听我们的！甚至她自己都说&ldquo;一个打一个护，一辈子不上路！&rdquo;所以在我们管教姗姗的时候，她总是站的远远的，即使她很心疼，但是她记得我们对她说过：我们管孩子的时候你千万不能护，否则孩子白受罪了，我们也白辛苦了！！而且我总是拿事实跟她说话：您看XX孩子多蛮横，他奶奶太惯他了吧？您不希望自己的孙女将来也是那么令人讨厌的孩子吧？这个时候奶奶总是腼腆的笑着，默认着<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-15.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" /></span></div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 至于培养姗姗的良好习惯方面，我也总是将论坛里学习的一些故事讲给她听。老人有时都喜欢哄骗孩子，觉得逗孩子玩是件很开心的事！可是我告诉她，孩子其实是很认真的，她在学习大人的每一言一行，您说什么她就认为是什么！我甚至把一个小姑娘听了大人的笑话，把正在哭泣的弟弟的小鸡鸡剪掉了，然后失去理智的父亲又把小姑娘从楼上扔下去整个家破人亡的故事也讲给奶奶听了！这是坛里看到的，不知道真假，但是值得我们深思！孩子是不会说戏言的，所以大人最好也不要和孩子说戏言！！奶奶听完，感慨的说：对呀，孩子懂什么呢？您大人说让她把小弟弟的鸡鸡剪掉他就不哭了，她就以为真是这样的！！所以我趁机告诉奶奶：和孩子说话，一就是一，二就是二，不要觉得骗骗她过去就行了！孩子是什么都记得的！而事实也让奶奶知道，小孩子的记性真是比我们强多了！所以奶奶谨慎的从不对姗姗乱说话，更&nbsp;不对姗姗顺便承诺什么，因为我告诉过她，我们承诺什么，最后我们必须做到，如果您做不到，就不要对孩子说。</span></div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 同样，我们也培养姗姗说话算话遵守承诺的好习惯！就拿坐摇摇车来说吧，小区里很多孩子坐上去就不愿意下来，不一会儿8块10块的就没有了，就这样，孩子还在哭闹个不停！！可姗姗算是个最听话的孩子吧！每次坐完都立刻下来，即使她还很想坐，但是她知道只能等到明天了！因为我们跟她说过：每次只能坐一下，每天只能做两次！！也就是她只能上午坐一次，下午坐一次！！从一开始我们就跟她明确的说了，那时她还不到两岁，可是我们一直遵守这个规定！第一次她哭，第二次她就知道耐心的等待下一次了！有时下班，遇到接我的她，我会问奶奶，姗姗今天坐了摇摇车了没有啊？她自己会主动的回答：我已经坐过两次了，今天不能坐了！！<img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/skin/emot/emot-37.gif\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" /></span></div><div>&nbsp;</div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 一切的一切，我们在教育着自己的同时，也在教育着孩子，而我们的奶奶也在同时和我们成长着，经历着！我觉得这就是一个家庭最大的幸福和快乐吧？我们一起遵守承诺，我们一起为孩子健康成长而努力！</span></div><div>&nbsp;</div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 还有最重要的，一定要让事实说话，这样老人才会信服你！同时我们也要最大程度的尊重老人！这样大家才能达到一致的意见！为了孩子，请我们自己先努力吧！！</span></div><div>&nbsp;</div><div><span style=\"FONT-SIZE: 14px\">亲密的祖孙俩</span></div><div><span style=\"FONT-SIZE: 14px\"><img alt=\"\" src=\"http://image.bbs.redbaby.com.cn/Upload/2008/9/11/a8e6415b-ec59-49ea-ad7c-c625ab88259a.JPG\" width=\"600\" onload=\"if(this.width&gt;700){this.height = this.height * 700 / this.width;this.width=700;}\" /></span></div>','3','laxinicer','127.0.0.1','2008-10-26 14:29:03',NULL,0),
 (29,8,24,'dsafasd','fadsfads','3','laxinicer','127.0.0.1','2008-10-27 09:20:47',NULL,0);
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (30,8,24,' ','dfgdfs','3','laxinicer','127.0.0.1','2008-10-27 09:21:47',NULL,0),
 (31,7,23,' ','范德萨福师大','5','test','192.168.49.88','2008-10-27 14:24:55',NULL,0),
 (32,7,25,'gsdfg','gdfsgfsd','3','laxinicer','127.0.0.1','2008-10-27 14:40:19',NULL,0),
 (33,9,26,'广州法帖','广州法帖广州法帖广州法帖广州法帖广州法帖','3','laxinicer','127.0.0.1','2008-10-28 12:31:59',NULL,0);
/*!40000 ALTER TABLE `ntsky_t_post` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_role`
--

DROP TABLE IF EXISTS `ntsky_t_role`;
CREATE TABLE `ntsky_t_role` (
  `id` smallint(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `description` text,
  `icon` varchar(255) NOT NULL,
  `min_topic` int(11) NOT NULL,
  `permissions` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛角色表';

--
-- Dumping data for table `ntsky_t_role`
--

/*!40000 ALTER TABLE `ntsky_t_role` DISABLE KEYS */;
INSERT INTO `ntsky_t_role` (`id`,`name`,`type`,`description`,`icon`,`min_topic`,`permissions`) VALUES 
 (0,'guest',1,'过客','level1.gif',-1,'mostReceiverUsers=10|canPostPoll=0|canBatchDelete=0|id=0|canSendMessage=0|canAnnounce=0|canViewTopic=1|canViewBestTopic=1|messageMaxSize=100|canGetAttachment=0|canReplyPost=0|inboxSize=|canCategory=0|canUseSignature=0|canDeleteOwnPost=0|canEditPost=0|canEditForum=0|canLockTopic=0|canViewUser=0|canTopTopic=0|canPostNew=0|canVote=0|canCustomFace=0|canBestTopic=0|canMoveOwn=0|uploadAttachmentSize=0|canMove=0|canEditOther=0|canDelete=0|selectIcon=level1.gif|canUploadAttachment=0|canViewForum=1|'),
 (1,'系统管理员',1,'system manager','level100.gif',-1,'mostReceiverUsers=10|canPostPoll=1|canBatchDelete=1|id=1|canSendMessage=1|canAnnounce=1|canViewTopic=1|canViewBestTopic=1|messageMaxSize=100|canGetAttachment=1|inboxSize=|canReplyPost=1|canCategory=1|canUseSignature=1|canDeleteOwnPost=1|canEditPost=1|canEditForum=1|canLockTopic=1|canViewUser=1|canTopTopic=1|canPostNew=1|canVote=1|canBestTopic=1|canCustomFace=1|canMoveOwn=1|uploadAttachmentSize=30720|canMove=0|canEditOther=1|canDelete=1|selectIcon=level1.gif|canUploadAttachment=1|canViewForum=1|'),
 (2,'超级版主',1,'可以管理多个版块','level101.gif',-1,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=30720|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=1|canMove=0|canTopTopic=1|canEditOther=1|canBestTopic=1|canLockTopic=1|canAnnounce=1|canEditForum=1|canCategory=1|canBatchDelete=1|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=20480');
INSERT INTO `ntsky_t_role` (`id`,`name`,`type`,`description`,`icon`,`min_topic`,`permissions`) VALUES 
 (3,'版主',1,'可以管理单个版块','level102.gif',-1,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=20480|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=1|canMove=0|canTopTopic=1|canEditOther=1|canBestTopic=1|canLockTopic=1|canAnnounce=1|canEditForum=1|canCategory=1|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=10240'),
 (4,'注册用户',1,'刚注册的用户','level1.gif',0,'mostReceiverUsers=10|canPostPoll=1|canBatchDelete=0|id=4|canSendMessage=1|canAnnounce=0|canViewTopic=1|canViewBestTopic=1|messageMaxSize=100|canGetAttachment=1|inboxSize=|canReplyPost=1|canCategory=0|canUseSignature=1|canDeleteOwnPost=1|canEditPost=1|canEditForum=0|canLockTopic=0|canViewUser=1|canTopTopic=0|canPostNew=1|canVote=1|canBestTopic=0|canCustomFace=1|canMoveOwn=1|uploadAttachmentSize=1024|canMove=0|canEditOther=0|canDelete=0|selectIcon=level1.gif|canUploadAttachment=1|canViewForum=1|'),
 (5,'初级会员',0,'注册基本会员','level1.gif',0,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canPostNew=1|canReplyPost=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=0|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=1024|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=1024');
INSERT INTO `ntsky_t_role` (`id`,`name`,`type`,`description`,`icon`,`min_topic`,`permissions`) VALUES 
 (6,'中级会员',0,' ','level2.gif',50,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=1024|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=1024'),
 (7,'高级会员',0,' ','level3.gif',100,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=2048|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=2048'),
 (8,'铜牌会员',0,' ','level4.gif',200,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=3072|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=3072');
INSERT INTO `ntsky_t_role` (`id`,`name`,`type`,`description`,`icon`,`min_topic`,`permissions`) VALUES 
 (9,'银牌会员',0,' ','level5.gif',400,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=4096|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=4096'),
 (10,'金牌会员',0,' ','level6.gif',1000,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=5120|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=5120');
/*!40000 ALTER TABLE `ntsky_t_role` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_stat`
--

DROP TABLE IF EXISTS `ntsky_t_stat`;
CREATE TABLE `ntsky_t_stat` (
  `id` bigint(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `place` varchar(200) NOT NULL,
  `referer` varchar(50) NOT NULL,
  `agent` varchar(100) NOT NULL,
  `language` varchar(20) NOT NULL,
  `view_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛统计表';

--
-- Dumping data for table `ntsky_t_stat`
--

/*!40000 ALTER TABLE `ntsky_t_stat` DISABLE KEYS */;
INSERT INTO `ntsky_t_stat` (`id`,`username`,`place`,`referer`,`agent`,`language`,`view_time`) VALUES 
 (1,'ntsky','深圳南山(test)','bbs/index.jsp','Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.1.4322)','zh-cn','2006-04-04 00:00:00');
/*!40000 ALTER TABLE `ntsky_t_stat` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_style`
--

DROP TABLE IF EXISTS `ntsky_t_style`;
CREATE TABLE `ntsky_t_style` (
  `style_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `main_css` text NOT NULL,
  `layout_css` text NOT NULL,
  `image_dir` varchar(20) NOT NULL default '',
  `page_index` text NOT NULL,
  PRIMARY KEY  (`style_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面样式表';

--
-- Dumping data for table `ntsky_t_style`
--

/*!40000 ALTER TABLE `ntsky_t_style` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntsky_t_style` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_topic`
--

DROP TABLE IF EXISTS `ntsky_t_topic`;
CREATE TABLE `ntsky_t_topic` (
  `id` int(11) NOT NULL,
  `forum_id` smallint(6) NOT NULL,
  `category_id` smallint(6) default '0',
  `mood` smallint(3) NOT NULL default '0',
  `title` varchar(255) NOT NULL,
  `username` varchar(20) NOT NULL,
  `views` bigint(20) default '0',
  `status` tinyint(1) default '0',
  `is_top` tinyint(1) default '0',
  `is_vote` tinyint(1) default '0',
  `date_created` datetime NOT NULL,
  `last_post_user` varchar(20) NOT NULL,
  `last_post_time` datetime default NULL,
  `replies` int(11) default '0',
  `is_delete` tinyint(1) default '0',
  PRIMARY KEY  (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_username` (`username`),
  KEY `FK_ntsky_t_topic_2` (`category_id`),
  CONSTRAINT `FK_ntsky_t_topic_2` FOREIGN KEY (`category_id`) REFERENCES `ntsky_t_category` (`id`),
  CONSTRAINT `FK_ntsky_t_topic_1` FOREIGN KEY (`username`) REFERENCES `ntsky_t_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题表';

--
-- Dumping data for table `ntsky_t_topic`
--

/*!40000 ALTER TABLE `ntsky_t_topic` DISABLE KEYS */;
INSERT INTO `ntsky_t_topic` (`id`,`forum_id`,`category_id`,`mood`,`title`,`username`,`views`,`status`,`is_top`,`is_vote`,`date_created`,`last_post_user`,`last_post_time`,`replies`,`is_delete`) VALUES 
 (1,7,3,1,'test1','admin',8,0,0,0,'2008-10-09 23:16:51','admin','2008-10-12 01:12:02',1,1),
 (2,5,3,1,'aaaaa','admin',3,1,0,0,'2008-10-12 03:58:57','admin','2008-10-12 03:58:57',0,1),
 (4,5,3,1,'阿塞的发送到非','admin',2,1,0,0,'2008-10-12 16:43:38','admin','2008-10-12 16:43:38',0,1),
 (5,7,3,3,'发贴发贴发贴发贴发贴发贴','laxinicer',9,0,0,0,'2008-10-15 02:16:58','nicer','2008-10-21 14:31:58',1,1),
 (6,7,3,1,'sdfsf','admin',1,0,0,0,'2008-10-21 10:01:59','admin','2008-10-21 10:01:59',0,1),
 (7,7,3,1,'sdfsf','admin',3,0,0,0,'2008-10-21 10:02:24','admin','2008-10-21 10:02:24',0,1),
 (8,7,4,1,'fsdfasdf','admin',3,0,0,0,'2008-10-21 10:12:19','admin','2008-10-21 10:12:19',0,1),
 (9,8,4,1,'宝宝贴图','nicer',1,0,0,0,'2008-10-21 14:11:39','nicer','2008-10-21 14:11:39',0,1),
 (10,7,4,1,'犯得上','nicer',4,0,0,0,'2008-10-21 14:11:59','nicer','2008-10-21 14:11:59',0,1),
 (11,7,5,1,'34 ','nicer',1,0,0,1,'2008-10-21 14:12:19','nicer','2008-10-21 14:12:19',0,1);
INSERT INTO `ntsky_t_topic` (`id`,`forum_id`,`category_id`,`mood`,`title`,`username`,`views`,`status`,`is_top`,`is_vote`,`date_created`,`last_post_user`,`last_post_time`,`replies`,`is_delete`) VALUES 
 (12,7,5,1,'发表投票','nicer',16,0,0,1,'2008-10-21 17:50:47','laxinicer','2008-10-22 14:35:17',1,1),
 (13,7,5,1,'dfgf','laxinicer',1,0,0,0,'2008-10-22 09:21:21','laxinicer','2008-10-22 09:21:21',0,1),
 (14,7,5,1,'发表','admin',12,0,0,0,'2008-10-24 11:05:24','admin','2008-10-24 11:05:24',0,1),
 (15,7,3,1,'asfsf','noside',9,0,0,0,'2008-10-24 11:23:47','noside','2008-10-24 11:23:47',0,1),
 (16,7,3,1,'aaaaaaaaaaaa','noside',8,0,0,0,'2008-10-24 12:37:23','noside','2008-10-24 12:37:23',0,1),
 (17,7,3,1,'投票测试','noside',24,0,0,1,'2008-10-24 13:09:19','noside','2008-10-24 13:09:19',0,1),
 (18,7,4,1,'asfdasfd','noside',4,0,0,1,'2008-10-24 13:26:12','noside','2008-10-24 13:26:12',0,1),
 (19,7,4,1,'dfsdfsafsdf','admin',5,0,0,1,'2008-10-24 13:39:22','admin','2008-10-24 13:39:22',0,1),
 (20,8,4,1,'施恩公司2008宝宝摄影大赛','noside',14,1,0,0,'2008-10-26 03:08:59','noside','2008-10-26 03:08:59',0,0),
 (21,15,4,1,'测试','noside',3,0,2,0,'2008-10-26 03:12:32','noside','2008-10-26 03:12:32',0,0);
INSERT INTO `ntsky_t_topic` (`id`,`forum_id`,`category_id`,`mood`,`title`,`username`,`views`,`status`,`is_top`,`is_vote`,`date_created`,`last_post_user`,`last_post_time`,`replies`,`is_delete`) VALUES 
 (22,8,5,1,'宝宝百天“丑”照 ','noside',19,1,0,0,'2008-10-26 03:24:08','noside','2008-10-26 03:31:46',2,0),
 (23,7,5,2,'如何和老人沟通教育孩子？？','laxinicer',12,0,2,0,'2008-10-26 14:29:03','test','2008-10-27 14:24:55',1,0),
 (24,8,3,3,'dsafasd','laxinicer',2,0,0,1,'2008-10-27 09:20:47','laxinicer','2008-10-27 09:21:47',1,0),
 (25,7,3,3,'gsdfg','laxinicer',1,0,0,0,'2008-10-27 14:40:19','laxinicer','2008-10-27 14:40:19',0,0),
 (26,9,3,4,'广州法帖','laxinicer',2,0,0,0,'2008-10-28 12:31:59','laxinicer','2008-10-28 12:31:59',0,0);
/*!40000 ALTER TABLE `ntsky_t_topic` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_user`
--

DROP TABLE IF EXISTS `ntsky_t_user`;
CREATE TABLE `ntsky_t_user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` char(32) NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `email` varchar(100) NOT NULL,
  `question` varchar(50) NOT NULL,
  `answer` varchar(50) NOT NULL,
  `roles` varchar(50) NOT NULL default '0',
  `register_time` datetime NOT NULL,
  `login_times` int(11) default '0',
  `last_login_time` datetime NOT NULL,
  `last_login_ip` varchar(25) NOT NULL,
  `is_lock` tinyint(1) default '0',
  `birthday` char(10) default '',
  `website` varchar(100) default '',
  `im_qq` varchar(12) default '',
  `im_msn` varchar(50) default '',
  `im_icq` varchar(20) default '',
  `im_yahoo` varchar(50) default '',
  `gmail` varchar(100) default '',
  `address` varchar(100) default '',
  `face` varchar(50) default '',
  `signature` varchar(255) default '',
  `intro` text,
  `money` int(11) default '0',
  `integral` int(11) default '0',
  `total_topic` int(11) default '0',
  `total_post` int(11) default '0',
  `online_time` int(11) default '0',
  `history_passwords` text,
  `is_star` tinyint(1) unsigned default '0',
  `alias` varchar(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛用户表';

--
-- Dumping data for table `ntsky_t_user`
--

/*!40000 ALTER TABLE `ntsky_t_user` DISABLE KEYS */;
INSERT INTO `ntsky_t_user` (`id`,`username`,`password`,`sex`,`email`,`question`,`answer`,`roles`,`register_time`,`login_times`,`last_login_time`,`last_login_ip`,`is_lock`,`birthday`,`website`,`im_qq`,`im_msn`,`im_icq`,`im_yahoo`,`gmail`,`address`,`face`,`signature`,`intro`,`money`,`integral`,`total_topic`,`total_post`,`online_time`,`history_passwords`,`is_star`,`alias`) VALUES 
 (1,'admin','e10adc3949ba59abbe56e057f20f883e',1,'yntsky@gmail.com','What is your name?','10f37e6eac91e4fbab892d3eca1fb2b2','1','2008-10-09 21:55:48',22,'2008-10-24 13:39:08','127.0.0.1',0,'9.17','','','','','','','','images/face/starfish.jpg','','',11,0,0,1,21,NULL,0,NULL),
 (2,'nicer','8440b94795262d6fe87a07ae7ed69ceb',0,'dasda@21cn.com','d','196b0f14eba66e10fba74dbf9e99c22f','1','2008-10-15 01:04:16',6,'2008-10-30 09:32:57','127.0.0.1',0,'','http://hi.baidu.com/laxinicer','150089207','','','','',',  , ','UserFiles/2/2_1.jpg','',NULL,-3,0,0,1,0,NULL,1,''),
 (3,'laxinicer','8440b94795262d6fe87a07ae7ed69ceb',0,'dasdsadas@21cn.com','fd','633de4b0c14ca52ea2432a3c8a5c4c31','4','2008-10-15 01:04:53',7,'2008-10-28 12:31:43','127.0.0.1',0,'','http://hi.baidu.com/laxinicer',' ',' ',' ',' ',' ',' ,  , , , , ','http://image.bbs.redbaby.com.cn/skin/face1/28.gif','',NULL,8,0,4,6,0,NULL,0,''),
 (4,'noside','96e79218965eb72c92a549dd5a330112',0,'noside@126.com','adf','7b064dad507c266a161ffc73c53dcdc5','3','2008-10-19 15:57:50',10,'2008-10-26 03:31:12','127.0.0.1',0,'9.17',' ',' ',' ',' ',' ',' ',' ','images/face/default.gif',' ',NULL,10,0,3,5,0,NULL,1,NULL);
INSERT INTO `ntsky_t_user` (`id`,`username`,`password`,`sex`,`email`,`question`,`answer`,`roles`,`register_time`,`login_times`,`last_login_time`,`last_login_ip`,`is_lock`,`birthday`,`website`,`im_qq`,`im_msn`,`im_icq`,`im_yahoo`,`gmail`,`address`,`face`,`signature`,`intro`,`money`,`integral`,`total_topic`,`total_post`,`online_time`,`history_passwords`,`is_star`,`alias`) VALUES 
 (5,'test','e10adc3949ba59abbe56e057f20f883e',0,' ',' ','7215ee9c7d9dc229d2921a40e899ec5f','4','2008-10-27 14:23:59',1,'2008-10-27 14:23:59','192.168.49.88',0,'',' ',' ',' ',' ',' ',' ',' ','images/face/default.gif',' ',' ',1,0,0,1,0,NULL,0,' '),
 (7,'lala','8440b94795262d6fe87a07ae7ed69ceb',0,'sdadsada@21cn.com','n','865c0c0b4ab0e063e5caa3387c1a8741','1','2008-11-03 14:25:12',1,'2008-11-03 14:25:12','127.0.0.1',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'images/face/default.gif',NULL,NULL,0,0,0,0,0,NULL,0,NULL);
/*!40000 ALTER TABLE `ntsky_t_user` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_user_face`
--

DROP TABLE IF EXISTS `ntsky_t_user_face`;
CREATE TABLE `ntsky_t_user_face` (
  `id` smallint(6) NOT NULL,
  `name` varchar(20) NOT NULL,
  `path` varchar(255) NOT NULL,
  `type` tinyint(2) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='头像数据信息表';

--
-- Dumping data for table `ntsky_t_user_face`
--

/*!40000 ALTER TABLE `ntsky_t_user_face` DISABLE KEYS */;
INSERT INTO `ntsky_t_user_face` (`id`,`name`,`path`,`type`) VALUES 
 (1,'蓝色的鱼','images/face/blueFish.jpg',1),
 (2,'黑猩猩','images/face/chimpanzee.jpg',1),
 (3,'狗','images/face/dog.jpg',1),
 (4,'海豚','images/face/dolphin.jpg',1),
 (5,'青蛙','images/face/frog.jpg',1),
 (6,'豚鼠','images/face/guineaPig.jpg',1),
 (7,'水母','images/face/jellyFish.jpg',1),
 (8,'小猫','images/face/kitty.jpg',1),
 (9,'瓢虫','images/face/ladyBug.jpg',1),
 (10,'猴子','images/face/monkey.jpg',1),
 (11,'鸵鸟','images/face/ostrich.jpg',1),
 (12,'猫头鹰','images/face/owl.jpg',1),
 (13,'熊猫','images/face/panda.jpg',1),
 (14,'猪','images/face/pig.jpg',1),
 (15,'蝾螈','images/face/salamander.jpg',1),
 (16,'蜘蛛','images/face/spider.jpg',1),
 (17,'海星','images/face/starfish.jpg',1),
 (18,'老虎','images/face/tiger.jpg',1),
 (19,'飞机','images/face/airplane.jpg',2),
 (20,'愤怒的猫','images/face/angryCat.jpg',2),
 (21,'愤怒的狗','images/face/angryDog.jpg',2),
 (22,'怪鱼','images/face/blueDragon.jpg',2),
 (23,'厨师','images/face/chef.jpg',2);
INSERT INTO `ntsky_t_user_face` (`id`,`name`,`path`,`type`) VALUES 
 (24,'牛仔','images/face/cowboy.jpg',2),
 (25,'螃蟹','images/face/crab.jpg',2),
 (26,'跳舞的企鹅','images/face/dancingPenguin.jpg',2),
 (27,'医生','images/face/doctor.jpg',2),
 (28,'邪恶的护士','images/face/evilNurse.jpg',2),
 (29,'喷火的青蛙','images/face/fireFrog.jpg',2),
 (30,'友好的狮子','images/face/friendlyLion.jpg',2),
 (31,'发光的星星','images/face/shinyStarFace.jpg',2),
 (32,'受惊吓的男人','images/face/shockedMan.jpg',2),
 (33,'购物女孩','images/face/shoppingGirl.jpg',2),
 (34,'唱歌的女人','images/face/singingWoman.jpg',2),
 (35,'微笑的梨','images/face/smilingPear.jpg',2),
 (36,'超人','images/face/superHero.jpg',2),
 (37,'雨伞','images/face/umbrella.jpg',2),
 (38,'听随身听的人','images/face/walkmanGuy.jpg',2),
 (39,'佛像','images/face/buddha.jpg',3),
 (40,'埃及','images/face/egypt.jpg',3),
 (41,'希腊','images/face/greece.jpg',3),
 (42,'伦敦','images/face/london.jpg',3),
 (43,'纽约','images/face/newYork.jpg',3);
INSERT INTO `ntsky_t_user_face` (`id`,`name`,`path`,`type`) VALUES 
 (44,'塔','images/face/pagoda.jpg',3),
 (45,'狮身人面像','images/face/sphinx.jpg',3),
 (46,'仙人掌','images/face/cactus.jpg',4),
 (47,'花','images/face/flower.jpg',4),
 (48,'叶子','images/face/leaf.jpg',4),
 (49,'柑桔','images/face/orange.jpg',4),
 (50,'红色的花','images/face/redFlower.jpg',4),
 (51,'红辣椒','images/face/redpepper.jpg',4),
 (52,'荷花','images/face/waterLily.jpg',4),
 (53,'蜡烛','images/face/candle.jpg',5),
 (54,'飞扬的心','images/face/flyingHeart.jpg',5),
 (55,'艺妓','images/face/geisha.jpg',5),
 (56,'红桃 A','images/face/heartAce.jpg',5),
 (57,'橙色的心','images/face/orangeHeart.jpg',5),
 (58,'外星人','images/face/alien.jpg',6),
 (59,'外星人的脑袋','images/face/alienHead.jpg',6),
 (60,'绿色的外星人','images/face/greenAlien.jpg',6),
 (61,'小机器人','images/face/littleRobot.jpg',6),
 (62,'机器虫子','images/face/robotInsect.jpg',6),
 (63,'太空','images/face/space.jpg',6),
 (64,'UFO','images/face/UFO.jpg',6);
INSERT INTO `ntsky_t_user_face` (`id`,`name`,`path`,`type`) VALUES 
 (65,'大笑的星星','images/face/laughingStar.jpg',7),
 (66,'粉红恶魔','images/face/pinkDevil.jpg',7),
 (67,'傲慢的猫','images/face/proudCat.jpg',7),
 (68,'傲慢的狗','images/face/proudDog.jpg',7),
 (69,'玩跷跷板的小孩','images/face/seeSawBoy.jpg',7),
 (70,'棒球','images/face/baseball.jpg',8),
 (71,'篮球','images/face/basketball.jpg',8),
 (72,'潜水','images/face/diving.jpg',8),
 (73,'高尔夫球','images/face/golf.jpg',8),
 (74,'曲棍球','images/face/hockey.jpg',8),
 (75,'高尔夫球','images/face/golf.jpg',8),
 (76,'溜冰鞋','images/face/iceskates.jpg',8),
 (77,'山地自行车','images/face/mountainBiking.jpg',8),
 (78,'滑雪板','images/face/snowBoarding.jpg',8),
 (79,'瑜伽','images/face/yoga.jpg',8),
 (80,'热气球','images/face/balloon.jpg',9),
 (81,'海滨气球','images/face/beachball.jpg',9),
 (82,'帆船','images/face/boat.jpg',9),
 (83,'小丑鞋','images/face/clownshoe.jpg',9),
 (84,'咖啡杯','images/face/coffeeCup.jpg',9);
INSERT INTO `ntsky_t_user_face` (`id`,`name`,`path`,`type`) VALUES 
 (85,'骰子','images/face/dice.jpg',9),
 (86,'潜水鸭子','images/face/diverDuck.jpg',9),
 (87,'飞盘','images/face/flipFlop.jpg',9),
 (88,'小矮人','images/face/gnome.jpg',9),
 (89,'帽子','images/face/hat.jpg',9),
 (90,'骷髅','images/face/skull.jpg',9),
 (91,'太阳','images/face/sun.jpg',9),
 (92,'骷髅','images/face/skull.jpg',9),
 (93,'寿司','images/face/sushi.jpg',9),
 (94,'玩具熊','images/face/teddy.jpg',9),
 (95,'三轮车','images/face/tricycle.jpg',9),
 (96,'小货车','images/face/wagon.jpg',9);
/*!40000 ALTER TABLE `ntsky_t_user_face` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_user_favorite_topic`
--

DROP TABLE IF EXISTS `ntsky_t_user_favorite_topic`;
CREATE TABLE `ntsky_t_user_favorite_topic` (
  `id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `topic_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ntsky_t_user_favorite_topic`
--

/*!40000 ALTER TABLE `ntsky_t_user_favorite_topic` DISABLE KEYS */;
INSERT INTO `ntsky_t_user_favorite_topic` (`id`,`user_id`,`topic_id`) VALUES 
 (1,3,20),
 (2,3,24),
 (3,3,26),
 (4,3,21),
 (5,6,28);
/*!40000 ALTER TABLE `ntsky_t_user_favorite_topic` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
