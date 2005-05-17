<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead(request,"./..","BeeBB :: Content"));%>
<body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >    
<%@ include file="servletObjects.jsp" %>
<table id="tableSearch" border="0" width="100%">
<tr><th><% 
        Commons.setCachingNever(response);
        out.println(Messages.wielka(Messages.login())+"</th>"+
        "<th>"+Messages.wielka(Messages.name())+" "+Messages.and()+" "+Messages.wielka(Messages.surname())+"</th>"+
        "<th>"+Messages.wielka(Messages.email())+"</th>"+
        "<th>"+Messages.wielka(Messages.lastLogged())+"</th>"+
        "</tr>");

        ArrayList users = db_con.getUsersAktywni(true);
        for(int i=0; i<users.size(); i++)
        {
            User u = (User)users.get(i);
            if (u.getID() != Config.GUEST_ID)
            {
                out.println("<tr>");
                out.println("<td align=\"center\" class=\"tdUsersList\"><table width=\"100%\" border=\"0\"><tr><td align=\"left\"><img align=\"middle\" src=\""+(u.ifMale()?"../images/male.gif\" alt=\""+Messages.wielka(Messages.men()):"../images/female.gif\" alt=\""+Messages.wielka(Messages.woman()))+"\"></td><td>&nbsp;"+Commons.aHref(request,u.getLogin(),"./profile.jsp?uid="+u.getID())+"</td></tr></table></td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowName()?u.getImie()+"<br/>"+u.getNazwisko():"")+"</td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowEmail()?"<a href=\"mailto:"+u.getEmail()+"\" class=\"aHref\">"+u.getEmail()+"</a>":"")+"</td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+u.getLastLog()+"</td>");
                out.println("</tr>");
            }
        }
    
%>

</table>
</body>
</html>
