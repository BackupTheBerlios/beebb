<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>


<% 
String css = request.getParameter("style");
if (css == null) css = Config.DEFAULT_STYLE; 
out.println(Commons.htmlHead(".","BeeBB :: Content",css));
%>
    <body>
        <table width="100%" border="0"> 
            <tr>
                <td id="cellHead"> 
                    <iframe id="frameHead" name="frameHead" width="100%" src="./pages/header.jsp<% if (request.getParameter("style") != null) out.print("?style="+request.getParameter("style"));%>" scrolling="no" frameborder="0"></iframe>
                </td>
            </tr>
            <tr>
                <td id="cellTresc">
                    <iframe id="frameTresc_1" name="frameTresc_1" width="100%" height="5000" src="./pages/main.jsp<% if (request.getParameter("style") != null) out.print("?style="+request.getParameter("style"));%>" scrolling="no" frameborder="0" style=" display : none;" ></iframe>
                    <iframe id="frameTresc_2" name="frameTresc_2" width="100%" height="5000" src="" scrolling="no" frameborder="0" style=" display : block;" ></iframe>
                </td>
            </tr>
        </table>    
    </body>
</html>
