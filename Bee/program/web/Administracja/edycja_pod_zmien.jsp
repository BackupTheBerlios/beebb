<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

    <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
     <jsp:useBean id="p" scope="request" class="pl.ltd.bee.Podforum" />

     <%
       String nazwa=request.getParameter("nazwa");
       String opis=request.getParameter("opis");
       String id_pod=request.getParameter("id_pod");
       String kategoria=request.getParameter("kategoria");
       String id_katb=request.getParameter("id_kat");
       p.setNazwa(nazwa);
       p.setOpis(opis);
       p.setID(id_pod);
       p.setIdKat(id_katb);
       
      if( nazwa.compareTo("")== 0 ) { p.setWiad("Nalezy podac nazwe podforum"); %>
      <jsp:forward page="./edycja_pod.jsp"/>
      <% }
       
         if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   p.setWiad("Blad polaczenia z baza!");
                   %><jsp:forward page="./edycja_pod.jsp"/> <%
                }
            }
            String id_kat=db_con.dajIdKategorii(kategoria);
            if(id_kat.compareTo(id_katb)!=0)
            {   
            if ( db_con.czyPodforum(id_kat, nazwa) ) {
                  p.setWiad("Podforum o podanej nazwie w tej kategorii juz istnieje"); %>
                <jsp:forward page="./edycja_pod.jsp"/>
              <%}
            }
            
            
               if (db_con.updatePodforum(id_pod, id_kat, nazwa, opis) ) {
                    p.setWiad("ok");
                    p.setNazwa("");
                    p.setOpis("");
                 }
               else p.setWiad("Zmiana Podforum nie powiodlo sie");
            
       %>
          <jsp:forward page="./edycja_podforow.jsp"/>