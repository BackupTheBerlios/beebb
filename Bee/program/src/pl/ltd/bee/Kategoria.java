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

    /**
     * Metoda powoduje wypisanie forum na przekazany strumien
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException
    {
        strona.println("<tr>");
        strona.println("<td class=\"tdTytulKategorii\" colspan=\"5\" height=\"20\"><span class=\"tytulKategorii\"><a href=\"index.jsp?kid="+ this.ID +"\" class=\"aTytulKategorii\">" + Tytul + "</a></span></td>");
        strona.println("</tr>");
        ArrayList podfora = db.getPodforaKategorii(this.ID);
        for(int i=0;i<podfora.size();i++)
        {
            Podforum p = db.getPodforum(((Integer)podfora.get(i)).intValue());
            strona.println("<tr>");
            strona.println("<td class=\"tdPicturePodforum\" align=\"center\" valign=\"middle\" height=\"50\"><img src=\"./images/category2.gif\" width=\"24\" height=\"24\"/></td>");
            strona.println("<td class=\"tdTytulPodforum\" width=\"100%\" height=\"50\"><span class=\"tytulPOdforum\"> <a href=\"index.jsp?pid=" + p.getID() + "\" class=\"aTytulPodforum\">"+ p.getTytul() +"</a><br/></span><span class=\"opisPodforum\">Opis co zawiera<br/>");
            strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"50\"><span class=\"liczba\">17</span></td>");
            strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"50\"><span class=\"liczba\">109</span></td>");
            strona.println("<td class=\"tdLastPost\" align=\"center\" valign=\"middle\" height=\"50\" nowrap=\"nowrap\"> <span class=\"lastPost\">Czw Mar 17, 2005 3:29 am<br /><a href=\"profile.html\">User 1</a> <a href=\"viewtopic.html\"></a></span></td>");
            strona.println("</tr>");
        }

    }
    
}
