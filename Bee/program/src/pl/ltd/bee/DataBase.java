/*
 * DataBase.java
 *
 * Created on 7 marzec 2005, 10:16
 */

package pl.ltd.bee;

/**
 * Klasa wyższy interfejs do bazy danych. Dostarcza metody do uzyskania danych zawartych na forach.
 * @author wilk
 */
public class DataBase {
    
    static final String Bee_Users = "Bee_Users";

    static final String Bee_Watki = "Bee_Watki";
    static final String Bee_Wypowiedzi = "Bee_Wypowiedzi";
    static final String Bee_Podfora = "Bee_Podfora";
    static final String Bee_Kategorie = "Bee_Kategorie";
    static final String Bee_Forum = "Bee_Forum";

    static final String Bee_Watki_Wypowiedzi = "Bee_Watki_Wypowiedzi";
    static final String Bee_Podfora_Wypowiedzi = "Bee_Podfora_Wypowiedzi";
    static final String Bee_Kategorie_Podfora = "Bee_Kategorie_Podfora";
    static final String Bee_Moderatorzy = "Bee_Moderatorzy";
    
    static ConnectorDB baza = new ConnectorDB();
    
    /** Creates a new instance of DataBase */
    public DataBase() {
    }
    
    /**
     * Metoda zwaraca objekt Watek o podanym identyfikatorze
     * @param ID Identyfikator szukanego watku
     */
    public Watek getWatek(int ID){
        baza.query("SELECT * FROM " + Bee_Watki + " WHERE ID=" + ID);
    }
}
