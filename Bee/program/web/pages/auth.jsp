<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<html>
    <head><title>Authorization page</title></head>
    <body>
        <jsp:useBean id="auth" scope="session" class="pl.ltd.bee.Autoryzator" /> 
        <% Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) { %>
        <form method="POST" action="auth.jsp">
            Zaloguj sie <br>
            user: <input type="text" size="20" name="user"><br>
            haslo: <input type="password" size="20" name="haslo"><br>
            <input type="submit" name="submit" value="submit"/>
        </form>
        <br><br>
        <a href="addUser.jsp">Zarejestruj się</a> <br><br>
        <% } else { 
        if (flds.hasMoreElements()) { 
            String field = (String) flds.nextElement(); 
                if (field.compareTo("logout")==0) {
                    auth.zaloguj("Guest","");
                    response.sendRedirect("../index.jsp");
                }
                else {
                
                }
        %>
        <%= field %> = <%= request.getParameter(field) %><br>
        <%   } %> <%--while--%>
    
    
    <% } %>
    </body>
</html>
