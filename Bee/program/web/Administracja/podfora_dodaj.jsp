<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

    <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
     <jsp:useBean id="pf" scope="request" class="pl.ltd.bee.Podforum" />

     <%
       String tytul=request.getParameter("nazwa");
       String opis=request.getParameter("opis");
       String id_kat=request.getParameter("id_kat");
       
       pf.setNazwa(tytul);
       pf.setOpis(opis);
      
       
      if( tytul.compareTo("")== 0 ) { pf.setWiad("Nalezy podac nazwe podforum"); %>
      <jsp:forward page="./edycja_podforow.jsp"/>
      <% }
       
         if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   pf.setWiad("Blad polaczenia z baza!");
                   %><jsp:forward page="./edycja_podforow.jsp"/> <%
                }
            }
    
         if ( db_con.czyPodforum(id_kat, tytul) ) pf.setWiad("Kategoria o podanej nazwie juz istnieje");
             else
            {
              if (db_con.insertPodforum(id_kat, tytul, opis) ) { 
                  pf.setWiad("ok");
                  pf.setNazwa("");
                  pf.setOpis("");
              }
               else pf.setWiad("Dodanie Podforum nie powiodla sie");
            }
       
       %>
          <jsp:forward page="./edycja_podforow.jsp"/>