<%@ page language="java" import="java.util.*"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<html>
    <head><title>JSP Page</title></head>
    <body>
        <jsp:useBean id="db_con" class="pl.ltd.bee.DataBase" scope="application"/>
    <%
       pl.ltd.bee.Watek w = db_con.getWatek(1);
       if (w!=null) {
            out.println("Temat: " + w.getTemat());
       } else {
            out.println("Brak polaczenia z baza!/Brak takiego watku!<br>");
       }
    %>

    </body>
</html>
