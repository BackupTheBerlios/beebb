<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<% out.println(Commons.htmlHead(request,"./..",Messages.wielka(Messages.forgetPasswd())));%>
    <body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >
<%@ include file="servletObjects.jsp" %>
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
                out.println(Messages.messageForgetPasswdEmailSend() +  "<center>"+Commons.aHref(request,Messages.wielka(Messages.back()), "main.jsp")+"</center>");
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
    </body>
</html>
