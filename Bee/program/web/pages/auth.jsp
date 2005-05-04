<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead("./..",Messages.wielka(Messages.logInYourself())));%>
    <body onload="<% if ((request.getParameter("user") == null)) out.print("swapIframes();");%>reloadHeader('./header.jsp');resizeMain();setResizeFunction(resizeMain);" >
    
<%@ include file="servletObjects.jsp" %>
        <% 
        Enumeration flds = request.getParameterNames();
        try {
         %>
        <br/><br/>
        <table align="center" border="0">
            <tr>
            <td>
        <%
         boolean bez_formy = false;
         if (flds.hasMoreElements()) { 
            String field = (String) flds.nextElement(); 
            if (field.compareTo("logout")==0) {
                auth.wyloguj(response);//auth.zaloguj(Config.GUEST,"",db_con.getUser(Config.GUEST));
                out.println(Commons.aHref(Messages.wielka(Messages.back()),"main.jsp"));
                bez_formy = true;
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
                              {
                              out.println(Commons.aHref(Messages.wielka(Messages.back()),"main.jsp"));
                              bez_formy = true;
                              }
                        } catch (Exception e) {
                            out.print(e);
                        }
                    }
                    else 
                        out.println(Messages.errorBadUserOrPass());
            }
            if (!bez_formy)
                out.println(Messages.errorBadUserOrPass());
         }
         if (!bez_formy){
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
                            <td><% out.println(Commons.aHref(Messages.iForgetPasswd(),"forgetPasswd.jsp"));%></td><td align="right"><input type="submit" name="submit" value="Loguj"/></td>
                        </tr>
                    </table>
                </form>
                <br/>
                <p><%out.println(Messages.doNotHaveAccount());out.println(Commons.aHref(Messages.wielka(Messages.registerYourself()),"addUser.jsp"));%> </p>
     <%
         }
       }
       catch (Exception e) {
          out.println(Messages.errorUnknown());
       }
     %>
            </td>
            </tr>
        </table>
    </body>
</html>
