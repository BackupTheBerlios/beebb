/*
 * Autoryzator.java
 *
 * Created on 17 marzec 2005, 19:36
 */

package pl.ltd.bee;

/**
 *
 * @author pawel
 */
public class Autoryzator {
    
    User uzytkownik;
    boolean zalogowany;
    
    /** Creates a new instance of Autoryzator */
    public Autoryzator() {
        zalogowany=false;
    }
    
    public User zaloguj(String uzytkownik, String haslo) {
        if(uzytkownik.compareTo("Guest")==0)
            zalogowany=false;
        else
            zalogowany=true;
        
        return null;
    } 
    
    public boolean zalogowany() {
        return zalogowany;
    }
    
    public String user() {
        return "user";
    }
}

