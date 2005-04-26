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
     * @param path Sciezka wzgledem korzenia calego serwisu do strony w ktorej zostanie umieszczony znacznik. Dla korzenia podaje sie ".". Sciezka nie moze byc zakonczona separatorem "/"
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
                "<link rel=\"stylesheet\" href=\""+path+"/styles/temat.css\" type=\"text/css\"/>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/skrypt.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/iframe_resize.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/forms.js\"></script>\n"+
                "<script type=\"text/javascript\" src=\""+path+"/js/header.js\"></script>\n"+
                "</head>\n";

    }
    
    /**
     * Metoda zwraca wypelniony znacznik HEAD. Tytul przyjety jest jako "BeeBB :: Content"
     * @param path Sciezka wzgledem korzenia calego serwisu do strony w ktorej zostanie umieszczony znacznik. Dla korzenia podaje sie ".". Sciezka nie moze byc zakonczona separatorem "/"
     * @return Zwaraca lancuch znakow bedacy wypelnionym znacznikiem head.
     */
    public static String htmlHead(String path){
        return htmlHead(path, "BeeBB :: Content");
    }
    
    
}
