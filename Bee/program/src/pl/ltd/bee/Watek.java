/*
 * Watek.java
 *
 * Created on 9 marzec 2005, 12:21
 */

package pl.ltd.bee;


import java.util.*;
/**
 *
 * @author pawelb
 */
public class Watek {
    
    private String ID;
    private String ID_Autor;
    private String Temat;
    private String Data;
    
    /** Creates a new instance of Watek
     *@param ID - id watku
     *@param ID_Autor - id autora watku
     *@param Temat - temat watku
     *@param Data - data watku
     */
    public Watek(String ID, String ID_Autor, String Temat, String Data)  {
        this.ID=ID;
        this.ID_Autor=ID_Autor;
        this.Temat=Temat;
        this.Data=Data;
    }
    
    public String getTemat() {
        return Temat;
    }
}
