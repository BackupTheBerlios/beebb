<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<html>
    <jsp:useBean id="kat" scope="request" class="pl.ltd.bee.Kategoria" />
    <head><title>Kategorie</title></head>
    <body>
      <font size="4"> Dodawanie Kategorii </font>
         
      
    <form action="./kategorie_dodaj.jsp" method="post">
      NAZWA:   <input type="text" name="nazwa" value="<%=kat.getNazwa()%>"/> <br/>
      OPIS:   <input type="text" name="opis" value="<%=kat.getOpis()%>"/>  <br/>
              <br/> <input type="submit" value="Dodaj"/>
    </form>
    <br/>
    <% if ( kat.getWiad().compareTo("ok") == 0 ) { %>
        <font color="blue"> Kategoria została dodana </font>
     <% }  else  {%>   
    <font color="red"> <%= kat.getWiad() %> </font>
    <% } %>
    </body>
</html>
