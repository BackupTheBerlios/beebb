<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead("./..",Messages.wielka(Messages.forgetPasswd())));%>
    <body onload="swapIframes();resizeForgetPass()" onresize="resizeForgetPass()">
<%@ include file="servletObjects.jsp" %>
    <table align="center" border="0" id="tableForgetPass"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr>
            <td>
        <br/><br/>
        <% 
        Enumeration flds = request.getParameterNames();
        if (flds.hasMoreElements()) { 
            String uzytkownik=request.getParameter("user");
            String email=request.getParameter("email");
            if (uzytkownik!=null && email!=null) {
                
                if (db_con.getUser(uzytkownik)!=null) {
                    Random r = new Random();

                    String number = Long.toHexString(r.nextLong());
                    while (!db_con.sprawdzKluczZapomnianeHaslo(number))        
                        number = Long.toHexString(r.nextLong());
    
                    if (!db_con.wstawZapomnianeHaslo(email,number)) 
                        out.print(Messages.errorDataBaseConnection());
                    else
                        SendMail.send(email,Config.FORGET_MAIL_SUBJECT,Messages.wielka(Messages.welcome()) + uzytkownik + "\n" + Config.FORGET_MAIL_BODY + Config.URL_FORUM + "/pages/reg/forget.jsp?id=" + number);
                }
                out.println(Messages.messageForgetPasswdEmailSend() +  "<center><span style=\"cursor: pointer;\" onclick=\"hrefClick('main.jsp')\">" + Messages.wielka(Messages.back()) + " </span></center>");
            } else {
                response.sendRedirect("forgetPasswd.jsp");
            }
        }
        else
        {
         %>
                <form method="post" action="forgetPasswd.jsp" onsubmit="return submitForgetPasswd('<% out.print(Messages.wielka(Messages.errorFieldNeeded()));%>')">
                    <table align="center" cellpadding="2" cellspacing="1" border="0">
                        <tr>
                        <th colspan="2"><%out.println(Messages.forgetPasswd());%>:</th>
                        </tr> <tr>
                            <td><% out.println(Messages.wielka(Messages.nick())); %>:</td><td><input type="text" size="20" name="user" id="user"/></td>
                        </tr> <tr>
                        <td><% out.println(Messages.wielka(Messages.email())); %>:</td><td><input type="text" size="20" name="email" id="email"/></td>
                        </tr> <tr>        
                            <td align="right" colspan="2"><input type="submit" name="submit" value="<% out.println(Messages.wielka(Messages.send())); %>"/></td>
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
