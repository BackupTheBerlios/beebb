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
<body onload="swapIframes();resizeSearch()" onresize="resizeSearch()">    
<table id="tableSearch" border="0" width="100%">
<tr><td>

<form action="search.jsp" method="post">
<table class="tableSearch" border="0" align="center">
<tr><th colspan="2"><% out.print(Messages.wielka(Messages.search()));%></th></tr>
<tr><td class="tdSearch"><% out.print(Messages.wielka("szukana fraza"));%></td><td class="tdSearch"><input type="text" name="fraza" /></td></tr>
<tr><td class="tdSearch" colspan="2"><input type="checkbox" name="wypowiedzi" checked="true" /><% out.print(Messages.wielka("szukaj tez w wypowiedziach"));%></td></tr><!--zmeinie w domu -->
<tr><td colspan="2" align="center" class="tdSearch"><input type="submit" value="<% out.print(Messages.wielka(Messages.search()));%>" /></td></tr>
</table>
</form>

<%
    String fraza = request.getParameter("fraza");
    if (fraza != null)
    {
       // najpierw to co zwykle
        %>
    <%@ include file="servletObjects.jsp" %>
    <%
        //TERAZ DOPIERO MOGE SZUKAC
       
    }
%>
</tr></td>
</table>
</body>
</html>
