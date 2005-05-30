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
        out.println(Messages.wielka(Messages.groups())+"</th>"+
        "</tr>");

        ArrayList grupy = db_con.getGroups();
        for(int i=0; i<grupy.size(); i++)
        {
            Group g = (Group)grupy.get(i);
                out.println("<tr>");
                out.println("<td align=\"center\" class=\"tdGroupsList\">&nbsp;"+g.getNazwa()+"&nbsp;</td>");
                out.println("</tr>");
        }
    
%>

</table>
</body>
</html>
