/*
 * Watek.java
 *
 * Created on 9 marzec 2005, 12:21
 */

package pl.ltd.bee;


import java.util.ArrayList;
/**
 *
 * @author pawelb
 */
public class Watek {
    
    private int ID;
    private int ID_Autor;
    private String Autor;
    private String Temat;
    private String Data;
    private boolean Prywatny;
    private DataBase db;
    
    /** Creates a new instance of Watek
     *@param ID id watku
     *@param ID_Autor id autora watku
     *@param Temat temat watku
     *@param Data data watku
     *@param Aktywnyokresla czy watek jest aktywny
     *@param Prywatny okresla czy watek jest prywatny
     *@param db Obiekt DataBase
     */
    public Watek(String ID, String ID_Autor, String Autor, String Temat, String Data, String Prywatny , DataBase db)  {
        this.ID=Integer.decode(ID).intValue();
        this.ID_Autor=Integer.decode(ID_Autor).intValue();
        this.Autor=Autor;
        this.Temat=Temat;
        this.Data=Data;
        if (Prywatny != null) this.Prywatny=Prywatny.compareTo(DataBase.TAK) == 0;
        else this.Prywatny = false;
        this.db=db;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca String bedacy temetem watki
     */
    public String getTemat() {
        return Temat;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca nliczbe bedac� identyfikatorem watku w bazie
     */
    public int getID() {
        return ID;
    }
    
    /** Zwraca czy watek jest prywatny
     * @return true w przypadku gdy watek jest prywatny, wpp false
     */
    public boolean czyPrywatny() {
        return Prywatny;
    }
    
    /** to do usuniecia */
    public String printJSPHeader() {
        //outs.write("Temat: " + Temat);
        return "<a href=\"?wid="  + ID +" \">" + Temat + "</a> Utworzony dnia: " + Data + "<br>";
        
    }
    
    /**
     * Metoda wypisuje naglowek watku
     * @param strona strumien wyjsciowy
     */
    public void printJSPHeader(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("<tr>");
        strona.println("<td class=\"tdPictureWatek\" align=\"center\" valign=\"middle\" height=\"50\"><img src=\"./../images/koperta2.gif\" width=\"14\" height=\"11\"/></td>");
        strona.println("<td class=\"tdTytulWatek\" width=\"100%\" height=\"25\"><span class=\"tytulPOdforum\"> <a href=\"?wid="+ ID +"\" class=\"aTytulWatek\">"+ Temat +"</a></span>");
        strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\">17</span></td>");
        strona.println("<td class=\"tdAutor\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\">");
        if (Config.GUEST_ID.compareTo( String.valueOf(this.ID_Autor))==0)
            strona.println("~" + this.Autor);
        else
        {
            User u = db.getUser(this.ID_Autor);
            strona.println("<a href=\"profile.jsp?uid=" + this.ID_Autor + "\"> " + u.getLogin() + "</a>");
        }
        strona.println("</span></td>");
        strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\">109</span></td>");
        strona.println("<td class=\"tdLastPost\" align=\"center\" valign=\"middle\" height=\"25\" nowrap=\"nowrap\"> <span class=\"lastPost\">" + Data + "<br/><a href=\"profile.html\">User 1</a> <a href=\"viewtopic.html\"></a></span></td>");
        strona.println("</tr>");
    }
    
    
    /**
     * Metoda wypisuje na stronie glowną tabele i jej naglowki
     * @param strona strumien wyjsciowy
     */
    public void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        Podforum p = db.getPodforumbyWatek(ID);
        Kategoria k = null;
        if (p!=null) k = db.getKategoriabyPodforum(p.getID());
        Forum f = db.getForum();
        strona.println("<table border=\"0\" id=\"textNadWatkiem\" width=\"100%\" nowrap=\"nowrap\"><tr>");
        if (p!=null && k!=null && f!=null)
        strona.println("<td align=\"left\"><a href=\"./main.jsp\">"+ f.getNazwa() +"</a> -> <a href=\"./main.jsp?kid=" + k.getID() + "\">"+ k.getNazwa() +"</a> -> <a href=\"./main.jsp?pid=" + p.getID() + "\">"+ p.getTytul() +"</a> -> <a href=\"./main.jsp?wid=" + ID + "\">"+ Temat +"</a></td>");
        strona.println("<td align=\"right\"><a href=\"./dodajW.jsp?w=" + ID + "\">" + Messages.add() +" "+ Messages.message() + "</a></td>");
        strona.println("</tr></table>");
        strona.println("<table class=\"tableWatek\" id=\"tableWatek\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
        strona.println("<tr>");
        strona.println("<th class=\"thTopLCorner\" width=\"100%\" height=\"25\" nowrap=\"nowrap\">" +
                "<table class=\"tableTitleWatek\" border=\"0\" width=\"100%\"><tr><td align=\"center\"  width=\"20%\">&nbsp;" + Messages.author() + "&nbsp;</td><td align=\"center\">&nbsp;" + Messages.wielka(Messages.message()) + "&nbsp;</td></tr></table>"+
                "</th>");
        strona.println("</tr>");
    }
    
    
    /**
     * Metoda wypisuje na stronie zamkniecie glownej tabeli
     * @param strona strumien wyjsciowy
     */
    public void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
        strona.println("</body>");
        strona.println("</html>");
    }
    
    /**
     * Metoda powoduje wypisanie w�tku na przekazany strumien
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        printMainTableJSP(strona);
        ArrayList Wypowiedzi=db.getWypowiedziWatku(this.ID);
        for(int i=0;i<Wypowiedzi.size();i++) {
            strona.println("<tr class=\"trWypowiedz\">");
            strona.println("<td colspan=\"2\" class=\"tdWypowiedzBox\" align=\"center\" valign=\"middle\" nowrap=\"nowrap\"><iframe width=\"100%\" height=\"100%\" src=\"./main.jsp?wpid=" + ((Integer)Wypowiedzi.get(i)).intValue() + "\" scrolling=\"no\" frameborder=\"0\"></iframe></td>");
            strona.println("</tr>");
        }
        printMainTableCloseJSP(strona);
    }
    
    
}
