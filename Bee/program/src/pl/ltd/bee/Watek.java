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
    private String DataOstWypowiedzi;
    private String AutorOstWypowiedzi;
    private boolean Prywatny;
    private boolean Aktywny;
    private boolean Zablokowany;
    private boolean Zamkniety;
    private int LiczbaWypowiedzi;
    private int LicznikOdwiedzin;
    private long LicznikOdwiedzinDateLastSave;
    private DataBase db;
    
    
    /** Creates a new instance of Watek
     * @param ID id watku
     * @param ID_Autor id autora watku
     * @param Temat temat watku
     * @param Data data watku
     * @param DataOstWypowiedzi data ostatniej wypowiedziw wątku
     * @param AutorOstWypowiedzi autor ostatniej wypowiedziw wątku
     * @param Prywatny okresla czy watek jest prywatny
     * @param Aktywny okresla czy watek jest aktywny
     * @param Zablokowany okresla czy watek jest zablokowany
     * @param Zamkniety okresla czy watek jest zamknięty
     * @param LiczbaWypowiedzi liczba aktywnych wypowiedzi w watku
     * @param LicznikOdwiedzin okresla liczbe odwiedzin watku
     * @param db Obiekt DataBase
     */
    public Watek(String ID, String ID_Autor, String Autor, String Temat, String Data, String DataOstWypowiedzi, String AutorOstWypowiedzi, String Prywatny,String Aktywny,String Zablokowany,String Zamkniety, String LiczbaWypowiedzi, String LicznikOdwiedzin, DataBase db)  {
        this.ID=Integer.decode(ID).intValue();
        this.ID_Autor=Integer.decode(ID_Autor).intValue();
        this.Autor=Autor;
        this.Temat=Temat;
        this.Data=Data;
        this.DataOstWypowiedzi=DataOstWypowiedzi;
        this.AutorOstWypowiedzi=AutorOstWypowiedzi;
        if (Prywatny != null) this.Prywatny=Prywatny.compareTo(DataBase.TAK) == 0;
        else this.Prywatny = false;
        if (Aktywny != null) this.Aktywny=Aktywny.compareTo(DataBase.TAK) == 0;
        else this.Aktywny = false;
        if (Zablokowany != null) this.Zablokowany=Zablokowany.compareTo(DataBase.TAK) == 0;
        else this.Zablokowany = false;
        if (Zamkniety != null) this.Zamkniety=Zamkniety.compareTo(DataBase.TAK) == 0;
        else this.Zamkniety = false;
        this.LiczbaWypowiedzi=Integer.decode(LiczbaWypowiedzi).intValue();
        this.LicznikOdwiedzin=Integer.decode(LicznikOdwiedzin).intValue();
        this.LicznikOdwiedzinDateLastSave=0;
        this.db=db;
    }
    
    
    /** Zwraca identyfikator forum
     * @return zwraca String bedacy temetem watki
     */
    public String getTemat() {
        return Temat;
    }
    
    
    /** Zwraca identyfikator forum
     * @return zwraca liczbe bedacą identyfikatorem watku w bazie
     */
    public int getID() {
        return ID;
    }
    
    
    /** Zwraca identyfikator autora wątku
     * @return zwraca liczbe bedacą identyfikatorem autora wątku
     */
    public int getIDAutora() {
        return ID_Autor;
    }
    
    
    /** Zwraca tymczasową ksywkę autora wątku (tylko w przypadku gościa!!)
     * @return zwraca string bedacy tymczasową ksywką autora wątku
     */
    public String getAutor() {
        return Autor;
    }
    
    
    /** Zwraca date utworzenia wątku
     * @return zwraca string bedacy datą utworzenia wątku
     */
    public String getData() {
        return Data;
    }
    
    
    /** Zwraca date ostatniej wypowiedzi w watku
     * @return zwraca string reprezentujący date ostatniej wypowiedzi w watku
     */
    public String getDataOstWypowiedzi() {
        return DataOstWypowiedzi;
    }
    
    
    /** Ustawia autora ostatniej wypowiedzi w wątku
     * @param AutorOstWypowiedzi string reprezentujący autora ostatniej wypowiedzi w wątku
     */
    public void getAutorOstWypowiedzi(String AutorOstWypowiedzi) {
        this.AutorOstWypowiedzi=AutorOstWypowiedzi;
    }
    
    
    /** Ustawia date ostatniej wypowiedzi w watku
     * @param DataOstWypowiedzi string reprezentujący date ostatniej wypowiedzi w watku
     */
    public void setDataOstWypowiedzi(String DataOstWypowiedzi) {
        this.DataOstWypowiedzi=DataOstWypowiedzi;
    }
    
    
    /** Zwraca autora ostatniej wypowiedzi w wątku
     * @return zwraca string reprezentujący autora ostatniej wypowiedzi w wątku
     */
    public String getAutorOstWypowiedzi() {
        return AutorOstWypowiedzi;
    }

    
    /** Ustawia autora ostatniej wypowiedzi w watku
     * @param AutorOstWypowiedzi string reprezentujący autora ostatniej wypowiedzi w watku
     */
    public void setAutorOstWypowiedzi(String AutorOstWypowiedzi) {
        this.AutorOstWypowiedzi=AutorOstWypowiedzi;
    }
    
    
    /** Zwraca czy watek jest prywatny
     * @return true w przypadku gdy watek jest prywatny, wpp false
     */
    public boolean czyPrywatny() {
        return Prywatny;
    }
    
    
    /** Zwraca czy watek jest aktywny
     * @return true w przypadku gdy watek jest aktywny, wpp false
     */
    public boolean czyAktywny() {
        return Aktywny;
    }
    
    
    /** Zwraca czy watek jest Zablokowany
     * @return true w przypadku gdy watek jest Zablokowany, wpp false
     */
    public boolean czyZablokowany() {
        return Zablokowany;
    }
    
    
    /** Zwraca czy watek jest zamkniety
     * @return true w przypadku gdy watek jest zamkniety, wpp false
     */
    public boolean czyZamkniety() {
        return Zamkniety;
    }
    
    
    /** Zwieksza liczbe aktywnych wypowiedzi w wątku o 1 */
    public void zwiekszLiczbeAktywnychWypowiedzi() {
        this.LiczbaWypowiedzi++;
    }
    
    
    /** Podaje liczbe aktywnych wypowiedzi w wątku
     * @return liczba aktywnych wypowiedzi w wątku
     */
    public int liczbaAktywnychWypowiedzi() {
        return LiczbaWypowiedzi;
    }
    
    
    /** Podaje liczbe aktywnych wypowiedzi w wątku
     * @return liczba aktywnych wypowiedzi w wątku
     */
    public int licznikOdwiedzin() {
        return LicznikOdwiedzin;
    }
    
    
    /** Zwieksza licznik odwiedzin i zapisuje do bazy jeżeli potrzeba */
    public void incrLicznikOdwiedzin() {
        this.LicznikOdwiedzin++;
        if (System.currentTimeMillis() - LicznikOdwiedzinDateLastSave > (Config.CACHE_COUNTER*1000)) {
            LicznikOdwiedzinDateLastSave = System.currentTimeMillis();
            db.updateWatek(this);
        }
    }
    
    
    /**
     * Metoda wypisuje naglowek watku
     * @param strona strumien wyjsciowy
     */
    public void printJSPHeader(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("<tr>");
        strona.println("<td class=\"tdPictureWatek\" align=\"center\" valign=\"middle\" height=\"50\"><img src=\"./../images/koperta2.gif\" width=\"14\" height=\"11\"/></td>");
        strona.println("<td class=\"tdTytulWatek\" width=\"100%\" height=\"25\"><span class=\"tytulPOdforum\"> <a href=\"?wid="+ ID +"\" class=\"aTytulWatek\">"+ Temat +"</a></span>");
        strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\">");
        if (LiczbaWypowiedzi > 0) strona.println((LiczbaWypowiedzi-1)); else strona.println(LiczbaWypowiedzi);
        strona.println("</span></td>");
        strona.println("<td class=\"tdAutor\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\">");
        if (Config.GUEST_ID == this.ID_Autor)
            strona.println("~" + this.Autor);
        else {
            User u = db.getUser(this.ID_Autor);
            strona.println("<a href=\"profile.jsp?uid=" + this.ID_Autor + "\"> " + u.getLogin() + "</a>");
        }
        strona.println("</span></td>");
        strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"25\"><span class=\"liczba\">" + this.LicznikOdwiedzin + "</span></td>");
        strona.println("<td class=\"tdLastPost\" align=\"center\" valign=\"middle\" height=\"25\" nowrap=\"nowrap\"> <span class=\"lastPost\">" + DataOstWypowiedzi + "<br/>");
        User u = db.getUser(AutorOstWypowiedzi);
        if (u==null || AutorOstWypowiedzi.compareTo(Config.GUEST)==0) strona.println(AutorOstWypowiedzi); else
        strona.println("<a href=\"./profile.jsp?uid=" + u.getID() + "\">" + AutorOstWypowiedzi + "</a>");
        strona.println("<a href=\"viewtopic.html\"></a></span></td>");
        strona.println("</tr>");
    }
    
    
    /**
     * Metoda wypisuje na stronie glowną tabele i jej naglowki
     * @param strona strumien wyjsciowy
     */
    public void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        this.incrLicznikOdwiedzin();
        Podforum p = db.getPodforumbyWatek(ID);
        Kategoria k = null;
        if (p!=null) k = db.getKategoriabyPodforum(p.getID());
        Forum f = db.getForum();
        strona.println("<table border=\"0\" class=\"tableTextNadWatkiem\" id=\"textNadWatkiem\" width=\"100%\" nowrap=\"nowrap\"><tr>");
        if (p!=null && k!=null && f!=null)
            strona.println("<td class=\"tdPath\" align=\"left\"><a class=\"aPath\" href=\"./main.jsp\">"+ f.getNazwa() +"</a> -> <a class=\"aPath\" href=\"./main.jsp?kid=" + k.getID() + "\">"+ k.getNazwa() +"</a> -> <a class=\"aPath\" href=\"./main.jsp?pid=" + p.getID() + "\">"+ p.getTytul() +"</a> -> <a class=\"aPath\" href=\"./main.jsp?wid=" + ID + "\">"+ Temat +"</a></td>");
        strona.println("<td class=\"tdTopAction\" align=\"right\"><a class=\"aTopAction\" href=\"./dodajW.jsp?w=" + ID + "\">" + Messages.wielka(Messages.add()) +" "+ Messages.message() + "</a></td>");
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
