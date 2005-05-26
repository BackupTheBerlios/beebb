/*
 * Privilage.java
 *
 * Created on 8 maj 2005, 00:31
 */

package pl.ltd.bee;

/**
 *
 * @author Piotrek
 */
public class Privilage {
    
    int id_group;
    int id_kat;
    int id_pod;
    boolean czytanie;
    boolean pisanie;
    
    /** Creates a new instance of Privilage */
    public Privilage() {
    }
    
    public Privilage(int id_group, int id_kat, int id_pod, boolean czytanie, boolean pisanie) {
        this.id_group=id_group;
        this.id_kat=id_kat;
        this.id_pod=id_pod;
        this.czytanie=czytanie;
        this.pisanie=pisanie;
        
    }
    
     /** Metoda zwraca ID grupy
     * @return Integer reprezentujacy grupę
     */
    public int getIdGroup() {
        return id_group;
    }
    
     
    /** Metoda ustawia id grupy
     * @param id Int z id-em grupy
     **/
    public void setIdGroup(int id){
        this.id_group=id;
    }
    
     /** Metoda zwraca ID kategorii
     * @return Integer reprezentujacy kategorie
     */
    public int getIdKat() {
        return id_kat;
    }
    
     
    /** Metoda ustawia id kategorii
     * @param id Int z id-em kategorii
     **/
    public void setIdKat(int id){
        this.id_kat=id;
    }
    
    /** Metoda zwraca ID podforum
     * @return Integer reprezentujacy podforum
     */
    public int getIdPod() {
        return id_pod;
    }
    
     
    /** Metoda ustawia id podforum
     * @param id Int z id-em podforum
     **/
    public void setIdPod(int id){
        this.id_pod=id;
    }
    
    /** metoda zwraca pisanie
     * @return T lub N 
     */
    public boolean pisanie() {
        return pisanie;
    }
    
     /** metoda zwraca czytanie
     * @return T lub N 
     */
    public boolean czytanie() {
        return czytanie;
    }
    
     /** metoda ustawia pisanie
     * @param pis Okresla prawa pisania
     */
    public void setPisanie(boolean pis) {
      pisanie=pis;
    }
    
    /** metoda ustawia czytanie
     * @param czyt Okresla prawa odczytu
     */
    public void setCzytanie(boolean czyt) {
      czytanie=czyt;
    }
    
}
