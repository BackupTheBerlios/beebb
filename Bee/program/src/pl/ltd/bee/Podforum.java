/*
 * Podforum.java
 *
 * Created on 9 marzec 2005, 17:51
 */

package pl.ltd.bee;

import java.util.ArrayList;

/**
 *
 * @author pawelb
 */
public class Podforum {
    
    private int ID;
    private String Tytul;
    private String Opis;
    private String AutorOstWypowiedzi;
    private String DataOstWypowiedzi;
    private boolean zmienDateOstWypowiedzi;
    private boolean Aktywne;
    private boolean Prywatne;
    private DataBase db;
    private int LiczbaWatkow;
    private int LiczbaWypowiedzi;
    private int id_kat;
    
    
    /** Tworzy instancje podforum
     * @param ID identyfikator podforum w bazie danych
     * @param Tytul tytul podforum
     * @param Opis opis podforum
     * @param Aktywne okresla czy podforum jest aktywne
     * @param Prywatne okresla czy podforum jest prywatne
     * @param db Obiekt DataBase
     */
    public Podforum(String ID, String Tytul,String Opis,String DataOstWypowiedzi,String AutorOstWypowiedzi, String Aktywne, String Prywatne,String LiczbaWatkow,String LiczbaWypowiedzi, DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        this.DataOstWypowiedzi=DataOstWypowiedzi;
        this.zmienDateOstWypowiedzi=false;
        this.AutorOstWypowiedzi=AutorOstWypowiedzi;
        if (Aktywne != null) this.Aktywne=Aktywne.compareTo(DataBase.TAK) == 0;
        else this.Aktywne = false;
        if (Prywatne != null) this.Prywatne=Prywatne.compareTo(DataBase.TAK) == 0;
        else this.Prywatne = false;
        this.LiczbaWatkow=Integer.decode(LiczbaWatkow).intValue();
        this.LiczbaWypowiedzi=Integer.decode(LiczbaWypowiedzi).intValue();
        this.db=db;
        this.Opis = Opis;
    }

    
    /** Zwraca identyfikator forum
     * @return zwraca integer będący identyfikatorem podforum w bazie
     */
    public int getID() {
        return ID;
    }
    
    
    /** Zwraca identyfikator kat
     * @return zwraca integer będący identyfikatorem podforum w bazie
     */
    public int getIdKat() {
        return id_kat;
    }
    
    
    /** Metoda zwraca tytuł podforum
     * @return String z tytulem podforum
     */
    public String getTytul(){
        if(Tytul==null) return "";
        return Tytul;
    }
    
    
    /** Metoda zwraca opis podforum
     * @return String z opisem podforum
     */
    public String getOpis(){
        if(Opis==null) return "";
        return Opis;
    }
    
    
    /** Metoda zwraca autora ostatniej wypowiedzi
     * @return String z autorem ostatniej wypowiedzi
     */
    public String getAutorOstWypowiedzi(){
        return AutorOstWypowiedzi;
    }
    
    
    /** Metoda zwraca datę ostatniej wypowiedzi
     * @return String z datą ostatniej wypowiedzi
     */
    public String getDataOstWypowiedzi(){
        return DataOstWypowiedzi;
    }
    
    
    /** Zwraca czy podforum jest aktywne
     * @return T jeżeli podforum jest aktywne
     */
    public boolean getAktywne() {
        return Aktywne;
    }
    
    
    /** Ustawia opis podforum
     * @param op String reprezentujący ustawiany opis
     */
    public void setOpis(String op) {
        this.Opis=op;
    }
    
    
    /** Ustawia autora ostatniej wypowiedzi
     * @param AutorOstWypowiedzi String opisujący autora ostatniej wypowiedzi
     */
    public void setAutorOstWypowiedzi(String AutorOstWypowiedzi) {
        this.AutorOstWypowiedzi=AutorOstWypowiedzi;
    }
    
    
    /** Ustawia datę ostatniej wypowiedzi
     * @param DataOstWypowiedzi String reprezentujący datę ostatniej wypowiedzi
     */
    public void setDataOstWypowiedzi(String DataOstWypowiedzi) {
        this.zmienDateOstWypowiedzi=true;
        this.DataOstWypowiedzi=DataOstWypowiedzi;
    }
    
    
    /** Sprawdza czy data ostatniej wypowiedzi sie zmieniła 
     * @return T w przypadku gdy data się zmieniła, wpp F
     */
    public boolean checkDataOstWypowiedzi() {
        return zmienDateOstWypowiedzi;
    }
    
    
    /** Ustawia nazwe podforum
     * @param naz String ustawianej nazwy
     */
    public void setNazwa(String naz) {
        this.Tytul=naz;
    }
    
    
    /** Ustawia id podforum
     * @param id String ustawianego opisu
     */
    public void setID(String id) {
        this.ID=Integer.decode(id).intValue();
    }
    
    
    /** Ustawia identyfikator kategorii w której znajduje się forum
     * @param id String ustawianego id_kat
     */
    public void setIdKat(String id) {
        this.id_kat=Integer.decode(id).intValue();
    }
    
    
    /** Zwraca czy podforum jest prywatne
     * @return true w przypadku gdy podforum jest prywatne, wpp false
     */
    public boolean czyPrywatne() {
        return Prywatne;
    }
    
    
    /** Zwraca czy podforum jest aktywne
     * @return true w przypadku gdy podforum jest aktywne, wpp false
     */
    public boolean czyAktywne() {
        return Aktywne;
    }
    
    
    /** Podaje liczbe aktywnych wątków w danym podforum
     * @return liczba aktywnych wątków w danym podforum
     */
    public int liczbaAktywnychWatkow() {
        return this.LiczbaWatkow;
    }
    
    
    /** Podaje liczbe aktywnych wypowiedzi w danym podforum
     * @return liczba aktywnych wątków w danych podforum
     */
    public int liczbaAktywnychWypowiedzi() {
        return this.LiczbaWypowiedzi;
    }
    
    /** Zwieksza liczbe aktywnych wypowiedzi w podforum o 1
     */
    public void zwiekszLiczbeAktywnychWypowiedzi() {
        this.LiczbaWypowiedzi++;
    }
    
    /** Zwieksza liczbe aktywnych watkow w podforum o 1
     */
    public void zwiekszLiczbeAktywnychWatkow() {
        this.LiczbaWatkow++;
    }
    
    
    /**
     * Metoda wypisuje na strone główną listę wątkow
     * @param strona strumien wyjsciowy
     * @param pytanie Zaptanie otrzymane przez serwer
     * @param auth Obiekt autoryzacji
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona, javax.servlet.http.HttpServletRequest pytanie, Autoryzator auth) throws java.io.IOException {
        boolean drukuj = false;
        if (this.czyPrywatne()){
            User user = auth.getUser(pytanie,this.db);
            if (user != null)
                drukuj = user.hasReadPodforumRight(this.getID());
        }else drukuj = true;
        
        if(drukuj){
            printMainTableJSP(pytanie,strona);
            ArrayList watki=db.getWatkiPodforum(this.ID,true,true);
            for(int i=0;i<watki.size();i++) {
                ((Watek)watki.get(i)).printJSPHeader(pytanie, strona,auth);
            }
        printMainTableCloseJSP(strona);
        }
    }
    
    
    /**
     * Metoda wypisuje wiersz w tabeli kategorii z opisem podforum
     * @param strona strumien wyjsciowy
     * @param pytanie Zapytanie otrzymane przez serwer
     * @param auth Obiekt autoryzacji
     */
    public void printJSPHeader(javax.servlet.jsp.JspWriter strona, javax.servlet.http.HttpServletRequest pytanie, Autoryzator auth) throws java.io.IOException {
        boolean drukuj = false;
        if (this.czyPrywatne()){
            User user = auth.getUser(pytanie,this.db);
            if (user != null)
                drukuj = user.hasReadPodforumRight(this.getID());
        }else drukuj = true;
        if (drukuj){
        strona.println("<tr>");
        strona.println("<td class=\"tdPicturePodforum\" align=\"center\" valign=\"middle\" height=\"50\"><img src=\"./../images/category2.gif\" width=\"24\" height=\"24\" alt=\"Category\"/></td>");
        strona.println("<td class=\"tdTytulPodforum\" width=\"100%\" height=\"50\"><span class=\"tytulPodforum\">"+Commons.aHref(pytanie, this.getTytul(),"main.jsp?pid=" + this.getID(),"aTytulPodforum")+"</span><br/><span class=\"opisPodforum\">" + this.getOpis() + "</span><br/></td>");
        strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"50\"><span class=\"liczba\">"+ liczbaAktywnychWatkow() + "</span></td>");
        strona.println("<td class=\"tdLiczba\" align=\"center\" valign=\"middle\" height=\"50\"><span class=\"liczba\">" + liczbaAktywnychWypowiedzi() + "</span></td>");
        strona.println("<td class=\"tdLastPost\" align=\"center\" valign=\"middle\" height=\"50\" nowrap=\"nowrap\">");
        if (AutorOstWypowiedzi.compareTo("")!=0) {
            strona.println("<span class=\"lastPost\">" + DataOstWypowiedzi + "<br />");
            User u = db.getUser(AutorOstWypowiedzi);
            if (u==null || AutorOstWypowiedzi.compareTo(Config.GUEST)==0) strona.println(AutorOstWypowiedzi); else
            strona.println(Commons.aHref(pytanie, AutorOstWypowiedzi,"./profile.jsp?uid=" + u.getID(),"aAutor"));
            strona.println("<a href=\"viewtopic.html\"></a></span>");
        } else strona.println("&nbsp;");
        strona.println("</td></tr>");
        }
    }
    
    
    /**
     * Metoda wypisuje na stronę główną tabelę z wątkami
     * @param request Otrzymane zapytanie HTTP
     * @param strona strumien wyjściowy
     */
    public void printMainTableJSP(javax.servlet.http.HttpServletRequest request, javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        Kategoria k = db.getKategoriabyPodforum(ID);
        Forum f = db.getForum();
        strona.println("<table border=\"0\" class=\"tableTextNadPodforum\" id=\"textNadPodforum\" width=\"100%\"><tr>");
        strona.println("<td class=\"tdPath\" align=\"left\">"+Commons.aHref(request, f.getNazwa(), "main.jsp","aPath")+ " -> " +Commons.aHref(request,k.getNazwa(),"main.jsp?kid=" + k.getID(),"aPath")+" -> "+Commons.aHref(request,Tytul,"main.jsp?pid=" + ID,"aPath")+"</td>");
        strona.println("<td class=\"tdTopAction\" align=\"right\">" + Commons.aHref(request, Messages.wielka(Messages.add())+ " " + Messages.thread(), "./dodajW.jsp?p=" + ID,"aTopAction")+"</td>");
        strona.println("</tr></table>");
        strona.println("<table id=\"tablePodforum\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
        strona.println("<tr>");
        strona.println("<th colspan=\"2\" class=\"thTopLCorner\" height=\"30\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.themes()) + "&nbsp;</th>");
        strona.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.actions()) + "&nbsp;</th>");
        strona.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.answers()) + "&nbsp;</th>");
        strona.println("<th width=\"80\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.author()) + "&nbsp;</th>");
        strona.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.showed()) + "&nbsp;</th>");
        strona.println("<th class=\"thTopRCorner\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.lastPost()) + "&nbsp;</th>");
        strona.println("</tr>");
    }
    
    
    /**
     * Metoda wypisuje na stronie zamkniecie tabeli
     * @param strona strumien wyjsciowy
     */
    public void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
    }
}
