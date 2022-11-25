-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: byke
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.22.04.1

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

drop database if exists byke;
create database byke;
use byke;

--
-- Table structure for table `a_pour_distance`
--

DROP TABLE IF EXISTS `a_pour_distance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `a_pour_distance` (
                                   `id_etape_dep` int NOT NULL,
                                   `id_etape_arr` int NOT NULL,
                                   `nbr_km` decimal(6,2) DEFAULT NULL,
                                   PRIMARY KEY (`id_etape_dep`,`id_etape_arr`),
                                   KEY `id_etape_arr` (`id_etape_arr`),
                                   CONSTRAINT `a_pour_distance_ibfk_1` FOREIGN KEY (`id_etape_dep`) REFERENCES `etape` (`id_etape`),
                                   CONSTRAINT `a_pour_distance_ibfk_2` FOREIGN KEY (`id_etape_arr`) REFERENCES `etape` (`id_etape`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a_pour_distance`
--

LOCK TABLES `a_pour_distance` WRITE;
/*!40000 ALTER TABLE `a_pour_distance` DISABLE KEYS */;
INSERT INTO `a_pour_distance` VALUES (1,3,6.40),(2,4,2.60),(3,5,6.80),(4,6,12.90),(5,7,8.60),(6,8,4.60),(7,9,5.60),(8,10,6.40),(9,11,5.30),(10,12,8.10),(11,13,7.20),(12,14,6.70),(13,15,5.10),(14,16,5.10),(15,17,8.60),(16,18,2.70),(17,19,2.70),(18,20,3.70),(19,21,6.50),(20,22,2.70),(21,23,5.20),(22,24,6.50),(23,25,4.90),(24,26,5.20),(25,1,15.20),(26,27,4.90),(27,2,10.70);
/*!40000 ALTER TABLE `a_pour_distance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achete`
--

DROP TABLE IF EXISTS `achete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `achete` (
                          `id_velo` int NOT NULL,
                          `num_util` int NOT NULL,
                          `date_jour` date NOT NULL,
                          PRIMARY KEY (`id_velo`,`num_util`,`date_jour`),
                          CONSTRAINT `achete_ibfk_1` FOREIGN KEY (`id_velo`) REFERENCES `velo` (`id_velo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achete`
--

LOCK TABLES `achete` WRITE;
/*!40000 ALTER TABLE `achete` DISABLE KEYS */;
INSERT INTO `achete` VALUES (1,1,'2013-11-10'),(2,1,'2018-05-12'),(3,1,'2020-09-15');
/*!40000 ALTER TABLE `achete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etape`
--

DROP TABLE IF EXISTS `etape`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `etape` (
                         `id_etape` int NOT NULL AUTO_INCREMENT,
                         `num_etape` int NOT NULL,
                         `num_sortie` int NOT NULL,
                         `nom_etape` char(25) DEFAULT NULL,
                         `latitude` decimal(16,14) DEFAULT NULL,
                         `longitude` decimal(16,14) DEFAULT NULL,
                         PRIMARY KEY (`id_etape`),
                         UNIQUE KEY `num_etape` (`num_etape`,`num_sortie`),
                         KEY `num_sortie` (`num_sortie`),
                         CONSTRAINT `etape_ibfk_1` FOREIGN KEY (`num_sortie`) REFERENCES `sortie` (`num_sortie`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etape`
--

LOCK TABLES `etape` WRITE;
/*!40000 ALTER TABLE `etape` DISABLE KEYS */;
INSERT INTO `etape` VALUES (1,1,1,'RONTALON',45.66052627560000,4.63221406937000),(2,1,2,'COL DE LA LUERE',45.76320538304853,4.62908812807553),(3,2,1,'SAINT ANDRE LA COTE',45.63100000000000,4.60100000000000),(4,2,2,'COL DE MALVAL',45.74772421385712,4.61158104883301),(5,3,1,'SAINT MARTIN EN HAUT',45.66000000000000,4.55900000000000),(6,3,2,'DUERNE',45.68635971090647,4.52697130259405),(7,4,1,'SAINTE CATHERINE',45.39582000000000,-73.56574300000000),(8,4,2,'AVEIZE',45.68026051032419,4.48057381147755),(9,5,1,'Intersection',45.62861466155582,4.52521573815698),(10,5,2,'GREYZIEU LE MARCHE',45.66182791270027,4.42396251904246),(11,6,1,'Intersection',45.65620857484888,4.55312020725788),(12,6,2,'SAINTE FOY L\'ARGENTIERE',45.70569896294748,4.47046508170943),(13,7,1,'CROIX PERRIERE',45.68594298751449,4.57593439831060),(14,7,2,'MONTROMANT',45.70802393301300,4.52539125664541),(15,8,1,'COL DES BROSSES',45.70682093090597,4.55467737970977),(16,8,2,'LA CROIX DE PARS',45.72126840968257,4.56804910562751),(17,9,1,'COL DE MALVAL',45.74772421385712,4.61158104883301),(18,9,2,'Intersection',45.72491361448375,4.59929470994397),(19,10,1,'COL DE LA LUERE',45.76320538304853,4.62908812807553),(20,10,2,'COL DE MALVAL',45.74772421385712,4.61158104883301),(21,11,1,'POLLIONNAY',45.76592776077334,4.66072522354320),(22,11,2,'COL DE LA LUERE',45.76320538304853,4.62908812807553),(23,12,1,'SAINTE CONSORCE',45.77646635536404,4.69614559515543),(24,12,2,'POLLIONNAY',45.76592776077334,4.66072522354320),(25,13,1,'SAINT GENIS LES OLLIERES',45.75796018172125,4.72596372582408),(26,13,2,'SAINTE CONSORCE',45.77646635536404,4.69614559515543),(27,14,2,'SAINT GENIS LES OLLIERES',45.75796018172125,4.72596372582408),(34,100,1,'La bite',0.00000000000000,0.00000000000000);
/*!40000 ALTER TABLE `etape` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (35);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `route` (
                         `id_velo` int NOT NULL,
                         `type_cadre` char(20) DEFAULT NULL,
                         PRIMARY KEY (`id_velo`),
                         CONSTRAINT `route_ibfk_1` FOREIGN KEY (`id_velo`) REFERENCES `velo` (`id_velo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (2,'CARBONE');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sortie`
--

DROP TABLE IF EXISTS `sortie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `sortie` (
                          `num_sortie` int NOT NULL AUTO_INCREMENT,
                          `num_util` int NOT NULL,
                          `date_sortie` date DEFAULT NULL,
                          `heure_depart` time DEFAULT NULL,
                          `heure_arrivee` time DEFAULT NULL,
                          `lieu_depart` char(30) DEFAULT NULL,
                          `distance_parcourue` decimal(6,2) DEFAULT NULL,
                          PRIMARY KEY (`num_sortie`),
                          KEY `num_util` (`num_util`),
                          CONSTRAINT `sortie_ibfk_1` FOREIGN KEY (`num_util`) REFERENCES `utilisateur` (`num_util`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sortie`
--

LOCK TABLES `sortie` WRITE;
/*!40000 ALTER TABLE `sortie` DISABLE KEYS */;
INSERT INTO `sortie` VALUES (1,1,'2022-09-30','08:15:00','12:20:00','SAINT GENIS LES OLLLIERES',86.20),(2,2,'2022-09-23','08:15:00','12:45:00','SAINT GENIS LES OLLLIERES',88.10);
/*!40000 ALTER TABLE `sortie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
                               `num_util` int NOT NULL AUTO_INCREMENT,
                               `nom_util` char(20) DEFAULT NULL,
                               `prenom_util` char(20) DEFAULT NULL,
                               `date_naissance` date DEFAULT NULL,
                               `taille` decimal(6,2) DEFAULT NULL,
                               `poids` decimal(6,2) DEFAULT NULL,
                               PRIMARY KEY (`num_util`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'GALLOIS','Jean','1964-04-23',183.00,75.00),(2,'VILLE','Pierre','1962-04-12',173.00,69.00);
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vae`
--

DROP TABLE IF EXISTS `vae`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `vae` (
                       `id_velo` int NOT NULL,
                       `puissance_moteur` int DEFAULT NULL,
                       `puissance_batterie` int DEFAULT NULL,
                       PRIMARY KEY (`id_velo`),
                       CONSTRAINT `vae_ibfk_1` FOREIGN KEY (`id_velo`) REFERENCES `velo` (`id_velo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vae`
--

LOCK TABLES `vae` WRITE;
/*!40000 ALTER TABLE `vae` DISABLE KEYS */;
INSERT INTO `vae` VALUES (3,250,625);
/*!40000 ALTER TABLE `vae` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `velo`
--

DROP TABLE IF EXISTS `velo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `velo` (
                        `id_velo` int NOT NULL AUTO_INCREMENT,
                        `nom_velo` char(20) DEFAULT NULL,
                        `roues` decimal(6,2) DEFAULT NULL,
                        `casette` char(10) DEFAULT NULL,
                        PRIMARY KEY (`id_velo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `velo`
--

LOCK TABLES `velo` WRITE;
/*!40000 ALTER TABLE `velo` DISABLE KEYS */;
INSERT INTO `velo` VALUES (1,'LAPIERRE X-CONTROL 3',29.00,'11/42'),(2,'CANONDALE SYNASPE',700.00,'11/28'),(3,'CUBE Kathmandu Hybri',29.00,'11/52');
/*!40000 ALTER TABLE `velo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vtt`
--

DROP TABLE IF EXISTS `vtt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `vtt` (
                       `id_velo` int NOT NULL,
                       PRIMARY KEY (`id_velo`),
                       CONSTRAINT `vtt_ibfk_1` FOREIGN KEY (`id_velo`) REFERENCES `velo` (`id_velo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vtt`
--

LOCK TABLES `vtt` WRITE;
/*!40000 ALTER TABLE `vtt` DISABLE KEYS */;
INSERT INTO `vtt` VALUES (1);
/*!40000 ALTER TABLE `vtt` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-22  9:52:32
