
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

    <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
     <jsp:useBean id="k" scope="request" class="pl.ltd.bee.Kategoria" />

     <%
       String nazwa=request.getParameter("nazwa");
       String opis=request.getParameter("opis");
       String id_kat=request.getParameter("id_kat");
       
       k.setNazwa(nazwa);
       k.setOpis(opis);
       k.setID(id_kat);
       
      if( nazwa.compareTo("")== 0 ) { k.setWiad("Nalezy podac nazwe kategorii"); %>
      <jsp:forward page="./edycja_kat.jsp"/>
      <% }
       
         if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   k.setWiad("Blad polaczenia z baza!");
                   %><jsp:forward page="./edycja_kat.jsp"/> <%
                }
            }
         
            if ( db_con.czyKategoriaInna(nazwa,id_kat) ){
                k.setWiad("Kategoria o podanej nazwie juz istnieje"); %>
                <jsp:forward page="./edycja_kat.jsp"/>
              <%}  
             else
             {
               if (db_con.updateKategoria(id_kat,nazwa,opis) ) {
                    k.setWiad("ok");
                    k.setNazwa("");
                    k.setOpis("");
               }
               else k.setWiad("Zmiana Kategorii nie powiodla sie");
             }
       %>
          <jsp:forward page="./edycja_podforow.jsp"/>