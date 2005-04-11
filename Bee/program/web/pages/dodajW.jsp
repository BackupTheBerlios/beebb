<%@page contentType="text/html;"%>
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
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>
    </head>
    <body onLoad="resizeDodajW()" onresize="resizeDodajW()">
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
        <table align="center" border="0" id="tableDodajW">
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
/*
 To powinno dzialac :
        if (request.getCharacterEncoding() == null)
               request.setCharacterEncoding("UTF-8");
 ale nie dziala, za to dziala to:
      autor = new String(autor.getBytes("8859_1"),"UTF-8");
*/
        
        String watek=request.getParameter("w");
        String podforum=request.getParameter("p");
            if (watek==null && podforum==null) {
                out.print(Messages.formError());
            } else {
            String text=request.getParameter("text");
            String ID_Usera = Config.GUEST_ID;; 
            String Nazwa_Usera= Config.GUEST;
            if (text!=null) {
            text = new String(text.getBytes("8859_1"),"UTF-8");
            out.print("<br/><br/>");
            text=text.replaceAll("\r\n","<br/>");
            text=text.replaceAll("\n","<br/>");

            if (auth.zalogowany(request,db_con)) {
                ID_Usera = String.valueOf(auth.getUser(request,db_con).getID());
            } else {
                String autor=request.getParameter("autor");
                if (autor!=null) {
                      autor = new String(autor.getBytes("8859_1"),"UTF-8");
                      Nazwa_Usera=autor;
                }
            }
            if (watek!=null) {
               if (!db_con.insertWypowiedz(watek,ID_Usera,Nazwa_Usera,text,db_con.getDate(),DataBase.NIE)) 
                    out.print(Messages.errorDataBaseConnection()); else
                    out.print(Messages.addMessage());
                } else
                if (podforum!=null && text!=null) {
                    String title=request.getParameter("title");
                    title = new String(title.getBytes("8859_1"),"UTF-8");
                    Podforum pf = db_con.getPodforum(Integer.decode(podforum).intValue());
                    Watek wt;
                    String prywatne=DataBase.NIE;
                    if(pf.czyPrywatne()) prywatne=DataBase.TAK;
                    wt = db_con.insertWatek(podforum,ID_Usera,Nazwa_Usera,title,db_con.getDate(),prywatne);
                    
                    if (wt!=null) { 
                        if (!db_con.insertWypowiedz(String.valueOf(wt.getID()),ID_Usera,Nazwa_Usera,text,db_con.getDate(),prywatne))
                            out.print(Messages.errorDataBaseConnection());
                        else out.print(Messages.addThread()); 
                    } else out.print(Messages.errorDataBaseConnection());
                  }
                    out.print("<center><br><br><a href=\"./main.jsp"); 
                    if(watek!=null) out.print("?wid="+watek); else out.print("?pid="+podforum); out.print("\">" + Messages.back() + "</a></center>");
                }
                else {
        %>
                    <form method="POST" action="dodajW.jsp<%if (watek!=null) out.print("?w="+watek); else out.print("?p="+podforum); %>">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                                <th colspan="2">
                                    <%out.print(Messages.wielka(Messages.add())+" "); if (watek!=null) out.print(Messages.message()); else out.print(Messages.thread()); %> 
                                </th>
                            <% if (watek==null) { %>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.title()) + ":");%></td><td><input type="text" size="50" name="title"></td>
                            <% } %>
                            </tr> <tr>
                                <td valign="top"><%out.print(Messages.wielka(Messages.tresc()) + ":");%></td><td><textarea cols="50" rows="5" name="text"></textarea></td>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.author()) + ":");%></td><td><% if(!auth.zalogowany(request,db_con)){%><input type="text" size="50" name="autor"><%}else{out.print(auth.user(request));}%></td>
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
