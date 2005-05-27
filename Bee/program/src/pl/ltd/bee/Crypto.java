/*
 * Crypto.java
 *
 * Created on 22 marzec 2005, 14:04
 */

package pl.ltd.bee;

import pl.aislib.util.crypt.UnixCrypt;

/**
 * Klasa odpowiedzialna na szyfrowanie haseł użytkowników
 * @author pawelb
 */
public class Crypto {
    
    
    /** Szyfruje hasło
     * @param haslo hasło w plain tekst
     * @return Zakodowane hasło
     */
    public static String crypt(String haslo) {
        return UnixCrypt.crypt(haslo);
    }
    
    
    /** Sprawdza czy zakodowane hasło (1 arg) jest takie samo jak hasło w plain tekst (2 arg)
     * @param zakodhaslo zakodowane hasło
     * @param plainhaslo hasło w plain tekst
     * @return True lub false w zależności czy hasła są takie same czy nie
     */
    public static boolean matches(String zakodhaslo, String plainhaslo) {
        return UnixCrypt.matches(zakodhaslo, plainhaslo);
    }
    
    
}
