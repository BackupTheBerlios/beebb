/*
 * Crypto.java
 *
 * Created on 22 marzec 2005, 14:04
 */

package pl.ltd.bee;

import pl.aislib.util.crypt.UnixCrypt;

/**
 *
 * @author pawelb
 */
public class Crypto {
    
    
    /** Szyfruje haslo
     * @param haslo haslo w plain tekst
     * @return zakodowane haslo
     */
    public static String crypt(String haslo) {
        return UnixCrypt.crypt(haslo);
    }
    
    
    /** Sprawdza czy zakodowane haslo (1 arg) jest takie same jak haslo w plain tekst (2 arg)
     * @param zakodhaslo zakodowane haslo
     * @param plainhaslo haslo w plain tekst
     * @return T lub F w zaleznosci czy hasla sa takie same czy nie
     */
    public static boolean matches(String zakodhaslo, String plainhaslo) {
        return UnixCrypt.matches(zakodhaslo, plainhaslo);
    }
    
    
}
