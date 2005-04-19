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
        <title>BeeBB :: editProfile</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>
    </head>
    <body onload="resizeAuth()" onresize="resizeAuth()">
    
        <%@ include file="servletObjects.jsp" %>
        <table width="100%" border="0" id="tableAuth"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr><td>
                
    <%
                User user = auth.getUser(request,db_con);
                if (user!=null) {
    %>
                <form method="POST" action="editProfile.jsp">
                <table align="center" class="tableProfile" border="0">
                    <tr><th><% out.print(Messages.nick());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getLogin());%></td><td></td></tr>
                    <tr><th><% out.print(Messages.name());%></th><td class="tdProfileField"><input type="text" name="imie" size="35" value="<% out.print(user.getImie());%>"></td><td class="tdProfileField" rowspan="2" valign="bottom"><input type="checkbox" name="imieNazwiskoPrywatne" <%if(!user.ifShowName()) out.print("checked");%>> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                    <tr><th><% out.print(Messages.subname());%></th><td class="tdProfileField"><input type="text" name="nazwisko" size="35" value="<% out.print(user.getNazwisko());%>"></td><td></td></tr>
                    <tr><th><% out.print(Messages.wielka(Messages.email()));%></th><td class="tdProfileField"><input type="text" name="email" size="35" value="<% out.print(user.getEmail());%>"></td><td class="tdProfileField"><input type="checkbox" name="imieNazwiskoPrywatne" <%if(!user.ifShowName()) out.print("checked");%>> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                    <tr><th><% out.print(Messages.wielka(Messages.number())+ " " + Messages.gg());%></th><td class="tdProfileField"><input type="text" name="gg" size="35" value="<% out.print(user.getGG());%>"></td><td class="tdProfileField"><input type="checkbox" name="imieNazwiskoPrywatne" <%if(!user.ifShowName()) out.print("checked");%>> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                    <tr><th><% out.print(Messages.wielka(Messages.jabber()));%></th><td class="tdProfileField"><input type="text" name="jabber" size="35" value="<% out.print(user.getJabber());%>"></td><td class="tdProfileField"><input type="checkbox" name="imieNazwiskoPrywatne" <%if(!user.ifShowName()) out.print("checked");%>> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                    <tr><td colspan="2" align="right"><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td></tr>
                </table>
                </form>
                <br/><br/>
                <form method="POST" action="editProfile.jsp">
                <table align="center" class="tableProfile" border="0">
                    <tr><th><% out.print(Messages.wielka(Messages.old()) + " " + Messages.password());%></th><td class="tdProfileField"><input type="password" name="oldpasswd" size="35"></td></tr>
                    <tr><th><% out.print(Messages.wielka(Messages.password()));%></th><td class="tdProfileField"><input type="password" name="passwd1" size="35"></td></tr>
                    <tr><th><% out.print(Messages.wielka(Messages.password()) + " " + Messages.oneMoreTime()); %></th><td class="tdProfileField"><input type="password" name="passwd2" size="35"></td></tr>
                    <tr><td colspan="2" align="right"><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td></tr>
                </table>
                </form>
                <br/>
                
     <%
                } else {
                    out.println(Messages.makeError(Messages.errorNotLoggedIn()));
                }
      %>
            </td>
            </tr>
        </table>
    </body>
</html>
