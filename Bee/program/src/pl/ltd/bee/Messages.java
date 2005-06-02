/*
 * Messages.java
 *
 * Created on 24 marzec 2005, 21:52
 */

package pl.ltd.bee;

/**
 * Klasa odpowiada za dostarczenie wszystkich komunikatów kierowanych do użytkownika forum
 * W późniejszym etapie można zaimplementować wyciąganie komunikatów we wskazanym języku z pliku XML
 * @author wilk
 */
public class Messages {
    
    /** Tworzy nową instancję klasy Messages */
    public Messages() {
    }
    
    /** Metoda zmienia pierwszą literą na wielką przekazanego napisu
     * @param s napis do przeróbki
     * @return Zwraca podany parametr ale z pierwszą literą zamienioną na wielką
     */
    public static String wielka(String s){
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
    
    /** Metoda zamienia podany napis na komunikat o błędzie
     * @param s komunikat
     * @return Zwraca podany komunikat zawarty w znacznikach xhtml
     */
    public static String makeError(String s){
        return "<p class=\"error\">"+s+"</p>";
    }
    
    /** Metoda zamienia podany napis na komunikat informacyjny
     * @param s komunikat
     * @return Zwraca podany komunikat zawarty w znacznikach xhtml
     */
    public static String makeInfo(String s){
        return "<p class=\"info\">"+s+"</p>";
    }
    
    /** Metoda zamienia podany napis na komunikat powiadamiający o powodzeniu
     * @param s komunikat
     * @return Zwraca podany komunikat zawarty w znacznikach xhtml
     */
    public static String makeSuccess(String s){
        return "<p class=\"success\">"+s+"</p>";
    }
    
    
    //_______________________________________________________________________________________________
    //                              Metody zwracajace slowa
    //_______________________________________________________________________________________________
    /** Metoda dostarcza łącznika "lub" */
    public static String or(){
        return "lub";}
    
    /** Metoda dostarcza łącznika "i" */
    public static String and(){
        return "i";}
    
    /** Metoda dostarcza słowo "zapamiętaj" */
    public static String save(){
        return "zapamiętaj";}
    
    /** Metoda dostarcza frazę "nowy użytkownik" */
    public static String newUser(){
        return "nowy użytkownik";}
    
    /** Metoda dostarcza słowo "powrót" */
    public static String back(){
        return "powrót";}
    
    /** Metoda dostarcza słowo "witaj" */
    public static String welcome(){
        return "witaj";}
    
    /** Metoda dostarcza słowo "użytkownik" */
    public static String user(){
        return "użytkownik";}
    
    /** Metoda dostarcza frazę "został dodany" */
    public static String hasBeenAdded(){
        return "został dodany";}
    
    /** Metoda dostarcza frazę "dodaj użytkownika" */
    public static String addUser(){
        return "dodaj użytkownika";}
    
    /** Metoda dostarcza słowo "nick" */
    public static String nick(){
        return "nick";}
    
    /** Metoda dostarcza słowo "hasło" */
    public static String password(){
        return "hasło";}
    
    /** Metoda dostarcza frazę "jeszcze raz" */
    public static String oneMoreTime(){
        return "jeszcze raz";}
    
    /** Metoda dostarcza słowo "imię" */
    public static String name(){
        return "imię";}
    
    /** Metoda dostarcza słowo "nazwisko" */
    public static String surname(){
        return "nazwisko";}
    
    /** Metoda dostarcza słowo "e-mail" */
    public static String email(){
        return "e-mail";}
    
    /** Metoda dostarcza słowo "numer" */
    public static String number(){
        return "numer";}
    
    /** Metoda dostarcza słowo "gadu-gadu" */
    public static String gg(){
        return "gadu-gadu";}
    
    /** Metoda dostarcza słowo "jabber" */
    public static String jabber(){
        return "jabber";}
    
    /** Metoda dostarcza słowo "tlen" */
    public static String tlen(){
        return "tlen";}
    
    /** Metoda dostarcza słowo "wpKontakt" */
    public static String wpKontakt(){
        return "wpKontakt";}
    
    /** Metoda dostarcza słowo "icq" */
    public static String icq(){
        return "icq";}
    
    /** Metoda dostarcza słowo "msn" */
    public static String msn(){
        return "msn";}
    
    /** Metoda dostarcza słowo "yahoo" */
    public static String yahoo(){
        return "yahoo";}
    
    /** Metoda dostarcza słowo "skype" */
    public static String skype(){
        return "skype";}
    
    /** Metoda dostarcza słowo "miasto" */
    public static String city(){
        return "miasto";}
    
    /** Metoda dostarcza słowo "płeć" */
    public static String sex(){
        return "płeć";}
    
    /** Metoda dostarcza słowo "meżczyzna" */
    public static String men(){
        return "meżczyzna";}
    
    /** Metoda dostarcza słowo "kobieta" */
    public static String woman(){
        return "kobieta";}
    
    /** Metoda dostarcza frazę "pola wymagane" */
    public static String fieldsObligatory(){
        return "pola wymagane";}
    
    /** Metoda dostarcza słowo "wyślij" */
    public static String send(){
        return "wyślij";}
    
    /** Metoda dostarcza słowo "dodaj" */
    public static String add(){
        return "dodaj";}
    
    /** Metoda dostarcza słowo "wątek" */
    public static String thread(){
        return "wątek";}
    
    /** Metoda dostarcza słowo "tytuł" */
    public static String title(){
        return "tytuł";}
    
    /** Metoda dostarcza słowo "wypowiedź" */
    public static String message(){
        return "wypowiedź";}
    
    /** Metoda dostarcza słowo "autor" */
    public static String author(){
        return "autor";}
    
    /** Metoda dostarcza słowo "treść" */
    public static String tresc(){
        return "treść";}
    
    /** Metoda dostarcza słowo "pomoc" */
    public static String help(){
        return "pomoc";}
    
    /** Metoda dostarcza słowo "szukaj" */
    public static String search(){
        return "szukaj";}
    
    /** Metoda dostarcza słowo "użytkownicy" */
    public static String users(){
        return "użytkownicy";}
    
    /** Metoda dostarcza słowo "grupy" */
    public static String groups(){
        return "grupy";}
    
    /** Metoda dostarcza słowo "przenieś" */
    public static String move(){
        return "przenieś";}
    
    /** Metoda dostarcza słowo "profil" */
    public static String profile(){
        return "profil";}
    
    /** Metoda dostarcza frazę "strona www" */
    public static String wwwPage(){
        return "strona www";}
    
    /** Metoda dostarcza frazę "panel administracyjny" */
    public static String adminPanel(){
        return "panel administracyjny";}
    
    /** Metoda dostarcza słowo "rejestracja" */
    public static String registration(){
        return "rejestracja";}
    
    /** Metoda dostarcza frazę "login" */
    public static String login(){
        return "login";}
    
    /** Metoda dostarcza frazę "zaloguj" */
    public static String logIn(){
        return "zaloguj";}
    
    /** Metoda dostarcza frazę "wyloguj" */
    public static String logOut(){
        return "wyloguj";}
    
    /** Metoda dostarcza słowo "tematy" */
    public static String themes(){
        return "tematy";}
    
    /** Metoda dostarcza słowo "temat" */
    public static String theme(){
        return "temat";}
    
    /** Metoda dostarcza słowo "odpowiedzi" */
    public static String answers(){
        return "odpowiedzi";}
    
    /** Metoda dostarcza słowo "wyświetleń" */
    public static String showed(){
        return "wyświetleń";}
    
    /** Metoda dostarcza frazę "ostatnia wypowiedź" */
    public static String lastPost(){
        return "ostatnia wypowiedź";}
    
    /** Metoda dostarcza frazę "ukryj" */
    public static String hide(){
        return "ukryj";}
    
    /** Metoda dostarcza frazę "stare" */
    public static String old(){
        return "stare";}
    
    /** Metoda dostarcza frazę "ok" */
    public static String ok(){
        return "ok";}
    
    /** Metoda dostarcza frazę "strona główna" */
    public static String mainPage(){
        return "strona główna";}
    
    /** Metoda dostarcza słowo "uprawnienia" */
    public static String priviliges(){
        return "uprawnienia";}
    
    /** Metoda dostarcza słowo "edycja" */
    public static String edition(){
        return "edycja";}
    
    /** Metoda dostarcza słowo "konfiguracja" */
    public static String configuration(){
        return "konfiguracja";}
    
    /** Metoda dostarcza słowo "usunięte" */
    public static String removed(){
        return "usunięte";}
    
    /** Metoda dostarcza frazę "backup" */
    public static String backup(){
        return "backup";}
    
    /** Metoda dostarcza słowo "nr" */
    public static String nr(){
        return "nr";}
    
    /** Metoda dostarcza słowo "administrator" */
    public static String admin(){
        return "administrator";}
    
    /** Metoda dostarcza słowo "moderator" */
    public static String moderator(){
        return "moderator";}
    
    /** Metoda dostarcza słowo "aktywny" */
    public static String active(){
        return "aktywny";}
    
    /** Metoda dostarcza frazę "Zmień uprawnienia" */
    public static String changePriviliges(){
        return "zmień uprawnienia";}
    
    /** Metoda dostarcza frazę "Dane użytkownika" */
    public static String userData(){
        return "dane użytkownika";}
    
    /** Metoda dostarcza słowo "opis" */
    public static String describe(){
        return "opis";}
    
    /** Metoda dostarcza słowo "usuń" */
    public static String remove(){
        return "usuń";}
    
    /** Metoda dostarcza frazę "podfora kategorii" */
    public static String podKat(){
        return "podfora kategorii";}
    
    /** Metoda dostarcza frazę "dodawanie kategorii" */
    public static String addingKat(){
        return "dodawanie kategorii";}
    
    /** Metoda dostarcza słowo "zmień" */
    public static String change(){
        return "zmień";}
    
    /** Metoda dostarcza słowo "czytanie" */
    public static String read(){
        return "czytanie";}
    
    /** Metoda dostarcza słowo "pisanie" */
    public static String write(){
        return "pisanie";}
    
    /** Metoda dostarcza frazę "edycja kategorii" */
    public static String editionKat(){
        return "edycja kategorii";}
    
    /** Metoda dostarcza frazę "dodawanie podfor" */
    public static String addingPod(){
        return "dodawanie podfor";}
    
    /** Metoda dostarcza frazę "edycja podforum" */
    public static String editionPod(){
        return "edycja podforum";}
    
    /** Metoda dostarcza frazę "wybierz kategorie" */
    public static String chooseKat(){
        return "wybierz kategorie";}
    
    /** Metoda dostarcza słowo "rozwiń" */
    public static String rozwin(){
        return "rozwiń";}
    
    /** Metoda dostarcza frazę "pobierz plik z backupem" */
    public static String getBackyupFile(){
        return "pobierz plik z backupem";}
    
    /** Metoda dostarcza frazę "zrób backup" */
    public static String doBackup(){
        return "zrób backup";}
    
    /** Metoda dostarcza frazę "wybierz plik" */
    public static String chooseFile(){
        return "wybierz plik";}
    
    /** Metoda dostarcza frazę "odtwórz bazę danych" */
    public static String recreateDB(){
        return "odtwórz bazę danych";}
    
    /** Metoda dostarcza słowo "aktywuj" */
    public static String activation(){
        return "aktywuj";}
    
    /** Metoda dostarcza frazę "konfiguracja forum" */
    public static String forumConfiguration(){
        return "konfiguracja forum";}
    
    /** Metoda dostarcza słowo "główne" */
    public static String main(){
        return "główne";}
    
    /** Metoda dostarcza frazę "baza danych" */
    public static String db(){
        return "baza danych";}
    
    /** Metoda dostarcza słowo "zachowanie" */
    public static String behave(){
        return "zachowanie";}
    
    /** Metoda dostarcza słowo "wiadomości" */
    public static String mailing(){
        return "wiadomości";}
    
    /** Metoda dostarcza słowo "tak" */
    public static String yes(){
        return "tak";}
    
    /** Metoda dostarcza słowo "nie" */
    public static String no(){
        return "nie";}
    
    /** Metoda dostarcza frazę "url forum" */
    public static String urlForum(){
        return "url forum";}
    
    /** Metoda dostarcza słowo "host" */
    public static String host(){
        return "host";}
    
    /** Metoda dostarcza frazę "nazwa bazy danych" */
    public static String dbName(){
        return "nazwa bazy danych";}
    
    /** Metoda dostarcza frazę "prefix tabel" */
    public static String tablePrefix(){
        return "prefix tabel";}
    
    /** Metoda dostarcza frazę "konto gościa" */
    public static String guestAccount(){
        return "konto gościa";}
    
    /** Metoda dostarcza frazę "identyfikator gościa" */
    public static String guestId(){
        return "identyfikator gościa";}
    
    /** Metoda dostarcza frazę "maksymalny czas trwania sesji" */
    public static String maxSession(){
        return "maksymalny czas trwania sesji";}
    
    /** Metoda dostarcza frazę "minimalna długość hasła" */
    public static String minPassLength(){
        return "minimalna długośc hasła";}
    
    /** Metoda dostarcza frazę "wysyłanie maila w celach autentykacji" */
    public static String newUserMail(){
        return "wysyłanie maila w celach autentykacji";}
    
    /** Metoda dostarcza frazę "SMTP server" */
    public static String smtpServer(){
        return "smtp server";}
    
    /** Metoda dostarcza frazę "adres pocztowy nadawcy" */
    public static String mailFrom(){
        return "adres poczty nadawcy";}
    
    /** Metoda dostarcza frazę "temat rejestracji" */
    public static String registrationTopic(){
        return "temat rejestracji";}
    
    /** Metoda dostarcza frazę "wiadomość po rejestracji" */
    public static String registrationMessage(){
        return "wiadomość po rejestracji";}
    
    /** Metoda dostarcza frazę "temat sekcji zapomniane" */
    public static String forgetTopic(){
        return "temat sekcji zapomniane";}
    
    /** Metoda dostarcza frazę "wiadomość sekcji zapomniane" */
    public static String forgetMessage(){
        return "wiadomość sekcji zapomniane";}
    
    /** Metoda dostarcza frazę "zmień ustawienia forum" */
    public static String applySettings(){
        return "zastosuj ustawienia forum";}
    
    /** Metoda dostarcza słowo "kategotia" */
    public static String category(){
        return "kategoria";}
    
    /** Metoda dostarcza frazę "dodawanie podforów" */
    public static String addPodforums(){
        return "dodawanie podforów";}
    
    /** Metoda dostarcza słowo "next" */
    public static String next(){
        return "next";}
    
    /** Metoda dostarcza słowo "moderatorzy" */
    public static String moderators(){
        return "moderatorzy";}
    
    /** Metoda dostarcza frazę "moderowane podfora" */
    public static String moderatePodforas(){
        return "moderowane podfora";}
    
    /** Metoda dostarcza słowo "podforum" */
    public static String podforum(){
        return "podforum";}
    
    /** Metoda dostarcza słowo "zablokuj" */
    public static String block(){
        return "zablokuj";}
    
    /** Metoda dostarcza słowo "zamknij" */
    public static String close(){
        return "zamknij";}
    
    /** Metoda dostarcza słowo "usuń" */
    public static String delete(){
        return "usuń";}
    
    /** Metoda dostarcza słowo "edytuj" */
    public static String edit(){
        return "edytuj";}
    
    /** Metoda dostarcza słowo "prywatne" */
    public static String privates(){
        return "prywatne";}
    
    /** Metoda dostarcza frazę "używaj ssl" */
    public static String useSsl(){
        return "używaj ssl";}
    
    /** Metoda dostarcza frazę "używaj kompresji" */
    public static String useCompresion(){
        return "używaj kompresji";}
    
    /** Metoda dostarcza słowo "emotikonki" */
    public static String emotikons(){
        return "emotikonki";}
    
    /** Metoda dostarcza słowo "grafika" */
    public static String graphics(){
        return "grafika";}
    
    /** Metoda dostarcza słowo "statystyki" */
    public static String stats(){
        return "statystyki";}
    
    /** Metoda dostarcza frazę "dodawanie emotikone" */
    public static String addingEmotikons(){
        return "dodawanie emotikonek";}
    
    /** Metoda dostarcza frazę "dodawanie grup" */
    public static String addingGroups(){
        return "dodawanie grup";}
    
    /** Metoda dostarcza frazę "użytkownicy grupy" */
    public static String groupUsers(){
        return "użytkownicy grupy";}
    
    
    //_______________________________________________________________________________________________
    //                              Metody zwracajace komunikaty
    //_______________________________________________________________________________________________
    
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isRemoveKat(){
        return "Czy napewno chcesz usunąć kategorie?";}
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isRemoveEmo(){
        return "Czy napewno chcesz usunąć emotikonkę?";}
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isChangeKat(){
        return "Czy napewno chcesz zmienić prywatność kategorii?";}
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isChangePod(){
        return "Czy napewno chcesz zmienić prywatność podforum?";}
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isRemoveGroup(){
        return "Czy napewno chcesz usunąć grupę?";}
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isRemovePod(){
        return "Czy napewno chcesz usunąć podforum?";}
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isRemoveUserGroup(){
        return "Czy napewno chcesz usera z grupy? ";}
    
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isActiveKat(){
        return "Czy napewno chcesz uaktywnić kategorie?";}
    
    /** Metoda dostarcza komunikat z zapytaniem
     * @return String z komunikatem
     */
    public static String isActivePod(){
        return "Czy napewno chcesz uaktywnić podforum?";}
    
    /** Metoda dostarcza komunikat o wyslaniu emaila aktywacyjnego
     * @return String z komunikatem
     */
    public static String messageActivEmailSend(){
        return "Email z linkiem aktywacyjnym został wysłany.";}
    
    
    /** Metoda dostarcza komunikat o wyslaniu emaila z kluczem do zapomnianego hasła
     * @return String z komunikatem
     */
    public static String messageForgetPasswdEmailSend(){
        return "Email z kluczem został wysłany.";}
    
    
    /** Metoda dostarcza komunikat "Czy prywatne?".
     * @return String z komunikatem
     */
    public static String ifPrivate(){
        return "Czy prywatne?";}
    
    
    /** Metoda dostarcza komunikat "Nie pokazuj mojego imienia i nazwiska".
     * @return String z komunikatem
     */
    public static String ifShowName(){
        return "Nie pokazuj mojego imienia i nazwiska";}
    
    
    /** Metoda dostarcza komunikat "Nowe hasło".
     * @return String z komunikatem
     */
    public static String newPassword(){
        return "Nowe hasło";}
    
    
    /** Metoda dostarcza komunikat "Zapomniane hasło"
     * @return String z komunikatem
     */
    public static String forgetPasswd(){
        return "Zapomniane hasło";}
    
    
    /** Metoda dostarcza komunikat "Hasła się nie zgadzają"
     * @return String z komunikatem
     */
    public static String passwordNotMatch(){
        return "Hasła się nie zgadzają";}
    
    
    /** Metoda dostarcza komunikat "Stare hasło się nie zgadza"
     * @return String z komunikatem
     */
    public static String oldPasswordNotMatch(){
        return "Stare hasło się nie zgadza";}
    
    
    /** Metoda dostarcza komunikat "Hasło powinno mieć przynajmniej Config.MIN_PASSWD znaków"
     * @return String z komunikatem
     */
    public static String passwordTooShort(){
        return "Hasło powinno mieć przynajmniej " + new String().valueOf(Config.MIN_PASSWD) +" znaków";}
    
    
    /** Metoda dostarcza frazę "data urodzenia" */
    public static String birthdate(){
        return "data urodzenia";}
    
    
    /** Metoda dostarcza komunikat "Ostatnio zalogowany"
     * @return String z komunikatem
     */
    public static String lastLogged(){
        return "Ostatnio zalogowany";}
    
    
    /** Metoda dostarcza komunikat "Zaloguj się"
     * @return String z komunikatem
     */
    public static String logInYourself(){
        return "Zaloguj się";}
    
    
    /** Metoda dostarcza komunikat "Zarejestruj się"
     * @return String z komunikatem
     */
    public static String registerYourself(){
        return "Zarejestruj się";}
    
    
    /** Metoda dostarcza komunikat "Nie masz jeszcze konta?"
     * @return String z komunikatem
     */
    public static String doNotHaveAccount(){
        return "Nie masz jeszcze konta?";}
    
    
    /** Metoda dostarcza komunikat "Zapomniałem hasła"
     * @return String z komunikatem
     */
    public static String iForgetPasswd(){
        return "Zapomniałem hasła";}
    
    
    /** Metoda dostarcza komunikat "Hasło zmieniono"
     * @return String z komunikatem
     */
    public static String passwordChanged(){
        return "Hasło zmieniono";}
    
    
    /**
     * Metoda dostarcza komunikat błedu o połączeniu z bazą danych
     * @return String z komunikatem o braku połączenia z bazą danych
     */
    public static String errorDataBaseConnection(){
        return "Błąd połączenia z bazą danych!";}
    
    
    /**
     * Metoda dostarcza komunikat błedu informujacego że użytkownik nie może zostać stworzony
     * @return String z komunikatem o tym że użytkownik nie może zostać stworzony
     */
    public static String errorUserCreate(){
        return "Nie można dodać użytkownika - skontaktuj się z administratorem forum";}
    
    
    /**
     * Metoda dostarcza komunikat błedu informujacego że podany użytkownik już istnieje
     * @param nick String z nazwa użytkownika
     * @return String z komunikatem o tym że podany użytkownik już istnieje
     */
    public static String errorUserExists(String nick){
        return "Użytkownik " + nick + " już istnieje!";}
    
    
    /**
     * Metoda dostarcza komunikat błedu informujacego że pole jest wymagane
     * @return String z komunikatem o tym że pole jest wymagane
     */
    public static String errorFieldNeeded(){
        return "Pole jest wymagane!";}
    
    
    /**
     * Metoda dostarcza komunikat błedu niewystarczajacych uprawnień
     * @return String z komunikatem o braku wystarczajacych uprawnień
     */
    public static String errorPermissionDenied(){
        return "Nie wystarczające uprawnienia";}
    
    /**
     * Metoda dostarcza komunikat błedu informujacego że pole jest wymagane
     * @param field String z nazwa pola
     * @return String z komunikatem o tym że pole jest wymagane
     */
    public static String errorFieldNeeded(String field){
        return "Pole '" + field + "'jest wymagane!";}
    
    /**
     * Metoda dostarcza komunikat błedu informujacy że hasło jest za krótkie
     * @param length liczba wyznaczająca minimalną długość hasła
     * @return String z komunikatem o tym że hasło jest za krótkie
     */
    public static String errorPassToShort(int length){
        return "Hasło musi mieć przynajmniej " + length + " znaków";}
    
    
    /**
     * Metoda dostarcza komunikat błedu informujacy że hasła nie są identyczne
     * @return String z komunikatem o tym że hasła nie są identyczne
     */
    public static String errorPassNotMatch(){
        return "Hasła się nie zgadzają";}
    
    /**
     * Metoda dostarcza komunikat błedu informujacego o błędzie logowania
     * @return String z komunikatem o błędzie logowania
     */
    public static String errorBadUserOrPass(){
        return "Błędny użytkownik lub hasło!";}
    
    
    /**
     * Metoda dostarcza komunikat błedu nieokreślonego
     * @return String z komunikatem o błędzie nieokreślonym
     */
    public static String errorUnknown(){
        return "Błąd! skontaktuj się z administratorem strony!";}
    
    
    /**
     * Metoda dostarcza komunikat o nie istnieniu wątku
     * @return String z komunikatem o nie istnieniu wątku
     */
    public static String errorThreadNotExists(){
        return "Brak takiego wątku!";    }
    
    
    /**
     * Metoda dostarcza komunikat o nie istnieniu kategorii
     * @return String z komunikatem o nie istnieniu kategorii
     */
    public static String errorCategoryNotExists(){
        return "Brak takiej kategorii!";    }
    
    
    /**
     * Metoda dostarcza komunikat o nie istnieniu podforum
     * @return String z komunikatem o nie istnieniu podforum
     */
    public static String errorSubForumNotExists(){
        return "Brak takiego podforum!";    }
    
    
    /**
     * Metoda dostarcza komunikat o błędzie odczytu pliku konfiguracyjnego
     * @return String z komunikatem o błędzie odczytu pliku konfiguracyjnego
     */
    public static String errorXMLRead(){
        return "Błąd odczytu pliku konfiguracyjnego.";}
    
    
    /**
     * Metoda dostarcza komunikat o błędzie zapisu pliku konfiguracyjnego
     * @return String z komunikatem o błędzie zapisu pliku konfiguracyjnego
     */
    public static String errorXMLWrite(){
        return "Błąd zapisu pliku konfiguracyjnego.";}
    
    
    /**
     * Metoda dostarcza komunikat o błędzie klucza nowego użytkownika
     * @return String z komunikatem o błędzie klucza nowego użytkownika
     */
    public static String errorKeyNewUser(){
        return "Błędny klucz.";}
    
    
    /**
     * Metoda dostarcza komunikat o błędzie zmiany hasła
     * @return String z komunikatem o błędzie zmiany hasła
     */
    public static String errorChangePasswd(){
        return "Nie można zmienić hasła, skontaktuj się z administratorem forum.";}
    
    
    /**
     * Metoda dostarcza komunikat o istniejącym już użytkowniku z takim emailem
     * @return String z komunikatem o istniejącym już użytkowniku z takim emailem
     */
    public static String errorEmailExists(){
        return "Użytkownik ma już konto w serwisie.";}
    
    
    /**
     * Metoda dostarcza komunikat o istniejącej już nazwie kategorii
     * @return String z komunikatem
     */
    public static String errorNameKat(){
        return "Kategoria o podanej nazwie już istnieje.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że należy podać nazwę kategorii
     * @return String z komunikatem
     */
    public static String errorFieldNameKat(){
        return "Należy podać nazwę kategorii.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że dodanie kategorii nie powiodło się
     * @return String z komunikatem
     */
    public static String errorAddKat(){
        return "Dodanie kategorii nie powiodło się.";}
    
    
    /**
     * Metoda dostarcza komunikat o istniejącej już nazwie grupy
     * @return String z komunikatem
     */
    public static String errorNameGroup(){
        return "Grupa o podanej nazwie już istnieje.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że należy podać nazwę grupy
     * @return String z komunikatem
     */
    public static String errorFieldNameGroup(){
        return "Należy podać nazwę grupy.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że dodanie grupy nie powiodło się
     * @return String z komunikatem
     */
    public static String errorAddGroup(){
        return "Dodanie grupy nie powiodło się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana kategorii nie powiodła się
     * @return String z komunikatem
     */
    public static String errorChangeKat(){
        return "Zmiana kategorii nie powiodła się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana podforum nie powiodła się
     * @return String z komunikatem
     */
    public static String errorChangePod(){
        return "Zmiana podforum nie powiodła się.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana prywaności się nie powiodła
     * @return String z komunikatem
     */
    public static String errorChangePrivates(){
        return "Zmiana pól prywatne nie powiodła się.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana uprawnień nie powiodła się
     * @return String z komunikatem
     */
    public static String errorChangeUpr(){
        return "Zmiana uprawnien nie powiodła się.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana prywaności się powiodła
     * @return String z komunikatem
     */
    public static String changePrivates(){
        return "Zmiana pól prywatne powiodła się.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że dodanie kategorii powiodło się
     * @return String z komunikatem
     */
    public static String addKat(){
        return "Kategoria została dodana.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że dodanie grupy powiodło się
     * @return String z komunikatem
     */
    public static String addGroup(){
        return "Grupa została dodana.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana uprawnień powiodła się
     * @return String z komunikatem
     */
    public static String changeUpr(){
        return "Uprawnienia zostały zmienione.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana podforum powiodła sie
     * @return String z komunikatem
     */
    public static String changePod(){
        return "Podforum zostało zmienione.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że usuniecie kategorii powiodło sie
     * @return String z komunikatem
     */
    public static String removeKat(){
        return "Kategoria została usunięta.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że aktywacja kategorii powiodła sie
     * @return String z komunikatem
     */
    public static String activeKat(){
        return "Kategoria została aktywowana.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana kategorii powiodła się.
     * @return String z komunikatem
     */
    public static String changeKat(){
        return "Kategoria została zmieniona.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że usunięcie podforum powiodło się.
     * @return String z komunikatem
     */
    public static String removePodforum(){
        return "Podforum zostało usunięte.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że usunięcie grupy powiodło się.
     * @return String z komunikatem
     */
    public static String removeGroup(){
        return "Grupa została usunięta.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że aktywacja podforum powiodła się.
     * @return String z komunikatem
     */
    public static String activePodforum(){
        return "Podforum zostało aktywowane.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że dodanie usera do grupy powiodło się.
     * @return String z komunikatem
     */
    public static String addUserGroup(){
        return "User został dodany do grupy.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że usunięcie usera z grupy powiodło się.
     * @return String z komunikatem
     */
    public static String removeUserGroup(){
        return "User został dodany usunięty z grupy.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że usunięcie kategorii nie powiodło się
     * @return String z komunikatem
     */
    public static String errorRemoveKat(){
        return "Usunięcie kategorii nie powiodło się.";}
    
    /**
     * Metoda dostarcza komunikat o tym, usunięcie grupy nie powiodło się
     * @return String z komunikatem
     */
    public static String errorRemoveGroup(){
        return "Usunięcie grupy nie powiodło się.";}
    
    /**
     * Metoda dostarcza komunikat o tym, usunięcie usera nie powiodło się
     * @return String z komunikatem
     */
    public static String errorRemoveUserGroup(){
        return "Usunięcie usera nie powiodło się.";}
    
    /**
     * Metoda dostarcza komunikat o tym, dodanie usera nie powiodło się
     * @return String z komunikatem
     */
    public static String errorAddUserGroup(){
        return "Dodanie usera nie powiodło się.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że aktywacja kategorii nie powiodła się
     * @return String z komunikatem
     */
    public static String errorActiveKat(){
        return "Aktywacja kategorii nie powiodła się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, usunięcie podforum nie powiodło się
     * @return String z komunikatem
     */
    
    public static String errorRemovePodforum(){
        return "Usunięcie podforum nie powiodło się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że nie została podana nazwa podforum
     * @return String z komunikatem
     */
    
    public static String errorFieldNamePodforum(){
        return "Nie została podana nazwa podforum.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że aktwacja podforum nie powiodła się
     * @return String z komunikatem
     */
    
    public static String errorActivePodforum(){
        return "Aktywacja podforum nie powiodła się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że podforum o podanej nazwie już istnieje.
     * @return String z komunikatem
     */
    public static String errorNamePodforum(){
        return "Podforum o podanej nazwie już istnieje.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że dodanie podforum nie powiodło się.
     * @return String z komunikatem
     */
    public static String errorAddPodforum(){
        return "Dodanie podforum nie powiodło się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że użytkownik nie jest zalogowany.
     * @return String z komunikatem
     */
    public static String errorNotLoggedIn(){
        return "Proszę się zalogować.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że dodanie podforum powiodło się
     * @return String z komunikatem
     */
    public static String addPodforum(){
        return "Podforum zostało dodane.";}
    
    /**
     * Metoda dostarcza komunikat o tym, ze zmiana konfiguracji głównej powiodła się
     * @return String z komunikatem
     */
    public static String changeConfigMain(){
        return "Konfiguracja główna została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana konfiguracji bazy danych powiodła się
     * @return String z komunikatem
     */
    public static String changeConfigDb(){
        return "Konfiguracja bazy danych została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana konfiguracji zachowania forum powiodła się
     * @return String z komunikatem
     */
    public static String changeConfigBehave(){
        return "Konfiguracja zachowania forum została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana konfiguracji emotikonek forum powiodła się
     * @return String z komunikatem
     */
    public static String changeConfigEmotikons(){
        return "Konfiguracja emotikonek została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana konfiguracji mailingu powiodła się
     * @return String z komunikatem
     */
    public static String changeConfigMail(){
        return "Konfiguracja mailingu została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o błędzie wywołania formularza
     * @return String z komunikatem
     */
    public static String formError(){
        return "Błąd formularza.";}
    
    
    /**
     * Metoda dostarcza komunikat o błędzie wczytywania avatara
     * @return String z komunikatem
     */
    public static String avatarError(){
        return "Obrazek nieprawidłowy! Proszę podać plik graficzny o rozmiarach " + Config.AVATAR_WIDTH + "x" + Config.AVATAR_HEIGHT + " nienwiększy niż " + Config.AVATAR_SIZE/1000 + " kbajtów." ;}
    
    
    /**
     * Metoda dostarcza komunikat o poprawnym dodaniu wypowiedzi
     * @return String z komunikatem
     */
    public static String addedMessage(){
        return "Dodano Wypowiedź";}
    
    
    /**
     * Metoda dostarcza komunikat o poprawnym dodaniu wątka
     * @return String z komunikatem
     */
    public static String addedThread(){
        return "Dodano Wątek";}
    
    
    /**
     * Metoda dostarcza komunikat "Edycja Profilu"
     * @return String z komunikatem
     */
    public static String editProfile(){
        return "Edycja Profilu";}
    
    
    /**
     * Metoda dostarcza komunikat "Dane zaktualizowano"
     * @return String z komunikatem
     */
    public static String updated(){
        return "Dane zaktualizowano";}
    
    /**
     * Metoda dostarcza komunikat "Operacja powiodła się"
     * @return Komunikat o poprawnym przebiegu operacji
     */
    public static String actionDone(){
        return "operacja powiodła się";}
    
    /**
     * Metoda dostarcza komunikat "przenieś wątek"
     * @return Komunikat o poprawnym przebiegu operacji
     */
    public static String moveThread(){
        return "przenieś wątek";}
    
    /**
     * Metoda dostarcza komunikat "czy na pewno ?"
     * @return Pytanie czy na pewno wykonać daną operację
     */
    public static String areYouSure(){
        return "czy na pewno ?";}
    
    
    /**
     * Metoda dostarcza słowo "akcje"
     * @return słowo "akcje"
     */
    public static String actions(){
        return "akcje";}
    
    /**
     * Metoda dostarcza frazę "Banuj Autora"
     * @return string reprezentujący frazę
     */
    public static String banAuthor(){
        return "Banuj Autora";}
    
    
    /** Metoda dostarcza frazę "szukana fraza"
     *  @return String zawierający frazę
     */
    public static String searchingWords(){
        return "szukana fraza";
    }
    
    
    /** Metoda dostarcza frazę "szukaj w wypowiedziach"
     *  @return String zawierający frazę
     */
    public static String searchInMessages(){
        return "szukana fraza";
    }
    
    
    /** Metoda dostarcza frazę "Liczba utworzonych wątków"
     *  @return String zawierający frazę
     */
    public static String numberCreatedThreads(){
        return "Liczba utworzonych wątków";
    }
    
    
    /** Metoda dostarcza frazę "Liczba napisanych wypowiedzi"
     *  @return String zawierający frazę
     */
    public static String numberWroteMessages(){
        return "Liczba napisanych wypowiedzi";
    }
    
    //_______________________________________________________________________________________________
    //                              Metody zwracajace komunikaty
    //_______________________________________________________________________________________________
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla operacji edytowania wypowiedzi
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintEditMessages(){
        return "<img src=&quot;./images/edit.gif&quot; alt=&quot;" + Messages.wielka(Messages.edit())+"&quot;/>&nbsp;Operacja pozwala edytować treść wskazanej wypowiedzi. Aby móc edytować wypowiedź należy być zalogowanym użytkownikiem forum oraz albo być właścicielem wypowiedzi albo być moderatorem podforum, w którym znajduje się wypowiedź.";
    }
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla operacji banowania uzytkownika na podanym podforum
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintBanAuthor(){
        return "<img src=&quot;./images/kick_user.gif&quot; alt=&quot;" + Messages.wielka(Messages.banAuthor())+"&quot;/>&nbsp;Operacja powoduje &quot;zabanowanie&quot; użytkownika forum. Spowoduje to zablokowanie możliwości wypowiadania się danego użytkownika na podforum, w którym znajduje się dana wypowiedź. Aby móc wykonać tą operację należy być zalogowanym użytkownikiem forum oraz być moderatorem podforum, w którym znajduje się wątek.";
    }
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla operacji kasowania wypowiedzi
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintDeleteMessage(){
        return "<img src=&quot;./images/delete.gif&quot; alt=&quot;" + Messages.wielka(Messages.delete())+"&quot;/>&nbsp;Operacja powoduje skasowanie wypowiedzi. Nie będzie ona później dostępna. Aby móc wykonać tą operację należy być zalogowanym użytkownikiem forum oraz być moderatorem podforum, w którym znajduje się wypowiedź.";
    }
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla operacji przenoszenia wątku
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintMoveThread(){
        return "<img src=&quot;./images/move.gif&quot; alt=&quot;" + Messages.wielka(Messages.move()) + "&quot;/>&nbsp;Operacja powoduje próbę przeniesienia wątku do innego podforum. Aby móc wykonać tą operację należy być zalogowanym użytkownikiem forum oraz być moderatorem podforum, w którym znajduje się wątek, a także moderatorem podforum docelowego.";
    }
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla operacji blokowania wątku
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintBlockThread(){
        return "<img src=&quot;./images/lock.png&quot; alt=&quot;" + Messages.wielka(Messages.block())+"&quot;/>&nbsp;Operacja powoduje zablokowanie wątku. Dalsza dyskusja nie będzie możliwa. Aby móc wykonać tą operację należy być zalogowanym użytkownikiem forum oraz być moderatorem podforum, w którym znajduje się wątek.";
    }
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla operacji zamknięcia wątku
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintCloseThread(){
        return "<img src=&quot;./images/cut.gif&quot; alt=&quot;" + Messages.wielka(Messages.close())+"&quot;/>&nbsp;Operacja powoduje zamknięcie wątku. Dalsza dyskusja nie będzie możliwa. Aby móc wykonać tą operację należy być zalogowanym użytkownikiem forum oraz być moderatorem podforum, w którym znajduje się wątek.";
    }
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla wskaźnika statusu jabber
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintJabberStatus(){
        return "Wskaźnik statusu dla sieci Jabber. Aby wskaźnik działał należy wysłać autoryzację do kontaktu jrky2@jabber.org";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi informującej o potrzebie zalogowania się
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintPleaseLogin(){
        return "<img src=&quot;./images/stop.gif&quot; alt=&quot;" + Messages.wielka(Messages.logInYourself())+"&quot;/>&nbsp;Proszę się najpierw zalogować.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla loginu
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintLogin(){
        return "Login użytkownika - będzie służył do identyfikacji użytkownika na forum, a także pojawiał się przy kazdej wypowiedzi napisanej przez użytkownika. Login musi być unikalny.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla imienia
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintName(){
        return "Imię użytkownika";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla nazwisko
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintSurname(){
        return "Nazwisko użytkownika";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla email
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintEmail(){
        return "Adres e-mail użytkownika. Należy wpisać poprawny adres email - przy wyświetlaniu adresu na stronie będzie on zabezpieczony filtrem antyspamowym.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla WWW
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintWWW(){
        return "Prywatna strona www użytkownika. Po wpisaniu tej wartości będzie się ona pojawiać zawsze w wizytówce użytkownika.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla ukrywania danych
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintHide(){
        return "Zaznaczenie tej opcji spowoduje ukrycie danych przed innymi użytkownikami. Oznacza to że wartość nie pojawi się na publicznej wizytówce.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla gg
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintGG(){
        return "Numer gadu-gadu. Wpisanie tej wartości pozwoli na obserwowanie Twojego statusu przez innych użytkowników.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Jabbera
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintJabber(){
        return "Identyfikator jabbera. Wpisanie tej wartości pozwoli na obserwowanie Twojego statusu przez innych użytkowników.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla wpKontakt
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintTlen(){
        return "Identyfikator Tlen. Wpisanie tej wartości pozwoli na obserwowanie Twojego statusu przez innych użytkowników.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla WPKontakt
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintWPKontakt(){
        return "Identyfikator WPKontakt. Wpisanie tej wartości pozwoli na obserwowanie Twojego statusu przez innych użytkowników.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla ICQ
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintICQ(){
        return "Identyfikator ICQ. Wpisanie tej wartości pozwoli na obserwowanie Twojego statusu przez innych użytkowników.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla MSN
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintMSN(){
        return "Identyfikator MSN. Wpisanie tej wartości pozwoli na obserwowanie Twojego statusu przez innych użytkowników.";
    }
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Yahoo
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintYahoo(){
        return "Identyfikator Yahoo. Wpisanie tej wartości pozwoli na obserwowanie Twojego statusu przez innych użytkowników.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Skype
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintSkype(){
        return "Identyfikator Skype. Wpisanie tej wartości pozwoli na obserwowanie Twojego statusu przez innych użytkowników.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Miasta, z którego pochodzi uzytkownik
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintCity(){
        return "Miasto, z którego pochodzi użytkownik.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Daty Urodzenia
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintBirthDate(){
        return "Data urodzenia, wykorzystywana min. przy generowaniu urodzin użytkowników.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Roku urodzenia
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintBirthYear(){
        return "Rok urodzenia";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Miesiąca Urodzenia
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintBirthMonth(){
        return "Miesiąc urodzenia";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Dnia Urodzenia
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintBirthDay(){
        return "Dzień urodzenia";
    }
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla płci użytkownika
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintSex(){
        return "Płeć użytkownika";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla tematu
     * @return String zawierający wycinek dokumentu HTML
     */
    public static String hintTheme(){
        return "Temat wyglądu. Po zalogowaniu temat zostanie zmieniony na wybrany.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla starego hasła
     * @return String zawierajacy wycinek dokumentu HTML
     */
    public static String hintOldPassword(){
        return "Pole zawiera aktualne hasło użytkownika, aby móc zmienić hasło należy podać stare.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla hasła
     * @return String zawierajacy wycinek dokumentu HTML
     */
    public static String hintPassword(){
        return "Pole zawiera hasło użytkownika, hasło powinno być mieć nie mniej niż " + Config.MIN_PASSWD + " znaków.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla hasła
     * @return String zawierajacy wycinek dokumentu HTML
     */
    public static String hintPasswordOnceAgain(){
        return "Aby uniemożliwić pomyłkę przy zmianie hasła, należy podać je jeszcze raz.";
    }
    
    
    /** Metoda dostarcza zawartość chmurki podpowiedzi dla Avatarka
     * @return String zawierajacy wycinek dokumentu HTML
     */
    public static String hintAvatar(){
        return "Obrazek który będzie pojawiał się w wizytówce użytkownika,a także przy wszystkich jego wypowiedziach. Plik reprezentujący obrazek nie może być rozmiaru większego niż " + Config.AVATAR_WIDTH + "x" + Config.AVATAR_HEIGHT + " i zajmować więcej niż " + Config.AVATAR_SIZE/1000 + "bajtów.";
    }
    
    
    /** Metoda dostarcza komunikat o nie istnieniu poszukiwanego zasobu na stronie
     * @return Zwraca łańcuch znaków, którego treść informuje o braku żądanego zasobu
     */
    public static String error404(){
        return "Przepraszamy, ale poszykiwana przez Ciebie strona nie istnieje.";
    }
    
    /** Metoda dostarcza komunikat o braku uprawnień do strony
     * @return Zwraca łańcuch znaków, którego treść informuje o braku praw dostępu
     */
    public static String error403(){
        return "Przepraszamy, ale nie masz uprawnień aby wyświetlić poszykiwaną przez Ciebie stronę.";
    }
    
    /** Metoda dostarcza komunikat o błędzie po stronie serwera
     * @return Zwraca łańcuch znaków, którego treść informuje o wystąpieniu błędu na serwerze
     */
    public static String error500(){
        return "Przepraszamy, ale wystąpił błąd serwera. Prosimy spróbować za kilka chwil. Jeśli problem nie zniknie prosimy skontaktować się z administratorem forum.";
    }
}
