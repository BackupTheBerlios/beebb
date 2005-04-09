package pl.ltd.bee;

import java.util.*;
import java.sql.*;
import java.lang.*;

/**
 * Klasa implementujaca placzenie z baza danych i wykonywanie zapytan SQL.
 */
public class ConnectorDB {
    /**
     * Wyjatek na wewnetrzne potrzeby klasy ConnectDB rzucany przez metode connect(). 
     */
    class ConnectionException extends Exception{};
    
    private String Host;
    private String User;
    private String Pass;
    private String DataBase;
    
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
    
    /**
     * Metoda tworzaca polaczenie z baza danych
     * @return Objekt klasy Connection bedacy uchwytem do polaczenia z baza.
     */
    private Connection connect() throws ConnectionException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection("jdbc:mysql://"+ Host +"/" + DataBase + "?useUnicode=true&characterEncoding=UTF-8",User,Pass);
        }
        catch (Exception e){
            throw (ConnectionException)((new ConnectionException()).initCause(e));
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
            Connection con = this.connect();
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
            con.close();
        } catch( Exception e ) { 
/*            Hashtable row2=new Hashtable();
            row2.put("EXCEPTION",e);
            pom.add(row2);*/
        }
        return pom;
    }
    
    /** 
     * Metoda wykonuje Insert, Update lub Delete 
     * @return True jesli operacja sie powiodla, False w p.p.
     **/
    public boolean dmlQuery(String q) {
        try{
            Connection con = this.connect();
            Statement select = con.createStatement();
            int wynik = select.executeUpdate(q);
            con.close();
            return (wynik!=0);
        } catch( Exception e ) { e.printStackTrace(); }
        return false;
    }

}
