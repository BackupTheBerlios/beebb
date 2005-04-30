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
        return "<head>\n"+
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
            text=text.replaceAll("\r\n","<br/>");
            text=text.replaceAll("\n","<br/>");
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
}
