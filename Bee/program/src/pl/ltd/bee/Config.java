/*
 * Config.java
 *
 * Created on 20 marzec 2005, 20:22
 */

package pl.ltd.bee;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.*;
import pl.ltd.bee.Exceptions.*;

/**
 *
 * @author wilk
 */
public class Config {
 
    private final String FILE_NAME = "/config/config.xml"; //TODO beda problemy, oj beda :D
    
    /** znaczniki w XMLu */
    private final String TAG_BEE_CONFIGURATION="bee_configuration";
    private final String TAG_GENERAL="general";
    private final String TAG_DATABASE="databse";
    private final String TAG_BEHAVE="behave";
    private final String TAG_MAILING="mailing";
    private final String TAG_URL_FORUM="url_forum";
    private final String TAG_HOST="host";
    private final String TAG_USER="user";
    private final String TAG_PASSWORD="password";
    private final String TAG_DATABASE_NAME="database_name";
    private final String TAG_TABLES_PREFIX="tables_prefix";
    private final String TAG_GUEST_ACCOUNT="guest_account";
    private final String TAG_MINIMUM_PASS_LENGTH="minimum_pass_length";
    private final String TAG_NEW_USER_MAIL_AUTH="new_user_mail_auth";
    private final String TAG_SMTP_SERVER="smtp_server";
    private final String TAG_MAIL_FROM="mail_from";
    private final String TAG_REGISTRATION_SUBJECT="registration_subject";
    private final String TAG_REGISTRATION_BODY="registration_body";
    private final String TAG_FORGET_SUBJECT="forget_subject";
    private final String TAG_FORGET_BODY="forget_body";
    
    
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
    
    /** minimalna d³ugo¶æ has³a wpisanego przez u¿ytkownika */
    public static int MIN_PASSWD = 5;
    
    /** stale potrzebne do wysylania poczty do uzytkownikow */
    public static String SMTP_SERVER = "127.0.0.1";
    public static String MAIL_FROM = "bee@localhost";
    
    /** rejestracja uzytkownika */
    public static boolean NEW_USER_MAIL_AUTH = true;
    public static String REG_MAIL_SUBJECT = "[BEE] Rejestracja";
    public static String REG_MAIL_BODY = "W³a¶nie za³o¿y³e¶ konto na forum Bee, poni¿ej znajduje siê link w który nale¿y klikn±æ aby aktywowaæ konto: \n";

    /** zapomniane haslo */
    public static String FORGET_MAIL_SUBJECT = "[BEE] Zapomniane has³o";
    public static String FORGET_MAIL_BODY = "Poni¿ej link do zmiany zapomnianego has³a: \n";

    /**    
     * Klasa odpowiedzialna za przetworzenie dokumnetu XML
     */
    private class MyHandler extends DefaultHandler {

        public void startElement(String uri,String localName,String qName,Attributes attributes)
        {
            
        }
        
        public void characters(char[] ch, int start, int length) {
            String str = new String(ch, start, length);
            //System.out.println("CHARACTERS: ["+str+"]");
        }
    }    
    
    /**
     * Metoda wczytuje konfiguracje z pliku XML
     * @param app context serwletu na ktorym sie wykonujemy
     */
    public void readConfig(javax.servlet.ServletContext app) throws BeeException{
        String fileName = app.getRealPath(FILE_NAME);
       	System.setProperty("org.xml.sax.driver","org.apache.crimson.parser.XMLReaderImpl");
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ContentHandler handler = new MyHandler();
            reader.setContentHandler(handler);
            reader.parse(fileName);
        }
        catch (Exception e) {
            BeeIOException nowy = new BeeIOException(Messages.errorXMLRead());
            nowy.initCause(e);
            throw nowy;
        }
    }
       
    /**
     * Konstruktor. Podczas tworzenia objektu odczytywane sa ustawienia z pliku XML.
     */
    public Config(){// throws BeeException{
        //this.readConfig();
    }

}
