/*
 * Kategoria.java
 *
 * Created on 9 marzec 2005, 17:55
 */

package pl.ltd.bee;

import java.util.ArrayList;

/**
 *
 * @author pawelb i sopi
 */
public class Kategoria {
    
    private int ID;
    private String Tytul;
    private String Opis;
    private ArrayList Podfora;
    private DataBase db;
    
    private String Wiad;

    
    /** Konstruktor bezargumentowy
     */
    public Kategoria()
    { }
    
     /** Tworzy instancje klasy Kategoria zawierajaca w Podfora
     * obiekty klasy Podforum
     * @param db obiekt klasy DataBase
     * @param ID identyfikator kategorii w bazie danych
     * @param Tytul nazwa kategorii
     * @param Opis Opis kategorii
     */

    public Kategoria(DataBase db,String ID, String Tytul, String Opis) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        this.Opis=Opis;
        this.db=db;
        this.Podfora=db.getPodforaKategoriiAll(ID);
      }
    
    /** Tworzy nowa instancje klasy Kategoria
     * @param ID identyfikator kategorii w bazie danych
     * @param Tytul nazwa kategorii
     */

    public Kategoria(String ID, String Tytul,DataBase db) {
        this.ID=Integer.decode(ID).intValue();
        this.Tytul=Tytul;
        this.Podfora=db.getPodforaKategorii(this.ID);
        this.db=db;
    }
    
    /** Zwraca identyfikator forum
     * @return zwraca long bedacy identyfikatorem kategorii w bazie
     */
    public long getID() {
        return ID;
    }
     /** Zwraca nazwe kategori
     * @return string bedacy nazwa kategori
     */
    public String getNazwa() {
        if (Tytul==null) return "";
        return Tytul;
    }
    

    /** Zwraca opis kategori
     * @return zwraca string bedacy opisem kategori
     */
    public String getOpis() {
        if (Opis==null) return "";
        return Opis;
    }
    
    /** Ustawia id kategori
     * @param id String ustawianego id
     */
    public void setID(String id) {
        this.ID=Integer.decode(id).intValue();
    }
    
    /** Ustawia opis kategori
     * @param op String ustawianego opisu
     */
    public void setOpis(String op) {
        this.Opis=op;
    }
    
    /** Ustawia opis kategori
     * @param op String ustawianej nazwy
     */
    public void setNazwa(String naz) {
        this.Tytul=naz;
    }
     /** Zwraca opis kategori
     * @return zwraca string bedacy wiadomoscia (Wykorzystuje w dodawaniu kategori 
     */
    public String getWiad() {
        if (Wiad==null) return "";
        return Wiad;
    }
    
     
     /** Zwraca liste podforow
     * @return zwraca ArralList bedacy lista podforow
     */
    public ArrayList getPodfora() {
        return Podfora;
    }
    
    /** Ustawia opis kategori
     * @param w String ustawianego opisu
     */
    public void setWiad(String w) {
        this.Wiad=w;
    }
    
    /**
     * Metoda powoduje wypisanie forum na przekazany strumien
     * @param strona strumien wyjsciowy
     */
    public void printJSP(javax.servlet.jsp.JspWriter strona) throws java.io.IOException
    {
        strona.println("<tr>");
        strona.println("<td class=\"tdTytulKategorii\" colspan=\"5\" height=\"20\"><span class=\"tytulKategorii\"><a href=\"index.jsp?kid="+ this.ID +"\" class=\"aTytulKategorii\">" + Tytul + "</a></span></td>");
        strona.println("</tr>");
        ArrayList podfora = db.getPodforaKategorii(this.ID);
        for(int i=0;i<podfora.size();i++)
        {
            Podforum p = db.getPodforum(((Integer)podfora.get(i)).intValue());
            p.printJSPHeader(strona);
        }

    }
    
}

