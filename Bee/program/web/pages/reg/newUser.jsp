<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Nowy użytkownik</title>
        <link rel="stylesheet" href="../../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="./../../js/iframe_resize.js"></script>
    </head>
    <body onload="resizeNewUser()" onresize="resizeNewUser()">
<%
       DataBase db_con;
       Object o = application.getAttribute(Config.APPLICATION_OBJECT_DATABASE);
       if (o == null)
       {
           DataBase d = new DataBase();
           application.setAttribute(Config.APPLICATION_OBJECT_DATABASE,d);
           db_con = d;
       }
       else db_con = (DataBase)o;
       
%>
    
        <table align="center" border="0" id="tableNewUser"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr>
                <td> 
        <br/><br/>
        <% Enumeration flds = request.getParameterNames();
        if (!db_con.isConnected()) {
        try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
        } catch (Exception e) {
            out.print(Messages.errorDataBaseConnection());
        } }
        String klucz=request.getParameter("id");
        if (klucz!=null) {
            String nick = db_con.getLoginNewUser(klucz);
            if (nick!=null)
            {
                 if(!db_con.setAktywnyUser(nick))
                    out.println(Messages.errorUserCreate()); 
                 else {
                    db_con.usunKluczNewUser(klucz);
                    out.println("Uzytkownik " + nick + " zostal dodany<BR><br><a href=./../main.jsp>powrot</a><br>"); 
                 }
            } else 
                out.print(Messages.errorKeyNewUser());
            
        } else out.println("<a href=./../../index.jsp>powrot</a><br>"); 
        %>
                </td>
            </tr>
        </table>
    </body>
</html>
