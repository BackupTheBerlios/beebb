/*
 * UserGen.java
 *
 * Created on 9 marzec 2005, 18:02
 */

package pl.ltd.bee;

/**
 *
 * @author pawel
 */
public class UserFactory {
    
    
     public UserFactory() {
        
    }
    
    
    /** Tworzy uzytkownika forum 
     * @param User_ID identyfikator uzytkownika w bazie
     * @param User_Login login uzytkownika na forum
     * @param User_Haslo haslo uzytkownika
     * @param User_Admin czy uzytkownik jest adminem
     * @param User_Moderator czy uzytkownik jedt moderatorem
     * @return zwraca obiekt klasy dziedziczacej po User reprezentujacy danego uzytkownika
     */
    public static User getUser(String User_ID, String User_Login, String User_Haslo, String User_Admin, String User_Moderator) {
       return null;
    }
    
}