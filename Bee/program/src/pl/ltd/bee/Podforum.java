/*
 * Podforum.java
 *
 * Created on 9 marzec 2005, 17:51
 */

package pl.ltd.bee;

/**
 *
 * @author pawel
 */
public class Podforum {
    
    private long ID;
    private String Tytul;
    
    /** Tworzy instancjê podforum
     * @param ID identyfikator podforum w bazie danych
     * @param Tytul tytul podforum
     */
    public Podforum(String ID, String Tytul) {
        this.ID=Long.decode(ID).longValue();
        this.Tytul=Tytul;
    }
    
    public long getID() {
        return ID;
    }
    
}
