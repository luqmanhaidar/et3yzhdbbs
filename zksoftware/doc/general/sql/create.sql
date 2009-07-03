#2009-07-03 14:48 Edwin
CREATE DATABASE IF NOT EXISTS zksoftware;
USE zksoftware;

DROP TABLE IF EXISTS t_admin;
CREATE TABLE t_admin (
  id int(10) unsigned NOT NULL auto_increment,
  username varchar(45) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;