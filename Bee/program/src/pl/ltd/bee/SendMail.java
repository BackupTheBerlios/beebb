/*
 * SendMail.java
 *
 * Created on 23 marzec 2005, 19:35
 */

package pl.ltd.bee;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.*;

/**
 *
 * @author pawelb
 */
public class SendMail {
    
    /** Statyczna metoda sluzaca do wysylania maili. Wiekszosc ustawien jest brana z klasy Config
     * @param To adres emailowy uzytkownika do ktorego ma byc wyslana wiadomosc
     * @param Subject temat wiadomosci
     * @param Body tresc wiadomosci
     */
    public static void send(String To, String Subject, String Body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", Config.SMTP_SERVER);
            Session session = Session.getInstance(props, null);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(Config.MAIL_FROM));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To, false));
            msg.setSubject(Subject);
            msg.setText(Body);
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
}
