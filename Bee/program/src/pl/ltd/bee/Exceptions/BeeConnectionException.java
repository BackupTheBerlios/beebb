/*
 * BeeConnectionException.java
 *
 * Created on 14 kwiecien 2005, 15:13
 */

package pl.ltd.bee.Exceptions;

/**
 *
 * @author wilk
 */
public class BeeConnectionException extends BeeException{
    
    /** Tworzy nowa instancje BeeIOException 
     * @param wyjatek tresc wyjatku reprezentowana jako string  
     */
    public BeeConnectionException(String wyjatek) {
        super(wyjatek);
    }
    
}
