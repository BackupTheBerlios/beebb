/*
 * RegisteredUser.java
 *
 * Created on 18 marzec 2005, 22:18
 */

package pl.ltd.bee;

import pl.aislib.util.crypt.UnixCrypt;

/**
 *
 * @author pawel
 */
public class RegisteredUser extends User {
    private long ID;
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String email;
    private String gg;
    private String jabber;
    
    /** Creates a new instance of RegisteredUser */
    public RegisteredUser(long ID, String login, String haslo, String imie, String nazwisko, String email, String gg, String jabber) {
        this.ID=ID;
        this.login=login;
        this.haslo=haslo;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.gg=gg;
        this.jabber=jabber;
    }
    
    public boolean checkPasswd(String passwd) {
        return UnixCrypt.matches(haslo, passwd);
    }
    
}
