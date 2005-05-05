<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead("./..","BeeBB :: Content"));%>
<body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >    
<%@ include file="servletObjects.jsp" %>
<table id="tableSearch" border="0" width="100%">
<tr><th><% out.print(Messages.wielka(Messages.login())+"</th><th>"+Messages.wielka(Messages.name())+"</th><th>"+Messages.wielka(Messages.surname())+
        "</th><th>"+Messages.wielka(Messages.email())+"</th><th>"+Messages.wielka(Messages.gg())+"</th><th>"+Messages.wielka(Messages.jabber())+
        "</th><th>"+Messages.wielka(Messages.lastLogged())+"</th></tr>");

        ArrayList users = db_con.getUsersAktywni(true);
        for(int i=0; i<users.size(); i++)
        {
            User u = (User)users.get(i);
            if (u.getID() != Config.GUEST_ID)
            {
                out.print("<tr>");
                out.print("<td align=\"center\" class=\"tdUsersList\">&nbsp;"+Commons.aHref(u.getLogin(),"./profile.jsp?uid="+u.getID())+"</td>");
                out.print("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowName()?u.getImie():"")+"</td>");
                out.print("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowName()?u.getNazwisko():"")+"</td>");
                out.print("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowEmail()?"<a href=\"mailto:"+u.getEmail()+"\" class=\"aHref\">"+u.getEmail()+"</a>":"")+"</td>");
                out.print("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowGG()?"<a href=\"gg:"+u.getGG()+"\"><img border=\"0\" src=\"http://www.gadu-gadu.pl/users/status.asp?id="+u.getGG()+"&amp;style=1\" alt=\""+u.getGG()+"\"></a>":"")+"</td>");
                out.print("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowJabber()?u.getJabber():"")+"</td>");
                out.print("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+u.getLastLog()+"</td>");
                out.print("</tr>");
            }
        }
    
%>

</table>
</body>
</html>
