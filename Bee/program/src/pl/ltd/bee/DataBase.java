/*
 * DataBase.java
 *
 * Created on 7 marzec 2005, 10:16
 */

package pl.ltd.bee;

import java.util.*;

/**
 * Klasa wyższy interfejs do bazy danych. Dostarcza metody do uzyskania danych zawartych na forach.
 * @author wilk
 */
public class DataBase {
    
    /**
     * Stala reprezentujaca nazwe tabeli w bazie danych
     */
    static final String BEE_USERS = "Bee_Users";

    static final String BEE_WATKI = "Bee_Watki";
    static final String BEE_WYPOWIEDZI = "Bee_Wypowiedzi";
    static final String BEE_PODFORA = "Bee_Podfora";
    static final String BEE_KATEGORIE = "Bee_Kategorie";
    static final String BEE_FORUM = "Bee_Forum";

    static final String BEE_WATKI_WYPOWIEDZI = "Bee_Watki_Wypowiedzi";
    static final String BEE_PODFORA_WATKI = "Bee_Podfora_Watki";
    static final String BEE_KATEGORIE_PODFORA = "Bee_Kategorie_Podfora";
    static final String BEE_MODERATORZY = "Bee_Moderatorzy";
    
    /**
     * Stala reprezentujaca nazwe pola w tabeli w bazie danych
     */
    static final String WATEK_ID = "ID";
    static final String WATEK_ID_AUTORA = "ID_Autora";
    static final String WATEK_TEMAT = "Temat";
    static final String WATEK_DATA = "Data";
    
    static final String WYPOWIEDZ_ID = "ID";
    static final String WYPOWIEDZ_ID_AUTORA = "ID_Autora";
    static final String WYPOWIEDZ_DATA = "Data";
    static final String WYPOWIEDZ_TEKST = "Tekst";

    static final String PODFORUM_ID = "ID";
    static final String PODFORUM_TYTUL = "Tytul";

    static final String KATEGORIA_ID = "ID";
    static final String KATEGORIA_TYTUL = "Tytul";
    
    static final String WATKI_WYPOWIEDZI_ID_KATEGORII = "ID_Kategoria";
    static final String WATKI_WYPOWIEDZI_ID_PODFORUM = "ID_Podforum";
    
    static final String PODFORA_WATKI_ID_PODFORUM = "ID_Podforum";
    static final String PODFORA_WATKI_ID_WATKU = "ID_Watku";
    
    static final String WATKI_WYPOWIEDZI_ID_WATKU = "ID_Watku";
    static final String WATKI_WYPOWIEDZI_ID_WYPOWIEDZI = "ID_Wypowiedzi";
    
    static final String USER_ID = "ID";
    static final String USER_LOGIN = "Login";
    static final String USER_HASLO = "Haslo";
    static final String USER_ADMIN = "Admin";
    static final String USER_MODERATOR = "Moderator";

    static ConnectorDB baza = new ConnectorDB();
    
    /** Creates a new instance of DataBase */
    public DataBase() {
    }
    
    /**
     * Metoda dostarcza pierwszy element (wiersz) z zadanego zapytania.
     */
    private Hashtable getObject(String query)
    {
        ArrayList lista = baza.query(query);
        if (lista.size() > 0)
            return (Hashtable)lista.get(0);
        else return null; //TODO: Co robic jesli nie ma zadanego objektu ?
    }
    
    /**
     * Metoda zwaraca objekt Watek o podanym identyfikatorze
     * @param ID Identyfikator szukanego watku
     */
    public Watek getWatek(int ID){
        Hashtable watek = getObject("SELECT * FROM " + BEE_WATKI + " WHERE "+ WATEK_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID, ID_autora, Temat i Date
        if (watek == null) return null;
        return new Watek(watek.get(WATEK_ID),watek.get(WATEK_ID_AUTORA),watek.get(WATEK_TEMAT),watek.get(WATEK_DATA));
    }

    /**
     * Metoda zwaraca objekt Wypowiedz o podanym identyfikatorze
     * @param ID Identyfikator szukanej wypowiedzi
     */
    public Wypowiedz getWypowiedz(int ID){
        Hashtable wypowiedz = getObject("SELECT * FROM " + BEE_WYPOWIEDZI + " WHERE " + WYPOWIEDZ_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID, ID_autora, Date i Tekst
        if (wypowiedz == null) return null;
        return new Wypowiedz(wypowiedz.get(WYPOWIEDZ_ID),wypowiedz.get(WYPOWIEDZ_ID_AUTORA),wypowiedz.get(WYPOWIEDZ_DATA),wypowiedz.get(WYPOWIEDZ_TEKST));
    }

    /**
     * Metoda zwaraca objekt Podforum o podanym identyfikatorze
     * @param ID Identyfikator szukanego podforum
     */
    public Podforum getPodforum(int ID){
        Hashtable podforum = getObject("SELECT * FROM " + BEE_PODFORA + " WHERE " + PODFORUM_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID i Tytul
        if (podforum == null) return null;
        return new Podforum(podforum.get(PODFORUM_ID),podforum.get(PODFORUM_TYTUL));
    }

    /**
     * Metoda zwaraca objekt Kategoria o podanym identyfikatorze
     * @param ID Identyfikator szukanej Kategorii
     */
    public Kategoria getKategorie(int ID){
        Hashtable kategoria = getObject("SELECT * FROM " + BEE_KATEGORIE + " WHERE " + KATEGORIA_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID i Tytul
        if (kategoria == null) return null;
        return new Kategoria(kategoria.get(KATEGORIA_ID),kategoria.get(KATEGORIA_TYTUL));
    }
    
    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami podfor w podanej Kategorii
     * @param kat Kategoria w ramach ktorej interesuja nas podfora
     * @return ArrayList obiektow Integer
     */
    public ArrayList getPodforaKategorii(Kategoria kat)
    {
        ArrayList wynik = new ArrayList();
        ArrayList podfora = baza.query("SELECT * FROM "+ BEE_WATKI_WYPOWIEDZI + "WHERE " + WATKI_WYPOWIEDZI_ID_KATEGORII + "=" + kat.getID());
        for(int i=0;i<podfora.size();i++)
            {
                Hashtable podforum = (Hashtable)podfora.get(i);
                Integer id = Integer.parseInt(podforum.get(WATKI_WYPOWIEDZI_ID_PODFORUM));
                wynik.add(new Integer(id));
            }   
        return wynik;
    }

    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami Watkow w podanym Podforum
     * @param pod Podforum w ramach ktorego interesuja nas watki
     * @return ArrayList obiektow Integer
     */
    public ArrayList getWatkiPodforum(Podforum pod)
    {
        ArrayList wynik = new ArrayList();
        ArrayList watki = baza.query("SELECT * FROM "+ BEE_PODFORA_WATKI + "WHERE " + PODFORA_WATKI_ID_PODFORUM + "=" + pod.getID());
        for(int i=0;i<watki.size();i++)
            {
                Hashtable watek = (Hashtable)watki.get(i);
                Integer id = Integer.parseInt(watek.get(PODFORA_WATKI_ID_WATKU));
                wynik.add(new Integer(id));
            }   
        return wynik;
    }

    /**
     * Metoda zwaraca liste obiektow Integer bedacych identyfikatorami Wypowiedzi w podanym Watku
     * @param wat Watek w ramach ktorego interesuja nas Wypowiedzi
     * @return ArrayList obiektow Integer
     */
    public ArrayList getWypowiedziWatku(Watek wat)
    {
        ArrayList wynik = new ArrayList();
        ArrayList wypowiedzi = baza.query("SELECT * FROM "+ BEE_WATKI_WYPOWIEDZI + "WHERE " + WATKI_WYPOWIEDZI_ID_WATKU + "=" + wat.getID());
        for(int i=0;i<wypowiedzi.size();i++)
            {
                Hashtable wypowiedz = (Hashtable)wypowiedzi.get(i);
                Integer id = Integer.parseInt(wypowiedz.get(WATKI_WYPOWIEDZI_ID_WYPOWIEDZI));
                wynik.add(new Integer(id));
            }   
        return wynik;
    }
    
    /**
     * Metoda zwaraca objekt User o podanym identyfikatorze
     * @param ID Identyfikator szukanego uzytkownika
     */
    public User getUser(int ID){
        Hashtable user = getObject("SELECT * FROM " + BEE_USERS + " WHERE "+ USER_ID +"=" + ID);
        //zakladam ze mam konstruktor ktory bierze ID, Login, Haslo, Admin, Moderator
        if (user == null) return null;
        return new User(user.get(USER_ID),user.get(USER_LOGIN),user.get(USER_HASLO),user.get(USER_ADMIN),user.get(USER_MODERATOR));
    }
    
}
