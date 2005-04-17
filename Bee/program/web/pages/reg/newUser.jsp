<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pl.ltd.bee.*"%>
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
        <title>BeeBB :: Nowy użytkownik</title>
        <link rel="stylesheet" href="../../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="./../../js/iframe_resize.js"></script>
    </head>
    <body onload="resizeNewUser()" onresize="resizeNewUser()">
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
                    out.println(Messages.wielka(Messages.user()) + " " + nick + " " + Messages.hasBeenAdded() + "<br/><br/><a href=\"../../index.jsp\">" + Messages.back() + "</a><br/>"); 
                 }
            } else 
                out.print(Messages.errorKeyNewUser());
            
        } else out.println("<a href=\"../../index.jsp\">" + Messages.back() + "</a><br/>"); 
        %>
                </td>
            </tr>
        </table>
    </body>
</html>
