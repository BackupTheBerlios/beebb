<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

 <jsp:useBean id="podf" scope="page" class="pl.ltd.bee.Podforum" />
        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
        <jsp:useBean id="lista" scope="page" class="java.util.ArrayList" />
        <jsp:useBean id="lista2" scope="page" class="java.util.ArrayList" />
        <jsp:useBean id="kat" scope="request" class="pl.ltd.bee.Kategoria" />
        <jsp:useBean id="k" scope="page" class="pl.ltd.bee.Kategoria" />
        <jsp:useBean id="pf" scope="request" class="pl.ltd.bee.Podforum" />
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
      <% Enumeration fl = request.getParameterNames();
        if (fl.hasMoreElements()) { 
           String f = (String) fl.nextElement();
           if( (f.compareTo("usun_kat")==0)||(f.compareTo("usun_pod")==0) )
           {   %>
        <script LANGUAGE="JavaScript">
        <!--
           function Info()
                    {if (!confirm("Czy na 100% sie zastanowiles co chcesz zrobic"))
                    history.go(-1);return " "}
          document.writeln(Info())
        <!--End-->
       </script>
       <%} } %>
    </head>
    
    <body> 
      
      <% if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print(Messages.errorDataBaseConnection());
                out.print(e);
            }
        } %>
      <br/>
      <% Enumeration flds = request.getParameterNames();
        if (flds.hasMoreElements()) {
           String field = (String) flds.nextElement();
           if(field.compareTo("usun_kat")==0 )
           {
             String nr=request.getParameter("usun_kat");
             if ( db_con.usunKategorie(nr))
             {%>
              <p align="center"><font color="blue">KATEGORIA ZOSTALA USUNIETA </font> </p>
             <%
             }else {
                 %><p align="center"><font color="red">USUNIECIE KATEGORII NIE POWIODLO SIE </font> </p> <%   
             }
          
           }
        if (field.compareTo("usun_pod")==0 )
           {
             String nr=request.getParameter("usun_pod");
             if ( db_con.usunPodforum(nr))
             {%>
              <p align="center"><font color="blue">PODFORUM ZOSTALO USUNIETE </font> </p>
             <%
             }else {
                 %><p align="center"><font color="red">USUNIECIE PODFORUM NIE POWIODLO SIE </font> </p> <%   
             }
           }
        }
      %> 
    
     <p align="center"> 
      <% if ( kat.getWiad().compareTo("ok") == 0 ) { %>
       <font color="blue"> Kategoria została dodana </font>
       <% } else  {%>   
       <font color="red"> <%= kat.getWiad() %> </font>  
       <% } %>
     </p> 
     
     <p align="center"> 
       <% if ( pf.getWiad().compareTo("ok") == 0 ) { %>
       <font color="blue"> Podforum zostalo dodane </font>
       <% } else  {%>   
       <font color="red"> <%= pf.getWiad() %> </font>  
       <% } %>
     </p> 
     
       <br/>
     <% lista=db_con.getKategorie(); %>  
      <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
       <caption> <font size="5" style="bold">TABELA KATEGORII </font> </caption>
       <tr> <th>Rozwiń</th> <th>Nr.</th> <th>Nazwa</th> <th>Opis</th> <th>Edycja</th> <th>Usun</th> <th>Dodaj</th> </tr>
       <% for(int i=0; i<lista.size(); i++)
            { k=(Kategoria) lista.get(i);
              lista2 = k.getPodfora();  
         %><tr bgcolor="gold" ><td align="center"> <a href="">+</a> </td> <td><%= i+1 %>  </td> <td> <%=k.getNazwa() %> </td> <td><%=k.getOpis() %> </td> 
               <td><form action="" method="post">
                     <input type="hidden" value="<%= k.getID() %>"/>
                     <input align="center" size="15"  type="submit" value="EDYTUJ"/>
                   </form> 
               </td> 
               <td><form action="./edycja_podforow.jsp" method="post">
                     <input type="hidden" name="usun_kat" value="<%= k.getID() %>"/>
                     <input  align="center" size="15"  type="submit" value="USUN"/>
                   </form> 
               </td> 
                <td><form action="./podfora_form.jsp" method="post">
                     <input name="id" type="hidden" value="<%= k.getID() %>"/>
                     <input name="tytul" type="hidden" value="<%= k.getNazwa() %>"/>
                     <input name="opis" type="hidden" value="<%= k.getOpis() %>"/>
                     <input align="center" size="15"  type="submit" value="DODAJ"/>
                   </form> 
                </td>
          </tr>
          <tr bgcolor="yellow" > <td> </td> <td colspan="6" align="center"> Podfora kategorii: <%=k.getNazwa() %> </td></tr>
         <%     
          for(int j=0; j<lista2.size(); j++)
            { podf =(Podforum) lista2.get(j);
         %><tr  style="" bgcolor="goldenrod"> <td> </td><td><%=i+1%>.<%=j+1%>  </td> <td> <%=podf.getTytul()%> </td> <td><%=podf.getOpis()%> </td> 
                <td><form action="" method="post">
                     <input type="hidden" value="<%= podf.getID() %>"/>
                     <input align="center" size="15"  type="submit" value="EDYTUJ"/>
                   </form> 
                </td> 
                <td><form  action="./edycja_podforow.jsp" method="post">
                     <input type="hidden" name="usun_pod" value="<%= podf.getID() %>"/>
                     <input align="center" size="15"  type="submit" value="USUN"/>
                   </form> 
                </td> 
                <td></td>
          </tr>
          <% }%>
    
       <% }%>
     </table>
     <br/> <br/>
     <form action="./kategorie_dodaj.jsp" method="post">
     <table align="center" cellpadding="2" cellspacing="1" border="1">
      <caption> <font size="5" style="bold">DODAWANIE KATEGORII </font> </caption>
      <tr> <td>NAZWA: </td>  <td> <input  size="50" type="text" name="nazwa" value="<%=kat.getNazwa()%>"/> </td> </tr>
      <tr> <td>OPIS:  </td>  <td> <input  size="50" type="text" name="opis" value="<%=kat.getOpis()%>"/>  </td>  </tr>
      <tr> <td></td> <td> <input align="center" size="20"  type="submit" value="DODAJ"/> </td> </tr>
      </table>       
    </form>
   
    </body>
</html>