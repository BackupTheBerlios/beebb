/*
 * Config.java
 *
 * Created on 20 marzec 2005, 20:22
 */

package pl.ltd.bee;

/**
 *
 * @author wilk
 */
public class Config {
    /**
     * Zmienne ktore pozniej stana sie pewnie metodami,
     * ale teraz skupia w jednym miejscu konfiguracje.
     */
    public static String HOST = "192.168.3.174";//"wilk.waw.pl";
    public static String USER = "bee";
    public static String PASSWORD = "bee";
    public static String DATABASE = "Bee";
    public static String DATABASE_PREFIX = "Bee";
    
    /** Adres www forum */
    public static String URL_FORUM = "http://127.0.0.1:8080";
    
    /** nazwa uzytkownika ktorego bedziemy traktowac jako goscia */
    public static String GUEST = "Guest";
    
    /** stale potrzebne do wysylania poczty do uzytkownikow */
    public static String SMTP_SERVER = "127.0.0.1";
    public static String MAIL_FROM = "bee@localhost";
    
    /** rejestracja uzytkownika */
    public static boolean NEW_USER_MAIL_AUTH = true;
    public static String REG_MAIL_SUBJECT = "[BEE] Rejestracja";
    public static String REG_MAIL_BODY = "W³a¶nie za³o¿y³e¶ konto na forum Bee, poni¿ej znajduje siê link w który nale¿y klikn±æ aby aktywowaæ konto: \n";
    
}
