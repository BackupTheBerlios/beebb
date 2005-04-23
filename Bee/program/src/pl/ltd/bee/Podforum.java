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
    private boolean Aktywne;
    private boolean Prywatne;
    private DataBase db;
    private int id_kat;
    
    /** Konstruktor bezargumentowy */
    public Podforum() {}
    
    
    /** Tworzy instancje podforum
     * @param ID identyfikator podforum w bazie danych
     * @param Tytul tytul podforum
     * @param Opis opis podforum
     * @param Aktywne okresla czy podforum jest aktywne
     * @param Prywatne okresla czy podforum jest prywatne
     * @param db Obiekt DataBase
     */
    public Podforum(String ID, String Tytul,String Opis, String Aktywne, String Prywatne, DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        if (Aktywne != null) this.Aktywne=Aktywne.compareTo(DataBase.TAK) == 0;
            else this.Aktywne = false;
        if (Prywatne != null) this.Prywatne=Prywatne.compareTo(DataBase.TAK) == 0;
            else this.Prywatne = false;
        this.db=db;
        this.Opis = Opis;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca long bedacy identyfikatorem podforum w bazie
     */
    public int getID() {
        return ID;
    }
    
     /** Zwraca identyfikator kat
     * @return zwraca long bedacy identyfikatorem podforum w bazie
     */
    public int getIdKat() {
        return id_kat;
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
    
        
     /** Zwraca  pole aktywne
     * @return boolean
     */
    public boolean getAktywne() {
        return Aktywne;
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
       
    /** Ustawia id podforum
     * @param id String ustawianego opisu
     */
    public void setID(String id) {
        this.ID=Integer.decode(id).intValue();
    }
    
       
    /** Ustawia id kat
     * @param id String ustawianego id_kat
     */
    public void setIdKat(String id) {
        this.id_kat=Integer.decode(id).intValue();
    }
    
    
    /** Zwraca czy podforum jest prywatne
     * @return true w przypadku gdy podforum jest prywatne, wpp false
     */
    public boolean czyPrywatne() {
        return Prywatne;
    }

        
    /** Zwraca czy podforum jest aktywne
     * @return true w przypadku gdy podforum jest aktywne, wpp false
     */
    public boolean czyAktywne() {
        return Aktywne;
    }
    
    /**
     * Metoda wypisuje na strone glowna liste watkow
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        printMainTableJSP(strona);
        ArrayList Watki=db.getWatkiPodforum(this.ID);
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
            strona.println("<td class=\"tdPicturePodforum\" align=\"center\" valign=\"middle\" height=\"50\"><img src=\"./../images/category2.gif\" width=\"24\" height=\"24\"/></td>");
            strona.println("<td class=\"tdTytulPodforum\" width=\"100%\" height=\"50\"><span class=\"tytulPOdforum\"> <a href=\"main.jsp?pid=" + this.getID() + "\" class=\"aTytulPodforum\">"+ this.getTytul() +"</a><br/></span><span class=\"opisPodforum\">" + this.getOpis() + "<br/>");
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
        Kategoria k = db.getKategoriabyPodforum(ID);
        Forum f = db.getForum();
        strona.println("<table border=\"0\" class=\"tableTextNadPodforum\" id=\"textNadPodforum\" width=\"100%\"><tr>");
        strona.println("<td class=\"tdPath\" align=\"left\"><a class=\"aPath\" href=\"main.jsp\">"+ f.getNazwa() +"</a> -> <a class=\"aPath\" href=\"main.jsp?kid=" + k.getID() + "\">"+ k.getNazwa() +"</a> -> <a class=\"aPath\" href=\"main.jsp?pid=" + ID + "\">"+ Tytul +"</a></td>");
        strona.println("<td class=\"tdTopAction\" align=\"right\"><a class=\"aTopAction\" href=\"./dodajW.jsp?p=" + ID + "\">" + Messages.wielka(Messages.add())+ " " + Messages.thread() + "</a></td>");
        strona.println("</tr></table>");
        strona.println("<table id=\"tablePodforum\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
        strona.println("<tr>");
        strona.println("<th colspan=\"2\" class=\"thTopLCorner\" height=\"30\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.themes()) + "&nbsp;</th>");
        strona.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.answers()) + "&nbsp;</th>");
        strona.println("<th width=\"80\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.author()) + "&nbsp;</th>");
        strona.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.showed()) + "&nbsp;</th>");
        strona.println("<th class=\"thTopRCorner\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.lastPost()) + "&nbsp;</th>");
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
