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
    private String Opis;
    private ArrayList Watki;
    private DataBase db;
    
    /** Tworzy instancjê podforum
     * @param ID identyfikator podforum w bazie danych
     * @param Tytul tytul podforum
     * @param Opis opis podforum
     */
    public Podforum(String ID, String Tytul,String Opis,DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        this.Watki=db.getWatkiPodforum(this.ID);
        this.db=db;
        this.Opis = Opis;
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
    
    /**
     * Metoda zwraca opis podforum
     * @return String z opisem podforum
     */
    public String getOpis(){
        return Opis;
    }
    
    /**
     * Metoda wypisuje na strone glowna liste watkow
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        printMainTableJSP(strona);
        for(int i=0;i<Watki.size();i++) {
            Watek w = ((Watek)db.getWatek(((Integer)Watki.get(i)).intValue()));
            w.printJSPHeader(strona);
        }
        printMainTableCloseJSP(strona);
    }
    
    
    /**
     * Metoda wypisuje na strone glowna tabele z watkami
     * @param strona strumien wyjsciowy
     */
    private void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("<table width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
        strona.println("<tr>");
        strona.println("<th colspan=\"2\" class=\"thTopLCorner\" height=\"30\" nowrap=\"nowrap\">&nbsp;Tematy&nbsp;</th>");
        strona.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;Odpowiedzi&nbsp;</th>");
        strona.println("<th width=\"80\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;Autor&nbsp;</th>");
        strona.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;Wy¶wietleñ&nbsp;</th>");
        strona.println("<th class=\"thTopRCorner\" nowrap=\"nowrap\">&nbsp;Ostatni post&nbsp;</th>");
        strona.println("</tr>");
    }
    
    /**
     * Metoda wypisuje na stronie zamkniecie tabeli
     * @param strona strumien wyjsciowy
     */
    private void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
    }
}
