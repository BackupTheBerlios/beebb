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
         <%! 
          String dajDana(String param)
            {
            if (param.compareTo("null") == 0) return "Brak danych";
              return param;
            }
           boolean dajUpr(String param)
           {
             if ( param.compareTo("T")==0) return true;
             return false;
           }
         %>
    </head>
    <body>
        <font size="6">DANE USERA    </font>
        <A href="uprawnienia.jsp" target="prawa">   Powrot   </A>
        <table align="center" border="1">
          <tr> <td>LOGIN</td> <td><%= dajDana(request.getParameter("login")) %> </td> </tr>
          <tr> <td>IMIE</td> <td><%= dajDana(request.getParameter("imie")) %> </td> </tr>
          <tr> <td>NAZWISKO</td> <td><%= dajDana(request.getParameter("nazwisko")) %> </td> </tr>
          <tr> <td>EMAIL</td> <td><%= dajDana(request.getParameter("email")) %> </td> </tr>
          <tr> <td>GG</td> <td><%= dajDana(request.getParameter("gg")) %> </td> </tr>
          <tr> <td>JABBER</td> <td><%= dajDana(request.getParameter("jabber")) %> </td> </tr>
        </table>
        <br>
        <form action="zmiana_upr.jsp" method="post" target="prawa">
            
        <table align="center">
         <tr> <td> UPRAWNIENIA </td> </tr>
           <tr> <td> <input type="checkbox" name="czy_admin" 
                      <%= dajUpr(request.getParameter("czy_admin")) ? "checked" : "" %>>Administrator</input> </td> </tr>
            <tr> <td> <input type="checkbox" name="czy_moderator" 
                      <%= dajUpr(request.getParameter("czy_moderator")) ? "checked" : "" %>>Moderator</input> </td> </tr>
           <tr> <td><input type="checkbox" name="czy_aktywny" 
                      <%= dajUpr(request.getParameter("czy_aktywny")) ? "checked" : "" %>>Aktywny</input> </td> </tr>
             <tr> <td>  <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="login" value="<%=dajDana(request.getParameter("login"))%>">
                        <input type="hidden" name="imie" value="<%=dajDana(request.getParameter("imie"))%>">
                        <input type="hidden" name="nazwisko" value="<%=dajDana(request.getParameter("nazwisko"))%>">
                        <input type="hidden" name="email" value="<%=dajDana(request.getParameter("email"))%>">
                        <input type="hidden" name="gg" value="<%=dajDana(request.getParameter("gg"))%>">
                        <input type="hidden" name="jabber" value="<%=dajDana(request.getParameter("jabber"))%>">
                        <input type="submit" value="Zmien uprawnienia"></td> </tr>
        </table>
         
        </form>
      
    </body>
</html>
