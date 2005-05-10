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
    private String tlen;
    private String wpKontakt;
    private String icq;
    private String msn;
    private String miasto;
    private String plec;
    private String rokUrodzenia;
    private String lastlog;
    private String currentlog;
    private boolean imieNazwiskoPrywatne;
    private boolean emailPrywatny;
    private boolean ggPrywatne;
    private boolean jabberPrywatny;
    private boolean tlenPrywatny;
    private boolean wpKontaktPrywatny;
    private boolean icqPrywatne;
    private boolean msnPrywatny;
    private boolean miastoPrywatne;
    private boolean rokUrodzeniaPrywatny;
    private boolean aktywny;
    private boolean admin;
    private boolean moderator;
    private DataBase db;
    
    public User() {}
    
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
     * @param tlen id w komunikatorze tlen
     * @param tlenPrywatny czy uznawac id komunikatora tlen za prywatne
     * @param wpKontakt id komunikatora wpKontakt
     * @param wpPrywatny czy uznawac id komunikatora wpKontakt za prywatny
     * @param icq id komnikatora icq
     * @param icqPrywatne czy uznawac id komnikatora icq za prywatne
     * @param msn id komnikatora msn
     * @param msnPrywatne czy uznawac id komnikatora msn za prywatne
     * @param miasto miasto w ktorym mieszka uzytkownik (bądź z którego pochodzi)
     * @param miastoPrywatne czy uznawac miasto za prywatne
     * @param plec plec uzytkownika ('K' lub 'M')
     * @param rokUrodzenia rok urodzenia uzytkownika
     * @param rokUrodzeniaPrywatny czy uznawac rok urodzenia jako prywatny
     * @param lastlog ostatnie logowanie
     * @param currentlog data bierzacego logowania
     * @param aktywny czy użytkownik jest aktywny DataBase.TAK lub DataBase.NIE
     * @param db objekt bazy danych
     */
    public User(int ID, String login, String haslo, String imie, String nazwisko,String imieNazwiskoPrywatne, String email, String emailPrywatny, String gg, String ggPrywatne, String jabber, String jabberPrywatny,String tlen, String tlenPrywatny,String wpKontakt, String wpKontaktPrywatny,String icq, String icqPrywatne,String msn, String msnPrywatny,String miasto, String miastoPrywatne,String plec, String rokUrodzenia,String rokUrodzeniaPrywatny,String lastlog,String currentlog,String aktywny,String admin,String moderator,DataBase _db) {
        this.ID=ID;
        this.login=login;
        this.haslo=haslo;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.gg=gg;
        this.jabber=jabber;
        this.tlen=tlen;
        this.wpKontakt=wpKontakt;
        this.icq=icq;
        this.msn=msn;
        this.miasto=miasto;
        this.plec=plec;
        this.rokUrodzenia=rokUrodzenia;
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
        if (tlenPrywatny.compareTo(DataBase.TAK)==0)
            this.tlenPrywatny=true; else this.tlenPrywatny=false;
        if (wpKontaktPrywatny.compareTo(DataBase.TAK)==0)
            this.wpKontaktPrywatny=true; else this.wpKontaktPrywatny=false;
        if (icqPrywatne.compareTo(DataBase.TAK)==0)
            this.icqPrywatne=true; else this.icqPrywatne=false;
        if (msnPrywatny.compareTo(DataBase.TAK)==0)
            this.msnPrywatny=true; else this.msnPrywatny=false;
        if (miastoPrywatne.compareTo(DataBase.TAK)==0)
            this.miastoPrywatne=true; else this.miastoPrywatne=false;
        if (rokUrodzeniaPrywatny.compareTo(DataBase.TAK)==0)
            this.rokUrodzeniaPrywatny=true; else this.rokUrodzeniaPrywatny=false;
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
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie id tlenu
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowTlen() {
        return !tlenPrywatny;
    }
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie id wpKontakt
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowWPKontakt() {
        return !wpKontaktPrywatny;
    }
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie id icq
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowICQ() {
        return !icqPrywatne;
    }
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie id msn
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowMSN() {
        return !msnPrywatny;
    }
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie miasta zamieszkania
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowCity() {
        return !miastoPrywatne;
    }
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie roku urodzenia
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowBirthDate() {
        return !rokUrodzeniaPrywatny;
    }
    
    /** Metoda zwraca ID użytkownika
     * @return Integer reprezentujacy uzytkownika
     */
    public int getID() {
        return ID;
    }
    
    
    /** Metoda ustawia id uzytkownika
     * @param id Int z id-em uzytkownika
     **/
    public void setID(int id){
        this.ID=id;
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
    
    /** Metoda ustawia login uzytkownika
     * @param login String z loginem użytkownika
     **/
    public void setLogin(String login){
        this.login=login;
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
    
    
    /** Metoda zwraca konto tlen uzytkownika
     * @return String z kontem tlen
     **/
    public String getTlen(){
        return this.tlen;
    }
    
    
    /** Metoda ustawia tlen id uzytkownika
     * @param jabber String z id tlen
     **/
    public void setTlen(String tlen){
        this.tlen=tlen;
    }
    
    
    /** Metoda zwraca konto wpKontakt uzytkownika
     * @return String z kontem wpKontakt
     **/
    public String getWPKontakt(){
        return this.wpKontakt;
    }
    
    
    /** Metoda ustawia wpKontakt id uzytkownika
     * @param jabber String z id wpKontakt
     **/
    public void setWPKontakt(String wpKontakt){
        this.wpKontakt=wpKontakt;
    }
    
    
    
    /** Metoda zwraca konto ICQ uzytkownika
     * @return String z kontem ICQ
     **/
    public String getICQ(){
        return this.icq;
    }
    
    
    /** Metoda ustawia ICQ id uzytkownika
     * @param jabber String z id ICQ
     **/
    public void setICQ(String icq){
        this.icq=icq;
    }
    
    
    /** Metoda zwraca konto MSN uzytkownika
     * @return String z kontem MSN
     **/
    public String getMSN(){
        return this.msn;
    }
    
    
    /** Metoda ustawia MSN id uzytkownika
     * @param jabber String z id MSN
     **/
    public void setMSN(String msn){
        this.msn=msn;
    }
    
    
    /** Metoda zwraca miasto zamieszkania uzytkownka
     * @return String z miastem
     **/
    public String getCity(){
        return this.miasto;
    }
    
    
    /** Metoda ustawia miasto uzytkownika
     * @param jabber String z miastem uzytkownika
     **/
    public void setCity(String miasto){
        this.miasto=miasto;
    }
    
    
    /** Metoda zwraca rok urodzenia Uzytkownka
     * @return String z rokiem
     **/
    public String getBirthDate(){
        return this.rokUrodzenia;
    }
    
    
    /** Metoda ustawia rok urodzenia uzytkownika
     * @param jabber String z rokiem urodzenia
     **/
    public void setBirthDate(String rokUrodzenia){
        this.rokUrodzenia=rokUrodzenia;
    }
    
    
    /** Metoda zwraca płeć użytkownika
     * @return String z płcią
     **/
    public String getSex(){
        return this.plec;
    }
    
    
    /** Metoda ustawia płeć uzytkownika {Database.kobieta, Database.mezczyzna}
     * @param jabber String z płcią
     **/
    public void setSex(String plec){
        this.plec=plec;
    }
    
    
    
    /** Metoda ustawia lastlog
     * @param ll string z datą
     **/
    public void setLastLog(String ll){
        this.lastlog=ll;
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
    
    
    /** Metoda wstawia date bierzącego logowania do bazy*/
    public void insertCurrentLog() {
        this.lastlog=this.currentlog;
        this.currentlog=db.getDate();
        db.updateUser(this);
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
    public void setEmailPrivate(boolean prv){
        this.emailPrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność numeru gadu-gadu
     * @param czyPrywatne T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setGGPrivate(boolean prv){
        this.ggPrywatne=prv;
    }
    
    
    /** Metoda ustawia prywatność jid
     * @param czyPrywatny T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setJabberPrivate(boolean prv){
        this.jabberPrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność id tlena
     * @param czyPrywatny T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setTlenPrivate(boolean prv){
        this.tlenPrywatny=prv;
    }
    
    /** Metoda ustawia prywatność id wpKontakt
     * @param czyPrywatny T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setWPKontaktPrivate(boolean prv){
        this.wpKontaktPrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność id icq
     * @param czyPrywatny T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setICQPrivate(boolean prv){
        this.icqPrywatne=prv;
    }
    
    
    /** Metoda ustawia prywatność id msn
     * @param czyPrywatny T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setMSNPrivate(boolean prv){
        this.msnPrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność miasta uzytkownika
     * @param czyPrywatny T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setCityPrivate(boolean prv){
        this.miastoPrywatne=prv;
    }
    
    
    /** Metoda sprawdza czy dany uzytkownik ma prawo odczytu danego watku
     * @param id Identyfikator watku
     * @return True jesli użytkownik ma prawo odczytu wskazanego wątku lub False w p.p.
     */
    public boolean hasReadWatekRight(int id){
        Podforum p = db.getPodforumbyWatek(id);
        return db.hasPodforumRights(this.ID, p.getID(), true, false);
    }
    
    /** Metoda sprawdza czy dany uzytkownik ma prawo zapisu do danego watku
     * @param id Identyfikator watku
     * @return True jesli użytkownik ma prawo zapisu do wskazanego wątku lub False w p.p.
     */
    public boolean hasWriteWatekRight(int id){
        Podforum p = db.getPodforumbyWatek(id);
        return db.hasPodforumRights(this.ID, p.getID(), false, true);
    }
    
    /** Metoda sprawdza czy dany uzytkownik ma prawo odczytu danego podforum
     * @param id Identyfikator podforum
     * @return True jesli użytkownik ma prawo odczytu wskazanego Podforum lub False w p.p.
     */
    public boolean hasReadPodforumRight(int id){
        return db.hasPodforumRights(this.ID, id, true, false);
    }
    
    /** Metoda sprawdza czy dany uzytkownik ma prawo zapisu do danego podforum
     * @param id Identyfikator podforum
     * @return True jesli użytkownik ma prawo zapisu do wskazanego Podforum lub False w p.p.
     */
    public boolean hasWritePodforumRight(int id){
        return db.hasPodforumRights(this.ID, id, false, true);
    }
    
    /** Metoda sprawdza czy dany uzytkownik ma prawo odczytu danej Kategorii
     * @param id Identyfikator Kategorii
     * @return True jesli użytkownik ma prawo odczytu wskazanej Ktegorii lub False w p.p.
     */
    public boolean hasReadKategoriaRight(int id){
        return db.hasKategoriaRights(this.ID, id, true, false);
    }
    
    /** Metoda sprawdza czy dany uzytkownik ma prawo zapisu do danej Kategorii
     * @param id Identyfikator Kategorii
     * @return True jesli użytkownik ma prawo zapisu do wskazanej Ktegorii lub False w p.p.
     */
    public boolean hasWriteKategoriaRight(int id){
        return db.hasKategoriaRights(this.ID, id, false, true);
    }
}
