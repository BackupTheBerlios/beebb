<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Dodaj</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
    </head>
    <body>
        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
        <jsp:useBean id="auth" scope="session" class="pl.ltd.bee.Autoryzator" />
        <br><br>
        <table align="center" border="0">
            <tr>
                <td> 
        <% Enumeration flds = request.getParameterNames();
        if (!db_con.isConnected()) {
        try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
        } catch (Exception e) {
            out.print(Messages.errorDataBaseConnection());
        } }
        /* Validacja za pomocą JS */
        
        String watek=request.getParameter("w");
        String podforum=request.getParameter("p");
            if (watek==null && podforum==null) {
                out.print(Messages.formError());
            } else {
            String text=request.getParameter("text");
            if (watek!=null && text!=null) {
                out.print("dodaje wypowiedz");
            } else
                if (podforum!=null && text!=null) {
                out.print("dodaje watek");
                }
                else {
        %>
                    <form method="POST" action="dodajW.jsp<%if (watek!=null) out.print("?w="+watek); else out.print("?p="+podforum); %>">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                                <th colspan="2">
                                    <%out.print(Messages.add()); if (watek!=null) out.print(Messages.message()); else out.print(Messages.thread()); %> 
                                </th>
                            <% if (watek==null) { %>
                            </tr> <tr>
                            <td>Tytuł:</td><td><input type="text" size="59" name="title"></td>
                            <% } %>
                            </tr> <tr>
                                <td valign="top">Treść:</td><td><textarea cols="50" rows="5" name="text"></textarea></td>
                            </tr> <tr>
                            <td>Autor:</td><td><% if(!auth.zalogowany()){%><input type="text" size="59" name="autor"><%}else{out.print(auth.user());}%></td>
                            </tr> <tr>
                                <td colspan="2" align="right"><input type="submit" name="submit" value="Wyślij"/></td>
                            </tr>
                        </table>
                    </form>
                 
   <% } }%>
                </td>
            </tr>
        </table>
    </body>
</html>
