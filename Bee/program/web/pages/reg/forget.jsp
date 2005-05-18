<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead(request,"./../..",Messages.wielka(Messages.forgetPasswd())));
 String css = Commons.getQueryStyle(request);
%>
    <body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);">
<%@ include file="../servletObjects.jsp" %>
        <br/><br/>
        <% 
        String klucz =request.getParameter("id");
        if (klucz!=null)
            {
            String email = db_con.getEmailForgetPasswd(klucz);
            if (email!=null) {
              %>
                    <form method="post" action="forget.jsp<% out.print(css.length()>0?"?"+css:"");%>">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                            <th colspan="2"><%out.println(Messages.newPassword());%>:</th>
                            </tr> <tr>
                                <td class="tdForget"><%out.println(Messages.wielka(Messages.password()));%>:</td><td><input type="password" size="20" name="haslo1"/></td>
                            </tr> <tr>
                            <td class="tdForget"><%out.println(Messages.wielka(Messages.password()) + " (" + Messages.oneMoreTime()+ ")");%>:</td><td><input type="password" size="20" name="haslo2"/></td>
                            </tr> <tr>        
                                <td align="right" colspan="2"><input type="hidden" size="20" name="email" value="<%out.print(email);%>"/><input type="submit" name="submit" value="<%out.println(Messages.wielka(Messages.ok()));%>"/></td>
                            </tr>
                        </table>
                    </form>
            <% }
        } else 
        {
            String haslo=request.getParameter("haslo1");
            String email=request.getParameter("email");
            if (haslo!=null && email!=null) {
                if(db_con.zmienHasloByEmail(email,haslo)) {
                    db_con.usunZapomnianeHaslo(email);
                    out.print(Messages.passwordChanged() + "<br>"); }
                else
                    out.print(Messages.errorChangePasswd());
            }
            out.println("<a class=\"aHref\" href=\"./../../index.jsp\">"+Messages.wielka(Messages.back())+"</a><br/>"); 
        }
        %>
    </body>
</html>
