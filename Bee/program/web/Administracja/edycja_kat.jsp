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
         <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
         <jsp:useBean id="wiad" scope="request" class="java.util.ArrayList" />

         
           <% if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                     out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                }
            } %>
         
     <% String napis="";
       Enumeration f = request.getParameterNames();
        if (f.hasMoreElements()) {
           napis = (String) f.nextElement();
           if( (napis.compareTo("id_kat")==0) ||(napis.compareTo("tytul")==0)||(napis.compareTo("opis")==0)) {
               
              k.setNazwa((String) request.getParameter("tytul"));
              k.setOpis((String) request.getParameter("opis"));
              k.setID((String) request.getParameter("id_kat"));
           }
        
       if((napis.compareTo("id_kat_pom")==0) ||(napis.compareTo("tytul_pom")==0)||(napis.compareTo("opis_pom")==0)) {     
           
             k.setID((String) request.getParameter("id_kat_pom"));
             k.setNazwa((String) request.getParameter("tytul_pom"));
             k.setOpis((String) request.getParameter("opis_pom"));

       
        if( (k.getNazwa()).compareTo("")== 0 ) wiad.add(Messages.makeError(Messages.errorFieldNameKat())); 
          else
            if ( db_con.czyKategoriaInna(k.getNazwa(), k.getID() ) ) wiad.add(Messages.makeError(Messages.errorNameKat())); 
             else
             {
               if (db_con.updateKategoria(k.getID(), k.getNazwa(), k.getOpis()) ) wiad.add(Messages.makeInfo(Messages.changeKat()));
                    else wiad.add(Messages.makeError(Messages.errorChangeKat())); %>
                <jsp:forward page="./edycja_podforow.jsp"/>
             <%  
             }
            } 
      
       } 
 
          for(int i=0; i<wiad.size(); i++) {
          out.print((String) wiad.get(i));
        }
      %>
      
            
        <p align="center"> <a href="./edycja_podforow.jsp"><%out.println(Messages.wielka(Messages.back())); %> </a>  </p>
        <br/>
     
    <form action="./edycja_kat.jsp" method="post">
      <table align="center" cellpadding="2" cellspacing="1" border="0">
       <caption> <%out.println(Messages.wielka(Messages.editionKat())); %> </caption>
       <tr>  <th> <%out.println(Messages.wielka(Messages.title())); %> </th> <th> <%out.println(Messages.wielka(Messages.describe())); %> </th> </tr>
       <tr> 
           <td> <input size="40" type="text" name="tytul_pom" value="<%=k.getNazwa()%>"/> </td>
           <td> <input size="40" type="text" name="opis_pom" value="<%=k.getOpis() %>"/>  </td> 
      </tr>
            <input type="hidden" name="id_kat_pom" value="<%=k.getID()%>"/>
       <tr>
         <td> </td> <td><input size="40" type="submit" value="<%out.println(Messages.wielka(Messages.change())); %>"/> </td>
       </tr>
      </table>
    </form>

     
    </body>
</html>