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
    
    static String BEE_FORGET_PASSWD = "Forget_Passwd";
    
    static String BEE_NEW_USER = "New_User";
    
    /**
     * Stala reprezentujaca nazwe pola w tabeli w bazie danych
     */
    static final String WATEK_ID = "ID";
    static final String WATEK_ID_AUTORA = "ID_AUTORA";
    static final String WATEK_AUTOR = "AUTOR";
    static final String WATEK_TEMAT = "TEMAT";
    static final String WATEK_DATA = "DATA";
    static final String WATEK_PRYWATNY = "PRYWATNY";
    
    static final String WYPOWIEDZ_ID = "ID";
    static final String WYPOWIEDZ_ID_AUTORA = "ID_AUTORA";
    static final String WYPOWIEDZ_AUTOR = "AUTOR";
    static final String WYPOWIEDZ_DATA = "DATA";
    static final String WYPOWIEDZ_TEKST = "TEKST";
    static final String WYPOWIEDZ_PRYWATNA = "PRYWATNA";
    
    static final String PODFORUM_ID = "ID";
    static final String PODFORUM_TYTUL = "TYTUL";
    static final String PODFORUM_OPIS = "OPIS";
    static final String PODFORUM_AKTYWNE = "AKTYWNE";
    static final String PODFORUM_PRYWATNE = "PRYWATNE";
    
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
    static final String USER_EMAIL = "EMAIL";
    static final String USER_GG = "GG";
    static final String USER_JABBER = "JABBER";
    static final String USER_LASTLOG = "OSTATNIELOGOWANIE";
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
        baza.connect();
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
        return new Watek((String)watek.get(WATEK_ID),(String)watek.get(WATEK_ID_AUTORA),(String)watek.get(WATEK_AUTOR),(String)watek.get(WATEK_TEMAT),(String)watek.get(WATEK_DATA),(String)watek.get(WATEK_PRYWATNY),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Wypowiedz o podanym identyfikatorze
     * @param ID Identyfikator szukanej wypowiedzi
     * @return Zwraca obiekt Wypowiedz badz null w razie bledu.
     */
    public Wypowiedz getWypowiedz(int ID){
        Hashtable wypowiedz = getObject("SELECT * FROM " + BEE_WYPOWIEDZI + " WHERE " + WYPOWIEDZ_ID +"=" + ID);
        if (wypowiedz == null) return null;
        return new Wypowiedz((String)wypowiedz.get(WYPOWIEDZ_ID),(String)wypowiedz.get(WYPOWIEDZ_ID_AUTORA),(String)wypowiedz.get(WYPOWIEDZ_AUTOR),(String)wypowiedz.get(WYPOWIEDZ_DATA),(String)wypowiedz.get(WYPOWIEDZ_TEKST),(String)wypowiedz.get(WYPOWIEDZ_PRYWATNA),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Podforum o podanym identyfikatorze
     * @param ID Identyfikator szukanego podforum
     * @return Zwraca obiekt Podforum badz null w razie bledu.
     */
    public Podforum getPodforum(int ID){
        Hashtable podforum = getObject("SELECT * FROM " + BEE_PODFORA + " WHERE " + PODFORUM_ID +"=" + ID);
        if (podforum == null) return null;
        return new Podforum((String)podforum.get(PODFORUM_ID),(String)podforum.get(PODFORUM_TYTUL),(String)podforum.get(PODFORUM_OPIS),(String)podforum.get(PODFORUM_AKTYWNE),(String)podforum.get(PODFORUM_PRYWATNE),this);
    }
    
    
    /**
     * Metoda zwaraca objekt Podforum w ktorym znajduje sie Watek o podanym identyfikatorze
     * @param ID Identyfikator watku w szukanym podforum
     * @return Zwraca obiekt Podforum badz null w razie bledu.
     */
    public Podforum getPodforumbyWatek(int ID){
        Hashtable podforum = getObject("SELECT * FROM " + BEE_PODFORA + " WHERE "+ PODFORUM_ID +"= (SELECT " + PODFORA_WATKI_ID_PODFORUM + " FROM " + BEE_PODFORA_WATKI + " WHERE " + PODFORA_WATKI_ID_WATKU + "=" + ID + ")");
        if (podforum == null) return null;
        return new Podforum((String)podforum.get(PODFORUM_ID),(String)podforum.get(PODFORUM_TYTUL),(String)podforum.get(PODFORUM_OPIS),(String)podforum.get(PODFORUM_AKTYWNE),(String)podforum.get(PODFORUM_PRYWATNE),this);
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
     * Metoda zwaraca objekt User o podanym identyfikatorze
     * @param ID Identyfikator szukanego uzytkownika
     * @return Zwraca obiekt User badz null w razie bledu.
     */
    public User getUser(int ID){
        Hashtable user = getObject("SELECT * FROM " + BEE_USERS + " WHERE "+ USER_ID +"=" + ID);
        if (user == null) return null;
        return UserFactory.getUser((String)user.get(USER_ID),(String)user.get(USER_LOGIN),(String)user.get(USER_HASLO),(String)user.get(USER_IMIE),(String)user.get(USER_NAZWISKO),(String)user.get(USER_EMAIL),(String)user.get(USER_GG),(String)user.get(USER_JABBER),(String)user.get(USER_LASTLOG),(String)user.get(USER_AKTYWNY),(String)user.get(USER_ADMIN),(String)user.get(USER_MODERATOR));
    }
    
    
    /**
     * Metoda zwaraca objekt User o podanym identyfikatorze
     * @param login Identyfikator (login) szukanego uzytkownika
     * @return Zwraca obiekt User badz null w razie bledu.
     */
    public User getUser(String login) {
        Hashtable user = getObject("SELECT * FROM " + BEE_USERS + " WHERE "+ USER_LOGIN +" = '" + login + "'");
        if (user == null) return null;
        return UserFactory.getUser((String)user.get(USER_ID),(String)user.get(USER_LOGIN),(String)user.get(USER_HASLO),(String)user.get(USER_IMIE),(String)user.get(USER_NAZWISKO),(String)user.get(USER_EMAIL),(String)user.get(USER_GG),(String)user.get(USER_JABBER),(String)user.get(USER_LASTLOG),(String)user.get(USER_AKTYWNY),(String)user.get(USER_ADMIN),(String)user.get(USER_MODERATOR));
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
        return baza.dmlQuery("INSERT INTO " + BEE_USERS + " VALUES (0,\"" + u.getLogin() + "\",\"" + u.getHaslo() + "\" ,'"+  u.getImie() +"' ,'" + u.getNazwisko() + "' ,'" + u.getEmail() + "' ,'" + u.getGG() + "' ,'" + u.getJabber() + "','" + u.getLastLog() + "','" + aktywny + "','" + NIE + "','" + NIE + "')");
    }
    
    
    /** Ustawia uzytkownika jako aktywnego
     * @param nick login uzytkownika
     * @return T lub N w zaleznosci czy update sie powiodl
     */
    public boolean setAktywnyUser(String nick){
        return baza.dmlQuery("UPDATE " + BEE_USERS + " SET " + USER_AKTYWNY + "='" + TAK + "' WHERE " + USER_LOGIN + "='" + nick + "'");
    }
    
    
    /**
     * Metoda zwaraca liste wszystkich obiektow tabeli Users z bazy
     * @return ArrayList obiektow RegisteredUsers
     */
    public ArrayList getUsers() {
        ArrayList wynik = new ArrayList();
        ArrayList users= baza.query("SELECT * FROM "+ BEE_USERS);
        for(int i=0; i<users.size(); i++) {
             Hashtable user = (Hashtable)users.get(i);
             wynik.add(new User(Integer.parseInt((String) user.get(USER_ID)), (String) user.get(USER_LOGIN), (String) user.get(USER_HASLO), (String) user.get(USER_IMIE), (String) user.get(USER_NAZWISKO), (String) user.get(USER_EMAIL), (String) user.get(USER_GG), (String) user.get(USER_JABBER), (String) user.get(USER_LASTLOG), (String) user.get(USER_AKTYWNY) , (String) user.get(USER_ADMIN), (String) user.get(USER_MODERATOR)));
        }
        return wynik;
    }
    
    
    /**
     * Metoda ustawia pola uprawnien Usera, jezeli moderator jest false, to
     * usuwa wszystkie wiersze z taveli bee_moderatorzy z podanym id 
     * @param id int id uzytkownika w bazie danych
     * @param admin boolean T lub F
     * @param moderator boolena  T lub F
     * @param aktywny boolena T lub F
     */
    public boolean zmienUpr(int id, boolean czy_admin, boolean czy_moderator, boolean czy_aktywny){
        String admin,moderator,aktywny;
        if(czy_admin) admin=TAK; else admin=NIE;
        if(czy_aktywny) aktywny=TAK; else aktywny=NIE;
        if ( czy_moderator ) {
            baza.dmlQuery("DELETE FROM "+BEE_MODERATORZY+ " WHERE "+MODERATORZY_ID_USER+"="+id);
            moderator=TAK; } else moderator=NIE;
        return  baza.dmlQuery("UPDATE "+BEE_USERS+" SET "+USER_ADMIN+"='"+admin+"' , "+USER_MODERATOR+"='"+moderator+"' , "+USER_AKTYWNY+"='"+aktywny+"' WHERE "+USER_ID+"="+id);
    }
    
    
    /**
     * Metoda umieszcza kategorie w bazie danych
     * @param nazwa nazwa kategorii
     * @param opis kategorii
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertKategoria(String tytul, String opis, String nazwaforum) {
        if ( baza.dmlQuery("INSERT INTO " + BEE_KATEGORIE + " VALUES (0, '"+tytul+"' ,'"+opis+"', '" + TAK + "', '" + NIE + "')")) {
            Hashtable kat = getObject("SELECT * FROM " + BEE_KATEGORIE + " WHERE "+KATEGORIA_TYTUL+" = '"+tytul+"'");
            if (kat==null) return false;
            
            Hashtable forum = getObject("SELECT * FROM " + BEE_FORUM + " WHERE "+FORUM_NAZWA+" = '"+nazwaforum+"'");
            if (forum==null) return false;
            
            return baza.dmlQuery("INSERT INTO " + BEE_FORUM_KATEGORIE + " VALUES ("+forum.get("ID")+", "+kat.get("ID")+")");
        }
        return false;
    }
    
    
    /**
     * Metoda umieszcza podforum w bazie danych
     * @param id_kat id kategorii do ktorej dodawane jest podforum
     * @param tytul tytul podforum
     * @param opis podforum
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertPodforum(int id_kat, String tytul, String opis) {
        if ( baza.dmlQuery("INSERT INTO " + BEE_PODFORA + " VALUES (0, '"+tytul+"' ,'"+opis+"', '" + TAK + "', '" + NIE + "')")) {
            Hashtable pf = getObject("SELECT * FROM " + BEE_PODFORA + " WHERE "+PODFORUM_TYTUL+" = '"+tytul+"'");
            if (pf==null) return false;
            
            return baza.dmlQuery("INSERT INTO " + BEE_KATEGORIE_PODFORA + " VALUES ("+id_kat+", "+pf.get("ID")+")");
        }
        return false;
    }
    
    
    /**
     * Metoda umieszcza wypowiedź w bazie
     * @param id_wat id wątku, w którym dodajemy wypowiedź
     * @param w obiekt Wypowiedź (bez ważnego id) ktory należy wstawić do bazy
     * @return zwraca true jezeli insert sie powiodl
     */
    public boolean insertWypowiedz(String id_wat, Wypowiedz w) {
        String prywatna;
        if (w.czyPrywatna()) prywatna=DataBase.TAK; else prywatna=DataBase.NIE;
        if ( baza.dmlQuery("INSERT INTO " + BEE_WYPOWIEDZI + " VALUES (0, " + w.getIDAutora() + ", '" + w.getAutor() + "' , '" + w.getData() + "' , \"" + w.getTekst() + "\",'" + prywatna + "')")) {
            Hashtable wid = getObject("SELECT * FROM " + BEE_WYPOWIEDZI + " WHERE " + WYPOWIEDZ_ID_AUTORA + "=" + w.getIDAutora() + " AND " + WYPOWIEDZ_TEKST + "='" + w.getTekst() + "' AND " + WYPOWIEDZ_DATA + " = '" + w.getData() + "'");
            if (wid==null) return false;
            return baza.dmlQuery("INSERT INTO " + BEE_WATKI_WYPOWIEDZI + " VALUES (" + id_wat + "," + wid.get(WYPOWIEDZ_ID) + ")");
        }
        return false;
    }
    
    
    /**
     * Metoda umieszcza wątek w bazie
     * @param id_podforum id podforum w którym lezy wątek
     * @param w obiekt Watek (bez ważnego id) ktory należy wstawić do bazy
     * @return zwraca obiekt Watek jeżeli insert się powiódł, wpp zwraca null
     */
    public Watek insertWatek(String id_podforum, Watek w) {
        String prywatny;
        if (w.czyPrywatny()) prywatny=DataBase.TAK; else prywatny=DataBase.NIE;
        if(baza.dmlQuery("INSERT INTO " + BEE_WATKI + " VALUES (0, " + w.getIDAutora() + ", '" + w.getAutor() + "' , '" + w.getTemat() + "' , '" + w.getData() + "','" + prywatny + "')")) {
            Hashtable watek = getObject("SELECT * FROM " + BEE_WATKI + " WHERE " + WATEK_ID_AUTORA + "=" + w.getIDAutora() + " AND " + WATEK_DATA + " = '" + w.getData() + "' AND " + WATEK_TEMAT + " = '" + w.getTemat() + "'");
            if (watek==null) return null;
            if (!baza.dmlQuery("INSERT INTO " + BEE_PODFORA_WATKI + " VALUES (" + id_podforum + "," + watek.get(WATEK_ID) + ")")) return null;
            return new Watek((String)watek.get(WATEK_ID),(String)watek.get(WATEK_ID_AUTORA),(String)watek.get(WATEK_AUTOR),(String)watek.get(WATEK_TEMAT),prywatny,(String)watek.get(WATEK_DATA),this);
        }
        return null;
    }
    
    
    /**
     * Metoda sprawdz czy kategoria o podanym tytule juz istnieje
     * @param tytul tytul kategorii
     * @return zwraca true jezeli kategoria o podanym tytule juz istnieje
     */
    public boolean czyKategoria(String tytul){
        Hashtable kategoria = getObject("SELECT * FROM " + BEE_KATEGORIE + " WHERE "+KATEGORIA_TYTUL+" = '"+tytul+"'");
        if (kategoria==null) return false;
        return true;
    }
    
    
    /**
     * Metoda sprawdz czy podforum o podanym tytule juz istnieje
     * @param int id_kat id kategorii
     * @param String tytul tytuł podforum
     * @return zwraca true jezeli podforum o podanym tytule juz istnieje
     */
    public boolean czyPodforum(int id_kat, String tytul){
        Hashtable kategoria = getObject("SELECT * FROM " + BEE_PODFORA + " ,"+BEE_KATEGORIE_PODFORA +" WHERE "+PODFORUM_ID+"="+KATEGORIE_PODFORA_ID_PODFORUM+" and "+KATEGORIE_PODFORA_ID_KATEGORII+"="+id_kat+" and "+PODFORUM_TYTUL+" = '" +tytul+ "'");
        if (kategoria==null) return false;
        return true;
    }
    
    
    /**
     * Metoda zwraca id kategorii
     * @param tytul tytul kategorii
     * @return int numer kategorii
     */
    public int dajIdKategorii(String tytul){
        Hashtable kategoria = getObject("SELECT "+KATEGORIA_ID+" FROM " + BEE_KATEGORIE + " WHERE "+KATEGORIA_TYTUL+" = '" +tytul+ "'");
        if (kategoria==null) return 0;
        return  Integer.decode((String) kategoria.get(KATEGORIA_ID)).intValue();
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
     * Metoda zwaraca liste obiektow Podforum w podanej Kategorii, aktywne badż nie w
     * zalezności od parametru aktywne
     * @param aktywne String 'T' lub 'N'
     * @param ID Identyfikator kategorii w ramach ktorej interesuja nas podfora
     * @return ArrayList obiektow Podforum
     */
    public ArrayList  getPodforaKategoriiAll(int ID, String aktywne) {
        ArrayList wynik = new ArrayList();
        ArrayList podfora = baza.query("SELECT * FROM "+BEE_KATEGORIE_PODFORA+" ,"+BEE_PODFORA+" WHERE "+PODFORUM_ID+"="+KATEGORIE_PODFORA_ID_PODFORUM+" and "+KATEGORIE_PODFORA_ID_KATEGORII+"=" + ID + " and "+PODFORUM_AKTYWNE+"= '"+aktywne+"'");
        for(int i=0;i<podfora.size();i++) {
            Hashtable podforum = (Hashtable)podfora.get(i);
            wynik.add(new Podforum((String)podforum.get(PODFORUM_ID),(String)podforum.get(PODFORUM_TYTUL),(String)podforum.get(PODFORUM_OPIS),(String)podforum.get(PODFORUM_AKTYWNE),(String)podforum.get(PODFORUM_PRYWATNE),this));
        }
        return wynik;
    }
    
    
    /**
     * Metoda zwaraca liste obiektow Kategoria, które są aktywne.
     * @param aktywne String 'T' lub 'N'
     * @return ArrayList obiektow Kategoria
     */
    public ArrayList getKategorie(String aktywna) {
        ArrayList wynik = new ArrayList();
        ArrayList kategorie = baza.query("SELECT * FROM "+ BEE_KATEGORIE+" WHERE "+KATEGORIA_AKTYWNA+"='"+aktywna+"' ");
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
     * @param String aktywne 'T' lub 'N'
     * @param String id kategorii
     * @return boolean true jezeli update sie powiodl dalse wpp.
     **/
    public boolean zmienKategorie(String id, String aktywne){
        baza.dmlQuery("UPDATE "+BEE_PODFORA+" ,"+BEE_KATEGORIE_PODFORA+" SET "+PODFORUM_AKTYWNE+"='"+aktywne+"' WHERE "+PODFORUM_ID+"="+KATEGORIE_PODFORA_ID_PODFORUM+" and "+KATEGORIE_PODFORA_ID_KATEGORII+"=" + id);
        return  baza.dmlQuery("UPDATE "+BEE_KATEGORIE+" SET "+KATEGORIA_AKTYWNA+"='"+aktywne+"' WHERE "+KATEGORIA_ID+"="+id);
    }
    
    
    /**
     * Metoda zmienia pole aktywna na podane w parametrze aktywne
     * @param String aktywne 'T' lub 'N'
     * @param String id podforum
     * @return boolean true jezeli update sie powiodl dalse wpp.
     **/
    public boolean zmienPodforum(String id, String aktywne){
        return  baza.dmlQuery("UPDATE "+BEE_PODFORA+" SET "+PODFORUM_AKTYWNE+"='"+aktywne+"' WHERE "+PODFORUM_ID+"="+id);
    }
    
    
    /**
     * Metoda sprawdz czy kategoria o podanym tytult juz istnieje i czy ma inny id
     * @param tytul tytul kategorii
     * @param id kategorii
     * @return zwraca true jezeli kategoria o podanym tytule juz istnieje
     */
    public boolean czyKategoriaInna(String tytul, int id){
        Hashtable kategoria = getObject("SELECT * FROM " + BEE_KATEGORIE + " WHERE "+KATEGORIA_TYTUL+" = '" +tytul+ "' and "+KATEGORIA_ID+"<>"+id);
        if (kategoria==null) return false;
        return true;
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
        return "1970-01-01 00:00:00";
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
    
}

