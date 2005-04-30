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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import pl.ltd.bee.Exceptions.*;
import java.util.*;

/**
 *
 * @author wilk
 */
public class Config {
    
    /** stale ktore sloza do umieszczenia objektow w contenerze na ktorym sie wykonuje Bee */
    public final static String APPLICATION_OBJECT_DATABASE = "pl.ltd.bee.DataBase";
    public final static String APPLICATION_OBJECT_CONFIG = "pl.ltd.bee.Config";
    public final static String APPLICATION_OBJECT_AUTORYZACJA = "pl.ltd.bee.Autoryzator";
 
    private final static String FILE_NAME = "/config/config.xml"; 
    private final static String KODOWANIE = "UTF-8";
    
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
    private final static String TAG_GUEST_ID="guest_id";
    private final static String TAG_LOG_IN_MAX_AGE="log_in_max_age";
    private final static String TAG_MINIMUM_PASS_LENGTH="minimum_pass_length";
    private final static String TAG_NEW_USER_MAIL_AUTH="new_user_mail_auth";
    private final static String TAG_SMTP_SERVER="smtp_server";
    private final static String TAG_MAIL_FROM="mail_from";
    private final static String TAG_REGISTRATION_SUBJECT="registration_subject";
    private final static String TAG_REGISTRATION_BODY="registration_body";
    private final static String TAG_FORGET_SUBJECT="forget_subject";
    private final static String TAG_FORGET_BODY="forget_body";
    private final static String TAG_USE_COMPRESSION="use_compres";
    private final static String TAG_USE_SSL="use_ssl";
    private final static String TAG_SMILES="smiles";
    private final static String TAG_SMILE="smile";
    private final static String TAG_SMILE_TAG="smile_tag";
    private final static String TAG_SMILE_URL="smile_url";
    private final static String TAG_CACHE_COUNTER="cache_counter";
    private final static String TAG_SMILE_TAG_OPEN="smile_open_tag";
    private final static String TAG_SMILE_TAG_CLOSE="smile_close_tag";
    
    
    /** Zmienna informuje czy konfig zostal odczytany */
    private static boolean read = false;
    /** Zmienna informuje czy konfig jest "brudny". Jesli !read to ta zmienna jest true */
    private static boolean modified = true;
    
    /**
     * Zmienne ktore pozniej stana sie pewnie metodami,
     * ale teraz skupia w jednym miejscu konfiguracje.
     * Te zmienne nie powinny byc static, bo kazda z instancji Config dba tylko o swoje zmienne
     */
    public static String HOST = "";//"wilk.waw.pl";
    public static String USER = "";//"bee";
    public static String PASSWORD = "";//"bee";
    public static String DATABASE = "";//"Bee";
    public static String DATABASE_PREFIX = "Bee";
    
    /** Ustala czy polaczenie z baza ma byc kompresowane */
    public static boolean USE_COMPRESSION = false;
    
    /** Ustala czy polaczenie z baza ma byc szyfrowane */
    public static boolean USE_SSL = false;
    
    /** Adres www forum */
    public static String URL_FORUM = "http://127.0.0.1:8080";
    
    /** nazwa uzytkownika ktorego bedziemy traktowac jako goscia */
    public static String GUEST = "Guest";
    public static int GUEST_ID = 1;
    
    /** czas w sekundach po jakim uzytkownik zostanie automatycznie wylogowany */
    public static int LOG_IN_MAX_AGE = 3600;
    
    /** minimalna d�ugo�� has�a wpisanego przez u�ytkownika */
    public static int MIN_PASSWD = 5;
    
    /** stale potrzebne do wysylania poczty do uzytkownikow */
    public static String SMTP_SERVER = "127.0.0.1";
    public static String MAIL_FROM = "bee@localhost";
    
    /** rejestracja uzytkownika */
    public static boolean NEW_USER_MAIL_AUTH = true;
    public static String REG_MAIL_SUBJECT = "[BEE] Rejestracja";
    public static String REG_MAIL_BODY = "Właśnie założyłeś konto na forum Bee, poniżej znajduje się link w który należy kliknąć aby aktywować konto: \n";

    /** zapomniane haslo */
    public static String FORGET_MAIL_SUBJECT = "[BEE] Zapomniane hasło";
    public static String FORGET_MAIL_BODY = "Poniżej link do zmiany zapomnianego hasła: \n";

    /** cacheowanie liczby odwiedzin 
     * co ile sekund zapisywac do bazy licznik odwiedzin
     */
    public static int CACHE_COUNTER = 10; 
    
    /** Hashtable w ktorych kluczami sa znaczniki emotikonek a wartosciami url do nich
     **/
    public static Hashtable SMILES = new Hashtable();
    
    
    /** Ustala ciag znakow rozpoczynajacych znacznik emotikony */
    public static String SMILE_TAG_OPEN = ":|";
    
    /** Ustala ciag znakow konczacy znacznik emotikony */
    public static String SMILE_TAG_CLOSE = "|:";
    
    /**    
     * Klasa odpowiedzialna za przetworzenie dokumnetu XML
     */
    private class MyHandler extends DefaultHandler {
        
        private String tag;
        private Config config;
        private String last_smile_tag="";
        
        public MyHandler(Config conf){
            config = conf;
        }
                
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
            if ( tag.compareTo(Config.TAG_CACHE_COUNTER) == 0)  config.CACHE_COUNTER = Integer.parseInt(wynik);
            if ( tag.compareTo(Config.TAG_DATABASE_NAME) == 0)  config.DATABASE = wynik;
            if ( tag.compareTo(Config.TAG_FORGET_BODY) ==0)     config.FORGET_MAIL_BODY = wynik;
            if ( tag.compareTo(Config.TAG_FORGET_SUBJECT) ==0)  config.FORGET_MAIL_SUBJECT = wynik;
            if ( tag.compareTo(Config.TAG_GUEST_ACCOUNT) ==0)   config.GUEST = wynik;
            if ( tag.compareTo(Config.TAG_GUEST_ID) ==0)        config.GUEST_ID = Integer.parseInt(wynik);
            if ( tag.compareTo(Config.TAG_HOST) == 0)           config.HOST = wynik;
            if ( tag.compareTo(Config.TAG_LOG_IN_MAX_AGE) == 0) config.LOG_IN_MAX_AGE = Integer.parseInt(wynik);
            if ( tag.compareTo(Config.TAG_MAIL_FROM) ==0)       config.MAIL_FROM = wynik;
            if ( tag.compareTo(Config.TAG_MINIMUM_PASS_LENGTH) ==0) config.MIN_PASSWD = Integer.parseInt(wynik);
            if ( tag.compareTo(Config.TAG_NEW_USER_MAIL_AUTH) ==0) config.NEW_USER_MAIL_AUTH = Boolean.valueOf(wynik).booleanValue();
            if ( tag.compareTo(Config.TAG_PASSWORD) == 0)       config.PASSWORD = wynik;
            if ( tag.compareTo(Config.TAG_REGISTRATION_BODY) == 0) config.REG_MAIL_BODY = wynik;
            if ( tag.compareTo(Config.TAG_REGISTRATION_SUBJECT) == 0) config.REG_MAIL_SUBJECT = wynik;
            if ( tag.compareTo(Config.TAG_SMTP_SERVER) == 0)    config.SMTP_SERVER = wynik;
            if ( tag.compareTo(Config.TAG_TABLES_PREFIX) ==0)   config.DATABASE_PREFIX = wynik;
            if ( tag.compareTo(Config.TAG_URL_FORUM) == 0)      config.URL_FORUM = wynik;
            if ( tag.compareTo(Config.TAG_USE_COMPRESSION) == 0)config.USE_COMPRESSION = Boolean.valueOf(wynik).booleanValue();
            if ( tag.compareTo(Config.TAG_USE_SSL) == 0)        config.USE_SSL = Boolean.valueOf(wynik).booleanValue();
            if ( tag.compareTo(Config.TAG_USER) == 0)           config.USER = wynik;
            if ( tag.compareTo(Config.TAG_SMILE_TAG_OPEN) == 0) config.SMILE_TAG_OPEN = wynik;
            if ( tag.compareTo(Config.TAG_SMILE_TAG_CLOSE) == 0) config.SMILE_TAG_CLOSE = wynik;
            if ( tag.compareTo(Config.TAG_SMILE_URL) == 0)      if (last_smile_tag.length() > 0) SMILES.put(last_smile_tag,wynik);
            if ( tag.compareTo(Config.TAG_SMILE_TAG) == 0)      last_smile_tag = wynik; //to musi byc przed SMILE_URL boolean zeruje last_smile_tag
                                else                            last_smile_tag = "";
        }
    }    
    
    /**
     * Metoda wczytuje konfiguracje z pliku XML
     * @param app context serwletu na ktorym sie wykonujemy
     */
    public void readConfig(javax.servlet.ServletContext app) throws BeeException{
        if ((read) && (!modified)) return;//TODO uznaje ze jesli jest odczytany a zmodyfikowany to znaczy ze odczyt jest wymuszany wiec pomijam zmiany
        String fileName = app.getRealPath(FILE_NAME);
       	System.setProperty("org.xml.sax.driver","org.apache.crimson.parser.XMLReaderImpl");
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ContentHandler handler = new MyHandler(this);
            reader.setContentHandler(handler);
            reader.parse(fileName);
            read = true;
            modified = false;
        }
        catch (Exception e) {
            BeeIOException nowy = new BeeIOException(Messages.errorXMLRead());
            nowy.initCause(e);
            throw nowy;
        }
    }
    
    /**
     * Metoda tworzy drzewo DOM
     * @param fileName sciezka do pliku XML
     */
    private Document wczytajXML(String fileName) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true); //TODO zmienc na true jak juz wszystko bedzie dzialac
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(fileName);
    }
    
    /**
     * Metoda wpisuje wartosci zmiennych do drzewa DOM
     * @param node drzewo DOM
     */
    private void ustawZmienne(Node node) throws Exception
    {
       int nodeType = node.getNodeType();
           if (nodeType == Node.TEXT_NODE) {
           Node parent = node.getParentNode();
           if (parent.getNodeName().compareTo(TAG_CACHE_COUNTER) == 0)          node.setNodeValue(Integer.toString(CACHE_COUNTER));
           if (parent.getNodeName().compareTo(TAG_DATABASE_NAME) == 0)          node.setNodeValue(DATABASE);
           if (parent.getNodeName().compareTo(TAG_FORGET_BODY) == 0)            node.setNodeValue(FORGET_MAIL_BODY);
           if (parent.getNodeName().compareTo(TAG_FORGET_SUBJECT) == 0)         node.setNodeValue(FORGET_MAIL_SUBJECT);
           if (parent.getNodeName().compareTo(TAG_GUEST_ACCOUNT) == 0)          node.setNodeValue(GUEST);
           if (parent.getNodeName().compareTo(TAG_GUEST_ID) == 0)               node.setNodeValue(Integer.toString(GUEST_ID));
           if (parent.getNodeName().compareTo(TAG_HOST) == 0)                   node.setNodeValue(HOST);
           if (parent.getNodeName().compareTo(TAG_LOG_IN_MAX_AGE) == 0)         node.setNodeValue(Integer.toString(LOG_IN_MAX_AGE));
           if (parent.getNodeName().compareTo(TAG_MAIL_FROM) == 0)              node.setNodeValue(MAIL_FROM);
           if (parent.getNodeName().compareTo(TAG_MINIMUM_PASS_LENGTH) == 0)    node.setNodeValue(Integer.toString(MIN_PASSWD));
           if (parent.getNodeName().compareTo(TAG_NEW_USER_MAIL_AUTH) == 0)     node.setNodeValue(Boolean.toString(NEW_USER_MAIL_AUTH));
           if (parent.getNodeName().compareTo(TAG_PASSWORD) == 0)               node.setNodeValue(PASSWORD);
           if (parent.getNodeName().compareTo(TAG_REGISTRATION_BODY) == 0)      node.setNodeValue(REG_MAIL_BODY);
           if (parent.getNodeName().compareTo(TAG_REGISTRATION_SUBJECT) == 0)   node.setNodeValue(REG_MAIL_SUBJECT);
           if (parent.getNodeName().compareTo(TAG_SMTP_SERVER) == 0)            node.setNodeValue(SMTP_SERVER);
           if (parent.getNodeName().compareTo(TAG_TABLES_PREFIX) == 0)          node.setNodeValue(DATABASE_PREFIX);
           if (parent.getNodeName().compareTo(TAG_URL_FORUM) == 0)              node.setNodeValue(URL_FORUM);
           if (parent.getNodeName().compareTo(TAG_USE_COMPRESSION) == 0)        node.setNodeValue(Boolean.toString(USE_COMPRESSION));
           if (parent.getNodeName().compareTo(TAG_USE_SSL) == 0)                node.setNodeValue(Boolean.toString(USE_SSL));
           if (parent.getNodeName().compareTo(TAG_USER) == 0)                   node.setNodeValue(USER);       
           if (parent.getNodeName().compareTo(TAG_SMILE_TAG_OPEN) == 0)         node.setNodeValue(SMILE_TAG_OPEN);       
           if (parent.getNodeName().compareTo(TAG_SMILE_TAG_CLOSE) == 0)        node.setNodeValue(SMILE_TAG_CLOSE);       
           }
       if (nodeType == Node.ELEMENT_NODE || nodeType == Node.DOCUMENT_NODE) {
           NodeList chldrn = node.getChildNodes();
           for (int i = 0; i < chldrn.getLength(); i++) {
	       ustawZmienne(chldrn.item(i));
	   }
       }        
    }
    
    /** TODO TRZEBA PRZEPISAC
     * Metoda zapisuje drzewo DOM do pliku
     * @param node drzewo DOM
     * @param plik strumien do zapisu
     */
    private void zapiszXML(Node node,OutputStreamWriter plik) throws Exception
    {
       int nodeType = node.getNodeType();
       if (nodeType == Node.DOCUMENT_NODE)
       {
           plik.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
           plik.write("<!DOCTYPE bee_configuration SYSTEM \"config.dtd\">");
       }   
       if (nodeType == Node.TEXT_NODE)
           if (node.getNodeValue() != null) plik.write(node.getNodeValue());
       if (nodeType == Node.ELEMENT_NODE) {
           plik.write("<"+node.getNodeName());
           NamedNodeMap att = node.getAttributes();
           for(int i=0;i<att.getLength();i++)
                    {
                        Node a = att.item(i);
                        plik.write(" " + a.getNodeName()+"=\"" + a.getNodeValue()+"\"");
                    }
           plik.write(">");
       }
       if (nodeType == Node.ELEMENT_NODE || nodeType == Node.DOCUMENT_NODE) {
           NodeList chldrn = node.getChildNodes();
           for (int i = 0; i < chldrn.getLength(); i++) {
	       zapiszXML(chldrn.item(i),plik);
	   }
           if (nodeType == Node.ELEMENT_NODE) plik.write("</" +node.getNodeName() +">");
       }
    }
    
    /**
     * Metoda zapisuje konfiguracje do pliku XML
     * @param app context serwletu na ktorym sie wykonujemy
     */
    public void saveConfig(javax.servlet.ServletContext app) throws BeeException{
        if (!modified) return;//zakladam, ze zmienna jest poprawna boolean Config mozna zmienic tylko przez set*
        try
        {
            Document xml = wczytajXML(app.getRealPath(FILE_NAME));
            ustawZmienne(xml);
            OutputStreamWriter plik = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(app.getRealPath(FILE_NAME), false)), KODOWANIE);
            zapiszXML(xml,plik);
            plik.close();
            read = true; // bo skoro zapisalem to jest tez przeczytany
            modified = false;//nie jest zmodyfikowany wzgledem XML'a
        }
        catch(Exception e)
        {
            BeeIOException nowy = new BeeIOException(Messages.errorXMLWrite());
            nowy.initCause(e);
            throw nowy;
        }
    }
    /** Metoda ustawia adres forum
     * @param url adres forum
     */
    public void setUrlForum(String url){
        modified = true;
        URL_FORUM = url;}

    /** Metoda ustawia adres bazy danych
     * @param host adres bazy danych
     */
    public void setHost(String host){
        modified = true;
        HOST = host;}

    /** Metoda ustawia nazwe uzytkownika bazy danych
     * @param user nazwa uzytkownika
     */
    public void setUser(String user){
        modified = true;
        USER = user;}

    /** Metoda ustawia haslo uzytkownika do bazy danych
     * @param pass haslo uzytkownika
     */
    public void setPassword(String pass){
        modified = true;
        PASSWORD = pass;}

    /** Metoda ustawia nazwe bazy danych
     * @param name nazwa bazy danych
     */
    public void setDatabaseName(String name){
        modified = true;
        DATABASE = name;}

    /** Metoda ustawia prefix tabel z bazie danych
     * @param prefix prefix tabel
     */
    public void setTablesPrefix(String prefix){
        modified = true;
        DATABASE_PREFIX = prefix;}

    /** Metoda ustawia nazwe konta gosc
     * @param guest nazwa konta gosc
     */
    public void setGuestAccount(String guest){
        modified = true;
        GUEST = guest;}

    /** Metoda ustawia numer konta gosc
     * @param id numer konta gosc
     */
    public void setGuestId(int id){
        modified = true;
        GUEST_ID = id;}

    /** Metoda ustawia dlugosc minimalna dlugosc hasla
     * @param len minimalna dlugosc hasla
     */
    public void setMinimumPassLength(int len){
        modified = true;
        MIN_PASSWD = len;}
    
    /** Metoda ustawia parametr potwierdzania rejestracji uzytkownika
     * @param makeIt nowa wartosc parametru
     */
    public void setNewUserMailAuth(boolean makeIt){
        modified = true;
        NEW_USER_MAIL_AUTH = makeIt;}

    /** Metoda ustawia adres serwera SMTP
     * @param server adres serwera SMTP
     */
    public void setSmtpServer(String server){
        modified = true;
        SMTP_SERVER = server;}

    /** Metoda ustawia wartosc pola FROM
     * @param from zawartosc pola
     */
    public void setMailFrom(String from){
        modified = true;
        MAIL_FROM = from;}

    /** Metoda ustawia temat listu rejestracyjnego
     * @param subject String z tematem
     */
    public void setRegistrationSubject(String subject){
        modified = true;
        REG_MAIL_SUBJECT = subject;}

    /** Metoda ustawia zawartosc listu rejestracyjnego
     * @param body String z zawartoscia
     */
    public void setRegistrationBody(String body){
        modified = true;
        REG_MAIL_BODY = body;}

    /** Metoda ustawia temat listu przypominajacego haslo
     * @param subject String z tytulem
     */
    public void setForgetSubject(String subject){
        modified = true;
        FORGET_MAIL_SUBJECT = subject;}

    /** Metoda ustawia zawartosc listu przypominajacego haslo
     * @param body String z zawartoscia listu
     */
    public void setForgetBody(String body){
        modified = true;
        FORGET_MAIL_BODY = body;}
    
     /** Metoda ustawia czas wygasania sesji
     * @param p ilosc sekund
     */
    public void setLogInMaxAge(int p){
        modified = true;
        LOG_IN_MAX_AGE = p;}
       
     /** Metoda ustala czy polaczenie z baza ma byc kompresowane
     * @param b Ustala wartosc
     */
    public void setUseCompression(boolean b){
        modified = true;
        USE_COMPRESSION = b;}

     /** Metoda ustala czy polaczenie z baza ma byc szyfrowane
     * @param b Ustala wartosc
     */
    public void setUseSsl(boolean b){
        modified = true;
        USE_SSL = b;}
    
    /** Metoda ustala co ile sekund zapisywac liczbe odwiedzin do bazy
     * @param i Ilosc sekund
     */
    public void setCacheCounter(int i){
        modified = true;
        CACHE_COUNTER = i;
    }
    
    /** Metoda dodaje tag dla emotikonki. Jesli tag istnieje zostanie podmieniony
     * @param tag Ciag znakow okreslajacy znacznik emotikony
     * @param url Sciezka do pliku graficznego
     */
    public void setSmileTag(String tag, String url){
        modified = true;
        SMILES.put(tag, url);
    }
    
    /**
     * Konstruktor
     */
    public Config(){// throws BeeException{
        //this.readConfig();
    }
    
    public Config(javax.servlet.ServletContext app) throws BeeException{
        this.readConfig(app);
    }

}
