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
-- Table structure for table `Bee_True_False`
--

DROP TABLE IF EXISTS `Bee_True_False`;
CREATE TABLE `Bee_True_False` (
  `Var` char(1) NOT NULL,
  PRIMARY KEY (`Var`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela z wartosciami "T" lub "N". Sztuczny CHECK.';

INSERT INTO `Bee_True_False`(`Var`) VALUES('T');
INSERT INTO `Bee_True_False`(`Var`) VALUES('N');

--
-- Table structure for table `Bee_Forum`
--

DROP TABLE IF EXISTS `Bee_Forum`;
CREATE TABLE `Bee_Forum` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Nazwa` varchar(100) NOT NULL default '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela z konfiguracja forum';


--
-- Table structure for table `Bee_Users`
--

DROP TABLE IF EXISTS `Bee_Users`;
CREATE TABLE `Bee_Users` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Login` varchar(40) NOT NULL default '',
  `Haslo` varchar(100) NOT NULL default '',
  `Imie` varchar(50) NOT NULL default '',
  `Nazwisko` varchar(100) NOT NULL default '',
  `Email` varchar(100) NOT NULL default '',
  `GG` varchar(10) NOT NULL default '',
  `Jabber` varchar(100) NOT NULL default '',
  `OstatnieLogowanie` datetime NOT NULL default '0000-00-00 00:00:00',
  `Aktywny` char(1) NOT NULL default 'N',
  `Admin` char(1) NOT NULL default 'N',
  `Moderator` char(1) NOT NULL default 'N',
  PRIMARY KEY  (`ID`),
  CONSTRAINT `Bee_Users_ibfk_1` FOREIGN KEY (`Aktywny`) REFERENCES `Bee_True_False` (`Var`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `Bee_Users_ibfk_2` FOREIGN KEY (`Admin`) REFERENCES `Bee_True_False` (`Var`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `Bee_Users_ibfk_3` FOREIGN KEY (`Moderator`) REFERENCES `Bee_True_False` (`Var`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela z uzytkownikami';


--
-- Table structure for table `Bee_Kategorie`
--

DROP TABLE IF EXISTS `Bee_Kategorie`;
CREATE TABLE `Bee_Kategorie` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Tytul` varchar(100) NOT NULL default '',
  `Opis` varchar(200) NOT NULL default '',
  `Aktywna` char(1) NOT NULL default 'T',
  PRIMARY KEY  (`ID`),
  CONSTRAINT `Bee_Kategorie_ibfk_1` FOREIGN KEY (`Aktywna`) REFERENCES `Bee_True_False` (`Var`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela kategorii';

--
-- Table structure for table `Bee_Podfora`
--

DROP TABLE IF EXISTS `Bee_Podfora`;
CREATE TABLE `Bee_Podfora` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Tytul` varchar(100) NOT NULL default '',
  `Opis` varchar(200) NOT NULL default '',
  `Aktywne` char(1) NOT NULL default 'T',
  PRIMARY KEY  (`ID`),
  CONSTRAINT `Bee_Podfora_ibfk_1` FOREIGN KEY (`Aktywne`) REFERENCES `Bee_True_False` (`Var`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela podfor';



--
-- Table structure for table `Bee_Watki`
--

DROP TABLE IF EXISTS `Bee_Watki`;
CREATE TABLE `Bee_Watki` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `ID_autora` int(10) unsigned NOT NULL default '0',
  `Temat` varchar(100) NOT NULL default '',
  `Data` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`ID`),
  KEY `id_aut` (`ID_autora`),
  CONSTRAINT `Bee_Watki_ibfk_1` FOREIGN KEY (`ID_autora`) REFERENCES `Bee_Users` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela z watkami';


--
-- Table structure for table `Bee_Wypowiedzi`
--

DROP TABLE IF EXISTS `Bee_Wypowiedzi`;
CREATE TABLE `Bee_Wypowiedzi` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `ID_autora` int(10) unsigned NOT NULL default '0',
  `Data` datetime NOT NULL default '0000-00-00 00:00:00',
  `Tekst` text NOT NULL,
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `Autor_FK` (`ID_autora`),
  CONSTRAINT `Bee_Wypowiedzi_ibfk_1` FOREIGN KEY (`ID_autora`) REFERENCES `Bee_Users` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela z wypowiedziami';

--
-- Table structure for table `Bee_Forum_Kategorie`
--

DROP TABLE IF EXISTS `Bee_Forum_Kategorie`;
CREATE TABLE `Bee_Forum_Kategorie` (
  `ID_Forum` int(10) unsigned NOT NULL default '0',
  `ID_Kategoria` int(10) unsigned NOT NULL default '0',
  KEY `id_for` (`ID_Forum`),
  KEY `id_kat` (`ID_Kategoria`),
  CONSTRAINT `Bee_Forum_Kategorie_ibfk_2` FOREIGN KEY (`ID_Forum`) REFERENCES `Bee_Forum` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `Bee_Forum_Kategorie_ibfk_1` FOREIGN KEY (`ID_Kategoria`) REFERENCES `Bee_Kategorie` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Lista Kategorii danego Forum';




--
-- Table structure for table `Bee_Kategorie_Podfora`
--

DROP TABLE IF EXISTS `Bee_Kategorie_Podfora`;
CREATE TABLE `Bee_Kategorie_Podfora` (
  `ID_Kategoria` int(10) unsigned NOT NULL default '0',
  `ID_Podforum` int(10) unsigned NOT NULL default '0',
  KEY `id_kat` (`ID_Kategoria`),
  KEY `id_pod` (`ID_Podforum`),
  CONSTRAINT `Bee_Kategorie_Podfora_ibfk_2` FOREIGN KEY (`ID_Podforum`) REFERENCES `Bee_Podfora` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Bee_Kategorie_Podfora_ibfk_1` FOREIGN KEY (`ID_Kategoria`) REFERENCES `Bee_Kategorie` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Wiele do wielu Kategorii i Podfor';


--
-- Table structure for table `Bee_Moderatorzy`
--

DROP TABLE IF EXISTS `Bee_Moderatorzy`;
CREATE TABLE `Bee_Moderatorzy` (
  `ID_Podforum` int(10) unsigned NOT NULL default '0',
  `ID_User` int(10) unsigned NOT NULL default '0',
  KEY `id_pod` (`ID_Podforum`),
  KEY `id_use` (`ID_User`),
  CONSTRAINT `Bee_Moderatorzy_ibfk_2` FOREIGN KEY (`ID_User`) REFERENCES `Bee_Users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Bee_Moderatorzy_ibfk_1` FOREIGN KEY (`ID_Podforum`) REFERENCES `Bee_Podfora` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela moderatorow';


--
-- Table structure for table `Bee_Watki_Wypowiedzi`
--

DROP TABLE IF EXISTS `Bee_Watki_Wypowiedzi`;
CREATE TABLE `Bee_Watki_Wypowiedzi` (
  `ID_Watku` int(10) unsigned NOT NULL default '0',
  `ID_Wypowiedzi` int(10) unsigned NOT NULL default '0',
  KEY `id` (`ID_Watku`),
  KEY `id_wyp` (`ID_Wypowiedzi`),
  CONSTRAINT `Bee_Watki_Wypowiedzi_ibfk_1` FOREIGN KEY (`ID_Watku`) REFERENCES `Bee_Watki` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `Bee_Watki_Wypowiedzi_ibfk_2` FOREIGN KEY (`ID_Wypowiedzi`) REFERENCES `Bee_Wypowiedzi` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Wiele do wielu Watki i Wypowiedzi';


--
-- Table structure for table `Bee_Podfora_Wypowiedzi`
--

DROP TABLE IF EXISTS `Bee_Podfora_Watki`;
CREATE TABLE `Bee_Podfora_Watki` (
  `ID_Podfora` int(10) unsigned NOT NULL default '0',
  `ID_Watku` int(10) unsigned NOT NULL default '0',
  KEY `id_pod` (`ID_Podfora`),
  KEY `id_wat` (`ID_Watku`),
  CONSTRAINT `Bee_Podfora_Watki_ibfk_1` FOREIGN KEY (`ID_Podfora`) REFERENCES `Bee_Podfora` (`ID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `Bee_Podfora_Watki_ibfk_2` FOREIGN KEY (`ID_Watku`) REFERENCES `Bee_Watki` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Wiele do wielu Podfora i Watki';


--
-- Tabela z kluczami do zapomnianych hasel
--

DROP TABLE IF EXISTS `Bee_Forget_Passwd`;
CREATE TABLE `Bee_Forget_Passwd` (
  `Klucz` varchar(20) NOT NULL default '',
  `Email` varchar(100) NOT NULL default '',
  PRIMARY KEY (`Klucz`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2 COMMENT='Tabela z kluczami do zapomnianych hasel';



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

