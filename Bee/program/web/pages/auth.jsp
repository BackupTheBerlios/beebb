<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<html>
    <head><title>Authorization page</title></head>
    <body>
        <jsp:useBean id="auth" scope="session" class="pl.ltd.bee.Autoryzator" />
        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
        <% 
        if (!db_con.isConnected()) {
        try {
        db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
        db_con.setTablePrefix("Bee");
        } catch (Exception e) {
        out.print("Blad polaczenia z baza!");
        } }
        Enumeration flds = request.getParameterNames();
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
                auth.zaloguj("Guest","",db_con);
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
                        try {
                        User u = auth.zaloguj(uzytkownik,haslo,db_con);
                        
                        if (u!=null)
                            response.sendRedirect("../index.jsp");
                        } catch (Exception e) {
                            out.print(e);
                        }
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
