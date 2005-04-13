/*
 * Kategoria.java
 *
 * Created on 9 marzec 2005, 17:55
 */

package pl.ltd.bee;

import java.util.ArrayList;

/**
 *
 * @author pawelb i sopi
 */
public class Kategoria {
    
    private int ID;
    private String Tytul;
    private String Opis;
    private boolean Aktywna;
    private boolean Prywatna;
    private DataBase db;
    
    
    /** Konstruktor bezargumentowy
     */
    public Kategoria() {
    }
    
    /** Tworzy instancje klasy Kategoria zawierajaca w Podfora
     * obiekty klasy Podforum
     * @param ID identyfikator kategorii w bazie danych
     * @param Tytul nazwa kategorii
     * @param Opis Opis kategorii
     * @param Aktywna okresla czy kategoria jest aktywna
     * @param Prywatna okresla czy kategoria jest prywatna
     * @param db obiekt klasy DataBase
     */
    
    public Kategoria(String ID, String Tytul, String Opis, String Aktywna, String Prywatna,DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        this.Opis=Opis;
        this.Aktywna=Aktywna.compareTo(DataBase.TAK) == 0;
        this.Prywatna=Prywatna.compareTo(DataBase.TAK) == 0;
        this.db=db;
    }
    
    
    /** Zwraca identyfikator forum
     * @return zwraca long bedacy identyfikatorem kategorii w bazie
     */
    public int getID() {
        return ID;
    }
    
    
    /** Zwraca nazwe kategori
     * @return string bedacy nazwa kategori
     */
    public String getNazwa() {
        if (Tytul==null) return "";
        return Tytul;
    }
    
    
    /** Zwraca opis kategorii
     * @return zwraca string bedacy opisem kategori
     */
    public String getOpis() {
        if (Opis==null) return "";
        return Opis;
    }
    
    /** Ustawia id kategorii
     * @param id String ustawianego id
     */
    public void setID(String id) {
        this.ID=Integer.decode(id).intValue();
    }
    
     /** Zwraca  pole aktywna
     * @return boolean
     */
    public boolean getAktywna() {
        return Aktywna;
    }
    
    /** Ustawia opis kategori
     * @param op String ustawianego opisu
     */
    public void setOpis(String op) {
        this.Opis=op;
    }
    
    /** Ustawia opis kategorii
     * @param op String ustawianej nazwy
     */
    public void setNazwa(String naz) {
        this.Tytul=naz;
    }
    
    /** Zwraca liste podforow
     * @param String 'T' lub 'N'  
     * @return zwraca ArralList bedacy lista podforow
     */
    public ArrayList getPodfora(String aktywne) {
        return db.getPodforaKategoriiAll(this.ID, aktywne);
    }
    
    
    /**
     * Metoda wypisuje na stronie glowną tabele i jej naglowki
     * @param strona strumien wyjsciowy
     */
    public void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        //TODO te naglowki moze trzeba bedziew wywalic gdzie indziej .. ale poki co to dobre dla nich miejsce
        Forum f = db.getForum();
        strona.println("<table border=\"0\" class=\"tableTextNadForum\" id=\"textNadForum\" width=\"100%\"><tr>");
        strona.println("<td class=\"tdPath\" align=\"left\"><a class=\"aPath\" href=\"main.jsp\">"+ f.getNazwa() +"</a> -> <a class=\"aPath\" href=\"main.jsp?kid=" + ID + "\">"+ Tytul +"</a>");
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
     * Metoda powoduje wypisanie forum na przekazany strumien
     * @param shtrona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("<tr>");
        strona.println("<td class=\"tdTytulKategorii\" colspan=\"5\" height=\"20\"><span class=\"tytulKategorii\"><a href=\"main.jsp?kid="+ this.ID +"\" class=\"aTytulKategorii\">" + Tytul + "</a></span></td>");
        strona.println("</tr>");
        ArrayList podfora = db.getPodforaKategorii(this.ID);
        for(int i=0;i<podfora.size();i++) {
            Podforum p = db.getPodforum(((Integer)podfora.get(i)).intValue());
            p.printJSPHeader(strona);
        }
    }
    
    
    /**
     * Metoda wypisuje na stronie zamkniecie glownej tabeli
     * @param strona strumien wyjsciowy
     */
    public void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
    }
    
}

