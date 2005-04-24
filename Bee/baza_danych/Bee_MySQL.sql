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
-- Table structure for table `Bee_Forum`
--

DROP TABLE IF EXISTS `Bee_Forum`;
CREATE TABLE `Bee_Forum` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Nazwa` varchar(100) NOT NULL default '',
  `Opis` varchar(255),
  PRIMARY KEY (`ID`)
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z konfiguracja forum';


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
  `ImieNazwiskoPrywatne` enum('T','N') NOT NULL default 'N',
  `Email` varchar(100) NOT NULL default '',
  `EmailPrywatny` enum('T','N') NOT NULL default 'N',
  `GG` varchar(10) NOT NULL default '',
  `GGPrywatne` enum('T','N') NOT NULL default 'N',
  `Jabber` varchar(100) NOT NULL default '',
  `JabberPrywatny` enum('T','N') NOT NULL default 'N',
  `OstatnieLogowanie` datetime NOT NULL default '0000-00-00 00:00:00',
  `BierzaceLogowanie` datetime NOT NULL default '0000-00-00 00:00:00',
  `Aktywny` enum('T','N') NOT NULL default 'N',
  `Admin` enum('T','N') NOT NULL default 'N',
  `Moderator` enum('T','N') NOT NULL default 'N',
  PRIMARY KEY  (`ID`)
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z uzytkownikami';


--
-- Table structure for table `Bee_Kategorie`
--

DROP TABLE IF EXISTS `Bee_Kategorie`;
CREATE TABLE `Bee_Kategorie` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Tytul` varchar(100) NOT NULL default '',
  `Opis` varchar(200) NOT NULL default '',
  `Aktywna` enum('T','N') NOT NULL default 'T',
  `Prywatna` enum('T','N') NOT NULL default 'N',
  PRIMARY KEY  (`ID`)
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela kategorii';

--
-- Table structure for table `Bee_Podfora`
--

DROP TABLE IF EXISTS `Bee_Podfora`;
CREATE TABLE `Bee_Podfora` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Tytul` varchar(100) NOT NULL default '',
  `Opis` varchar(200) NOT NULL default '',
  `Aktywne` enum('T','N') NOT NULL default 'T',
  `Prywatne` enum('T','N') NOT NULL default 'N',
  `LiczbaWatkow` int(10) unsigned NOT NULL default '0',
  `LiczbaWypowiedzi` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`ID`)
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela podfor';



--
-- Table structure for table `Bee_Watki`
--

DROP TABLE IF EXISTS `Bee_Watki`;
CREATE TABLE `Bee_Watki` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `ID_autora` int(10) unsigned NOT NULL default '0',
  `Autor` varchar(40) NOT NULL default '',
  `Temat` varchar(100) NOT NULL default '',
  `Data` datetime NOT NULL default '0000-00-00 00:00:00',
  `Prywatny` enum('T','N') NOT NULL default 'N',
  `Aktywny` enum('T','N') NOT NULL default 'T',
  `Zablokowany` enum('T','N') NOT NULL default 'N',
  `Zamkniety` enum('T','N') NOT NULL default 'N',
  `LiczbaWypowiedzi` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`ID`),
  KEY `id_aut` (`ID_autora`),
  CONSTRAINT `Bee_Watki_ibfk_1` FOREIGN KEY (`ID_autora`) REFERENCES `Bee_Users` (`ID`) ON UPDATE CASCADE
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z watkami';


--
-- Table structure for table `Bee_Wypowiedzi`
--

DROP TABLE IF EXISTS `Bee_Wypowiedzi`;
CREATE TABLE `Bee_Wypowiedzi` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `ID_autora` int(10) unsigned NOT NULL default '0',
  `Autor` varchar(40) NOT NULL default '',
  `Data` datetime NOT NULL default '0000-00-00 00:00:00',
  `Tekst` text NOT NULL,
  `Prywatna` enum('T','N') NOT NULL default 'N',
  `Aktywna` enum('T','N') NOT NULL default 'T',
  PRIMARY KEY  (`ID`),
  CONSTRAINT `Bee_Wypowiedzi_ibfk_1` FOREIGN KEY (`ID_autora`) REFERENCES `Bee_Users` (`ID`) ON UPDATE CASCADE
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z wypowiedziami';

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
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Lista Kategorii danego Forum';




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
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Wiele do wielu Kategorii i Podfor';


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
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela moderatorow';


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
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Wiele do wielu Watki i Wypowiedzi';


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
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Wiele do wielu Podfora i Watki';


--
-- Tabela z kluczami do zapomnianych hasel
--

DROP TABLE IF EXISTS `Bee_Forget_Passwd`;
CREATE TABLE `Bee_Forget_Passwd` (
  `Klucz` varchar(20) NOT NULL default '',
  `Email` varchar(100) NOT NULL default '',
  PRIMARY KEY (`Klucz`)
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z kluczami do zapomnianych hasel';

--
-- Tabela z kluczami do odblokowania konta nowego uzytkownika 
--

DROP TABLE IF EXISTS `Bee_New_User`;
CREATE TABLE `Bee_New_User` (
  `Klucz` varchar(20) NOT NULL default '',
  `Login` varchar(100) NOT NULL default '',
  PRIMARY KEY (`Klucz`)
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z kluczami do odblokowania konta nowego uzytkownika';


--
-- Tabela z grupami
--

DROP TABLE IF EXISTS `Bee_Groups`;
CREATE TABLE `Bee_Groups` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Nazwa` varchar(100) NOT NULL default '',
  PRIMARY KEY (`ID`)
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z dostepnymi grupami';

--
-- Tabela z grupami
--

DROP TABLE IF EXISTS `Bee_Users_Groups`;
CREATE TABLE `Bee_Users_Groups` (
  `ID_User` int(10) unsigned NOT NULL default '0',
  `ID_Group` int(10) unsigned NOT NULL default '0',
  KEY `id_u` (`ID_User`),
  KEY `id_g` (`ID_Group`),
  CONSTRAINT `Bee_Users_Groups_ibfk_1` FOREIGN KEY (`ID_User`) REFERENCES `Bee_Users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Bee_Users_Groups_ibfk_2` FOREIGN KEY (`ID_Group`) REFERENCES `Bee_Groups` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z czlonkami grup';

--
-- Tabela z grupami
--

DROP TABLE IF EXISTS `Bee_Privilages`;
CREATE TABLE `Bee_Privilages` (
  `ID_Group` int(10) unsigned NOT NULL,
  `ID_Kategoria` int(10) unsigned NULL,
  `ID_Podforum` int(10) unsigned NULL,
  `ID_Watek` int(10) unsigned NULL,
  `Czytanie` enum('T','N') NOT NULL,
  `Pisanie` enum('T','N') NOT NULL,
  KEY `id_g` (`ID_Group`),
  KEY `id_k` (`ID_Kategoria`),
  KEY `id_p` (`ID_Podforum`),
  KEY `id_w` (`ID_Watek`),
  CONSTRAINT `Bee_Privilages_ibfk_1` FOREIGN KEY (`ID_Group`) REFERENCES `Bee_Groups` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Bee_Privilages_ibfk_2` FOREIGN KEY (`ID_Kategoria`) REFERENCES `Bee_Kategorie` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Bee_Privilages_ibfk_3` FOREIGN KEY (`ID_Podforum`) REFERENCES `Bee_Podfora` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Bee_Privilages_ibfk_4` FOREIGN KEY (`ID_Watek`) REFERENCES `Bee_Watki` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CHECK ((ID_Kategoria is not NULL) AND (ID_PODFORUM is NULL) AND (ID_Watek is NULL))
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela z prawami grup';



--
-- Tabela okreslajaca zabanowanych uzytkownikow na podforach
--

DROP TABLE IF EXISTS `Bee_Banned_Users`;
CREATE TABLE `Bee_Banned_Users` (
  `ID_User` int(10) unsigned NOT NULL default '0',
  `ID_Podforum` int(10) unsigned NOT NULL default '0',
  KEY `id_u` (`ID_User`),
  KEY `id_p` (`ID_Podforum`),
  CONSTRAINT `Bee_Banned_Users_ibfk_1` FOREIGN KEY (`ID_User`) REFERENCES `Bee_Users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Bee_Banned_Users_ibfk_2` FOREIGN KEY (`ID_Podforum`) REFERENCES `Bee_Podfora` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) TYPE=InnoDB CHARACTER SET utf8 COMMENT='Tabela okreslajaca zabanowanych uzytkownikow na podforach';




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

