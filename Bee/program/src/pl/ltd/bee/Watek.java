/*
 * Watek.java
 *
 * Created on 9 marzec 2005, 12:21
 */

package pl.ltd.bee;


import java.util.ArrayList;
/**
 *
 * @author pawelb
 */
public class Watek {
    
    private int ID;
    private int ID_Autor;
    private String Temat;
    private String Data;
    private ArrayList Wypowiedzi;
    private DataBase db;
    
    /** Creates a new instance of Watek
     *@param ID id watku
     *@param ID_Autor id autora watku
     *@param Temat temat watku
     *@param Data data watku
     */
    public Watek(String ID, String ID_Autor, String Temat, String Data,DataBase db)  {
        this.ID=Integer.decode(ID).intValue();
        this.ID_Autor=Integer.decode(ID_Autor).intValue();
        this.Temat=Temat;
        this.Data=Data;
        this.db=db;
        Wypowiedzi=db.getWypowiedziWatku(this.ID);
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca String bedacy temetem watki
     */
    public String getTemat() {
        return Temat;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca nliczbe bedac± identyfikatorem watku w bazie
     */
    public int getID() {
        return ID;
    }
    
    public String printJSPHeader() {
        //outs.write("Temat: " + Temat);
        return "<a href=\"?wid="  + ID +" \">" + Temat + "</a> Utworzony dnia: " + Data + "<br>";
    }
    
    
    public String printJSP() {
        //outs.write("Temat: " + Temat);
        String s =  "<h2>" + Temat + "</h2>(Utworzony dnia: " + Data + ")<br><br><br>";
        for(int i=0;i<Wypowiedzi.size();i++) {
            Wypowiedz w = ((Wypowiedz)db.getWypowiedz(((Integer)Wypowiedzi.get(i)).intValue()));
            s+= w.printJSP();
        }
        return s;
    }
    
}
