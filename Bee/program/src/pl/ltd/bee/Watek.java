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
    private ArrayList Wypowiedzi;
    private DataBase db;
    
    /** Creates a new instance of Watek
     *@param ID id watku
     *@param ID_Autor id autora watku
     *@param Temat temat watku
     *@param Data data watku
     */
    public Watek(String ID, String ID_Autor, String Autor, String Temat, String Data,DataBase db)  {
        this.ID=Integer.decode(ID).intValue();
        this.ID_Autor=Integer.decode(ID_Autor).intValue();
        this.Autor=Autor;
        this.Temat=Temat;
        this.Data=Data;
        this.db=db;
        Wypowiedzi=db.getWypowiedziWatku(this.ID);
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
        strona.println("<td class=\"tdPictureWatek\" align=\"center\" valign=\"middle\" height=\"50\"><img src=\"./images/koperta2.gif\" width=\"14\" height=\"11\"/></td>");
        strona.println("<td class=\"tdTytulWatek\" width=\"100%\" height=\"25\"><span class=\"tytulPOdforum\"> <a href=\"?wid="+ ID +"\" class=\"aTytulWatek\">"+ Temat +"</a></span>");
        strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\">17</span></td>");
        strona.println("<td class=\"tdAutor\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\"><a href=\"profile.html\">User 1</a></span></td>");
        strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\">109</span></td>");
        strona.println("<td class=\"tdLastPost\" align=\"center\" valign=\"middle\" height=\"25\" nowrap=\"nowrap\"> <span class=\"lastPost\">" + Data + "<br/><a href=\"profile.html\">User 1</a> <a href=\"viewtopic.html\"></a></span></td>");
        strona.println("</tr>");
    }
    
    
    /**
     * Metoda wypisuje na stronie glowną tabele i jej naglowki
     * @param strona strumien wyjsciowy
     */
    public void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        strona.println("<html>");
        strona.println("<head>");
        strona.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        strona.println("<meta http-equiv=\"Content-Style-Type\" content=\"text/css\">");
        strona.println("<meta name=\"Copyright\" content=\"BeeBB Group &copy; 2005\" />");
        strona.println("<meta name=\"Author\" content=\"BeeBB Group\" />");
        strona.println("<meta name=\"description\" content=\"??\" />");
        strona.println("<meta name=\"keywords\" content=\"??\" />");
        
        strona.println("<title>BeeBB :: Index</title>");
        strona.println("<link rel=\"stylesheet\" href=\"./temat.css\" type=\"text/css\"/>");
        strona.println("<script type=\"text/javascript\" src=\"./iframe_resize.js\"></script>");
        strona.println("<script type=\"text/javascript\" src=\"./skrypt.js\"></script>");
        
        strona.println("<!--<FRAMESET ROWS=\"150,*\" BORDER=\"0\">");
        strona.println("<FRAME SRC=\"naglowek.html\" SCROLLING=\"no\" NAME=\"Naglowek\">");
        strona.println("<FRAME SRC=\"forum.html\" SCROLLING=\"auto\" NAME=\"Strona\">");
        strona.println("</FRAMESET>");
        strona.println("-->");
        strona.println("</head>");
        strona.println("<body onLoad=\"resizeMain();funOnResize(new Array(691,691,691,691,691,691,691,691,691,691,691))\" onResize=\"resizeMain();funOnResize(new Array(691,691,691,691,691,691,691,691,691,691,691))\">");
        Podforum p = db.getPodforumbyWatek(ID);
        Kategoria k = db.getKategoriabyPodforum(p.getID());
        Forum f = db.getForum();
        strona.println("<div align=\"left\">>> <a href=\"index.jsp\">"+ f.getNazwa() +"</a> > <a href=\"index.jsp?kid=" + k.getID() + "\">"+ k.getNazwa() +"</a> > <a href=\"index.jsp?pid=" + p.getID() + "\">"+ p.getTytul() +"</a> > <a href=\"index.jsp?wid=" + ID + "\">"+ Temat +"</a></div>");
        strona.println("<div align=\"right\"><a href=\"pages/dodajW.jsp?w=" + ID + "\">Dodaj Wypowiedź</a></div><br>");
        strona.println("<table id=\"tableWatek\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
        strona.println("<tr>");
        strona.println("<th class=\"thTopLCorner\" width=\"15%\" height=\"25\" nowrap=\"nowrap\">&nbsp;Autor&nbsp;</th>");
        strona.println("<th class=\"thTopRCorner\" nowrap=\"nowrap\">&nbsp;Wiadomo��&nbsp;</th>");
        strona.println("</tr>");
    }
    
    
    /**
     * Metoda wypisuje na stronie zamkniecie glownej tabeli
     * @param strona strumien wyjsciowy
     */
    public void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
        strona.println("<table width=\"0%\" cellpadding=\"2\" cellspacing=\"1\" border=\"1\" style=\"visibility:hidden\">");
        strona.println("<tr class=\"noExists\"><td width=\"0\" height=\"0\" id=\"przyklad\"><div id=\"napis\">A</div></td></tr>");
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
        for(int i=0;i<Wypowiedzi.size();i++) {
            strona.println("<tr class=\"trWypowiedz\">");
            strona.println("<td class=\"tdAutorBox\" align=\"center\" valign=\"middle\"><img src=\"./images/folder_big.gif\" width=\"46\" height=\"25\"/></td>");
            strona.println("<td class=\"tdWypowiedzBox\" align=\"center\" valign=\"middle\" nowrap=\"nowrap\"><iframe width=\"100%\" height=\"100%\" src=\"?wpid=" + ((Integer)Wypowiedzi.get(i)).intValue() + "\" scrolling=\"no\" frameborder=\"0\"></iframe></td>");
            strona.println("</tr>");
        }
        printMainTableCloseJSP(strona);
    }
    
    /** to do usuniecia */
    public String printJSP() {
        //outs.write("Temat: " + Temat);
        String s =  "<h2>" + Temat + "</h2>(Utworzony dnia: " + Data + ")<br><br><br>";
        for(int i=0;i<Wypowiedzi.size();i++) {
            Wypowiedz w = ((Wypowiedz)db.getWypowiedz(((Integer)Wypowiedzi.get(i)).intValue()));
            s+= w.printJSP();
        }
        return s;
    }
    
}
