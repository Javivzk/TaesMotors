-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: taesmotors
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customization_option`
--

DROP TABLE IF EXISTS `customization_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customization_option` (
  `customization_option_id` bigint NOT NULL AUTO_INCREMENT,
  `accesory_id` bigint DEFAULT NULL,
  `customization_id` bigint DEFAULT NULL,
  `option_id` bigint DEFAULT NULL,
  PRIMARY KEY (`customization_option_id`),
  KEY `FKspmw6ldfvw4xpp5ty3g94ml2q` (`accesory_id`),
  KEY `FKd30n08lt1wtkshuoge723alpf` (`customization_id`),
  KEY `FKrm6o6fo70u7i2h418s08cdg7w` (`option_id`),
  CONSTRAINT `FKd30n08lt1wtkshuoge723alpf` FOREIGN KEY (`customization_id`) REFERENCES `customizations` (`customization_id`),
  CONSTRAINT `FKrm6o6fo70u7i2h418s08cdg7w` FOREIGN KEY (`option_id`) REFERENCES `options` (`option_id`),
  CONSTRAINT `FKspmw6ldfvw4xpp5ty3g94ml2q` FOREIGN KEY (`accesory_id`) REFERENCES `accesories` (`accesory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customization_option`
--

LOCK TABLES `customization_option` WRITE;
/*!40000 ALTER TABLE `customization_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `customization_option` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-23  0:15:49
