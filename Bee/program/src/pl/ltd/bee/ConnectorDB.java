package pl.ltd.bee;

import java.util.*;
import java.sql.*;
import java.lang.*;

public class ConnectorDB {
    
    private String Host;
    private String User;
    private String Pass;
    
    public ConnectorDB(String Host, String User, String Pass) {
        setParameters(Host,User,Pass);
    }
    
    public void setParameters(String Host, String User, String Pass) {
        this.Host=Host;
        this.User=User;
        this.Pass=Pass;
    }
    
    /**Metoda zwraca liste wierszy ktore zwieraja hashtable:
     * klucz:nazwa kolumny       wartosc: wartosc w kolumnie
     **/
    public ArrayList query(String q) {
        ArrayList pom=new ArrayList();
        try {
            Connection con = DriverManager.getConnection(Host,User,Pass);
            Statement select = con.createStatement();
            ResultSet result = select.executeQuery(q);
            ResultSetMetaData rsmd = result.getMetaData();
            int licz = rsmd.getColumnCount();
            
            while(result.next()) {
                Hashtable row=new Hashtable();
                for(int i=0;i<licz;i++) {
                    row.put(rsmd.getColumnName(i),result.getString(i));
                }
                pom.add(row);
            }
            
            con.close();
        } catch( Exception e ) { e.printStackTrace(); }
        return pom;
    }
    
    /**metoda wykonuje Insert, Update lub Delete **/
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
