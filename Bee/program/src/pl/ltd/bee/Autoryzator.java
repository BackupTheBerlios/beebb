/*
 * Autoryzator.java
 *
 * Created on 17 marzec 2005, 19:36
 */

package pl.ltd.bee;

/**
 *
 * @author pawelb
 */
public class Autoryzator {
    
    User uzytkownik;
    String login = "Guest";
    boolean zalogowany;
    
    
    /** Creates a new instance of Autoryzator */
    public Autoryzator() {
        zalogowany=false;
    }
    
    /** Zalogowuje uzytkownika
     * @param login nazwa uzytwkownika
     * @param haslo haslo uzytkownika w plain tekst
     * @param db obiekt typu Database do wyciagania uzytkownika
     */
    public User zaloguj(String login, String haslo,DataBase db) {
        uzytkownik = db.getUser(login);
        zalogowany=false;
        if (uzytkownik==null) return null;
        
        if(login.compareTo("Guest")==0) {
            this.login=login;
            return uzytkownik;
        }
        
        zalogowany=true;
        if (uzytkownik.checkPasswd(haslo)) {
            this.login=login;
            return uzytkownik;
        }
        return null;
        
    }
    
    public boolean zalogowany() {
        return zalogowany;
    }
    
    public String user() {
        return login;
    }
}

