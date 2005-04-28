<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

 <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
 <jsp:useBean id="user" scope="request" class="pl.ltd.bee.User" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Dane moderatora</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
 
              
        <script type="text/javascript" LANGUAGE="JavaScript">
            function Info(param)
            {
            return confirm(param);
            }
        </script>  
         <%! 
            String dajDana(String param)
            {
            if ((param == null)||(param.compareTo("null")==0)||(param.compareTo("")==0)) return "brak";
              return param;
            }
        
         %>
    </head>
     <% 
         if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                out.print(e);
            }
        } 
        
        ArrayList kategorie=db_con.getKategorie(true);
        int id_kat=-1;
        if ( kategorie.size() > 0 ) { Kategoria k=(Kategoria) kategorie.get(0); id_kat=k.getID();  }
        ArrayList identpodforow= new ArrayList();
        
       Enumeration fff = request.getParameterNames();
       if (fff.hasMoreElements()) {
           String pom = (String) fff.nextElement();
              if( (pom.compareTo("id")==0)||(pom.compareTo("login")==0)||(pom.compareTo("imie")==0)|| 
                  (pom.compareTo("nazwisko")==0)||(pom.compareTo("email")==0)||(pom.compareTo("gg")==0)
                  ||(pom.compareTo("jabber")==0)||(pom.compareTo("lastlog")==0) ) {
               
                user.setID(Integer.parseInt(request.getParameter("id")));
                user.setLogin(dajDana(request.getParameter("login")));
                user.setImie(dajDana(request.getParameter("imie")));
                user.setNazwisko(dajDana(request.getParameter("nazwisko")));
                user.setEmail(dajDana(request.getParameter("email")));
                user.setGG(dajDana(request.getParameter("gg")));
                user.setJabber(dajDana(request.getParameter("jabber")));
                user.setLastLog(dajDana(request.getParameter("lastlog")));
               
              }
           
                if( (pom.compareTo("id_dodaj")==0)||(pom.compareTo("login_dodaj")==0)||(pom.compareTo("imie_dodaj")==0)|| 
                  (pom.compareTo("nazwisko_dodaj")==0)||(pom.compareTo("email_dodaj")==0)||(pom.compareTo("gg_dodaj")==0)
                  ||(pom.compareTo("jabber_dodaj")==0)||(pom.compareTo("lastlog_dodaj")==0)
                   ||(pom.compareTo("kategoria_dod")==0)||(pom.compareTo("podforum_dod")==0) ) {
               
                user.setID(Integer.parseInt(request.getParameter("id_dodaj")));
                user.setLogin(dajDana(request.getParameter("login_dodaj")));
                user.setImie(dajDana(request.getParameter("imie_dodaj")));
                user.setNazwisko(dajDana(request.getParameter("nazwisko_dodaj")));
                user.setEmail(dajDana(request.getParameter("email_dodaj")));
                user.setGG(dajDana(request.getParameter("gg_dodaj")));
                user.setJabber(dajDana(request.getParameter("jabber_dodaj")));
                user.setLastLog(dajDana(request.getParameter("lastlog_dodaj")));
                
                int k=db_con.dajIdKategorii(request.getParameter("kategoria_dod"));
                if (k != -1) {
                int p=db_con.dajIdPodforum(k,request.getParameter("podforum_dod"));
                 if ( db_con.insertModerator( p, user.getID() ) ) {
                    out.print(Messages.makeInfo(Messages.addPodforum()));
                 } else out.print(Messages.makeError(Messages.errorAddPodforum()));
                }else out.print(Messages.makeError(Messages.errorAddPodforum()));
              }
           
             if( (pom.compareTo("id_usun")==0)||(pom.compareTo("login_usun")==0)||(pom.compareTo("imie_usun")==0)|| 
                  (pom.compareTo("nazwisko_usun")==0)||(pom.compareTo("email_usun")==0)||(pom.compareTo("gg_usun")==0)
                  ||(pom.compareTo("jabber_usun")==0)||(pom.compareTo("lastlog_usun")==0)
                   ||(pom.compareTo("id_podforum")==0) ) {
               
                user.setID(Integer.parseInt(request.getParameter("id_usun")));
                user.setLogin(dajDana(request.getParameter("login_usun")));
                user.setImie(dajDana(request.getParameter("imie_usun")));
                user.setNazwisko(dajDana(request.getParameter("nazwisko_usun")));
                user.setEmail(dajDana(request.getParameter("email_usun")));
                user.setGG(dajDana(request.getParameter("gg_usun")));
                user.setJabber(dajDana(request.getParameter("jabber_usun")));
                user.setLastLog(dajDana(request.getParameter("lastlog_usun")));
                
                int id_pod=Integer.parseInt(request.getParameter("id_podforum"));
           
                  
              if ( db_con.usunPrawaModeratora(id_pod, user.getID()) ) out.print(Messages.makeInfo(Messages.removePodforum()));
                else  out.print(Messages.makeError(Messages.errorRemovePodforum()));
             }
       }
      %>
    <body>
     <a href="./moderatorzy.jsp" target="tresc"><%= Messages.wielka(Messages.back()) %></a>
        <table name="tabuser" style="" align="center" cellpadding="2" cellspacing="1" border="1">
          <th colspan="2">  <%= Messages.wielka(Messages.userData()) %></th>
          <tr> <td><%= Messages.wielka(Messages.login())%></td> <td><%= user.getLogin()  %> </td> </tr>
          <tr> <td><%= Messages.wielka(Messages.name())%></td> <td><%= user.getImie() %> </td> </tr>
          <tr> <td><%out.print(Messages.wielka(Messages.surname()));%></td> <td><%= user.getNazwisko() %> </td> </tr>
          <tr> <td><%out.print(Messages.wielka(Messages.email()));%></td> <td><%= user.getEmail()  %> </td> </tr>
          <tr> <td><%out.print(Messages.wielka(Messages.gg()));%></td> <td><%= user.getGG()  %> </td> </tr>
          <tr> <td><%out.print(Messages.wielka(Messages.jabber()));%></td> <td><%= user.getJabber()  %> </td> </tr>
          <tr> <td><%out.print(Messages.wielka(Messages.lastLogged()));%></td> <td><%= user.getLastLog()  %> </td> </tr>
        </table>
        <br>
        
       <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
            <caption> <font size="5" style="bold"><%= Messages.wielka(Messages.moderatePodforas()) %></font> </caption>
            <tr> <th><%= Messages.wielka(Messages.nr()) %></th> <th><%= Messages.wielka(Messages.title()) %></th> 
                 <th><%= Messages.wielka(Messages.describe()) %></th> <th><%= Messages.wielka(Messages.remove()) %></th> </tr>
         <%
            ArrayList lista2=db_con.getModerowanePodfora(user.getID());          
          for (int j=0; j<lista2.size(); j++)
            { Podforum podf =(Podforum) lista2.get(j);
             identpodforow.add(new Integer(podf.getID()));
         %><tr bgcolor="goldenrod"> <td><%=j+1%>  </td> <td> <%=podf.getTytul()%> </td> <td><%=podf.getOpis()%> </td> 
          
             <td><form  action="./moderator_dane.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemovePod())+"');" %>">
                 
                 <input type="hidden" name="id_usun" value="<%= user.getID()%>">
                 <input type="hidden" name="login_usun" value="<%= user.getLogin()%>">
                 <input type="hidden" name="imie_usun" value="<%= user.getImie() %>">
                 <input type="hidden" name="nazwisko_usun" value="<%= user.getNazwisko() %>">
                 <input type="hidden" name="email_usun" value="<%= user.getEmail()%>">
                 <input type="hidden" name="gg_usun" value="<%= user.getGG()%>">
                 <input type="hidden" name="jabber_usun" value="<%= user.getJabber()%>">
                 <input type="hidden" name="lastlog_usun" value="<%= user.getLastLog()%>">
                 <input type="hidden" name="id_podforum" value="<%= podf.getID() %>"/>
                 <input align="center" size="20"  type="submit" value="<%= Messages.wielka(Messages.remove()) %>"/>
                </form> 
             </td> 
            
         </tr>
          <% }%>
       </table>  <br/>
       
     <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
       <caption> <font size="5" style="bold"></font> </caption>
        <form action="./moderator_dane.jsp" method="post" target="tresc">
           <tr> <th colspan="2"> <%= Messages.wielka(Messages.addPodforums()) %> </th> </tr>
           <tr> <td><%= Messages.wielka(Messages.category()) %> </td> 
                  <td>  <select name="kategoria_dod" > <% 
                                                    for (int i=0; i<kategorie.size(); i++) { 
                                                            Kategoria kat= (Kategoria) kategorie.get(i);    %>
                                                        <option><%= kat.getNazwa() %></option>
                                                      <%  } %>      
                        </select>
                  </td>
           </tr>
           <tr> <td><%= Messages.wielka(Messages.podforum()) %> </td> 
                  <td>  <select name="podforum_dod" > <% ArrayList podfora=db_con.getPodforaKategoriiAll(id_kat,true);
                                                    for (int i=0; i<podfora.size(); i++) { 
                                                            Podforum pod= (Podforum) podfora.get(i);
                                                             if ( !identpodforow.contains(new Integer(pod.getID())) ) {      %>
                                                               <option><%= pod.getTytul()%></option>
                                                      <%       } 
                                                        } %>      
                        </select>
                  </td>
           </tr> 
           <tr>
             <td colspan="2" align="center">
             
                 <input type="hidden" name="id_dodaj" value="<%= user.getID()%>">
                 <input type="hidden" name="login_dodaj" value="<%= user.getLogin()%>">
                 <input type="hidden" name="imie_dodaj" value="<%= user.getImie() %>">
                 <input type="hidden" name="nazwisko_dodaj" value="<%= user.getNazwisko() %>">
                 <input type="hidden" name="email_dodaj" value="<%= user.getEmail()%>">
                 <input type="hidden" name="gg_dodaj" value="<%= user.getGG()%>">
                 <input type="hidden" name="jabber_dodaj" value="<%= user.getJabber()%>">
                 <input type="hidden" name="lastlog_dodaj" value="<%= user.getLastLog()%>">
              
                 <input align="center" size="20"  type="submit" value="<%= Messages.wielka(Messages.add()) %>"/>
               
             </td> 
           </tr>
           
         </form>
     </table>
    </body>
</html>
