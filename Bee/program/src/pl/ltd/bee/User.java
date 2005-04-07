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
    
    /** Abstrakcyjny konstruktor abstrakcyjnej klasy */
    public User() {};
 
    /** Metoda sprawdza haslo
     * @param passwd haslo w plain tekst
     * @return ponieważ jest to abstrakcyjna klasa zwraca zawsze false
     */
    public abstract  boolean checkPasswd(String passwd);
    
    /** Metoda sprawdza czy uzytkownik jest aktywny
     * @return ponieważ jest to abstrakcyjna klasa zwraca zawsze false
     */
    public abstract boolean aktywny();
    
    
    /** Metoda zwraca ID użytkownika
     * @return Integer reprezentujacy uzytkownika
     */
    public abstract int getID();

    /** Metoda zwraca zahashowane haslo uzytkownika
     * @return String z reprezentacja hasla
     **/
    public abstract String getHaslo();    
}
