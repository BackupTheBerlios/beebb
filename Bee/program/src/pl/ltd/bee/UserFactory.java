/*
 * UserGen.java
 *
 * Created on 9 marzec 2005, 18:02
 */

package pl.ltd.bee;

/**
 *
 * @author pawelb
 */
public class UserFactory {
    
    /** Tworzy nowa instancje UserFactory */
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
    public static User getUser(String User_ID, String User_Login, String User_Haslo,String User_Imie,String User_Nazwisko, String User_Email, String User_gg, String User_Jabber , String User_Admin, String User_Moderator) {
        int userID = Integer.decode(User_ID).intValue();
        if (User_Admin.compareTo("T")==0) {
            return null;
        }
        if (User_Moderator.compareTo("T")==0) {
            
        }
        return new RegisteredUser(userID,User_Login,User_Haslo,User_Imie,User_Nazwisko,User_Email,User_gg,User_Jabber);
    }
    
}
