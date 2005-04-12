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
    
    public static String wielka(String s){
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
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
    public static String subname(){
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

    
    //_______________________________________________________________________________________________
    //                              Metody zwracajace komunikaty
    //_______________________________________________________________________________________________
    
    
    /** Metoda dostarcza komunikat o wyslaniu emaila aktywacyjnego */
    public static String messageActivEmailSend(){
        return "<p class=\"info\">Email z linkiem aktywacyjnym został wysłany.</p>";}
    
    /**
     * Metoda dostarcza komunikat bledu o polaczeniu z baza danych
     * @return String z komunikatem o braku polaczenia z baza danych
     */
    public static String errorDataBaseConnection(){
        return "<p class=\"error\">Błąd połączenia z bazą danych!</p>";}
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze uzytkownik nie moze zostac stworzony
     * @return String z komunikatem o tym ze uzytkownik nie moze zostac stworzony
     */
    public static String errorUserCreate(){
        return "<p class=\"error\">Nie można dodać użytkownika - skontaktuj się z administratorem forum</p>";}
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze podany uzytkownik juz istnieje
     * @param nick String z nazwa uzytkownika
     * @return String z komunikatem o tym ze podany uzytkownik juz istnieje
     */
    public static String errorUserExists(String nick){
        return "<p class=\"error\">Użytkownik " + nick + " już istnieje!</p>";}
    
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze pole jest wymagane
     * @return String z komunikatem o tym ze pole jest wymagane
     */
    public static String errorFieldNeeded(){
        return "<p class=\"error\">Pole jest wymagane!</p>";}
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze pole jest wymagane
     * @param field String z nazwa pola
     * @return String z komunikatem o tym ze pole jest wymagane
     */
    public static String errorFieldNeeded(String field){
        return "<p class=\"error\">Pole '" + field + "'jest wymagane!</p>";}
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze haslo jest za krotkie
     * @param length liczba wyznaczajaca minimalna dlugosc hasla
     * @return String z komunikatem o tym ze haslo jest za krotkie
     */
    public static String errorPassToShort(int length){
        return "<p class=\"error\">Hasło musi mieć przynajmniej " + length + " znaków</p>";}
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze hasla nie sa identyczne
     * @return String z komunikatem o tym ze hasla nie sa identyczne
     */
    public static String errorPassNotMatch(){
        return "<font class=\"error\">Hasła się nie zgadzają</p>";}
    
    /**
     * Metoda dostarcza komunikat bledu informujacy ze bledzie logowania
     * @return String z komunikatem o bledzie logowania
     */
    public static String errorBadUserOrPass(){
        return "<p class=\"error\">Błędny użytkownik lub hasło!</p>";}
    
    /**
     * Metoda dostarcza komunikat bledu nieokreslonego
     * @return String z komunikatem o bledzie nieokreslonym
     */
    public static String errorUnknown(){
        return "<p class=\"error\">Błąd! skontaktuj się z administratorem strony!<p>";}
    
    /**
     * Metoda dostarcza komunikat o nie istnieniu watku
     * @return String z komunikatem o nie istnieniu watku
     */
    public static String errorThreadNotExists(){
        return "<p class=\"error\">Brak takiego wątku!</p>";    }
    
    /**
     * Metoda dostarcza komunikat o nie istnieniu kategorii
     * @return String z komunikatem o nie istnieniu kategorii
     */
    public static String errorCategoryNotExists(){
        return "<p class=\"error\">Brak takiej kategorii!</p>";    }
    
    /**
     * Metoda dostarcza komunikat o nie istnieniu podforum
     * @return String z komunikatem o nie istnieniu podforum
     */
    public static String errorSubForumNotExists(){
        return "<p class=\"error\">Brak takiego podforum!</p>";    }
    
    /**
     * Metoda dostarcza komunikat o bledzie odczytu pliku konfiguracyjnego
     * @return String z komunikatem o bledzie odczytu pliku konfiguracyjnego
     */
    public static String errorXMLRead(){
        return "<p class=\"error\">Błąd odczytu pliku konfiguracyjnego.</p>";}
    
    /**
     * Metoda dostarcza komunikat o bledzie zapisu pliku konfiguracyjnego
     * @return String z komunikatem o bledzie zapisu pliku konfiguracyjnego
     */
    public static String errorXMLWrite(){
        return "<p class=\"error\">Błąd zapisu pliku konfiguracyjnego.</p>";}
    
    /**
     * Metoda dostarcza komunikat o bledzie klucza nowego uzytkownika
     * @return String z komunikatem o bledzie klucza nowego uzytkownika
     */
    public static String errorKeyNewUser(){
        return "<p class=\"error\">Błędny klucz.</p>";}
    
    /**
     * Metoda dostarcza komunikat o bledzie zmiany hasla
     * @return String z komunikatem o bledzie zmiany hasla
     */
    public static String errorChangePasswd(){
        return "<p class=\"error\">Nie można zmienić hasła, skontaktuj się z administratorem forum.</p>";}
    
    
    /**
     * Metoda dostarcza komunikat o istniejacym juz uzytkowniku z takim emailem
     * @return String z komunikatem o istniejaym juz uzytkowniku z takim emailem
     */
    public static String errorEmailExists(){
        return "<p class=\"error\">Użytkownik ma już konto w serwisie.</p>";}
    
    
    /**
     * Metoda dostarcza komunikat o istniejacej juz nazwie kategorii
     * @return String z komunikatem
     */
    public static String errorNameKat(){
        return "<p align=\"center\" class=\"error\">Kategoria o podanej nazwie już istnieje.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e naleďż˝y podaďż˝ nazwie kategorii
     * @return String z komunikatem
     */
    public static String errorFieldNameKat(){
        return "<p align=\"center\" class=\"error\"> Należy podać nazwę kategorii.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e dodanie kategorii nie powiodďż˝o sie
     * @return String z komunikatem
     */
    public static String errorAddKat(){
        return "<p  align=\"center\" class=\"error\"> Dodanie kategorii nie powiodło się.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e zmiana kategorii nie powiodďż˝a sie
     * @return String z komunikatem
     */
    public static String errorChangeKat(){
        return "<p  align=\"center\" class=\"error\"> Zmiana kategorii nie powiodła się.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e zmiana podforum nie powiodďż˝a sie
     * @return String z komunikatem
     */
    public static String errorChangePod(){
        return "<p  align=\"center\" class=\"error\"> Zmiana podforum nie powiodła się.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e zmiana uprawnien nie powiodďż˝a sie
     * @return String z komunikatem
     */
    public static String errorChangeUpr(){
        return "<p  align=\"center\" class=\"error\"> Zmiana uprawnien nie powiodła się.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e dodanie kategorii powiodďż˝o sie
     * @return String z komunikatem
     */
    
    public static String addKat(){
        return "<p align=\"center\" class=\"info\"> Kategoria została dodana.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e zmiana uprawnien powiodďż˝a sie
     * @return String z komunikatem
     */
    
    public static String changeUpr(){
        return "<p align=\"center\" class=\"info\"> Uprawnienia zostały zmienione.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana podforum powiodła sie
     * @return String z komunikatem
     */
    public static String changePod(){
        return "<p align=\"center\" class=\"info\"> Podforum zostało zmienione.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że usuniecie kategorii powiodło sie
     * @return String z komunikatem
     */
    
    public static String removeKat(){
        return "<p align=\"center\" class=\"info\"> Kategoria została usunięta.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że aktywacja kategorii powiodła sie
     * @return String z komunikatem
     */
    
    public static String activeKat(){
        return "<p align=\"center\" class=\"info\"> Kategoria została aktywowana.</p>";}
    
    
    /**
     * Metoda dostarcza komunikat o tym, że zmiana kategorii powiodła się.
     * @return String z komunikatem
     */
    public static String changeKat(){
        return "<p align=\"center\" class=\"info\"> Kategoria została zmieniona.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że usuniecie podforum powiodło się.
     * @return String z komunikatem
     */
    
    public static String removePodforum(){
        return "<p align=\"center\" class=\"info\" > Podforum zostało usunięte.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że aktywacja podforum powiodła się.
     * @return String z komunikatem
     */
    
    public static String activePodforum(){
        return "<p align=\"center\" class=\"info\" > Podforum zostało aktywowane.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, usunięcie kategorii nie powiodło się
     * @return String z komunikatem
     */
    public static String errorRemoveKat(){
        return "<p  align=\"center\" class=\"error\"> Usunięcie kategorii nie powiodło się.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że aktywacja kategorii nie powiodła się
     * @return String z komunikatem
     */
    public static String errorActiveKat(){
        return "<p  align=\"center\" class=\"error\"> Aktywacja kategorii nie powiodła się.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, usuniecie podforum nie powiodďż˝o sie
     * @return String z komunikatem
     */
    
    public static String errorRemovePodforum(){
        return "<p  align=\"center\" class=\"error\"> Usunięcie podforum nie powiodło się.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że nie została podana nazwa podforum
     * @return String z komunikatem
     */
    
    public static String errorFieldNamePodforum(){
        return "<p align=\"center\" class=\"error\"> Nie została podana nazwa podforum.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że aktwacja podforum nie powiodło się
     * @return String z komunikatem
     */
    
    public static String errorActivePodforum(){
        return "<p align=\"center\" class=\"error\"> Aktywacja podforum nie powiodła się.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że podforum o podanej nazwie juz istnieje.
     * @return String z komunikatem
     */
    public static String errorNamePodforum(){
        return "<p align=\"center\" class=\"error\">Podforum o podanej nazwie już istnieje.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, że dodanie podforum nie powiodło się.
     * @return String z komunikatem
     */
    public static String errorAddPodforum(){
        return "<p  align=\"center\" class=\"error\">Dodanie podforum nie powiodło się.</p>";}
    
    public static String errorNotLoggedIn(){
        return "<p align=\"center\" class=\"error\">Proszę się zalogować.</p>";}
    
    /**
     * Metoda dostarcza komunikat o tym, ďż˝e dodanie podforum powiodĹo sie
     * @return String z komunikatem
     */
    public static String addPodforum(){
        return "<p align=\"center\" class=\"info\">Podforum zostało dodane.</p>";}
    
    /**
     * Metoda dostarcza komunikat o blďż˝dzie wywolania formularza
     * @return String z komunikatem
     */
    
    public static String formError(){
        return "<p align=\"center\" class=\"info\">Błąd formularza.</p>";}
    
    
    /**
     * Metoda dostarcza komunikat o poprawnym dodaniu wypowiedzi
     * @return String z komunikatem
     */
    
    public static String addMessage(){
        return "Dodano Wypowiedź";}
    
    
    /**
     * Metoda dostarcza komunikat o poprawnym dodaniu wÄtka
     * @return String z komunikatem
     */
    
    public static String addThread(){
        return "Dodano Wątek";}
    
}
