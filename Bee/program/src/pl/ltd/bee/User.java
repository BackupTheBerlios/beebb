/*
 * User.java
 *
 * Created on 9 marzec 2005, 18:01
 */

package pl.ltd.bee;

/**
 *
 * @author pawelb
 */
public abstract class User {
    
    /** abstrakcyjny konstruktor abstrakcyjnej klasy */
    public User() {};
 
    /** metoda sprawdza haslo
     * @param passwd haslo w plain tekst
     * @return poniewa¿ jest to abstrakcyjna klasa zwraca zawsze false
     */
    public boolean checkPasswd(String passwd) {
        return false;
    }
    
    /** metoda sprawdza czy uzytkownik jest aktywny
     * @return poniewa¿ jest to abstrakcyjna klasa zwraca zawsze false
     */
    public boolean aktywny() {
        return false;
    }
}
