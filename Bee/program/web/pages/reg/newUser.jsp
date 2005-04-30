<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead("./../..",Messages.wielka(Messages.newUser())));%>
    <body onload="swapIframes();resizeNewUser()" onresize="resizeNewUser()">
<%@ include file="../servletObjects.jsp" %>    
        <table align="center" border="0" id="tableNewUser"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr>
                <td> 
        <br/><br/>
        <% Enumeration flds = request.getParameterNames();
        String klucz=request.getParameter("id");
        if (klucz!=null) {
            String nick = db_con.getLoginNewUser(klucz);
            if (nick!=null)
            {
                 if(!db_con.setAktywnyUser(nick))
                    out.println(Messages.errorUserCreate()); 
                 else {
                    db_con.usunKluczNewUser(klucz);
                    out.println(Messages.wielka(Messages.user()) + " " + nick + " " + Messages.hasBeenAdded() + "<br/><br/><span style=\"cursor: pointer;\" onclick=\"hrefClick('../../index.jsp')\">" + Messages.wielka(Messages.back()) + "</span><br/>"); 
                 }
            } else 
                out.print(Messages.errorKeyNewUser());
            
        } else out.println("<span style=\"cursor: pointer;\" onclick=\"hrefClick('../../index.jsp')\">" + Messages.wielka(Messages.back()) + "</span><br/>"); 
        %>
                </td>
            </tr>
        </table>
    </body>
</html>
