/*
 * Wypowiedz.java
 *
 * Created on 9 marzec 2005, 17:44
 */

package pl.ltd.bee;

/**
 *
 * @author pawelb
 */
public class Wypowiedz {
    
    private int ID;
    private int ID_Autora;
    private String Autor;
    private String Data;
    private String Tekst;
    private boolean Prywatna;
    private DataBase db;
    
    /** Tworzy instancje klasy Wypowiedz
     * @param ID identyfikator autora
     * @param ID_Autora identyfikator autora w bazie
     * @param Data data wypowiedzi
     * @param Tekst tresc wypowiedzi
     * @param Aktywna okresla czy wypowiedz jest aktywna
     * @param Prywatna okresla czy wypowiedz jest prywatna
     * @param db Obiekt DataBase
     */
    public Wypowiedz(String ID,String ID_Autora,String Autor,String Data, String Tekst, String Prywatna, DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.ID_Autora=Integer.decode(ID_Autora).intValue();
        this.Autor=Autor;
        this.Data=Data;
        this.Tekst=Tekst;
        if (Prywatna != null) this.Prywatna = Prywatna.compareTo(DataBase.TAK) == 0;
            else this.Prywatna = false;
        this.db=db;
    }
    
    /**
     * Metoda wypisuje tresc wiadomosci
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        //strona.println("<body class=\"bodyWypowiedz\">");
        strona.println("<table border=\"0\" id=\"tableWypowiedz\" class=\"tableWypowiedz\" width=\"100%\" cellpadding=\"4\" cellspacing=\"1\">");
        strona.println("<tr>");
        strona.println("<td class=\"tdAutorBox\" id=\"tdAutorBox\" width=\"20%\" align=\"center\" valign=\"middle\">");
        if (Config.GUEST_ID.compareTo( String.valueOf(this.ID_Autora))==0)
                strona.println("<span class=\"linkAutor\">~" + this.Autor + "</span><br/>");
        else
        {
            User u = db.getUser(this.ID_Autora);
            strona.println("<span class=\"linkAutor\"><a href=\"\" onclick=\"wypowiedzLinkClick('./profile.jsp?uid=" + this.ID_Autora + "')\">" + u.getLogin() + "</a></span><br/>");
        }
        strona.println("<span class=\"wypowiedzData\">" + this.Data + "</span><br/>");
        strona.println("<img src=\"./../images/folder_big.gif\" width=\"46\" height=\"25\"/></td>");
        strona.println("<td class=\"tdWypowiedz\" id=\"wypowiedzTekst\">");
        strona.println(Tekst);
        strona.println("</td>");
        strona.println("</tr>");
        strona.println("</table>");
    }
    
    
    public String printJSP() {
        return "Wiadomosc:<BR> napisana dnia: " +Data + "<BR>" + Tekst + "<BR>";
    }
    
}
