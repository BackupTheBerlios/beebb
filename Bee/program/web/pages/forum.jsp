<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<html>
    <head><title>Forum main page</title></head>
    <body>

        <% 
            int id = 1;
            pl.ltd.bee.DataBase baza = new pl.ltd.bee.DataBase("jdbc:mysql:///Bee","wilk","wilk");
//            pl.ltd.bee.Watek watek = baza.getWatek(id);
           Hashtable watek = baza.getWatek(id);
            out.println(watek);
            //out.println(watek.getTemat());
        %>
    </body>
</html>
