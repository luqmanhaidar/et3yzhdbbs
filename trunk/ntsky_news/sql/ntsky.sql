-- MySQL dump 9.07
--
-- Host: localhost    Database: ntsky
---------------------------------------------------------
-- Server version	4.0.12-nt

--
-- Table structure for table 'news'
--

CREATE TABLE news (
  newsId int(11) NOT NULL auto_increment,
  classId int(11) NOT NULL default '0',
  kindId int(11) NOT NULL default '0',
  myOther int(11) NOT NULL default '0',
  headTitle varchar(255) NOT NULL default '',
  content text NOT NULL,
  connect varchar(255) default NULL,
  author varchar(20) NOT NULL default '',
  editor varchar(20) default NULL,
  newsFrom varchar(40) default NULL,
  top int(11) default NULL,
  newsTime varchar(20) default NULL,
  hits int(11) default '0',
  state int(11) default '0',
  tag int(11) NOT NULL default '0',
  PRIMARY KEY  (newsId)
) TYPE=MyISAM;

--
-- Dumping data for table 'news'
--


--
-- Table structure for table 'newsadmin'
--

CREATE TABLE newsadmin (
  userName varchar(20) NOT NULL default '',
  passWd varchar(20) NOT NULL default '',
  purview int(11) NOT NULL default '0',
  lastLogin datetime default NULL,
  lastLoginIp varchar(20) default NULL,
  PRIMARY KEY  (userName)
) TYPE=MyISAM;

--
-- Dumping data for table 'newsadmin'
--

INSERT INTO newsadmin VALUES ('ntsky123','ntsky123',0,NULL,NULL);

--
-- Table structure for table 'newsclass'
--

CREATE TABLE newsclass (
  classId int(11) NOT NULL default '0',
  content varchar(20) NOT NULL default '',
  PRIMARY KEY  (classId)
) TYPE=MyISAM;

--
-- Dumping data for table 'newsclass'
--

INSERT INTO newsclass VALUES (1,'linux入门');
INSERT INTO newsclass VALUES (2,'服务器配置');
INSERT INTO newsclass VALUES (3,'应用开发');
INSERT INTO newsclass VALUES (4,'数据库应用');
INSERT INTO newsclass VALUES (5,'系统安全');

--
-- Table structure for table 'newscommon'
--

CREATE TABLE newscommon (
  counter int(11) NOT NULL default '0',
  ip varchar(20) NOT NULL default ''
) TYPE=MyISAM;

--
-- Dumping data for table 'newscommon'
--

INSERT INTO newscommon VALUES (1,'127.0.0.1');

--
-- Table structure for table 'newskind'
--

CREATE TABLE newskind (
  kindId int(11) NOT NULL auto_increment,
  content varchar(255) NOT NULL default '',
  classId int(11) NOT NULL default '0',
  PRIMARY KEY  (kindId)
) TYPE=MyISAM;

--
-- Dumping data for table 'newskind'
--

INSERT INTO newskind VALUES (1,'linux教程',1);
INSERT INTO newskind VALUES (2,'学习笔记',1);
INSERT INTO newskind VALUES (3,'windows',2);
INSERT INTO newskind VALUES (4,'linux',2);
INSERT INTO newskind VALUES (5,'unix',2);
INSERT INTO newskind VALUES (6,'jsp&servlet',3);
INSERT INTO newskind VALUES (7,'java',3);
INSERT INTO newskind VALUES (8,'xml',3);
INSERT INTO newskind VALUES (9,'delphi',3);
INSERT INTO newskind VALUES (10,'.net',3);
INSERT INTO newskind VALUES (11,'oracle',4);
INSERT INTO newskind VALUES (12,'mysql',4);
INSERT INTO newskind VALUES (13,'mssql',4);
INSERT INTO newskind VALUES (14,'安全防护',5);
INSERT INTO newskind VALUES (15,'漏洞攻击',5);

--
-- Table structure for table 'newspopedom'
--

CREATE TABLE newspopedom (
  gradeId int(11) NOT NULL default '0',
  grade varchar(20) default NULL,
  PRIMARY KEY  (gradeId)
) TYPE=MyISAM;

--
-- Dumping data for table 'newspopedom'
--

INSERT INTO newspopedom VALUES (3,'金牌会员');
INSERT INTO newspopedom VALUES (2,'银牌会员');
INSERT INTO newspopedom VALUES (1,'普通会员');

--
-- Table structure for table 'newsreply'
--

CREATE TABLE newsreply (
  replyId int(11) NOT NULL auto_increment,
  newsId int(11) NOT NULL default '0',
  user varchar(20) default NULL,
  content varchar(100) default NULL,
  replyTime varchar(20) default NULL,
  PRIMARY KEY  (replyId)
) TYPE=MyISAM;

--
-- Dumping data for table 'newsreply'
--


--
-- Table structure for table 'newsusr'
--

CREATE TABLE newsusr (
  userName varchar(20) NOT NULL default '',
  passWd varchar(20) NOT NULL default '',
  sex int(11) default NULL,
  question varchar(255) default NULL,
  answer varchar(255) default NULL,
  emailAddr varchar(50) default NULL,
  qq varchar(10) default NULL,
  http varchar(30) default NULL,
  purview int(11) default '1',
  regTime varchar(20) default NULL,
  PRIMARY KEY  (userName)
) TYPE=MyISAM;

--
-- Dumping data for table 'newsusr'
--


--
-- Table structure for table 'noteadmin'
--

CREATE TABLE noteadmin (
  adminName char(20) NOT NULL default '',
  adminPasswd char(20) NOT NULL default '',
  PRIMARY KEY  (adminName)
) TYPE=MyISAM;

--
-- Dumping data for table 'noteadmin'
--

INSERT INTO noteadmin VALUES ('ntsky123','ntsky123');

--
-- Table structure for table 'noteguest'
--

CREATE TABLE noteguest (
  noteId int(11) NOT NULL auto_increment,
  userName varchar(20) NOT NULL default '',
  sex int(11) NOT NULL default '0',
  email varchar(50) default NULL,
  qq varchar(9) default NULL,
  url varchar(50) default NULL,
  headTitle text NOT NULL,
  content text NOT NULL,
  image text,
  noteTime varchar(20) default NULL,
  PRIMARY KEY  (noteId)
) TYPE=MyISAM;

--
-- Dumping data for table 'noteguest'
--


--
-- Table structure for table 'notereply'
--

CREATE TABLE notereply (
  replyId int(11) NOT NULL auto_increment,
  noteId int(11) NOT NULL default '0',
  content text,
  replyTime varchar(20) default NULL,
  PRIMARY KEY  (replyId)
) TYPE=MyISAM;

--
-- Dumping data for table 'notereply'
--


