package pl.ltd.bee;

import java.util.*;
import java.sql.*;
import java.lang.*;

/**
 * Klasa implementujaca placzenie z baza danych i wykonywanie zapytan SQL.
 */
public class ConnectorDB {
    
    private String Host;
    private String User;
    private String Pass;
    
    /**
     * Konstruktor
     * @param Host adres serwera bazy danych
     * @param User nazwa uzytkownika bazy danych
     * @param Pass haslo uzytkownika bazy danych
     */
    public ConnectorDB(String Host, String User, String Pass) {
        setParameters(Host,User,Pass);
    }
    
    /**
     * Metoda ustawia parametry polaczenia z baza danych
     * @param Host adres serwera bazy danych
     * @param User nazwa uzytkownika bazy danych
     * @param Pass haslo uzytkownika bazy danych
     */
    public void setParameters(String Host, String User, String Pass) {
        this.Host=Host;
        this.User=User;
        this.Pass=Pass;
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
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Bee","wilk","wilk");
            Statement select = con.createStatement();        
            ResultSet result = select.executeQuery(q);
            ResultSetMetaData rsmd = result.getMetaData();
            
            Hashtable row1=new Hashtable();
            row1.put("QUERY",q);
            pom.add(row1);
            int licz = rsmd.getColumnCount();            
            while(result.next()) {
                Hashtable row=new Hashtable();
                for(int i=0;i<licz;i++) {
                    row.put(rsmd.getColumnName(i),result.getString(i));
                }
                pom.add(row);
            }            
            con.close();
        } catch( Exception e ) { 
            Hashtable row2=new Hashtable();
            row2.put("EXCEPTION",e);
            pom.add(row2);
                //e.printStackTrace(); 
        }
        return pom;
    }
    
    /** 
     * Metoda wykonuje Insert, Update lub Delete 
     * @return True jesli operacja sie powiodla, False w p.p.
     **/
    public boolean dmlQuery(String q) {
        try{
            Connection con = DriverManager.getConnection(Host,User,Pass);
            Statement select = con.createStatement();
            int wynik = select.executeUpdate(q);
            con.close();
            return (wynik!=0);
        } catch( Exception e ) { e.printStackTrace(); }
        return false;
    }

}
