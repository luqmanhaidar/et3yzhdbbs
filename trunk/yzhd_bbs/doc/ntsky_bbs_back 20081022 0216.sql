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
 (1,'admin','e10adc3949ba59abbe56e057f20f883e','0','2008-10-12 15:33:46','127.0.0.1');
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
 (5,'的反对法','三大幅洒的','admin',0,'2008-10-19 01:39:47',NULL),
 (6,'sdfsdf','sdfdsfsdf','admin',5,'2008-10-20 01:02:31',NULL);
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
 (3,'33.gif','33.gif',14,'Image','/yzhd_bbs/UserFiles/1/Image',' ',0,'2008-10-21 21:54:33','1');
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
 (1,14,'开心一笑',0),
 (2,5,'test1',0);
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
INSERT INTO `ntsky_t_favorite` (`id`,`name`,`url`,`user_id`,`date_created`) VALUES 
 (1,'dsfasdf','http://localhost:8080/yzhd_bbs/topic.action?topicId=10',1,'2008-10-20 22:54:37');
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
  `sign_image` varchar(50) default '',
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
 (5,'论坛公告',2,'2,',2,2,2,'admin','论坛公告','','2008-10-09 23:03:03','skins/default/99.jpg',0,0,0),
 (6,'活动中心',2,'2,',2,3,2,'','活动中心','','2008-10-09 23:03:16','skins/default/81.jpg',1,0,0),
 (7,'育儿心经',3,'3,',2,2,3,'','育儿心经','','2008-10-09 23:03:37','skins/default/122.jpg',1,0,0),
 (8,'宝宝贴图',3,'3,',2,3,3,'','宝宝贴图','','2008-10-09 23:03:56','',1,0,0),
 (9,'广州论坛',4,'4,',2,2,4,'','广州论坛','','2008-10-09 23:04:23','skins/default/33.gif',1,0,0),
 (10,'北京论坛',4,'4,',2,3,4,'','北京论坛','','2008-10-09 23:04:34','',1,0,0),
 (11,'其他论坛',0,'0',1,1,5,'','其他论坛','其他论坛','2008-10-15 00:55:24','',0,0,0);
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
 (2,'文字连接','http://replays.net',0,NULL,'文字连接',0,5);
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
 (2,'admin','nicer','欢迎信息!','\n			欢迎光临网络天空论坛系统!\n		','2008-10-15 01:04:16',0,0,0,0,1),
 (3,'admin','laxinicer','欢迎信息!','\n			欢迎光临网络天空论坛系统!\n		','2008-10-15 01:04:53',0,0,0,1,1),
 (4,'admin','aa','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-21 01:18:54',0,0,0,0,1),
 (5,'admin','super','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-21 01:39:10',0,0,0,0,1),
 (6,'admin','noside','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-21 01:39:48',0,0,0,0,1),
 (7,'admin','noside2','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-21 01:40:05',0,0,0,0,1),
 (8,'admin','noside4','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-21 01:45:13',0,1,0,0,1),
 (9,'admin','noside8','欢迎信息!','\n			欢迎光临婴知岛论坛系统!\n		','2008-10-21 02:20:38',0,0,0,0,1);
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
 (1,8,'dfdfsadfsadf',0,'2008-10-20 02:56:11'),
 (2,18,'aaaaa',0,'2008-10-21 21:51:34');
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
 (1,1,2,'sfasdf',0),
 (2,1,3,'asdfsadf',0),
 (3,1,1,'aasdsad',0),
 (4,2,3,'asdfsdf',0),
 (5,2,2,'adfsdf',0),
 (6,2,1,'fffffff',0);
/*!40000 ALTER TABLE `ntsky_t_poll_result` ENABLE KEYS */;


--
-- Definition of table `ntsky_t_poll_voter`
--

DROP TABLE IF EXISTS `ntsky_t_poll_voter`;
CREATE TABLE `ntsky_t_poll_voter` (
  `id` bigint(20) NOT NULL,
  `poll_id` int(11) NOT NULL,
  `poll_result_id` int(11) NOT NULL,
  `poll_user` int(11) NOT NULL,
  `poll_ip` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投票用户表';

--
-- Dumping data for table `ntsky_t_poll_voter`
--

/*!40000 ALTER TABLE `ntsky_t_poll_voter` DISABLE KEYS */;
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
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题回复表';

--
-- Dumping data for table `ntsky_t_post`
--

/*!40000 ALTER TABLE `ntsky_t_post` DISABLE KEYS */;
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (1,5,1,'test1','<p>aaasdfdfd</p><p><img height=\"100\" width=\"750\" alt=\"\" src=\"/ntsky_bbs/UserFiles/1/Image/adview.gif\" /></p><p>dsdfasdf</p>','1','admin','127.0.0.1','2008-10-09 23:16:51',NULL,0),
 (2,5,1,' ','asdf','1','admin','127.0.0.1','2008-10-12 01:12:02',NULL,0),
 (3,5,2,'aaaaa','****','1','admin','127.0.0.1','2008-10-12 03:58:57',NULL,0),
 (4,5,3,'test2','asdfsdfasdf','0','guest','127.0.0.1','2008-10-12 14:39:39',NULL,0),
 (5,5,4,'阿塞的发送到非','阿塞的发送到阿四大幅','1','admin','127.0.0.1','2008-10-12 16:43:38',NULL,0),
 (6,7,5,'发贴发贴发贴发贴发贴发贴','发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴发贴','3','laxinicer','127.0.0.1','2008-10-15 02:16:58',NULL,0),
 (7,5,6,'sdfsdfsdf','asdfsdfsdfsdf','1','admin','127.0.0.1','2008-10-19 03:15:59',NULL,0);
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (8,5,7,'adsfasdf','asdfsadfsdf','1','admin','127.0.0.1','2008-10-20 02:53:09',NULL,0),
 (9,5,8,'asdfsadfsadf','asdfsadfsadf','1','admin','127.0.0.1','2008-10-20 02:56:11',NULL,0),
 (10,7,9,'asdfasdf','asfasdfasfsdfsdf','1','admin','127.0.0.1','2008-10-20 03:14:32',NULL,0),
 (11,5,10,'dsfasdf','asdfasdf','1','admin','127.0.0.1','2008-10-20 04:27:46',NULL,0),
 (12,7,11,'sdfsdf','asdfsadfsadf','1','admin','127.0.0.1','2008-10-20 04:28:06',NULL,0),
 (13,7,12,'asdfsdaf','sadfsdfsdf','1','admin','127.0.0.1','2008-10-20 04:35:27',NULL,0),
 (14,7,13,'dsfasdf','asdfsdfsdf','1','admin','127.0.0.1','2008-10-20 04:35:36',NULL,0),
 (15,7,14,'的发送到发送到','岁的发送到发送到','1','admin','127.0.0.1','2008-10-20 04:36:22',NULL,0),
 (16,5,2,' ','tyertrgfd','1','admin','127.0.0.1','2008-10-20 22:53:36',NULL,0),
 (17,5,10,' ','sdfsadfsadfsdf','1','admin','127.0.0.1','2008-10-20 22:53:50',NULL,0),
 (18,5,10,'fdsfdsf','<div class=\"quote\"><span style=\"COLOR: blue\">admin的原贴: </span><br />asdfasdf </div><br />dfdsfsdf','1','admin','127.0.0.1','2008-10-20 22:55:04',NULL,0);
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (19,6,15,'asdfsdf','sdfsdfsdf','1','admin','127.0.0.1','2008-10-21 00:30:53',NULL,0),
 (20,5,2,' ','sdfsdfsdfdsf','1','admin','127.0.0.1','2008-10-21 00:33:24',NULL,0),
 (21,5,7,' ','sdfsdf','1','admin','127.0.0.1','2008-10-21 00:33:36',NULL,0),
 (22,5,7,'sdfsdf','sdfsdfdsf','1','admin','127.0.0.1','2008-10-21 00:35:10',NULL,0),
 (23,6,16,'sadfsadfsadf','sadfsadfasdfsdfsadfasdfsdf','1','admin','127.0.0.1','2008-10-21 02:41:45',NULL,0),
 (24,6,16,' ','fsdf','1','admin','127.0.0.1','2008-10-21 02:42:21',NULL,0),
 (25,5,7,' ','sdfsdfdsfsdfd','1','admin','127.0.0.1','2008-10-21 02:43:22',NULL,0),
 (26,6,17,'asdfsadfsdf','sadfsafsdaf','1','admin','127.0.0.1','2008-10-21 21:50:41',NULL,0),
 (27,5,18,'fsafasdf','asdfasdfsadf','1','admin','127.0.0.1','2008-10-21 21:51:34',NULL,0),
 (28,5,18,' ','sdfsdfdf','1','admin','127.0.0.1','2008-10-21 21:52:15',NULL,0),
 (29,5,18,' ','asdfsdf','1','admin','127.0.0.1','2008-10-21 21:52:30',NULL,0);
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (30,5,18,' ','dsfsdf','1','admin','127.0.0.1','2008-10-21 21:52:48',NULL,0),
 (31,5,18,' ','sdfsdfsdf','1','admin','127.0.0.1','2008-10-21 21:52:52',NULL,0),
 (32,5,18,' ','asdfsadfsdf','1','admin','127.0.0.1','2008-10-21 21:52:57',NULL,0),
 (33,5,18,' ','asdfsdfsdf','1','admin','127.0.0.1','2008-10-21 21:53:02',NULL,0),
 (34,5,18,' ','asdfsafsdf','1','admin','127.0.0.1','2008-10-21 21:53:07',NULL,0),
 (35,5,18,' ','asdfsdfsdf','1','admin','127.0.0.1','2008-10-21 21:53:12',NULL,0),
 (36,5,18,' ','asdfsdfsdf','1','admin','127.0.0.1','2008-10-21 21:53:18',NULL,0),
 (37,5,18,' ','sdafsdfsdf','1','admin','127.0.0.1','2008-10-21 21:53:24',NULL,0),
 (38,6,19,'sdfsdfsdf','sdfsdfsdfsdfdsf<img height=\"120\" width=\"120\" alt=\"\" src=\"/yzhd_bbs/UserFiles/1/Image/33.gif\" />','1','admin','127.0.0.1','2008-10-21 21:54:40',NULL,0),
 (39,6,19,'sadfsadf','sdfsdfsdfsdf','1','admin','127.0.0.1','2008-10-21 21:54:57',NULL,0),
 (40,6,20,'的发送到发送到','三大幅洒的','1','admin','127.0.0.1','2008-10-21 21:55:19',NULL,0);
INSERT INTO `ntsky_t_post` (`id`,`forum_id`,`topic_id`,`title`,`content`,`user_id`,`username`,`ip`,`date_created`,`date_edited`,`edit_count`) VALUES 
 (41,7,12,' ','sdfdsf','1','admin','127.0.0.1','2008-10-22 01:54:57',NULL,0),
 (42,5,18,' ','dfdfdf','1','admin','127.0.0.1','2008-10-22 02:05:49',NULL,0),
 (43,5,18,' ','sdfsdf','1','admin','127.0.0.1','2008-10-22 02:09:55',NULL,0),
 (44,5,7,' ','dsfdsf','1','admin','127.0.0.1','2008-10-22 02:13:45',NULL,0),
 (45,5,7,' ','dsgdfg','1','admin','127.0.0.1','2008-10-22 02:14:01',NULL,0);
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
 (4,'注册用户',1,'刚注册的用户','level1.gif',0,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=0|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=1024|canViewUser=1|canUseSignature=0|canCustomFace=0|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=1024'),
 (5,'初级会员',0,'注册基本会员','level1.gif',0,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canPostNew=1|canReplyPost=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=0|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=1024|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=1024');
INSERT INTO `ntsky_t_role` (`id`,`name`,`type`,`description`,`icon`,`min_topic`,`permissions`) VALUES 
 (6,'中级会员',0,' ','level2.gif',50,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=1024|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=1024'),
 (7,'高级会员',0,' ','level3.gif',100,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=2048|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=2048'),
 (8,'铜牌会员',0,' ','level4.gif',200,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=3072|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=3072');
INSERT INTO `ntsky_t_role` (`id`,`name`,`type`,`description`,`icon`,`min_topic`,`permissions`) VALUES 
 (9,'银牌会员',0,' ','level5.gif',400,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=4096|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=4096'),
 (10,'金牌会员',0,' ','level6.gif',1000,'canViewForum=1|canViewTopic=1|canViewBestTopic=1|canPostNew=1|canReplyPost=1|canReplyOther=1|canEditPost=1|canDeleteOwnPost=1|canMoveOwn=1|canPostPoll=1|canVote=1|canGetAttachment=1|canUploadAttachment=1|uploadAttachmentSize=5120|canViewUser=1|canUseSignature=1|canCustomFace=1|canDelete=0|canMove=0|canTopTopic=0|canEditOther=0|canBestTopic=0|canLockTopic=0|canAnnounce=0|canEditForum=0|canCategory=0|canBatchDelete=0|canSendMessage=1|mostReceiverUsers=10|messageMaxSize=100|inboxSize=5120'),
 (11,'fdsfsdf',0,'asdfsadf','level7.gif',2000,'mostReceiverUsers=5|canPostPoll=0|canBatchDelete=0|canSendMessage=1|canAnnounce=0|canViewTopic=1|canViewBestTopic=1|messageMaxSize=500|canGetAttachment=0|inboxSize=5000|canReplyPost=0|canCategory=0|canUseSignature=1|canDeleteOwnPost=0|canEditPost=0|canEditForum=0|canLockTopic=0|canViewUser=1|canTopTopic=0|canPostNew=0|canVote=0|canBestTopic=0|canCustomFace=1|canMoveOwn=0|uploadAttachmentSize=500|canMove=0|canEditOther=0|canDelete=0|selectIcon=level7.gif|canUploadAttachment=0|canViewForum=1|');
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
  KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题表';

--
-- Dumping data for table `ntsky_t_topic`
--

/*!40000 ALTER TABLE `ntsky_t_topic` DISABLE KEYS */;
INSERT INTO `ntsky_t_topic` (`id`,`forum_id`,`category_id`,`mood`,`title`,`username`,`views`,`status`,`is_top`,`is_vote`,`date_created`,`last_post_user`,`last_post_time`,`replies`,`is_delete`) VALUES 
 (1,5,0,1,'test1','admin',7,0,0,0,'2008-10-09 23:16:51','admin','2008-10-12 01:12:02',1,0),
 (2,5,0,1,'aaaaa','admin',4,1,0,0,'2008-10-12 03:58:57','admin','2008-10-21 00:33:24',2,1),
 (3,5,0,1,'test2','guest',2,0,1,0,'2008-10-12 14:39:39','guest','2008-10-12 14:39:39',0,1),
 (4,5,0,1,'阿塞的发送到非','admin',4,1,0,0,'2008-10-12 16:43:38','admin','2008-10-12 16:43:38',0,0),
 (6,5,0,1,'sdfsdfsdf','admin',1,0,1,0,'2008-10-19 03:15:59','admin','2008-10-19 03:15:59',0,0),
 (7,5,1,1,'adsfasdf','admin',22,0,1,0,'2008-10-20 02:53:09','admin','2008-10-22 02:14:01',5,0),
 (8,5,1,1,'asdfsadfsadf','admin',3,1,0,1,'2008-10-20 02:56:11','admin','2008-10-20 02:56:11',0,0),
 (9,7,1,1,'asdfasdf','admin',7,0,0,0,'2008-10-20 03:14:32','admin','2008-10-20 03:14:32',0,0),
 (10,5,1,1,'dsfasdf','admin',5,1,0,0,'2008-10-20 04:27:46','admin','2008-10-20 22:55:04',2,0),
 (11,7,1,1,'sdfsdf','admin',0,0,0,0,'2008-10-20 04:28:06','admin','2008-10-20 04:28:06',0,0);
INSERT INTO `ntsky_t_topic` (`id`,`forum_id`,`category_id`,`mood`,`title`,`username`,`views`,`status`,`is_top`,`is_vote`,`date_created`,`last_post_user`,`last_post_time`,`replies`,`is_delete`) VALUES 
 (12,7,1,1,'asdfsdaf','admin',16,1,0,0,'2008-10-20 04:35:27','admin','2008-10-22 01:54:57',1,0),
 (13,7,1,1,'dsfasdf','admin',1,1,0,0,'2008-10-20 04:35:36','admin','2008-10-20 04:35:36',0,0),
 (14,7,1,1,'的发送到发送到','admin',1,0,0,0,'2008-10-20 04:36:22','admin','2008-10-20 04:36:22',0,1),
 (15,6,1,1,'asdfsdf','admin',40,1,0,0,'2008-10-21 00:30:53','admin','2008-10-21 00:30:53',0,0),
 (16,6,1,1,'sadfsadfsadf','admin',4,1,0,0,'2008-10-21 02:41:45','admin','2008-10-21 02:42:21',1,0),
 (17,6,1,1,'asdfsadfsdf','admin',0,0,0,0,'2008-10-21 21:50:41','admin','2008-10-21 21:50:41',0,0),
 (18,5,1,1,'fsafasdf','admin',33,1,0,1,'2008-10-21 21:51:34','admin','2008-10-22 02:09:55',12,0),
 (19,6,1,1,'sdfsdfsdf','admin',2,0,0,0,'2008-10-21 21:54:40','admin','2008-10-21 21:54:57',1,0),
 (20,6,1,1,'的发送到发送到','admin',6,0,0,0,'2008-10-21 21:55:19','admin','2008-10-21 21:55:19',0,0);
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
 (1,'admin','e10adc3949ba59abbe56e057f20f883e',1,'yntsky@gmail.com','What is your name?','10f37e6eac91e4fbab892d3eca1fb2b2','3','2008-10-09 21:55:48',40,'2008-10-22 01:31:13','127.0.0.1',0,'9.17','','','','','','','','images/face/salamander.jpg','','',103,0,16,41,117,NULL,1,NULL),
 (2,'nicer','8440b94795262d6fe87a07ae7ed69ceb',0,'dasda@21cn.com','d','196b0f14eba66e10fba74dbf9e99c22f','1','2008-10-15 01:04:16',1,'2008-10-15 01:04:16','127.0.0.1',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'images/face/default.gif',NULL,NULL,0,0,0,0,0,NULL,1,NULL),
 (3,'laxinicer','8440b94795262d6fe87a07ae7ed69ceb',0,'dasdsadas@21cn.com','fd','633de4b0c14ca52ea2432a3c8a5c4c31','5','2008-10-15 01:04:53',3,'2008-10-15 02:16:37','127.0.0.1',0,'','http://',' ',' ',' ',' ',' ',' ','images/face/default.gif',' ',' ',-1,0,0,0,0,NULL,0,NULL),
 (4,'aa','96e79218965eb72c92a549dd5a330112',0,' ',' ','7215ee9c7d9dc229d2921a40e899ec5f','4','2008-10-21 01:18:54',1,'2008-10-21 01:18:54','127.0.0.1',0,'','http://',' ',' ',' ',' ',' ',' ','images/face/default.gif',' ',' ',0,0,0,0,0,NULL,0,NULL);
INSERT INTO `ntsky_t_user` (`id`,`username`,`password`,`sex`,`email`,`question`,`answer`,`roles`,`register_time`,`login_times`,`last_login_time`,`last_login_ip`,`is_lock`,`birthday`,`website`,`im_qq`,`im_msn`,`im_icq`,`im_yahoo`,`gmail`,`address`,`face`,`signature`,`intro`,`money`,`integral`,`total_topic`,`total_post`,`online_time`,`history_passwords`,`is_star`,`alias`) VALUES 
 (5,'super','96e79218965eb72c92a549dd5a330112',0,' ',' ','7215ee9c7d9dc229d2921a40e899ec5f','4','2008-10-21 01:39:10',1,'2008-10-21 01:39:10','127.0.0.1',0,'',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',0,0,0,0,0,NULL,0,NULL),
 (6,'noside','96e79218965eb72c92a549dd5a330112',0,' ',' ','7215ee9c7d9dc229d2921a40e899ec5f','4','2008-10-21 01:39:48',1,'2008-10-21 01:39:48','127.0.0.1',0,'',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',0,0,0,0,0,NULL,0,NULL),
 (7,'noside2','96e79218965eb72c92a549dd5a330112',0,' ',' ','7215ee9c7d9dc229d2921a40e899ec5f','4','2008-10-21 01:40:05',1,'2008-10-21 01:40:05','127.0.0.1',0,'',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',0,0,0,0,0,NULL,0,NULL),
 (8,'noside4','96e79218965eb72c92a549dd5a330112',0,' ',' ','7215ee9c7d9dc229d2921a40e899ec5f','4','2008-10-21 01:45:13',1,'2008-10-21 01:45:13','127.0.0.1',0,'',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',0,0,0,0,0,NULL,0,NULL);
INSERT INTO `ntsky_t_user` (`id`,`username`,`password`,`sex`,`email`,`question`,`answer`,`roles`,`register_time`,`login_times`,`last_login_time`,`last_login_ip`,`is_lock`,`birthday`,`website`,`im_qq`,`im_msn`,`im_icq`,`im_yahoo`,`gmail`,`address`,`face`,`signature`,`intro`,`money`,`integral`,`total_topic`,`total_post`,`online_time`,`history_passwords`,`is_star`,`alias`) VALUES 
 (9,'noside8','e10adc3949ba59abbe56e057f20f883e',0,' ',' ','7215ee9c7d9dc229d2921a40e899ec5f','4','2008-10-21 02:20:38',1,'2008-10-21 02:20:38','127.0.0.1',0,'',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',0,0,0,0,0,NULL,0,NULL);
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




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
