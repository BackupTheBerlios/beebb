<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Dodawanie podforow</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/> 
    </head>
    <body>
        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
     <% if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   out.print("Blad polaczenia z baza!");
                }
            } %>
      
       <br/> 
       <p align="center">Kategoria: <%=request.getParameter("tytul")%> </p>
       <p align="center">Opis: <%=request.getParameter("opis")%> </p>
       <br/>
     
    <form action="./podfora_dodaj.jsp" method="post">
      <table align="center" cellpadding="2" cellspacing="1" border="0">
       <caption> Dodawanie Podforów </caption>
       <tr> <th> Next </th> <th> Tytul </th> <th> Opis </th> </tr>
       <% for(int i=0;i<5;i++) { %>       
      <tr> <td> <input type="button" name="plusik" value="+"/>  </td> 
           <td> <input size="40" type="text" name="nazwa<%=i%>" value=""/> </td>
           <td> <input size="40" type="text" name="opis<%=i%>" value=""/>  </td> 
      </tr>
        <input type="hidden" name="id_kat<%=i%>" value="<%=request.getParameter("id")%>"/>
       <% }%>
      </table>
         
        <p align="center"> <input size="25" type="submit" size="30" value="Dodaj"/> </p>
     </form>
     
    </body>
</html>
