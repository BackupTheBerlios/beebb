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
 
    private final static String FILE_NAME = "/config/config.xml"; 
    
    /** znaczniki w XMLu */
    private final static String TAG_BEE_CONFIGURATION="bee_configuration";
    private final static String TAG_GENERAL="general";
    private final static String TAG_DATABASE="databse";
    private final static String TAG_BEHAVE="behave";
    private final static String TAG_MAILING="mailing";
    private final static String TAG_URL_FORUM="url_forum";
    private final static String TAG_HOST="host";
    private final static String TAG_USER="user";
    private final static String TAG_PASSWORD="password";
    private final static String TAG_DATABASE_NAME="database_name";
    private final static String TAG_TABLES_PREFIX="tables_prefix";
    private final static String TAG_GUEST_ACCOUNT="guest_account";
    private final static String TAG_MINIMUM_PASS_LENGTH="minimum_pass_length";
    private final static String TAG_NEW_USER_MAIL_AUTH="new_user_mail_auth";
    private final static String TAG_SMTP_SERVER="smtp_server";
    private final static String TAG_MAIL_FROM="mail_from";
    private final static String TAG_REGISTRATION_SUBJECT="registration_subject";
    private final static String TAG_REGISTRATION_BODY="registration_body";
    private final static String TAG_FORGET_SUBJECT="forget_subject";
    private final static String TAG_FORGET_BODY="forget_body";
    
    
    /**
     * Zmienne ktore pozniej stana sie pewnie metodami,
     * ale teraz skupia w jednym miejscu konfiguracje.
     * Te zmienne nie powinny byc static, bo kazda z instancji Config dba tylko o swoje zmienne
     */
    public static String HOST = "wilk.waw.pl";
    public static String USER = "bee";
    public static String PASSWORD = "bee";
    public static String DATABASE = "Bee";
    public static String DATABASE_PREFIX = "Bee";
    
    /** Adres www forum */
    public static String URL_FORUM = "http://127.0.0.1:8080";
    
    /** nazwa uzytkownika ktorego bedziemy traktowac jako goscia */
    public static String GUEST = "Guest";
    
    /** minimalna d�ugo�� has�a wpisanego przez u�ytkownika */
    public static int MIN_PASSWD = 5;
    
    /** stale potrzebne do wysylania poczty do uzytkownikow */
    public static String SMTP_SERVER = "127.0.0.1";
    public static String MAIL_FROM = "bee@localhost";
    
    /** rejestracja uzytkownika */
    public static boolean NEW_USER_MAIL_AUTH = true;
    public static String REG_MAIL_SUBJECT = "[BEE] Rejestracja";
    public static String REG_MAIL_BODY = "W�a�nie za�o�y�e� konto na forum Bee, poni�ej znajduje si� link w kt�ry nale�y klikn�� aby aktywowa� konto: \n";

    /** zapomniane haslo */
    public static String FORGET_MAIL_SUBJECT = "[BEE] Zapomniane has�o";
    public static String FORGET_MAIL_BODY = "Poni�ej link do zmiany zapomnianego has�a: \n";

    /**    
     * Klasa odpowiedzialna za przetworzenie dokumnetu XML
     */
    private class MyHandler extends DefaultHandler {
        
        private String tag;
                
        public void startElement(String uri,String localName,String qName,Attributes attributes)
        {
            tag = localName;
        }
        
        public void endElement(String uri,String localName,String qName)
        {
            tag = "";
        }
        
        public void characters(char[] ch, int start, int length) {
            String wynik = new String(ch,start,length);
            if ( tag.compareTo(Config.TAG_DATABASE_NAME) == 0)  Config.this.DATABASE = wynik;
            if ( tag.compareTo(Config.TAG_FORGET_BODY) ==0)     Config.this.FORGET_MAIL_BODY = wynik;
            if ( tag.compareTo(Config.TAG_FORGET_SUBJECT) ==0)  Config.this.FORGET_MAIL_SUBJECT = wynik;
            if ( tag.compareTo(Config.TAG_GUEST_ACCOUNT) ==0)   Config.this.GUEST = wynik;
            if ( tag.compareTo(Config.TAG_HOST) == 0)           Config.this.HOST = wynik;
            if ( tag.compareTo(Config.TAG_MAIL_FROM) ==0)       Config.this.MAIL_FROM = wynik;
            if ( tag.compareTo(Config.TAG_MINIMUM_PASS_LENGTH) ==0) Config.this.MIN_PASSWD = Integer.parseInt(wynik);
            if ( tag.compareTo(Config.TAG_NEW_USER_MAIL_AUTH) ==0) Config.this.NEW_USER_MAIL_AUTH = Boolean.valueOf(wynik).booleanValue();
            if ( tag.compareTo(Config.TAG_PASSWORD) == 0)       Config.this.PASSWORD = wynik;
            if ( tag.compareTo(Config.TAG_REGISTRATION_BODY) == 0) Config.this.REG_MAIL_BODY = wynik;
            if ( tag.compareTo(Config.TAG_REGISTRATION_SUBJECT) == 0) Config.this.REG_MAIL_SUBJECT = wynik;
            if ( tag.compareTo(Config.TAG_SMTP_SERVER) == 0)    Config.this.SMTP_SERVER = wynik;
            if ( tag.compareTo(Config.TAG_TABLES_PREFIX) ==0)   Config.this.DATABASE_PREFIX = wynik;
            if ( tag.compareTo(Config.TAG_URL_FORUM) == 0)      Config.this.URL_FORUM = wynik;
            if ( tag.compareTo(Config.TAG_USER) == 0)           Config.this.USER = wynik;
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
     * Konstruktor
     */
    public Config(){// throws BeeException{
        //this.readConfig();
    }

}
