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
        <jsp:useBean id="wiad" scope="request" class="java.util.ArrayList" />
     <% if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   out.print("Blad polaczenia z baza!");
                }
            } %>
      
            
    <% Enumeration pom = request.getParameterNames();
      String tytul="",opis="", id_kat="";
    if (pom.hasMoreElements()) {
           String field = (String) pom.nextElement();
      if( (field.compareTo("id")==0) || (field.compareTo("tytul")==0) ||(field.compareTo("opis")==0) ) {     
       tytul=request.getParameter("tytul");
       opis=request.getParameter("opis");
       id_kat=request.getParameter("id");
      } 
       else {
         String id_k=request.getParameter("id_kat");  
         for(int j=0; j<5; j++) {  
            String t=request.getParameter("tytul"+j);
            String o=request.getParameter("opis"+j);
                 
            if( t.compareTo("")== 0 ) wiad.add(Messages.errorFieldNamePodforum());
              else
                if ( db_con.czyPodforum(id_k, t) ) wiad.add(Messages.errorNamePodforum());
                  else 
                    if (db_con.insertPodforum(id_k, t, o) ) wiad.add(Messages.AddPodforum());
                      else wiad.add(Messages.errorAddPodforum());
                  
            }%>
              <jsp:forward page="./edycja_podforow.jsp"/>
            <%
        }     
    }
       %>
            
       <br/> 
       <p align="center">Kategoria: <%=tytul%> </p>
       <p align="center">Opis: <%=opis%> </p>
       <br/>
     
    <form action="./podfora_form.jsp" method="post">
      <table align="center" cellpadding="2" cellspacing="1" border="0">
       <caption> Dodawanie Podforów </caption>
       <tr> <th> Next </th> <th> Tytul </th> <th> Opis </th> </tr>
       <%for(int i=0;i<5;i++) { %>       
      <tr> <td> <input type="button" name="plusik" value="+"/>  </td> 
           <td> <input size="40" type="text" name="tytul<%=i%>" value=""/> </td>
           <td> <input size="40" type="text" name="opis<%=i%>" value=""/>  </td> 
      </tr>
       <%}%>
      </table>
         <input type="hidden" name="id_kat" value="<%=id_kat%>"/>
        <p align="center"> <input size="25" type="submit" size="30" value="Dodaj"/> </p>
     </form>
     
    </body>
</html>
