<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<html>
    <head><title>JSP Page</title></head>
    <body>

        <jsp:useBean id="db_con" class="pl.ltd.bee.DataBase" scope="application"/>
        
        <%
        db_con.connect("wilk.waw.pl","pawelb","asd");
        db_con.setTablePrefix("Bee");
  
        Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) {
            String redirectURL = "pages/forum.jsp";
            response.sendRedirect(redirectURL);
        } else {
            String field = (String) flds.nextElement();
            String redirectURL;
            
            if (field.compareTo("wid") == 0) {
                redirectURL = "pages/watek.jsp?" + field + "=" + request.getParameter(field);
                response.sendRedirect(redirectURL); }
        }
    %>
    </body>
</html>
