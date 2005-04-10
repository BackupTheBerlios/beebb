/*
 * RegisteredUser.java
 *
 * Created on 18 marzec 2005, 22:18
 */

package pl.ltd.bee;

/**
 *
 * @author pawelb
 */
public class RegisteredUser extends User {
    private int ID;
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String email;
    private String gg;
    private String jabber;
    private boolean aktywny;
    /** Tworzy obiekt RegisteredUser
     * @param ID identyfikator uzytkownika
     * @param haslo zakodowane haslo uzytkownika
     * @pram imie imie uzytkownika
     * @pram nazwisko nazwisko uzytkownika
     * @param email email uzytkownika
     * @param gg numer gadu-gadu
     * @param jabber adres jabbera
     */
    public RegisteredUser(int ID, String login, String haslo, String imie, String nazwisko, String email, String gg, String jabber,String aktywny) {
        this.ID=ID;
        this.login=login;
        this.haslo=haslo;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.gg=gg;
        this.jabber=jabber;
        if (aktywny.compareTo(DataBase.TAK)==0)
            this.aktywny=true; else this.aktywny=false;
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
}
