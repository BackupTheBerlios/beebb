/*
 * Forum.java
 *
 * Created on 9 marzec 2005, 17:27
 */

package pl.ltd.bee;

import java.util.ArrayList;

/**
 *
 * @author pawelb
 */
public class Forum {
    
    private String Nazwa;
    private ArrayList Kategorie;
    private DataBase db;
    
    /** Tworzy nowa instancje klasy Forum 
     @param ID identyfikator forum w bazie
     @param kategorie lista identyfikatorow (long) kategori nalezacych do forum
     */
    public Forum(String Nazwa, ArrayList Kategorie,DataBase db) {
        this.Nazwa=Nazwa;
        this.Kategorie=Kategorie;
        this.db=db;
    }
    
    public String printJSP() {
        String s = "<h2>Forum: " + Nazwa + "</h2><br>";
        for(int i=0;i<Kategorie.size();i++) {
            Kategoria k = ((Kategoria)db.getKategoria(((Integer)Kategorie.get(i)).intValue()));
            s+= k.printJSP();
        }
       
        return s;
    } 
}
