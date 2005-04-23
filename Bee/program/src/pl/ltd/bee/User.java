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
    private String currentlog;
    private boolean imieNazwiskoPrywatne;
    private boolean emailPrywatny;
    private boolean ggPrywatne;
    private boolean jabberPrywatny;
    private boolean aktywny;
    private boolean admin;
    private boolean moderator;
    private DataBase db;
    
    
    /** Tworzy obiekt User
     * @param ID identyfikator uzytkownika
     * @param haslo zakodowane haslo uzytkownika
     * @pram imie imie uzytkownika
     * @pram nazwisko nazwisko uzytkownika
     * @pram imieNazwiskoPrywatne czy uznawać imie i nazwisko za prywatne
     * @param email email uzytkownika
     * @param emailPrywatny czy uznawac email za prywatny
     * @param gg numer gadu-gadu
     * @param ggPrywatne czy uznawac numer gg za prywatny
     * @param jabber adres jabbera
     * @param jabberPrywatny czy uznawac jid za prywatny
     * @param lastlog ostatnie logowanie
     * @param currentlog data bierzacego logowania
     * @param aktywny czy użytkownik jest aktywny DataBase.TAK lub DataBase.NIE
     * @param db objekt bazy danych
     */
    public User(int ID, String login, String haslo, String imie, String nazwisko,String imieNazwiskoPrywatne, String email, String emailPrywatny, String gg, String ggPrywatne, String jabber, String jabberPrywatny,String lastlog,String currentlog,String aktywny,String admin,String moderator,DataBase _db) {
        this.ID=ID;
        this.login=login;
        this.haslo=haslo;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.gg=gg;
        this.jabber=jabber;
        this.lastlog=lastlog;
        this.currentlog=currentlog;
        if (imieNazwiskoPrywatne.compareTo(DataBase.TAK)==0)
            this.imieNazwiskoPrywatne=true; else this.imieNazwiskoPrywatne=false;
        if (emailPrywatny.compareTo(DataBase.TAK)==0)
            this.emailPrywatny=true; else this.emailPrywatny=false;
        if (ggPrywatne.compareTo(DataBase.TAK)==0)
            this.ggPrywatne=true; else this.ggPrywatne=false;
        if (jabberPrywatny.compareTo(DataBase.TAK)==0)
            this.jabberPrywatny=true; else this.jabberPrywatny=false;
        if (aktywny.compareTo(DataBase.TAK)==0)
            this.aktywny=true; else this.aktywny=false;
        if (admin.compareTo(DataBase.TAK)==0)
            this.admin=true; else this.admin=false;
        if (moderator.compareTo(DataBase.TAK)==0)
            this.moderator=true; else this.moderator=false;
        this.db = _db;
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
    
    /** Metoda sprawdza czy uzytkownik jest moderatorem podanego podforum
     * @param id_podforum Podforum o ktorego moderowanie pytamy
     * @return T lub N w zaleznosci czy user jest moderatorem czy nie
     */
    public boolean moderator(int id_podforum){
            return this.db.isModerator(this.ID, id_podforum);
    }
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie Imienia i nazwiska
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowName() {
        return !imieNazwiskoPrywatne;
    }
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie emaila
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowEmail() {
        return !emailPrywatny;
    }
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie numeru gg
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowGG() {
        return !ggPrywatne;
    }
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie jid
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowJabber() {
        return !jabberPrywatny;
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
    
    
    /** Metoda ustawia haslo uzytkownika
     * @param haslo String z zakodowanym hasłem użytkownika
     **/
    public void setHaslo(String haslo){
        this.haslo=haslo;
    }
    
    
    /** Metoda zwraca imie uzytkownika
     * @return String z imieniem
     **/
    public String getImie(){
        return this.imie;
    }
    
    
    /** Metoda ustawia Imie uzytkownika
     * @param imie String z Imieniem użytkownika
     **/
    public void setImie(String imie){
        this.imie=imie;
    }
    
    
    /** Metoda zwraca nazwisko uzytkownika
     * @return String z nazwiskiem
     **/
    public String getNazwisko(){
        return this.nazwisko;
    }
    
    
    /** Metoda ustawia Nazwisko uzytkownika
     * @param nazwisko String z Nazwiskiem użytkownika
     **/
    public void setNazwisko(String nazwisko){
        this.nazwisko=nazwisko;
    }
    
    
    /** Metoda zwraca e-mail uzytkownika
     * @return String z e-mailem
     **/
    public String getEmail(){
        return this.email;
    }
    
    
    /** Metoda ustawia email uzytkownika
     * @param email String z emailem użytkownika
     **/
    public void setEmail(String email){
        this.email=email;
    }
    
    
    /** Metoda zwraca numer GG uzytkownika
     * @return String z numerem GG
     **/
    public String getGG(){
        return this.gg;
    }
    
    
    /** Metoda ustawia numer GG uzytkownika
     * @param gg String z numerem GG
     **/
    public void setGG(String gg){
        this.gg=gg;
    }
    
    
    /** Metoda zwraca konto jabber uzytkownika
     * @return String z kontem jabber
     **/
    public String getJabber(){
        return this.jabber;
    }
    
    
    /** Metoda ustawia jid uzytkownika
     * @param jabber String z jid
     **/
    public void setJabber(String jabber){
        this.jabber=jabber;
    }
    
    
    /** Metoda zwraca date ostatniego zalogowania uzytkownika
     * @return String z datą ostatniego zalogowania
     **/
    public String getLastLog(){
        return this.lastlog;
    }
    
    
    /** Metoda zwraca date bierzącego zalogowania uzytkownika
     * @return String z datą bierzącego zalogowania
     **/
    public String getCurrentLog(){
        return this.currentlog;
    }
    
    
    /** Metoda ustawia bierzace logowanie
     * @param currentLog data zalogowania
     */
    public void setCurrentLog(String currentLog) {
        this.lastlog=this.currentlog;
        this.currentlog=currentLog;
    }
    
    
    /** Metoda ustawia prywatność Imienia i Nazwiska
     * @param czyPrywatne T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setImieNazwiskoPrywatne(boolean czyPrywatne){
        this.imieNazwiskoPrywatne=czyPrywatne;
    }
    
    
    /** Metoda ustawia prywatność Emaila
     * @param czyPrywatny T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setEmailPrywatny(boolean czyPrywatny){
        this.emailPrywatny=czyPrywatny;
    }
    
    
    /** Metoda ustawia prywatność numeru gadu-gadu
     * @param czyPrywatne T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setGGPrywatne(boolean czyPrywatne){
        this.ggPrywatne=czyPrywatne;
    }
    
    
    /** Metoda ustawia prywatność jid
     * @param czyPrywatny T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setJabberPrywatny(boolean czyPrywatny){
        this.jabberPrywatny=czyPrywatny;
    }
        
}
