/*
 * Autoryzator.java
 *
 * Created on 7 kwiecień 2005, 21:46
 */

package pl.ltd.bee;

/**
 *
 * @author wilk
 */
public class Autoryzator {
    
    
    public static final String COOKIE_USER_NAME = "user";
    public static final String COOKIE_PASS_NAME = "pass";
    
    /** Creates a new instance of Autoryzator */
    public Autoryzator() {
    }
    
    /** Zalogowuje uzytkownika
     * @param login nazwa uzytwkownika
     * @param haslo haslo uzytkownika w postaci hash'a
     * @param db obiekt klasy Database do wyciagania uzytkownika
     * @param conf obiekt klasy Config do ustawienia parametrow logowania
     * @param odpowiedz Odpowiedz serwera jaka zostanie wyslana do uzytkownika
     *  @return User reprezentujacy zalogowanego użytkownika
     */
    public User zaloguj(String login, String haslo,DataBase db, Config conf, javax.servlet.http.HttpServletResponse odpowiedz) {
        User user = db.getUser(login);
        if (user == null) return null;        
        if (!user.aktywny()) return null; //TODO to sprawdzanie wyrzucil bym do auth.jsp wtedy mozna napisac "Twoje konto jest zablokowane"
        if (Crypto.matches(user.getHaslo(),haslo))
        {
            javax.servlet.http.Cookie coockieUser = new javax.servlet.http.Cookie(COOKIE_USER_NAME,login);
            javax.servlet.http.Cookie coockiePass = new javax.servlet.http.Cookie(COOKIE_PASS_NAME,user.getHaslo());
            coockieUser.setMaxAge(conf.LOG_IN_MAX_AGE);
            coockiePass.setMaxAge(conf.LOG_IN_MAX_AGE);
            coockieUser.setPath("/");
            coockiePass.setPath("/");
            odpowiedz.addCookie(coockieUser);
            odpowiedz.addCookie(coockiePass);
            user.insertCurrentLog();
            return user;
        }
        else return null;
    }

    /** Wylogowuje uzytkownika
     * @param odpowiedz Odpowiedz serwera jaka zostanie wyslana do uzytkownika
     */
    public void wyloguj(javax.servlet.http.HttpServletResponse odpowiedz) {
            javax.servlet.http.Cookie coockieUser = new javax.servlet.http.Cookie(COOKIE_USER_NAME,"");
            javax.servlet.http.Cookie coockiePass = new javax.servlet.http.Cookie(COOKIE_PASS_NAME,"");
            coockieUser.setMaxAge(0);
            coockiePass.setMaxAge(0);
            coockieUser.setPath("/");
            coockiePass.setPath("/");
            odpowiedz.addCookie(coockieUser);
            odpowiedz.addCookie(coockiePass);
    }
    
    /** metoda sprawdza czy uzytkownik jest zalogowany
     *  @param pytanie Zapytanie otrzymane przez serwer
     *  @param db obiekt klasy Database do wyciagania uzytkownika
     *  @return T lub N w zaleznosci czy jest zalogowany czy nie
     */
    public boolean zalogowany(javax.servlet.http.HttpServletRequest pytanie,DataBase db) {
        return this.getUser(pytanie,db) != null;
    }
    
    /** Metoda zwraca nazwe uzytkownika aktualnie zalogowanego
     *  @param pytanie Zapytanie otrzymane przez serwer
     *  @return String reprezentujacy nazwe aktualnie zalogowanego użytkownika
     */
    public String user(javax.servlet.http.HttpServletRequest pytanie) {
        javax.servlet.http.Cookie[] ciastka = pytanie.getCookies();
        if (ciastka == null) return "";
        for(int i=0;i<ciastka.length;i++)
            if (ciastka[i].getName().compareTo(COOKIE_USER_NAME) == 0)
                return ciastka[i].getValue();
        return "";
    }
    
    /** Metoda zwraca zalogowanego uzytkownika
     *  @param pytanie Zapytanie otrzymane przez serwer
     *  @param db obiekt klasy Database do wyciagania uzytkownika
     *  @return User reprezentujacy zalogowanego użytkownika lub NULL jesli zaden nie jest zalogowany
     */
    public User getUser(javax.servlet.http.HttpServletRequest pytanie,DataBase db) {
        javax.servlet.http.Cookie[] ciastka = pytanie.getCookies();
        if (ciastka == null) return null;
        String login = "",haslo="";
        for(int i=0;i<ciastka.length;i++)
            if (ciastka[i].getName().compareTo(COOKIE_USER_NAME) == 0)
                login = ciastka[i].getValue();
            else if (ciastka[i].getName().compareTo(COOKIE_PASS_NAME) == 0)
                haslo = ciastka[i].getValue();
        User user = db.getUser(login);
        if (user == null) return null;
        if ((user.getHaslo().compareTo(haslo) ==0 ) && (user.aktywny()))
            return user;    
        else return null;
    }
     
}

