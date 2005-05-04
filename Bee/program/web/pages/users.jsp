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
<body onload="<% if (request.getParameter("fraza") == null) out.print("swapIframes();");%>resizeMain();setResizeFunction(resizeMain);" >    
<table id="tableSearch" border="0" width="100%">
<tr><td>

<%@ include file="servletObjects.jsp" %>
<%
        ArrayList users = db_con.getUsersAktywni(true);
        for(int i=0; i<users.size(); i++)
        {
            User u = (User)users.get(i);
            if (u.getID() != Config.GUEST_ID)
                out.println(u);
        }
    
%>
</tr></td>
</table>
</body>
</html>
