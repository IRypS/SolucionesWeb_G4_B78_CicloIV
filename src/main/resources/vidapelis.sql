CREATE DATABASE  IF NOT EXISTS `vidapelis` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vidapelis`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: vidapelis
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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id_activity` varchar(255) NOT NULL,
  `name_activity` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_activity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id_country` varchar(255) NOT NULL,
  `url_icon_country` varchar(255) DEFAULT NULL,
  `name_country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id_genre` varchar(255) NOT NULL,
  `name_genre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES ('631d2935-dbae-4fc1-b51e-c26167725a3f','Película'),('982dad54-ede7-4f87-b228-9dd688ac8878','Comedia'),('aea03770-a072-4d4b-bdfe-67b4c9e4414d','YouTube'),('af0e086f-e55c-4bfd-9ea3-54eaca50cd34','Remake'),('b678cb4b-8b4f-4f26-9aa1-90cd69f064a0','Anime');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `id_language` varchar(255) NOT NULL,
  `url_icon_language` varchar(255) DEFAULT NULL,
  `name_language` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_language`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES ('0e5c1359-0ba9-4024-a87e-99207f976ebd','/resources/icons/flags/mexico.svg','Latino'),('7f239dd3-0c47-419c-9a44-286efd481b1a','/resources/icons/flags/japan.svg','Japonés'),('baced414-c8e0-4d6e-a117-89f508cfd8ce','/resources/icons/international.svg',''),('c1640c8b-794d-43ab-a7eb-f7d357971e04','/resources/icons/flags/united-states.svg','Inglés'),('ecd758b8-7fa9-4ba8-a910-f3592c146b14','/resources/icons/flags/spain.svg','Castellano');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id_movie` varchar(255) NOT NULL,
  `cover_movie` varchar(255) DEFAULT NULL,
  `duration_movie` int DEFAULT NULL,
  `name_movie` varchar(255) DEFAULT NULL,
  `rate_movie` double DEFAULT NULL,
  `realease_movie` varchar(255) DEFAULT NULL,
  `synopsis_movie` text,
  PRIMARY KEY (`id_movie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES ('50e4cc7b-f3f6-46c2-a8b4-c89824c6c252','veenjt3qyzk0a1x6xqkl.png',100,'MADAME WEB',0,'2024-02-14','Madame Web es una próxima película estadounidense de superhéroes basada en Marvel Comics, con el personaje del mismo nombre, producida por Columbia Pictures y Di Bonaventura Pictures en asociación con Marvel Entertainment.'),('5c7d88f2-8f04-4c33-a110-6dc20520a6b6','fxc3vmj2ei8lwga5mw0f.png',100,'Bob marley',0,'2024-01-14','En la sinopsis oficial y para Cinépolis la película de Bob Marley relata la vida “de superación de la adversidad del rey del reggae y la travesía que subyace a su música revolucionaria”.'),('70defe73-5c60-4b25-a68b-4904ea03a8ef','nbowusocibukkygznvkp.png',104,'Next Goal Wins',0,'2023-11-17','Next Goal Wins es una película biográfica de comedia dramática sobre deportes dirigida por Taika Waititi, quien coescribió el guion con Iain Morris.'),('75225dd5-b7a1-40b7-90e3-d368f0532cf4','dm4dvqmu4iotfelylbci.png',1080,'THE CROWDED ROOM',0,'2023-01-01','En Manhattan, en el verano de 1979, un joven es detenido por un crimen espeluznante, y un improbable detective se ve obligado a resolver el misterio que se esconde tras él antes de que el verdadero criminal vuelva a atacar.'),('769dcb16-6438-4766-9cd9-e43115005ad9','pkzyhov9gbssgltgzujn.png',218,'Napoleón',0,'2023-11-22','Una mirada a los orígenes del comandante militar y su rápido y despiadado ascenso a emperador, visto a través del prisma de su adictiva y a menudo volátil relación con su esposa y único amor verdadero, Josefina.'),('8d085e9c-66ec-481e-9a9c-97fa4527b9fc','nvsivzipayybtdlsd40o.png',104,'Venganza silenciosa',0,'2023-12-01','Silent Night es una película estadounidense de acción y thriller dirigida por John Woo y escrita por Robert Archer Lynn. La película está protagonizada por Joel Kinnaman, Scott Mescudi, Harold Torres y Catalina Sandino Moreno, y no contiene diálogos.'),('8d300d60-c0b4-4b06-891a-5d2f107719bb','mok7lofjw8devson3sld.png',206,'LOS ASESINOS DE LA LUNA',0,'2023-10-30','Ambientada en la Oklahoma de la década de 1920, narra los asesinatos en serie de los miembros de la nación indígena Osage, que era muy rica en petróleo; una serie de crímenes brutales que más tarde se conocería como el \"Reinado del Terror\".'),('9b70377f-1a4b-482b-9544-3544f88e0484','ygy9txf49comwxsk0i6u.png',115,'Wonka (2023)',0,'2023-12-08','Basada en el personaje que protagoniza \'Charlie y la fábrica de chocolate\', el libro infantil más emblemático de Roald Dahl y uno de los más vendidos de todos los tiempos, \'Wonka\' cuenta la historia de cómo el mayor inventor, mago y chocolatero del mundo se convirtió en el querido Willy Wonka que conocemos hoy en día, centrándose en su juventud y en cómo conoció a un Oompa-Loompa en una de sus primeras aventuras. (FILMAFFINITY)'),('d790d007-661f-4345-80dc-ec2d3e5f4408','sm0hvcyfgo7dtn1nxndo.png',180,'YouTube',0,'0000-00-00','YouTube es un sitio web de origen estadounidense dedicado a compartir videos. Presenta una variedad de clips de películas, programas de televisión y vídeos musicales, así como contenidos amateur como videoblogs y YouTube Gaming.'),('dfe0b2e7-3d9e-492d-a190-6e2aefc4553e','y294m7bod93ulvyokboe.png',101,'Beekeeper: sentencia de muerte',0,'2024-01-12','La brutal campaña de venganza de Adam Clay (Jason Statham) adquiere tintes nacionales tras revelarse que es un antiguo agente de una poderosa organización clandestina conocida como \"Beekeeper\".'),('e96a4af2-76a4-48f3-aa07-149153924dfb','ubygw9v83kixn3njekh8.png',195,'ARGYLLE',0,'2024-01-01','Argylle, un superespía, se ve arrastrado a una búsqueda del tesoro que le lleva por todo el mundo. El turbulento pasado del agente secreto podría poner en peligro la misión.');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_director`
--

DROP TABLE IF EXISTS `movie_director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_director` (
  `movie_id` varchar(255) NOT NULL,
  `person_id` varchar(255) NOT NULL,
  KEY `FKrw6gkv0he6334jg0ivwdpu5rw` (`person_id`),
  KEY `FKbay4b2v2db4yfaww2oocpld9m` (`movie_id`),
  CONSTRAINT `FKbay4b2v2db4yfaww2oocpld9m` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id_movie`),
  CONSTRAINT `FKrw6gkv0he6334jg0ivwdpu5rw` FOREIGN KEY (`person_id`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_director`
--

LOCK TABLES `movie_director` WRITE;
/*!40000 ALTER TABLE `movie_director` DISABLE KEYS */;
INSERT INTO `movie_director` VALUES ('d790d007-661f-4345-80dc-ec2d3e5f4408','f7909d3c-01f4-4f15-9b94-757e1418a98b'),('d790d007-661f-4345-80dc-ec2d3e5f4408','7f6acde3-3fa9-46da-b0ce-695444701bb2'),('d790d007-661f-4345-80dc-ec2d3e5f4408','76820989-ee76-4dfe-969c-f591bf663132'),('d790d007-661f-4345-80dc-ec2d3e5f4408','5fdaca99-68ac-4f40-a87f-d7bb94a12f6f'),('d790d007-661f-4345-80dc-ec2d3e5f4408','3e9b68b8-9d57-4add-bd16-71fb16814725'),('9b70377f-1a4b-482b-9544-3544f88e0484','3e9b68b8-9d57-4add-bd16-71fb16814725'),('dfe0b2e7-3d9e-492d-a190-6e2aefc4553e','7f6acde3-3fa9-46da-b0ce-695444701bb2'),('dfe0b2e7-3d9e-492d-a190-6e2aefc4553e','f7909d3c-01f4-4f15-9b94-757e1418a98b'),('8d300d60-c0b4-4b06-891a-5d2f107719bb','5fdaca99-68ac-4f40-a87f-d7bb94a12f6f'),('8d300d60-c0b4-4b06-891a-5d2f107719bb','7f6acde3-3fa9-46da-b0ce-695444701bb2'),('8d300d60-c0b4-4b06-891a-5d2f107719bb','3e9b68b8-9d57-4add-bd16-71fb16814725'),('5c7d88f2-8f04-4c33-a110-6dc20520a6b6','3e9b68b8-9d57-4add-bd16-71fb16814725'),('50e4cc7b-f3f6-46c2-a8b4-c89824c6c252','3e9b68b8-9d57-4add-bd16-71fb16814725'),('769dcb16-6438-4766-9cd9-e43115005ad9','76820989-ee76-4dfe-969c-f591bf663132'),('75225dd5-b7a1-40b7-90e3-d368f0532cf4','7f6acde3-3fa9-46da-b0ce-695444701bb2'),('e96a4af2-76a4-48f3-aa07-149153924dfb','76820989-ee76-4dfe-969c-f591bf663132'),('70defe73-5c60-4b25-a68b-4904ea03a8ef','7f6acde3-3fa9-46da-b0ce-695444701bb2'),('70defe73-5c60-4b25-a68b-4904ea03a8ef','f7909d3c-01f4-4f15-9b94-757e1418a98b'),('8d085e9c-66ec-481e-9a9c-97fa4527b9fc','7f6acde3-3fa9-46da-b0ce-695444701bb2'),('8d085e9c-66ec-481e-9a9c-97fa4527b9fc','76820989-ee76-4dfe-969c-f591bf663132');
/*!40000 ALTER TABLE `movie_director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_genre` (
  `movie_id` varchar(255) NOT NULL,
  `genre_id` varchar(255) NOT NULL,
  KEY `FK86p3roa187k12avqfl28klp1q` (`genre_id`),
  KEY `FKp6vjabv2e2435at1hnuxg64yv` (`movie_id`),
  CONSTRAINT `FK86p3roa187k12avqfl28klp1q` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id_genre`),
  CONSTRAINT `FKp6vjabv2e2435at1hnuxg64yv` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_genre`
--

LOCK TABLES `movie_genre` WRITE;
/*!40000 ALTER TABLE `movie_genre` DISABLE KEYS */;
INSERT INTO `movie_genre` VALUES ('d790d007-661f-4345-80dc-ec2d3e5f4408','aea03770-a072-4d4b-bdfe-67b4c9e4414d'),('9b70377f-1a4b-482b-9544-3544f88e0484','af0e086f-e55c-4bfd-9ea3-54eaca50cd34'),('9b70377f-1a4b-482b-9544-3544f88e0484','631d2935-dbae-4fc1-b51e-c26167725a3f'),('dfe0b2e7-3d9e-492d-a190-6e2aefc4553e','aea03770-a072-4d4b-bdfe-67b4c9e4414d'),('dfe0b2e7-3d9e-492d-a190-6e2aefc4553e','631d2935-dbae-4fc1-b51e-c26167725a3f'),('8d300d60-c0b4-4b06-891a-5d2f107719bb','aea03770-a072-4d4b-bdfe-67b4c9e4414d'),('8d300d60-c0b4-4b06-891a-5d2f107719bb','631d2935-dbae-4fc1-b51e-c26167725a3f'),('5c7d88f2-8f04-4c33-a110-6dc20520a6b6','aea03770-a072-4d4b-bdfe-67b4c9e4414d'),('5c7d88f2-8f04-4c33-a110-6dc20520a6b6','631d2935-dbae-4fc1-b51e-c26167725a3f'),('50e4cc7b-f3f6-46c2-a8b4-c89824c6c252','631d2935-dbae-4fc1-b51e-c26167725a3f'),('769dcb16-6438-4766-9cd9-e43115005ad9','aea03770-a072-4d4b-bdfe-67b4c9e4414d'),('769dcb16-6438-4766-9cd9-e43115005ad9','af0e086f-e55c-4bfd-9ea3-54eaca50cd34'),('769dcb16-6438-4766-9cd9-e43115005ad9','631d2935-dbae-4fc1-b51e-c26167725a3f'),('75225dd5-b7a1-40b7-90e3-d368f0532cf4','aea03770-a072-4d4b-bdfe-67b4c9e4414d'),('75225dd5-b7a1-40b7-90e3-d368f0532cf4','631d2935-dbae-4fc1-b51e-c26167725a3f'),('e96a4af2-76a4-48f3-aa07-149153924dfb','af0e086f-e55c-4bfd-9ea3-54eaca50cd34'),('70defe73-5c60-4b25-a68b-4904ea03a8ef','aea03770-a072-4d4b-bdfe-67b4c9e4414d'),('70defe73-5c60-4b25-a68b-4904ea03a8ef','af0e086f-e55c-4bfd-9ea3-54eaca50cd34'),('70defe73-5c60-4b25-a68b-4904ea03a8ef','631d2935-dbae-4fc1-b51e-c26167725a3f'),('70defe73-5c60-4b25-a68b-4904ea03a8ef','982dad54-ede7-4f87-b228-9dd688ac8878'),('8d085e9c-66ec-481e-9a9c-97fa4527b9fc','af0e086f-e55c-4bfd-9ea3-54eaca50cd34'),('8d085e9c-66ec-481e-9a9c-97fa4527b9fc','aea03770-a072-4d4b-bdfe-67b4c9e4414d'),('8d085e9c-66ec-481e-9a9c-97fa4527b9fc','631d2935-dbae-4fc1-b51e-c26167725a3f');
/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id_person` varchar(255) NOT NULL,
  `birthday_person` varchar(255) DEFAULT NULL,
  `last_name_person` varchar(255) DEFAULT NULL,
  `name_person` varchar(255) DEFAULT NULL,
  `country_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('3e9b68b8-9d57-4add-bd16-71fb16814725','1973-02-09','Shinkai','Makoto',NULL),('5fdaca99-68ac-4f40-a87f-d7bb94a12f6f','1970-00-00','Berger','Edward',NULL),('76820989-ee76-4dfe-969c-f591bf663132','1969-11-30','Forster','Marc',NULL),('7f6acde3-3fa9-46da-b0ce-695444701bb2','1987-02-09','Jordan','Michael B.',NULL),('f7909d3c-01f4-4f15-9b94-757e1418a98b','1966-10-18','Taniguchi','Gorou',NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` varchar(255) NOT NULL,
  `authority_rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES ('1a6a6f13-f60c-41ca-b854-328df2ae36c7','ROLE_USER'),('a888513d-bfbe-44f0-bce8-68f14281b199','ROLE_ADMIN');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trailer`
--

DROP TABLE IF EXISTS `trailer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trailer` (
  `id_trailer` varchar(255) NOT NULL,
  `url_cover_trailer` varchar(255) DEFAULT NULL,
  `name_trailer` varchar(255) DEFAULT NULL,
  `url_trailer` varchar(255) DEFAULT NULL,
  `views_trailer` int DEFAULT NULL,
  `language_id` varchar(255) DEFAULT NULL,
  `movie_id` varchar(255) DEFAULT NULL,
  `subtitle_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_trailer`),
  KEY `FKdtsam16ay1t486er77uclpogh` (`language_id`),
  KEY `FKlmdmm3oncdy18v4008cssh81t` (`movie_id`),
  KEY `FK67xcd9d05h0adcm8v1019wb69` (`subtitle_id`),
  CONSTRAINT `FK67xcd9d05h0adcm8v1019wb69` FOREIGN KEY (`subtitle_id`) REFERENCES `language` (`id_language`),
  CONSTRAINT `FKdtsam16ay1t486er77uclpogh` FOREIGN KEY (`language_id`) REFERENCES `language` (`id_language`),
  CONSTRAINT `FKlmdmm3oncdy18v4008cssh81t` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trailer`
--

LOCK TABLES `trailer` WRITE;
/*!40000 ALTER TABLE `trailer` DISABLE KEYS */;
INSERT INTO `trailer` VALUES ('-YRw-3dgsjo','','WONKA | Tráiler Oficial | Doblado','',1,'0e5c1359-0ba9-4024-a87e-99207f976ebd','9b70377f-1a4b-482b-9544-3544f88e0484','baced414-c8e0-4d6e-a117-89f508cfd8ce'),('0uXBphTHfmo','','BEEKEEPER EL PROTECTOR Tráiler Español (2024) Jason Statham','',0,'ecd758b8-7fa9-4ba8-a910-f3592c146b14','dfe0b2e7-3d9e-492d-a190-6e2aefc4553e','baced414-c8e0-4d6e-a117-89f508cfd8ce'),('57yrWZt5z2g','','LOS ASESINOS DE LA LUNA Tráiler Español Latino (Nuevo, 2023) Leonardo DiCaprio ᴴᴰ','',0,'0e5c1359-0ba9-4024-a87e-99207f976ebd','8d300d60-c0b4-4b06-891a-5d2f107719bb','baced414-c8e0-4d6e-a117-89f508cfd8ce'),('fXTeXSpBBoA','','BOB MARLEY: LA LEYENDA Tráiler Español Latino (2023) ᴴᴰ','',1,'0e5c1359-0ba9-4024-a87e-99207f976ebd','5c7d88f2-8f04-4c33-a110-6dc20520a6b6','baced414-c8e0-4d6e-a117-89f508cfd8ce'),('hza_trxHayA','','FURIOSA Tráiler Español Latino (2024) Mad Max','',1,'0e5c1359-0ba9-4024-a87e-99207f976ebd','d790d007-661f-4345-80dc-ec2d3e5f4408','0e5c1359-0ba9-4024-a87e-99207f976ebd'),('jYttNBlfcM0','','MADAME WEB Tráiler Español Latino Subtitulado (2024)','',1,'c1640c8b-794d-43ab-a7eb-f7d357971e04','50e4cc7b-f3f6-46c2-a8b4-c89824c6c252','0e5c1359-0ba9-4024-a87e-99207f976ebd'),('NjMJwewmcfA','','NAPOLEÓN | Tráiler Oficial','',1,'c1640c8b-794d-43ab-a7eb-f7d357971e04','769dcb16-6438-4766-9cd9-e43115005ad9','0e5c1359-0ba9-4024-a87e-99207f976ebd'),('nWFP8GaH8gE','','THE CROWDED ROOM Tráiler Español (2023) Tom Holland','',1,'ecd758b8-7fa9-4ba8-a910-f3592c146b14','75225dd5-b7a1-40b7-90e3-d368f0532cf4','baced414-c8e0-4d6e-a117-89f508cfd8ce'),('pZKfEyOaUL0','','ARGYLLE Tráiler Español Latino Subtitulado (2023) Henry Cavill, Dua Lipa','',0,'c1640c8b-794d-43ab-a7eb-f7d357971e04','e96a4af2-76a4-48f3-aa07-149153924dfb','0e5c1359-0ba9-4024-a87e-99207f976ebd'),('SBk-zEIUf2s','','GOL GANA Tráiler Español Latino (2023)','',0,'0e5c1359-0ba9-4024-a87e-99207f976ebd','70defe73-5c60-4b25-a68b-4904ea03a8ef','baced414-c8e0-4d6e-a117-89f508cfd8ce'),('w4GDNK0QYcA','','VENGANZA SILENCIOSA Tráiler Español Latino (2023)','',1,'c1640c8b-794d-43ab-a7eb-f7d357971e04','8d085e9c-66ec-481e-9a9c-97fa4527b9fc','0e5c1359-0ba9-4024-a87e-99207f976ebd');
/*!40000 ALTER TABLE `trailer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` varchar(255) NOT NULL,
  `avatar_user` varchar(255) DEFAULT NULL,
  `email_user` varchar(255) DEFAULT NULL,
  `password_user` text,
  `username_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('3a226f96-e6b4-4b38-b87f-54ea97640da5',NULL,'admin@admin.com','$2a$10$T7r9PwQx57HXLUwUNuxifufvpwrKgWv5ELxpAWaZDSyYLKsVqrTJS','Admin IRypS'),('b0ce4af9-3f72-4534-93ca-d2c4631c14d4',NULL,'user@user.com','$2a$10$KcWv0M8gWC9avTMKoGrdHez/021EfyiqdL5oc/WzzjrMQsTGbMeom','User unknow');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_rol`
--

DROP TABLE IF EXISTS `user_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_rol` (
  `user_id` varchar(255) NOT NULL,
  `rol_id` varchar(255) NOT NULL,
  KEY `FKpfraq7jod5w5xd3sxm3m6y1o` (`rol_id`),
  KEY `FKkijwolbkui74em8ip1i6vniu4` (`user_id`),
  CONSTRAINT `FKkijwolbkui74em8ip1i6vniu4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`),
  CONSTRAINT `FKpfraq7jod5w5xd3sxm3m6y1o` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rol`
--

LOCK TABLES `user_rol` WRITE;
/*!40000 ALTER TABLE `user_rol` DISABLE KEYS */;
INSERT INTO `user_rol` VALUES ('3a226f96-e6b4-4b38-b87f-54ea97640da5','a888513d-bfbe-44f0-bce8-68f14281b199'),('b0ce4af9-3f72-4534-93ca-d2c4631c14d4','1a6a6f13-f60c-41ca-b854-328df2ae36c7');
/*!40000 ALTER TABLE `user_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'vidapelis'
--

--
-- Dumping routines for database 'vidapelis'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-13 13:31:02
