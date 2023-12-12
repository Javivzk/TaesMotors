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
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `car_id` bigint NOT NULL AUTO_INCREMENT,
  `base_price` double NOT NULL,
  `brand` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `fuel` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `motor` varchar(255) NOT NULL,
  `stock` bit(1) DEFAULT NULL,
  `year` varchar(255) NOT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,50000,'Seat','Azul','Gasolina','https://preview.netcarshow.com/Seat-Leon_Cupra_R-2018-1600-04.jpg','Leon','110cv',_binary '','2022'),(2,15000,'Mazda','tan','Gasolina','https://carnovo.com/wp-content/uploads/2023/04/mazda3-2024.jpg','3','110cv',_binary '\0','2023-05-14'),(3,15000,'Mazda','mint green','Gasolina','https://carnovo.com/wp-content/uploads/2022/11/mazda-mx5.jpg','MX5','110cv',_binary '\0','2023-05-14'),(4,15000,'Mazda','lime','Gasolina','https://carnovo.com/wp-content/uploads/2021/09/mazda-cx5-2022-2.jpg','CX5','110cv',_binary '\0','2023-05-14'),(5,15000,'Seat','teal','Gasolina','https://s1.cdn.autoevolution.com/images/news/gallery/2017-seat-ibiza-starts-from-gbp-13130-in-the-uk_8.jpg','Ibiza','110cv',_binary '\0','2023-05-14'),(6,15000,'Mazda','mint green','Gasolina','https://carnovo.com/wp-content/uploads/2023/04/mazda3-2024.jpg','3','110cv',_binary '\0','2023-05-14'),(7,15000,'Mazda','ivory','Gasolina','https://carnovo.com/wp-content/uploads/2021/09/mazda-cx5-2022-2.jpg','CX5','110cv',_binary '\0','2023-05-14'),(8,15000,'Mazda','yellow','Gasolina','https://carnovo.com/wp-content/uploads/2022/11/mazda-mx5.jpg','MX5','110cv',_binary '\0','2023-05-14'),(9,15000,'Seat','olive','Gasolina','https://preview.netcarshow.com/Seat-Leon_Cupra_R-2018-1600-04.jpg','Leon','110cv',_binary '\0','2023-05-14'),(10,15000,'Seat','cyan','Gasolina','https://static.automarket.ro/img/auto_resized/db/model/004/659/733168l-1000x640-b-d427ae4f.jpg','Arona','110cv',_binary '\0','2023-05-14');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
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
