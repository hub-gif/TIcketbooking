-- MySQL dump 10.13  Distrib 8.4.0, for Win64 (x86_64)
--
-- Host: localhost    Database: ruangong
-- ------------------------------------------------------
-- Server version	8.4.0

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

--
-- Table structure for table `checi`
--

DROP TABLE IF EXISTS `checi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checi` (
  `StartStation` varchar(100) DEFAULT NULL,
  `EndingStation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `StartTime` varchar(100) DEFAULT NULL,
  `EndingTime` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Way` varchar(300) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Level` varchar(100) DEFAULT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `TicketNumber` int DEFAULT NULL,
  `Type` varchar(100) DEFAULT NULL,
  `ID` varchar(100) DEFAULT NULL,
  `Number` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checi`
--

LOCK TABLES `checi` WRITE;
/*!40000 ALTER TABLE `checi` DISABLE KEYS */;
INSERT INTO `checi` VALUES ('广州南','汕头','12.24','14.00','16.00','潮汕',23.5,NULL,NULL,19,'高铁','G6062',1),('广州南','汕头','12.24','12.00','16.00','潮汕',22.5,NULL,NULL,5,'火车','G6020',2);
/*!40000 ALTER TABLE `checi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information_user`
--

DROP TABLE IF EXISTS `information_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `information_user` (
  `UserName` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `PhoneNumber` varchar(100) DEFAULT NULL,
  `ID` int NOT NULL,
  `information` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information_user`
--

LOCK TABLES `information_user` WRITE;
/*!40000 ALTER TABLE `information_user` DISABLE KEYS */;
INSERT INTO `information_user` VALUES ('NewUser1','NewTest1','New7355608',100001,NULL),('TestUser3','test1','000003',100002,NULL),('TestUser2','test1','000002',100003,NULL),('123456','123456','15819128648',100004,NULL);
/*!40000 ALTER TABLE `information_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `Level` varchar(100) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Number` int NOT NULL AUTO_INCREMENT,
  `Location` varchar(100) DEFAULT NULL,
  `StartStation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `EndingStation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Way` varchar(100) DEFAULT NULL,
  `Checi_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Customer_ID` int DEFAULT NULL,
  `Ticket_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Checi_Number` int DEFAULT NULL,
  PRIMARY KEY (`Number`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES ('商务座',235,1,'靠近过道','广州南','汕头','潮汕','G6062',100004,'1',1),('一等座',90,2,'靠近过道','广州南','汕头','潮汕','G6062',NULL,'2',1),('二等座',23.5,3,'靠近过道','广州南','汕头','潮汕','G6062',NULL,'3',1),('二等座',23.5,4,'靠近过道','广州南','汕头','潮汕','G6062',NULL,'4',1),('二等座',23.5,5,'靠近过道','广州南','汕头','潮汕','G6062',NULL,'5',1),('二等座',23.5,6,'靠近过道','广州南','汕头','潮汕','G6062',NULL,'6',1),('二等座',23.5,7,'靠近过道','广州南','汕头','潮汕','G6062',NULL,'7',1),('二等座',23.5,8,'靠近过道','广州南','汕头','潮汕','G6062',NULL,'8',1),('二等座',23.5,9,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'9',1),('二等座',23.5,10,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'10',1),('二等座',23.5,11,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'11',1),('二等座',23.5,12,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'12',1),('二等座',23.5,13,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'13',1),('二等座',23.5,14,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'14',1),('二等座',23.5,15,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'15',1),('二等座',23.5,16,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'16',1),('二等座',23.5,17,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'17',1),('二等座',23.5,18,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'18',1),('二等座',23.5,19,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'19',1),('二等座',23.5,20,'靠近窗户','广州南','汕头','潮汕','G6062',NULL,'20',1),('商务座',225,21,'靠近窗户','广州南','汕头','潮汕','G6020',NULL,'21',2),('一等座',50,22,'靠近窗户','广州南','汕头','潮汕','G6020',NULL,'22',2),('二等座',22.5,23,'靠近窗户','广州南','汕头','潮汕','G6020',NULL,'23',2),('二等座',22.5,24,'靠近窗户','广州南','汕头','潮汕','G6020',NULL,'24',2),('二等座',22.5,25,'靠近窗户','广州南','汕头','潮汕','G6020',NULL,'25',2);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warden`
--

DROP TABLE IF EXISTS `warden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warden` (
  `power` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warden`
--

LOCK TABLES `warden` WRITE;
/*!40000 ALTER TABLE `warden` DISABLE KEYS */;
/*!40000 ALTER TABLE `warden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ruangong'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-26 11:59:42
