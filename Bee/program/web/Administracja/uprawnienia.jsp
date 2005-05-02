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
         
       <%! ArrayList u;
           String dajDana(String param)
            {
            if ((param == null)||(param.compareTo("null")==0)||(param.compareTo("")==0)) return "brak";
              return param;
            }
           String takNie(String param)
            {
            if ((param == null)||(param.compareTo("null")==0)||(param.compareTo("")==0)) return "brak";
              if (param.compareTo("T")==0) return "TAK";
               else return "NIE";
            }
             boolean dajTN(String param)
            {
              if (param==null) return false;
              return true;
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
         
            
   <% Enumeration params = request.getParameterNames();
      if (params.hasMoreElements()) {
           String field = (String) params.nextElement();
           
        if( (field.compareTo("czy_admin")==0) || (field.compareTo("czy_aktywny")==0)|| (field.compareTo("id")==0) ) {
           if( db_con.zmienUpr(Integer.decode(request.getParameter("id")).intValue(), dajTN(request.getParameter("czy_admin")), dajTN(request.getParameter("czy_aktywny"))))

              out.print(Messages.makeInfo(Messages.changeUpr())); 
            else out.print(Messages.makeError(Messages.errorChangeUpr()));
         }
        }   
            u=db_con.getUsers();  %>
 
     <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
      <th><%out.println(Messages.wielka(Messages.nr())); %></th> <th><%out.println(Messages.wielka(Messages.login())); %></th> <th><%out.println(Messages.wielka(Messages.name())); %></th> 
      <th><%out.println(Messages.wielka(Messages.surname())); %></th> <th><%out.println(Messages.wielka(Messages.email())); %></th> <th><%out.println(Messages.wielka(Messages.gg())); %></th> 
      <th><%out.println(Messages.wielka(Messages.jabber())); %></th> <th><%out.println(Messages.wielka(Messages.lastLogged())); %> </th> <th><%out.println(Messages.wielka(Messages.active())); %></th> 
      <th><%out.println(Messages.wielka(Messages.admin())); %></th><th><%out.println(Messages.wielka(Messages.moderator())); %></th> <th><%out.println(Messages.wielka(Messages.edition())); %></th>
     <% String czy_admin="", czy_moderator="", czy_aktywny="";
      
         Config conf = new Config();
          try {
             conf.readConfig(application);
           }
          catch(Exception e) {
           out.println(e);
         }
        
         int licz=1;
        for(int i=0; i<u.size(); i++) 
        { User pom=(User) u.get(i);
          
          int id= pom.getID();
         if ( id != conf.GUEST_ID ) { 
          String login=pom.getLogin();
          String imie=pom.getImie();
          String nazwisko=pom.getNazwisko();
          String email=pom.getEmail();
          String gg=pom.getGG();
          String jabber=pom.getJabber();
          String lastlog=pom.getLastLog();
          if( pom.aktywny() ) czy_aktywny=DataBase.TAK; else czy_aktywny=DataBase.NIE; 
          if( pom.admin() ) czy_admin=DataBase.TAK; else czy_admin=DataBase.NIE; 
          if( pom.moderator() ) czy_moderator=DataBase.TAK; else czy_moderator=DataBase.NIE; 
    
         %><tr> <td><%=licz %>. </td><td> <%=dajDana(login)%> </td><td> <%=dajDana(imie)%> </td> <td> <%=dajDana(nazwisko)%> </td>
                <td> <%=dajDana(email)%> </td><td> <%=dajDana(gg)%> </td> <td> <%=dajDana(jabber)%> </td> <td><%=dajDana(lastlog) %> </td>
                <td align="center"> <%=takNie(czy_aktywny)%> </td> <td align="center"> <%=takNie(czy_admin)%> </td>
                <td align="center"> <%=takNie(czy_moderator)%> </td> 
                <td align="center"> <form action="user_dane.jsp" method="post" target="tresc">
                        <input type="hidden" name="id" value="<%=id%>">
                        <input type="hidden" name="login" value="<%=login%>">
                        <input type="hidden" name="imie" value="<%=imie %>">
                        <input type="hidden" name="nazwisko" value="<%=nazwisko%>">
                        <input type="hidden" name="email" value="<%=email%>">
                        <input type="hidden" name="gg" value="<%=gg%>">
                        <input type="hidden" name="jabber" value="<%=jabber%>">
                        <input type="hidden" name="lastlog" value="<%=lastlog%>">
                        <input type="hidden" name="czy_admin" value="<%=czy_admin%>">
                        <input type="hidden" name="czy_moderator" value="<%=czy_moderator%>">
                        <input type="hidden" name="czy_aktywny" value="<%=czy_aktywny%>">
                        <input size="40" type="submit" value="<%out.println(Messages.wielka(Messages.edition())); %>">
                     </form>
                </td>
           </tr> <%       
         licz++;   }
         }   %>
     </table>      
    </body>
</html>
