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
    
    private String Wiad;
    
    /** Konstruktor bezargumentowy */
    public Podforum() {}
    
    /** Tworzy instancjê podforum bez watkow
     * @param ID identyfikator podforum w bazie danych
     * @param Tytul tytul podforum
     * @param Opis opis podforum
     */
    public Podforum(String ID, String Tytul, String Opis) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        this.Opis = Opis;
    }
    
    
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
        if(Tytul==null) return "";
        return Tytul;
    }
    

    /** Metoda zwraca opis podforum
     * @return String z opisem podforum
     */
    public String getOpis(){
        if(Opis==null) return "";
        return Opis;
    }
    
    /** Metoda zwraca wiad podforum
     * @return String z wiad podforum
     */
    public String getWiad(){
        if(Wiad==null) return "";
        return Wiad;
    }
    
    /** Ustawia opis podforum
     * @param op String ustawianego opisu
     */
    public void setOpis(String op) {
        this.Opis=op;
    }
    
    /** Ustawia nazwe podforum
     * @param op String ustawianej nazwy
     */
    public void setNazwa(String naz) {
        this.Tytul=naz;
    }
    
    /** Ustawia wiad kategorii
     * @param w String ustawianego opisu
     */
    public void setWiad(String w) {
        this.Wiad=w;
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
     * Metoda wypisuje wiersz w tabeli kategorii z opisem podforum
     * @param strona strumien wyjsciowy
     */
    public void printJSPHeader(javax.servlet.jsp.JspWriter strona) throws java.io.IOException 
    {
            strona.println("<tr>");
            strona.println("<td class=\"tdPicturePodforum\" align=\"center\" valign=\"middle\" height=\"50\"><img src=\"./images/category2.gif\" width=\"24\" height=\"24\"/></td>");
            strona.println("<td class=\"tdTytulPodforum\" width=\"100%\" height=\"50\"><span class=\"tytulPOdforum\"> <a href=\"index.jsp?pid=" + this.getID() + "\" class=\"aTytulPodforum\">"+ this.getTytul() +"</a><br/></span><span class=\"opisPodforum\">" + this.getOpis() + "<br/>");
            strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"50\"><span class=\"liczba\">17</span></td>");
            strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"50\"><span class=\"liczba\">109</span></td>");
            strona.println("<td class=\"tdLastPost\" align=\"center\" valign=\"middle\" height=\"50\" nowrap=\"nowrap\"> <span class=\"lastPost\">Czw Mar 17, 2005 3:29 am<br /><a href=\"profile.html\">User 1</a> <a href=\"viewtopic.html\"></a></span></td>");
            strona.println("</tr>");
    }
    
    /**
     * Metoda wypisuje na strone glowna tabele z watkami
     * @param strona strumien wyjsciowy
     */
    public void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("<table id=\"tablePodforum\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
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
    public void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
    }
}
