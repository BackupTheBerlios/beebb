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
        <script type="text/javascript" src="./../js/forms.js"></script>
    </head>
    <body onLoad="resizeDodajW()" onresize="resizeDodajW()">
<%@ include file="servletObjects.jsp" %>
        <table align="center" border="0" id="tableDodajW"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr>
                <td> 
        <% Enumeration flds = request.getParameterNames();
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
            String ID_Usera = new String().valueOf(Config.GUEST_ID); 
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
               Watek wt = db_con.getWatek(Integer.decode(watek).intValue());
               String prywatna=DataBase.NIE;
               if(wt.czyPrywatny()) prywatna=DataBase.TAK;
                
                Wypowiedz wp = new Wypowiedz("0",ID_Usera,Nazwa_Usera,db_con.getDate(),text,prywatna,db_con);
               if (!db_con.insertWypowiedz(watek,wp)) 
                    out.print(Messages.errorDataBaseConnection()); else
                    out.print(Messages.addedMessage());
                } else
                if (podforum!=null && text!=null) {
                    String title=request.getParameter("title");
                    title = new String(title.getBytes("8859_1"),"UTF-8");
                    Podforum pf = db_con.getPodforum(Integer.decode(podforum).intValue());
                    Watek wt;
                    String prywatne=DataBase.NIE;
                    if(pf.czyPrywatne()) prywatne=DataBase.TAK;
                    wt = new Watek("0",ID_Usera,Nazwa_Usera,title,db_con.getDate(),prywatne,db_con);
                    wt = db_con.insertWatek(podforum,wt);
                    
                    if (wt!=null) { 
                        Wypowiedz wp = new Wypowiedz("0",ID_Usera,Nazwa_Usera,db_con.getDate(),text,prywatne,db_con);
                        if (!db_con.insertWypowiedz(String.valueOf(wt.getID()),wp))
                            out.print(Messages.errorDataBaseConnection());
                        else out.print(Messages.addedThread()); 
                    } else out.print(Messages.errorDataBaseConnection());
                  }
                    out.print("<center><br/><br/><a href=\"./main.jsp"); 
                    if(watek!=null) out.print("?wid="+watek); else out.print("?pid="+podforum); out.print("\">" + Messages.back() + "</a></center>");
                }
                else {
        %>
                    <form method="POST" action="dodajW.jsp<%if (watek!=null) out.print("?w="+watek); else out.print("?p="+podforum); %>" onsubmit="return submitDodajW('<% out.print(Messages.wielka(Messages.errorFieldNeeded())); %>')">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                                <th colspan="2">
                                    <%out.print(Messages.wielka(Messages.add())+" "); if (watek!=null) out.print(Messages.message()); else out.print(Messages.thread()); %> 
                                </th>
                            <% if (watek==null) { %>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.title()) + ":");%></td><td><input type="text" size="50" name="title" id="title"></td>
                            <% } %>
                            </tr> <tr>
                                <td valign="top"><%out.print(Messages.wielka(Messages.tresc()) + ":");%></td><td><textarea cols="50" rows="5" name="text" id="text"></textarea></td>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.author()) + ":");%></td><td><% if(!auth.zalogowany(request,db_con)){%><input type="text" size="50" name="autor" id="autor"><%}else{out.print(auth.user(request));}%></td>
                            </tr> <tr>
                                <td colspan="2" align="right"><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td>
                            </tr>
                        </table>
                    </form>
                    <br><br>
                    <center><a href="main.jsp<% if(watek!=null) out.print("?wid="+watek); else out.print("?pid="+podforum); %>"><%out.print(Messages.back());%></a></center>
   <% } }%>
                </td>
            </tr>
        </table>
    </body>
</html>
