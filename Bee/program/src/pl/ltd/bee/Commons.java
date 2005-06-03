/*
 * Commons.java
 *
 * Created on 26 kwiecien 2005, 13:14
 */

package pl.ltd.bee;

import java.util.*;
import java.util.regex.*;

/**
 * Klasa zawierająca metody ogólnego zastosowania
 * @author wilk
 */
public class Commons {
    
    /** Tworzy nową instancję klasy Commons */
    public Commons() {
    }
    
    
    /**
     * Metoda zwraca znacznik head wypełniony parametrami meta oraz źródłami JavaScript i CSS
     * @param request otrzymane zapytanie HTTP
     * @param path ścieżka względna do korzenia całego serwisu od strony w której zostanie umieszczony znacznik. Dla korzenia podaje się ".". Ścieżka nie może być zakończona separatorem "/"
     * @param title tytuł strony
     * @param css nazwa pliku z arkuszem stylu (Uwaga tylko nazwa bez rozszeżenia)
     * @return Zwraca łancuch znaków będący wypełnionym znacznikiem head.
     */
    public static String htmlHead(javax.servlet.http.HttpServletRequest request, String path, String title, String css){
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
                "<link rel=\"stylesheet\" href=\""+path+"/styles/"+css+".css\" type=\"text/css\"/>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/skrypt.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/iframe_resize.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/forms.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/header.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/hints.js\"></script>\n"+
                "</head>\n";

    }
    

    /**
     * Metoda zwraca znacznik head wypełniony parametrami meta oraz źródłami JavaScript i CSS
     * @param request otrzymane zapytanie HTTP
     * @param path ścieżka względna do korzenia całego serwisu od strony w której zostanie umieszczony znacznik. Dla korzenia podaje się ".". Ścieżka nie może być zakończona separatorem "/"
     * @param title tytuł strony
     * @return Zwraca łancuch znaków będący wypełnionym znacznikiem head.
     */
    public static String htmlHead(javax.servlet.http.HttpServletRequest request, String path, String title){
        String css = request.getParameter("style");
        if (css == null) css = Config.DEFAULT_STYLE;
        return htmlHead(request,path,title,css);
    }
    
    /**
     * Metoda zwraca znacznik head wypełniony parametrami meta oraz źródłami JavaScript i CSS
     * @param request otrzymane zapytanie HTTP
     * @param path ścieżka względna do korzenia całego serwisu od strony w której zostanie umieszczony znacznik. Dla korzenia podaje się ".". Ścieżka nie może być zakończona separatorem "/"
     * @return Zwraca łancuch znaków będący wypełnionym znacznikiem head.
     */
    public static String htmlHead(javax.servlet.http.HttpServletRequest request, String path){
        return htmlHead(request,path, "BeeBB :: Content");
    }
    
    
    /** 
     * Metoda ustawia nagłówki o cacheowaniu. Określa dokument jako trwały przez rok
     * @param response odpowiedź jaka zostanie wysłana z serwera do klienta
     */
    public static void setCachingForever(javax.servlet.http.HttpServletResponse response){
        setCachingFor(response, 365 * 24 * 60 * 60);
    }
    

    /** 
     * Metoda ustawia nagłówki o cacheowaniu. Określa dokument jako trwały przez wskazaną liczbę sekund
     * @param response odpowiedź jaka zostanie wysłana z serwera do klienta
     * @param sec ilość sekund ważności dokumentu
     */
    public static void setCachingFor(javax.servlet.http.HttpServletResponse response, long sec){
        java.util.Date d = new java.util.Date((new java.util.Date()).getTime() + sec * 1000);
        //java.text.SimpleDateFormat d1 = new java.text.SimpleDateFormat("dd MM yyyy hh:mm:ss");
        response.setHeader("Expires",d.toString());//d1.format(d));
        response.setHeader("Cache-Control","max-age = "+Long.toString(sec));
    }
    
    
    /** 
     * Metoda ustawia nagłówki o cacheowaniu. Określa dokument jako nie trwały
     * @param response odpowiedź jaka zostanie wysłana z serwera do klienta
     */
    public static void setCachingNever(javax.servlet.http.HttpServletResponse response){
        //response.setHeader("Expires",(new java.util.Date()).toString());
        response.setHeader("Cache-Control","max-age=0, must-revalidate");//TODO SPRAWDZIC CZY TAK !!!!!!!!!!!!
    }
    
    
    /* Metoda przygotowuje wpisaną wypowiedź użytkownika do wprowadzenia do bazy
     * @param text string który zostanie zmodyfikowany
     * @return Zmodyfikowany String
     */
    public static String wypowiedzDoBazy(String text){
            text=text.replaceAll("&","&amp;");
            text=text.replaceAll("<","&lt;");
            text=text.replaceAll(">","&gt;");
            text=text.replaceAll("\"","&quot;");
            text=text.replaceAll("'","\\\\'");
            text=text.replaceAll("\r\n","<br/>");
            text=text.replaceAll("\n","<br/>");
            text = Commons.dodajEmotikonki(text);
            return text;
    }

    /* Metoda przygotowuje wypowiedź z bazy do wypisania jej na stronie  do edycji
     * @param text string który zostanie zmodyfikowany
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
    
    
    /** Metoda dostarcza zapytanie GET które należy dokleić do odnośnika
     * @param request otrzymane zapytanie HTTP
     * @return String z nazwą parametru i wartością lub pusty jeśli styl nie jest określony
     */
    public static String getQueryStyle(javax.servlet.http.HttpServletRequest request){
        String css = request.getParameter("style");
        if (css != null)
            return "style="+css;
        else return "";           
    }
    
    /**
     * Metoda dostarcza znacznik xhtml będący poprawnym odnośnikiem w projekcie wykorzystującym rotacje ramek
     * @param request otrzymane zapytanie HTTP 
     * @param text zawartość tekstowa odnośnika
     * @param where strona docelowa
     * @param classType nazwa klasy odnośnika
     * @return Znacznik xhtml z odnośnikiem
     */
    public static String aHref(javax.servlet.http.HttpServletRequest request, String text, String where, String classType){
        String css = request.getParameter("style");
        if (css != null)
            if (where.indexOf('?') != -1)
                where+="&amp;style="+css;
            else where+="?style="+css;           
        return "<span class=\""+classType+"\" style=\"cursor: pointer;\" onclick=\"hrefClick('"+where+"')\">"+text+"</span>";
    }
    
    /**
     * Metoda dostarcza znacznik xhtml będący poprawnym odnośnikiem w projekcie wykorzystującym rotacje ramek
     * @param request otrzymane zapytanie HTTP 
     * @param text zawartość tekstowa odnośnika
     * @param where strona docelowa
     * @return Znacznik xhtml z odnośnikiem
     */
    public static String aHref(javax.servlet.http.HttpServletRequest request, String text, String where){
        return aHref(request,text,where,"aHref");
    }
    
    /** Metoda zamienia wszystkie znaczniki w podanym tekscie na znaczniki xhtml odnoszące się do emotikonek
     * @param s tekst do zmiany
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
     * @param s tekst do zmiany
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
    
    /** Metoda dostarcza znacznika xhtml wyświetlającego emotikone na podstawie podanego url'a
     * @param url ścieżka do pliku graficznego zaczynając od korzenia serwisu
     * @return Znacznik z grafiką
     */
    public static String makeEmotikonLink(String url){
       return makeEmotikonLink(url,">Image<");
    }
    
    /** Metoda dostarcza znacznika xhtml wyświetlającego emotikone na podstawie podanego url'a
     * @param url ścieżka do pliku graficznego zaczynając od korzenia serwisu
     * @param alt tekst opisujący obrazek
     * @return Znacznik z grafiką
     */
    public static String makeEmotikonLink(String url,String alt){
       return makeEmotikonLink(url,alt,"");
    }
    
    /** Metoda dostarcza znacznika xhtml wyświetlającego emotikone na podstawie podanego url'a
     * @param url ścieżka do pliku graficznego zaczynając od korzenia serwisu
     * @param alt tekst opisujący obrazek
     * @param extraAttr dodatkowe właściwości dla znacznika xhtml. Np: "onclick=\"aHref('index.jsp')\""
     * @return Znacznik z grafiką
     */
    public static String makeEmotikonLink(String url,String alt, String extraAttr){
       return "<img src=\""+url+"\" alt=\""+alt+"\" class=\"imgEmotikona\" "+extraAttr+" />";
    }
    

}
