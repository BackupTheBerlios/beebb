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
        <title>BeeBB :: Grupy</title>
        <link rel="stylesheet" href="../../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="../../js/podfora.js"></script>
    </head>
    
    <body> 
     <%@ include file="../../pages/servletObjects.jsp" %>

    <%
       User user = auth.getUser(request,db_con);
                if ( (user==null)||(!user.admin()) ) {  out.println(Messages.makeError(Messages.wielka(Messages.errorNotLoggedIn()))); } else {%>  
        
        
   <% Enumeration pom = request.getParameterNames();
           String nazwa="";
    if (pom.hasMoreElements()) {
           String field = (String) pom.nextElement();
           
       if( field.compareTo("nazwa_grupy")==0 ) {  
           nazwa=request.getParameter("nazwa_grupy");
    
            if( nazwa.compareTo("")== 0 ) out.print(Messages.makeError(Messages.errorFieldNameGroup()));
                else  {
             if ( db_con.dajIdGrupy(nazwa)!= -1 ) out.print(Messages.makeError(Messages.errorNameGroup()));
                else {
                 Group g= new Group(0,nazwa);
                 if ( db_con.insertGrupa(g) ) {
                     nazwa="";
                     out.print(Messages.makeInfo(Messages.addGroup()));
                  }
                 else
                   out.print(Messages.makeError(Messages.errorAddGroup()));
                }
               }
            
        }
             
       if(field.compareTo("usun_grupe")==0 )
           {
             String nr=request.getParameter("usun_grupe");
             if ( db_con.usunGrupe(Integer.parseInt(nr)) ) out.print(Messages.makeInfo(Messages.removeGroup()));
                else out.print(Messages.makeError(Messages.errorRemoveGroup()));
           }
    }
           
 
          
      %> 
      
      <% for(int i=0; i<wiad.size(); i++) {
          out.print((String) wiad.get(i));
        }
      %>
   
 
   <br/>
     <% ArrayList lista=db_con.getGroups(); %>  
        <table style="" align="center" cellpadding="2" cellspacing="1" border="1">
            <caption> <font size="5" style="bold"></font> </caption>
            <tr>  <th><%= Messages.wielka(Messages.nr()) %></th> 
                  <th><%= Messages.wielka(Messages.title()) %></th> 
                 <th><%= Messages.wielka(Messages.groupUsers()) %></th> 
                 <th><%= Messages.wielka(Messages.remove()) %></th>
                  <th><%= Messages.wielka(Messages.priviliges()) %></th>
            </tr> 
       <% for(int i=0; i<lista.size(); i++)
            { Group g=(Group) lista.get(i); 
         %><tr bgcolor="gold" >
             <td><%= i+1 %>  </td> <td> <%=g.getNazwa() %> </td> 
                                
             <td><form action="./group_users.jsp" method="post">
                 <input name="id_g" type="hidden" value="<%= g.getID() %>"/>
                 <input name="nazwa_g" type="hidden" value="<%= g.getNazwa() %>"/>
               
                 <input align="center" size="15"  type="submit" value="<%= Messages.wielka(Messages.groupUsers()) %>"/>
             </form> 
             </td> 
             <td><form action="./grupy.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemoveGroup())+"');" %>">
                 <input type="hidden" name="usun_grupe" value="<%= g.getID() %>"/>
                 <input  align="center" size="15"  type="submit" value="<%= Messages.wielka(Messages.remove()) %>"/>
             </form> 
             </td>
               <td><form action="./grupy_upr.jsp" method="post">
                 <input name="id_g" type="hidden" value="<%= g.getID() %>"/>
                 <input name="nazwa_g" type="hidden" value="<%= g.getNazwa() %>"/>
               
                 <input align="center" size="15"  type="submit" value="<%= Messages.wielka(Messages.priviliges()) %>"/>
             </form> 
             </td> 
           
         </tr>
      
       <% }%>
        </table>

        <br/> <br/>
        <form action="./grupy.jsp" method="post">
            <table align="center" cellpadding="2" cellspacing="1" border="1">
                <caption> <font size="5" style="bold"> <%out.println(Messages.wielka(Messages.addingGroups())); %> </font> </caption>
                <tr> <td><%out.println(Messages.wielka(Messages.title())); %>: </td>  <td> <input  size="50" type="text" name="nazwa_grupy" value="<%=nazwa%>"/> </td> </tr>
                <tr> <td></td> <td> <input align="center" size="20"  type="submit" value="<%out.println(Messages.wielka(Messages.add())); %>"/> </td> </tr>
            </table>       
        </form>
      <% } %>
    </body>
</html>