/*
 * BeeException.java
 *
 * Created on 24 marzec 2005, 18:04
 */

package pl.ltd.bee;

/**
 *
 * @author pawelb
 */
public class BeeException extends Exception{
    
    /** Tworzy nowa instancje BeeException 
     * @param wyjatek tresc wyjatku reprezentowana jako string  
     */
    public BeeException(String wyjatek) {
        super(wyjatek);
    }
    
}
