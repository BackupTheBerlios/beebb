-- MySQL dump 10.9
--
-- Host: localhost    Database: Bee
-- ------------------------------------------------------
-- Server version	4.1.10-standard

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

--
-- Dumping data for table `Bee_Forum`
--


/*!40000 ALTER TABLE `Bee_Forum` DISABLE KEYS */;
LOCK TABLES `Bee_Forum` WRITE;
INSERT INTO `Bee_Forum` VALUES (1,'Zagorzelców');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Forum` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Forum_Kategorie`
--


/*!40000 ALTER TABLE `Bee_Forum_Kategorie` DISABLE KEYS */;
LOCK TABLES `Bee_Forum_Kategorie` WRITE;
INSERT INTO `Bee_Forum_Kategorie` VALUES (1,1),(1,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Forum_Kategorie` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Kategorie`
--


/*!40000 ALTER TABLE `Bee_Kategorie` DISABLE KEYS */;
LOCK TABLES `Bee_Kategorie` WRITE;
INSERT INTO `Bee_Kategorie` VALUES (1,'Piwosze'),(2,'Winiarnie');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Kategorie` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Kategorie_Podfora`
--


/*!40000 ALTER TABLE `Bee_Kategorie_Podfora` DISABLE KEYS */;
LOCK TABLES `Bee_Kategorie_Podfora` WRITE;
INSERT INTO `Bee_Kategorie_Podfora` VALUES (1,1),(1,2),(1,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Kategorie_Podfora` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Moderatorzy`
--


/*!40000 ALTER TABLE `Bee_Moderatorzy` DISABLE KEYS */;
LOCK TABLES `Bee_Moderatorzy` WRITE;
INSERT INTO `Bee_Moderatorzy` VALUES (1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Moderatorzy` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Podfora`
--


/*!40000 ALTER TABLE `Bee_Podfora` DISABLE KEYS */;
LOCK TABLES `Bee_Podfora` WRITE;
INSERT INTO `Bee_Podfora` VALUES (1,'Tyskie'),(2,'Warka'),(3,'Lech');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Podfora` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Podfora_Watki`
--


/*!40000 ALTER TABLE `Bee_Podfora_Watki` DISABLE KEYS */;
LOCK TABLES `Bee_Podfora_Watki` WRITE;
INSERT INTO `Bee_Podfora_Watki` VALUES (1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Podfora_Watki` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Users`
--


/*!40000 ALTER TABLE `Bee_Users` DISABLE KEYS */;
LOCK TABLES `Bee_Users` WRITE;
INSERT INTO `Bee_Users` VALUES (1,'piwo','e734dc5328d5a555de5f06c7c9459667','N','T'),(2,'paliwo','588ae983298ee2a03b6a37532c9f86ef','T','N');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Users` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Watki`
--


/*!40000 ALTER TABLE `Bee_Watki` DISABLE KEYS */;
LOCK TABLES `Bee_Watki` WRITE;
INSERT INTO `Bee_Watki` VALUES (1,1,'Piwko','2005-03-09 20:00:00');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Watki` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Watki_Wypowiedzi`
--


/*!40000 ALTER TABLE `Bee_Watki_Wypowiedzi` DISABLE KEYS */;
LOCK TABLES `Bee_Watki_Wypowiedzi` WRITE;
INSERT INTO `Bee_Watki_Wypowiedzi` VALUES (1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Watki_Wypowiedzi` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Wypowiedzi`
--


/*!40000 ALTER TABLE `Bee_Wypowiedzi` DISABLE KEYS */;
LOCK TABLES `Bee_Wypowiedzi` WRITE;
INSERT INTO `Bee_Wypowiedzi` VALUES (1,1,'2005-03-09 20:00:00','Piwo to moje paliwo.');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Wypowiedzi` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
