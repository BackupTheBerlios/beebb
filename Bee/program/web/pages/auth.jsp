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
        <title>BeeBB :: Autoryzacja</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
    </head>
    <body>
        <jsp:useBean id="auth" scope="session" class="pl.ltd.bee.Autoryzator" />
        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
        <% 
        if (!db_con.isConnected()) {
        try {
        db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
        db_con.setTablePrefix(Config.DATABASE_PREFIX);
        } catch (Exception e) {
        out.print("Blad polaczenia z baza!");
        } }
        Enumeration flds = request.getParameterNames();
        try {
         %>
        <br><br>
        <form method="post" action="auth.jsp">
        <table align="center" border="0">
            <tr>
            <td>
        <%
         if (flds.hasMoreElements()) { 
            String field = (String) flds.nextElement(); 
            if (field.compareTo("logout")==0) {
                auth.zaloguj(Config.GUEST,"",db_con.getUser(Config.GUEST));
                response.sendRedirect("../index.jsp");
            }
            else {
                String uzytkownik;
                String haslo;
                
                    uzytkownik=request.getParameter("user");
                    haslo=request.getParameter("haslo");
                    if (uzytkownik!=null && haslo!=null)
                    {
                        try {
                        User u = auth.zaloguj(uzytkownik,haslo,db_con.getUser(uzytkownik));
                        
                        if (u!=null)
                            response.sendRedirect("../index.jsp");
                        } catch (Exception e) {
                            out.print(e);
                        }
                    }    
            }
                out.println("<p class=\"error\">Błędny użytkownik lub hasło!</p>");
         }
        %> 
                <table align="center" cellpadding="2" cellspacing="1" border="0">
                    <tr>
                    <th colspan="2">Zaloguj sie</th>
                    </tr> <tr>
                        <td>Użytkownik:</td><td><input type="text" size="20" name="user"/></td>
                    </tr> <tr>
                    <td>Hasło:</td><td><input type="password" size="20" name="haslo"/></td>
                    </tr> <tr>        
                        <td><a href="forgetPasswd.jsp">Zapomniałem hasło</a></td><td align="right"><input type="submit" name="submit" value="Loguj"/></td>
                    </tr>
                </table>
                </form>
                <br>
                <p>Nie masz jeszcze konta? <a href="addUser.jsp">Zarejestruj się</a></p>
            </td>
            </td>
        </table>
     <%
       }
       catch (Exception e) {
          out.println("<p class=\"error\">Blad! skontaktuj sie z administratorem strony!<p>");
       }
     %>
    </body>
</html>
