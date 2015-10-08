CREATE DATABASE  IF NOT EXISTS `tp_ajedrez_java` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tp_ajedrez_java`;
-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: tp_ajedrez_java
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `Jugador`
--

DROP TABLE IF EXISTS `Jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Jugador` (
  `DNI` int(11) NOT NULL,
  `Apellido` varchar(45) DEFAULT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Jugador`
--

LOCK TABLES `Jugador` WRITE;
/*!40000 ALTER TABLE `Jugador` DISABLE KEYS */;
INSERT INTO `Jugador` VALUES (11111111,'perez','seba'),(12312312,'Kasparov','garry'),(12345678,'mas','otro'),(22222222,'fulanito','cosme'),(33333330,'peon','el'),(33333333,'karpov','anatoli'),(44444444,'master','chess');
/*!40000 ALTER TABLE `Jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Partida`
--

DROP TABLE IF EXISTS `Partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Partida` (
  `idPartida` int(11) NOT NULL AUTO_INCREMENT,
  `dni1` int(11) NOT NULL,
  `dni2` int(11) NOT NULL,
  `Turno` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPartida`),
  KEY `fk_jugador_1_idx` (`dni1`),
  KEY `fk_jugador_2_idx` (`dni2`),
  CONSTRAINT `fk_jugador_1` FOREIGN KEY (`dni1`) REFERENCES `Jugador` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_jugador_2` FOREIGN KEY (`dni2`) REFERENCES `Jugador` (`DNI`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Partida`
--

LOCK TABLES `Partida` WRITE;
/*!40000 ALTER TABLE `Partida` DISABLE KEYS */;
INSERT INTO `Partida` VALUES (1,11111111,22222222,'F'),(2,11111111,22222222,'F'),(3,11111111,22222222,'F'),(4,11111111,22222222,'F'),(5,11111111,22222222,'F'),(6,22222222,11111111,'F'),(45,11111111,22222222,'B'),(47,44444444,11111111,'B'),(48,12345678,11111111,'B'),(49,12345678,11111111,'B'),(50,33333333,11111111,'B'),(51,33333330,11111111,'B'),(52,12312312,11111111,'B');
/*!40000 ALTER TABLE `Partida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Partida_Posicion`
--

DROP TABLE IF EXISTS `Partida_Posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Partida_Posicion` (
  `idPartida` int(11) NOT NULL,
  `posicion` varchar(45) NOT NULL,
  `pieza` varchar(45) NOT NULL,
  PRIMARY KEY (`idPartida`,`posicion`),
  CONSTRAINT `id_partida` FOREIGN KEY (`idPartida`) REFERENCES `Partida` (`idPartida`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Partida_Posicion`
--

LOCK TABLES `Partida_Posicion` WRITE;
/*!40000 ALTER TABLE `Partida_Posicion` DISABLE KEYS */;
INSERT INTO `Partida_Posicion` VALUES (1,'a2','TB'),(1,'a4','PB'),(1,'a6','PN'),(1,'a8','TN'),(1,'b2','PB'),(1,'b7','PN'),(1,'c1','AB'),(1,'c2','PB'),(1,'c3','CB'),(1,'c6','CN'),(1,'c7','PN'),(1,'c8','AN'),(1,'d2','PB'),(1,'d7','PN'),(1,'e1','DN'),(1,'e2','PB'),(1,'e7','PN'),(1,'e8','RN'),(1,'f1','AB'),(1,'f2','PB'),(1,'f7','PN'),(1,'f8','AN'),(1,'g1','CB'),(1,'g2','PB'),(1,'g7','PN'),(1,'g8','CN'),(1,'h1','TB'),(1,'h2','PB'),(1,'h7','PN'),(1,'h8','TN'),(2,'a1','TB'),(2,'a3','PB'),(2,'a7','PN'),(2,'a8','TN'),(2,'b1','CB'),(2,'b2','PB'),(2,'b7','PN'),(2,'b8','CN'),(2,'c1','AB'),(2,'c2','PB'),(2,'c7','PN'),(2,'c8','AN'),(2,'d2','PB'),(2,'d7','PN'),(2,'e1','DN'),(2,'e2','PB'),(2,'e7','PN'),(2,'e8','RN'),(2,'f1','AB'),(2,'f2','PB'),(2,'f7','PN'),(2,'f8','AN'),(2,'g1','CB'),(2,'g2','PB'),(2,'g7','PN'),(2,'g8','CN'),(2,'h1','TB'),(2,'h2','PB'),(2,'h7','PN'),(2,'h8','TN'),(3,'a2','TB'),(3,'a3','PB'),(3,'a6','PN'),(3,'a8','TN'),(3,'b1','CB'),(3,'b2','PB'),(3,'b7','PN'),(3,'c1','AB'),(3,'c2','PB'),(3,'c6','CN'),(3,'c7','PN'),(3,'c8','AN'),(3,'d2','PB'),(3,'d7','PN'),(3,'e1','DN'),(3,'e4','PB'),(3,'e7','PN'),(3,'e8','RN'),(3,'f1','AB'),(3,'f2','PB'),(3,'f7','PN'),(3,'f8','AN'),(3,'g1','CB'),(3,'g2','PB'),(3,'g7','PN'),(3,'g8','CN'),(3,'h1','TB'),(3,'h2','PB'),(3,'h7','PN'),(3,'h8','TN'),(4,'a4','PB'),(4,'a5','PN'),(4,'a6','TN'),(4,'b2','PB'),(4,'b3','PB'),(4,'b6','PN'),(4,'c1','AB'),(4,'c7','PN'),(4,'c8','AN'),(4,'d1','DB'),(4,'d2','PB'),(4,'d8','DN'),(4,'e1','RB'),(4,'e2','PB'),(4,'e7','PN'),(4,'e8','CB'),(4,'f1','AB'),(4,'f2','PB'),(4,'f7','PN'),(4,'f8','AN'),(4,'g1','CB'),(4,'g2','PB'),(4,'g7','PN'),(4,'g8','CN'),(4,'h1','TB'),(4,'h4','PB'),(4,'h7','PN'),(4,'h8','TN'),(5,'a1','TB'),(5,'a3','CB'),(5,'a5','PN'),(5,'b2','CN'),(5,'b5','PB'),(5,'b6','PN'),(5,'b8','CN'),(5,'c1','AB'),(5,'c2','PB'),(5,'c7','PN'),(5,'c8','AN'),(5,'d3','PB'),(5,'d7','PN'),(5,'d8','DN'),(5,'e1','RB'),(5,'e2','PB'),(5,'e7','PN'),(5,'e8','CB'),(5,'f1','AB'),(5,'f6','PN'),(5,'f8','AN'),(5,'g2','PB'),(5,'g7','PN'),(5,'h1','TB'),(5,'h2','PB'),(5,'h6','PN'),(5,'h8','TN'),(6,'a3','TB'),(6,'b1','CB'),(6,'b2','AB'),(6,'d2','PB'),(6,'e1','RB'),(6,'e5','DB'),(6,'e8','AB'),(6,'f2','PB'),(6,'f5','CN'),(6,'g1','CB'),(6,'g2','PB'),(6,'g7','PN'),(6,'h1','TB'),(6,'h2','PB'),(6,'h7','PN'),(6,'h8','TN'),(45,'a1','TB'),(45,'a2','PB'),(45,'a3','CB'),(45,'a7','PN'),(45,'a8','TN'),(45,'b2','PB'),(45,'b7','PN'),(45,'b8','CN'),(45,'c1','AB'),(45,'c2','PB'),(45,'c7','PN'),(45,'c8','AN'),(45,'d1','DB'),(45,'d2','PB'),(45,'d7','PN'),(45,'d8','DN'),(45,'e1','RB'),(45,'e2','PB'),(45,'e7','PN'),(45,'e8','RN'),(45,'f1','AB'),(45,'f2','PB'),(45,'f6','CN'),(45,'f7','PN'),(45,'f8','AN'),(45,'g2','PB'),(45,'g7','PN'),(45,'h1','TB'),(45,'h2','PB'),(45,'h4','CB'),(45,'h7','PN'),(45,'h8','TN'),(47,'a1','TB'),(47,'a2','PB'),(47,'a7','PN'),(47,'a8','TN'),(47,'b1','CB'),(47,'b2','PB'),(47,'b7','PN'),(47,'b8','CN'),(47,'c1','AB'),(47,'c2','PB'),(47,'c7','PN'),(47,'c8','AN'),(47,'d1','DB'),(47,'d2','PB'),(47,'d7','PN'),(47,'d8','DN'),(47,'e1','RB'),(47,'e2','PB'),(47,'e7','PN'),(47,'e8','RN'),(47,'f1','AB'),(47,'f2','PB'),(47,'f7','PN'),(47,'f8','AN'),(47,'g1','CB'),(47,'g2','PB'),(47,'g7','PN'),(47,'g8','CN'),(47,'h1','TB'),(47,'h2','PB'),(47,'h7','PN'),(47,'h8','TN'),(48,'a1','TB'),(48,'a2','PB'),(48,'a7','PN'),(48,'a8','TN'),(48,'b2','PB'),(48,'b7','PN'),(48,'c1','AB'),(48,'c2','PB'),(48,'c3','CB'),(48,'c6','CN'),(48,'c7','PN'),(48,'c8','AN'),(48,'d1','DB'),(48,'d2','PB'),(48,'d7','PN'),(48,'d8','DN'),(48,'e1','RB'),(48,'e2','PB'),(48,'e7','PN'),(48,'e8','RN'),(48,'f1','AB'),(48,'f2','PB'),(48,'f7','PN'),(48,'f8','AN'),(48,'g1','CB'),(48,'g2','PB'),(48,'g7','PN'),(48,'g8','CN'),(48,'h1','TB'),(48,'h2','PB'),(48,'h7','PN'),(48,'h8','TN'),(49,'a1','TB'),(49,'a2','PB'),(49,'a7','PN'),(49,'a8','TN'),(49,'b1','CB'),(49,'b2','PB'),(49,'b7','PN'),(49,'b8','CN'),(49,'c1','AB'),(49,'c2','PB'),(49,'c7','PN'),(49,'c8','AN'),(49,'d1','DB'),(49,'d2','PB'),(49,'d7','PN'),(49,'d8','DN'),(49,'e1','RB'),(49,'e2','PB'),(49,'e7','PN'),(49,'e8','RN'),(49,'f1','AB'),(49,'f2','PB'),(49,'f7','PN'),(49,'f8','AN'),(49,'g1','CB'),(49,'g2','PB'),(49,'g7','PN'),(49,'g8','CN'),(49,'h1','TB'),(49,'h2','PB'),(49,'h7','PN'),(49,'h8','TN'),(50,'a1','TB'),(50,'a2','PB'),(50,'a7','PN'),(50,'a8','TN'),(50,'b1','CB'),(50,'b2','PB'),(50,'b7','PN'),(50,'b8','CN'),(50,'c1','AB'),(50,'c2','PB'),(50,'c7','PN'),(50,'c8','AN'),(50,'d1','DB'),(50,'d2','PB'),(50,'d7','PN'),(50,'d8','DN'),(50,'e1','RB'),(50,'e2','PB'),(50,'e7','PN'),(50,'e8','RN'),(50,'f1','AB'),(50,'f2','PB'),(50,'f7','PN'),(50,'f8','AN'),(50,'g1','CB'),(50,'g2','PB'),(50,'g7','PN'),(50,'g8','CN'),(50,'h1','TB'),(50,'h2','PB'),(50,'h7','PN'),(50,'h8','TN'),(51,'a1','TB'),(51,'a2','PB'),(51,'a7','PN'),(51,'a8','TN'),(51,'b1','CB'),(51,'b2','PB'),(51,'b7','PN'),(51,'b8','CN'),(51,'c1','AB'),(51,'c2','PB'),(51,'c7','PN'),(51,'c8','AN'),(51,'d1','DB'),(51,'d2','PB'),(51,'d7','PN'),(51,'d8','DN'),(51,'e1','RB'),(51,'e2','PB'),(51,'e7','PN'),(51,'e8','RN'),(51,'f1','AB'),(51,'f2','PB'),(51,'f7','PN'),(51,'f8','AN'),(51,'g1','CB'),(51,'g2','PB'),(51,'g7','PN'),(51,'g8','CN'),(51,'h1','TB'),(51,'h2','PB'),(51,'h7','PN'),(51,'h8','TN'),(52,'a1','TB'),(52,'a2','PB'),(52,'a7','PN'),(52,'a8','TN'),(52,'b1','CB'),(52,'b2','PB'),(52,'b7','PN'),(52,'b8','CN'),(52,'c1','AB'),(52,'c2','PB'),(52,'c7','PN'),(52,'c8','AN'),(52,'d1','DB'),(52,'d2','PB'),(52,'d7','PN'),(52,'d8','DN'),(52,'e1','RB'),(52,'e2','PB'),(52,'e7','PN'),(52,'e8','RN'),(52,'f1','AB'),(52,'f2','PB'),(52,'f7','PN'),(52,'f8','AN'),(52,'g1','CB'),(52,'g2','PB'),(52,'g7','PN'),(52,'g8','CN'),(52,'h1','TB'),(52,'h2','PB'),(52,'h7','PN'),(52,'h8','TN');
/*!40000 ALTER TABLE `Partida_Posicion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-08 18:44:54
