/*
 * BeeIOException.java
 *
 * Created on 26 marzec 2005, 10:39
 */

package pl.ltd.bee.Exceptions;

/**
 *
 * @author wilk
 */
public class BeeIOException extends BeeException{
    
    /** Tworzy nowa instancje BeeIOException 
     * @param wyjatek tresc wyjatku reprezentowana jako string  
     */
    public BeeIOException(String wyjatek) {
        super(wyjatek);
    }
    
}
