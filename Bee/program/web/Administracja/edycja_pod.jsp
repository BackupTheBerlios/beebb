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
         <jsp:useBean id="wiad" scope="request" class="java.util.ArrayList" />

         
        <% if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                out.print(e);
            }
        } %>
         
     <% 
       Enumeration fff = request.getParameterNames();
       int id_kat=0;
       if (fff.hasMoreElements()) {
           String pom = (String) fff.nextElement();
          if((pom.compareTo("id_pod")==0)||(pom.compareTo("opis")==0)||(pom.compareTo("tytul")==0)||(pom.compareTo("id_kat")==0) ) {  
            p.setNazwa( (String) request.getParameter("tytul") );
            p.setOpis( (String) request.getParameter("opis") );
            p.setID( (String) request.getParameter("id_pod") );
            p.setIdKat( (String) request.getParameter("id_kat") );
           }
        
     
      if((pom.compareTo("id_pod_pom")==0)||(pom.compareTo("opis_pom")==0)||(pom.compareTo("tytul_pom")==0)||(pom.compareTo("id_kat_pom")==0) ) { 
      
         p.setNazwa( (String) request.getParameter("tytul_pom") );
         p.setOpis( (String) request.getParameter("opis_pom") );
         p.setID( (String) request.getParameter("id_pod_pom") );
         p.setIdKat( (String) request.getParameter("id_kat_pom") );
         boolean ok=true;
        if( (p.getTytul()).compareTo("")== 0 ) wiad.add(Messages.makeError(Messages.errorFieldNamePodforum())); 
         else {
            id_kat=db_con.dajIdKategorii( (String) request.getParameter("kategoria_pom"));
            if( id_kat!=(p.getIdKat()) )   
              if ( db_con.dajIdPodforum(id_kat, p.getTytul()) != -1 ) { ok=false; wiad.add(Messages.makeError(Messages.errorNamePodforum())); } 
             
             if(ok) { 
                if ( db_con.updatePodforum(p.getID(), id_kat, p.getTytul(), p.getOpis()) )
                    wiad.add(Messages.makeInfo(Messages.changePod()));
                   else wiad.add(Messages.makeError(Messages.errorChangePod())); %>
                   <jsp:forward page="./edycja_podforow.jsp"/>
                <%
                }
             }
           }
        }
      
   %>
      <% for(int i=0; i<wiad.size(); i++) {
          out.print((String) wiad.get(i));
        }
        %>

            
        <p align="center"> <a href="./edycja_podforow.jsp"><%out.println(Messages.wielka(Messages.back())); %></a>  </p>
        <br/>
     
    <form action="./edycja_pod.jsp" method="post">
      <table align="center" cellpadding="2" cellspacing="1" border="0">
       <caption> <%out.println(Messages.wielka(Messages.editionPod())); %> </caption>
       <tr>  <th> <%out.println(Messages.wielka(Messages.title())); %> </th> <th> <%out.println(Messages.wielka(Messages.describe())); %></th> 
             <th> <%out.println(Messages.wielka(Messages.chooseKat())); %> : </th></tr>
       <tr> 
           <td> <input size="40" type="text" name="tytul_pom" value="<%=p.getTytul()%>"/> </td>
           <td> <input size="40" type="text" name="opis_pom" value="<%=p.getOpis() %>"/>  </td> 

           <td>  <select name="kategoria_pom" > <% ArrayList tytuly=db_con.getTytulyKategorii();
                                                    for (int i=0; i<tytuly.size(); i++) { %>
                                                        <option><%= tytuly.get(i) %></option>
                                                      <%  } %>      
                    </select>
                    </td>
        <input type="hidden" name="id_pod_pom" value="<%=p.getID()%>"/>
        <input type="hidden" name="id_kat_pom" value="<%=p.getIdKat()%>"/>
       <tr>
         <td> </td> <td><input size="40" type="submit" value="<%out.println(Messages.wielka(Messages.change())); %>"/> </td> <td> </td> </tr>
       </tr>
      </table>
    </form>

     
    </body>
</html>