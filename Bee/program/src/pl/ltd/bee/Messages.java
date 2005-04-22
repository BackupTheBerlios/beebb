/*
 * Messages.java
 *
 * Created on 24 marzec 2005, 21:52
 */

package pl.ltd.bee;

/**
 * Klasa odpowiada za dostarczenie wszystkich komunikatow kierowanych do uzytkownika forum
 * W pozniejszym etapie mozna zaimplementowac wyciaganie komunikatow we wskazanym jezyku z pliku XML
 * @author wilk
 */
public class Messages {
    
    /** Creates a new instance of Messages */
    public Messages() {
    }
    
    /** Metoda zmienia pierwsza litere na wilka przekazanego napisu
     * @param s Napis do przerobki
     * @return Zwraca podany parametr ale z pierwsza litera zamieniona na wielka
     */
    public static String wielka(String s){
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
    
    /** Metoda zamienia podany napis na komunikat o bledzie
     * @param s Komunikat
     * @return Zwraca podany komunikat zawarty w znacznikach xhtml
     */
    public static String makeError(String s){
        return "<p class=\"error\">"+s+"</p>";
    }
    
    /** Metoda zamienia podany napis na komunikat informacyjny
     * @param s Komunikat
     * @return Zwraca podany komunikat zawarty w znacznikach xhtml
     */
    public static String makeInfo(String s){
        return "<p class=\"info\">"+s+"</p>";
    }
    
    
    
    //_______________________________________________________________________________________________
    //                              Metody zwracajace slowa
    //_______________________________________________________________________________________________
    /** Metoda dostarcza lacznika "lub" */
    public static String or(){
        return "lub";}
    
    /** Metoda dostarcza fraze nowy uzytkownik */
    public static String newUser(){
        return "nowy użytkownik";}
    
    /** Metoda dostarcza slowa "powrot" */
    public static String back(){
        return "powrót";}
    
    /** Metoda dostarcza slowo "witaj" */
    public static String welcome(){
        return "witaj";}
    
    /** Metoda dostarcza slowo "uzytkownik" */
    public static String user(){
        return "użytkownik";}
    
    /** Metoda dostarcza fraze "zostal dodany" */
    public static String hasBeenAdded(){
        return "został dodany";}
    
    /** Metoda dostarcza fraze "dodaj uzytkownika" */
    public static String addUser(){
        return "dodaj użytkownika";}
    
    /** Metoda dostarcza slowo "nick" */
    public static String nick(){
        return "nick";}
    
    /** Metoda dostarcza slowo "haslo" */
    public static String password(){
        return "hasło";}
    
    /** Metoda dostarcza fraze "jeszcze raz" */
    public static String oneMoreTime(){
        return "jeszcze raz";}
    
    /** Metoda dostarcza slowo "imie" */
    public static String name(){
        return "imię";}
    
    /** Metoda dostarcza slowo "nazwisko" */
    public static String surname(){
        return "nazwisko";}
    
    /** Metoda dostarcza slowo "e-mail" */
    public static String email(){
        return "e-mail";}
    
    /** Metoda dostarcza słowo "numer" */
    public static String number(){
        return "numer";}
    
    /** Metoda dostarcza slowo "gadu-gadu" */
    public static String gg(){
        return "gadu-gadu";}
    
    /** Metoda dostarcza slowo "jabber" */
    public static String jabber(){
        return "jabber";}
    
    /** Metoda dostarcza fraze "pola wymagane" */
    public static String fieldsObligatory(){
        return "pola wymagane";}
    
    /** Metoda dostarcza slowo "wyslij" */
    public static String send(){
        return "wyślij";}
    
    /** Metoda dostarcza slowo "dodaj" */
    public static String add(){
        return "dodaj";}
    
    /** Metoda dostarcza slowo "wątek" */
    public static String thread(){
        return "wątek";}
    
    /** Metoda dostarcza slowo "Tytuł" */
    public static String title(){
        return "tytuł";}
    
    /** Metoda dostarcza slowo "wypowiedź" */
    public static String message(){
        return "wypowiedź";}
    
    /** Metoda dostarcza slowo "autor" */
    public static String author(){
        return "autor";}
    
    /** Metoda dostarcza slowo "treść" */
    public static String tresc(){
        return "treść";}
    
    /** Metoda dostarcza slowo "pomoc" */
    public static String help(){
        return "pomoc";}
    
    /** Metoda dostarcza slowo "szukaj" */
    public static String search(){
        return "szukaj";}
    
    /** Metoda dostarcza slowo "uzytkownicy" */
    public static String users(){
        return "użytkownicy";}
    
    /** Metoda dostarcza slowo "grupy" */
    public static String groups(){
        return "grupy";}
    
    /** Metoda dostarcza slowo "profil" */
    public static String profile(){
        return "profil";}
    
    /** Metoda dostarcza fraze "panel administracyjny" */
    public static String adminPanel(){
        return "panel administracyjny";}
    
    /** Metoda dostarcza slowo "rejestracja" */
    public static String registration(){
        return "rejestracja";}
    
    /** Metoda dostarcza fraze "login" */
    public static String login(){
        return "login";}
    
    /** Metoda dostarcza fraze "zaloguj" */
    public static String logIn(){
        return "zaloguj";}
    
    /** Metoda dostarcza fraze "wyloguj" */
    public static String logOut(){
        return "wyloguj";}
    
    /** Metoda dostarcza slowo "tematy" */
    public static String themes(){
        return "tematy";}
    
    /** Metoda dostarcza slowo "odpowiedzi" */
    public static String answers(){
        return "odpowiedzi";}
    
    /** Metoda dostarcza slowo "wyswietlen" */
    public static String showed(){
        return "wyświetleń";}
    
    /** Metoda dostarcza fraze "ostatnia wypowiedz" */
    public static String lastPost(){
        return "ostatnia wypowiedź";}
    
    /** Metoda dostarcza fraze "ukryj" */
    public static String hide(){
        return "ukryj";}
    
    /** Metoda dostarcza fraze "stare" */
    public static String old(){
        return "stare";}
    
    /** Metoda dostarcza fraze "ok" */
    public static String ok(){
        return "ok";}
    
   /** Metoda dostarcza fraze "strona główna" */
    public static String mainPage(){
        return "strona główna";}
    
   /** Metoda dostarcza fraze "uprawnienia" */
    public static String priviliges(){
        return "uprawnienia";}
    
     /** Metoda dostarcza fraze "edycja" */
    public static String edition(){
        return "edycja";}
    
   /** Metoda dostarcza fraze "konfiguracja" */
    public static String configuration(){
        return "konfiguracja";}
    
   /** Metoda dostarcza fraze "usunięte" */
    public static String removed(){
        return "usunięte";}
    
   /** Metoda dostarcza fraze "backup" */
    public static String backup(){
        return "backup";}
    
  /** Metoda dostarcza fraze "nr." */
    public static String nr(){
        return "nr.";}
      
  /** Metoda dostarcza fraze "administrator" */
    public static String admin(){
        return "administrator";}
    
   /** Metoda dostarcza fraze "moderator" */
    public static String moderator(){
        return "moderator";}
    
  /** Metoda dostarcza fraze "aktywny" */
    public static String active(){
        return "aktywny";}
    
  /** Metoda dostarcza fraze "Zmień uprawnienia" */
    public static String changePriviliges(){
        return "Zmień uprawnienia";}
    
 /** Metoda dostarcza fraze "Dane użytkownika" */
    public static String userData(){
        return "Dane użytkownika";}
    
 /** Metoda dostarcza fraze "opis" */
    public static String describe(){
        return "opis";}
    
 /** Metoda dostarcza fraze "usuń" */
    public static String remove(){
        return "usuń";}
    
  /** Metoda dostarcza fraze "podfora kategorii" */
    public static String podKat(){
        return "podfora kategorii";}
    
 /** Metoda dostarcza fraze "dodawanie kategorii" */
    public static String addingKat(){
        return "dodawanie kategorii";}
    
 /** Metoda dostarcza fraze "zmień" */
    public static String change(){
        return "zmień";}
    
 /** Metoda dostarcza fraze "edycja kategorii" */
    public static String editionKat(){
        return "edycja kategorii";}
    
 /** Metoda dostarcza fraze "edycja podforum" */
    public static String editionPod(){
        return "edycja podforum";}
    
 /** Metoda dostarcza fraze "wybierz kategorie" */
    public static String chooseKat(){
        return "wybierz kategorie";}
    
 /** Metoda dostarcza fraze "rozwiń" */
    public static String rozwin(){
        return "rozwiń";}
    
  /** Metoda dostarcza fraze "pobierz plik z backupem" */
    public static String getBackyupFile(){
        return "pobierz plik z backupem";}
    
  /** Metoda dostarcza fraze "zrób backup" */
    public static String doBackup(){
        return "zrób backup";}
    
  /** Metoda dostarcza fraze "wybierz plik" */
    public static String chooseFile(){
        return "wybierz plik";}
    
  /** Metoda dostarcza fraze "odtwórz bazę danych" */
    public static String recreateDB(){
        return "odtwórz bazę danych";}
    
  /** Metoda dostarcza fraze "aktywuj" */
    public static String activation(){
        return "aktywuj";}
    
  /** Metoda dostarcza fraze "konfiguracja forum" */
    public static String forumConfiguration(){
        return "konfiguracja forum";}
    
  /** Metoda dostarcza fraze "główne" */
    public static String main(){
        return "główne";}
    
  /** Metoda dostarcza fraze "baza danych" */
    public static String db(){
        return "baza danych";}
    
  /** Metoda dostarcza fraze "zachowanie" */
    public static String behave(){
        return "zachowanie";}
    
  /** Metoda dostarcza fraze "wiadomości" */
    public static String mailing(){
        return "wiadomości";}
    
  /** Metoda dostarcza fraze "tak" */
    public static String yes(){
        return "tak";}
        
  /** Metoda dostarcza fraze "nie" */
    public static String no(){
        return "nie";}
    
  /** Metoda dostarcza fraze "url forum" */
    public static String urlForum(){
        return "url forum";}
   
  /** Metoda dostarcza fraze "host" */
    public static String host(){
        return "host";}
  
  /** Metoda dostarcza fraze "nazwa bazy danych" */
    public static String dbName(){
        return "nazwa bazy danych";}
    
   /** Metoda dostarcza fraze "prefix tabel" */
    public static String tablePrefix(){
        return "prefix tabel";}
    
   /** Metoda dostarcza fraze "konto gościa" */
    public static String guestAccount(){
        return "konto gościa";}
   
   /** Metoda dostarcza fraze "identyfikator gościa" */
    public static String guestId(){
        return "identyfikator gościa";}
    
   /** Metoda dostarcza fraze "maksymalny czas trwania sesji" */
    public static String maxSession(){
        return "maksymalny czas trwania sesji";}
    
  /** Metoda dostarcza fraze "minimalna długość hasła" */
    public static String minPassLength(){
        return "minimalna długośc hasła";}
    
  /** Metoda dostarcza fraze "Wysyłanie maila w celach autentykacji" */
    public static String newUserMail(){
        return "wysyłanie maila w celach autentykacji";}
    
  /** Metoda dostarcza fraze "SMTP server" */
    public static String smtpServer(){
        return "smtp server";}
   
     /** Metoda dostarcza fraze "adres pocztowy nadawcy" */
    public static String mailFrom(){
        return "adres poczty nadawcy";}
    
   /** Metoda dostarcza fraze "temat rejestracji" */
    public static String registrationTopic(){
        return "temat rejestracji";}
    
     /** Metoda dostarcza fraze "wiadomość po rejestracji" */
    public static String registrationMessage(){
        return "wiadomość po rejestracji";}
    
    /** Metoda dostarcza fraze "temat sekcji zapomniane" */
    public static String forgetTopic(){
        return "temat sekcji zapomniane";}
    
     /** Metoda dostarcza fraze "wiadomość sekcji zapomniane" */
    public static String forgetMessage(){
        return "wiadomość sekcji zapomniane";}
    
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
    public static String isRemovePod(){
        return "Czy napewno chcesz usunąć podforum?";}
    
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
    
    
    /** Metoda dostarcza komunikat "Zapomniałem hasło"
     * @return String z komunikatem
     */
    public static String iForgetPasswd(){
        return "Zapomniałem hasło";}
    
    
    /** Metoda dostarcza komunikat "Hasło zmieniono"
     * @return String z komunikatem
     */
    public static String passwordChanged(){
        return "Hasło zmieniono";}
    
    
    /**
     * Metoda dostarcza komunikat bledu o polaczeniu z baza danych
     * @return String z komunikatem o braku polaczenia z baza danych
     */
    public static String errorDataBaseConnection(){
        return "Błąd połączenia z bazą danych!";}
    
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze uzytkownik nie moze zostac stworzony
     * @return String z komunikatem o tym ze uzytkownik nie moze zostac stworzony
     */
    public static String errorUserCreate(){
        return "Nie można dodać użytkownika - skontaktuj się z administratorem forum";}
    
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze podany uzytkownik juz istnieje
     * @param nick String z nazwa uzytkownika
     * @return String z komunikatem o tym ze podany uzytkownik juz istnieje
     */
    public static String errorUserExists(String nick){
        return "Użytkownik " + nick + " już istnieje!";}
    
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze pole jest wymagane
     * @return String z komunikatem o tym ze pole jest wymagane
     */
    public static String errorFieldNeeded(){
        return "Pole jest wymagane!";}
    
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze pole jest wymagane
     * @param field String z nazwa pola
     * @return String z komunikatem o tym ze pole jest wymagane
     */
    public static String errorFieldNeeded(String field){
        return "Pole '" + field + "'jest wymagane!";}
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze haslo jest za krotkie
     * @param length liczba wyznaczajaca minimalna dlugosc hasla
     * @return String z komunikatem o tym ze haslo jest za krotkie
     */
    public static String errorPassToShort(int length){
        return "Hasło musi mieć przynajmniej " + length + " znaków";}
    
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze hasla nie sa identyczne
     * @return String z komunikatem o tym ze hasla nie sa identyczne
     */
    public static String errorPassNotMatch(){
        return "Hasła się nie zgadzają";}
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze bledzie logowania
     * @return String z komunikatem o bledzie logowania
     */
    public static String errorBadUserOrPass(){
        return "Błędny użytkownik lub hasło!";}
    
    
    /**
     * Metoda dostarcza komunikat bledu nieokreslonego
     * @return String z komunikatem o bledzie nieokreslonym
     */
    public static String errorUnknown(){
        return "Błąd! skontaktuj się z administratorem strony!";}
    
    
    /**
     * Metoda dostarcza komunikat o nie istnieniu watku
     * @return String z komunikatem o nie istnieniu watku
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
     * Metoda dostarcza komunikat o bledzie odczytu pliku konfiguracyjnego
     * @return String z komunikatem o bledzie odczytu pliku konfiguracyjnego
     */
    public static String errorXMLRead(){
        return "Błąd odczytu pliku konfiguracyjnego.";}
    
    
    /**
     * Metoda dostarcza komunikat o bledzie zapisu pliku konfiguracyjnego
     * @return String z komunikatem o bledzie zapisu pliku konfiguracyjnego
     */
    public static String errorXMLWrite(){
        return "Błąd zapisu pliku konfiguracyjnego.";}
    
    
    /**
     * Metoda dostarcza komunikat o bledzie klucza nowego uzytkownika
     * @return String z komunikatem o bledzie klucza nowego uzytkownika
     */
    public static String errorKeyNewUser(){
        return "Błędny klucz.";}
    
    
    /**
     * Metoda dostarcza komunikat o bledzie zmiany hasla
     * @return String z komunikatem o bledzie zmiany hasla
     */
    public static String errorChangePasswd(){
        return "Nie można zmienić hasła, skontaktuj się z administratorem forum.";}
    
    
    /**
     * Metoda dostarcza komunikat o istniejacym juz uzytkowniku z takim emailem
     * @return String z komunikatem o istniejaym juz uzytkowniku z takim emailem
     */
    public static String errorEmailExists(){
        return "Użytkownik ma już konto w serwisie.";}
    
    
    /**
     * Metoda dostarcza komunikat o istniejacej juz nazwie kategorii
     * @return String z komunikatem
     */
    public static String errorNameKat(){
        return "Kategoria o podanej nazwie już istnieje.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e naleďż˝y podaďż˝ nazwie kategorii
     * @return String z komunikatem
     */
    public static String errorFieldNameKat(){
        return "Należy podać nazwę kategorii.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e dodanie kategorii nie powiodďż˝o sie
     * @return String z komunikatem
     */
    public static String errorAddKat(){
        return "Dodanie kategorii nie powiodło się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e zmiana kategorii nie powiodďż˝a sie
     * @return String z komunikatem
     */
    public static String errorChangeKat(){
        return "Zmiana kategorii nie powiodła się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e zmiana podforum nie powiodďż˝a sie
     * @return String z komunikatem
     */
    public static String errorChangePod(){
        return "Zmiana podforum nie powiodła się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e zmiana uprawnien nie powiodďż˝a sie
     * @return String z komunikatem
     */
    public static String errorChangeUpr(){
        return "Zmiana uprawnien nie powiodła się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e dodanie kategorii powiodďż˝o sie
     * @return String z komunikatem
     */
    public static String addKat(){
        return "Kategoria została dodana.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e zmiana uprawnien powiodďż˝a sie
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
     * Metoda dostarcza komunikat o tym, że usuniecie podforum powiodło się.
     * @return String z komunikatem
     */
    public static String removePodforum(){
        return "Podforum zostało usunięte.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że aktywacja podforum powiodła się.
     * @return String z komunikatem
     */
    public static String activePodforum(){
        return "Podforum zostało aktywowane.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, usunięcie kategorii nie powiodło się
     * @return String z komunikatem
     */
    public static String errorRemoveKat(){
        return "Usunięcie kategorii nie powiodło się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że aktywacja kategorii nie powiodła się
     * @return String z komunikatem
     */
    public static String errorActiveKat(){
        return "Aktywacja kategorii nie powiodła się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, usuniecie podforum nie powiodďż˝o sie
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
     * Metoda dostarcza komunikat o tym, że aktwacja podforum nie powiodło się
     * @return String z komunikatem
     */
    
    public static String errorActivePodforum(){
        return "Aktywacja podforum nie powiodła się.";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że podforum o podanej nazwie juz istnieje.
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
     * Metoda dostarcza komunikat o tym, że dodanie podforum powiodło sie
     * @return String z komunikatem
     */
    public static String addPodforum(){
        return "Podforum zostało dodane.";}
    
    /**
     * Metoda dostarcza komunikat o tym, ze zmiana konfiguracij głównej powiodła sie
     * @return String z komunikatem
     */
    public static String changeConfigMain(){
        return "Konfiguracja główna została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o tym, ze zmiana konfiguracij bazy danych powiodła sie
     * @return String z komunikatem
     */
    public static String changeConfigDb(){
        return "Konfiguracja bazy danych została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o tym, ze zmiana konfiguracij zachowania forum powiodła sie
     * @return String z komunikatem
     */
    public static String changeConfigBehave(){
        return "Konfiguracja zachowania forum została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o tym, ze zmiana konfiguracij mailingu powiodła sie
     * @return String z komunikatem
     */
    public static String changeConfigMail(){
        return "Konfiguracja mailingu została zmieniona.";}
    
    /**
     * Metoda dostarcza komunikat o blďż˝dzie wywolania formularza
     * @return String z komunikatem
     */
    public static String formError(){
        return "Błąd formularza.";}
    
    
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
}
