<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>

     <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
      <%! 
          String dajTN(String param)
            {
              if (param==null) return "N";
              return "T";
            }
      
         %>
<html>
    <head><title> Zmiana uprawnien </title></head>
<body>
       
      <% 
            if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   out.print("Blad polaczenia z baza!");
                }
            }
          %>
         <% 
          int id=Integer.parseInt((String) request.getParameter("id"));
          boolean pom=db_con.zmienUpr(id,dajTN(request.getParameter("admin")), dajTN(request.getParameter("moderator")), dajTN(request.getParameter("aktywny")));
          if (pom) {%>
                <font color="blue"> Uprawnienia zostały zmienione </font> 
           <% }else {%>
           <font color="red">Zmiana uprawnien nie powiodla sie </font>
             <% } %>
</body>
</html>