/*
 * Podforum.java
 *
 * Created on 9 marzec 2005, 17:51
 */

package pl.ltd.bee;

import java.util.ArrayList;

/**
 *
 * @author pawelb
 */
public class Podforum {
    
    private int ID;
    private String Tytul;
    private ArrayList Watki;
    private DataBase db;
    
    /** Tworzy instancjê podforum
     * @param ID identyfikator podforum w bazie danych
     * @param Tytul tytul podforum
     */
    public Podforum(String ID, String Tytul,DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        this.Watki=db.getWatkiPodforum(this.ID);
        this.db=db;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca long bedacy identyfikatorem podforum w bazie
     */
    public long getID() {
        return ID;
    }
    
    /** Metoda zwraca tytul podforum
     * @return String z tytulem podforum
     */
    public String getTytul(){
        return Tytul;
    }
    
    /** Wypisuje obiekt PodForum w postaci strony jsp
     *
     */
    public String printJSPHeader() {
        String s = "|<BR>|---- Podforum:   <a href=\"index.jsp?pid=" + ID + "\">" + Tytul + "</a><BR>";
        return s;
    }
    
    /** Wypisuje obiekt PodForum w postaci strony jsp
     *
     */
    public String printJSP() {
        String s = "Podforum:   <a href=\"index.jsp?pid=" + ID + "\">" + Tytul + "</a><BR>";
        for(int i=0;i<Watki.size();i++) {
            Watek w = ((Watek)db.getWatek(((Integer)Watki.get(i)).intValue()));
            s+= w.printJSPHeader();
        }
        return s;
    }
}
