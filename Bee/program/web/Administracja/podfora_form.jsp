<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<html>
    <jsp:useBean id="pf" scope="request" class="pl.ltd.bee.Podforum" />
     <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
     <% if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   out.print("Blad polaczenia z baza!");
                }
            } %>
      
    <head><title>Podfora</title></head>
    <body>
      <font size="4"> Dodawanie Podforów </font>
         
      
    <form action="./podfora_dodaj.jsp" method="post">
      NAZWA:   <input type="text" name="nazwa" value="<%=pf.getTytul()%>"/> <br/>
      OPIS:   <input type="text" name="opis" value="<%=pf.getOpis()%>"/>  <br/> <br/>
      WYBIERZ KATEGORIE:   <select name="kategoria"> <% ArrayList tytuly=db_con.getTytulyKategorii();
                                                       for (int i=0; i<tytuly.size(); i++) { %>
                                                    <option><%= tytuly.get(i) %></option>
                                                      <%  } %>      
                           </select>  <br/>
              <br/> <input type="submit" value="Dodaj"/>
    </form>
    <br/>
    <% if ( pf.getWiad().compareTo("ok") == 0 ) { %>
        <font color="blue"> Podforum zostało dodane </font>
     <% }  else  {%>   
    <font color="red"> <%= pf.getWiad() %> </font>
    <% } %>
    </body>
</html>
