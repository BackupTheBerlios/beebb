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
     * @param ID identyfikator forum w bazie
     * @param kategorie lista identyfikatorow (long) kategori nalezacych do forum
     */
    public Forum(String Nazwa, DataBase db) {
        this.Nazwa=Nazwa;
        this.Kategorie=db.getKategorieForum();
        this.db=db;
    }
    
    /** Wypisuje obiekt Forum w postaci strony jsp
     * 
     */
    public String printJSP() {
        String s = "<h2>Forum: " + Nazwa + "</h2><br>";
        for(int i=0;i<Kategorie.size();i++) {
            Kategoria k = ((Kategoria)db.getKategoria(((Integer)Kategorie.get(i)).intValue()));
            s+= k.printJSP();
        }
        return s;
    } 
    
    /**
     * Metoda powoduje wypisanie forum na przekazany strumien
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException
    {
        //teraz wypisanie kategorii
        ArrayList kategorie = db.getKategorieForum();
        for(int i=0;i<Kategorie.size();i++) {
            Kategoria k = ((Kategoria)db.getKategoria(((Integer)Kategorie.get(i)).intValue()));
            k.printJSP(strona);
        }
    }
    
     /**
      * Metoda wypisuje na stronie glown± tabele i jej naglowki
      * @param strona strumien wyjsciowy
      */
     public static void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException
     {
        //TODO te naglowki moze trzeba bedziew wywalic gdzie indziej .. ale poki co to dobre dla nich miejsce
        strona.println("<table width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
        strona.println("\t<tr>");
        strona.println("\t\t<th colspan=\"2\" class=\"thTopLCorner\" height=\"30\" nowrap=\"nowrap\">&nbsp;Forum&nbsp;</th>");
        strona.println("\t\t<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;Tematy&nbsp;</th>");
        strona.println("\t\t<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;Posty&nbsp;</th>");
        strona.println("\t\t<th class=\"thTopRCorner\" nowrap=\"nowrap\">&nbsp;Ostatni Post&nbsp;</th>");
        strona.println("\t</tr>");
    }
     
     /**
      * Metoda wypisuje na stronie zamkniecie glownej tabeli
      * @param strona strumien wyjsciowy
      */
     public static void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException
     {
         strona.println("</table>");
     }
}
