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
            if ((param == null)||(param.compareTo("null")==0)||(param.compareTo("")==0)) return "brak";
              return param;
            }
           boolean dajUpr(String param)
           {
             if ( param.compareTo("T")==0) return true;
             return false;
           }
         %>
    </head
    <body>
     <a href="uprawnienia.jsp" target="prawa">   Powrot   </a>
        <table name="tabuser" style="" align="center" cellpadding="2" cellspacing="1" border="1">
          <th colspan="2">DANE USERA </th>
          <tr> <td>LOGIN</td> <td><%= dajDana(request.getParameter("login")) %> </td> </tr>
          <tr> <td>IMIE</td> <td><%= dajDana(request.getParameter("imie")) %> </td> </tr>
          <tr> <td>NAZWISKO</td> <td><%= dajDana(request.getParameter("nazwisko")) %> </td> </tr>
          <tr> <td>EMAIL</td> <td><%= dajDana(request.getParameter("email")) %> </td> </tr>
          <tr> <td>GG</td> <td><%= dajDana(request.getParameter("gg")) %> </td> </tr>
          <tr> <td>JABBER</td> <td><%= dajDana(request.getParameter("jabber")) %> </td> </tr>
          <tr> <td>OSTATNI LOGIN</td> <td><%= dajDana(request.getParameter("lastlog")) %> </td> </tr>
        </table>
        <br>
        <form action="uprawnienia.jsp" method="post" target="tresc">
            
        <table align="center">
         <tr> <th> UPRAWNIENIA </th> </tr>
           <tr> <td> <input type="checkbox" name="czy_admin" 
                      <%= dajUpr(request.getParameter("czy_admin")) ? "checked" : "" %>>Administrator</input> </td> </tr>
            <tr> <td> <input type="checkbox" name="czy_moderator" 
                      <%= dajUpr(request.getParameter("czy_moderator")) ? "checked" : "" %>>Moderator</input> </td> </tr>
           <tr> <td><input type="checkbox" name="czy_aktywny" 
                      <%= dajUpr(request.getParameter("czy_aktywny")) ? "checked" : "" %>>Aktywny</input> </td> </tr>
             <tr> <td>  <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                        <input type="submit" value="Zmien uprawnienia"></td> </tr>
        </table>
         
        </form>
      
    </body>
</html>
