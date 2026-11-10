-- MySQL dump 10.13  Distrib 9.7.2, for Win64 (x86_64)
--
-- Host: localhost    Database: thogakadefx
-- ------------------------------------------------------
-- Server version	9.7.2

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '4461fdd1-c3a6-11f1-bebf-bec18d7ece91:1-288';

--
-- Current Database: `thogakadefx`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `thogakadefx` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `thogakadefx`;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `custID` varchar(10) NOT NULL,
  `title` varchar(10) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `postalCode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`custID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('C001','Mr','Kamal Sumanasiri','1999-04-17',85000,'No 12, Main Street','Colombo','Western','00100'),('C002','Ms','Nimali Silva','1998-07-24',65000,'No 45, Lake Road','Kandy','Central','20000'),('C003','Mr','Sunil Fernando','1990-11-05',120000,'No 78, Temple Road','Galle','Southern','80000'),('C004','Mrs','Sanduni Jayasinghe','1997-02-18',75000,'No 23, Station Road','Kurunegala','North Western','60000'),('C005','Mr','Nuwan Bandara','1993-09-30',95000,'No 56, Park Avenue','Matara','Southern','81000'),('C006','Ms','Tharushi Rathnayake','2000-06-14',55000,'No 89, School Lane','Negombo','Western','11500'),('C007','Mr','Kasun Wijesinghe','1988-12-25',150000,'No 34, Beach Road','Kalutara','Western','12000'),('C008','Mrs','Dilani Kumari','1992-03-08',88000,'No 67, Hill Street','Badulla','Uva','90000'),('C009','Mr','Ravindu Madushan','1999-08-19',70000,'No 15, New Town','Anuradhapura','North Central','50000'),('C010','Ms','Ishara Hansani','1996-05-27',105000,'No 92, River Road','Ratnapura','Sabaragamuwa','70000'),('C012','Mr','Niroth Samarawickrama','2025-11-05',850000,'colombo road,panadura','panadura','western','69789');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `itemCode` varchar(10) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `packSize` varchar(20) DEFAULT NULL,
  `unitPrice` double DEFAULT NULL,
  `qtyOnHand` int DEFAULT NULL,
  PRIMARY KEY (`itemCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('P001','Keerisamba Retail','1kg',106,3000),('P002','Nadu Rice','1kg',220,1500),('P003','Red Raw Rice','1kg',210,1200),('P004','White Sugar','1kg',250,800),('P005','Dhal','500g',180,600),('P006','Milk Powder','400g',1150,350),('P007','Coconut Oil','1L',950,200),('P008','Wheat Flour','1kg',190,900),('P009','Tea Powder','200g',450,450),('P010','Salt','1kg',120,1000);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-10 14:48:44
