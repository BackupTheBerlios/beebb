/*
 * Kategoria.java
 *
 * Created on 9 marzec 2005, 17:55
 */

package pl.ltd.bee;

import java.util.ArrayList;

/**
 *
 * @author pawelb
 */
public class Kategoria {
    
    private long ID;
    private String Tytul;
    private ArrayList Podfora;
    private DataBase db;
    
    /** Tworzy nowa instancje klasy Kategoria
     * @param ID identyfikator kategorii w bazie danych
     * @param Tytul nazwa kategorii
     */
    public Kategoria(String ID, String Tytul,ArrayList Podfora,DataBase db) {
        this.ID=Long.decode(ID).longValue();
        this.Tytul=Tytul;
        this.Podfora=Podfora;
        this.db=db;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca long bedacy identyfikatorem kategorii w bazie
     */
    public long getID() {
        return ID;
    }
    
    /** Wypisuje obiekt Kategoria w postaci strony jsp
     *
     */
    public String printJSP() {
        String s = "<HR><BR>Kategoria:   <a href=\"index.jsp?kid=" + ID + "\">" + Tytul + "</a><BR>";
        for(int i=0;i<Podfora.size();i++) {
            Podforum p = ((Podforum)db.getPodforum(((Integer)Podfora.get(i)).intValue()));
            s+= p.printJSP();
        }
        s+="<HR><BR>";
        return s;
    }
    
}
