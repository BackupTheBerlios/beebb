<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<html>
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
  
    <head><title>Dane Usera</title></head>
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
           <tr> <td> <input type="checkbox" name="admin" 
                      <%= dajUpr(request.getParameter("czy_admin")) ? "checked" : "" %>>Administrator</input> </td> </tr>
            <tr> <td> <input type="checkbox" name="moderator" 
                      <%= dajUpr(request.getParameter("czy_moderator")) ? "checked" : "" %>>Moderator</input> </td> </tr>
           <tr> <td><input type="checkbox" name="aktywny" 
                      <%= dajUpr(request.getParameter("czy_aktywny")) ? "checked" : "" %>>Aktywny</input> </td> </tr>
             <tr> <td>  <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                        <input type="submit" value="Zmien uprawnienia"></td> </tr>
        </table>
         
        </form>
      
    </body>
</html>
