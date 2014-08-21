/*
SQLyog Ultimate v9.20 
MySQL - 5.0.45-community-nt : Database - project_idea_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`project_idea_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `project_idea_db`;

/*Table structure for table `act_authority` */

DROP TABLE IF EXISTS `act_authority`;

CREATE TABLE `act_authority` (
  `id` bigint(20) NOT NULL auto_increment,
  `authority` varchar(255) collate utf8_unicode_ci default NULL,
  `expired_at` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `act_authority` */

insert  into `act_authority`(`id`,`authority`,`expired_at`) values (1,'NOTE_READ',NULL),(2,'NOTE_UPDATE',NULL),(3,'NOTE_DELETE',NULL),(4,'ROLE_ADMIN',NULL),(5,'ROLE_USER',NULL),(6,'NOTE_ADD',NULL);

/*Table structure for table `act_group` */

DROP TABLE IF EXISTS `act_group`;

CREATE TABLE `act_group` (
  `id` bigint(20) NOT NULL auto_increment,
  `deny_group` tinyint(1) NOT NULL,
  `group_name` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `act_group` */

/*Table structure for table `act_group_authority` */

DROP TABLE IF EXISTS `act_group_authority`;

CREATE TABLE `act_group_authority` (
  `id` bigint(20) NOT NULL auto_increment,
  `authority_id` bigint(20) default NULL,
  `group_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `act_group_authority` */

/*Table structure for table `act_group_user` */

DROP TABLE IF EXISTS `act_group_user`;

CREATE TABLE `act_group_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `group_id` bigint(20) default NULL,
  `user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `act_group_user` */

/*Table structure for table `act_user` */

DROP TABLE IF EXISTS `act_user`;

CREATE TABLE `act_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `complete_name` varchar(255) collate utf8_unicode_ci default NULL,
  `credential_expired_at` datetime default NULL,
  `email_id` varchar(255) collate utf8_unicode_ci default NULL,
  `enabled` tinyint(1) NOT NULL,
  `last_modified` datetime default NULL,
  `password` varchar(255) collate utf8_unicode_ci default NULL,
  `profile_pic_path` varchar(255) collate utf8_unicode_ci default NULL,
  `username` varchar(255) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `act_user` */

insert  into `act_user`(`id`,`complete_name`,`credential_expired_at`,`email_id`,`enabled`,`last_modified`,`password`,`profile_pic_path`,`username`) values (1,'Anand Mohan',NULL,'anandm@mkcl.org',1,NULL,'anand',NULL,'anand');

/*Table structure for table `act_user_authority` */

DROP TABLE IF EXISTS `act_user_authority`;

CREATE TABLE `act_user_authority` (
  `id` bigint(20) NOT NULL auto_increment,
  `authority_id` bigint(20) default NULL,
  `user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `act_user_authority` */

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL auto_increment,
  `path` varchar(255) collate utf8_unicode_ci default NULL,
  `size_in_bytes` bigint(20) NOT NULL,
  `type` varchar(255) collate utf8_unicode_ci default NULL,
  `project_idea` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8AF75923FEC2CA60` (`project_idea`),
  CONSTRAINT `FK8AF75923FEC2CA60` FOREIGN KEY (`project_idea`) REFERENCES `project_idea` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `attachment` */

insert  into `attachment`(`id`,`path`,`size_in_bytes`,`type`,`project_idea`) values (1,'1661859_10154440518950720_8002093130185062221_n.jpg',26485,'jpg',2),(2,'loading1.gif',3504,'gif',2),(3,'1661859_10154440518950720_8002093130185062221_n.jpg',26485,'jpg',3),(4,'1661859_10154440518950720_8002093130185062221_n.jpg',26485,'jpg',4),(5,'1661859_10154440518950720_8002093130185062221_n.jpg',26485,'jpg',3),(6,'1661859_10154440518950720_8002093130185062221_n.jpg',26485,'jpg',3),(7,'1661859_10154440518950720_8002093130185062221_n.jpg',26485,'jpg',3),(8,'1661859_10154440518950720_8002093130185062221_n.jpg',26485,'jpg',2);

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL,
  `created_on` datetime default NULL,
  `dismissed` tinyint(1) NOT NULL,
  `message` varchar(255) collate utf8_unicode_ci default NULL,
  `resourceuri` varchar(255) collate utf8_unicode_ci default NULL,
  `type` int(11) default NULL,
  `recipient` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK237A88EBEFC7C7D8` (`recipient`),
  CONSTRAINT `FK237A88EBEFC7C7D8` FOREIGN KEY (`recipient`) REFERENCES `act_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `notification` */

/*Table structure for table `project_idea` */

DROP TABLE IF EXISTS `project_idea`;

CREATE TABLE `project_idea` (
  `id` bigint(20) NOT NULL auto_increment,
  `last_modified` datetime default NULL,
  `description` varchar(255) collate utf8_unicode_ci default NULL,
  `time_in_milliseconds` bigint(20) default NULL,
  `time_in_words` varchar(255) collate utf8_unicode_ci default NULL,
  `title` varchar(255) collate utf8_unicode_ci default NULL,
  `status` varchar(255) collate utf8_unicode_ci default NULL,
  `author` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK37FBB45D6B13774A` (`author`),
  CONSTRAINT `FK37FBB45D6B13774A` FOREIGN KEY (`author`) REFERENCES `act_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `project_idea` */

insert  into `project_idea`(`id`,`last_modified`,`description`,`time_in_milliseconds`,`time_in_words`,`title`,`status`,`author`) values (1,NULL,'Some Test Project',4567895,NULL,'Test Idea','PUBLISHED',1),(2,NULL,'<pre>Simple Test Project Idea console.log(&#34;XSS&#34;);</pre>',31104000000,'1 Year 0 Month 0 Day','test','PUBLISHED',1),(3,NULL,'cascascas',31104000000,'1 Year 0 Month 0 Day','ascsacas','PUBLISHED',1),(4,NULL,'dadasd',86400000,'0 Year 0 Month 1 Day','asas','PUBLISHED',1),(5,NULL,'asfasfas',62208000000,'2 Year 0 Month 0 Day','afasf','PUBLISHED',1),(6,NULL,'asas',31104000000,'1 Year 0 Month 0 Day','asas','PUBLISHED',1),(7,NULL,'sasdsacasc',31104000000,'1 Year 0 Month 0 Day','asasa','PUBLISHED',1),(8,NULL,'asas',518400000,'0 Year 0 Month 6 Day','asas','PUBLISHED',1),(9,NULL,'asasas',124416000000,'4 Year 0 Month 0 Day','as','PUBLISHED',1),(10,NULL,'asas',23328000000,'0 Year 9 Month 0 Day','sasas','PUBLISHED',1),(11,NULL,'dasdasd',93312000000,'3 Year 0 Month 0 Day','asadas','PUBLISHED',1),(12,NULL,'asasa',93312000000,'3 Year 0 Month 0 Day','asas','PUBLISHED',1),(13,NULL,'asas',777600000,'0 Year 0 Month 9 Day','asas','PUBLISHED',1),(14,NULL,'asas',15552000000,'0 Year 6 Month 0 Day','asas','PUBLISHED',1),(15,NULL,'asasa',155520000000,'5 Year 0 Month 0 Day','sasas','PUBLISHED',1),(16,NULL,'sdsds',15552000000,'0 Year 6 Month 0 Day','dsadsd','DRAFTED',1);

/*Table structure for table `project_idea_tags` */

DROP TABLE IF EXISTS `project_idea_tags`;

CREATE TABLE `project_idea_tags` (
  `project_idea` bigint(20) NOT NULL,
  `tags` varchar(255) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`project_idea`,`tags`),
  KEY `FK59586ABBB03FF910` (`tags`),
  KEY `FK59586ABBFEC2CA60` (`project_idea`),
  CONSTRAINT `FK59586ABBB03FF910` FOREIGN KEY (`tags`) REFERENCES `tag` (`tag`),
  CONSTRAINT `FK59586ABBFEC2CA60` FOREIGN KEY (`project_idea`) REFERENCES `project_idea` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `project_idea_tags` */

insert  into `project_idea_tags`(`project_idea`,`tags`) values (1,'angular js'),(1,'java'),(1,'jpa'),(1,'spring security'),(2,'angular js'),(2,'java'),(3,'hibernate'),(3,'jpa'),(4,'angular js'),(4,'hibernate'),(4,'java'),(5,'angular js'),(5,'java'),(5,'jpa'),(6,'angular js'),(7,'jpa'),(8,'spring mvc'),(9,'java'),(10,'jpa'),(11,'jpa'),(12,'java'),(13,'jpa'),(14,'hibernate'),(15,'hibernate'),(16,'java');

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` bigint(20) NOT NULL auto_increment,
  `last_modified` datetime default NULL,
  `remark` varchar(255) collate utf8_unicode_ci default NULL,
  `star` int(11) NOT NULL,
  `project_idea` bigint(20) default NULL,
  `user` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC84EF758FEC2CA60` (`project_idea`),
  KEY `FKC84EF758BF1D418A` (`user`),
  CONSTRAINT `FKC84EF758BF1D418A` FOREIGN KEY (`user`) REFERENCES `act_user` (`id`),
  CONSTRAINT `FKC84EF758FEC2CA60` FOREIGN KEY (`project_idea`) REFERENCES `project_idea` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `review` */

insert  into `review`(`id`,`last_modified`,`remark`,`star`,`project_idea`,`user`) values (1,'2014-08-20 12:06:50','asasasa',4,1,1),(2,'2014-08-21 14:48:37','pl\';',5,2,1),(3,'2014-08-21 17:19:49','dfgdfgdfg',3,3,1);

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `tag` varchar(255) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tag` */

insert  into `tag`(`tag`) values ('angular js'),('hibernate'),('java'),('jpa'),('spring'),('spring mvc'),('spring security');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
