/*
 * DataBase.java
 *
 * Created on 7 marzec 2005, 10:16
 */

package pl.ltd.bee;

import java.util.*;
import pl.aislib.util.crypt.UnixCrypt;

/**
 * Klasa wyzszy interfejs do bazy danych. Dostarcza metody do uzyskania danych zawartych na forach.
 * @author wilk
 */

/*
(13:10:55) Wilk:  "wymyslilem" jak latwo mozemy buforowac zapytania z bazy danych
(13:12:21) Wilk:  wystarczy miec w DataBase static lub nie zmienna np HashTable i po kazdym zapytaniu wrzucac obiekty: put("Objekt_ID",Object)
(13:12:27) Wilk:  final-> ok
(13:12:33) Wilk:  wiec np:
(13:13:44) Wilk:  getWatek(id)
if ( jest object w hashtable("Watek_"+id)) to go zwroc
SELECT
Hastable.put("Watek_"+id,to co zwrocil SELECT);
(13:14:12) Wilk:  oczywiscie to powinno byc troszke madrzejsze, to znaczy to powinna byc HashTable z LRU
(13:14:47) Wilk:  i o zalozonym rozmiarze .. np 100 objektow
(13:15:25) pawelb:  brzmi sensownie
(13:15:52) Wilk:  mozna tez zrobic oddzielne bufory dla kazdego z objektow wtedy mozna wiecej stoic, np 10 miejsc na Kategorie ale juz 100 miejsc na Watki a 300 na wypowiedzi
(13:16:22) pawelb:  spoko, tylko mo¿e narazie siê wstrzymamy z implementacj±
(13:16:36) pawelb:  bo musze troche przerobiæ DataBase
(13:16:59) Wilk:  wtedy oczywiscie jest wiecej zmiennych, ale nie ma "Object_"+id tylko wystarczy sam id
(13:17:36) Wilk:  nie no implementacje nie teraz, to oczywiste
(13:18:01) Wilk:  tylko jak bys mogl moze wkleic te rozmowe do DataBase, albo do /teksty
(13:18:02) Wilk:  ok ?
(13:18:23) pawelb:  ok
 **/

public class DataBase {
    
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
    
    /**
     * Stala reprezentujaca nazwe tabeli w bazie danych
     */
    static String BEE_USERS = "Users";
    
    static String BEE_WATKI = "Watki";
    static String BEE_WYPOWIEDZI = "Wypowiedzi";
    static String BEE_PODFORA = "Podfora";
    static String BEE_KATEGORIE = "Kategorie";
    static String BEE_FORUM = "Forum";
    
    static String BEE_FORUM_KATEGORIE = "Forum_Kategorie";
    static String BEE_WATKI_WYPOWIEDZI = "Watki_Wypowiedzi";
    static String BEE_PODFORA_WATKI = "Podfora_Watki";
    static String BEE_KATEGORIE_PODFORA = "Kategorie_Podfora";
    static String BEE_MODERATORZY = "Moderatorzy";
    
    /**
     * Stala reprezentujaca nazwe pola w tabeli w bazie danych
     */
    static final String WATEK_ID = "ID";
    static final String WATEK_ID_AUTORA = "ID_AUTORA";
    static final String WATEK_TEMAT = "TEMAT";
    static final String WATEK_DATA = "DATA";
    
    static final String WYPOWIEDZ_ID = "ID";
    static final String WYPOWIEDZ_ID_AUTORA = "ID_AUTORA";
    static final String WYPOWIEDZ_DATA = "DATA";
    static final String WYPOWIEDZ_TEKST = "TEKST";
    
    static final String PODFORUM_ID = "ID";
    static final String PODFORUM_TYTUL = "TYTUL";
    
    static final String FORUM_ID = "ID";
    static final String FORUM_NAZWA = "NAZWA";
    
    static final String KATEGORIA_ID = "ID";
    static final String KATEGORIA_TYTUL = "TYTUL";
    
    static final String WATKI_WYPOWIEDZI_ID_KATEGORII = "ID_KATEGORIA";
    static final String WATKI_WYPOWIEDZI_ID_PODFORUM = "ID_PODFORUM";
    
    static final String PODFORA_WATKI_ID_PODFORUM = "ID_PODFORUM";
    static final String PODFORA_WATKI_ID_WATKU = "ID_WATKU";
    
    static final String WATKI_WYPOWIEDZI_ID_WATKU = "ID_WATKU";
    static final String WATKI_WYPOWIEDZI_ID_WYPOWIEDZI = "ID_WYPOWIEDZI";
    
    static final String USER_ID = "ID";
    static final String USER_LOGIN = "LOGIN";
    static final String USER_HASLO = "HASLO";
    static final String USER_ADMIN = "ADMIN";
    static final String USER_MODERATOR = "MODERATOR";
    
    static final String FORUM_KATEGORIE_ID_FORUM = "ID_FORUM";
    static final String FORUM_KATEGORIE_ID_KATEGORIA = "ID_KATEGORIA";
    //TODO baza jest static czyli jeden obiekt dla wszystkich obiektow klasy DataBase. Konstruktor(Host,User,Pass) zmieni ten obiekt dla wszystkich tych obiektow. To trzeba miec na uwadze w przyszlosci
    boolean connected = false;
    ConnectorDB baza = new ConnectorDB("localhost","Bee","bee","bee");
    
    
    /** Konstruktor bezargumentowy. Domyslnie laczy sie z baza Bee:bee@localhost. */
    public DataBase() {
    }
    
    /**
     * Konstruktor
     * @param host Adres serwera bazy danych
     * @param Db nazwa bazy danych
     * @param user Nazwa uzytkownika bazy danych
     * @param pass Haslo uzytkownika bazy danych
     */
    public DataBase(String host, String Db,String user, String pass) {
        this.connect(host, Db, user, pass);
    }
    
    /**
     * metoda sprawdzajaca czy obiekt polaczyl sie z baza - a dokladniej czy dostal base,usera i haslo
     */
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * Metoda powoduje przylaczenie do bazy o podanych parametrach
     * @param host Adres serwera bazy danych
     * @param Db nazwa bazy danych
     * @param user Nazwa uzytkownika bazy danych
     * @param pass Haslo uzytkownika bazy danych
     */
    public void connect(String host, String Db, String user, String pass) {
        baza = new ConnectorDB(host,Db,user,pass);
        connected=true;
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
        //return baza.query("SELECT * FROM " + BEE_WATKI + " WHERE ID=" + ID);
        Hashtable watek = getObject("SELECT * FROM " + BEE_WATKI + " WHERE "+ WATEK_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID, ID_autora, Temat i Date
        if (watek == null) return null;
        return new Watek((String)watek.get(WATEK_ID),(String)watek.get(WATEK_ID_AUTORA),(String)watek.get(WATEK_TEMAT),(String)watek.get(WATEK_DATA));
    }
    
    /**
     * Metoda zwaraca objekt Wypowiedz o podanym identyfikatorze
     * @param ID Identyfikator szukanej wypowiedzi
     * @return Zwraca obiekt Wypowiedz badz null w razie bledu.
     */
    public Wypowiedz getWypowiedz(int ID){
        Hashtable wypowiedz = getObject("SELECT * FROM " + BEE_WYPOWIEDZI + " WHERE " + WYPOWIEDZ_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID, ID_autora, Date i Tekst
        if (wypowiedz == null) return null;
        return new Wypowiedz((String)wypowiedz.get(WYPOWIEDZ_ID),(String)wypowiedz.get(WYPOWIEDZ_ID_AUTORA),(String)wypowiedz.get(WYPOWIEDZ_DATA),(String)wypowiedz.get(WYPOWIEDZ_TEKST));
    }
    
    /**
     * Metoda zwaraca objekt Podforum o podanym identyfikatorze
     * @param ID Identyfikator szukanego podforum
     * @return Zwraca obiekt Podforum badz null w razie bledu.
     */
    public Podforum getPodforum(int ID){
        Hashtable podforum = getObject("SELECT * FROM " + BEE_PODFORA + " WHERE " + PODFORUM_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID i Tytul
        if (podforum == null) return null;
        
        //zrobic arraylist z lista watkow
        return new Podforum((String)podforum.get(PODFORUM_ID),(String)podforum.get(PODFORUM_TYTUL));
    }
    
    /**
     * Metoda zwaraca objekt Forum
     * @return Zwraca obiekt Forum badz null w razie bledu.
     */
    public Forum getForum(){
        Hashtable forum = getObject("SELECT * FROM " + BEE_FORUM);
        if (forum == null) return null;
        return new Forum((String)forum.get(FORUM_NAZWA),this.getKategorieForum(),this);
    }
    
    /**
     * Metoda zwaraca objekt Kategoria o podanym identyfikatorze
     * @param ID Identyfikator szukanej Kategorii
     * @return Zwraca obiekt Kategoria badz null w razie bledu.
     */
    public Kategoria getKategoria(int ID){
        Hashtable kategoria = getObject("SELECT * FROM " + BEE_KATEGORIE + " WHERE " + KATEGORIA_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID i Tytul
        if (kategoria == null) return null;
        return new Kategoria((String)kategoria.get(KATEGORIA_ID),(String)kategoria.get(KATEGORIA_TYTUL),this.getPodforaKategorii(ID),this);
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
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami podfor w podanej Kategorii
     * @param kat Kategoria w ramach ktorej interesuja nas podfora
     * @return ArrayList obiektow Integer
     */
    public ArrayList getPodforaKategorii(int ID) {
        ArrayList wynik = new ArrayList();
        ArrayList podfora = baza.query("SELECT "+ WATKI_WYPOWIEDZI_ID_PODFORUM + " FROM "+ BEE_KATEGORIE_PODFORA + " WHERE " + WATKI_WYPOWIEDZI_ID_KATEGORII + "=" + ID);
        for(int i=0;i<podfora.size();i++) {
            Hashtable podforum = (Hashtable)podfora.get(i);
            int id = Integer.parseInt((String)podforum.get(WATKI_WYPOWIEDZI_ID_PODFORUM));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami Watkow w podanym Podforum
     * @param pod Podforum w ramach ktorego interesuja nas watki
     * @return ArrayList obiektow Integer
     */
    public ArrayList getWatkiPodforum(Podforum pod) {
        ArrayList wynik = new ArrayList();
        ArrayList watki = baza.query("SELECT * FROM "+ BEE_PODFORA_WATKI + " WHERE " + PODFORA_WATKI_ID_PODFORUM + "=" + pod.getID());
        for(int i=0;i<watki.size();i++) {
            Hashtable watek = (Hashtable)watki.get(i);
            int id = Integer.parseInt((String)watek.get(PODFORA_WATKI_ID_WATKU));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami Wypowiedzi w podanym Watku
     * @param wat Watek w ramach ktorego interesuja nas Wypowiedzi
     * @return ArrayList obiektow Integer
     */
    public ArrayList getWypowiedziWatku(Watek wat) {
        ArrayList wynik = new ArrayList();
        ArrayList wypowiedzi = baza.query("SELECT * FROM "+ BEE_WATKI_WYPOWIEDZI + " WHERE " + WATKI_WYPOWIEDZI_ID_WATKU + "=" + wat.getID());
        for(int i=0;i<wypowiedzi.size();i++) {
            Hashtable wypowiedz = (Hashtable)wypowiedzi.get(i);
            int id = Integer.parseInt((String)wypowiedz.get(WATKI_WYPOWIEDZI_ID_WYPOWIEDZI));
            wynik.add(new Integer(id));
        }
        return wynik;
    }
    
    /**
     * Metoda zwaraca objekt User o podanym identyfikatorze
     * @param ID Identyfikator szukanego uzytkownika
     * @return Zwraca obiekt User badz null w razie bledu.
     */
    public User getUser(int ID){
        Hashtable user = getObject("SELECT * FROM " + BEE_USERS + " WHERE "+ USER_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID, Login, Haslo, Admin, Moderator
        if (user == null) return null;
        return UserFactory.getUser((String)user.get(USER_ID),(String)user.get(USER_LOGIN),(String)user.get(USER_HASLO),"imie","nazwisko","email","gg","jabber",(String)user.get(USER_ADMIN),(String)user.get(USER_MODERATOR));
    }
    
    
    /**
     * Metoda zwaraca objekt User o podanym identyfikatorze
     * @param login Identyfikator (login) szukanego uzytkownika
     * @return Zwraca obiekt User badz null w razie bledu.
     */
    public User getUser(String login) {
        Hashtable user = getObject("SELECT * FROM " + BEE_USERS + " WHERE "+ USER_LOGIN +" like \"" + login + "\"");
        //zakladam ze mam konstruktor ktory bierze ID, Login, Haslo, Admin, Moderator
        if (user == null) return null;
        return UserFactory.getUser((String)user.get(USER_ID),(String)user.get(USER_LOGIN),(String)user.get(USER_HASLO),"imie","nazwisko","email","gg","jabber",(String)user.get(USER_ADMIN),(String)user.get(USER_MODERATOR));
    }
    
    /**
     * Metoda umieszcza uzytkownika w bazie danych
     * @param nick nick uzytkownika
     * @param imie imie uzytkownika
     * @param nazwisko nazwisko uzytkownika
     * @param email adres emailowy uzytkownika
     * @param gg numer gadugadu
     * @param jabber adres konta jabbera uzytkownika
     * @param haslo niezakodowane haslo
     * @return zwraca czy insert sie powidl
     */
    public boolean insertUser(String nick, String imie, String nazwisko, String email, String gg, String jabber, String haslo){
        return baza.dmlQuery("INSERT INTO " + BEE_USERS + " VALUES (0,\"" + nick + "\",\"" + UnixCrypt.crypt(haslo) + "\",'N','N')");
    }
    
    
}
