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
    
    /**
     * Metoda dostarcza lacznika "lub"
     * @return String z lacznikiem "lub"
     */
    public static String or(){
        return "<p class=\"info\">lub</p>";}
    
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
        return "<p class=\"error\">Blad! skontaktuj sie z administratorem strony!<p>";}
    
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
     * Metoda dostarcza komunikat o istniejącym juz uzytkowniku z takim emailem
     * @return String z komunikatem o istniejącym juz uzytkowniku z takim emailem
     */
    public static String errorEmailExists(){
        return "<p class=\"error\">Użytkownik ma już konto w serwisie.</p>";}


}
