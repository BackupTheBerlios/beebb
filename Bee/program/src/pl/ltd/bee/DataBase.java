/*
 * DataBase.java
 *
 * Created on 7 marzec 2005, 10:16
 */

package pl.ltd.bee;

import java.util.*;
import pl.ltd.bee.Exceptions.*;


/**
 * Klasa wyzszy interfejs do bazy danych. Dostarcza metody do uzyskania danych zawartych na forach.
 * @author wilk
 */

/*
(13:10:55) Wilk: "wymyslilem" jak latwo mozemy buforowac zapytania z bazy danych
(13:12:21) Wilk: wystarczy miec w DataBase static lub nie zmienna np HashTable i po kazdym zapytaniu wrzucac obiekty: put("Objekt_ID",Object)
(13:12:27) Wilk: final-> ok
(13:12:33) Wilk: wiec np:
(13:13:44) Wilk: getWatek(id)
if ( jest object w hashtable("Watek_"+id)) to go zwroc
SELECT
Hastable.put("Watek_"+id,to co zwrocil SELECT);
(13:14:12) Wilk: oczywiscie to powinno byc troszke madrzejsze, to znaczy to powinna byc HashTable z LRU
(13:14:47) Wilk: i o zalozonym rozmiarze .. np 100 objektow
(13:15:25) pawelb: brzmi sensownie
(13:15:52) Wilk: mozna tez zrobic oddzielne bufory dla kazdego z objektow wtedy mozna wiecej stoic, np 10 miejsc na Kategorie ale juz 100 miejsc na Watki a 300 na wypowiedzi
(13:16:22) pawelb: spoko, tylko mo�e narazie si� wstrzymamy z implementacj�
(13:16:36) pawelb: bo musze troche przerobi� DataBase
(13:16:59) Wilk: wtedy oczywiscie jest wiecej zmiennych, ale nie ma "Object_"+id tylko wystarczy sam id
(13:17:36) Wilk: nie no implementacje nie teraz, to oczywiste
(13:18:01) Wilk: tylko jak bys mogl moze wkleic te rozmowe do DataBase, albo do /teksty
(13:18:02) Wilk: ok ?
(13:18:23) pawelb: �ok
 
 * TODO:
 * (na sam koniec implementacji) przy insertach doda� schemat tabeli po jej nazwie
 **/

public class DataBase {
    
    public static final String TAK = "T";
    public static final String NIE = "N";
    public static final String KOBIETA = "K";
    public static final String MEZCZYZNA = "M";
    /**
     * Stala reprezentujaca podstawe nazwy tabeli w bazie danych
     */
    static final String BEE_USERS_BASE = "Users";
    
    static final String BEE_WATKI_BASE = "Watki";
    static final String BEE_WYPOWIEDZI_BASE = "Wypowiedzi";
    static final String BEE_PODFORA_BASE = "Podfora";
    static final String BEE_KATEGORIE_BASE = "Kategorie";
    static final String BEE_FORUM_BASE = "Forum";
    
    static final String BEE_FORUM_KATEGORIE_BASE = "Forum_Kategorie";
    static final String BEE_WATKI_WYPOWIEDZI_BASE = "Watki_Wypowiedzi";
    static final String BEE_PODFORA_WATKI_BASE = "Podfora_Watki";
    static final String BEE_KATEGORIE_PODFORA_BASE = "Kategorie_Podfora";
    static final String BEE_MODERATORZY_BASE = "Moderatorzy";
    
    static final String BEE_FORGET_PASSWD_BASE = "Forget_Passwd";
    
    static final String BEE_NEW_USER_BASE = "New_User";
    static final String BEE_BANNED_USERS_BASE = "Banned_Users";
    static final String BEE_GROUPS_BASE = "Groups";
    static final String BEE_USERS_GROUPS_BASE = "Users_Groups";
    static final String BEE_PRIVILAGES_BASE = "Privilages";
    
    /**
     * Stala reprezentujaca nazwe tabeli w bazie danych
     */
    static String BEE_USERS = "Users";
    
    static String BEE_WATKI = "Watki";
    static String BEE_WYPOWIEDZI = "Wypowiedzi";
    static String BEE_PODFORA = "Podfora";
    static String BEE_KATEGORIE = "Kategorie";
    static String BEE_FORUM = "Forum";
    
    static String BEE_GROUPS = "Groups";
    
    static String BEE_FORUM_KATEGORIE = "Forum_Kategorie";
    static String BEE_WATKI_WYPOWIEDZI = "Watki_Wypowiedzi";
    static String BEE_PODFORA_WATKI = "Podfora_Watki";
    static String BEE_KATEGORIE_PODFORA = "Kategorie_Podfora";
    static String BEE_MODERATORZY = "Moderatorzy";
    
    static String BEE_USERS_GROUPS = "Users_Groups";
    
    static String BEE_FORGET_PASSWD = "Forget_Passwd";
    
    static String BEE_NEW_USER = "New_User";
    static String BEE_BANNED_USERS = "Banned_Users";
    static String BEE_PRIVILAGES = "Privilages";
    
    /**
     * Stala reprezentujaca nazwe pola w tabeli w bazie danych
     */
    static final String WATEK_ID = "ID";
    static final String WATEK_ID_AUTORA = "ID_AUTORA";
    static final String WATEK_AUTOR = "AUTOR";
    static final String WATEK_TEMAT = "TEMAT";
    static final String WATEK_DATA = "DATA";
    static final String WATEK_DATA_OST_WYPOWIEDZI = "DATAOSTWYPOWIEDZI";
    static final String WATEK_AUTOR_OST_WYPOWIEDZI = "AUTOROSTWYPOWIEDZI";
    static final String WATEK_PRYWATNY = "PRYWATNY";
    static final String WATEK_AKTYWNY = "AKTYWNY";
    static final String WATEK_ZAMKNIETY = "ZAMKNIETY";
    static final String WATEK_ZABLOKOWANY = "ZABLOKOWANY";
    static final String WATEK_LICZBA_WYPOWIEDZI = "LICZBAWYPOWIEDZI";
    static final String WATEK_LICZBA_ODWIEDZIN = "LICZBAODWIEDZIN";
    
    static final String WYPOWIEDZ_ID = "ID";
    static final String WYPOWIEDZ_ID_AUTORA = "ID_AUTORA";
    static final String WYPOWIEDZ_AUTOR = "AUTOR";
    static final String WYPOWIEDZ_DATA = "DATA";
    static final String WYPOWIEDZ_TEKST = "TEKST";
    static final String WYPOWIEDZ_PRYWATNA = "PRYWATNA";
    static final String WYPOWIEDZ_AKTYWNA = "AKTYWNA";
    
    static final String PODFORUM_ID = "ID";
    static final String PODFORUM_TYTUL = "TYTUL";
    static final String PODFORUM_OPIS = "OPIS";
    static final String PODFORUM_DATA_OST_WYPOWIEDZI = "DATAOSTWYPOWIEDZI";
    static final String PODFORUM_AUTOR_OST_WYPOWIEDZI = "AUTOROSTWYPOWIEDZI";
    static final String PODFORUM_AKTYWNE = "AKTYWNE";
    static final String PODFORUM_PRYWATNE = "PRYWATNE";
    static final String PODFORUM_LICZBA_WATKOW = "LICZBAWATKOW";
    static final String PODFORUM_LICZBA_WYPOWIEDZI = "LICZBAWYPOWIEDZI";
    
    static final String FORUM_ID = "ID";
    static final String FORUM_NAZWA = "NAZWA";
    static final String FORUM_OPIS = "OPIS";
    
    static final String KATEGORIA_ID = "ID";
    static final String KATEGORIA_TYTUL = "TYTUL";
    static final String KATEGORIA_OPIS = "OPIS";
    static final String KATEGORIA_AKTYWNA = "AKTYWNA";
    static final String KATEGORIA_PRYWATNA = "PRYWATNA";
    
    static final String KATEGORIE_PODFORA_ID_KATEGORII = "ID_KATEGORIA";
    static final String KATEGORIE_PODFORA_ID_PODFORUM = "ID_PODFORUM";
    
    static final String PODFORA_WATKI_ID_PODFORUM = "ID_PODFORA";
    static final String PODFORA_WATKI_ID_WATKU = "ID_WATKU";
    
    static final String WATKI_WYPOWIEDZI_ID_WATKU = "ID_WATKU";
    static final String WATKI_WYPOWIEDZI_ID_WYPOWIEDZI = "ID_WYPOWIEDZI";
    
    static final String USER_ID = "ID";
    static final String USER_LOGIN = "LOGIN";
    static final String USER_HASLO = "HASLO";
    static final String USER_IMIE = "IMIE";
    static final String USER_NAZWISKO = "NAZWISKO";
    static final String USER_IMIE_NAZWISKO_PRYWATNE = "IMIENAZWISKOPRYWATNE";
    static final String USER_EMAIL = "EMAIL";
    static final String USER_EMAIL_PRYWATNY = "EMAILPRYWATNY";
    static final String USER_GG = "GG";
    static final String USER_GG_PRYWATNE = "GGPRYWATNE";
    static final String USER_JABBER = "JABBER";
    static final String USER_JABBER_PRYWATNY = "JABBERPRYWATNY";
    static final String USER_TLEN = "TLEN";
    static final String USER_TLEN_PRYWATNY = "TLENPRYWATNY";
    static final String USER_WPKONTAKT = "WPKONTAKT";
    static final String USER_WPKONTAKT_PRYWATNY = "WPKONTAKTPRYWATNY";
    static final String USER_ICQ = "ICQ";
    static final String USER_ICQ_PRYWATNE = "ICQPRYWATNE";
    static final String USER_MSN = "MSN";
    static final String USER_MSN_PRYWATNY = "MSNPRYWATNY";
    static final String USER_MIASTO = "MIASTO";
    static final String USER_MIASTO_PRYWATNE = "MIASTOPRYWATNE";
    static final String USER_PLEC = "PLEC";
    static final String USER_ROKURODZENIA = "ROKURODZENIA";
    static final String USER_ROKURODZENIA_PRYWATNY = "ROKURODZENIAPRYWATNY";
    
    static final String USER_LASTLOG = "OSTATNIELOGOWANIE";
    static final String USER_CURRENTLOG = "BIERZACELOGOWANIE";
    static final String USER_AKTYWNY = "AKTYWNY";
    static final String USER_ADMIN = "ADMIN";
    static final String USER_MODERATOR = "MODERATOR";
    
    static final String FORUM_KATEGORIE_ID_FORUM = "ID_FORUM";
    static final String FORUM_KATEGORIE_ID_KATEGORIA = "ID_KATEGORIA";
    
    static final String FORGET_PASSWD_KLUCZ = "KLUCZ";
    static final String FORGET_PASSWD_EMAIL = "EMAIL";
    
    static final String NEW_USER_KLUCZ = "KLUCZ";
    static final String NEW_USER_LOGIN = "LOGIN";
    
    static final String MODERATORZY_ID_PODFORUM = "ID_PODFORUM";
    static final String MODERATORZY_ID_USER = "ID_USER";
    
    static final String BANNED_USERS_ID_USER = "ID_USER";
    static final String BANNED_USERS_ID_PODFORUM = "ID_PODFORUM";
    
    static final String USERS_GROUPS_ID_USER = "ID_USER";
    static final String USERS_GROUPS_ID_GROUP = "ID_GROUP";
    
    static final String GROUP_ID = "ID";
    static final String GROUP_NAZWA = "NAZWA";
    
    
    static final String PRIVILAGES_ID_GROUP = "ID_GROUP";
    static final String PRIVILAGES_ID_KATEGORIA = "ID_KATEGORIA";
    static final String PRIVILAGES_ID_PODFORUM = "ID_PODFORUM";
    static final String PRIVILAGES_ID_CZYTANIE = "CZYTANIE";
    static final String PRIVILAGES_ID_PISANIE = "PISANIE";
    
    //TODO baza jest static czyli jeden obiekt dla wszystkich obiektow klasy DataBase. Konstruktor(Host,User,Pass) zmieni ten obiekt dla wszystkich tych obiektow. To trzeba miec na uwadze w przyszlosci
    ConnectorDB baza;
    
    
    /** Konstruktor bezargumentowy. Domyslnie laczy sie z baza Bee:bee@localhost. */
    public DataBase() {
        baza = new ConnectorDB(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
    }
    
    
    /**
     * Konstruktor
     * @param host Adres serwera bazy danych
     * @param Db nazwa bazy danych
     * @param user Nazwa uzytkownika bazy danych
     * @param pass Haslo uzytkownika bazy danych
     */
    public DataBase(String host, String Db,String user, String pass) {
        baza = new ConnectorDB(host,Db,user,pass);
    }
    
    
    /**
     * metoda sprawdzajaca czy obiekt polaczyl sie z baza - a dokladniej czy dostal base,usera i haslo
     */
    public boolean isConnected() {
        return baza.isConnected();
    }
    
    
    /**
     * Metoda powoduje przylaczenie do bazy o podanych parametrach
     * @param host Adres serwera bazy danych
     * @param Db nazwa bazy danych
     * @param user Nazwa uzytkownika bazy danych
     * @param pass Haslo uzytkownika bazy danych
     */
    public void connect(String host, String Db, String user, String pass) throws BeeConnectionException{
        baza.setParameters(host, Db, user, pass);
        baza.connect();
    }
    
    
    public void disconnect() throws BeeConnectionException{
        baza.disconnect();
    }
    
    
    /**
     * Metoda ustawia prefix tabel w bazie danych
     * @param pref - string reprezentujacy prefix
     */
    public void setTablePrefix(String pref) {
        BEE_USERS = pref + "_" + BEE_USERS_BASE;
        BEE_WATKI = pref + "_" + BEE_WATKI_BASE;
        BEE_WYPOWIEDZI = pref + "_" + BEE_WYPOWIEDZI_BASE;
        BEE_PODFORA = pref + "_" + BEE_PODFORA_BASE;
        BEE_KATEGORIE = pref + "_" + BEE_KATEGORIE_BASE;
        BEE_FORUM = pref + "_" + BEE_FORUM_BASE;
        BEE_FORUM_KATEGORIE = pref + "_" + BEE_FORUM_KATEGORIE_BASE;
        BEE_WATKI_WYPOWIEDZI = pref + "_" + BEE_WATKI_WYPOWIEDZI_BASE;
        BEE_PODFORA_WATKI = pref + "_" + BEE_PODFORA_WATKI_BASE;
        BEE_KATEGORIE_PODFORA = pref + "_" + BEE_KATEGORIE_PODFORA_BASE;
        BEE_MODERATORZY = pref + "_" + BEE_MODERATORZY_BASE;
        BEE_FORGET_PASSWD = pref + "_" + BEE_FORGET_PASSWD_BASE;
        BEE_NEW_USER = pref + "_" + BEE_NEW_USER_BASE;
        BEE_BANNED_USERS = pref + "_" + BEE_BANNED_USERS_BASE;
        BEE_GROUPS= pref+ "_" + BEE_GROUPS_BASE;
        BEE_USERS_GROUPS= pref+ "_" + BEE_USERS_GROUPS_BASE;
        BEE_PRIVILAGES= pref+ "_" + BEE_PRIVILAGES_BASE;
    }
    
    
    /**
     * Metoda dostarcza tylko pierwszy element (wiersz) z zadanego zapytania.
     */
    private Hashtable getObject(String query) {
        ArrayList lista = baza.query(query);
        if (lista.size() > 0)
            return (Hashtable)lista.get(0);
        else return null; //TODO: Co robic jesli nie ma zadanego objektu ?
    }
    
    
    /**
     * Metoda zwaraca objekt Watek o podanym identyfikatorze
     * @param ID Identyfikator szukanego watku
     * @return Zwraca obiekt Watek badz null w razie bledu.
     */
    public Watek getWatek(int ID){
        Hashtable watek = getObject("SELECT * FROM " + BEE_WATKI + " WHERE "+ WATEK_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID, ID_autora, Temat i Date
        if (watek == null) return null;
        return new Watek((String)watek.get(WATEK_ID),(String)watek.get(WATEK_ID_AUTORA),(String)watek.get(WATEK_AUTOR),(String)watek.get(WATEK_TEMAT),(String)watek.get(WATEK_DATA),(String)watek.get(WATEK_DATA_OST_WYPOWIEDZI),(String)watek.get(WATEK_AUTOR_OST_WYPOWIEDZI),(String)watek.get(WATEK_PRYWATNY),(String)watek.get(WATEK_AKTYWNY),(String)watek.get(WATEK_ZABLOKOWANY),(String)watek.get(WATEK_ZAMKNIETY),(String)watek.get(WATEK_LICZBA_WYPOWIEDZI),(String)watek.get(WATEK_LICZBA_ODWIEDZIN),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Wypowiedz o podanym identyfikatorze
     * @param ID Identyfikator szukanej wypowiedzi
     * @return Zwraca obiekt Wypowiedz badz null w razie bledu.
     */
    public Wypowiedz getWypowiedz(int ID){
        Hashtable wypowiedz = getObject("SELECT * FROM " + BEE_WYPOWIEDZI + " WHERE " + WYPOWIEDZ_ID +"=" + ID);
        if (wypowiedz == null) return null;
        return new Wypowiedz((String)wypowiedz.get(WYPOWIEDZ_ID),(String)wypowiedz.get(WYPOWIEDZ_ID_AUTORA),(String)wypowiedz.get(WYPOWIEDZ_AUTOR),(String)wypowiedz.get(WYPOWIEDZ_DATA),(String)wypowiedz.get(WYPOWIEDZ_TEKST),(String)wypowiedz.get(WYPOWIEDZ_PRYWATNA),(String)wypowiedz.get(WYPOWIEDZ_AKTYWNA),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Podforum o podanym identyfikatorze
     * @param ID Identyfikator szukanego podforum
     * @return Zwraca obiekt Podforum badz null w razie bledu.
     */
    public Podforum getPodforum(int ID){
        Hashtable podforum = getObject("SELECT * FROM " + BEE_PODFORA + " WHERE " + PODFORUM_ID +"=" + ID);
        if (podforum == null) return null;
        return new Podforum((String)podforum.get(PODFORUM_ID),(String)podforum.get(PODFORUM_TYTUL),(String)podforum.get(PODFORUM_OPIS),(String)podforum.get(PODFORUM_DATA_OST_WYPOWIEDZI),(String)podforum.get(PODFORUM_AUTOR_OST_WYPOWIEDZI),(String)podforum.get(PODFORUM_AKTYWNE),(String)podforum.get(PODFORUM_PRYWATNE),(String)podforum.get(PODFORUM_LICZBA_WATKOW),(String)podforum.get(PODFORUM_LICZBA_WYPOWIEDZI),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Podforum w ktorym znajduje sie Watek o podanym identyfikatorze
     * @param ID Identyfikator watku w szukanym podforum
     * @return Zwraca obiekt Podforum badz null w razie bledu.
     */
    public Podforum getPodforumbyWatek(int ID){
        Hashtable podforum = getObject("SELECT * FROM " + BEE_PODFORA + " WHERE "+ PODFORUM_ID +"= (SELECT " + PODFORA_WATKI_ID_PODFORUM + " FROM " + BEE_PODFORA_WATKI + " WHERE " + PODFORA_WATKI_ID_WATKU + "=" + ID + ")");
        if (podforum == null) return null;
        return new Podforum((String)podforum.get(PODFORUM_ID),(String)podforum.get(PODFORUM_TYTUL),(String)podforum.get(PODFORUM_OPIS),(String)podforum.get(PODFORUM_DATA_OST_WYPOWIEDZI),(String)podforum.get(PODFORUM_AUTOR_OST_WYPOWIEDZI),(String)podforum.get(PODFORUM_AKTYWNE),(String)podforum.get(PODFORUM_PRYWATNE),(String)podforum.get(PODFORUM_LICZBA_WATKOW),(String)podforum.get(PODFORUM_LICZBA_WYPOWIEDZI),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Watek w ktorym znajduje sie Wypowiedz o podanym identyfikatorze
     * @param ID Identyfikator wypowiedzi w szukanym watku
     * @return Zwraca obiekt Watek badz null w razie bledu.
     */
    public Watek getWatekByWypowiedz(int ID){
        Hashtable watek = getObject("SELECT * FROM " + BEE_WATKI + " WHERE "+ WATEK_ID +"= (SELECT " + WATKI_WYPOWIEDZI_ID_WATKU + " FROM " + BEE_WATKI_WYPOWIEDZI + " WHERE " + WATKI_WYPOWIEDZI_ID_WYPOWIEDZI + "=" + ID + ")");
        if (watek == null) return null;
        return new Watek((String)watek.get(WATEK_ID),(String)watek.get(WATEK_ID_AUTORA),(String)watek.get(WATEK_AUTOR),(String)watek.get(WATEK_TEMAT),(String)watek.get(WATEK_DATA),(String)watek.get(WATEK_DATA_OST_WYPOWIEDZI),(String)watek.get(WATEK_AUTOR_OST_WYPOWIEDZI),(String)watek.get(WATEK_PRYWATNY),(String)watek.get(WATEK_AKTYWNY),(String)watek.get(WATEK_ZABLOKOWANY),(String)watek.get(WATEK_ZAMKNIETY),(String)watek.get(WATEK_LICZBA_WYPOWIEDZI),(String)watek.get(WATEK_LICZBA_ODWIEDZIN),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Kategoria w ktorym znajduje sie Podforum o podanym identyfikatorze
     * @param ID Identyfikator podforum w szukanej kategorii
     * @return Zwraca obiekt Kategoria lub null gdy nie ma.
     */
    public Kategoria getKategoriabyPodforum(int ID){
        Hashtable kategoria = getObject("SELECT * FROM " + BEE_KATEGORIE + " WHERE " + KATEGORIA_ID + " = (SELECT " + KATEGORIE_PODFORA_ID_KATEGORII + " FROM " + BEE_KATEGORIE_PODFORA + " WHERE " + KATEGORIE_PODFORA_ID_PODFORUM + "=" + ID + ")");
        if (kategoria == null) return null;
        return new Kategoria((String)kategoria.get(KATEGORIA_ID),(String)kategoria.get(KATEGORIA_TYTUL),(String)kategoria.get(KATEGORIA_OPIS),(String)kategoria.get(KATEGORIA_AKTYWNA),(String)kategoria.get(KATEGORIA_PRYWATNA),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Forum
     * @return Zwraca obiekt Forum badz null w razie bledu.
     */
    public Forum getForum(){
        Hashtable forum = getObject("SELECT * FROM " + BEE_FORUM);
        if (forum == null) return null;
        return new Forum((String)forum.get(FORUM_NAZWA),(String)forum.get(FORUM_OPIS),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Kategoria o podanym identyfikatorze
     * @param ID Identyfikator szukanej Kategorii
     * @return Zwraca obiekt Kategoria badz null w razie bledu.
     */
    public Kategoria getKategoria(int ID){
        Hashtable kategoria = getObject("SELECT * FROM " + BEE_KATEGORIE + " WHERE " + KATEGORIA_ID +"=" + ID);
        if (kategoria == null) return null;
        return new Kategoria((String)kategoria.get(KATEGORIA_ID),(String)kategoria.get(KATEGORIA_TYTUL),(String)kategoria.get(KATEGORIA_OPIS),(String)kategoria.get(KATEGORIA_AKTYWNA),(String)kategoria.get(KATEGORIA_PRYWATNA),this);
    }
    
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami Kategorii w podanym Forum
     * @return ArrayList obiektow Integer
     */
    public ArrayList getKategorieForum() {
        ArrayList wynik = new ArrayList();
        ArrayList kategorie = baza.query("SELECT " + FORUM_KATEGORIE_ID_KATEGORIA + " FROM "+ BEE_FORUM_KATEGORIE);
        for(int i=0;i<kategorie.size();i++) {
            Hashtable kategoria = (Hashtable)kategorie.get(i);
            int id = Integer.parseInt((String)kategoria.get(FORUM_KATEGORIE_ID_KATEGORIA));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    
    /**
     * Metoda zwaraca liste obiektow String bedacych tytulami Kategorii w podanym Forum
     * @return ArrayList obiektow String
     */
    public ArrayList getTytulyKategorii() {
        ArrayList wynik = new ArrayList();
        ArrayList kategorie = baza.query("SELECT "+KATEGORIA_TYTUL+" FROM "+ BEE_KATEGORIE +" WHERE "+KATEGORIA_AKTYWNA+"='" + TAK + "'");
        for(int i=0;i<kategorie.size();i++) {
            Hashtable kategoria = (Hashtable)kategorie.get(i);
            String tytul = (String)kategoria.get(KATEGORIA_TYTUL);
            wynik.add(tytul);
        }
        return wynik;
    }
    
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami podfor w podanej Kategorii
     * @param kat Kategoria w ramach ktorej interesuja nas podfora
     * @return ArrayList obiektow Integer
     */
    public ArrayList getPodforaKategorii(int ID) {
        ArrayList wynik = new ArrayList();
        ArrayList podfora = baza.query("SELECT "+ KATEGORIE_PODFORA_ID_PODFORUM + " FROM "+ BEE_KATEGORIE_PODFORA + " WHERE " + KATEGORIE_PODFORA_ID_KATEGORII + "=" + ID);
        for(int i=0;i<podfora.size();i++) {
            Hashtable podforum = (Hashtable)podfora.get(i);
            int id = Integer.parseInt((String)podforum.get(KATEGORIE_PODFORA_ID_PODFORUM));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    /**
     * Metoda zwaraca liste obiektow Podforum w podanej Kategorii, aktywne badż nie w
     * zalezności od parametru aktywne
     * @param aktywne boolena T lub F
     * @param ID Identyfikator kategorii w ramach ktorej interesuja nas podfora
     * @return ArrayList obiektow Podforum
     */
    public ArrayList  getPodforaKategorii(int ID, boolean czy_aktywne) {
        ArrayList wynik = new ArrayList();
        String aktywne;
        if(czy_aktywne) aktywne=TAK; else aktywne=NIE;
        ArrayList podfora = baza.query("SELECT * FROM "+BEE_KATEGORIE_PODFORA+" ,"+BEE_PODFORA+" WHERE "+PODFORUM_ID+"="+KATEGORIE_PODFORA_ID_PODFORUM+" and "+KATEGORIE_PODFORA_ID_KATEGORII+"=" + ID + " and "+PODFORUM_AKTYWNE+"= '"+aktywne+"'");
        for(int i=0;i<podfora.size();i++) {
            Hashtable podforum = (Hashtable)podfora.get(i);
            wynik.add(new Podforum((String)podforum.get(PODFORUM_ID),(String)podforum.get(PODFORUM_TYTUL),(String)podforum.get(PODFORUM_OPIS),(String)podforum.get(PODFORUM_DATA_OST_WYPOWIEDZI),(String)podforum.get(PODFORUM_AUTOR_OST_WYPOWIEDZI),(String)podforum.get(PODFORUM_AKTYWNE),(String)podforum.get(PODFORUM_PRYWATNE),(String)podforum.get(PODFORUM_LICZBA_WATKOW),(String)podforum.get(PODFORUM_LICZBA_WYPOWIEDZI),this));
        }
        return wynik;
    }
    
    
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami Watkow w podanym Podforum
     * @param ID Podforum w ramach ktorego interesuja nas watki
     * @return ArrayList obiektow Integer
     */
    public ArrayList getWatkiPodforum(int ID) {
        ArrayList wynik = new ArrayList();
        ArrayList watki = baza.query("SELECT " + PODFORA_WATKI_ID_WATKU + " FROM "+ BEE_PODFORA_WATKI + " WHERE " + PODFORA_WATKI_ID_PODFORUM + "=" + ID);
        for(int i=0;i<watki.size();i++) {
            Hashtable watek = (Hashtable)watki.get(i);
            int id = Integer.parseInt((String)watek.get(PODFORA_WATKI_ID_WATKU));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    
    /**
     * Metoda zwaraca liste obiektow Watek bedacych Watkami w podanym Podforum oraz majace pole aktywny na podane w parametrze
     * @param ID Podforum w ramach ktorego interesuja nas watki
     * @param aktywne Okresla czy interesuja nas watki aktywne czy nie
     * @return ArrayList obiektow Watek
     */
    public ArrayList getWatkiPodforum(int ID, boolean aktywne) {
        ArrayList wynik = new ArrayList();
        ArrayList watki = baza.query("SELECT * FROM "+ BEE_PODFORA_WATKI+","+BEE_WATKI + " WHERE " + PODFORA_WATKI_ID_PODFORUM + "=" + ID +" AND "+PODFORA_WATKI_ID_WATKU+"="+WATEK_ID +" AND "+WATEK_AKTYWNY+"='"+ (aktywne?TAK:NIE) +"'");
        if (watki == null) return wynik;
        for(int i=0;i<watki.size();i++) {
            Hashtable watek = (Hashtable)watki.get(i);
            int id = Integer.parseInt((String)watek.get(PODFORA_WATKI_ID_WATKU));
            wynik.add(new Watek((String)watek.get(WATEK_ID),(String)watek.get(WATEK_ID_AUTORA),(String)watek.get(WATEK_AUTOR),(String)watek.get(WATEK_TEMAT),(String)watek.get(WATEK_DATA),(String)watek.get(WATEK_DATA_OST_WYPOWIEDZI),(String)watek.get(WATEK_AUTOR_OST_WYPOWIEDZI),(String)watek.get(WATEK_PRYWATNY),(String)watek.get(WATEK_AKTYWNY),(String)watek.get(WATEK_ZABLOKOWANY),(String)watek.get(WATEK_ZAMKNIETY),(String)watek.get(WATEK_LICZBA_WYPOWIEDZI),(String)watek.get(WATEK_LICZBA_ODWIEDZIN),this));
        }
        return wynik;
    }
    
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami Wypowiedzi w podanym Watku
     * @param wat Watek w ramach ktorego interesuja nas Wypowiedzi
     * @return ArrayList obiektow Integer
     */
    public ArrayList getWypowiedziWatku(int ID) {
        ArrayList wynik = new ArrayList();
        ArrayList wypowiedzi = baza.query("SELECT * FROM "+ BEE_WATKI_WYPOWIEDZI + " WHERE " + WATKI_WYPOWIEDZI_ID_WATKU + "=" + ID);
        for(int i=0;i<wypowiedzi.size();i++) {
            Hashtable wypowiedz = (Hashtable)wypowiedzi.get(i);
            int id = Integer.parseInt((String)wypowiedz.get(WATKI_WYPOWIEDZI_ID_WYPOWIEDZI));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami Wypowiedzi w podanym Watku o stanie aktywnosci podanym w parametrze
     * @param wat Watek w ramach ktorego interesuja nas Wypowiedzi
     * @param aktywne Okresla jakie wypowiedzi nas interesuja
     * @return ArrayList obiektow Integer
     */
    public ArrayList getWypowiedziWatku(int ID, boolean aktywne) {
        ArrayList wynik = new ArrayList();
        ArrayList wypowiedzi = baza.query("SELECT * FROM "+ BEE_WATKI_WYPOWIEDZI+","+BEE_WYPOWIEDZI + " WHERE "+WATKI_WYPOWIEDZI_ID_WYPOWIEDZI+"="+WYPOWIEDZ_ID+" AND " + WATKI_WYPOWIEDZI_ID_WATKU + "=" + ID+" AND "+WYPOWIEDZ_AKTYWNA+"='"+(aktywne?TAK:NIE)+"'");
        for(int i=0;i<wypowiedzi.size();i++) {
            Hashtable wypowiedz = (Hashtable)wypowiedzi.get(i);
            int id = Integer.parseInt((String)wypowiedz.get(WATKI_WYPOWIEDZI_ID_WYPOWIEDZI));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    /**
     * Metoda zwaraca obiekt User stworzony z danych znajdujących się w hashtablicy podanej jako parametr
     * @param user tablica (hashtable) z danymi usera wyciągniętymi z bazy
     * @return Zwraca obiekt User
     */
    
    public User ht2user(Hashtable user) {
        return new User(Integer.decode((String)user.get(USER_ID)).intValue(),(String)user.get(USER_LOGIN),
                (String)user.get(USER_HASLO),(String)user.get(USER_IMIE),(String)user.get(USER_NAZWISKO),
                (String)user.get(USER_IMIE_NAZWISKO_PRYWATNE),(String)user.get(USER_EMAIL),(String)user.get(USER_EMAIL_PRYWATNY),
                (String)user.get(USER_GG),(String)user.get(USER_GG_PRYWATNE),(String)user.get(USER_JABBER),
                (String)user.get(USER_TLEN),(String)user.get(USER_TLEN_PRYWATNY),(String)user.get(USER_WPKONTAKT),
                (String)user.get(USER_WPKONTAKT_PRYWATNY),(String)user.get(USER_ICQ),(String)user.get(USER_ICQ_PRYWATNE),
                (String)user.get(USER_MSN),(String)user.get(USER_MSN_PRYWATNY),(String)user.get(USER_MIASTO),
                (String)user.get(USER_MIASTO_PRYWATNE),(String)user.get(USER_PLEC),(String)user.get(USER_ROKURODZENIA),
                (String)user.get(USER_ROKURODZENIA_PRYWATNY),(String)user.get(USER_JABBER_PRYWATNY),(String)user.get(USER_LASTLOG),
                (String)user.get(USER_CURRENTLOG),(String)user.get(USER_AKTYWNY),(String)user.get(USER_ADMIN),
                (String)user.get(USER_MODERATOR),this);
    }

    
    
    /**
     * Metoda zwaraca obiekt User o podanym identyfikatorze
     * @param ID Identyfikator szukanego uzytkownika
     * @return Zwraca obiekt User badz null w razie bledu.
     */
    public User getUser(int ID){
        Hashtable user = getObject("SELECT * FROM " + BEE_USERS + " WHERE "+ USER_ID +"=" + ID);
        if (user == null) return null;
        return ht2user(user);
    }
    
    
    /**
     * Metoda zwaraca objekt User o podanym identyfikatorze
     * @param login Identyfikator (login) szukanego uzytkownika
     * @return Zwraca obiekt User badz null w razie bledu.
     */
    public User getUser(String login) {
        Hashtable user = getObject("SELECT * FROM " + BEE_USERS + " WHERE "+ USER_LOGIN +" = '" + login + "'");
        if (user == null) return null;
        return ht2user(user);
    }
    
    
    /**
     * Metoda zwaraca nazwe uzytkownika o danym kluczu w tabeli nowych kont
     * @param klucz losowy klucz zwiazany z uzytkownikiem
     * @return Zwraca nazwe uzytkownika badz null w razie bledu.
     */
    public String getLoginNewUser(String klucz){
        Hashtable user = getObject("SELECT * FROM " + BEE_NEW_USER + " WHERE "+ NEW_USER_KLUCZ +"='" + klucz + "'");
        if (user == null) return null;
        return (String)user.get(NEW_USER_LOGIN);
    }
    
    
    /**
     * Metoda zwaraca email uzytkownika o danym kluczu w tabeli zapomnianych hasel
     * @param klucz losowy klucz zwiazany z uzytkownikiem
     * @return Zwraca email uzytkownika badz null w razie bledu.
     */
    public String getEmailForgetPasswd(String klucz){
        Hashtable user = getObject("SELECT * FROM " + BEE_FORGET_PASSWD + " WHERE "+ FORGET_PASSWD_KLUCZ +"='" + klucz + "'");
        if (user == null) return null;
        return (String)user.get(FORGET_PASSWD_EMAIL);
    }
    
    
    /**
     * Metoda umieszcza uzytkownika w bazie danych
     * @param u obiekt User (bez waznego id) reprezentujący dodawaneg użytkownika
     * @return zwraca czy insert sie powidl
     */
    public boolean insertUser(User u){
        String aktywny;
        if(u.aktywny()) aktywny=TAK; else aktywny=NIE;
        String moderator;
        if(u.moderator()) moderator=TAK; else moderator=NIE;
        String admin;
        if(u.admin()) admin=TAK; else admin=NIE;
        String showName;
        if(u.ifShowName()) showName=NIE; else showName=TAK;
        String showEmail;
        if(u.ifShowEmail()) showEmail=NIE; else showEmail=TAK;
        String showGG;
        if(u.ifShowGG()) showGG=NIE; else showGG=TAK;
        String showJabber;
        if(u.ifShowJabber()) showJabber=NIE; else showJabber=TAK;
        return baza.dmlQuery("INSERT INTO " + BEE_USERS + " VALUES (" + u.getID() + ",\"" + u.getLogin() + "\",\"" + u.getHaslo() + "\" ,'"+  u.getImie() +"' ,'" + u.getNazwisko() + "' ,'" + showName + "' ,'" + u.getEmail()  + "' ,'" + showEmail + "' ,'" + u.getGG() + "' ,'" + showGG + "' ,'" + u.getJabber() + "' ,'" +  showJabber +  "','" + u.getLastLog() + "' ,'" + u.getCurrentLog() + "','" + aktywny + "','" + admin + "','" + moderator + "')");
    }
    
    
    /**
     * Metoda aktualizuje uzytkownika w bazie danych
     * @param u obiekt User reprezentujący użytkownika
     * @return zwraca czy update sie powiódl
     */
    public boolean updateUser(User u){
        String aktywny;
        if(u.aktywny()) aktywny=TAK; else aktywny=NIE;
        String moderator;
        if(u.moderator()) moderator=TAK; else moderator=NIE;
        String admin;
        if(u.admin()) admin=TAK; else admin=NIE;
        String showName;
        if(u.ifShowName()) showName=NIE; else showName=TAK;
        String showEmail;
        if(u.ifShowEmail()) showEmail=NIE; else showEmail=TAK;
        String showGG;
        if(u.ifShowGG()) showGG=NIE; else showGG=TAK;
        String showJabber;
        if(u.ifShowJabber()) showJabber=NIE; else showJabber=TAK;
        return baza.dmlQuery("UPDATE " + BEE_USERS + " set " + USER_LOGIN + " = '" + u.getLogin() + "'," + USER_HASLO + " = '" + u.getHaslo() + "'," + USER_IMIE + "='" + u.getImie() + "'," + USER_NAZWISKO + "='" + u.getNazwisko() + "'," + USER_IMIE_NAZWISKO_PRYWATNE + "='" + showName + "'," + USER_EMAIL + "='" + u.getEmail() + "'," + USER_EMAIL_PRYWATNY + "='" + showEmail + "'," + USER_GG + "='" + u.getGG() + "'," + USER_GG_PRYWATNE + "='" + showGG + "'," + USER_JABBER + "='" + u.getJabber() + "',"  + USER_JABBER_PRYWATNY + "='" + showJabber + "'," + USER_AKTYWNY + "='" + aktywny + "'," + USER_ADMIN + "='" + admin + "'," + USER_MODERATOR + "='" + moderator + "'," + USER_LASTLOG + "='" + u.getLastLog() + "'," + USER_CURRENTLOG + "='" + u.getCurrentLog() + "' where " + USER_ID + "="  + new String().valueOf(u.getID()) );
    }
    
    
    /** Ustawia uzytkownika jako aktywnego
     * @param nick login uzytkownika
     * @return T lub N w zaleznosci czy update sie powiódl
     */
    public boolean setAktywnyUser(String nick){
        return baza.dmlQuery("UPDATE " + BEE_USERS + " SET " + USER_AKTYWNY + "='" + TAK + "' WHERE " + USER_LOGIN + "='" + nick + "'");
    }
    
    
    /**
     * Metoda zwaraca liste wszystkich obiektow tabeli Users z bazy
     * @return ArrayList obiektow User
     */
    public ArrayList getUsers() {
        ArrayList wynik = new ArrayList();
        ArrayList users= baza.query("SELECT * FROM "+ BEE_USERS);
        for(int i=0; i<users.size(); i++) {
            Hashtable user = (Hashtable)users.get(i);
            wynik.add(new User(Integer.decode((String)user.get(USER_ID)).intValue(),(String)user.get(USER_LOGIN),
                (String)user.get(USER_HASLO),(String)user.get(USER_IMIE),(String)user.get(USER_NAZWISKO),
                (String)user.get(USER_IMIE_NAZWISKO_PRYWATNE),(String)user.get(USER_EMAIL),(String)user.get(USER_EMAIL_PRYWATNY),
                (String)user.get(USER_GG),(String)user.get(USER_GG_PRYWATNE),(String)user.get(USER_JABBER),
                (String)user.get(USER_TLEN),(String)user.get(USER_TLEN_PRYWATNY),(String)user.get(USER_WPKONTAKT),
                (String)user.get(USER_WPKONTAKT_PRYWATNY),(String)user.get(USER_ICQ),(String)user.get(USER_ICQ_PRYWATNE),
                (String)user.get(USER_MSN),(String)user.get(USER_MSN_PRYWATNY),(String)user.get(USER_MIASTO),
                (String)user.get(USER_MIASTO_PRYWATNE),(String)user.get(USER_PLEC),(String)user.get(USER_ROKURODZENIA),
                (String)user.get(USER_ROKURODZENIA_PRYWATNY),(String)user.get(USER_JABBER_PRYWATNY),(String)user.get(USER_LASTLOG),
                (String)user.get(USER_CURRENTLOG),(String)user.get(USER_AKTYWNY),(String)user.get(USER_ADMIN),
                (String)user.get(USER_MODERATOR),this));
        }
        return wynik;
    }
    
    /**
     * Metoda zwaraca liste wszystkich obiektow tabeli Groups z bazy
     * @return ArrayList obiektow Group
     */
    public ArrayList getGroups() {
        ArrayList wynik = new ArrayList();
        ArrayList gr= baza.query("SELECT * FROM "+BEE_GROUPS);
        for(int i=0; i<gr.size(); i++) {
            Hashtable g = (Hashtable)gr.get(i);
            wynik.add(new Group(Integer.parseInt((String) g.get(GROUP_ID)), (String) g.get(GROUP_NAZWA) ));
        }
        return wynik;
    }
    
    /**
     * Metoda usuwa grupe
     * @param id id grupy
     * @return T lub N w zależności czy udalo sie usunac
     */
    public boolean usunGrupe(int id){
        baza.dmlQuery("DELETE FROM " + BEE_USERS_GROUPS + " WHERE " + USERS_GROUPS_ID_GROUP + " = " + id );
        return  baza.dmlQuery("DELETE FROM "+BEE_GROUPS+" WHERE " + GROUP_ID + " = " + id );
    }
    
    /**
     * Metoda zwaraca liste aktywnych Userów
     * @param czy_aktywny true lub false
     * @return ArrayList obiektow User
     */
    public ArrayList getUsersAktywni(boolean czy_aktywny) {
        ArrayList wynik = new ArrayList();
        String aktywny,moderator;
        if(czy_aktywny) aktywny=TAK; else aktywny=NIE;
        ArrayList users= baza.query("SELECT * FROM "+ BEE_USERS +" WHERE "+USER_AKTYWNY+"= '"+aktywny+"' ");
        for(int i=0; i<users.size(); i++) {
            Hashtable user = (Hashtable)users.get(i);
            wynik.add(new User(Integer.decode((String)user.get(USER_ID)).intValue(),(String)user.get(USER_LOGIN),
                (String)user.get(USER_HASLO),(String)user.get(USER_IMIE),(String)user.get(USER_NAZWISKO),
                (String)user.get(USER_IMIE_NAZWISKO_PRYWATNE),(String)user.get(USER_EMAIL),(String)user.get(USER_EMAIL_PRYWATNY),
                (String)user.get(USER_GG),(String)user.get(USER_GG_PRYWATNE),(String)user.get(USER_JABBER),
                (String)user.get(USER_TLEN),(String)user.get(USER_TLEN_PRYWATNY),(String)user.get(USER_WPKONTAKT),
                (String)user.get(USER_WPKONTAKT_PRYWATNY),(String)user.get(USER_ICQ),(String)user.get(USER_ICQ_PRYWATNE),
                (String)user.get(USER_MSN),(String)user.get(USER_MSN_PRYWATNY),(String)user.get(USER_MIASTO),
                (String)user.get(USER_MIASTO_PRYWATNE),(String)user.get(USER_PLEC),(String)user.get(USER_ROKURODZENIA),
                (String)user.get(USER_ROKURODZENIA_PRYWATNY),(String)user.get(USER_JABBER_PRYWATNY),(String)user.get(USER_LASTLOG),
                (String)user.get(USER_CURRENTLOG),(String)user.get(USER_AKTYWNY),(String)user.get(USER_ADMIN),
                (String)user.get(USER_MODERATOR),this));
        }
        return wynik;
    }
    
     /**
     * Metoda zwaraca liste aktywnych userów danej grupy
     * @param czy_aktywny true lub false 
     * @param id_g id grupy
     * @return ArrayList obiektow User
     */
    public ArrayList getGroupUsersAktywni(boolean czy_aktywny, int id_g) {
        ArrayList wynik = new ArrayList();
        String aktywny;
        if(czy_aktywny) aktywny=TAK; else aktywny=NIE;
        ArrayList users= baza.query("SELECT * FROM "+ BEE_USERS +", "+BEE_USERS_GROUPS+" WHERE "+USER_ID+"="+USERS_GROUPS_ID_USER+" and "+USERS_GROUPS_ID_GROUP+"= "+id_g+" and "+USER_AKTYWNY+"= '"+aktywny+"' ");
        for(int i=0; i<users.size(); i++) {
            Hashtable user = (Hashtable)users.get(i);
            wynik.add(new User(Integer.parseInt((String) user.get(USER_ID)), (String)user.get(USER_LOGIN),(String)user.get(USER_HASLO),(String)user.get(USER_IMIE),(String)user.get(USER_NAZWISKO),(String)user.get(USER_IMIE_NAZWISKO_PRYWATNE),(String)user.get(USER_EMAIL),(String)user.get(USER_EMAIL_PRYWATNY),(String)user.get(USER_GG),(String)user.get(USER_GG_PRYWATNE),(String)user.get(USER_JABBER),(String)user.get(USER_JABBER_PRYWATNY),(String)user.get(USER_LASTLOG),(String)user.get(USER_CURRENTLOG),(String)user.get(USER_AKTYWNY),(String)user.get(USER_ADMIN),(String)user.get(USER_MODERATOR),this));
        }
        return wynik;
    }
    
    
    /**
     * Metoda ustawia pola uprawnien Usera
     * @param id int id uzytkownika w bazie danych
     * @param admin boolean T lub F
     * @param aktywny boolena T lub F
     */
    public boolean zmienUpr(int id, boolean czy_admin, boolean czy_aktywny){
        String admin,aktywny;
        if(czy_admin) admin=TAK; else admin=NIE;
        if(czy_aktywny) aktywny=TAK; else aktywny=NIE;
        return  baza.dmlQuery("UPDATE "+BEE_USERS+" SET "+USER_ADMIN+"='"+admin+"' , "+USER_AKTYWNY+"='"+aktywny+"' WHERE "+USER_ID+"="+id);
    }
    
    /**
     * Metoda ustawia pole moderator Usera
     * @param id int id uzytkownika w bazie danych
     * @param czy_moderator  boolean T lub F
     */
    public boolean zmienUprModerator(int id, boolean czy_moderator){
        String moderator;
        if(czy_moderator) moderator=TAK; else moderator=NIE;
        return  baza.dmlQuery("UPDATE "+BEE_USERS+" SET "+USER_MODERATOR+"='"+moderator+"'  WHERE "+USER_ID+"="+id);
    }
    
    
    /**
     * Metoda umieszcza kategorie w bazie danych,
     * @param int id_forum
     * @param Kategoria wstawiana kategoria
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertKategoria(int id_forum, Kategoria k) {
        if ( baza.dmlQuery("INSERT INTO " + BEE_KATEGORIE + " VALUES ("+k.getID()+", '"+k.getNazwa()+"' ,'"+k.getOpis()+"', '" + (k.czyAktywna()?TAK:NIE) + "', '" + (k.czyPrywatna()?TAK:NIE) + "')")) {
            Hashtable kat = getObject("SELECT * FROM " + BEE_KATEGORIE + " WHERE "+KATEGORIA_TYTUL+" = '"+k.getNazwa()+"'");
            if (kat==null) return false;
            
            Hashtable forum = getObject("SELECT * FROM " + BEE_FORUM + " WHERE "+FORUM_ID+" = "+id_forum);
            if (forum==null) return false;
            
            return baza.dmlQuery("INSERT INTO " + BEE_FORUM_KATEGORIE + " VALUES ("+forum.get(FORUM_ID)+", "+kat.get(KATEGORIA_ID)+")");
        }
        return false;
    }
    
    /**
     * Metoda umieszcza grupe w bazie danych,
     * @param Group wstawiana grupa
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertGrupa(Group g) {
        return baza.dmlQuery("INSERT INTO "+BEE_GROUPS+" VALUES ("+g.getID()+", '"+g.getNazwa()+"' )");
    }
    
      /**
     * Metoda umieszcza usera w podanej grupie,
     * @param id_u id usera
     * @param id_g id grupy
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertUserGroup(int id_u, int id_g) {
        return baza.dmlQuery("INSERT INTO "+BEE_USERS_GROUPS+" VALUES ("+id_u+", "+id_g+" )"); 
      }
    
    /**
     * Metoda usuwa usera z podanej grupy,
     * @param id_u id usera
     * @param id_g id grupy
     * @return zwraca true jezeli dlete sie powiodl
     */
    public boolean deleteUserGroup(int id_u, int id_g) {
        return baza.dmlQuery("DELETE FROM "+BEE_USERS_GROUPS+" WHERE "+USERS_GROUPS_ID_USER+"="+id_u+" and "+USERS_GROUPS_ID_GROUP+"="+id_g); 
      }
    
    /**
     * Metoda umieszcza podforum w bazie danych,
     * @param id_kat id kategorii do ktorej dodawane jest podforum
     * @param Podforum obiekt Podforum
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertPodforum(int id_kat, Podforum p) {
        if ( baza.dmlQuery("INSERT INTO " + BEE_PODFORA + " VALUES ( "+p.getID()+", '"+p.getTytul()+"' ,'"+p.getOpis()+"' ," + p.getDataOstWypowiedzi() + " ,'" + p.getAutorOstWypowiedzi() + "', NULL , '" + (p.czyAktywne()?TAK:NIE) + "', '" + (p.czyPrywatne()?TAK:NIE) + "' ,"+p.liczbaAktywnychWatkow()+" ,"+p.liczbaAktywnychWatkow()+")")) {
            Hashtable pf = getObject("SELECT * FROM " + BEE_PODFORA + " WHERE "+PODFORUM_TYTUL+" = '"+p.getTytul()+"'");
            if (pf==null) return false;
            
            return baza.dmlQuery("INSERT INTO " + BEE_KATEGORIE_PODFORA + " VALUES ("+id_kat+", "+pf.get(PODFORUM_ID)+")");
        }
        return false;
    }
    
    
    /**
     * Metoda umieszcza wypowiedź w bazie
     * date podajemy jak sekwencje do wyliczenia daty albo w ciapkach 'data'
     * @param id_wat id wątku, w którym dodajemy wypowiedź
     * @param w obiekt Wypowiedź (bez ważnego id) ktory należy wstawić do bazy
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertWypowiedz(String id_wat, Wypowiedz w) {
        if ( baza.dmlQuery("INSERT INTO " + BEE_WYPOWIEDZI + " VALUES ("+ w.getID() +", " + w.getIDAutora() + ", '" + w.getAutor() + "' ," + w.getData() + ", '" + w.getTekst() + "','" + (w.czyPrywatna()?TAK:NIE) + "','" + (w.czyAktywna()?TAK:NIE) + "')")) {
            Hashtable wid = getObject("SELECT * FROM " + BEE_WYPOWIEDZI + " WHERE " + WYPOWIEDZ_ID_AUTORA + "=" + w.getIDAutora() + " AND " + WYPOWIEDZ_AUTOR + "= '" + w.getAutor() + "' AND " + WYPOWIEDZ_TEKST + "='" + w.getTekst() + "'");
            if (wid==null) return false;
            return baza.dmlQuery("INSERT INTO " + BEE_WATKI_WYPOWIEDZI + " VALUES (" + id_wat + "," + wid.get(WYPOWIEDZ_ID) + ")");
        }
        return false;
    }
    
    
    /**
     * Metoda umieszcza wątek w bazie
     * date podajemy jak sekwencje do wyliczenia daty albo w ciapkach 'data'
     * @param id_podforum id podforum w którym lezy wątek
     * @param w obiekt Watek (bez ważnego id) ktory należy wstawić do bazy
     * @return zwraca obiekt Watek jeżeli insert się powiódł, wpp zwraca null
     */
    public Watek insertWatek(String id_podforum, Watek w) {
        if(baza.dmlQuery("INSERT INTO " + BEE_WATKI + " VALUES ("+ w.getID() + ", " + w.getIDAutora() + ", '" + w.getAutor() + "' , '" + w.getTemat() + "' , " + w.getData() + "," + w.getDataOstWypowiedzi() + " ,'" + w.getAutorOstWypowiedzi() + "','" + (w.czyPrywatny()?TAK:NIE) + "','" + (w.czyAktywny()?TAK:NIE) + "','" + (w.czyZablokowany()?TAK:NIE) + "','" + (w.czyZamkniety()?TAK:NIE) + "'," + w.liczbaAktywnychWypowiedzi() + "," + w.licznikOdwiedzin() + ")")) {
            Hashtable watek = getObject("SELECT * FROM " + BEE_WATKI + " WHERE " + WATEK_ID_AUTORA + "=" + w.getIDAutora() +  " AND "  + WATEK_LICZBA_WYPOWIEDZI + "=" + w.liczbaAktywnychWypowiedzi() + " AND " + WATEK_LICZBA_ODWIEDZIN + " = " + w.licznikOdwiedzin() + " AND " + WATEK_TEMAT + " = '" + w.getTemat() + "'");
            if (watek==null) return null;
            if (!baza.dmlQuery("INSERT INTO " + BEE_PODFORA_WATKI + " VALUES (" + id_podforum + "," + watek.get(WATEK_ID) + ")")) return null;
            return new Watek((String)watek.get(WATEK_ID),(String)watek.get(WATEK_ID_AUTORA),(String)watek.get(WATEK_AUTOR),(String)watek.get(WATEK_TEMAT),(String)watek.get(WATEK_DATA),(String)watek.get(WATEK_DATA_OST_WYPOWIEDZI),(String)watek.get(WATEK_AUTOR_OST_WYPOWIEDZI),(String)watek.get(WATEK_PRYWATNY),(String)watek.get(WATEK_AKTYWNY),(String)watek.get(WATEK_ZABLOKOWANY),(String)watek.get(WATEK_ZAMKNIETY),(String)watek.get(WATEK_LICZBA_WYPOWIEDZI),(String)watek.get(WATEK_LICZBA_ODWIEDZIN),this);
        }
        return null;
    }
    
    /**
     * Metoda umieszcza wiersz w tabeli moderatorzy
     * @param id_kat id kategorii
     * @param id_pod id podforum
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertModerator(int id_pod, int id_user) {
        return baza.dmlQuery("INSERT INTO " + BEE_MODERATORZY + " VALUES (" + id_pod + "," +id_user + ")");
    }
    
    
    /**
     * Metoda aktualizuje watek w bazie danych
     * @param w obiekt Watek reprezentujący watek
     * @return zwraca czy update sie powiódl
     */
    public boolean updateWatek(Watek w){
        String aktywny;
        if(w.czyAktywny()) aktywny=TAK; else aktywny=NIE;
        String prywatny;
        if(w.czyPrywatny()) prywatny=TAK; else prywatny=NIE;
        String zamkniety;
        if(w.czyZamkniety()) zamkniety=TAK; else zamkniety=NIE;
        String zablokowany;
        if(w.czyZablokowany()) zablokowany=TAK; else zablokowany=NIE;
        String data_zm = "";
        if (w.checkDataOstWypowiedzi())
            data_zm = WATEK_DATA_OST_WYPOWIEDZI + " = " + getDateToInsert()  + ",";
        return baza.dmlQuery("UPDATE " + BEE_WATKI + " set " + WATEK_AUTOR + " = '" + w.getAutor() + "'," + WATEK_ID_AUTORA + " = '" + w.getIDAutora() + "'," + data_zm + WATEK_AUTOR_OST_WYPOWIEDZI + " = '" + w.getAutorOstWypowiedzi() + "'," + WATEK_TEMAT + "='" + w.getTemat() + "'," + WATEK_LICZBA_WYPOWIEDZI + "='" + w.liczbaAktywnychWypowiedzi() + "'," +  WATEK_AKTYWNY + "='" + aktywny + "'," + WATEK_PRYWATNY + "='" + prywatny + "'," + WATEK_ZABLOKOWANY + "='" + zablokowany + "'," + WATEK_ZAMKNIETY + "='" + zamkniety + "'," + WATEK_LICZBA_ODWIEDZIN + "=" + w.licznikOdwiedzin() +  " where " + WATEK_ID + "="  + new String().valueOf(w.getID()) );
    }
    
    
    /**
     * Metoda aktualizuje podforum w bazie danych
     * @param p obiekt Podforum reprezentujący podforum
     * @return zwraca czy update sie powiódl
     */
    public boolean updatePodforum(Podforum p){
        String aktywne;
        if(p.czyAktywne()) aktywne=TAK; else aktywne=NIE;
        String prywatne;
        if(p.czyPrywatne()) prywatne=TAK; else prywatne=NIE;
        String data_zm = "";
        if (p.checkDataOstWypowiedzi())
            data_zm = PODFORUM_DATA_OST_WYPOWIEDZI + "= " + getDateToInsert() + ",";
        return baza.dmlQuery("UPDATE " + BEE_PODFORA + " set " + PODFORUM_TYTUL + " = '" + p.getTytul() + "'," + PODFORUM_OPIS + " = '" + p.getOpis() + "',"  + data_zm  + PODFORUM_AUTOR_OST_WYPOWIEDZI + " = '" + p.getAutorOstWypowiedzi()  + "'," + PODFORUM_AKTYWNE + "='" + aktywne + "'," + PODFORUM_PRYWATNE + "='" + prywatne + "'," + PODFORUM_LICZBA_WYPOWIEDZI + "='" + p.liczbaAktywnychWypowiedzi() + "'," + PODFORUM_LICZBA_WATKOW + "='" + p.liczbaAktywnychWatkow() + "' where " + WATEK_ID + "="  + new String().valueOf(p.getID()) );
    }
    
    
    /**
     * Metoda zwraca id podforum w danej kategorii
     * @param int id_kat id kategorii
     * @param String tytul tytuł podforum
     * @return zwraca id podforum
     */
    public int dajIdPodforum(int id_kat, String tytul){
        Hashtable podforum = getObject("SELECT "+PODFORUM_ID+" FROM " + BEE_PODFORA + " ,"+BEE_KATEGORIE_PODFORA +" WHERE "+PODFORUM_ID+"="+KATEGORIE_PODFORA_ID_PODFORUM+" and "+KATEGORIE_PODFORA_ID_KATEGORII+"="+id_kat+" and "+PODFORUM_TYTUL+" = '" +tytul+ "'");
        if (podforum==null) return -1;
        return Integer.decode((String) podforum.get(PODFORUM_ID)).intValue();
    }
    
    
    /**
     * Metoda zwraca id kategorii, jezeli jej nie ma to -1
     * @param tytul tytul kategorii
     * @return int numer kategorii
     */
    public int dajIdKategorii(String tytul){
        Hashtable kategoria = getObject("SELECT "+KATEGORIA_ID+" FROM " + BEE_KATEGORIE + " WHERE "+KATEGORIA_TYTUL+" = '" +tytul+ "'");
        if (kategoria==null) return -1;
        return  Integer.decode((String) kategoria.get(KATEGORIA_ID)).intValue();
    }
    
    
    /**
     * Metoda zwraca id grupy, jezeli jej nie ma to -1
     * @param tytul tytul grupy
     * @return int numer grupy
     */
    public int dajIdGrupy(String tytul){
        Hashtable grupa = getObject("SELECT "+GROUP_ID+" FROM "+BEE_GROUPS+" WHERE "+GROUP_NAZWA+" = '" +tytul+ "'");
        if (grupa==null) return -1;
        return  Integer.decode((String) grupa.get(GROUP_ID)).intValue();
    }
    
    /**
     * Metoda wstawia klucz do zapomnianego hasła
     * @param email adres mailowy uzytkownika
     * @param klucz losowo wygenerowany klucz
     * @return T lub N w zależności czy insert się powiódł
     */
    public boolean wstawZapomnianeHaslo(String email, String klucz){
        return baza.dmlQuery("INSERT INTO " + BEE_FORGET_PASSWD + " VALUES ('"+ klucz +"', '"+ email + "')");
    }
    
    
    /**
     * Metoda sprawdza czy istnieje klucz do zapomnianego hasła w bazie danych
     * @param klucz losowo wygenerowany klucz
     * @return N lub T w zależności czy jest czy nie (T gdy klucza nie ma)
     */
    public boolean sprawdzKluczZapomnianeHaslo(String klucz){
        if(getObject("SELECT " + BEE_FORGET_PASSWD + " WHERE " + FORGET_PASSWD_KLUCZ +  " = " + klucz) == null)  return true;
        else return false;
    }
    
    
    /**
     * Metoda wstawia klucz do tabeli z nowymi uzytkownikami
     * @param login login uzytkownika
     * @param klucz losowo wygenerowany klucz
     * @return T lub N w zależności czy insert się powiódł
     */
    public boolean insertNewUser(String login, String klucz){
        return baza.dmlQuery("INSERT INTO " + BEE_NEW_USER + " VALUES ('"+ klucz +"', '"+ login + "')");
    }
    
    
    /**
     * Metoda sprawdza czy istnieje klucz do aktywacji konta nowego uzytkownika w bazie danych
     * @param klucz losowo wygenerowany klucz
     * @return N lub T w zależności czy jest czy nie (T gdy klucza nie ma)
     */
    public boolean sprawdzKluczNewUser(String klucz){
        if(getObject("SELECT * FROM " + BEE_NEW_USER + " WHERE " + FORGET_PASSWD_KLUCZ +  " = '" + klucz + "'") == null)  return true;
        else return false;
    }
    
    
    /**
     * Metoda sprawdza czy istnieje juz user z takim emailem
     * @param email email uzytkownika
     * @return N lub T w zalezności czy jest czy nie (T gdy emaila nie ma)
     */
    public boolean sprawdzEmail(String email){
        if(getObject("SELECT * FROM " + BEE_USERS + " WHERE " + USER_EMAIL +  "='" + email + "'") == null)  return true;
        else return false;
    }
    
    
    /**
     * Metoda usuwa klucz do zapomnianego hasła
     * @param email email uzytkownika
     * @return T lub N w zależności czy udalo sie usunac
     */
    public boolean usunZapomnianeHaslo(String email){
        return baza.dmlQuery("DELETE FROM " + BEE_FORGET_PASSWD + " WHERE " + FORGET_PASSWD_EMAIL + "='" + email + "'");
    }
    
    
    /**
     * Metoda zmienia haslo uzytkownikowi
     * @param email email uzytkownika
     * @param haslo nowe haslo podane w plain txt
     * @return T lub N w zależności czy update się powiódł
     */
    public boolean zmienHasloByEmail(String email, String haslo){
        return baza.dmlQuery("UPDATE " + BEE_USERS + " SET " + USER_HASLO + "='" + Crypto.crypt(haslo) + "'"  + " WHERE " + USER_EMAIL  + "='" + email + "'");
    }
    
    
    /**
     * Metoda usuwa klucz do nowego uzytkownika
     * @param klucz losowo wygenerowany klucz
     * @return T lub N w zale�no�ci czy insert się powiódł
     */
    public boolean usunKluczNewUser(String klucz){
        return baza.dmlQuery("DELETE FROM " + BEE_NEW_USER + " WHERE " + NEW_USER_KLUCZ + "='" + klucz + "'");
    }
    
    
    /**
     * Metoda zwaraca liste obiektow Kategoria, aktywne badz nie, na podstawie parametru czy_aktywna
     * @param aktywne boolean T lub F
     * @return ArrayList obiektow Kategoria
     */
    public ArrayList getKategorie(boolean czy_aktywna) {
        ArrayList wynik = new ArrayList();
        String aktywna;
        if(czy_aktywna) aktywna=TAK; else aktywna=NIE;
        ArrayList kategorie = baza.query("SELECT * FROM "+ BEE_KATEGORIE+" WHERE "+KATEGORIA_AKTYWNA+"='"+aktywna+"' ");
        if (kategorie == null) return wynik;
        for(int i=0;i<kategorie.size();i++) {
            Hashtable kategoria = (Hashtable)kategorie.get(i);
            wynik.add(new Kategoria((String) kategoria.get(KATEGORIA_ID), (String) kategoria.get(KATEGORIA_TYTUL), (String) kategoria.get(KATEGORIA_OPIS), (String) kategoria.get(KATEGORIA_AKTYWNA), (String) kategoria.get(KATEGORIA_PRYWATNA),this ));
        }
        return wynik;
    }
    
    
    /**
     * Metoda zwaraca liste wszystkich obiektow Kategoria.
     * @return ArrayList obiektow Kategoria
     */
    public ArrayList getKategorieAll() {
        ArrayList wynik = new ArrayList();
        ArrayList kategorie = baza.query("SELECT * FROM "+ BEE_KATEGORIE);
        for(int i=0;i<kategorie.size();i++) {
            Hashtable kategoria = (Hashtable)kategorie.get(i);
            wynik.add(new Kategoria((String) kategoria.get(KATEGORIA_ID), (String) kategoria.get(KATEGORIA_TYTUL), (String) kategoria.get(KATEGORIA_OPIS), (String) kategoria.get(KATEGORIA_AKTYWNA), (String) kategoria.get(KATEGORIA_PRYWATNA),this ));
        }
        return wynik;
    }
    
    
    /**
     * Metoda zmienia pole aktywna , a takze wszystjie podfora w niej sie zawierajace
     * @param boolean aktywne T lub F
     * @param int id kategorii
     * @return boolean true jezeli update sie powiodl dalse wpp.
     **/
    public boolean zmienAktywnoscKategorii(int id, boolean czy_aktywne){
        String aktywne;
        if(czy_aktywne) aktywne=TAK; else aktywne=NIE;
        baza.dmlQuery("UPDATE "+BEE_PODFORA+" ,"+BEE_KATEGORIE_PODFORA+" SET "+PODFORUM_AKTYWNE+"='"+aktywne+"' WHERE "+PODFORUM_ID+"="+KATEGORIE_PODFORA_ID_PODFORUM+" and "+KATEGORIE_PODFORA_ID_KATEGORII+"=" + id);
        return  baza.dmlQuery("UPDATE "+BEE_KATEGORIE+" SET "+KATEGORIA_AKTYWNA+"='"+aktywne+"' WHERE "+KATEGORIA_ID+"="+id);
    }
    
    
    /**
     * Metoda zmienia pole aktywna na podane w parametrze aktywne
     * @param String aktywne 'T' lub 'N'
     * @param String id podforum
     * @return boolean true jezeli update sie powiodl dalse wpp.
     **/
    public boolean zmienAktywnoscPodforum(int id, boolean czy_aktywne){
        String aktywne;
        if(czy_aktywne) aktywne=TAK; else aktywne=NIE;
        return  baza.dmlQuery("UPDATE "+BEE_PODFORA+" SET "+PODFORUM_AKTYWNE+"='"+aktywne+"' WHERE "+PODFORUM_ID+"="+id);
    }
    
    /**
     * Metoda zmienia pole aktywna na podane w parametrze
     * @param id identyfikator watku
     * @param czy_aktywny ustawiana aktywnosc
     * @return boolean True jezeli zmiana sie powiodla False w p.p.
     **/
    public boolean zmienAktywnoscWatku(int id, boolean czy_aktywny){
        return  baza.dmlQuery("UPDATE "+BEE_WATKI+" SET "+WATEK_AKTYWNY+"='"+ (czy_aktywny?TAK:NIE)+"' WHERE "+WATEK_ID+"="+id);
    }
    
    /**
     * Metoda zmienia pole aktywna na podane w parametrze
     * @param id identyfikator wypowiedzi
     * @param czy_aktywna ustawiana aktywnosc
     * @return boolean True jezeli zmiana sie powiodla False w p.p.
     **/
    public boolean zmienAktywnoscWypowiedzi(int id, boolean czy_aktywna){
        if (baza.dmlQuery("UPDATE "+BEE_WYPOWIEDZI+" SET "+WYPOWIEDZ_AKTYWNA+"='"+ (czy_aktywna?TAK:NIE)+"' WHERE "+WYPOWIEDZ_ID+"="+id))
            if (baza.dmlQuery("UPDATE "+BEE_WATKI+" SET "+WATEK_LICZBA_WYPOWIEDZI+"="+WATEK_LICZBA_WYPOWIEDZI+"-1 WHERE "+ WATEK_ID+"= (SELECT "+WATKI_WYPOWIEDZI_ID_WATKU+" FROM "+BEE_WATKI_WYPOWIEDZI+" WHERE "+WATKI_WYPOWIEDZI_ID_WYPOWIEDZI+"="+id+")"))
                return true;
            else return false;
        else return false;
    }
    
    /**
     * Metoda zmienia pole prywatne na podane w parametrze
     * @param id identyfikator kategorii
     * @param czy_prywatny ustawiana prywatnosc
     * @return boolean True jezeli zmiana sie powiodla False w p.p.
     **/
    public boolean zmienPrywatnoscKategorii(int id, boolean czy_prywatny){
        return  baza.dmlQuery("UPDATE "+BEE_KATEGORIE+" SET "+KATEGORIA_PRYWATNA+"='"+ (czy_prywatny?TAK:NIE)+"' WHERE "+KATEGORIA_ID+"="+id);
    }
    
    /**
     * Metoda zmienia pole prywatne na podane w parametrze
     * @param id identyfikator podforum
     * @param czy_prywatny ustawiana prywatnosc
     * @return boolean True jezeli zmiana sie powiodla False w p.p.
     **/
    public boolean zmienPrywatnoscPodforum(int id, boolean czy_prywatny){
        return  baza.dmlQuery("UPDATE "+BEE_PODFORA+" SET "+PODFORUM_PRYWATNE+"='"+ (czy_prywatny?TAK:NIE)+"' WHERE "+PODFORUM_ID+"="+id);
    }
    
    /**
     * Metoda zmienia pole prywatne na podane w parametrze
     * @param id identyfikator wątku
     * @param czy_prywatny ustawiana prywatnosc
     * @return boolean True jezeli zmiana sie powiodla False w p.p.
     **/
    public boolean zmienPrywatnoscWatku(int id, boolean czy_prywatny){
        return  baza.dmlQuery("UPDATE "+BEE_WATKI+" SET "+WATEK_PRYWATNY+"='"+ (czy_prywatny?TAK:NIE)+"' WHERE "+WATEK_ID+"="+id);
    }
    
    /**
     * Metoda zmienia pole prywatne na podane w parametrze
     * @param id identyfikator wypowiedzi
     * @param czy_prywatny ustawiana prywatnosc
     * @return boolean True jezeli zmiana sie powiodla False w p.p.
     **/
    public boolean zmienPrywatnoscWypowiedzi(int id, boolean czy_prywatny){
        return  baza.dmlQuery("UPDATE "+BEE_WYPOWIEDZI+" SET "+WYPOWIEDZ_PRYWATNA+"='"+ (czy_prywatny?TAK:NIE)+"' WHERE "+WYPOWIEDZ_ID+"="+id);
    }
    
    /**
     * Metoda zmienia pole zablokowany na podane w parametrze
     * @param id identyfikator watku
     * @param czy_blokowac ustawiane blokowanie
     * @return boolean True jezeli zmiana sie powiodla False w p.p.
     **/
    public boolean blokowanieWatku(int id, boolean czy_blokowac){
        return  baza.dmlQuery("UPDATE "+BEE_WATKI+" SET "+WATEK_ZABLOKOWANY+"='"+ (czy_blokowac?TAK:NIE)+"' WHERE "+WATEK_ID+"="+id);
    }
    
    /**
     * Metoda zmienia pole zamkniety na podane w parametrze
     * @param id identyfikator watku
     * @param czy_zamkniety ustawiane zamkniecie
     * @return boolean True jezeli zmiana sie powiodla False w p.p.
     **/
    public boolean zamykanieWatku(int id, boolean czy_zamkniety){
        return  baza.dmlQuery("UPDATE "+BEE_WATKI+" SET "+WATEK_ZAMKNIETY+"='"+ (czy_zamkniety?TAK:NIE)+"' WHERE "+WATEK_ID+"="+id);
    }
    
    /**
     * Metoda zmienia tytul i opis kategorii
     * @param String id kategorii
     * @param String tytul kategorii
     * @param String opis kategorii
     * @return boolean true jezeli update sie powiodl dalse wpp.
     */
    public boolean updateKategoria(int id, String tytul, String opis ){
        return  baza.dmlQuery("UPDATE "+BEE_KATEGORIE+" SET "+KATEGORIA_TYTUL+"='"+tytul+"' , "+KATEGORIA_OPIS+"='"+opis+"' WHERE "+KATEGORIA_ID+"="+id);
        
    }
    
    
    // DO ZMIANY !!!! metoda ma przyjmować OBIEKT!!!
    /**
     * Metoda zmienia tytul i opis podforum, oraz zmienia kategorie podforum na id_kat
     * @param int id podforum
     * @param int id_kat kategorii
     * @param String tytul kategorii
     * @param String opis kategorii
     * @return boolean true jezeli update sie powiodl dalse wpp.
     */
    public boolean updatePodforum(int id, int id_kat, String tytul, String opis ){
        return  ( baza.dmlQuery("UPDATE "+BEE_KATEGORIE_PODFORA+" SET "+KATEGORIE_PODFORA_ID_KATEGORII+"="+id_kat+" WHERE "+KATEGORIE_PODFORA_ID_PODFORUM+"="+id)
        &&  baza.dmlQuery("UPDATE "+BEE_PODFORA+" SET "+PODFORUM_TYTUL+"='"+tytul+"' , "+PODFORUM_OPIS+"='"+opis+"' WHERE "+PODFORUM_ID+"="+id));
    }
    
    
    /** Metoda zwraca aktualną datę dla wybranej bazy danych
     * @return String reprezentujacy date dla aktualnej bazy danych
     */
    public static String getDate() {
        return "2006-09-09 12:12:12";
    }
    
    
    /** Metoda zwraca sekwencje do wyliczenia daty lub date dla wybranej bazy danych
     * @return String reprezentujacy date lub sekwencje do wyliczenia daty dla aktualnej bazy danych
     */
    public static String getDateToInsert() {
        return "NOW()";
    }
    
    
    /** Metoda zwraca date przygotowaną do updejtu, w zależności od bazy data jest różna
     * @param data string reprezentujacy date
     * @return String reprezentujacy date przygotowaną do updejtu
     */
    public static String prepareDateToUpdate(String data) {
        return data;
    }
    
    
    /** Metoda zwraca datę dla wybranej bazy danych
     * @param year String reprezentujacy rok
     * @param mounth String reprezentujacy miesiac
     * @param day String reprezentujacy dzień
     * @param hour String reprezentujacy godzine
     * @param min String reprezentujacy minute
     * @param sec String reprezentujacy sekunde
     * @return String reprezentujacy date dla aktualnej bazy danych
     */
    public static String getDate(String year,String mounth,String day,String hour,String min,String sec) {
        return year + "-" + mounth + "-" + day + " :"+ hour + ":" + min + ":" + sec;
    }
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami uzytkownikow,
     * ktorzy sa moderatorami w danym podforum
     * @param int id podforum
     * @return ArrayList obiektow Integer
     */
    public ArrayList getIdModeratorzyPodforum(int id_podforum) {
        ArrayList wynik = new ArrayList();
        ArrayList users_id = baza.query("SELECT " + MODERATORZY_ID_USER + " FROM "+ BEE_MODERATORZY+" WHERE "+MODERATORZY_ID_PODFORUM+"="+id_podforum);
        for(int i=0;i<users_id.size();i++) {
            Hashtable user = (Hashtable)users_id.get(i);
            int id = Integer.parseInt((String)user.get(MODERATORZY_ID_USER));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    /**
     * Metoda sprawdza czy podany uzytkownik jest moderatorem podanego podforum
     * @param user_id identyfikator uzytkownika
     * @param podforum_id identyfikator podforum
     * @return True jest uzytkownik jest moderatorem podanego forum False w p.p.
     */
    public boolean isModerator(int user_id, int podforum_id){
        ArrayList users_id = baza.query("SELECT " + MODERATORZY_ID_USER + " FROM "+ BEE_MODERATORZY+" WHERE ("+MODERATORZY_ID_PODFORUM+"="+podforum_id+") AND (" +MODERATORZY_ID_USER+"="+user_id+")");
        if (users_id.size() > 0) return true;
        else return false;
    }
    
    
    /**
     * Metoda pozwala zabanowac uzytkownika lub <i>odbanowac - to sie moze zakonczyc niepowodzeniem, bo nie bylo polaczenia z baza lub nie bylo co odbanowywac</i>
     * @param user_id identyfikator uzytkownika
     * @param podforum_id identyfikator podforum
     * @param ban okresla czy nalezy zabanowac uzytkownika czy odbanowac
     * @return True jest operacja powiodla sie False w p.p.
     */
    public boolean banUser(int user_id, int id_podforum, boolean ban){
        if (ban)
            return baza.dmlQuery("INSERT INTO "+BEE_BANNED_USERS+"("+BANNED_USERS_ID_USER+","+BANNED_USERS_ID_PODFORUM+") VALUES("+user_id+","+id_podforum+")");
        else return baza.dmlQuery("DELETE FROM "+BEE_BANNED_USERS+" WHERE ("+BANNED_USERS_ID_USER+"="+user_id+")AND("+BANNED_USERS_ID_PODFORUM+"="+id_podforum+")");
    }
    
    
    /** Metoda dostarcza podfora moderowane przez wskazanego uzytkownika
     * @param user_id identyfikator uzytkownika
     * @return ArrayList obiektow podforum
     */
    public ArrayList getModerowanePodfora(int user_id){
        ArrayList id_podfor = baza.query("SELECT " + MODERATORZY_ID_PODFORUM + " FROM "+ BEE_MODERATORZY+" WHERE "+MODERATORZY_ID_USER+"="+user_id);
        if (id_podfor == null) return null;
        ArrayList wynik = new ArrayList();
        for(int i=0;i<id_podfor.size();i++) {
            Hashtable podforum = (Hashtable)id_podfor.get(i);
            int id = Integer.parseInt((String)podforum.get(MODERATORZY_ID_PODFORUM));
            Podforum p = getPodforum(id);
            if (p != null)
                wynik.add(p);
        }
        return wynik;
    }
    
    /** Metoda przenosi watek z jednego podforum do drugiego
     * @param watek Przenoszony watek
     * @param id_from Identyfikator podforum z ktorego usuwamy
     * @param id_to Identyfikator podforum do ktorego przenosimy
     * @return True jesli operacja powiodla sie
     */
    public boolean moveWatek(Watek watek,int id_from,int id_to){
        if (id_from == id_to) return true;
        if (baza.dmlQuery("INSERT INTO "+BEE_PODFORA_WATKI+"("+PODFORA_WATKI_ID_PODFORUM+","+PODFORA_WATKI_ID_WATKU+") VALUE("+id_to+","+watek.getID()+")"))
            if (baza.dmlQuery("DELETE FROM "+BEE_PODFORA_WATKI+" WHERE ("+PODFORA_WATKI_ID_PODFORUM+"="+id_from+")AND("+PODFORA_WATKI_ID_WATKU+"="+watek.getID()+")"))
                //teraz trzeba przeniesc liczby
                if (baza.dmlQuery("UPDATE "+BEE_PODFORA+" SET "+PODFORUM_LICZBA_WATKOW+"="+PODFORUM_LICZBA_WATKOW+"-1,"+PODFORUM_LICZBA_WYPOWIEDZI+"="+PODFORUM_LICZBA_WYPOWIEDZI+"-"+watek.liczbaAktywnychWypowiedzi()+" WHERE "+PODFORUM_ID+"="+id_from))
                    if (baza.dmlQuery("UPDATE "+BEE_PODFORA+" SET "+PODFORUM_LICZBA_WATKOW+"="+PODFORUM_LICZBA_WATKOW+"+1,"+PODFORUM_LICZBA_WYPOWIEDZI+"="+PODFORUM_LICZBA_WYPOWIEDZI+"+"+watek.liczbaAktywnychWypowiedzi()+" WHERE "+PODFORUM_ID+"="+id_to))
                        return true;
                    else return false;
                else return false;
            else return false;
        else return false;
    }
    
    /** Metoda podmienia tekst wypowiedzi. Powoduje to wprowadzenie nowej wypoweidzi i wstawienie jej do dotychczasowego watku
     * @param w Obiekt wypowiedz w ktorym zmieniamy tekst
     * @param id_watku Identyfikator watku ktory jest wlascicielem zmienianej wypowiedzi
     * @param newText Nowa zawartosc wypowiedzi
     * @return True w przypadku poprawnej zmiany
     */
    public boolean zmienTekstWypowiedzi(Wypowiedz w, int id_watku, String newText){
        if (baza.dmlQuery("INSERT INTO " + BEE_WYPOWIEDZI + " VALUES (0," + w.getIDAutora() + ", '" + w.getAutor() + "' , '" +  prepareDateToUpdate(w.getData()) + "' , '" + newText + "','" + (w.czyPrywatna()?TAK:NIE) + "','" + (w.czyAktywna()?TAK:NIE) + "')")) {
            Hashtable wid = getObject("SELECT "+WYPOWIEDZ_ID+" FROM " + BEE_WYPOWIEDZI + " WHERE " + WYPOWIEDZ_ID_AUTORA + "=" + w.getIDAutora() + " AND " + WYPOWIEDZ_TEKST + "='" + newText + "' AND " + WYPOWIEDZ_DATA + " = '" + w.getData() + "'");
            if (wid==null) return false;
            if (baza.dmlQuery("INSERT INTO " + BEE_WATKI_WYPOWIEDZI+"("+WATKI_WYPOWIEDZI_ID_WATKU+","+WATKI_WYPOWIEDZI_ID_WYPOWIEDZI+") VALUES (" + id_watku + "," + wid.get(WYPOWIEDZ_ID) + ")"))
                return baza.dmlQuery("DELETE FROM " + BEE_WATKI_WYPOWIEDZI + " WHERE " + WATKI_WYPOWIEDZI_ID_WYPOWIEDZI+"="+ w.getID());
            else return false;
        }
        return false;
    }
    
    /**
     * Metoda usuwa uprawnienie z tabeli moderatorzy
     * @param id_pod int id podforum
     * @param id_user int id usera
     */
    public boolean usunPrawaModeratora(int id_pod, int id_user){
        
        return baza.dmlQuery("DELETE FROM "+BEE_MODERATORZY+ " WHERE "+MODERATORZY_ID_USER+"="+id_user+" and "+MODERATORZY_ID_PODFORUM+"="+id_pod);
    }
    
    
    /**
     * Metoda wyszukuje w tytulach Watkow podanej frazy i zwraca je
     * @param fraza String z szukana fraza
     * @param limit Liczba ograniczająca liczność wyniku
     * @param from Numer wiersza wyniku od którego zostanie zwrócony wynik
     * @return ArrayList obiektów Watek o liczności <= limit
     */
    public ArrayList searchWatki(String fraza, int limit, int from){
        ArrayList watki = baza.query("SELECT * FROM "+BEE_WATKI+" WHERE "+WATEK_TEMAT+" LIKE '%"+fraza+"%' LIMIT "+from+","+limit);
        ArrayList wynik = new ArrayList();
        if (watki == null) return wynik;
        for(int i=0; i < watki.size(); i++) {
            Hashtable watek = (Hashtable)watki.get(i);
            wynik.add(new Watek((String)watek.get(WATEK_ID),(String)watek.get(WATEK_ID_AUTORA),(String)watek.get(WATEK_AUTOR),(String)watek.get(WATEK_TEMAT),(String)watek.get(WATEK_DATA),(String)watek.get(WATEK_DATA_OST_WYPOWIEDZI),(String)watek.get(WATEK_AUTOR_OST_WYPOWIEDZI),(String)watek.get(WATEK_PRYWATNY),(String)watek.get(WATEK_AKTYWNY),(String)watek.get(WATEK_ZABLOKOWANY),(String)watek.get(WATEK_ZAMKNIETY),(String)watek.get(WATEK_LICZBA_WYPOWIEDZI),(String)watek.get(WATEK_LICZBA_ODWIEDZIN),this));
        }
        return wynik;
    }
    
    /**
     * Metoda wyszukuje w tekstach Wypowiedzi podanej frazy i zwraca ich identyfikatory
     * @param fraza String z szukana fraza
     * @param limit Liczba ograniczająca liczność wyniku
     * @param from Numer wiersza wyniku od którego zostanie zwrócony wynik
     * @return ArrayList obiektów Integer o liczności <= limit
     */
    public ArrayList searchWypowiedzi(String fraza, int limit, int from){
        ArrayList wypowiedzi = baza.query("SELECT "+WYPOWIEDZ_ID+" FROM "+BEE_WYPOWIEDZI+" WHERE "+WYPOWIEDZ_TEKST+" LIKE '%"+fraza+"%' LIMIT "+from+","+limit);
        ArrayList wynik = new ArrayList();
        if (wypowiedzi == null) return wynik;
        for(int i=0;i<wypowiedzi.size();i++){
            Hashtable id = (Hashtable)wypowiedzi.get(i);
            try{
                wynik.add(new Integer((String)id.get(WYPOWIEDZ_ID)));
            }catch(NumberFormatException e) {}
        }
        return wynik;
    }
    
    /** Metoda sprawdza czy dany uzytkownik ma podane prawa do danego Podforum
     * @param id_user Identyfikator użytkownika
     * @param id_podforum Identyfikator podforum
     * @param odczyt Prawo odczytu
     * @param zapis Prawo pisania
     * @return True jesli użytkownik ma prawo odczytu wskazanego Podforum lub False w p.p.
     */
    public boolean hasPodforumRights(int id_user, int id_podforum, boolean odczyt, boolean zapis){
        ArrayList prawa = baza.query("SELECT "+PRIVILAGES_ID_PISANIE+","+PRIVILAGES_ID_CZYTANIE+" FROM "+BEE_PRIVILAGES+" p,"+BEE_USERS_GROUPS+" u WHERE p."+PRIVILAGES_ID_GROUP+"=u."+USERS_GROUPS_ID_GROUP+" AND p."+PRIVILAGES_ID_PODFORUM+"="+id_podforum+" AND "+USERS_GROUPS_ID_USER+"="+id_user);
        //TODO Pytanie co jak user jest wiecej niz w jednej grupie ktora ma jakies prawa do tego podforum. Co gorsza wykluczajace sie prawa ? Trzeba wtedy przejrzec wszystkie te prawa i podjac decyzja. Damy prawo adminowi wybrac czy wazniejsze jest prawo odczytu czy zapisu.
        if (prawa.size() <=0) return false;
        Hashtable prawo = (Hashtable)prawa.get(0);
        String s_odczyt = (String)prawo.get(PRIVILAGES_ID_CZYTANIE);
        String s_zapis = (String)prawo.get(PRIVILAGES_ID_PISANIE);
        return (odczyt?(s_odczyt.compareTo(TAK) == 0):true) && (zapis?(s_zapis.compareTo(TAK) == 0):true);
    }
    
    /** Metoda sprawdza czy dany uzytkownik ma podane prawa do danej Kategorii
     * @param id_user Identyfikator użytkownika
     * @param id_kategorii Identyfikator Kategorii
     * @param odczyt Prawo odczytu
     * @param zapis Prawo pisania
     * @return True jesli użytkownik ma prawo odczytu wskazanego Podforum lub False w p.p.
     */
    public boolean hasKategoriaRights(int id_user, int id_kategorii, boolean odczyt, boolean zapis){
        ArrayList prawa = baza.query("SELECT p."+PRIVILAGES_ID_PISANIE+",p."+PRIVILAGES_ID_CZYTANIE+" FROM "+BEE_PRIVILAGES+" p,"+BEE_USERS_GROUPS+" u WHERE p."+PRIVILAGES_ID_GROUP+"=u."+USERS_GROUPS_ID_GROUP+" AND p."+PRIVILAGES_ID_KATEGORIA+"="+id_kategorii+" AND "+USERS_GROUPS_ID_USER+"="+id_user);
        //TODO Pytanie co jak user jest wiecej niz w jednej grupie ktora ma jakies prawa do tego podforum. Co gorsza wykluczajace sie prawa ? Trzeba wtedy przejrzec wszystkie te prawa i podjac decyzja. Damy prawo adminowi wybrac czy wazniejsze jest prawo odczytu czy zapisu.
        if (prawa.size() <=0) return false;
        Hashtable prawo = (Hashtable)prawa.get(0);
        String s_odczyt = (String)prawo.get(PRIVILAGES_ID_CZYTANIE);
        String s_zapis = (String)prawo.get(PRIVILAGES_ID_PISANIE);
        return (odczyt?(s_odczyt.compareTo(TAK) == 0):true) && (zapis?(s_zapis.compareTo(TAK) == 0):true);
    }
    
    
}

