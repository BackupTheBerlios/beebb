/*
 * Forum.java
 *
 * Created on 9 marzec 2005, 17:27
 */

package pl.ltd.bee;

import java.util.*;

/**
 *
 * @author pawelb
 */
public class Forum {
    
    private String Nazwa;
    private long ID;
    
    /** Tworzy nowa instancje klasy Forum 
     @param ID identyfikator forum w bazie
     @param Nazwa nazwa forum
     */
    public Forum(String ID, String Nazwa) {
        this.ID=Long.decode(ID).longValue();
        this.Nazwa=Nazwa;
    }
    
    /** Zwraca identyfikator forum
     @return zwraca long bedacy identyfikatorem forum w bazie
     */
    public long getID() {
        return ID;
    }
}
