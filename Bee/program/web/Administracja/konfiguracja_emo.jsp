<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page import="pl.ltd.bee.Exceptions.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title> BeeBB :: Emotikonki </title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/> 
          <script type="text/javascript" LANGUAGE="JavaScript">
        function Info(param){
               return confirm(param);
                   
            }
        function zmien_emo()
                {
                   var f = document.getElementById('obrazek');
                   var box = document.getElementById('urle');
                   var url=box.options[box.selectedIndex].value;
                   f.src='/Bee'+url;
              
                }
            </script>
    </head>
    <body> 
 
<%@ include file="../pages/servletObjects.jsp" %>
            

    <%
       User user = auth.getUser(request,db_con);
                if ( (user==null)||(!user.admin()) ) {  out.println(Messages.makeError(Messages.wielka(Messages.errorNotLoggedIn()))); } else {%>  
     <% 
       Config conf = konfiguracja; 
       
       try {
             conf.readConfig(application);
           }
       catch(Exception e) {
           out.println(e);
       }
       
       Enumeration fff = request.getParameterNames();
       if (fff.hasMoreElements()) {
           String pom = (String) fff.nextElement();
        
          if( (pom.compareTo("nazwa_emo")==0)||(pom.compareTo("pliki_emo")==0) ) {
        
              String nazwa=request.getParameter("nazwa_emo");
              String url=request.getParameter("pliki_emo");
              
              conf.setSmileTag(nazwa, "/Bee"+url); 
  
                
                try{
                    conf.saveConfig(application);
                    out.print(Messages.makeInfo(Messages.changeConfigEmotikons()));
                 } catch(Exception e) {
                    out.println(e);
                 }
           }
           
            if( pom.compareTo("usun_emo")==0 ) {
        
              String nazwa=request.getParameter("usun_emo");
              conf.SMILES.remove(nazwa);
  
                try{
                    conf.saveConfig(application);
                    out.print(Messages.makeInfo(Messages.changeConfigEmotikons()));
                 } catch(Exception e) {
                    out.println(e);
                 }
           }
      }
        
   %>
    
 <table align="center" cellpadding="2" cellspacing="1" border="1">
   <caption> <font size="5" style="bold"> <%=Messages.wielka(Messages.emotikons()) %> </font> </caption>     
    <tr>  <th>  <%= Messages.wielka(Messages.number()) %> </th> <th>  <%= Messages.wielka(Messages.describe()) %> </th>
          <th>  <%= Messages.wielka(Messages.graphics()) %> </th> 
          <th>  <%= Messages.wielka(Messages.remove()) %> </th>
    </tr>

        <%  Hashtable emo=conf.SMILES;
           Object[] zemo=(emo.keySet()).toArray();
           for(int i=0; i<zemo.length; i++) {
             %>
       <tr> <td align="center"> <%= i+1%> <%="."%>  </td> 
            <td align="center"> <%= zemo[i] %>  </td> 
            <td align="center"> <img src="<%= emo.get(zemo[i]) %>" alt="<%=zemo[i]%>" />  </td> 
       
           <td><form action="./konfiguracja_emo.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemoveEmo())+"');" %>">
                     <input type="hidden" name="usun_emo" value="<%= zemo[i] %> "/> 
                    <input  align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.remove())); %>"/>
                 </form> 
            </td> 
       </tr>
        <% }%>
  </table>
   <br/>
     <% Set zbiory=application.getResourcePaths("/images/smiles/default/");
         if(zbiory == null) out.print("aaaaaaaaaaaa");
           Object[] pliki=(zbiory).toArray();
            %>
     <table align="center" cellpadding="2" cellspacing="1" border="1">
        <form action="./konfiguracja_emo.jsp" method="post">
         <th colspan="3" align="center">  <%= Messages.wielka(Messages.addingEmotikons()) %> </th>
         <tr>
           <td> <input type="text" value="" name="nazwa_emo" size="20"/> </td>
           <td>   <select id="urle" name="pliki_emo"  onchange="zmien_emo();" > 
                          <% 
                                 for(int j=0; j<pliki.length; j++) {
                                      
                                      String plik=(String) pliki[j];
                                      if ( plik.endsWith(".gif") ) {
                                        %>  
                              <option><%=plik %></option>
                                  <% } } %>
                  </select> </td>
           <td> <img id="obrazek" src="<%="/Bee"+pliki[0] %>" alt="<%= pliki[0] %>" /> </td>
         </tr>
         <tr> <td colspan="3" align="center"> <input type="submit" value=" <%out.println(Messages.wielka(Messages.add())); %>"/> </td> </tr>
         </form> 
    </table> 
  <% } %>
 </body>
</html>
