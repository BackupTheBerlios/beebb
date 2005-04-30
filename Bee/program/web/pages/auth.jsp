<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead("./..",Messages.wielka(Messages.logInYourself())));%>
    <body onload="<% if ((request.getParameter("user") == null) && (request.getParameter("logout") == null)) out.print("swapIframes();");%>resizeAuth()" onresize="resizeAuth()">
    
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
                            <td><span style="cursor: pointer;" onclick="hrefClick('forgetPasswd.jsp')"><%out.println(Messages.iForgetPasswd());%> </span></td><td align="right"><input type="submit" name="submit" value="Loguj"/></td>
                        </tr>
                    </table>
                </form>
                <br>
                <p><%out.println(Messages.doNotHaveAccount());%> <span style="cursor: pointer;" onclick="hrefClick('addUser.jsp')"><%out.println(Messages.registerYourself());%></span></p>
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
