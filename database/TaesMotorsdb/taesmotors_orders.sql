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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `paid` bit(1) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `car_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `extra_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKsa6t0mutbkx4epj7gnsb0y3tc` (`car_id`),
  KEY `FKn0nawuptqdsb5vtjilh4bug43` (`customer_id`),
  KEY `FKrr5kkt3itmbw3dtr9qb2je4s2` (`employee_id`),
  KEY `FKmkdeojexwhviujn5vrwmat45b` (`extra_id`),
  CONSTRAINT `FKmkdeojexwhviujn5vrwmat45b` FOREIGN KEY (`extra_id`) REFERENCES `extras` (`extra_id`),
  CONSTRAINT `FKn0nawuptqdsb5vtjilh4bug43` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `FKrr5kkt3itmbw3dtr9qb2je4s2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  CONSTRAINT `FKsa6t0mutbkx4epj7gnsb0y3tc` FOREIGN KEY (`car_id`) REFERENCES `cars` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,_binary '',50500,1,1,1,1),(2,_binary '\0',15500,4,1,1,1),(3,_binary '\0',50500,1,1,1,1),(4,_binary '\0',50500,1,1,1,1),(5,_binary '\0',50500,1,1,1,1),(6,_binary '\0',15064.81,3,3,2,2),(7,_binary '\0',15500,4,3,2,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-12 22:01:58
