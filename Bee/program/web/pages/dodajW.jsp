<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
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
        <title>BeeBB :: Dodaj</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
    </head>
    <body>
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
       
       Autoryzator auth;
       Object obj = application.getAttribute(Config.APPLICATION_OBJECT_AUTORYZACJA);
       if (obj == null)
       {
           Autoryzator a = new Autoryzator();
           application.setAttribute(Config.APPLICATION_OBJECT_AUTORYZACJA,a);
           auth = a;
       }
       else auth = (Autoryzator)obj;
%>
        <br/><br/>
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
                out.print("<br><br>");
                text=text.replaceAll("\r\n","<br>");
                text=text.replaceAll("\n","<br>");
                String ID_Usera; 
                String Nazwa_Usera="";
                if (auth.zalogowany(request,db_con)) {
                    ID_Usera = String.valueOf(auth.getUser(request,db_con).getID());
                } else {
                    String autor=request.getParameter("autor");
                    if (autor==null) Nazwa_Usera=Config.GUEST; else Nazwa_Usera=autor;
                    ID_Usera = Config.GUEST_ID;
                }
                if (!db_con.insertWypowiedz(watek,ID_Usera,Nazwa_Usera,text,db_con.getDate())) 
                    out.print(Messages.errorDataBaseConnection()); else
                    out.print(Messages.addMessage());
                out.println("<br><br><a href=\"./main.jsp\">" + Messages.back() + "</a>");
            } else
                if (podforum!=null && text!=null) {
                out.print(Messages.addThread());
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
                            <td>Autor:</td><td><% if(!auth.zalogowany(request,db_con)){%><input type="text" size="59" name="autor"><%}else{out.print(auth.user(request));}%></td>
                            </tr> <tr>
                                <td colspan="2" align="right"><input type="submit" name="submit" value="Wyślij"/></td>
                            </tr>
                        </table>
                    </form>
                    <br><br>
                    <center><a href="./main.jsp<% if(watek!=null) out.print("?wid="+watek); else out.print("?pid="+podforum); %>"><%out.print(Messages.back());%></a></center>
   <% } }%>
                </td>
            </tr>
        </table>
    </body>
</html>
