<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

 <jsp:useBean id="wiad" scope="request" class="java.util.ArrayList" />
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
        
         <script type="text/javascript" LANGUAGE="JavaScript">
         
           function next() {
                   var t = document.getElementById('tab');
                   var w = t.appendChild(document.createElement('tr'));
                 
                   var k = w.appendChild(document.createElement('td'));
                   var a = k.appendChild(document.createElement('input'));
                   a.type='text';
                   a.name='tytuly';
                   a.size='40';
                   var k2 = w.appendChild(document.createElement('td'));
                   var b = k2.appendChild(document.createElement('input'));
                   b.type='text';
                   b.name='opisy';
                   b.size='40';
               }
               
               function zlicz() {
                     var x = document.getElementsByTagName('tr');
	             var licz=x.length;
                     return licz-1;    
                 }
               
           function usun() {
                     var x = document.getElementsByTagName('tr');
	             var licz=x.length;
	             var usun=x[licz-1];
	             
                    if ( licz > 2 ) { 
                        usun.parentNode.removeChild(usun);
                    }
                 
               }
   
           function dodaj()
                {
                   var f = document.getElementById('form');
                   var y = f.appendChild(document.createElement('input'));
                   y.type='hidden';
                   y.name='licz';
                   y.value=zlicz();
                }
           </script>
        
    </head>
    <body>

    <%@ include file="../pages/servletObjects.jsp" %>

    <%
       User user = auth.getUser(request,db_con);
                if ( (user==null)||(!user.admin()) ) {  out.println(Messages.makeError(Messages.wielka(Messages.errorNotLoggedIn()))); } else {%>  
      
            
    <% Enumeration pom = request.getParameterNames();
      String tytul="",opis="", id_kat="";
      int licz=1;
    if (pom.hasMoreElements()) {
           String field = (String) pom.nextElement();
      if( (field.compareTo("id")==0) || (field.compareTo("tytul")==0) ||(field.compareTo("opis")==0) ) {     
       tytul=request.getParameter("tytul");
       opis=request.getParameter("opis");
       id_kat=request.getParameter("id");
      } 
       else {
         String id_k=request.getParameter("id_kat"); 
         String l=request.getParameter("licz");
         licz=(int) Integer.decode(l).intValue();
          String[] tt=request.getParameterValues("tytuly");
          String[] oo=request.getParameterValues("opisy");
          
         for(int j=0; j<licz; j++) {  
           String t=tt[j];
           String op=oo[j];
            Podforum podforum= new Podforum("0",t,op,DataBase.getDateToInsert(),"",db_con.TAK,db_con.NIE,"0","0",db_con);
            if( t.compareTo("")== 0 ) wiad.add(Messages.makeError(Messages.errorFieldNamePodforum()));
              else
                if ( db_con.dajIdPodforum(Integer.decode(id_k).intValue(), t) != -1 ) wiad.add(Messages.makeError(Messages.errorNamePodforum()));
                  else 
                    if ( db_con.insertPodforum(Integer.decode(id_k).intValue(), podforum) ) wiad.add(Messages.makeInfo(Messages.addPodforum()));
                      else wiad.add(Messages.makeError(Messages.errorAddPodforum()));
                  
            }%>
              <jsp:forward page="./edycja_podforow.jsp"/>
            <%
        }     
    }
       %>
            
       <br/> 
       <p align="center"> <%=Messages.wielka(Messages.category()) %>: <%=tytul%> </p>
       <p align="center"> <%=Messages.wielka(Messages.describe()) %>: <%=opis%> </p>
       <br/>
        <p align="center"> <a href="./edycja_podforow.jsp"><%=Messages.wielka(Messages.back()) %> </a>  </p>
       <br/>
       <p align="center" > <button   onclick="next();">&nbsp + &nbsp </button> &nbsp <button onclick="usun();">&nbsp - &nbsp </button> </p>
    <form id="form" onsubmit="dodaj();"  action="./podfora_form.jsp" method="post">
      <table id="tab" align="center" cellpadding="2" cellspacing="1" border="0">
       <caption> <%=Messages.wielka(Messages.addPodforums()) %> </caption>
       <tr> <th> <%=Messages.wielka(Messages.title()) %> </th> <th> <%=Messages.wielka(Messages.describe()) %> </th>  </tr>
   
      <tr id="w1" class="wiersz"> 
           <td> <input size="40" type="text" name="tytuly" value=""/> </td>
           <td> <input size="40" type="text" name="opisy" value=""/>  </td> 
      </tr>
      </table>
         <input type="hidden" name="id_kat" value="<%=id_kat%>"/>
        <p  align="center"> <input size="40" type="submit" value="<%=Messages.wielka(Messages.add()) %>"/> </p>
     </form>
     
     <% }%>
    </body>
</html>
