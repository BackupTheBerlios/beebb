<%@ page language="java" import="java.util.*"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<html>
    <head><title>JSP Page</title></head>
    <body>

        <%-- <jsp:useBean id="beanInstanceName" scope="session" class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  property="propertyName" /> --%>
        <!--           //    pl.ltd.bee.Watek w = new pl.ltd.bee.Watek(12,12,"s","s");
        //    out.println(w.getTemat()); -->
        watek: <br>
        
        <%
            Enumeration flds = request.getParameterNames();
            while (flds.hasMoreElements()) { 
                String field = (String) flds.nextElement();
                out.println(field + " = " +  request.getParameter(field) + "<br>");
            }
        %>

    </body>
</html>
