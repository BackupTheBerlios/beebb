/*
 * Backup.java
 *
 * Created on 17 kwiecień 2005, 00:14
 */

package pl.ltd.bee;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import pl.ltd.bee.Exceptions.*;
import java.util.*;

/**
 *
 * @author Piotrek
 */
public class Backup {
    
    private final static String FILE_NAME = "/Administracja/backup.xml"; 
    private final static String KODOWANIE = "UTF-8";
    
    private DataBase db_con;
    private OutputStreamWriter plik;
    
    /** Creates a new instance of Backup */
    public Backup(DataBase db_con) {
        this.db_con=db_con;
    }
    
   private void zapiszWypowiedzi(int id) throws Exception {
            plik.write("\n          <wypowiedzi> \n" );
            ArrayList wyp=db_con.getWypowiedziWatku(id);
            for(int i=0;i<wyp.size();i++) {
               Wypowiedz w=db_con.getWypowiedz( ((Integer)wyp.get(i)).intValue() ) ;
                  plik.write("          <wypowiedz> \n" );
                  plik.write("            <id>"+w.getID()+"</id> \n" );
                  plik.write("            <id_autora>"+w.getIDAutora()+"</id_autora> \n" );
                  plik.write("            <autor>"+w.getAutor()+"</autor> \n" );
                  plik.write("            <data>"+w.getData()+"</data> \n" );
                  plik.write("            <tekst>"+w.getTekst()+"</tekst> \n" );
                  plik.write("            <prywatna>"+w.czyPrywatna()+"</prywatna> \n" );
                  plik.write("          </wypowiedz> \n" );
            }  
            plik.write("          </wypowiedzi> \n" );
      }
    
    private void zapiszWatki(int id) throws Exception {
            plik.write("\n        <watki> \n" );
            ArrayList wat=db_con.getWatkiPodforum(id,false,false);
            for(int i=0;i<wat.size();i++) {
               Watek w=db_con.getWatek( ((Integer)wat.get(i)).intValue() ) ;
                  plik.write("        <watek> \n" );
                   plik.write("         <id>"+w.getID()+"</id> \n" );
                  plik.write("          <id_autora>"+w.getIDAutora()+"</id_autora> \n" );
                  plik.write("          <autor>"+w.getAutor()+"</autor> \n" );
                  plik.write("          <temat>"+w.getTemat()+"</temat> \n" );
                  plik.write("          <data>"+w.getData()+"</data> \n" );
                  plik.write("          <prywatny>"+w.czyPrywatny()+"</prywatny> \n" );
                  zapiszWypowiedzi(w.getID());
                  plik.write("        </watek> \n" );
            }  
            plik.write("        </watki> \n" );
      }
    
    private void zapiszPodfora(int id) throws Exception {
             plik.write("\n     <podfora> \n" );
            ArrayList pod=db_con.getPodforaKategorii(id);
            for(int i=0;i<pod.size();i++) {
               Podforum p=db_con.getPodforum( ((Integer)pod.get(i)).intValue() ) ;
                  plik.write("      <podforum> \n" );
                  plik.write("        <id>"+p.getID()+"</id> \n" );
                  plik.write("        <tytul>"+p.getTytul()+"</tytul> \n" );
                  plik.write("        <opis>"+p.getOpis()+"</opis> \n" );
                  plik.write("        <aktywne>"+p.getAktywne()+"</aktywne> \n" );
                  plik.write("        <prywatne>"+p.czyPrywatne()+"</prywatne> \n" );
                  zapiszModeratorzy(p.getID());
                  zapiszWatki(p.getID());
                  
                  plik.write("      </podforum> \n" );
            }  
            plik.write("     </podfora> \n" );
     }
    
     private void zapiszUsers() throws Exception {
             plik.write("\n   <users> \n" );
            ArrayList users=db_con.getUsers();
            for(int i=0;i<users.size();i++) {
               User u= (User) users.get(i);
                  plik.write("     <user> \n" );
                  plik.write("        <id>"+u.getID()+"</id> \n" );
                  plik.write("        <login>"+u.getLogin()+"</login> \n" );
                  plik.write("        <haslo>"+u.getHaslo()+"</haslo> \n" );
                  plik.write("        <imie>"+u.getImie()+"</imie> \n" );
                  plik.write("        <nazwisko>"+u.getNazwisko()+"</nazwisko> \n" );
                  plik.write("        <imie_nazwisko_prywatne>"+u.ifShowName()+"</imie_nazwisko_prywatne> \n" );
                  plik.write("        <email>"+u.getEmail()+"</email> \n" );
                  plik.write("        <email_prywatne>"+u.ifShowEmail()+"</email_prywatne> \n" );
                  plik.write("        <gg>"+u.getGG()+"</gg> \n" );
                  plik.write("        <gg_prywatne>"+u.ifShowGG()+"</gg_prywatne> \n" );
                  plik.write("        <jabber>"+u.getJabber()+"</jabber> \n" );
                  plik.write("        <jabber_prywatne>"+u.ifShowJabber()+"</jabber_prywatne> \n" );
                  plik.write("        <ostatnie_logowanie>"+u.getLastLog()+"</ostatnie_logowanie> \n" );
                  plik.write("        <biezace_logowanie>"+u.getCurrentLog()+"</biezace_logowanie> \n" );
                  plik.write("        <aktywny>"+u.aktywny()+"</aktywny> \n" );
                  plik.write("        <admin>"+u.admin()+"</admin> \n" );
                  plik.write("        <moderator>"+u.moderator()+"</moderator> \n" );
                  plik.write("      </user> \n" );
            }  
            plik.write("     </users> \n" );
     }
     
    private void zapiszForgetPasswd() throws Exception {
    
     }
    
     private void zapiszModeratorzy(int id_podforum) throws Exception {
         
            plik.write("\n       <moderatorzy> \n" );
            ArrayList users_id=db_con.getIdModeratorzyPodforum(id_podforum);
            for(int i=0;i<users_id.size();i++) {
                   plik.write("         <id_user>"+ ((Integer)users_id.get(i)).intValue()+"</id_user> \n" );
               
                
            }  
            plik.write("      </moderatorzy> \n" );
    
     }
    
    private void zapiszDane() throws Exception {
        plik.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
        plik.write("<!DOCTYPE bee_configuration SYSTEM \"backup.dtd\"> \n");
        plik.write("\n<backup> \n");
         plik.write(" <forum> \n");
          Forum forum=db_con.getForum();
          plik.write("  <nazwa>"+forum.getNazwa()+"</nazwa> \n" );
          plik.write("  <opis>"+forum.getOpis()+"</opis> \n" );
          plik.write("\n   <kategorie> \n" );
          
           ArrayList kat=db_con.getKategorieForum();
            for(int i=0;i<kat.size();i++) {
               Kategoria k=db_con.getKategoria( ((Integer)kat.get(i)).intValue() ) ;
                  plik.write("    <kategoria> \n" );
                  plik.write("     <id>"+k.getID()+"</id> \n" );
                  plik.write("     <tytul>"+k.getNazwa()+"</tytul> \n" );
                  plik.write("     <opis>"+k.getOpis()+"</opis> \n" );
                  plik.write("     <aktywna>"+k.czyAktywna()+"</aktywna> \n" );
                  plik.write("     <prywatna>"+k.czyPrywatna()+"</prywatna> \n" );
                    zapiszPodfora(k.getID());
                  plik.write("    </kategoria> \n" );
            }  
            plik.write("   </kategorie> \n" ); 
         plik.write(" </forum> \n");
         zapiszUsers(); 
        
        plik.write("</backup>");  
     }
    
    public void zrobBackup(javax.servlet.ServletContext app) throws BeeException {
        try
        {
          plik = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(app.getRealPath(FILE_NAME), false)), KODOWANIE);
          zapiszDane();
          plik.close();
        }catch(Exception e)
        {
            BeeIOException nowy = new BeeIOException(Messages.errorXMLWrite());
            nowy.initCause(e);
            throw nowy;
        }   
      
    }
    
}
