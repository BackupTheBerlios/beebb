<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

    <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
     <jsp:useBean id="kat" scope="request" class="pl.ltd.bee.Kategoria" />

     <%
       String nazwa=request.getParameter("nazwa");
       String opis=request.getParameter("opis");
       
       kat.setNazwa(nazwa);
       kat.setOpis(opis);
       
      if( nazwa.compareTo("")== 0 ) { kat.setWiad("Nalezy podac nazwe kategorii"); %>
      <jsp:forward page="./edycja_podforow.jsp"/>
      <% }
       
         if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   kat.setWiad("Blad polaczenia z baza!");
                   %><jsp:forward page="./edycja_podforow.jsp"/> <%
                }
            }
         
            if ( db_con.czyKategoria(nazwa) ) kat.setWiad("Kategoria o podanej nazwie juz istnieje");
             else
             {
               if (db_con.insertKategoria(nazwa,opis) ) {
                    kat.setWiad("ok");
                    kat.setNazwa("");
                    kat.setOpis("");
               }
               else kat.setWiad("Dodanie Kategorii nie powiodla sie");
             }
       %>
          <jsp:forward page="./edycja_podforow.jsp"/>
