<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="pl.ltd.bee.*"%>

<jsp:useBean id="auth" scope="session" class="pl.ltd.bee.Autoryzator" />
<jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />

<html>
    <head><title>Dodaj Wypowiedź</title></head>
    <body>
        
        
        <form method="POST" action="addWypowiedz.jsp">
        Formularz <br><br>
        Tytul: <input type="text" name="tytul"><BR>
        Treść: <textarea></textarea><BR><br>
        <input type="submit">
        
    </body>
</html>
