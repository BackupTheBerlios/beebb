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
        <title>BeeBB :: Edycja Kategorii</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/> 
    </head>
    <body> 
        <jsp:useBean id="k" scope="request" class="pl.ltd.bee.Kategoria" />
         
     <% 
       Enumeration f = request.getParameterNames();
        if (f.hasMoreElements()) {
           String napis = (String) f.nextElement();
           if(napis.compareTo("id_kat")==0 ||(napis.compareTo("tytul")==0)||(napis.compareTo("opis")==0))
           {
            String id_kat= (String) request.getParameter("id_kat");
            String tytul= (String) request.getParameter("tytul");
            String opis= (String) request.getParameter("opis");
        
            k.setNazwa(tytul);
            k.setOpis(opis);
            k.setID(id_kat);
           }
        }
       %>
       
        <p align="center"> 
      <% if ( k.getWiad().compareTo("ok") == 0 ) { %>
            <font color="blue"> Kategoria została zmieniona </font>
       <% } else  {%>   
            <font color="red"> <%= k.getWiad() %> </font>  
       <% } %>
        </p> 
       
            
        <p align="center"> <a href="./edycja_podforow.jsp">Powrót</a>  </p>
        <br/>
     
        <form action="./edycja_kat_zmien.jsp" method="post">
            <table align="center" cellpadding="2" cellspacing="1" border="0">
                <caption> Edycja Kategorii </caption>
                <tr>  <th> Tytul </th> <th> Opis </th> </tr>
                <tr> 
                    <td> <input size="40" type="text" name="nazwa" value="<%=k.getNazwa()%>"/> </td>
                    <td> <input size="40" type="text" name="opis" value="<%=k.getOpis() %>"/>  </td> 
                </tr>
                <input type="hidden" name="id_kat" value="<%=k.getID()%>"/>
                <tr>
                    <td> </td> <td><input size="40" type="submit" value="   Zmien   "/> </td>
                </tr>
            </table>
        </form>
     
    </body>
</html>