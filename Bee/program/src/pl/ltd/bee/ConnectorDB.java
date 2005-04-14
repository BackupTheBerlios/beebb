package pl.ltd.bee;

import java.util.*;
import java.sql.*;
import java.lang.*;
import pl.ltd.bee.Exceptions.*;

/**
 * Klasa implementujaca placzenie z baza danych i wykonywanie zapytan SQL.
 */
public class ConnectorDB {

    private String Host;
    private String User;
    private String Pass;
    private String DataBase;
    private Connection con;
    
    /**
     * Konstruktor
     * @param Host adres serwera bazy danych
     * @param Db nazwa bazy danych
     * @param User nazwa uzytkownika bazy danych
     * @param Pass haslo uzytkownika bazy danych
     */
    public ConnectorDB(String Host, String Db, String User, String Pass) {
        setParameters(Host,Db,User,Pass);
    }
    
    /**
     * Metoda ustawia parametry polaczenia z baza danych
     * @param Host adres serwera bazy danych
     * @param Db nazwa bazy danych
     * @param User nazwa uzytkownika bazy danych
     * @param Pass haslo uzytkownika bazy danych
     */
    public void setParameters(String Host, String Db, String User, String Pass) {
        this.Host=Host;
        this.DataBase=Db;
        this.User=User;
        this.Pass=Pass;
    }
    
    /** Metoda zwraca odpowiedz na pytanie czy obiekt ma aktywne polaczenie z baza danych
     * @return True jesli polaczenie z baza danych jest aktywne i False w p.p.
     */
    public boolean isConnected(){//TODO to podobno jest zle, bo isClosed zwraca falszywe odpowiedzi. trzeba zrobic "SELECT 1;"
        if (con == null) return false;
        try{
            return !(con.isClosed());
        }catch (SQLException e) {return false;}
    }
    
    
    /**
     * Metoda tworzaca polaczenie z baza danych
     * @return Objekt klasy Connection bedacy uchwytem do polaczenia z baza.
     */
    public Connection connect() throws BeeConnectionException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties ustawienia = new Properties();
            ustawienia.setProperty("user",User);
            ustawienia.setProperty("password",Pass);
            ustawienia.setProperty("useCompression",Config.USE_COMPRESSION? "true":"false");
            ustawienia.setProperty("useSSL",Config.USE_SSL?"true":"false");
            ustawienia.setProperty("useUnicode","true");
            ustawienia.setProperty("characterEncoding","UTF-8");
            //TODO JAK JUZ BEDZIE DZIALAC TO DODAC CACHING !!!
            con = DriverManager.getConnection("jdbc:mysql://"+ Host +"/" + DataBase,ustawienia);
            return con;
        }
        catch (Exception e){
            throw (BeeConnectionException)(new BeeConnectionException(Messages.errorDataBaseConnection())).initCause(e);
        }
    }
    
    /**
     * Metoda wykonuje przekazane zapytanie i zwraca liste wierszy ktore zwieraja hashtable:
     * klucz:nazwa kolumny       wartosc: wartosc w kolumnie
     * Klucze i wartosci sa typu String
     * @param q Zapytanie do wykonania
     * @return ArrayList of Hashtables
     **/
    public ArrayList query(String q) {
        ArrayList pom=new ArrayList();
        try {
            //connect();
            Statement select = con.createStatement();        
            ResultSet result = select.executeQuery(q);
            ResultSetMetaData rsmd = result.getMetaData();
            
            int licz = rsmd.getColumnCount();            
            while(result.next()) {
                Hashtable row=new Hashtable();
                for(int i=1;i<=licz;i++) {
                    row.put(rsmd.getColumnName(i).toUpperCase(),result.getString(i));                    
                }
                pom.add(row);
            }            
            //con.close();
        } catch( Exception e ) { 
            //if ("08S01".equals(e.getSQLState()) || "41000".equals(e.getSQLState())) //to oznacza ze trzeba zrobic reconnect
        }
        return pom;
    }
    
    /** 
     * Metoda wykonuje Insert, Update lub Delete 
     * @return True jesli operacja sie powiodla, False w p.p.
     **/
    public boolean dmlQuery(String q) {
        try{
            //connect();
            Statement select = con.createStatement();
            int wynik = select.executeUpdate(q);
            //con.close();
            return (wynik!=0);
        } catch( Exception e ) { 
            //if ("08S01".equals(e.getSQLState()) || "41000".equals(e.getSQLState())) //to oznacza ze trzeba zrobic reconnect
        }
        return false;
    }

}
