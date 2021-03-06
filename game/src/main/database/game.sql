-- MySQL dump 10.13  Distrib 5.1.73, for redhat-linux-gnu (x86_64)
--
-- Host: localhost    Database: game
-- ------------------------------------------------------
-- Server version	5.1.73

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `game_player_info`
--

DROP TABLE IF EXISTS `game_player_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_player_info` (
  `uid` varchar(50) NOT NULL COMMENT '玩家id',
  `name` varchar(50) NOT NULL COMMENT '玩家昵称',
  `gender` int(2) DEFAULT '1',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '玩家等级',
  `current_exp` int(11) NOT NULL DEFAULT '0',
  `create_time` varchar(50) DEFAULT NULL COMMENT '角色创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `vip_level` int(11) NOT NULL DEFAULT '1',
  `silver` int(11) NOT NULL DEFAULT '0',
  `fame` int(11) NOT NULL DEFAULT '0',
  `gold` int(11) NOT NULL DEFAULT '0',
  `current_strength` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_player_info`
--

LOCK TABLES `game_player_info` WRITE;
/*!40000 ALTER TABLE `game_player_info` DISABLE KEYS */;
INSERT INTO `game_player_info` VALUES ('1-1','我是皇上',1,1,11,'2015-08-29 18:10:00','2015-08-29 10:09:47',11,22,22,55,15),('1-5','john',1,1,0,'2015-09-19 14:10:16','2015-09-19 14:10:17',1,0,0,0,0),('1-7','skull',1,1,0,'2015-09-19 15:11:26','2015-09-19 15:11:26',1,0,0,0,0),('1-8','cool',1,1,0,'2015-09-20 11:29:15','2015-09-20 03:29:15',1,0,0,0,0),('1-9','coolgirl',0,1,0,'2015-09-20 11:39:37','2015-09-20 03:39:37',1,0,0,0,0);
/*!40000 ALTER TABLE `game_player_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-20 16:25:22
