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
    public Watek(String ID, String ID_Autor, String Temat, String Data,DataBase db)  {
        this.ID=Integer.decode(ID).intValue();
        this.ID_Autor=Integer.decode(ID_Autor).intValue();
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
     * @return zwraca nliczbe bedac± identyfikatorem watku w bazie
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
