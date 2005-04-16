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
             String dajTN(String param)
            {
              if (param==null) return "N";
              return "T";
            }
           
        %>
        <% 
         if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print(Messages.errorDataBaseConnection());
                out.print(e);
            } }%>
            
   <% Enumeration params = request.getParameterNames();
      if (params.hasMoreElements()) {
           String field = (String) params.nextElement();
           
        if( (field.compareTo("czy_admin")==0) || (field.compareTo("czy_moderator")==0)|| (field.compareTo("czy_aktywny")==0)|| (field.compareTo("id")==0) ) {
             int tmp_id = Integer.decode(request.getParameter("id")).intValue();
             boolean bool_if_adm = false;
             if(dajTN(request.getParameter("czy_admin")).compareTo(DataBase.TAK)==0) bool_if_adm=true;
             boolean bool_if_mod = false;
             if(dajTN(request.getParameter("czy_moderator")).compareTo(DataBase.TAK)==0) bool_if_mod=true;
             boolean bool_if_akt = false;
             if(dajTN(request.getParameter("czy_aktywny")).compareTo(DataBase.TAK)==0) bool_if_akt=true;
         if( db_con.zmienUpr(tmp_id,bool_if_adm,bool_if_mod, bool_if_akt))
              out.print(Messages.changeUpr()); 
            else out.print(Messages.errorChangeUpr());
         }
        }   
            u=db_con.getUsers();  %>
    <head><title>Uprawnienia</title></head>
    <body>

     <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
      <th>Nr.</th> <th><%out.println(Messages.wielka(Messages.login())); %></th> <th><%out.println(Messages.wielka(Messages.name())); %></th> <th>Nazwisko</th> <th>Email</th> <th>Nr. GG</th> 
      <th>Jabber</th> <th>Ostatni login </th> <th>Aktywny</th> <th>Administrator</th>
      <th>Moderator</th> <th>Edycja</th>
     <% for(int i=0; i<u.size(); i++) 
        { Hashtable pom=(Hashtable) u.get(i);
          
          String id=(String) pom.get("ID");
          String login=(String) pom.get("LOGIN");
          String imie=(String) pom.get("IMIE");
          String nazwisko=(String) pom.get("NAZWISKO");
          String email=(String) pom.get("EMAIL");
          String gg=(String) pom.get("GG");
          String jabber=(String) pom.get("JABBER");
          String lastlog=(String) pom.get("OSTATNIELOGOWANIE");
          String czy_admin=(String) pom.get("ADMIN");
          String czy_moderator=(String) pom.get("MODERATOR");
          String czy_aktywny=(String) pom.get("AKTYWNY");
         %><tr> <td><%=i+1%>. </td><td> <%=dajDana(login)%> </td><td> <%=dajDana(imie)%> </td> <td> <%=dajDana(nazwisko)%> </td>
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
                        <input size="40" type="submit" value="Edytuj">
                     </form>
                </td>
           </tr> <%       
        }
            %>
     </table>      
    </body>
</html>
