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
    private DataBase db;
    
    /** Tworzy instancje klasy Wypowiedz
     * @param ID identyfikator autora
     * @param ID_Autora identyfikator autora w bazie
     * @param Data data wypowiedzi
     * @param Tekst tresc wypowiedzi
     */
    public Wypowiedz(String ID,String ID_Autora,String Autor,String Data, String Tekst,DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.ID_Autora=Integer.decode(ID_Autora).intValue();
        this.Autor=Autor;
        this.Data=Data;
        this.Tekst=Tekst;
        this.db=db;
    }
    
    /**
     * Metoda wypisuje tresc wiadomosci
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
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
        strona.println("<!--<SCRIPT type=\"text/javascript\" src=\"./skrypt.js\"/>-->");
        strona.println("</head>");
        strona.println("<body class=\"bodyWypowiedz\">");
        strona.println("<table border=\"0\">");
        strona.println("<tr>");
        strona.println("<td class=\"tdWypowiedz\">");
        strona.println(Tekst);
        strona.println("</td>");
        strona.println("</tr>");
        strona.println("</table>");
        strona.println("</body>");
        strona.println("</html>");
    }
    
    
    public String printJSP() {
        return "Wiadomosc:<BR> napisana dnia: " +Data + "<BR>" + Tekst + "<BR>";
    }
    
}
