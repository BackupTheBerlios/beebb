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
        <title>BeeBB :: Dodawanie podforow</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/> 
    </head>
    <body> 
         <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
         <jsp:useBean id="p" scope="request" class="pl.ltd.bee.Podforum" />
         
        <% if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print(Messages.errorDataBaseConnection());
                out.print(e);
            }
        } %>
         
     <% 
       Enumeration fff = request.getParameterNames();
       if (fff.hasMoreElements()) {
           String pom = (String) fff.nextElement();
          if((pom.compareTo("id_pod")==0)||(pom.compareTo("opis")==0)||(pom.compareTo("tytul")==0)||(pom.compareTo("id_kat")==0) )
           {
            String id_pod= (String) request.getParameter("id_pod");
            String tytul= (String) request.getParameter("tytul");
            String opis= (String) request.getParameter("opis");
            String id_kat= (String) request.getParameter("id_kat");
        
            p.setNazwa(tytul);
            p.setOpis(opis);
            p.setID(id_pod);
            p.setIdKat(id_kat);
           }
        }
       %>
       
      <p align="center"> 
      <% if ( p.getWiad().compareTo("ok") == 0 ) { %>
       <font color="blue"> Podforum zostało zmienione </font>
       <% } else  {%>   
       <font color="red"> <%= p.getWiad() %> </font>  
       <% } %>
     </p> 
       
            
       <p align="center"> <a href="./edycja_podforow.jsp">Powrót</a>  </p>
       <br/>
     
    <form action="./edycja_pod_zmien.jsp" method="post">
      <table align="center" cellpadding="2" cellspacing="1" border="0">
       <caption> Edycja Podforum </caption>
       <tr>  <th> Tytul </th> <th> Opis </th> <th> Wybierz kategorie: </th></tr>
       <tr> 
           <td> <input size="40" type="text" name="nazwa" value="<%=p.getTytul()%>"/> </td>
           <td> <input size="40" type="text" name="opis" value="<%=p.getOpis() %>"/>  </td> 
      
           <td>  <select name="kategoria" > <% ArrayList tytuly=db_con.getTytulyKategorii();
                                                       for (int i=0; i<tytuly.size(); i++) { %>
                                                    <option><%= tytuly.get(i) %></option>
                                                      <%  } %>      
                 </select>
           </td>
           
        <input type="hidden" name="id_pod" value="<%=p.getID()%>"/>
        <input type="hidden" name="id_kat" value="<%=p.getIdKat()%>"/>
       <tr>
         <td> </td> <td><input size="40" type="submit" value="   Zmien   "/> </td> <td> </td> </tr>
       </tr>
      </table>
    </form>
     
    </body>
</html>