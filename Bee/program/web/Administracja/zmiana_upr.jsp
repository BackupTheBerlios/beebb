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
        <title>BeeBB :: Dane usera</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/> 

     <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
      <%! 
          String dajTN(String param)
            {
              if (param==null) return "N";
              return "T";
            }
      
         %>
    </head>
    <body>
       
         <% 
            if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   out.print("Blad polaczenia z baza!");
                }
            }
          %>
         
           <%
          boolean pom=db_con.zmienUpr(request.getParameter("id"),dajTN(request.getParameter("czy_admin")), dajTN(request.getParameter("czy_moderator")), dajTN(request.getParameter("czy_aktywny")));
          if (pom) {%>
                <font color="blue"> Uprawnienia zostały zmienione </font> 
           <% }else {%>
                <font color="red"> Zmiana uprawnien nie powiodla sie </font>
           <% } %>
       
           <form action="user_dane.jsp" method="post" target="prawa">
                <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                <input type="hidden" name="login" value="<%=request.getParameter("login")%>">
                <input type="hidden" name="imie" value="<%=request.getParameter("imie")%>">
                <input type="hidden" name="nazwisko" value="<%=request.getParameter("nazwisko")%>">
                <input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                <input type="hidden" name="gg" value="<%=request.getParameter("gg")%>">
                <input type="hidden" name="jabber" value="<%=request.getParameter("jabber")%>">
                
                 <input type="hidden" name="czy_admin" value="<%=dajTN(request.getParameter("czy_admin"))%>">
                <input type="hidden" name="czy_moderator" value="<%=dajTN(request.getParameter("czy_moderator"))%>">
                <input type="hidden" name="czy_aktywny" value="<%=dajTN(request.getParameter("czy_aktywny"))%>">
                <input type="submit" value="POWRÓT"></td> </tr>
           </form>
</body>
</html>