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
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>
    </head>
    <body onload="resizeAuth()" onresize="resizeAuth()">
    
<%@ include file="servletObjects.jsp" %>
<table width="100%" border="0" id="tableAuth"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
<tr><td>
        <% 
        Enumeration flds = request.getParameterNames();
        try {
         %>
        <br/><br/>
        <table align="center" border="0">
            <tr>
            <td>
        <%
         if (flds.hasMoreElements()) { 
            String field = (String) flds.nextElement(); 
            if (field.compareTo("logout")==0) {
                auth.wyloguj(response);//auth.zaloguj(Config.GUEST,"",db_con.getUser(Config.GUEST));
                response.sendRedirect("main.jsp");
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
                            response.sendRedirect("main.jsp");
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
                        <th colspan="2"><%out.println(Messages.logInYourself());%></th>
                        </tr> <tr>
                            <td><%out.println(Messages.wielka(Messages.user()));%>:</td><td><input type="text" size="20" name="user"/></td>
                        </tr> <tr>
                        <td><%out.println(Messages.wielka(Messages.password()));%>:</td><td><input type="password" size="20" name="haslo"/></td>
                        </tr> <tr>        
                            <td><a href="forgetPasswd.jsp"><%out.println(Messages.iForgetPasswd());%> </a></td><td align="right"><input type="submit" name="submit" value="Loguj"/></td>
                        </tr>
                    </table>
                </form>
                <br>
                <p><%out.println(Messages.doNotHaveAccount());%> <a href="addUser.jsp"><%out.println(Messages.registerYourself());%></a></p>
            </td>
            </tr>
        </table>
     <%
       }
       catch (Exception e) {
          out.println(Messages.errorUnknown());
       }
     %>
    </body>
</html>
