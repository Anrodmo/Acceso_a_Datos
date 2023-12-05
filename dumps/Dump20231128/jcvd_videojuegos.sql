-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: jcvd
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `videojuegos`
--

DROP TABLE IF EXISTS `videojuegos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videojuegos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Genero` varchar(25) DEFAULT NULL,
  `FechaLanzamiento` date DEFAULT NULL,
  `Compañia` varchar(50) DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videojuegos`
--

LOCK TABLES `videojuegos` WRITE;
/*!40000 ALTER TABLE `videojuegos` DISABLE KEYS */;
INSERT INTO `videojuegos` VALUES (1,'wow','mmorpg','2023-11-21','Blizzard',0.34),(2,'wow','mmorpg','2023-11-21','Blizzard',0.34),(3,'wow','mmorpg','2023-11-21','Blizzard',0.34),(4,'wow','mmorpg','2023-11-21','Blizzard',0.34),(5,'wow','mmorpg','2023-11-21','Blizzard',0.34),(6,'wow classic','mmorpg','2023-11-21','Blizzard',49.99),(7,'Diablo IV','actionRPG','2023-11-21','Blizzard',69.99),(9,'Diablo III','actionRPG','2023-11-21','Blizzard',69.99),(11,'wow3',NULL,'2023-11-22','Blizzard',69.99),(18,'Diablo I','arpg','2023-11-24','Blizzard',49.99),(19,'Fifa','EA Sports','2022-12-05',NULL,23.56),(21,'Diablo I','arpg','2023-11-24','Blizzard',49.99),(23,'Diablo I','arpg','2023-11-24','Blizzard',49.99),(25,'Diablo I','arpg','2023-11-24','Blizzard',49.99),(27,'Diablo I','arpg','2023-11-24','Blizzard',49.99),(29,'Diablo I','arpg','2023-11-24','Blizzard',49.99),(31,'Diablo I','arpg','2023-11-24','Blizzard',49.99),(32,'nuevo',NULL,NULL,NULL,NULL),(34,'Diablo I','arpg','2023-11-27','Blizzard',49.99),(36,'Diablo I','arpg','2023-11-27','Blizzard',49.99),(37,'Baldurs Gate 3','Blizzard','2023-08-08',NULL,49.99),(39,'Diablo I','arpg','2023-11-27','Blizzard',49.99),(41,'Diablo I','arpg','2023-11-27','Blizzard',49.99),(43,'Diablo I','arpg','2023-11-27','Blizzard',49.99),(45,'Diablo I','arpg','2023-11-27','Blizzard',49.99),(47,'Diablo I','arpg','2023-11-27','Blizzard',49.99),(49,'Diablo I','arpg','2023-11-28','Blizzard',49.99),(50,'Cod V','Activision',NULL,NULL,69.80),(52,'Diablo I','arpg','2023-11-28','Blizzard',49.99),(56,'Diablo I','arpg','2023-11-28','Blizzard',49.99);
/*!40000 ALTER TABLE `videojuegos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-28 19:34:58
