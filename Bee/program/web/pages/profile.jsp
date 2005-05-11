<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>


<% out.println(Commons.htmlHead("./..","BeeBB :: Content"));%>
    <body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >    
        <table border="0" id="mainTableProfile" width="100%">
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
                User user = db_con.getUser(Integer.parseInt(uid));
                if (user==null) out.println(Messages.errorUnknown()); else {
                
                %>
                <table align="center" class="tableProfile" border="0">
                    <tr><th><% out.print(Messages.nick());%></th><td class="tdProfileField">&nbsp;<% out.print(new String(user.getLogin().getBytes("8859_1"),"UTF-8"));%></td></tr>
                <% if(user.ifShowName()) { %>
                    <tr><th><% out.print(Messages.name());%></th><td class="tdProfileField">&nbsp;<% out.print(new String(user.getImie().getBytes("8859_1"),"UTF-8"));%></td></tr>
                    <tr><th><% out.print(Messages.surname());%></th><td class="tdProfileField">&nbsp;<% out.print(new String(user.getNazwisko().getBytes("8859_1"),"UTF-8"));%></td></tr>
                <% }  if(user.ifShowEmail()) {%>
                    <tr><th><% out.print(Messages.wielka(Messages.email()));%></th><td class="tdProfileField">&nbsp;<% out.print(new String(user.getEmail().getBytes("8859_1"),"UTF-8"));%></td></tr>
                <% } if(user.ifShowGG()) {%>
                    <tr><th><% out.print(Messages.wielka(Messages.number())+ " " + Messages.gg());%></th><td class="tdProfileField">&nbsp;<% out.print(new String(user.getGG().getBytes("8859_1"),"UTF-8"));%></td></tr>
                <% } if(user.ifShowJabber()) {%>
                    <tr><th><% out.print(Messages.wielka(Messages.jabber()));%></th><td class="tdProfileField">&nbsp;<% out.print(new String(user.getJabber().getBytes("8859_1"),"UTF-8"));%></td></tr>
                <% } %>
                    <tr><th><% out.print(Messages.lastLogged());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getCurrentLog());%></td></tr>
                </table>
                <% }
            }
    %>
            </td></tr>
        </table>
    </body>
</html>
