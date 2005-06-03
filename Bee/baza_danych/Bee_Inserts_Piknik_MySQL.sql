-- MySQL dump 10.9
--
-- Host: localhost    Database: Bee
-- ------------------------------------------------------
-- Server version	4.1.10a

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
INSERT INTO `Bee_Forum` (`ID`, `Nazwa`, `Opis`) VALUES (0,'Piknik','Forum prezentujące możliwości projektu Bee.');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Forum` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Forum_Kategorie`
--


/*!40000 ALTER TABLE `Bee_Forum_Kategorie` DISABLE KEYS */;
LOCK TABLES `Bee_Forum_Kategorie` WRITE;
INSERT INTO `Bee_Forum_Kategorie` (`ID_Forum`, `ID_Kategoria`) VALUES (0,1),(0,2),(0,3),(0,4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Forum_Kategorie` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Kategorie`
--


/*!40000 ALTER TABLE `Bee_Kategorie` DISABLE KEYS */;
LOCK TABLES `Bee_Kategorie` WRITE;
INSERT INTO `Bee_Kategorie` (`ID`, `Tytul`, `Opis`, `Aktywna`, `Prywatna`) VALUES (1,'Gry','Kategoria o grach komputerowych','T','N'),
(2,'Książki','Kategoria o książkach','T','N'),
(3,'O forum','Miejsca gdzie mozna wypowiedzieć się o forum','T','N'),
(4,'Inne','Inne dyskusje.','T','N');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Kategorie` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Kategorie_Podfora`
--


/*!40000 ALTER TABLE `Bee_Kategorie_Podfora` DISABLE KEYS */;
LOCK TABLES `Bee_Kategorie_Podfora` WRITE;
INSERT INTO `Bee_Kategorie_Podfora` (`ID_Kategoria`, `ID_Podforum`) VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(3,6),(4,7);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Kategorie_Podfora` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Podfora`
--

/*!40000 ALTER TABLE `Bee_Podfora` DISABLE KEYS */;
LOCK TABLES `Bee_Podfora` WRITE;
INSERT INTO `Bee_Podfora` (`ID`, `Tytul`, `Opis`, `DataOstWypowiedzi`, `AutorOstWypowiedzi`, `IDOstWatku`, `Aktywne`, `Prywatne`, `LiczbaWatkow`, `LiczbaWypowiedzi`) VALUES 
(1,'Gry komputerowe','Dyskusja o grach komputerowych.','2005-06-03 18:19:18','~Bakus',NULL,'T','N',3,8),
(2,'Konsole','Tutaj piszemy o konsolach oraz o grach do nich.','2005-06-03 18:24:09','~albert2',NULL,'T','N',2,4),
(3,'Tips & Tricks','Triki, kody do gier, rozwiązania itp','2005-06-03 18:10:57','',NULL,'T','N',0,0),
(4,'Książki','Dyskusje na temat książek','2005-06-03 18:50:05','~kot',NULL,'T','N',3,13),
(5,'Nowości książkowe','Co piszczy w świecie książek, wydawnictw, pisarzy...','2005-06-03 19:02:08','~Chilly',NULL,'T','N',2,9),
(6,'Co sądzicie o forum?','Tutaj możesz napisać swoją opinię o forum.','2005-06-03 18:10:57','',NULL,'T','N',0,0),
(7,'O wszystkim','Tu jest miejsca na tematy niezwiązane z żadną z powyższych kategorii','2005-06-03 18:10:57','',NULL,'T','N',0,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Podfora` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Podfora_Watki`
--

/*!40000 ALTER TABLE `Bee_Podfora_Watki` DISABLE KEYS */;
LOCK TABLES `Bee_Podfora_Watki` WRITE;
INSERT INTO `Bee_Podfora_Watki` (`ID_Podfora`, `ID_Watku`) VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(4,6),(4,7),(4,8),(5,9),(5,10);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Podfora_Watki` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Users`
--

/*!40000 ALTER TABLE `Bee_Users` DISABLE KEYS */;
LOCK TABLES `Bee_Users` WRITE;
INSERT INTO `Bee_Users` (`ID`, `Login`, `Haslo`, `Imie`, `Nazwisko`, `ImieNazwiskoPrywatne`, `Email`, `EmailPrywatny`, `WWW`, `GG`, `GGPrywatne`, `Jabber`, `JabberPrywatny`, `Tlen`, `TlenPrywatny`, `WPKontakt`, `WPKontaktPrywatny`, `ICQ`, `ICQPrywatne`, `Msn`, `MsnPrywatny`, `Yahoo`, `YahooPrywatne`, `Skype`, `SkypePrywatny`, `Miasto`, `MiastoPrywatne`, `Plec`, `Avatar`, `Sygnaturka`, `SygnaturkaPubliczna`, `Styl`, `Jezyk`, `Powiadamianie`, `DataUrodzenia`, `DataUrodzeniaPrywatna`, `LiczbaWypowiedzi`, `LiczbaWatkow`, `OstatnieLogowanie`, `BierzaceLogowanie`, `Aktywny`, `Admin`, `Moderator`) VALUES (1,'Guest','','Gosc','','N','','N','','','N','','N','','N','','N','','N','','N','','N','','N','','N','M','','','N','','','N','2005-06-01 00:00:00','N',0,0,'2005-06-03 18:10:57','2005-06-03 18:10:57','T','N','N'),(2,'wilk','qJ59SC1w3QTt6','Marcin','Pruszczyński','N','wilk@bee.ltd.pl','N','','5924023','N','wilk2002@chrome.pl','N','','T','','T','','T','','T','','T','','T','Warszawa','N','M','wilk.jpg','','T','blue','pl','T','1981-08-22 00:00:00','N',0,0,'1970-01-01 00:00:00','1970-01-01 00:00:00','T','T','N');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Users` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Watki`
--

/*!40000 ALTER TABLE `Bee_Watki` DISABLE KEYS */;
LOCK TABLES `Bee_Watki` WRITE;
INSERT INTO `Bee_Watki` (`ID`, `ID_autora`, `Autor`, `Temat`, `Data`, `DataOstWypowiedzi`, `AutorOstWypowiedzi`, `Prywatny`, `Aktywny`, `Zablokowany`, `Zamkniety`, `LiczbaWypowiedzi`, `LiczbaOdwiedzin`) VALUES 
(1,1,'Glazus','Need for Speed: Undegraund 2','2005-06-03 18:13:45','2005-06-03 18:14:24','~Noxer_Away','N','T','N','N',2,2),
(2,1,'Sherence','Black &amp; White','2005-06-03 18:15:17','2005-06-03 18:15:50','~Bakus','N','T','N','N',2,2),
(3,1,'DuCh_','The Sims 2','2005-06-03 18:17:42','2005-06-03 18:19:18','~Bakus','N','T','N','N',4,4),
(4,1,'Bakus','PS2 nowa wersja','2005-06-03 18:21:07','2005-06-03 18:21:07','~Bakus','N','T','N','N',1,0),
(5,1,'Noxer_Away','GTA ?','2005-06-03 18:21:40','2005-06-03 18:24:09','~albert2','N','T','N','N',3,3),
(6,1,'Argonauta','Twój ulubiony autor?','2005-06-03 18:29:39','2005-06-03 18:33:01','~pokor18','N','T','N','N',7,7),
(7,1,'nme','Poezja','2005-06-03 18:35:42','2005-06-03 18:39:15','~Maxim212','N','T','N','N',4,4),
(8,1,'Kołysanka','&quot;Kobieta jak każda inna&quot; L.M.Montgomery','2005-06-03 18:42:44','2005-06-03 18:50:05','~kot','N','T','N','N',2,3),
(9,1,'Aurelia','Czy ktoś coś wie o Potterze &quot;6&quot;???','2005-06-03 18:56:53','2005-06-03 18:59:11','~Huma','N','T','N','N',6,7),
(10,1,'kggucwa','Po raz pierwszy kryminał Vernea po polsku','2005-06-03 19:01:13','2005-06-03 19:02:08','~Chilly','N','T','N','N',3,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Watki` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Watki_Wypowiedzi`
--

/*!40000 ALTER TABLE `Bee_Watki_Wypowiedzi` DISABLE KEYS */;
LOCK TABLES `Bee_Watki_Wypowiedzi` WRITE;
INSERT INTO `Bee_Watki_Wypowiedzi` (`ID_Watku`, `ID_Wypowiedzi`) VALUES (1,1),(1,2),(2,3),(2,4),(3,5),(3,6),(3,7),(3,8),(4,9),(5,10),(5,11),(5,12),(6,13),(6,14),(6,15),(6,16),(6,17),(6,18),(6,19),(7,20),(7,21),(7,22),(7,23),(8,24),(8,25),(9,26),(9,27),(9,28),(9,29),(9,30),(9,31),(10,32),(10,33),(10,34);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Watki_Wypowiedzi` ENABLE KEYS */;

--
-- Dumping data for table `Bee_Wypowiedzi`
--

/*!40000 ALTER TABLE `Bee_Wypowiedzi` DISABLE KEYS */;
LOCK TABLES `Bee_Wypowiedzi` WRITE;
INSERT INTO `Bee_Wypowiedzi` (`ID`, `ID_autora`, `Autor`, `Data`, `Tekst`, `Prywatna`, `Aktywna`) VALUES (1,1,'Glazus','2005-06-03 18:13:45','Dla mnie: świetna, wypasiona, super giera! A wy co sądzicie? ','N','T'),(2,1,'Noxer_Away','2005-06-03 18:14:24','Myślałem że się bardziej postarają','N','T'),(3,1,'Sherence','2005-06-03 18:15:17','Co sądzicie o B&amp;W? Jakie macie chowance?? <img src=\"/Bee/images/smiles/default/smile.gif\" alt=\"smile\" class=\"imgEmotikona\"  />','N','T'),(4,1,'Bakus','2005-06-03 18:15:50','B&amp;W ostatnio grałem szmat czasu temu ale miałem tygrysa czy jak to można inaczej nazwać. ale pozatym fajna gierka <img src=\"/Bee/images/smiles/default/very_happy.gif\" alt=\"very_happy\" class=\"imgEmotikona\"  />','N','T'),(5,1,'DuCh_','2005-06-03 18:17:42','Co sądzicie o tej grze, wedłóg mnie nie za fajna, to nie to samo co pierwsze simsy, myslałem że to bedzie hit no i bedzie, ale mnie i tak to nie przekonuje i zabardzo została &quot;naszpikowana&quot; ochroną, jeśli w windowsie sa włączone niepotrzebne programy to sie nie włączą a to jest błąd i jeszcze to ze jest na 4 płytach o Boże!! ','N','T'),(6,1,'Bakus','2005-06-03 18:18:14','Oglądałem recenzję w Gameboxsie. Też mi się wydaj że już to nie to samo co pierwsze Simsy. Zwykłe simsy były najlepsze jeszcze gdy były ze wszystkimi dodatkami i jeszcze było pare zeczy z neta','N','T'),(7,1,'tucus90','2005-06-03 18:18:38','Ja tam nie przepadam za Simsami ani za 1 ani za 2','N','T'),(8,1,'Bakus','2005-06-03 18:19:18','heh kupiłem oryginała i nawet fajne <img src=\"/Bee/images/smiles/default/very_happy.gif\" alt=\"very_happy\" class=\"imgEmotikona\"  /> trzeba poprostu zagrać i dopiero wtedu ocenić','N','T'),(9,1,'Bakus','2005-06-03 18:21:07','Co sądzicie o nowej wersji PS2?? Mi się niepodoba bo jest zbyt podobna do PSX. ','N','T'),(10,1,'Noxer_Away','2005-06-03 18:21:40','Co myslicie o GTA:San Andereas na PS2??','N','T'),(11,1,'Bakus','2005-06-03 18:23:22','świetna!! <img src=\"/Bee/images/smiles/default/ok.gif\" alt=\"ok\" class=\"imgEmotikona\"  /> Mam ją i już mam wszystkie mista odkryte  Coprawda grafika taka jak w VC ale gra się super <img src=\"/Bee/images/smiles/default/lol.gif\" alt=\"lol\" class=\"imgEmotikona\"  />','N','T'),(12,1,'albert2','2005-06-03 18:24:09','Wszystkie GTA są dobre <img src=\"/Bee/images/smiles/default/czat.gif\" alt=\"czat\" class=\"imgEmotikona\"  />','N','T'),(13,1,'Argonauta','2005-06-03 18:29:39','Coś nieodparcie uroczego jest prozie Anglików. Amis i Amis, Lodge, Bradbury, Douglas Adams, Huxley, Byron...<br/>Ciekawi mnie kogo z Europy Wschodniej można Waszym zdaniem przytoczyć?<br/><br/>Wasze najlepsze książki i autorzy na przestrzeni lat?','N','T'),(14,1,'Stormbringer','2005-06-03 18:30:02','Zdecydowanie Stanislawa Lema!','N','T'),(15,1,'malayka','2005-06-03 18:30:26','Osobiście to uwielbiam Carrolla, Irvinga, Evansa, Amisa lubię tylko seniora. A wszchodnia europa? Zdecydowanie Bułhakow ze swoim bezkonkurencyjnym &quot;Mistrzem i Małgorzatą&quot;!','N','T'),(16,1,'Trawa zielona','2005-06-03 18:30:58','a polscy pisarze, co myslicie o Tokarczuk? Fragment GRZYBOSC z Dom dzienny, dom nocny jest przeciez tak magiczny.','N','T'),(17,1,'Sceptyczka','2005-06-03 18:31:55','Moich ulubionych byłoby sporo: Carrol, Golding, Wharton, Perez-Reverte, Coelho, Marquez, London, Shaw, Wilde, Shakespeare i .... ( jeszcze kilkadziesiąt nazwisk) Z polskich autorów to ostatnio Tokarczuk <img src=\"/Bee/images/smiles/default/smile.gif\" alt=\"smile\" class=\"imgEmotikona\"  /> &quot;Prawiek i inne czasy&quot; jest baardzo magiczny...<br/>','N','T'),(18,1,'Barlog','2005-06-03 18:32:29','A mojim ulubionym autorem jest J.R.R. Tolkien.Jego &quot;Władca Pierścieni&quot; to klasyka i uważam te ksiazke za najlepsza ze wszystkich.Kto nie czytal niech przyczyta.Polecam.','N','T'),(19,1,'pokor18','2005-06-03 18:33:01','Amis i Hrabal to mistrzowie. Wprost powalają lekkością prozy i ujęciem rzeczywistości. Perez-Reverte to wieczne zaskoczenie. Oczywiście także autorzy z kręgu akademickiego - Amis, Lodge i Bradubry.<br/>Italo Calvino to osobny temat.<br/>I jeszcze wiele, wiele nazwisk z dzialu proza.<br/>Poezja to ponad wszystko Emily Dickinson.<br/>Polska - Huelle i Dr.Kohoutek z prozy Pilcha.','N','T'),(20,1,'nme','2005-06-03 18:35:42','Rozmawiamy o powieściach, opowiadaniach, nowelach. A poezja? Jest piękna! Co powiecie o moim najulubieńszym Leśmianie? Czy też dla Was pachnie lasem, łąką, wiosną? Czy też jest tak nieskończenie świetlista, natchniona? A innym razem mroczna, smutna, zadumana? Dla mnie to najpiękniejsza poezja! Przeczytajcie Kopciuszka, Garbusa','N','T'),(21,1,'jacqueline','2005-06-03 18:36:30','Jesli chodzi o mnie lubie wiersze Szymborskiej, jednak, w niektorych za bardzo wychwala idee komunizmu:/, natomiast wiersze,ktore zawsze nastawiaja mnie optymistycznie do zycia,sa wiersze Konstantego ildfonsa Galczynskiego-naprawde polecam!','N','T'),(22,1,'jakozak','2005-06-03 18:37:13','Nie lubię ani poezji Gałczyńskiego, ani Szymborskiej. KLubię Pawlikowską-Jasnorzeską, Lechonia, Tuwima, Kasprowicza, Tetmajera...','N','T'),(23,1,'Maxim212','2005-06-03 18:39:15','A spróbujcie, dla zmiany smaku, trochę satyr Załuckiego... Polecam. Nie tylko istnieje poezja poważna. Oto przykład :<br/><br/>&quot;Wszystko o męźczyźnie&quot;<br/><br/>Moja żona to mądra niewiasta.<br/>Moja córka, proszę Państwa, dorasta.<br/>Więc żona z głębi fotela<br/>rad naszej córce udziela...<br/>Nie szukaj, dziewczyno, mołojca.<br/>Nie trzeba. Popatrz na ojca.<br/>Nikt się na oko nie wyzna.<br/>A jednak mężczyzna.<br/>Mężczyźnie nie trzeba urody.<br/>Mężvczyzna nie musi być młody.<br/>Mężczyzna się nie ma podobać nikomu.<br/>Mężczyzna potrzebny jest w domu.<br/>Mężczyznę, dziecko kochane, zesłano nam nie po próżnicy.<br/>On ogień w piecu zapali, on węgla przyniesie z piwnicy...<br/>A kiedy , mówiąc już szczerze, nachodzą go myśłi niezdrowe<br/>to wtedy w mężczyźnie budzi się zwierzę,<br/>Ale DOMOWE, córeczko, DOMOWE.....<br/>Z mądrością to są przesądy.<br/>Intelekt to przecież nie wszystko<br/>Mężczyzna nie musi być mądry.<br/>Wystarczy jak ma stanowisko!<br/>Mieć życie wewnętrzne bogate???<br/>A po co mu?? Popatrz na tatę!<br/>Więc córeczka posłuszna Mamie<br/>spojrzała jeszcze raz na mnie<br/>z miłością i czcią nienaganną<br/>i z płaczem - JA CHCĘ BYĆ PANNĄ!!!<br/><br/>Jest to spisany z pamięci utwór pochodzący, chyba, z 1965 roku.<br/>Tak go zapamiętałem. Przepraszam za ewentualne braki w tekście, ale tak dawno go słyszałem... ','N','T'),(24,1,'Kołysanka','2005-06-03 18:47:08','Hej, hej, mam małe pytanko o opowiadanie L.M. Montgomery &quot;Kobieta jak każda inna&quot;. Słyszałam, że jest w nim coś wspomniane o wnukach Blytheów, a opowiadania oczywiście nigdzie nie mogę znaleźć... czy ktoś mógłby mi przybliżyć, co konkretnie było o nich napisane? Jestem bardzo ciekawa :) Z góry dziękuję<br/>','N','T'),(25,1,'kot','2005-06-03 18:50:05','Twoje pytanie zmobilizowało mnie do odkurzenia mojej kolekcji książek pani Montgomery (aż się zdziwiłam, że mam ich aż tyle! jakieś 12 lat temu czytałam je naprawdę nałogowo). Przeglądnęłam 4 zbiorki opowiadań (&quot;Spotkania po latach&quot;, &quot;Biała magia&quot;, &quot;Opowieści z Avonlea&quot; i &quot;Takie jak Ania&quot;) i nie znalazłam tytułu o którym piszesz. Być moze to opowiadanie pojawiło się także pod innym tytułem (pamiętam, że takie rzeczy sie zdarzały różnym wydawnictwom, sama mam jakieś zdublowane egzemplarze). Popatrzę jeszcze, do kilku książek nie mogę się w tej chwili dostać, bo się zaklinowały pod sufitem. pozdrowienia :) K.','N','T'),(26,1,'Aurelia','2005-06-03 18:56:53','Jeśli słyszałeś/aś o wydaniu, planach Rowling na szósty tom Pottera to szybko odpowiedz!!!!!!!!','N','T'),(27,1,'seweryn','2005-06-03 18:57:35','tom 6 Harry Pottera bedzie wydany po angielsku 16 czerwca 2005, wersja polska (slyszałem) ze dopiero na wiosne 2006','N','T'),(28,1,'aishunia','2005-06-03 18:58:00','Tak, zgadza sie. &quot;Harry Potter and the Half Blood Prince&quot; (nie wiem jak bedzie brzmial tytul przetlumaczony), szosty tom przygod mlodego czarodzieja bedzie w sprzedazy w Anglii juz w czerwcu. Niestety na tlumaczenie trzeba bedzie poczekac do przyszlego roku.','N','T'),(29,1,'alimak','2005-06-03 18:58:16','Polski tytuł brzmi &quot;Harry Potter i książę pół krwi&quot;','N','T'),(30,1,'oska','2005-06-03 18:58:38','tytuł własnie tak brzmi, oprocz tego widomo ze zginie ponownie w imie dobra kolejna osoba z grona pottera, ksiazka juz stałą sie hitem i bestellerem i goruje na listach pzrebolow ksiazkowych wielu krajach,','N','T'),(31,1,'Huma','2005-06-03 18:59:11','Z tego co mi się o uszy obiło polski tytuł ma brzmieć &quot;Harry Potter i pół krwi książe&quot;. Poza tym IMO lepiej brzmi.','N','T'),(32,1,'kggucwa','2005-06-03 19:01:13','Jedno z poważnych polskich wydawnictw zaprezentuje lada dzień nową książkę Juliusza Vernea.<br/><br/>Do stulecia śmierci wielkiego pisarza pozostało zaledwie kilka dni - 24 marca 2005 r.<br/>Z tej to uroczystej okazji przygotowywana była do druku nigdy jeszcze niepublikowana po polsku w postaci książkowej powieść z oryginalnymi ilustracjami pochodzącymi z XIX wiecznego francuskiego wydania.<br/><br/>Pierwsze egzemplarze już zaczynają opuszczać drukarnię...','N','T'),(33,1,'Raptusiewicz','2005-06-03 19:01:34','Hurra!!:)A jaki tytuł? Masz może dokładniejsze informacje?','N','T'),(34,1,'Chilly','2005-06-03 19:02:08','&quot;Tajemnicze wydarzenia w Inflantach&quot;, wydaw. Zielona Sowa','N','T');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Bee_Wypowiedzi` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

