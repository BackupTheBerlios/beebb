<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<% out.println(Commons.htmlHead(request,"./..",Messages.wielka(Messages.logInYourself())));
 String css = Commons.getQueryStyle(request);
%>
    <body onload="swapIframes();reloadHeader('./header.jsp<% out.print(css.length()>0?"?"+css:"");%>');resizeMain();setResizeFunction(resizeMain);" >
    
<%@ include file="servletObjects.jsp" %>
        <% Enumeration flds = request.getParameterNames();
        try {
         %>
        <br/><br/>
        <table align="center" border="0">
            <tr>
            <td>
        <%
         boolean bez_formy = false;
         String zm=request.getParameter("zm");
         String logout=request.getParameter("logout");
         if (logout!=null) {
             auth.wyloguj(response);//auth.zaloguj(Config.GUEST,"",db_con.getUser(Config.GUEST));
             out.println(Commons.aHref(request,Messages.wielka(Messages.back()),"main.jsp"));
             bez_formy = true;
          }
            else {
             if (zm!=null) {
                    String uzytkownik=request.getParameter("user");
                    String haslo=request.getParameter("haslo");
                    if (uzytkownik!=null && haslo!=null)
                    {
                        try {
                        User u = auth.zaloguj(uzytkownik,haslo,db_con,konfiguracja,response);//(uzytkownik,haslo,db_con.getUser(uzytkownik));
                        if (u!=null)
                              {
                              String user_css = u.getStyle();
                              if (user_css.length() > 0) user_css = "?style="+user_css;
                              out.println("<script type=\"text/javascript\">reloadHeader('./header.jsp"+user_css+"');</script>");
                              out.println(Commons.aHref(request,Messages.wielka(Messages.back()),"main.jsp"+user_css));
                              bez_formy = true;
                              }
                        } catch (Exception e) {
                            out.print(e);
                        }
                    }
                    else 
                        out.println(Messages.errorBadUserOrPass());
             }
            }
         if (!bez_formy){
        %> 
                <form method="post" action="auth.jsp<% out.print(css.length()>0?"?"+css:"");%>">
                    <table align="center" cellpadding="2" cellspacing="1" border="0">
                        <tr>
                        <th colspan="2"><%out.println(Messages.logInYourself());%></th>
                        </tr> <tr>
                            <td class="tdLogIn"><%out.println(Messages.wielka(Messages.user()));%>:</td><td class="tdLogIn"><input type="text" size="20" name="user"/></td>
                        </tr> <tr>
                        <td class="tdLogIn"><%out.println(Messages.wielka(Messages.password()));%>:</td><td class="tdLogIn"><input type="password" size="20" name="haslo"/></td>
                        </tr> <tr>        
                            <td><% out.println(Commons.aHref(request,Messages.iForgetPasswd(),"forgetPasswd.jsp"));%></td><td align="right"><input type="hidden" name="zm" value=""/><input type="submit" name="submit" value="Loguj"/></td>
                        </tr>
                    </table>
                </form>
                <br/>
                <p><%out.println(Messages.doNotHaveAccount());out.println(Commons.aHref(request,Messages.wielka(Messages.registerYourself()),"addUser.jsp"));%> </p>
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
