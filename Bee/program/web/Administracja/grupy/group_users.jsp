<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

   <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
   <jsp:useBean id="grupa" scope="request" class="pl.ltd.bee.Group" />
       
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Uzytkownicy grupy</title>
        <link rel="stylesheet" href="../../styles/temat.css" type="text/css"/> 
          <script type="text/javascript" LANGUAGE="JavaScript">
            function Info(param) {
               return confirm(param);
            }
            </script>
    </head>
    <body>
    
       <%! ArrayList u;
           String dajDana(String param)
            {
            if ((param == null)||(param.compareTo("null")==0)||(param.compareTo("")==0)) return "brak";
              return param;
            }
           
        %>
        <%
           if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                out.print(e);
            }
        } %>
         
            
   <%  ArrayList identuserow= new ArrayList();
   
   Enumeration params = request.getParameterNames();
      if (params.hasMoreElements()) {
           String field = (String) params.nextElement();
           
        if( (field.compareTo("id_g")==0) || (field.compareTo("nazwa_g")==0) ) {
             
             grupa.setID( Integer.parseInt(request.getParameter("id_g")) );
             grupa.setNazwa(request.getParameter("nazwa_g"));
         }
          
       if( (field.compareTo("id_u_u")==0)||(field.compareTo("id_g_u")==0) || (field.compareTo("nazwa_g_u")==0)    ) {
             
             grupa.setID( Integer.parseInt(request.getParameter("id_g_u")) );
             grupa.setNazwa(request.getParameter("nazwa_g_u"));
             
              if (db_con.deleteUserGroup(  Integer.parseInt(request.getParameter("id_u_u")), grupa.getID() ) )
                      out.print(Messages.makeInfo(Messages.removeUserGroup()));
                  else  out.print(Messages.makeError(Messages.errorRemoveUserGroup()));
         }
           
       if( (field.compareTo("user_dod")==0)||(field.compareTo("id_g_dod")==0) || (field.compareTo("nazwa_g_dod")==0)    ) {
             
             grupa.setID( Integer.parseInt(request.getParameter("id_g_dod")) );
             grupa.setNazwa(request.getParameter("nazwa_g_dod"));
             
             User us=db_con.getUser(request.getParameter("user_dod"));
             if (us != null) {
                 if (db_con.insertUserGroup( us.getID(), grupa.getID() ) )
                      out.print(Messages.makeInfo(Messages.addUserGroup()));
                  else  out.print(Messages.makeError(Messages.errorAddUserGroup()));
             }else out.print(Messages.makeError(Messages.errorAddUserGroup()));
             
         }
        }   
              //  u=db_con.getUsers();
            u=db_con.getGroupUsersAktywni(true,grupa.getID());  %>
           
    <p align="center"> <a href="./grupy.jsp"><%= Messages.wielka(Messages.back()) %></a>  </p>
        <br/>
 
     <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
       <caption> <font size="4" style="bold"><%= Messages.wielka(Messages.groupUsers()) %>:<%="  "+grupa.getNazwa() %> </font> </caption>
      <th><%= Messages.wielka(Messages.nr()) %></th> <th><%= Messages.wielka(Messages.login()) %></th> <th><%= Messages.wielka(Messages.name()) %></th> 
      <th><%= Messages.wielka(Messages.surname()) %></th> <th><%= Messages.wielka(Messages.email()) %></th> <th><%= Messages.wielka(Messages.gg()) %></th> 
      <th><%= Messages.wielka(Messages.jabber()) %></th>  <th><%= Messages.wielka(Messages.remove()) %></th> 
     <% 
      
        for(int i=0; i<u.size(); i++) { 
          User pom=(User) u.get(i);
        
          identuserow.add(new Integer(pom.getID()));
          
         %><tr> <td><%=i+1 %>. </td><td> <%=dajDana(pom.getLogin())%> </td><td> <%=dajDana(pom.getImie())%> </td> <td> <%=dajDana(pom.getNazwisko())%> </td>
                <td> <%=dajDana(pom.getEmail())%> </td><td> <%=dajDana(pom.getGG())%> </td> <td> <%=dajDana(pom.getJabber())%> </td>
                <td align="center"> 
                     <form action="./group_users.jsp" method="post" target="tresc" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemoveUserGroup())+"');" %>">
                        <input type="hidden" name="id_u_u" value="<%=pom.getID()%>">
                        <input type="hidden" name="id_g_u" value="<%=grupa.getID()%>">
                        <input type="hidden" name="nazwa_g_u" value="<%=grupa.getNazwa()%>">
                        <input size="40" type="submit" value="<%out.println(Messages.wielka(Messages.remove())); %>">
                     </form>
                </td>
           </tr> <%       
          }   %>
     </table>
        <br/>
       
     <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
       <caption> <font size="3" style="bold"> </font> </caption>
           <tr>  <th><%= Messages.wielka(Messages.user()) %> </th> <th><%= Messages.wielka(Messages.add()) %> </th> </tr>
        <form class="dod" action="./group_users.jsp" method="post" target="tresc">
           <tr> 
                  <td>  <select name="user_dod" > 
                  <% ArrayList users=db_con.getUsersAktywni(true);
                      Config conf = new Config();
                       try {
                        conf.readConfig(application);
                       }
                      catch(Exception e) {
                       out.println(e);
                      }
                         for (int i=0; i<users.size(); i++) { 
                              User user= (User) users.get(i);
                              int id= user.getID();
                             if ( (!identuserow.contains(new Integer(id))) && (id != conf.GUEST_ID) ) {      %>
                               <option><%= user.getLogin()%></option>
                           <%       } 
                           } %>      
                        </select>
                  </td>
                   <td> 
                    <input type="hidden" name="id_g_dod" value="<%=grupa.getID()%>">
                    <input type="hidden" name="nazwa_g_dod" value="<%=grupa.getNazwa()%>">
                   <input align="center" size="20"  type="submit" value="<%= Messages.wielka(Messages.add()) %>"/> 
                  </td>
             </tr>     
         </form>
       </table  
    </body>
</html>