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
    
    private long ID;
    private int ID_Autor;
    private String Temat;
    private String Data;
    
    /** Creates a new instance of Watek
     *@param ID id watku
     *@param ID_Autor id autora watku
     *@param Temat temat watku
     *@param Data data watku
     */
    public Watek(String ID, String ID_Autor, String Temat, String Data)  {
        this.ID=Long.decode(ID).longValue();
        this.ID_Autor=Integer.decode(ID_Autor).intValue();
        this.Temat=Temat;
        this.Data=Data;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca String bedacy temetem watki
     */
    public String getTemat() {
        return Temat;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca long bedacy identyfikatorem watku w bazie
     */
    public long getID() {
        return ID;
    }
}
