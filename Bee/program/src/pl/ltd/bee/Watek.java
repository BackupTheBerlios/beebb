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
    private boolean zmienDateOstWypowiedzi;
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
     * @param DataOstWypowiedzi data ostatniej wypowiedziw wÄ…tku
     * @param AutorOstWypowiedzi autor ostatniej wypowiedziw wÄ…tku
     * @param Prywatny okresla czy watek jest prywatny
     * @param Aktywny okresla czy watek jest aktywny
     * @param Zablokowany okresla czy watek jest zablokowany
     * @param Zamkniety okresla czy watek jest zamkniÄ™ty
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
        this.zmienDateOstWypowiedzi=false;
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
     * @return zwraca liczbe bedacÄ… identyfikatorem watku w bazie
     */
    public int getID() {
        return ID;
    }
    
    
    /** Zwraca identyfikator autora wÄ…tku
     * @return zwraca liczbe bedacÄ… identyfikatorem autora wÄ…tku
     */
    public int getIDAutora() {
        return ID_Autor;
    }
    
    
    /** Zwraca tymczasowÄ… ksywkÄ™ autora wÄ…tku (tylko w przypadku goĹ›cia!!)
     * @return zwraca string bedacy tymczasowÄ… ksywkÄ… autora wÄ…tku
     */
    public String getAutor() {
        return Autor;
    }
    
    
    /** Zwraca date utworzenia wÄ…tku
     * @return zwraca string bedacy datÄ… utworzenia wÄ…tku
     */
    public String getData() {
        return Data;
    }
    
    
    /** Zwraca date ostatniej wypowiedzi w watku
     * @return zwraca string reprezentujÄ…cy date ostatniej wypowiedzi w watku
     */
    public String getDataOstWypowiedzi() {
        return DataOstWypowiedzi;
    }
    
    
    /** Ustawia autora ostatniej wypowiedzi w wÄ…tku
     * @param AutorOstWypowiedzi string reprezentujÄ…cy autora ostatniej wypowiedzi w wÄ…tku
     */
    public void getAutorOstWypowiedzi(String AutorOstWypowiedzi) {
        this.AutorOstWypowiedzi=AutorOstWypowiedzi;
    }
    
    
    /** Ustawia date ostatniej wypowiedzi w watku
     * @param DataOstWypowiedzi string reprezentujÄ…cy date ostatniej wypowiedzi w watku
     */
    public void setDataOstWypowiedzi(String DataOstWypowiedzi) {
        this.zmienDateOstWypowiedzi=true;
        this.DataOstWypowiedzi=DataOstWypowiedzi;
    }
    
    
    /** Sprawdza czy data ostatniej wypowiedzi sie zmieniĹ‚a
     * @return T w przypadku gdy data siÄ™ zmieniĹ‚a, wp F
     */
    public boolean checkDataOstWypowiedzi() {
        return zmienDateOstWypowiedzi;
    }
    
    
    /** Zwraca autora ostatniej wypowiedzi w wÄ…tku
     * @return zwraca string reprezentujÄ…cy autora ostatniej wypowiedzi w wÄ…tku
     */
    public String getAutorOstWypowiedzi() {
        return AutorOstWypowiedzi;
    }
    
    
    /** Ustawia autora ostatniej wypowiedzi w watku
     * @param AutorOstWypowiedzi string reprezentujÄ…cy autora ostatniej wypowiedzi w watku
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
    
    
    /** Zwieksza liczbe aktywnych wypowiedzi w wÄ…tku o 1 */
    public void zwiekszLiczbeAktywnychWypowiedzi() {
        this.LiczbaWypowiedzi++;
    }
    
    
    /** Podaje liczbe aktywnych wypowiedzi w wÄ…tku
     * @return liczba aktywnych wypowiedzi w wÄ…tku
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
    
    
    /** Zwieksza licznik odwiedzin i zapisuje do bazy jeĹĽeli potrzeba */
    public void incrLicznikOdwiedzin() {
        this.LicznikOdwiedzin++;
        if (System.currentTimeMillis() - LicznikOdwiedzinDateLastSave > (Config.CACHE_COUNTER*1000)) {
            LicznikOdwiedzinDateLastSave = System.currentTimeMillis();
            db.updateWatek(this);
        }
    }
    
    
    /**
     * Metoda wypisuje naglowek watku
     * @param request Otrzymane zapytanie HTTP
     * @param strona strumien wyjsciowy
     * @param auth Obiekt autoryzacji
     */
    public void printJSPHeader(javax.servlet.http.HttpServletRequest request, javax.servlet.jsp.JspWriter strona,Autoryzator auth) throws java.io.IOException {
        String css = Commons.getQueryStyle(request);
        strona.println("<tr>");
        if (this.Zablokowany) strona.println("<td class=\"tdPictureWatek\" align=\"center\" valign=\"middle\"><img src=\"./../images/lock.png\" alt=\"Zablokowany\"/></td>");
        else if (this.Zamkniety) strona.println("<td class=\"tdPictureWatek\" align=\"center\" valign=\"middle\"><img src=\"./../images/cut.gif\" alt=\"Zamkniety\" /></td>");
        else strona.println("<td class=\"tdPictureWatek\" align=\"center\" valign=\"middle\"><img src=\"./../images/koperta2.gif\" width=\"14\" height=\"11\" alt=\"Koperta\"/></td>");
        // tytul
        strona.println("<td class=\"tdTytulWatek\" width=\"100%\">");
        User usr = auth.getUser(request, db);
        
        if (usr!=null) {
            String dat1 =usr.getLastLog();
            if ( (Integer.decode(db.getYear(dat1)).intValue() < Integer.decode(db.getYear(DataOstWypowiedzi)).intValue()) ||
                    (Integer.decode(db.getYear(dat1)).intValue() <= Integer.decode(db.getYear(DataOstWypowiedzi)).intValue() && Integer.decode(db.getMonth(dat1)).intValue() < Integer.decode(db.getMonth(DataOstWypowiedzi)).intValue()) ||
                    (Integer.decode(db.getYear(dat1)).intValue() <= Integer.decode(db.getYear(DataOstWypowiedzi)).intValue() && Integer.decode(db.getMonth(dat1)).intValue() <= Integer.decode(db.getMonth(DataOstWypowiedzi)).intValue() && Integer.decode(db.getDay(dat1)).intValue() < Integer.decode(db.getDay(DataOstWypowiedzi)).intValue()) ||
                    (Integer.decode(db.getYear(dat1)).intValue() <= Integer.decode(db.getYear(DataOstWypowiedzi)).intValue() && Integer.decode(db.getMonth(dat1)).intValue() <= Integer.decode(db.getMonth(DataOstWypowiedzi)).intValue() && Integer.decode(db.getDay(dat1)).intValue() <= Integer.decode(db.getDay(DataOstWypowiedzi)).intValue() && Integer.decode(db.getHour(dat1)).intValue() < Integer.decode(db.getHour(DataOstWypowiedzi)).intValue()) ||
                    (Integer.decode(db.getYear(dat1)).intValue() <= Integer.decode(db.getYear(DataOstWypowiedzi)).intValue() && Integer.decode(db.getMonth(dat1)).intValue() <= Integer.decode(db.getMonth(DataOstWypowiedzi)).intValue() && Integer.decode(db.getDay(dat1)).intValue() <= Integer.decode(db.getDay(DataOstWypowiedzi)).intValue() && Integer.decode(db.getHour(dat1)).intValue() <= Integer.decode(db.getHour(DataOstWypowiedzi)).intValue() && Integer.decode(db.getMin(dat1)).intValue() < Integer.decode(db.getMin(DataOstWypowiedzi)).intValue()) ||
                    (Integer.decode(db.getYear(dat1)).intValue() <= Integer.decode(db.getYear(DataOstWypowiedzi)).intValue() && Integer.decode(db.getMonth(dat1)).intValue() <= Integer.decode(db.getMonth(DataOstWypowiedzi)).intValue() && Integer.decode(db.getDay(dat1)).intValue() <= Integer.decode(db.getDay(DataOstWypowiedzi)).intValue() && Integer.decode(db.getHour(dat1)).intValue() <= Integer.decode(db.getHour(DataOstWypowiedzi)).intValue() && Integer.decode(db.getMin(dat1)).intValue() <= Integer.decode(db.getMin(DataOstWypowiedzi)).intValue() && Integer.decode(db.getSec(dat1)).intValue() < Integer.decode(db.getSec(DataOstWypowiedzi)).intValue())
                    )
                strona.println("<b><span class=\"tytulPodforum\">"+ Commons.aHref(request,Temat,"./main.jsp?wid="+ ID,"aTytulWatek")+"</span></b>");
            else
                strona.println("<span class=\"tytulPodforum\">" + Commons.aHref(request,Temat,"./main.jsp?wid="+ ID,"aTytulWatek")+"</span>");
            } else strona.println("<span class=\"tytulPodforum\">"+ Commons.aHref(request,Temat,"./main.jsp?wid="+ ID,"aTytulWatek")+"</span>");
            strona.println("</td>");
            strona.println("<td class=\"tdModeratingWatek\"><span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) hrefClick('moderating.jsp?wid="+this.ID+"&amp;id_autor="+this.ID_Autor+"&amp;op=ban&amp;"+css+"')\"><img src=\"../images/kick_user.gif\" alt=\"" + Messages.banAuthor() + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintBanAuthor()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");
            strona.println("<span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) hrefClick('moderating.jsp?wid="+this.ID+"&amp;op=move&amp;"+css+"')\"><img src=\"../images/move.gif\" alt=\"" + Messages.wielka(Messages.move()) + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintMoveThread()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");
            strona.println("<span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) hrefClick('moderating.jsp?wid="+this.ID+"&amp;op=block&amp;"+css+"')\"><img src=\"../images/lock.png\" alt=\"" + Messages.wielka(Messages.block()) + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintBlockThread()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");
            strona.println("<span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) hrefClick('moderating.jsp?wid="+this.ID+"&amp;op=close&amp;"+css+"')\"><img src=\"../images/cut.gif\" alt=\"" + Messages.wielka(Messages.close()) + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintCloseThread()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");
            
            strona.println("</td><td class=\"tdLiczba\" align=\"center\" valign=\"middle\"><span class=\"liczba\">");
            if (LiczbaWypowiedzi > 0) strona.println((LiczbaWypowiedzi-1)); else strona.println(LiczbaWypowiedzi);
            strona.println("</span></td>");
            strona.println("<td class=\"tdAutor\" align=\"center\" valign=\"middle\"><span class=\"liczba\">");
            if (Config.GUEST_ID == this.ID_Autor)
                strona.println("~" + this.Autor);
            else {
                User u = db.getUser(this.ID_Autor);
                strona.println(Commons.aHref(request,u.getLogin(),"profile.jsp?uid=" + this.ID_Autor,"aAutor"));
            }
            strona.println("</span></td>");
            strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\"><span class=\"liczba\">" + this.LicznikOdwiedzin + "</span></td>");
            strona.println("<td class=\"tdLastPost\" align=\"center\" valign=\"middle\" nowrap=\"nowrap\"> <span class=\"lastPost\">" + DataOstWypowiedzi + "<br/>");
            User u = db.getUser(AutorOstWypowiedzi);
            if (u==null || AutorOstWypowiedzi.compareTo(Config.GUEST)==0) strona.println(AutorOstWypowiedzi); else
                strona.println(Commons.aHref(request,AutorOstWypowiedzi,"./profile.jsp?uid=" + u.getID(),"aAutor"));
            strona.println("<a href=\"viewtopic.html\"></a></span></td>");
            strona.println("</tr>");
        }
        
        
        /**
         * Metoda wypisuje na stronie glowną tabele i jej nagłówki
         * @param request Otrzymane zapytanie HTTP
         * @param strona strumień wyjsciowy
         */
        public void printMainTableJSP(javax.servlet.http.HttpServletRequest request, javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
            this.incrLicznikOdwiedzin();
            Podforum p = db.getPodforumbyWatek(ID);
            Kategoria k = null;
            if (p!=null) k = db.getKategoriabyPodforum(p.getID());
            Forum f = db.getForum();
            strona.println("<table border=\"0\" class=\"tableTextNadWatkiem\" id=\"textNadWatkiem\" width=\"100%\"><tr>");
            if (p!=null && k!=null && f!=null)
                strona.println("<td class=\"tdPath\" align=\"left\">"+Commons.aHref(request, f.getNazwa(), "./main.jsp","aPath")+" -> " + Commons.aHref(request, k.getNazwa(), "./main.jsp?kid=" + k.getID(),"aPath")+ " -> "+ Commons.aHref(request,p.getTytul(),"./main.jsp?pid=" + p.getID(),"aPath")+" -> "+ Commons.aHref(request,Temat,"./main.jsp?wid=" + ID,"aPath" )+"</td>");
            strona.println("<td class=\"tdTopAction\" align=\"right\">"+ Commons.aHref(request,Messages.wielka(Messages.add()) +" "+ Messages.message(),"./dodajW.jsp?w=" + ID,"aTopAction")+"</td>");
            strona.println("</tr></table>");
            strona.println("<table class=\"tableWatek\" id=\"tableWatek\" width=\"100%\" border=\"0\">");
            strona.println("<tr>");
            strona.println("<th class=\"thTopLCorner\" width=\"100%\" height=\"25\" nowrap=\"nowrap\">" +
                    "<table class=\"tableTitleWatek\" border=\"0\" width=\"100%\"><tr><td align=\"center\"  width=\"20%\">&nbsp;" + Messages.wielka(Messages.author()) + "&nbsp;</td><td align=\"center\">&nbsp;" + Messages.wielka(Messages.message()) + "&nbsp;</td></tr></table>"+
                    "</th>");
            strona.println("</tr>");
        }
        
        
        /**
         * Metoda wypisuje na stronie zamkniecie glownej tabeli
         * @param strona strumien wyjsciowy
         */
        public void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
            strona.println("</table>");
        }
        
        /**
         * Metoda powoduje wypisanie wďż˝tku na przekazany strumien
         * @param request Otrzymane zapytanie HTTP
         * @param strona strumien wyjsciowy
         */
        public void printJSP(javax.servlet.http.HttpServletRequest request, javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
            printMainTableJSP(request,strona);
            String css = Commons.getQueryStyle(request);
            ArrayList Wypowiedzi=db.getWypowiedziWatku(this.ID,true,true);
            for(int i=0;i<Wypowiedzi.size();i++) {
                strona.println("<tr class=\"trWypowiedz\">");
                strona.println("<td colspan=\"2\" class=\"tdWypowiedzBox\" align=\"center\" valign=\"middle\" nowrap=\"nowrap\"><iframe id=\"iframeWypowiedz"+i+"\" name=\"iframeWypowiedz"+i+"\" width=\"100%\" height=\"100%\" src=\"./main.jsp?wpid=" + ((Integer)Wypowiedzi.get(i)).intValue() +(css.length()>0?"&amp;"+css:"")+ "\" scrolling=\"no\" frameborder=\"0\"></iframe></td>");
                strona.println("</tr>");
            }
            printMainTableCloseJSP(strona);
        }
        
        
    }
