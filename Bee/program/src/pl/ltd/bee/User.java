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
    
    
    /** Metoda zwraca login uzytkownika
     * @return String z nazwa Login
     **/
    public abstract String getLogin();
    
    
    /** Metoda zwraca e-mail uzytkownika
     * @return String z e-mailem
     **/
    public abstract String getEmail();
    
    
    /** Metoda zwraca imie uzytkownika
     * @return String z imieniem
     **/
    public abstract String getImie();
    
    
    /** Metoda zwraca nazwisko uzytkownika
     * @return String z nazwiskiem
     **/
    public abstract String getNazwisko();
    
    
    /** Metoda zwraca numer GG uzytkownika
     * @return String z numerem GG
     **/
    public abstract String getGG();
    
    
    /** Metoda zwraca konto jabber uzytkownika
     * @return String z kontem jabber
     **/
    public abstract String getJabber();
    
    
    /** Metoda zwraca date ostatniego zalogowania uzytkownika
     * @return String z datą ostatniego zalogowania
     **/
    public abstract String getLastLog();
    
}
