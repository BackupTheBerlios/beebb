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
    
    private User uzytkownik;
    private String login = "Guest";
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
    public User zaloguj(String login, String haslo,User uzytkownik) {
        zalogowany=false;
        if(login.compareTo(Config.GUEST)==0) {
            this.login=login;
            return uzytkownik;
        }
        
        if (uzytkownik==null) return null;
        zalogowany=true;
        
        if (uzytkownik.checkPasswd(haslo) && uzytkownik.aktywny()) {
            this.login=login;
            this.uzytkownik = uzytkownik;
            return uzytkownik;
        }
        return null;
    }
    
    /** metoda sprawdza czy uzytkownik jest zalogowany
     * @return T lub N w zaleznosci czy jest zalogowany czy nie
     */
    public boolean zalogowany() {
        return zalogowany;
    }
    
    /** Metoda zwraca nazwe uzytkownika aktualnie zalogowanego
     * @return String reprezentujacy nazwe aktualnie zalogowanego użytkownika
     */
    public String user() {
        return login;
    }
    
    /** Metoda zwraca zalogowanego uzytkownika
     * @return User reprezentujacy zalogowanego użytkownika
     */
    public User getUser() {
        return uzytkownik;
    }
     
}

