<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
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
        <title>BeeBB :: Zapomniane hasło</title>
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
       
%>
        <br/><br/>
        <table align="center" border="0">
            <tr>
            <td>
        <% 
        if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                    out.print(Messages.errorDataBaseConnection());
            } 
        }
        Enumeration flds = request.getParameterNames();
        if (flds.hasMoreElements()) { 
            String uzytkownik=request.getParameter("user");
            String email=request.getParameter("email");
            if (uzytkownik!=null && email!=null) {
                
                if (db_con.getUser(uzytkownik,email)!=null) {
                    Random r = new Random();

                    String numer = Long.toHexString(r.nextLong());
                    while (!db_con.sprawdzKluczZapomnianeHaslo(numer))        
                        numer = Long.toHexString(r.nextLong());
    
                    if (!db_con.wstawZapomnianeHaslo(email,numer)) 
                        out.print(Messages.errorDataBaseConnection());
                    else
                        SendMail.send(email,Config.FORGET_MAIL_SUBJECT,"Witaj "+ uzytkownik + "\n" + Config.FORGET_MAIL_BODY + Config.URL_FORUM + "/pages/reg/forget.jsp?id=" + numer);
                }
                out.println("Email z kluczem powinien zostać wysłany<br/><center><a href=\"./main.jsp\">powrot</a></center>");
            } else {
                response.sendRedirect("forgetPasswd.jsp");
            }
        }
        else
        {
         %>
                <form method="post" action="forgetPasswd.jsp">
                    <table align="center" cellpadding="2" cellspacing="1" border="0">
                        <tr>
                        <th colspan="2">Zapomniane hasło:</th>
                        </tr> <tr>
                            <td>Nick:</td><td><input type="text" size="20" name="user"/></td>
                        </tr> <tr>
                        <td>E-mail:</td><td><input type="text" size="20" name="email"/></td>
                        </tr> <tr>        
                            <td align="right" colspan="2"><input type="submit" name="submit" value="Wyślij"/></td>
                        </tr>
                    </table>
                </form>
        <%     
        }
        %>
            </td>
            </td>
        </table>
    </body>
</html>
