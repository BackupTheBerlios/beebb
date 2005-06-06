/*
 * Kategoria.java
 *
 * Created on 9 marzec 2005, 17:55
 */

package pl.ltd.bee;

import java.util.ArrayList;

/**
 * Klasa będąca odwzorowaniem obiektu Kategoria z bazy danych
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
    
    /** Tworzy instancje klasy Kategoria
     * @param ID identyfikator kategorii w bazie danych
     * @param Tytul nazwa kategorii
     * @param Opis opis kategorii
     * @param Aktywna określa czy kategoria jest aktywna
     * @param Prywatna określa czy kategoria jest prywatna
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
     * @return Zwraca long będący identyfikatorem kategorii w bazie
     */
    public int getID() {
        return ID;
    }
    
    
    /** Zwraca nazwę kategorii
     * @return String będący nazwą kategorii
     */
    public String getNazwa() {
        if (Tytul==null) return "";
        return Tytul;
    }
    
    
    /** Zwraca opis kategorii
     * @return Zwraca String będący opisem kategorii
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
     * @return True jeśli aktywna i false w p.p.
     */
    public boolean czyAktywna() {
        return Aktywna;
    }
    
    /** Zwraca  pole prywatna
     * @return True jeśli prywatna i false w p.p.
     */
    public boolean czyPrywatna() {
        return Prywatna;
    }
    
    /** Ustawia opis kategorii
     * @param op String ustawianego opisu
     */
    public void setOpis(String op) {
        this.Opis=op;
    }
    
    /** Ustawia opis kategorii
     * @param naz String ustawianej nazwy
     */
    public void setNazwa(String naz) {
        this.Tytul=naz;
    }
    
    /** Zwraca listę podfor
     * @param aktywne określa aktywność podfor
     * @return zwraca ArralList będący listą podfor
     */
    public ArrayList getPodfora(boolean aktywne) {
        return db.getPodforaKategorii(this.ID, aktywne);
    }
    
    
    /**
     * Metoda wypisuje na stronie główną tabele i jej nagłówki
     * @param request otrzymane zapytanie HTTP
     * @param strona strumień wyjściowy
     */
    public void printMainTableJSP(javax.servlet.http.HttpServletRequest request,javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        //TODO te naglowki moze trzeba bedziew wywalic gdzie indziej .. ale poki co to dobre dla nich miejsce
        Forum f = db.getForum();
        strona.println("<table border=\"0\" class=\"tableTextNadForum\" id=\"textNadForum\" width=\"100%\"><tr>");
        strona.println("<td class=\"tdPath\" align=\"left\">"+ Commons.aHref(request,f.getNazwa(), "main.jsp","aPath")+" -> "+Commons.aHref(request,Tytul, "main.jsp?kid=" + ID,"aPath"));
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
     * Metoda powoduje wypisanie forum na przekazany strumień
     * @param strona strumień wyjściowy
     * @param pytanie zapytanie otrzymane przez serwer
     * @param auth obiekt autoryzacji
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona, javax.servlet.http.HttpServletRequest pytanie, Autoryzator auth) throws java.io.IOException {
        boolean drukuj = false;
        strona.println("<tr>");
        strona.println("<td class=\"tdTytulKategorii\" colspan=\"5\" height=\"20\"><span class=\"tytulKategorii\">"+Commons.aHref(pytanie,Tytul,"main.jsp?kid="+ this.ID, "aTytulKategorii")+ "</span></td>");
        strona.println("</tr>");
        if (this.czyPrywatna()){
            User user = auth.getUser(pytanie,this.db);
            if (user != null)
                drukuj = user.hasReadKategoriaRight(this.getID());
        }
        else drukuj = true;
        if (drukuj)
        {
                ArrayList podfora = db.getPodforaKategorii(this.ID,true);
                for(int i=0;i<podfora.size();i++) {
                    ((Podforum)podfora.get(i)).printJSPHeader(strona,pytanie,auth);
                }
        } 
    }
    
    
    /**
     * Metoda wypisuje na stronie zamknięcie głównej tabeli
     * @param strona strumień wyjściowy
     */
    public void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
    }
    
}

