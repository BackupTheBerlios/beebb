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
       <%! ArrayList u; %>
        <% 
            if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   out.print("Blad polaczenia z baza!");
                }
            } 
            u=db_con.getUsers(); %>
    <head><title>Uprawnienia</title></head>
    <body>
     <font size="6"> Edycja uprawnien </font>
     <table border="1" align="center">
      <th>Login</th>
      <th>Imie</th>
      <th>Nazwisko</th>
     <% for(int i=0; i<u.size(); i++) 
        { Hashtable pom=(Hashtable) u.get(i);
          
          String id=(String) pom.get("ID");
          String login=(String) pom.get("LOGIN");
          String imie=(String) pom.get("IMIE");
          String nazwisko=(String) pom.get("NAZWISKO");
          String email=(String) pom.get("EMAIL");
          String gg=(String) pom.get("GG");
          String jabber=(String) pom.get("JABBER");
          String czy_admin=(String) pom.get("ADMIN");
          String czy_moderator=(String) pom.get("MODERATOR");
          String czy_aktywny=(String) pom.get("AKTYWNY");
         %><tr> <td> <%=login%> </td>
                <td> <%=imie%> </td>
                <td> <%=nazwisko%> </td>
                <td> <form action="user_dane.jsp" method="post" target="prawa">
                        <input type="hidden" name="id" value="<%=id%>">
                        <input type="hidden" name="login" value="<%=login%>">
                        <input type="hidden" name="imie" value="<%=imie %>">
                        <input type="hidden" name="nazwisko" value="<%=nazwisko%>">
                        <input type="hidden" name="email" value="<%=email%>">
                        <input type="hidden" name="gg" value="<%=gg%>">
                        <input type="hidden" name="jabber" value="<%=jabber%>">
                        <input type="hidden" name="czy_admin" value="<%=czy_admin%>">
                        <input type="hidden" name="czy_moderator" value="<%=czy_moderator%>">
                        <input type="hidden" name="czy_aktywny" value="<%=czy_aktywny%>">
                        <input type="submit" value="Edytuj">
                     </form>
                </td>
           </tr> <%       
        }
            %>
     </table>      
    </body>
</html>
