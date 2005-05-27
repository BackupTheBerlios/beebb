/*
 * Forum.java
 *
 * Created on 9 marzec 2005, 17:27
 */

package pl.ltd.bee;

import java.util.ArrayList;

/**
 * Klasa będąca odwzorowaniem obiektu Forum z bazy danych
 * @author pawelb
 */
public class Forum {
    
    private String Nazwa;
    private String Opis;
    private DataBase db;
    
    /** Tworzy nową instancję klasy Forum
     * @param Nazwa nazwa forum
     * @param Opis opis forum
     * @param db obiekt połączenia z bazą danych
     */
    public Forum(String Nazwa, String Opis, DataBase db) {
        this.Nazwa=Nazwa;
        this.Opis=Opis;
        this.db=db;
    }
    
    
    /** Zwraca nazwę forum
     * @return String będący nazwa forum
     */
    public String getNazwa() {
        if (Nazwa==null) return "";
        return Nazwa;
    }
    
    /** Zwraca opis forum
     * @return String będący opisem forum bądź null jeśli nie istnieje
     */
    public String getOpis() {
        return Opis;
    }
    
    /** Zwraca listę identyfikatorów kategorii
     * @return ArrayList z identyfikatorami kategori
     */
    public ArrayList getKategorie() {
        return db.getKategorie(true);
    }
    
    /**
     * Metoda powoduje wypisanie forum na przekazany strumień
     * @param strona strumień wyjściowy
     * @param pytanie zapytanie otrzymane przez serwer
     * @param auth obiekt autoryzacji
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona, javax.servlet.http.HttpServletRequest pytanie, Autoryzator auth) throws java.io.IOException {
        //teraz wypisanie kategorii
        ArrayList kategorie = db.getKategorie(true);
        for(int i=0;i<kategorie.size();i++) {
            Kategoria k = (Kategoria)kategorie.get(i);
            if (k.czyAktywna())
                if(!k.czyPrywatna()) k.printJSP(strona,pytanie,auth);
                else {
                    User user = auth.getUser(pytanie,this.db);
                    if (user != null)
                        if (user.hasReadKategoriaRight(k.getID())) k.printJSP(strona,pytanie,auth);
                }
        }
    }
    
    /**
     * Metoda wypisuje na stronie główną tabele i jej nagłówki
     * @param strona strumień wyjściowy
     */
    public static void printMainTableJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        //TODO te naglowki moze trzeba bedziew wywalic gdzie indziej .. ale poki co to dobre dla nich miejsce
        strona.println("<table border=\"0\" class=\"tableTextNadForum\" id=\"textNadForum\" width=\"100%\"><tr><td>");
        strona.println("</td></tr></table>");
        strona.println("<table id=\"tableForum\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
        strona.println("\t<tr>");
        strona.println("\t\t<th colspan=\"2\" class=\"thTopLCorner\" nowrap=\"nowrap\">&nbsp;Forum&nbsp;</th>");
        strona.println("\t\t<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;Tematy&nbsp;</th>");
        strona.println("\t\t<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;Posty&nbsp;</th>");
        strona.println("\t\t<th class=\"thTopRCorner\" nowrap=\"nowrap\">&nbsp;Ostatni Post&nbsp;</th>");
        strona.println("\t</tr>");
    }
    
    /**
     * Metoda wypisuje na stronie zamknięcie głównej tabeli
     * @param strona strumien wyjsciowy
     */
    public static void printMainTableCloseJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException {
        strona.println("</table>");
    }
}
