/*
 * Commons.java
 *
 * Created on 26 kwiecien 2005, 13:14
 */

package pl.ltd.bee;

/**
 *
 * @author wilk
 */

import java.util.*;
import java.util.regex.*;

public class Commons {
    
    /** Creates a new instance of Commons */
    public Commons() {
    }
    
    
    /**
     * Metoda zwraca wypelniony znacznik HEAD.
     * @param path Sciezka wzgledna do korzenia calego serwisu od strony w ktorej zostanie umieszczony znacznik. Dla korzenia podaje sie ".". Sciezka nie moze byc zakonczona separatorem "/"
     * @param title Tytul strony
     * @return Zwaraca lancuch znakow bedacy wypelnionym znacznikiem head.
     */
    public static String htmlHead(String path, String title){
        return  "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd\">" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                "<head>\n"+
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"+
                "<meta http-equiv=\"Content-Style-Type\" content=\"text/css\"/>\n"+
                "<meta name=\"Copyright\" content=\"BeeBB Group &copy; 2005\" />\n"+
                "<meta name=\"Author\" content=\"BeeBB Group\" />\n"+
                "<meta name=\"description\" content=\"??\" />\n"+
                "<meta name=\"keywords\" content=\"??\" />\n"+
                "<title>"+title+"</title>\n"+
                "<link href=\""+path+"/images/bee_icon.jpg\" rel=\"SHORTCUT ICON\" />\n"+
                "<link rel=\"stylesheet\" href=\""+path+"/styles/temat.css\" type=\"text/css\"/>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/skrypt.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/iframe_resize.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/forms.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/header.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/hints.js\"></script>\n"+
                "</head>\n";

    }
    
    /**
     * Metoda zwraca wypelniony znacznik HEAD. Tytul przyjety jest jako "BeeBB :: Content"
     * @param path Sciezka wzgledna do korzenia calego serwisu od strony w ktorej zostanie umieszczony znacznik. Dla korzenia podaje sie ".". Sciezka nie moze byc zakonczona separatorem "/"
     * @return Zwaraca lancuch znakow bedacy wypelnionym znacznikiem head.
     */
    public static String htmlHead(String path){
        return htmlHead(path, "BeeBB :: Content");
    }
    
    
    
    /** 
     * Metoda ustawia naglowki o cacheowaniu. Okresla dokument jako trwaly przez rok
     * @param response Odpowiedz jaka zostanie wyslana z serwera do klienta
     */
    public static void setCachingForever(javax.servlet.http.HttpServletResponse response){
        setCachingFor(response, 365 * 24 * 60 * 60);
    }
    

    /** 
     * Metoda ustawia naglowki o cacheowaniu. Okresla dokument jako trwaly przez wskazana liczbe sekund
     * @param response Odpowiedz jaka zostanie wyslana z serwera do klienta
     * @param sec Ilosc sekund waznosci dokumentu
     */
    public static void setCachingFor(javax.servlet.http.HttpServletResponse response, long sec){
        java.util.Date d = new java.util.Date((new java.util.Date()).getTime() + sec * 1000);
        //java.text.SimpleDateFormat d1 = new java.text.SimpleDateFormat("dd MM yyyy hh:mm:ss");
        response.setHeader("Expires",d.toString());//d1.format(d));
        response.setHeader("Cache-Control","max-age = "+Long.toString(sec));
    }
    
    
    /* Metoda przygotowuje wpisana wypowiedz uzytkownika do wprowadzenia do bazy
     * @param text String ktory zostanie zmodyfikowany
     * @return Zmodyfikowany String
     */
    public static String wypowiedzDoBazy(String text){
            text=text.replaceAll("&","&amp;");
            text=text.replaceAll("<","&lt;");
            text=text.replaceAll(">","&gt;");
            text=text.replaceAll("\"","&quot;");
            text=text.replaceAll("'","\'");
            text=text.replaceAll("\r\n","<br/>");
            text=text.replaceAll("\n","<br/>");
            text = Commons.dodajEmotikonki(text);
            return text;
    }

    /* Metoda przygotowuje wypowiedz z bazy do wypisania jej na stronie  do edycji
     * @param text String ktory zostanie zmodyfikowany
     * @return Zmodyfikowany String
     */
    public static String wypowiedzDoTekst(String text){
            text=text.replaceAll("<br/>","\r\n");
            text=text.replaceAll("&lt;","<");
            text=text.replaceAll("&gt;",">");
            text=text.replaceAll("&amp;","&");
            text=text.replaceAll("&quot;","\"");
            text = Commons.zabierzEmotikonki(text);
            return text;
    }
    
    /**
     * Metoda dostarcza znacznik xhtml bedacy poprawnym odnosnikiem w projekcie wykorzystujacym rotacje ramek
     * @param text Zawartosc tekstowa odnosnika
     * @param where Strona docelowa
     * @param classType Nazwa klasy odnosnika
     * @return Znacznik xhtml z odnosnikiem
     */
    public static String aHref(String text, String where, String classType){
        return "<span class=\""+classType+"\" style=\"cursor: pointer;\" onclick=\"hrefClick('"+where+"')\">"+text+"</span>";
    }
    
    /**
     * Metoda dostarcza znacznik xhtml bedacy poprawnym odnosnikiem w projekcie wykorzystujacym rotacje ramek
     * @param text Zawartosc tekstowa odnosnika
     * @param where Strona docelowa
     * @param classType Nazwa klasy odnosnika
     * @return Znacznik xhtml z odnosnikiem
     */
    public static String aHref(String text, String where){
        return aHref(text,where,"aHref");
    }
    
    /** Metoda zamienia wszystkie znaczniki w podanym tekscie na znaczniki xhtml odnoszace sie do emotikonek
     * @param s Tekst do zmiany
     * @return Zmieniony tekst
     */
    public static String dodajEmotikonki(String s){
        Enumeration ikony = Config.SMILES.keys();
        while (ikony.hasMoreElements()){
            Object key = ikony.nextElement();
            s = s.replaceAll(Config.SMILE_TAG_OPEN+(String)key+Config.SMILE_TAG_CLOSE,makeEmotikonLink((String)Config.SMILES.get(key),(String)key));
        }
        return s;
    }
    
    /** Metoda zamienia wszystkie znaczniki <img w podanym tekscie na znaczniki emotikonek 
     * @param s Tekst do zmiany
     * @return Zmieniony tekst
     */
    public static String zabierzEmotikonki(String s){
        String wynik = s;
        Pattern pattern = Pattern.compile("<img src=\"([^\"]+)\" alt=\"([^\"]+)\" class=\"imgEmotikona\" />");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find())
        {
            String url = matcher.group(1);
            String tag = matcher.group(2);
            url = url.replaceAll("\\\\","\\\\\\\\");
            url = url.replaceAll("\\:","\\\\\\:");
            url = url.replaceAll("\\.","\\\\\\.");
            wynik = wynik.replaceAll("<img src=\""+url+"\" alt=\""+tag+"\" class=\"imgEmotikona\" />",Config.SMILE_TAG_OPEN+tag+Config.SMILE_TAG_CLOSE);
        }
        return wynik;
    }
    
    /** Metoda dostarcza znacznika xhtml wyswietlajacego emotikone na podstawie podanego url'a
     * @param url Sciezka do pliku graficznego zaczynajac od korzenia serwisu
     * @return Znacznik z grafika
     */
    public static String makeEmotikonLink(String url){
       return makeEmotikonLink(url,">Image<");
    }
    
    /** Metoda dostarcza znacznika xhtml wyswietlajacego emotikone na podstawie podanego url'a
     * @param url Sciezka do pliku graficznego zaczynajac od korzenia serwisu
     * @param alt Tekst opisujacy obrazek
     * @return Znacznik z grafika
     */
    public static String makeEmotikonLink(String url,String alt){
       return makeEmotikonLink(url,alt,"");
    }
    
    /** Metoda dostarcza znacznika xhtml wyswietlajacego emotikone na podstawie podanego url'a
     * @param url Sciezka do pliku graficznego zaczynajac od korzenia serwisu
     * @param alt Tekst opisujacy obrazek
     * @param extraAttr Dodatkowe wlasciwosci dla znacznika xhtml. Np: "onclick=\"aHref('index.jsp')\""
     * @return Znacznik z grafika
     */
    public static String makeEmotikonLink(String url,String alt, String extraAttr){
       return "<img src=\""+url+"\" alt=\""+alt+"\" class=\"imgEmotikona\" "+extraAttr+" />";
    }
}
