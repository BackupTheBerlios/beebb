<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
          
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

        <script type="text/javascript" LANGUAGE="JavaScript">
            function Info(param)
            {
            return confirm(param);
            }
        </script>
    </head>
    
    <body> 
    <% 
      
       if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print(Messages.errorDataBaseConnection());
                out.print(e);
            }
        } %>
        
        
   <% Enumeration pom = request.getParameterNames();
    if (pom.hasMoreElements()) {
           String field = (String) pom.nextElement();
           
      if(field.compareTo("akt_kat")==0 )
           {
             String nr=request.getParameter("akt_kat");
             if ( db_con.zmienKategorie(nr,"T")) out.print(Messages.activeKat());
                else out.print(Messages.errorActiveKat());
           }
        if (field.compareTo("akt_pod")==0 )
           {
             String nr=request.getParameter("akt_pod");
             if ( db_con.zmienPodforum(nr,"T")) out.print(Messages.activePodforum());
                else  out.print(Messages.errorActivePodforum());
           }
        }
      %> 
     
      
   <br/>
     <% ArrayList lista=db_con.getKategorieAll(); int licz=0; %>  
        <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
            <caption> <font size="5" style="bold">TABELA NIEAKTYWNYCH KATEGORII </font> </caption>
            <tr> <th>Rozwiń</th> <th>Nr.</th> <th>Nazwa</th> <th>Opis</th>  <th>Aktywacja</th>  </tr>
       <% for(int i=0; i<lista.size(); i++)
            { Kategoria kkk=(Kategoria) lista.get(i);
              ArrayList lista2 = kkk.getPodfora("N");
           if ( (lista2.size() > 0)||(!kkk.getAktywna()) ) {  
               licz++;
         %><tr bgcolor="gold" ><td align="center"> <a href="">+</a> </td> <td><%= licz %>  </td> <td> <%=kkk.getNazwa() %> </td> <td><%=kkk.getOpis() %> </td> 
          
             <td><form action="./edycja_usunietych.jsp" method="post" onsubmit="return Info('Czy napewno chcesz uaktywnić kategorie?');">
                 <input type="hidden" name="akt_kat" value="<%= kkk.getID() %>"/>
                 <% if (kkk.getAktywna()) { %>
                 <input disabled=""  align="center" type="submit" value="AKTYWUJ"/>
                 <% } else { %>
                  <input align="center" type="submit" value="AKTYWUJ"/>
                 <% } %>
             </form> 
             </td>
         </tr>
            <tr bgcolor="yellow" > <td> </td> <td colspan="6" align="center"> Podfora kategorii: <%=kkk.getNazwa() %> </td></tr>
         <%     
          for(int j=0; j<lista2.size(); j++)
            { Podforum podf =(Podforum) lista2.get(j);
         %><tr bgcolor="goldenrod"> <td> </td><td><%= licz %>.<%=j+1%>  </td> <td> <%=podf.getTytul()%> </td> <td><%=podf.getOpis()%> </td> 
             <td><form  action="./edycja_usunietych.jsp" method="post" onsubmit="return Info('Czy napewno chcesz uaktywnić podforum?');">
                 <input type="hidden" name="akt_pod" value="<%= podf.getID() %>"/>
                 <% if (!kkk.getAktywna()) { %>
                 <input disabled=""  align="center" type="submit" value="AKTYWUJ"/>
                 <% } else { %>
                  <input align="center" type="submit" value="AKTYWUJ"/>
                 <% } %>
             </form> 
             </td> 
             <td></td>
         </tr>
          <% }
          }%>
    
       <% }%>
        </table>
   
    </body>
</html>