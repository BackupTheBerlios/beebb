<%@ page language="java" import="java.util.*"%>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<html>
    <head><title>JSP Page</title></head>
    <body>

        <%-- <jsp:useBean id="beanInstanceName" scope="session" class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  property="propertyName" /> --%>
        <%
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
