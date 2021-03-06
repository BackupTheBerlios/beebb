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
        <script type="text/javascript" src="../js/podfora.js"></script>
       
    </head>
    
    <body> 
    <%@ include file="../pages/servletObjects.jsp" %>

    <%
       User user = auth.getUser(request,db_con);
                if ( (user==null)||(!user.admin()) ) {  out.println(Messages.makeError(Messages.wielka(Messages.errorNotLoggedIn()))); } else {%>  
        
        
   <% Enumeration pom = request.getParameterNames();
    if (pom.hasMoreElements()) {
           String field = (String) pom.nextElement();
           
      if(field.compareTo("akt_kat")==0 )
           {
             String nr=request.getParameter("akt_kat");
             if ( db_con.zmienAktywnoscKategorii(Integer.parseInt(nr),true)) out.print(Messages.makeInfo(Messages.activeKat()));
                else out.print(Messages.makeError(Messages.errorActiveKat()));
           }
        if (field.compareTo("akt_pod")==0 )
           {
             String nr=request.getParameter("akt_pod");
             if ( db_con.zmienAktywnoscPodforum(Integer.parseInt(nr),true)) out.print(Messages.makeInfo(Messages.activePodforum()));
                else  out.print(Messages.makeError(Messages.errorActivePodforum()));
           }
        }
      %> 
     
      
   <br/>
     <% ArrayList lista=db_con.getKategorieAll(); int licz=0; %>  
        <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
            <caption> <font size="5" style="bold"></font> </caption>
         <tr> <th><%out.println(Messages.wielka(Messages.rozwin())); %></th> <th><%out.println(Messages.wielka(Messages.nr())); %></th> <th><%out.println(Messages.wielka(Messages.title())); %></th> 
                 <th><%out.println(Messages.wielka(Messages.describe())); %></th> <th><%out.println(Messages.wielka(Messages.activation())); %></th> </tr>
       <% for(int i=0; i<lista.size(); i++)
            { Kategoria kkk=(Kategoria) lista.get(i);
              ArrayList lista2 = kkk.getPodfora(false);
           if ( (lista2.size() > 0)||(!kkk.czyAktywna()) ) {  
               licz++;
         %><tr bgcolor="gold" > <td align="center"> <button id="plusik<%=i%>" onClick="rozwijanie('<%=i%>'); return false;"> +/- </button> </td> <td> <%= licz %>  </td> <td> <%=kkk.getNazwa() %> </td> <td><%=kkk.getOpis() %> </td> 
          
             <td><form action="./edycja_usunietych.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isActiveKat())+"');" %>">
                 <input type="hidden" name="akt_kat" value="<%= kkk.getID() %>"/>
                 <% if (kkk.czyAktywna()) { %>
                 <input disabled=""  align="center" type="submit" value="<%out.println(Messages.wielka(Messages.activation())); %>"/>
                 <% } else { %>
                  <input align="center" type="submit" value="<%out.println(Messages.wielka(Messages.activation())); %>"/>
                 <% } %>
             </form> 
             </td>
         </tr>
         

            <tr class="w<%=i%>" bgcolor="yellow" > <td> </td> <td colspan="5" align="center">  <%out.println(Messages.wielka(Messages.podKat())); %>: <%=kkk.getNazwa() %> </td></tr>
         <%     
          for(int j=0; j<lista2.size(); j++)
            { Podforum podf =(Podforum) lista2.get(j);
         %><tr class="w<%=i%>" bgcolor="goldenrod"> <td> </td><td><%= licz %>.<%=j+1%>  </td> <td> <%=podf.getTytul()%> </td> <td><%=podf.getOpis()%> </td> 
             <td><form  action="./edycja_usunietych.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isActivePod())+"');" %>">
                 <input type="hidden" name="akt_pod" value="<%= podf.getID() %>"/>
                 <% if (!kkk.czyAktywna()) { %>
                 <input disabled=""  align="center" type="submit" value="<%out.println(Messages.wielka(Messages.activation())); %>"/>
                 <% } else { %>
                  <input align="center" type="submit" value="<%out.println(Messages.wielka(Messages.activation())); %>"/>
                 <% } %>
             </form> 
             </td> 
         </tr>
          <% } %>
        
          <% } %>
    
       <% }%>
        </table>
     <% } %>
    </body>
</html>