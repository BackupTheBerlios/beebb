package pl.ltd.bee;

import java.util.*;
import java.sql.*;
import java.lang.*;
import pl.ltd.bee.Exceptions.*;

/**
 * Klasa implementujaca połączenie z bazą danych i wykonywanie zapytań SQL.
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
     * @param User nazwa użytkownika bazy danych
     * @param Pass hasło użytkownika bazy danych
     */
    public ConnectorDB(String Host, String Db, String User, String Pass) {
        setParameters(Host,Db,User,Pass);
    }
    
    
    /**
     * Metoda likwiduje połączenie z bazą danych jeśli takie było.
     * @throws W przypadku problemów ze sterownikiem lub połączeniem metoda rzuca wyjątek BeeConnectionException
     */
    public void disconnect() throws BeeConnectionException{
        try{
        if (con != null)
            if (!con.isClosed()) con.close();
        }catch (SQLException e) {return;}
    }
    
    /**
     * Metoda ustawia parametry połączenia z bazą danych
     * @param Host adres serwera bazy danych
     * @param Db nazwa bazy danych
     * @param User nazwa użytkownika bazy danych
     * @param Pass hasło użytkownika bazy danych
     */
    public void setParameters(String Host, String Db, String User, String Pass) {
        this.Host=Host;
        this.DataBase=Db;
        this.User=User;
        this.Pass=Pass;
    }
    
    /** Metoda zwraca odpowiedź na pytanie czy obiekt ma aktywne połączenie z bazą danych
     * @return True jeśli połączenie z bazą danych jest aktywne i False w p.p.
     */
    public boolean isConnected(){//TODO to podobno jest zle, bo isClosed zwraca falszywe odpowiedzi. trzeba zrobic "SELECT 1;"
        if (con == null) return false;
        try{
            if (con.isClosed()) return false;
            else
            {
                Statement select = con.createStatement();        
                ResultSet result = select.executeQuery("SELECT 1");
                ResultSetMetaData rsmd = result.getMetaData();
            
                int licz = rsmd.getColumnCount();            
                if (result.next()) return true;
                    else return false;
            }
        }catch (SQLException e) {return false;}
    }
    
    
    /**
     * Metoda tworząca połączenie z bazą danych
     * @throws W przypadku problemów ze sterownikiem lub połączeniem metoda rzuca wyjątek BeeConnectionException
     * @return Obiekt klasy Connection będący uchwytem do połączenia z bazą.
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
            //jakas proba cache'owania na poziomie drivera
            //Should the driver cache ResultSetMetaData for Statements and PreparedStatements? (Req. JDK-1.4+, true/false, default 'false')
            ustawienia.setProperty("cacheResultSetMetadata","true");
            //Should the driver cache the parsing stage of PreparedStatements?
            ustawienia.setProperty("cachePrepStmts","true");
            //Should the driver cache the parsing stage of CallableStatements
            ustawienia.setProperty("cacheCallableStmts","true");
            //If prepared statement caching is enabled, what's the largest SQL the driver will cache the parsing for?
            ustawienia.setProperty("prepStmtCacheSqlLimit","512");//TODO Czy taka stala dodac do Config'a ... To chyba juz nie jest stala dla admina ... chociaz, moze niech ma
            //If prepared statement caching is enabled, how many prepared statements should be cached?
            ustawienia.setProperty("prepStmtCacheSize","50");
            //The number of queries to cacheResultSetMetadata for if cacheResultSetMetaData is set to 'true' (default 50)
            ustawienia.setProperty("metadataCacheSize","100");
            con = DriverManager.getConnection("jdbc:mysql://"+ Host +"/" + DataBase,ustawienia);
            return con;
        }
        catch (Exception e){
            throw (BeeConnectionException)(new BeeConnectionException(Messages.errorDataBaseConnection())).initCause(e);
        }
    }
    
    /**
     * Metoda wykonuje przekazane zapytanie i zwraca listę wierszy które zwierają hashtable:
     * klucz:nazwa kolumny       wartość: wartość w kolumnie
     * Klucze i wartości są typu String
     * @param q zapytanie do wykonania
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
                    if (result.getString(i)!=null)
                        row.put(rsmd.getColumnName(i).toUpperCase(),result.getString(i));                    
                }
                pom.add(row);
            }            
            //con.close();
        } catch( Exception e ) { 
            //if ("08S01".equals(e.getSQLState()) || "41000".equals(e.getSQLState())) //to oznacza ze trzeba zrobic reconnect
            System.err.println(q);
            System.err.println(e);
        }
        return pom;
    }
    
    /** 
     * Metoda wykonuje Insert, Update lub Delete 
     * @param q zapytanie do wykonania
     * @return True jesli operacja się powiodła, False w p.p.
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

    //TODO niech ta metoda rzuca wyjatkiem w razie niepowodzenia i -1 jak nie ma last_id
    /** 
     * Metoda wykonuje Insert z jednoczesny sprawdzeniem ostatnio wstawionego identyfikatora.
     * Uwaga: Metoda nie analizuje przekazanego zapytania. Jeśli zapytanie nie powoduje wygenerowanie nowego identyfikatora zostanie zwrócony ostatnio znany bądź losowy
     * @param q zapytanie do wykonania
     * @return Zwracany jest identyfikator jaki został wygenerowany przez przekazane zapytanie. W przypadku niepowodzenia metoda zwraca -1.
     **/
    public int insert(String q) {
        try{
            //connect();
            Statement select = con.createStatement();
            int wynik = select.executeUpdate(q);
            if (wynik > 0 ){
              ArrayList ids = this.query("SELECT LAST_INSERT_ID() as ID");
              if (ids == null) return -1;
              if (ids.size()>0)
              {
                  Hashtable hid = (Hashtable)ids.get(0);
                  String id = (String)hid.get("ID");
                  if (id == null)  return -1;
                    else return Integer.parseInt(id);
              }
              else return -1;
              }
        } catch( Exception e ) { 
            //if ("08S01".equals(e.getSQLState()) || "41000".equals(e.getSQLState())) //to oznacza ze trzeba zrobic reconnect
            System.err.println(q);
            System.err.println(e);
        }
        return -1;
    }
}
