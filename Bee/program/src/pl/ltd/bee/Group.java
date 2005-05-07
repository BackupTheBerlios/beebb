/*
 * Group.java
 *
 * Created on 5 maj 2005, 20:03
 */

package pl.ltd.bee;

/**
 *
 * @author Piotrek
 */
public class Group {
    
    private int ID;
    private String nazwa;
    
    public Group() {}
    
    /** Creates a new instance of Group */
    public Group(int ID, String nazwa ) {
        this.ID=ID;
        this.nazwa=nazwa;
    }
   
    /** Metoda zwraca ID grupy
     * @return Integer reprezentujacy grupę
     */
    public int getID() {
        return ID;
    }
    
     
    /** Metoda ustawia id grupy
     * @param id Int z id-em grupy
     **/
    public void setID(int id){
        this.ID=id;
    }
    
     /** Metoda zwraca nazwe grupy
     * @return String z nazwa grupy
     **/
    public String getNazwa(){
        return this.nazwa;
    }
    
    
    /** Metoda ustawia nazwe grupy 
     * @param naz String z nazwa grupy
     **/
    public void setNazwa(String naz){
        this.nazwa=naz;
    }
    
}
