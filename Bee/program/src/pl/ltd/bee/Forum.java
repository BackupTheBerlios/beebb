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
    private String Opis;
    private DataBase db;
    
    /** Tworzy nowa instancje klasy Forum
     * @param ID identyfikator forum w bazie
     * @param kategorie lista identyfikatorow (long) kategori nalezacych do forum
     */
    public Forum(String Nazwa, String Opis, DataBase db) {
        this.Nazwa=Nazwa;
        this.Opis=Opis;
        this.db=db;
    }
    
    
    /** Zwraca nazwe forum
     * @return string bedacy nazwa forum
     */
    public String getNazwa() {
        if (Nazwa==null) return "";
        return Nazwa;
    }
    
    /** Zwraca opis forum
     * @return string bedacy opisem forum badz null jesli nie istnieje
     */
    public String getOpis() {
        return Opis;
    }
    
    /** Zwraca liste identyfikatorow kategorii
     * @return ArrayList 
     */
    public ArrayList getKategorie() {
        return db.getKategorie(true);
    }
    
    /**
     * Metoda powoduje wypisanie forum na przekazany strumien
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        //teraz wypisanie kategorii
        ArrayList kategorie = db.getKategorie(true);
        for(int i=0;i<kategorie.size();i++) {
            ((Kategoria)kategorie.get(i)).printJSP(strona);
        }
    }
    
    /**
     * Metoda wypisuje na stronie glowną tabele i jej naglowki
     * @param strona strumien wyjsciowy
     */
    public static void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        //TODO te naglowki moze trzeba bedziew wywalic gdzie indziej .. ale poki co to dobre dla nich miejsce
        strona.println("<table border=\"0\" class=\"tableTextNadForum\" id=\"textNadForum\" width=\"100%\"><tr><td>");
        strona.println("</td></tr></table>");
        strona.println("<table id=\"tableForum\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
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
    public static void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
    }
}
