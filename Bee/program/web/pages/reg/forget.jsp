<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pl.ltd.bee.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Nowy użytkownik</title>
        <link rel="stylesheet" href="../../styles/temat.css" type="text/css"/>
    </head>
    <body>
        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
        <br><br>
        <table align="center" border="0">
            <tr>
                <td> 
        <% Enumeration flds = request.getParameterNames();
        if (!db_con.isConnected()) {
        try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
        } catch (Exception e) {
            out.print(Messages.errorDataBaseConnection());
        } }
        String klucz=request.getParameter("id");
        if (klucz!=null) {
            String email = db_con.getEmailForgetPasswd(klucz);
            if (email!=null)
            {
              %>
                    <form method="post" action="forget.jsp">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                            <th colspan="2">Nowe hasło:</th>
                            </tr> <tr>
                                <td>Hasło:</td><td><input type="password" size="20" name="haslo1"/></td>
                            </tr> <tr>
                            <td>Hasło (jeszcze raz):</td><td><input type="password" size="20" name="haslo2"/></td>
                            </tr> <tr>        
                                <td align="right" colspan="2"><input type="hidden" size="20" name="email" value="<%out.print(email);%>"/><input type="submit" name="submit" value="OK"/></td>
                            </tr>
                        </table>
                    </form>
            <%            
            } 
        } else 
        {
            String haslo=request.getParameter("haslo1");
            String email=request.getParameter("email");
            if (haslo!=null && email!=null) {
                if(db_con.zmienHasloByEmail(email,haslo)) {
                    db_con.usunZapomnianeHaslo(email);
                    out.print("Hasło zmieniono<br>"); }
                else
                    out.print(Messages.errorChangePasswd());
            }
            out.println("<a href=../../index.jsp>powrot</a><br>"); 
        }
        %>
                </td>
            </tr>
        </table>
    </body>
</html>