/*
 * Wypowiedz.java
 *
 * Created on 9 marzec 2005, 17:44
 */

package pl.ltd.bee;

/**
 *
 * @author pawel
 */
public class Wypowiedz {
    
    private long ID;
    private long ID_Autora;
    private String Data;
    private String Tekst;
    
    /** Tworzy instancje klasy Wypowiedz
     * @param ID identyfikator autora
     * @param ID_Autora identyfikator autora w bazie
     * @param Data data wypowiedzi
     * @param Tekst tresc wypowiedzi
     */
    public Wypowiedz(String ID,String ID_Autora,String Data, String Tekst) {
        this.ID=Long.decode(ID).longValue();
        this.ID_Autora=Long.decode(ID_Autora).longValue();
        this.Data=Data;
        this.Tekst=Tekst;
    }
    
}
