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
    private boolean Aktywna;
    private DataBase db;
    
    /** Tworzy instancje klasy Wypowiedz
     * @param ID identyfikator autora
     * @param ID_Autora identyfikator autora w bazie
     * @param Data data wypowiedzi
     * @param Tekst tresc wypowiedzi
     * @param Prywatna okresla czy wypowiedz jest prywatna
     * @param Aktywna okresla czy wypowiedz jest aktywna
     * @param db Obiekt DataBase
     */
    public Wypowiedz(String ID,String ID_Autora,String Autor,String Data, String Tekst, String Prywatna, String Aktywna, DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.ID_Autora=Integer.decode(ID_Autora).intValue();
        this.Autor=Autor;
        this.Data=Data;
        this.Tekst=Tekst;
        if (Prywatna != null) this.Prywatna = Prywatna.compareTo(DataBase.TAK) == 0;
        else this.Prywatna = false;
        if (Aktywna != null) this.Aktywna = Aktywna.compareTo(DataBase.TAK) == 0;
        else this.Aktywna = false;
        this.db=db;
    }
    
    /** Zwraca identyfikator wypowiedzi
     * @return zwraca liczbe bedacÄ… identyfikatorem wypowiedzi w bazie
     */
    public int getID() {
        return ID;
    }
    
    
    /** Zwraca identyfikator autora wypowiedzi
     * @return zwraca liczbe bedacÄ… identyfikatorem autora wypowiedzi
     */
    public int getIDAutora() {
        return ID_Autora;
    }
    
    /** Zwraca tymczasowÄ… ksywkÄ™ autora wypowiedzi (tylko w przypadku goĹ›cia!!)
     * @return zwraca string bedacy tymczasowÄ… ksywkÄ… autora wypowiedzi
     */
    public String getAutor() {
        return Autor;
    }
    
    
    /** Zwraca date utworzenia wypowiedzi
     * @return zwraca string bedacy datÄ… utworzenia wypowiedzi
     */
    public String getData() {
        return Data;
    }
    
    
    /** Zwraca treĹ›Ä‡ wypowiedzi
     * @return zwraca string bÄ™dÄ…cy treĹ›ciÄ… wypowiedzi
     */
    public String getTekst() {
        return Tekst;
    }
    
    
    /** Zwraca czy wypowiedĹş jest prywatna
     * @return true w przypadku gdy wypowiedĹş jest prywatna, wpp false
     */
    public boolean czyPrywatna() {
        return Prywatna;
    }
    
    
    /** Zwraca czy wypowiedĹş jest Aktywna
     * @return true w przypadku gdy wypowiedĹş jest aktywna, wpp false
     */
    public boolean czyAktywna() {
        return Aktywna;
    }
    
    
    /**
     * Metoda wypisuje tresc wiadomosci
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        //strona.println("<body class=\"bodyWypowiedz\">");
        strona.println("<table border=\"0\" id=\"tableWypowiedz\" class=\"tableWypowiedz\" width=\"100%\" cellpadding=\"0\" cellspacing=\"1\">");
        strona.println("<tr><td><table cellpadding=\"4\" cellspacing=\"1\" width=\"100%\"><tr>");
        strona.println("<td class=\"tdAutorBox\" id=\"tdAutorBox\" width=\"20%\" align=\"center\" valign=\"middle\">");
        strona.println("<span ");
        if (Config.GUEST_ID == this.ID_Autora)
            strona.println(">~" + this.Autor);
        else {
            User u = db.getUser(this.ID_Autora);
            strona.println("class=\"linkAutor\"><span onclick=\"hrefClick('./profile.jsp?uid=" + this.ID_Autora + "')\"> " + u.getLogin() + "</span>");
        }
        strona.println("</span><br/>");
        strona.println("<span class=\"wypowiedzData\">" + this.Data + "</span><br/>");
        strona.println("<img src=\"./../images/folder_big.gif\" width=\"46\" height=\"25\" alt=\"Folder img\"/></td>");
        strona.println("<td class=\"tdWypowiedz\" id=\"wypowiedzTekst\">");
        strona.println(Tekst);
        strona.println("</td></tr></table></td></tr>");
        strona.println("<tr><td class=\"tdWypowiedzModer\">");
        strona.println("<table width=\"100%\" border=\"0\"><tr><td align=\"left\">");
        strona.println("<span style=\"cursor: pointer\" onclick=\"wypowiedzLinkClick('moderating.jsp?wpid="+this.ID+"&amp;op=edit')\"><img src=\"../images/edit.gif\" alt=\"" + Messages.wielka(Messages.edit()) + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintEditMessages()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>"); 
        strona.println("<span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) wypowiedzLinkClick('moderating.jsp?wpid="+this.ID+"&amp;op=delete')\"><img src=\"../images/delete.gif\" alt=\"" + Messages.wielka(Messages.delete()) + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintDeleteMessage()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");
        strona.println("</td><td align=\"right\">");
        strona.println("<span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) wypowiedzLinkClick('moderating.jsp?wpid="+this.ID+"&amp;id_autor="+this.ID_Autora+"&amp;op=ban')\"><img src=\"../images/kick_user.gif\" alt=\"" + Messages.banAuthor() + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintBanAuthor()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");//INFO tu nie wyciagam ID_KAT bo i po co ? Lepiej jednorazowo zrobic to na podstawie id wypowiedzi przy banie raz na 1000 ;)
        strona.println("<span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) wypowiedzLinkClick('moderating.jsp?wpid="+this.ID+"&amp;op=move')\"><img src=\"../images/move.gif\" alt=\"" + Messages.wielka(Messages.move()) + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintMoveThread()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");//INFO tu nie wyciagam ID_KAT bo i po co ? Lepiej jednorazowo zrobic to na podstawie id wypowiedzi przy banie raz na 1000 ;)
        strona.println("<span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) wypowiedzLinkClick('moderating.jsp?wpid="+this.ID+"&amp;op=block')\"><img src=\"../images/lock.png\" alt=\"" + Messages.wielka(Messages.block()) + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintBlockThread()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");
        strona.println("<span style=\"cursor: pointer\" onclick=\"if (czyNaPewno('"+ Messages.wielka(Messages.areYouSure())+"')) wypowiedzLinkClick('moderating.jsp?wpid="+this.ID+"&amp;op=close')\"><img src=\"../images/cut.gif\" alt=\"" + Messages.wielka(Messages.close()) + "\" onmouseover=\"showHint(isLogin('"+Autoryzator.COOKIE_USER_NAME+"')?'"+Messages.hintCloseThread()+"':'"+Messages.hintPleaseLogin()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\" border=\"0\"/></span>");//INFO tu nie wyciagam ID_KAT bo i po co ? Lepiej jednorazowo zrobic to na podstawie id wypowiedzi przy banie raz na 1000 ;)
        strona.println("</td></tr></table>");
        strona.println("</td></tr>");
        strona.println("</table>");
    }
    
    
    public String printJSP() {
        return "Wiadomosc:<BR> napisana dnia: " +Data + "<BR>" + Tekst + "<BR>";
    }
    
}
