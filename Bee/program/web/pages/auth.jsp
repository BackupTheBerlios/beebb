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
            String field = (String) flds.nextElement(); 
            if (field.compareTo("logout")==0) {
                auth.zaloguj("Guest","");
                response.sendRedirect("../index.jsp");
            }
            else {
                String uzytkownik;
                String haslo;
                try {
                    uzytkownik=request.getParameter("user");
                    haslo=request.getParameter("haslo");
                    if (uzytkownik!=null && haslo!=null)
                    {
                        auth.zaloguj(uzytkownik,haslo);
                        response.sendRedirect("../index.jsp");
                    } 
                    
                    out.println("Bledny user lub haslo<br><a href=\"auth.jsp\">powrot</a><br>");
                    
                }
                catch (Exception e) {
                    out.println("Blad! skontaktuj sie z administratorem strony!<BR>");
                }
                
            }
        %>
    <% } %>
    </body>
</html>
