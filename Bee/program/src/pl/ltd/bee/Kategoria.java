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
    
    private int ID;
    private String Tytul;
    private ArrayList Podfora;
    private DataBase db;
    
    /** Tworzy nowa instancje klasy Kategoria
     * @param ID identyfikator kategorii w bazie danych
     * @param Tytul nazwa kategorii
     */
    public Kategoria(String ID, String Tytul,DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        this.Podfora=db.getPodforaKategorii(this.ID);
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
            s+= p.printJSPHeader();
        }
        s+="<HR><BR>";
        return s;
    }
    
}
