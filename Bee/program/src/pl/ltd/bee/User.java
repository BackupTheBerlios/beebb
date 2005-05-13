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
    private String www;
    private String gg;
    private String jabber;
    private String tlen;
    private String wpKontakt;
    private String icq;
    private String msn;
    private String yahoo;
    private String skype;
    private String miasto;
    private String plec;
    private String avatar;
    private String sygnaturka;
    private String styl;
    private String jezyk;
    private String dataUrodzenia;
    private int liczbaWypowiedzi;
    private int liczbaWatkow;
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
    private boolean yahooPrywatne;
    private boolean skypePrywatny;
    private boolean miastoPrywatne;
    private boolean sygnaturkaPubliczna;
    private boolean dataUrodzeniaPrywatna;
    private boolean powiadamianie;
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
     * @param www strona www uzytkownika
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
     * @param yahoo id komnikatora yahoo
     * @param yahooPrywatne czy uznawac id komnikatora yahoo za prywatne
     * @param skype id komnikatora skype
     * @param skypePrywatny czy uznawac id komnikatora skype za prywatne
     * @param miasto miasto w ktorym mieszka uzytkownik (bądź z którego pochodzi)
     * @param miastoPrywatne czy uznawac miasto za prywatne
     * @param plec plec uzytkownika ('K' lub 'M')
     * @param avatar ikonka uzytkownika - nazwa pliku z ikonką
     * @param sygnaturka Sygnaturka użytkownika doklejana pod wypowiedziami
     * @param sygnaturkaPubliczna czy wyświetlać sygnaturkę
     * @param styl styl forum
     * @param jezyk jezyk forum
     * @param powiadamianie czy powiadamiac o zmianach w zalozonych watkach
     * @param dataUrodzenia data urodzenia uzytkownika
     * @param dataUrodzeniaPrywatna czy uznawac rok urodzenia jako prywatny
     * @param liczbaWypowiedzi liczba wypowiedzi napisanych przez użytkownika
     * @param liczbaWatkow liczba watkow napisanych przez użytkownika
     * @param lastlog ostatnie logowanie
     * @param currentlog data bierzacego logowania
     * @param aktywny czy użytkownik jest aktywny DataBase.TAK lub DataBase.NIE
     * @param db objekt bazy danych
     */
    public User(int ID, String login, String haslo, String imie, String nazwisko,String imieNazwiskoPrywatne,
            String email, String emailPrywatny, String www, String gg, String ggPrywatne, String jabber, String jabberPrywatny,
            String tlen, String tlenPrywatny,String wpKontakt, String wpKontaktPrywatny,String icq, String icqPrywatne,
            String msn, String msnPrywatny,String yahoo, String yahooPrywatne,String skype, String skypePrywatny, String miasto,
            String miastoPrywatne,String plec, String avatar, String sygnaturka, String sygnaturkaPubliczna, String styl,String jezyk,
            String powiadamianie, String dataUrodzenia, String dataUrodzeniaPrywatna, String liczbaWypowiedzi,String liczbaWatkow,
            String lastlog, String currentlog, String aktywny,String admin, String moderator,DataBase _db) {
        this.ID=ID;
        this.login=login;
        this.haslo=haslo;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.www=www;
        this.gg=gg;
        this.jabber=jabber;
        this.tlen=tlen;
        this.wpKontakt=wpKontakt;
        this.icq=icq;
        this.msn=msn;
        this.yahoo=yahoo;
        this.skype=skype;
        this.miasto=miasto;
        this.plec=plec;
        this.avatar=avatar;
        this.sygnaturka=sygnaturka;
        this.styl=styl;
        this.jezyk=jezyk;
        this.dataUrodzenia=dataUrodzenia;
        this.liczbaWypowiedzi=Integer.decode(liczbaWypowiedzi).intValue();
        this.liczbaWatkow=Integer.decode(liczbaWatkow).intValue();
        this.lastlog=lastlog;
        this.currentlog=currentlog;
        if (imieNazwiskoPrywatne.compareTo(DataBase.TAK)==0) this.imieNazwiskoPrywatne=true; else this.imieNazwiskoPrywatne=false;
        if (emailPrywatny.compareTo(DataBase.TAK)==0) this.emailPrywatny=true; else this.emailPrywatny=false;
        if (ggPrywatne.compareTo(DataBase.TAK)==0) this.ggPrywatne=true; else this.ggPrywatne=false;
        if (jabberPrywatny.compareTo(DataBase.TAK)==0) this.jabberPrywatny=true; else this.jabberPrywatny=false;
        if (tlenPrywatny.compareTo(DataBase.TAK)==0) this.tlenPrywatny=true; else this.tlenPrywatny=false;
        if (wpKontaktPrywatny.compareTo(DataBase.TAK)==0) this.wpKontaktPrywatny=true; else this.wpKontaktPrywatny=false;
        if (icqPrywatne.compareTo(DataBase.TAK)==0) this.icqPrywatne=true; else this.icqPrywatne=false;
        if (msnPrywatny.compareTo(DataBase.TAK)==0) this.msnPrywatny=true; else this.msnPrywatny=false;
        if (yahooPrywatne.compareTo(DataBase.TAK)==0) this.yahooPrywatne=true; else this.yahooPrywatne=false;
        if (skypePrywatny.compareTo(DataBase.TAK)==0) this.skypePrywatny=true; else this.skypePrywatny=false;
        if (sygnaturkaPubliczna.compareTo(DataBase.TAK)==0) this.sygnaturkaPubliczna=true; else this.sygnaturkaPubliczna=false;
        if (powiadamianie.compareTo(DataBase.TAK)==0) this.powiadamianie=true; else this.powiadamianie=false;
        if (miastoPrywatne.compareTo(DataBase.TAK)==0) this.miastoPrywatne=true; else this.miastoPrywatne=false;
        if (dataUrodzeniaPrywatna.compareTo(DataBase.TAK)==0) this.dataUrodzeniaPrywatna=true; else this.dataUrodzeniaPrywatna=false;
        if (aktywny.compareTo(DataBase.TAK)==0) this.aktywny=true; else this.aktywny=false;
        if (admin.compareTo(DataBase.TAK)==0) this.admin=true; else this.admin=false;
        if (moderator.compareTo(DataBase.TAK)==0) this.moderator=true; else this.moderator=false;
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
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie id yahoo
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowYahoo() {
        return !yahooPrywatne;
    }
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie id skype
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowSkype() {
        return !skypePrywatny;
    }
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie miasta zamieszkania
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowCity() {
        return !miastoPrywatne;
    }
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie date urodzenia
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowBirthDate() {
        return !dataUrodzeniaPrywatna;
    }
    
    
    
    /** metoda sprawdza czy uzytkownik wyraża zgode na ujawnienie swojej sygnatury
     * @return T lub N w zaleznosci czy user wyraża zgode czy nie
     */
    public boolean ifShowSig() {
        return sygnaturkaPubliczna;
    }
    
    
    /** metoda sprawdza czy uzytkownik chce byc powiadamiany o nowych wiadomościach w jego watkach
     * @return T lub N w zaleznosci czy user chce czy nie
     */
    public boolean ifNotify() {
        return powiadamianie;
    }
    
    
    /** Metoda zwraca sprawdza czy użytkownik jest meżczyzną
     * @return True jeśli użytkownik to meżczyzna
     **/
    public boolean ifMale(){
        return this.plec.compareTo(DataBase.MEZCZYZNA) == 0;
    }
    
    
    /** Metoda zwraca sprawdza czy użytkownik jest kobietą
     * @return True jeśli użytkownik to kobieta
     **/
    public boolean ifFemale(){
        return this.plec.compareTo(DataBase.KOBIETA) == 0;
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
    
    
    /** Metoda zwraca strone www uzytkownika
     * @return String ze stroną www
     **/
    public String getWWW(){
        return this.www;
    }
    
    
    /** Metoda ustawia strone www uzytkownika
     * @param email String ze stroną www użytkownika
     **/
    public void setWWW(String www){
        this.www=www;
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
    
    
    /** Metoda zwraca konto Yahoo uzytkownika
     * @return String z kontem Yahoo
     **/
    public String getYahoo(){
        return this.yahoo;
    }
    
    
    /** Metoda ustawia Yahoo id uzytkownika
     * @param jabber String z id Yahoo
     **/
    public void setYahoo(String yahoo){
        this.yahoo=yahoo;
    }
    
    
    /** Metoda zwraca konto Skype uzytkownika
     * @return String z kontem Skype
     **/
    public String getSkype(){
        return this.skype;
    }
    
    
    /** Metoda ustawia id Skype uzytkownika
     * @param jabber String z id Skype
     **/
    public void setSkype(String skype){
        this.skype=skype;
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
    
    
    /** Metoda zwraca datę urodzenia Uzytkownka (YYYY-MM-DD)
     * @return String z datą
     **/
    public String getBirthDate(){
        return this.dataUrodzenia;
    }
    
    
    /** Metoda zwraca rok urodzenia Uzytkownka
     * @return String z rokiem
     **/
    public String getBirthYear(){
        //dataUrodzenia musi być w formacie YYYY-MM-DD
        return dataUrodzenia.substring(0, 4);
    }
    
    
    /** Metoda zwraca miesiąc urodzenia Uzytkownka
     * @return String z miesiącem
     **/
    public String getBirthMonth(){
        //dataUrodzenia musi być w formacie YYYY-MM-DD
        return dataUrodzenia.substring(5, 7);
    }
    
        
    /** Metoda zwraca dzien miesiaca urodzenia Uzytkownka
     * @return String z dniem
     **/
    public String getBirthDay(){
        //dataUrodzenia musi być w formacie YYYY-MM-DD
        return dataUrodzenia.substring(8, 10);
    }

    
    /** Metoda ustawia datę urodzenia uzytkownika (YYYY-MM-DD)
     * @param jabber String z datą urodzenia
     **/
    public void setBirthDate(String dataUrodzenia){
        this.dataUrodzenia=dataUrodzenia;
    }
    
    
    /** Metoda zwraca płeć użytkownika
     * @return String z płcią
     **/
    public String getSex(){
        return this.plec;
    }
    
    
    /** Metoda ustawia płeć uzytkownika {Database.kobieta, Database.mezczyzna}
     * @param plec String z płcią
     **/
    public void setSex(String plec){
        this.plec=plec;
    }
    
    
    /** Metoda zwraca avatar użytkownika (nazwe pliku w katalogu)
     * @return String z avatarem
     **/
    public String getAvatar(){
        return this.avatar;
    }
    
    
    /** Metoda zwraca sygnaturke użytkownika
     * @return String z sygnaturką
     **/
    public String getSig(){
        return this.sygnaturka;
    }
    
    
    /** Metoda zwraca wtyl wybrany przez użytkownika
     * @return String reprezentujący styl
     **/
    public String getStyle(){
        return this.styl;
    }
    
    
    /** Metoda zwraca jezyk preferowany przez użytkownika
     * @return String reprezentujący wybrany język
     **/
    public String getLang(){
        return this.jezyk;
    }
    
    
    /** Metoda ustawia avatar uzytkownika (nazwe pliku w katalogu)
     * @param avatar String z nazwą pliku
     **/
    public void setAvatar(String avatar){
        this.avatar=avatar;
    }
    
    
    /** Metoda ustawia sygnaturke uzytkownika
     * @param sig String z sygnaturką
     **/
    public void setSig(String sig){
        this.sygnaturka=sig;
    }
    
    
    
    /** Metoda ustawia jezyk uzytkownika
     * @param lang String z wybranym językiem
     **/
    public void setLang(String lang){
        this.jezyk=lang;
    }
    
    
    /** Metoda ustawia styl uzytkownika
     * @param style String z wybranym stylem
     **/
    public void setStyle(String style){
        this.styl=style;
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
    
    
    /** Metoda zwraca liczbe wypowiedzi napisanych przez użytkownika
     * @return int reprezentujący liczbę wypowiedzi
     **/
    public int getLiczbaWypowiedzi(){
        return this.liczbaWypowiedzi;
    }
    
    
    /** Metoda zwraca liczbe watkow utworzonych przez użytkownika
     * @return int reprezentujący liczbę wypowiedzi
     **/
    public int getLiczbaWatkow(){
        return this.liczbaWatkow;
    }
    
    
    /** Metoda wstawia date bierzącego logowania do bazy*/
    public void insertCurrentLog() {
        this.lastlog=this.currentlog;
        this.currentlog=db.getDate();
        db.updateUser(this);
    }
    
    
    /** Metoda ustawia prywatność Imienia i Nazwiska
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setImieNazwiskoPrywatne(boolean prv){
        this.imieNazwiskoPrywatne=prv;
    }
    
    
    /** Metoda ustawia prywatność Emaila
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setEmailPrivate(boolean prv){
        this.emailPrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność numeru gadu-gadu
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setGGPrivate(boolean prv){
        this.ggPrywatne=prv;
    }
    
    
    /** Metoda ustawia prywatność jid
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setJabberPrivate(boolean prv){
        this.jabberPrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność id tlena
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setTlenPrivate(boolean prv){
        this.tlenPrywatny=prv;
    }
    
    /** Metoda ustawia prywatność id wpKontakt
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setWPKontaktPrivate(boolean prv){
        this.wpKontaktPrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność id icq
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setICQPrivate(boolean prv){
        this.icqPrywatne=prv;
    }
    
    
    /** Metoda ustawia prywatność id msn
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setMSNPrivate(boolean prv){
        this.msnPrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność id yahoo
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setYahooPrivate(boolean prv){
        this.yahooPrywatne=prv;
    }
    
    
    /** Metoda ustawia prywatność id skype
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setSkypePrivate(boolean prv){
        this.skypePrywatny=prv;
    }
    
    
    /** Metoda ustawia prywatność daty urodzenia
     * @param prv N w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setBirthDatePrivate(boolean prv){
        this.dataUrodzeniaPrywatna=prv;
    }
    
    
    /** Metoda ustawia publicznosc sygnaturki
     * @param prv T w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setSigPublic(boolean prv){
        this.sygnaturkaPubliczna=prv;
    }
    
    
    /** Metoda ustawia czy powiadamiać użytkownika o zmianach w jego watkach
     * @param nt T w przypadku gdy użytkownik chce być powiadamiany
     **/
    public void setSigNotify(boolean nt){
        this.powiadamianie=nt;
    }
    
    
    /** Metoda ustawia prywatność miasta uzytkownika
     * @param N prv w przypadku gdy chcemy udostepnic dane do widoku innych osób
     **/
    public void setCityPrivate(boolean prv){
        this.miastoPrywatne=prv;
    }
    
    
    /** Metoda zwiększa liczbe wypowiedzi napisanych przez użytkownika **/
    public void incrLiczbaWypowiedzi(){
        this.liczbaWypowiedzi++;
    }
    
    
    /** Metoda zwiększa liczbe watkow utworzonych przez użytkownika **/
    public void incrLiczbaWatkow(){
        this.liczbaWatkow++;
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
