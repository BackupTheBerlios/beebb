<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead(".","BeeBB :: Content"));%>
    <body>
<body>
<table width="100%" border="0"> 
<tr>
<td id="cellHead"> 
	<iframe id="frameHead" width="100%" src="./pages/header.jsp" scrolling="no" frameborder="0"></iframe>
</td>
</tr>
<tr>
<td id="cellTresc">
	<iframe id="frameTresc_1" width="100%" height="5000" src="./pages/main.jsp" scrolling="no" frameborder="0" style=" display : none;" ></iframe>
	<iframe id="frameTresc_2" width="100%" height="5000" src="" scrolling="no" frameborder="0" style=" display : block;" ></iframe>
</td>
</tr>
</table>    
    </body>
</html>
