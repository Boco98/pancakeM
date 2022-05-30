CREATE DATABASE  IF NOT EXISTS `pancake1` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pancake1`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pancake1
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discount` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `discount_UNIQUE` (`discount`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
INSERT INTO `discounts` VALUES (4,0),(1,5),(2,10),(3,15);
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient_categories`
--

DROP TABLE IF EXISTS `ingredient_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `kategory_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient_categories`
--

LOCK TABLES `ingredient_categories` WRITE;
/*!40000 ALTER TABLE `ingredient_categories` DISABLE KEYS */;
INSERT INTO `ingredient_categories` VALUES (1,'baza'),(2,'fil'),(3,'preliv'),(4,'voce');
/*!40000 ALTER TABLE `ingredient_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` decimal(4,1) unsigned NOT NULL,
  `category_id` int NOT NULL,
  `healthy_ingredient` tinyint(3) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `ingredient_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,'brasno',2.5,1,001),(2,'jaja',0.4,1,001),(3,'jagoda',8.0,4,001),(4,'malina',9.0,4,001),(5,'tresnja',4.0,4,001),(6,'cokolada',2.3,3,000),(7,'slag',13.5,3,000),(8,'sladoled',7.0,3,000),(9,'margarin',10.5,2,000),(10,'pavlaka',6.7,2,000),(11,'sojino mlijeko',3.0,1,000),(12,'jogurt',2.5,1,001),(16,'brasnoNovo',22.5,1,001),(17,'MlijekoKravica',5.5,2,001),(20,'MlijekoNatura',5.0,2,001);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_datetime` datetime NOT NULL,
  `discount_id` int NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `discount_id_idx` (`discount_id`),
  CONSTRAINT `discount_id` FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2022-05-27 00:00:00',4,'opis1'),(2,'2022-05-27 00:00:00',4,'opis2'),(3,'2022-05-27 00:00:00',4,'opis2'),(4,'2022-05-27 00:00:00',4,'opis2'),(5,'2022-05-27 00:00:00',4,'opis2'),(6,'2022-05-27 00:00:00',4,'opis2'),(7,'2022-05-27 00:00:00',4,'opis45'),(8,'2022-05-27 00:00:00',4,''),(9,'2022-05-27 00:00:00',4,''),(10,'2022-05-27 00:00:00',4,''),(11,'2022-05-27 00:00:00',4,''),(12,'2020-05-27 00:00:00',2,'opis1sad22');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_pancakes`
--

DROP TABLE IF EXISTS `orders_pancakes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_pancakes` (
  `order_id` int NOT NULL,
  `pancake1_id` int NOT NULL,
  PRIMARY KEY (`order_id`,`pancake1_id`),
  KEY `pancake1_id_idx` (`pancake1_id`),
  KEY `order_id_idx` (`order_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `pancake1_id` FOREIGN KEY (`pancake1_id`) REFERENCES `pancakes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_pancakes`
--

LOCK TABLES `orders_pancakes` WRITE;
/*!40000 ALTER TABLE `orders_pancakes` DISABLE KEYS */;
INSERT INTO `orders_pancakes` VALUES (3,1),(1,2),(2,2),(6,3),(7,3),(8,3),(8,4),(10,4),(4,5),(11,5),(2,6),(5,6),(6,6),(7,6),(9,7),(1,8),(2,8),(6,8);
/*!40000 ALTER TABLE `orders_pancakes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pancakes`
--

DROP TABLE IF EXISTS `pancakes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pancakes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pancakes`
--

LOCK TABLES `pancakes` WRITE;
/*!40000 ALTER TABLE `pancakes` DISABLE KEYS */;
INSERT INTO `pancakes` VALUES (24,'AlfaRomeo'),(38,'AlfaRomeo1'),(39,'AlfaRomeo122333445555'),(2,'Americka palacinka'),(13,'Bojan1998'),(9,'Bojanova'),(8,'Cokoladne'),(41,'Idemo'),(1,'Kaiserschmarrn'),(3,'Palacinka sa sojinim mlijekom'),(5,'Palacinka-jagode'),(4,'Palacinka-maline'),(18,'PalacinkaS'),(19,'PalacinkaSlana'),(40,'Promjenjeno!'),(6,'Suzette'),(20,'WMB'),(7,'ZapeceneSuve');
/*!40000 ALTER TABLE `pancakes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pancakes_ingredients`
--

DROP TABLE IF EXISTS `pancakes_ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pancakes_ingredients` (
  `pancake_id` int NOT NULL,
  `ingredient_id` int NOT NULL,
  PRIMARY KEY (`pancake_id`,`ingredient_id`),
  KEY `pancake_id_idx` (`pancake_id`),
  KEY `ingredient_id_idx` (`ingredient_id`),
  CONSTRAINT `ingredient_id` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`),
  CONSTRAINT `pancake_id` FOREIGN KEY (`pancake_id`) REFERENCES `pancakes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pancakes_ingredients`
--

LOCK TABLES `pancakes_ingredients` WRITE;
/*!40000 ALTER TABLE `pancakes_ingredients` DISABLE KEYS */;
INSERT INTO `pancakes_ingredients` VALUES (1,1),(1,3),(1,7),(2,1),(2,2),(2,4),(2,6),(2,9),(3,1),(3,7),(3,9),(3,11),(4,1),(4,2),(4,4),(4,7),(4,9),(5,1),(5,2),(5,3),(5,8),(5,10),(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7),(6,8),(6,9),(6,10),(6,11),(7,1),(7,2),(8,1),(8,2),(8,6),(8,10),(38,1),(38,2),(39,1),(39,2),(40,1);
/*!40000 ALTER TABLE `pancakes_ingredients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-30 22:48:58
