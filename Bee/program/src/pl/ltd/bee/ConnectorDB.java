package pl.ltd.bee;

import java.util.*;
import java.sql.*;
import java.lang.*;

public class ConnectorDB {
    
    
    public ConnectorDB() {}
    
    //zwraca liste wierszy ktore zwieraja hashtable klucz:nazwa kolumny wartosc: wartosc w kolumnie
    public ArrayList query(String q)
    {
       ArrayList pom=new ArrayList();
       Hashtable row;
       Connection con=null;
       int licz,i,j=0;
     
      try {
        con = DriverManager.getConnection("jdbc:odbc:bee","sopi","sopi");
        Statement select = con.createStatement();
        ResultSet result = select.executeQuery(q);
        ResultSetMetaData rsmd = result.getMetaData();
        licz= rsmd.getColumnCount();

        while(result.next()) 
         {
           row=new Hashtable();         
           for(i=0;i<licz;i++)
            {
             row.put(rsmd.getColumnName(i),result.getString(i));   
            }
           pom.add(row);
         }
         con.close();
        }
       catch( Exception e ) { e.printStackTrace(); }
       
       return pom;
      }
     
    //metoda wykonuje Insert, Update lub Delete
   
    public boolean dmlQuery(String q)
    {
      Connection con=null;
      int wynik=0;
     try{
         con = DriverManager.getConnection("jdbc:odbc:bee","sopi","sopi");
         Statement select = con.createStatement();
         wynik = select.executeUpdate(q);
      
         con.close();   
        }
      catch( Exception e ) { e.printStackTrace(); }
      if (wynik==0) return false;
      else return true;
    }
}
