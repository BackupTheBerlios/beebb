<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Content</title>
        <link rel="stylesheet" href="./../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>

    </head>
<body onload="resizeProfile()" onresize="resizeProfile()">    
<table border="0" id="mainTableProfile" width="100%"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
<tr><td>
<%@ include file="servletObjects.jsp" %>
<%
//       User user = auth.getUser(requestdb_con);
//       if (user == null)
//       {
//           out.println(Messages.errorNotLoggedIn());
//       }
//       else
//       {
            String uid = request.getParameter("uid");
            if (uid == null) out.println(Messages.errorUnknown());
            else
            {   
                User usr = auth.getUser(request,db_con);
                if (uid.compareTo(new String().valueOf(Config.GUEST_ID)) !=0 && usr!=null && (new String().valueOf(usr.getID())).compareTo(uid) == 0 ) {
                    out.println("To ty :-)<br/> i tu będzie jakiś ficzer do edycji");
                } else {
                User user = db_con.getUser(Integer.parseInt(uid));
                %>
                <table align="center" class="tableProfile" border="0">
                <tr><th><% out.print(Messages.nick());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getLogin());%></td></tr>
                <% if(user.ifShowName()) { %>
                <tr><th><% out.print(Messages.name());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getImie());%></td></tr>
                <tr><th><% out.print(Messages.subname());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getNazwisko());%></td></tr>
                <% }  if(user.ifShowEmail()) {%>
                <tr><th><% out.print(Messages.wielka(Messages.email()));%></th><td class="tdProfileField">&nbsp;<% out.print(user.getEmail());%></td></tr>
                <% } if(user.ifShowGG()) {%>
                <tr><th><% out.print(Messages.wielka(Messages.number())+ " " + Messages.gg());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getGG());%></td></tr>
                <% } if(user.ifShowJabber()) {%>
                <tr><th><% out.print(Messages.wielka(Messages.jabber()));%></th><td class="tdProfileField">&nbsp;<% out.print(user.getJabber());%></td></tr>
                <% } %>
                <tr><th><% out.print(Messages.lastLogged());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getLastLog());%></td></tr>
                </table>
                <%
                }        
            }
    %>
    </td></tr>
    </table>
    </body>
</html>
