/*
 * Kategoria.java
 *
 * Created on 9 marzec 2005, 17:55
 */

package pl.ltd.bee;

/**
 *
 * @author pawel
 */
public class Kategoria {
    
    private long ID;
    private String Tytul;
    
    /** Tworzy nowa instancje klasy Kategoria
     * @param ID identyfikator kategorii w bazie danych
     * @param Tytul nazwa kategorii
     */
    public Kategoria(String ID, String Tytul) {
        this.ID=Long.decode(ID).longValue();
        this.Tytul=Tytul;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca long bedacy identyfikatorem kategorii w bazie
     */
    public long getID() {
        return ID;
    }
    
}
