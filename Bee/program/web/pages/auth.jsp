<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

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
    
<%
       DataBase db_con;
       Object o = application.getAttribute(Config.APPLICATION_OBJECT_DATABASE);
       if (o == null)
       {
           DataBase d = new DataBase();
           application.setAttribute(Config.APPLICATION_OBJECT_DATABASE,d);
           db_con = d;
       }
       else db_con = (DataBase)o;
       
       Config konfiguracja;
       Object ob = application.getAttribute(Config.APPLICATION_OBJECT_CONFIG);
       if (ob == null)
       {
           Config c = new Config();
           application.setAttribute(Config.APPLICATION_OBJECT_CONFIG,c);
           konfiguracja = c;
       }
       else konfiguracja = (Config)ob;
       
       Autoryzator auth;
       Object obj = application.getAttribute(Config.APPLICATION_OBJECT_AUTORYZACJA);
       if (obj == null)
       {
           Autoryzator a = new Autoryzator();
           application.setAttribute(Config.APPLICATION_OBJECT_AUTORYZACJA,a);
           auth = a;
       }
       else auth = (Autoryzator)obj;
       
%>
        <% 
        if (!db_con.isConnected()) {
        try {
        db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
        db_con.setTablePrefix(Config.DATABASE_PREFIX);
        } catch (Exception e) {
        out.print(Messages.errorDataBaseConnection());
        } }
        Enumeration flds = request.getParameterNames();
        try {
         %>
        <br><br>
        <table align="center" border="0">
            <tr>
            <td>
        <%
         if (flds.hasMoreElements()) { 
            String field = (String) flds.nextElement(); 
            if (field.compareTo("logout")==0) {
                auth.wyloguj(response);//auth.zaloguj(Config.GUEST,"",db_con.getUser(Config.GUEST));
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
                        User u = auth.zaloguj(uzytkownik,haslo,db_con,konfiguracja,response);//(uzytkownik,haslo,db_con.getUser(uzytkownik));
                        if (u!=null)
                            response.sendRedirect("../index.jsp");
                        } catch (Exception e) {
                            out.print(e);
                        }
                    }    
            }
                out.println(Messages.errorBadUserOrPass());
         }
        %> 
                <form method="post" action="auth.jsp">
                    <table align="center" cellpadding="2" cellspacing="1" border="0">
                        <tr>
                        <th colspan="2">Zaloguj się</th>
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
          out.println(Messages.errorUnknown());
       }
     %>
    </body>
</html>
