/*
 * RegisteredUser.java
 *
 * Created on 18 marzec 2005, 22:18
 */

package pl.ltd.bee;

/**
 *
 * @author pawelb i sopi
 */
public class User {
    private int ID;
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String email;
    private String gg;
    private String jabber;
    private String lastlog;
    private boolean aktywny;
    private boolean admin;
    private boolean moderator;
  
    
    /** Tworzy obiekt RegisteredUser
     * @param ID identyfikator uzytkownika
     * @param haslo zakodowane haslo uzytkownika
     * @pram imie imie uzytkownika
     * @pram nazwisko nazwisko uzytkownika
     * @param email email uzytkownika
     * @param gg numer gadu-gadu
     * @param jabber adres jabbera
     * @param lastlog ostatnie logowanie
     * @param aktywny czy użytkownik jest aktywny DataBase.TAK lub DataBase.NIE
     */
    public User(int ID, String login, String haslo, String imie, String nazwisko, String email, String gg, String jabber,String lastlog,String aktywny,String admin,String moderator) {
        this.ID=ID;
        this.login=login;
        this.haslo=haslo;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.gg=gg;
        this.jabber=jabber;
        this.lastlog=lastlog;
        if (aktywny.compareTo(DataBase.TAK)==0)
            this.aktywny=true; else this.aktywny=false;
        if (admin.compareTo(DataBase.TAK)==0)
            this.admin=true; else this.admin=false;
        if (moderator.compareTo(DataBase.TAK)==0)
            this.moderator=true; else this.moderator=false;
    }
    
    
    /** Sprawdza czy podane hasło (plain) zgadza się
     * @param passwd haslo w plain txt
     * @return T lub F w zaleznosci czy haslo sie zgadza
     */
    public boolean checkPasswd(String passwd) {
        return Crypto.matches(haslo, passwd);
    }
    
    
    /** metoda sprawdza czy uzytkownik jest aktywny
     * @return T lub N w zaleznosci czy user jest aktywny czy nie
     */
    public boolean aktywny() {
        return aktywny;
    }
    
    /** metoda sprawdza czy uzytkownik jest administratorem
     * @return T lub N w zaleznosci czy user jest administratorem czy nie
     */
    public boolean admin() {
        return admin;
    }
    
    /** metoda sprawdza czy uzytkownik jest moderatorem
     * @return T lub N w zaleznosci czy user jest moderatorem czy nie
     */
    public boolean moderator() {
        return moderator;
    }
    
    
    /** Metoda zwraca ID użytkownika
     * @return Integer reprezentujacy uzytkownika
     */
    public int getID() {
        return ID;
    }
    
    
    /** Metoda zwraca zahashowane haslo uzytkownika
     * @return String z reprezentacja hasla
     **/
    public String getHaslo(){
        return haslo;
    }
    
    
    /** Metoda zwraca login uzytkownika
     * @return String z nazwa Login
     **/
    public String getLogin(){
        return this.login;
    }
    
    
    /** Metoda zwraca e-mail uzytkownika
     * @return String z e-mailem
     **/
    public String getEmail(){
        return this.email;
    }
    
    
    /** Metoda zwraca imie uzytkownika
     * @return String z imieniem
     **/
    public String getImie(){
        return this.imie;
    }
    
    
    /** Metoda zwraca nazwisko uzytkownika
     * @return String z nazwiskiem
     **/
    public String getNazwisko(){
        return this.nazwisko;
    }
    
    
    /** Metoda zwraca numer GG uzytkownika
     * @return String z numerem GG
     **/
    public String getGG(){
        return this.gg;
    }
    
    
    /** Metoda zwraca konto jabber uzytkownika
     * @return String z kontem jabber
     **/
    public String getJabber(){
        return this.jabber;
    }
    
    
    /** Metoda zwraca date ostatniego zalogowania uzytkownika
     * @return String z datą ostatniego zalogowania
     **/
    public String getLastLog(){
        return this.lastlog;
    }
    
}
