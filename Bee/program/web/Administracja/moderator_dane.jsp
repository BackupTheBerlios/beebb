<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

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
            function Info(param) {
               return confirm(param);
            }
            
            function dodaj() {
                   var f = document.getElementById('strz');
                   var kkk = document.getElementById('kategorie');
                   var y = f.appendChild(document.createElement('input'));
                   y.type='hidden';
                   y.name='katnaz';
                   y.value=kkk.options[kkk.selectedIndex].value;
                }
            
        </script>  
         <%! 
            String dajDana(String param) {
            if ((param == null)||(param.compareTo("null")==0)||(param.compareTo("")==0)) return "brak";
              return param;
            }
            
            void ustawUsera(javax.servlet.http.HttpServletRequest request, User user, String id, String login, String imie, String nazwisko, String email, String gg, String jabber, String lastlog) {
                
                user.setID(Integer.parseInt(request.getParameter(id)));
                user.setLogin(dajDana(request.getParameter(login)));
                user.setImie(dajDana(request.getParameter(imie)));
                user.setNazwisko(dajDana(request.getParameter(nazwisko)));
                user.setEmail(dajDana(request.getParameter(email)));
                user.setGG(dajDana(request.getParameter(gg)));
                user.setJabber(dajDana(request.getParameter(jabber)));
                user.setLastLog(dajDana(request.getParameter(lastlog))); 
                
            }
        
         %>
    </head>
    <%@ include file="../pages/servletObjects.jsp" %>

    <%
       User user = auth.getUser(request,db_con);
                if ( (user==null)||(!user.admin()) ) {  out.println(Messages.makeError(Messages.wielka(Messages.errorNotLoggedIn()))); } else {%>  
     <%
        ArrayList kategorie=db_con.getKategorie(true);
        int id_kat=-1;
        String katNazwa="";
        if ( kategorie.size() > 0 ) { Kategoria k=(Kategoria) kategorie.get(0); id_kat=k.getID(); katNazwa=k.getNazwa();  }
        ArrayList identpodforow= new ArrayList();
        
       Enumeration fff = request.getParameterNames();
       if (fff.hasMoreElements()) {
           String pom = (String) fff.nextElement();
              if( (pom.compareTo("id")==0)||(pom.compareTo("login")==0)||(pom.compareTo("imie")==0)|| 
                  (pom.compareTo("nazwisko")==0)||(pom.compareTo("email")==0)||(pom.compareTo("gg")==0)
                  ||(pom.compareTo("jabber")==0)||(pom.compareTo("lastlog")==0) ) {
               
              ustawUsera(request,user,"id","login","imie","nazwisko","email", "gg", "jabber", "lastlog");
             }
           
                if( (pom.compareTo("id_dodaj")==0)||(pom.compareTo("login_dodaj")==0)||(pom.compareTo("imie_dodaj")==0)|| 
                  (pom.compareTo("nazwisko_dodaj")==0)||(pom.compareTo("email_dodaj")==0)||(pom.compareTo("gg_dodaj")==0)
                  ||(pom.compareTo("jabber_dodaj")==0)||(pom.compareTo("lastlog_dodaj")==0)
                   ||(pom.compareTo("kategoria_dod")==0)||(pom.compareTo("podforum_dod")==0)||(pom.compareTo("liczbaPod_dodaj")==0) ) {
               
               ustawUsera(request,user,"id_dodaj","login_dodaj","imie_dodaj","nazwisko_dodaj","email_dodaj", "gg_dodaj", "jabber_dodaj", "lastlog_dodaj");
                
                int k=db_con.dajIdKategorii(request.getParameter("kategoria_dod"));
                if (k != -1) {
                int p=db_con.dajIdPodforum(k,request.getParameter("podforum_dod"));
                 if ( db_con.insertModerator( p, user.getID() ) ) {
                    out.print(Messages.makeInfo(Messages.addPodforum()));
                 } else out.print(Messages.makeError(Messages.errorAddPodforum()));
                }else out.print(Messages.makeError(Messages.errorAddPodforum()));
                
                String  licz=request.getParameter("liczbaPod_dodaj");
                if( licz.compareTo("0")==0 ) {
                    db_con.zmienUprModerator(user.getID(),true);
                }
              }
           
             if( (pom.compareTo("id_usun")==0)||(pom.compareTo("login_usun")==0)||(pom.compareTo("imie_usun")==0)|| 
                  (pom.compareTo("nazwisko_usun")==0)||(pom.compareTo("email_usun")==0)||(pom.compareTo("gg_usun")==0)
                  ||(pom.compareTo("jabber_usun")==0)||(pom.compareTo("lastlog_usun")==0)
                   ||(pom.compareTo("id_podforum")==0) ||(pom.compareTo("liczbaPod_usun")==0) ) {
               
               ustawUsera(request,user,"id_usun","login_usun","imie_usun","nazwisko_usun","email_usun", "gg_usun", "jabber_usun", "lastlog_usun");
                
                int id_pod=Integer.parseInt(request.getParameter("id_podforum"));
           
                  
              if ( db_con.usunPrawaModeratora(id_pod, user.getID()) ) out.print(Messages.makeInfo(Messages.removePodforum()));
                else  out.print(Messages.makeError(Messages.errorRemovePodforum()));
                
                   String  licz=request.getParameter("liczbaPod_usun");
                if( licz.compareTo("1")==0 ) {
                    db_con.zmienUprModerator(user.getID(),false);
                }
             }
           
            if( (pom.compareTo("id_s")==0)||(pom.compareTo("login_s")==0)||(pom.compareTo("imie_s")==0)|| 
                  (pom.compareTo("nazwisko_s")==0)||(pom.compareTo("email_s")==0)||(pom.compareTo("gg_s")==0)
                  ||(pom.compareTo("jabber_s")==0)||(pom.compareTo("lastlog_s")==0)||(pom.compareTo("katnaz")==0)  ) {
               
              ustawUsera(request,user,"id_s","login_s","imie_s","nazwisko_s","email_s", "gg_s", "jabber_s", "lastlog_s");
              katNazwa=request.getParameter("katnaz");
              
                int k=db_con.dajIdKategorii(request.getParameter("katnaz"));
                if (k != -1) {
                     id_kat=k;
                }else out.print(Messages.makeError(Messages.errorAddPodforum()));
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
            <caption> <font size="3" style="bold"><%= Messages.wielka(Messages.moderatePodforas()) %></font> </caption>
            <tr> <th><%= Messages.wielka(Messages.nr()) %></th> <th><%= Messages.wielka(Messages.title()) %></th> 
                 <th><%= Messages.wielka(Messages.describe()) %></th> <th><%= Messages.wielka(Messages.remove()) %></th> </tr>
         <%
          
            ArrayList lista2=db_con.getModerowanePodfora(user.getID());
            int liczbaPod=lista2.size();
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
                 <input type="hidden" name="liczbaPod_usun" value="<%= liczbaPod %>"/>
                 <input align="center" size="20"  type="submit" value="<%= Messages.wielka(Messages.remove()) %>"/>
                </form> 
             </td> 
         </tr>
         
          <% }%>
       </table>  <br/>
       
     <table name="tab" style="" align="center" cellpadding="2" cellspacing="1" border="1">
       <caption> <font size="3" style="bold"><%= Messages.wielka(Messages.addPodforums()) %> </font> </caption>
           <tr> <th> <%= Messages.wielka(Messages.category()) %> </th> <th><%= Messages.wielka(Messages.podforum()) %> </th> <th><%= Messages.wielka(Messages.add()) %> </th> </tr>
        <form class="dod" action="./moderator_dane.jsp" method="post" target="tresc">
           <tr> 
                  <td>  <select id="kategorie" name="kategoria_dod"  onchange="" > <% 
                                                    for (int i=0; i<kategorie.size(); i++) { 
                                                            Kategoria kat= (Kategoria) kategorie.get(i);
                                                             if (katNazwa.compareTo( kat.getNazwa() )==0 ) {  %>
                                                        <option selected=""><%= kat.getNazwa() %></option>
                                                         <%  } else { %>
                                                         <option><%= kat.getNazwa() %></option>
                                                          <% }
                                                          }%>
                        </select>
                  </td>
        
              
                  <td>  <select name="podforum_dod" > <% ArrayList podfora=db_con.getPodforaKategorii(id_kat,true);
                                                    for (int i=0; i<podfora.size(); i++) { 
                                                            Podforum pod= (Podforum) podfora.get(i);
                                                             if ( !identpodforow.contains(new Integer(pod.getID())) ) {      %>
                                                               <option><%= pod.getTytul()%></option>
                                                      <%       } 
                                                        } %>      
                        </select>
                  </td>
         
             <td align="center">
             
                 <input type="hidden" name="id_dodaj" value="<%= user.getID()%>">
                 <input type="hidden" name="login_dodaj" value="<%= user.getLogin()%>">
                 <input type="hidden" name="imie_dodaj" value="<%= user.getImie() %>">
                 <input type="hidden" name="nazwisko_dodaj" value="<%= user.getNazwisko() %>">
                 <input type="hidden" name="email_dodaj" value="<%= user.getEmail()%>">
                 <input type="hidden" name="gg_dodaj" value="<%= user.getGG()%>">
                 <input type="hidden" name="jabber_dodaj" value="<%= user.getJabber()%>">
                 <input type="hidden" name="lastlog_dodaj" value="<%= user.getLastLog()%>">
                  <input type="hidden" name="liczbaPod_dodaj" value="<%= liczbaPod %>"/>
                 <input align="center" size="20"  type="submit" value="<%= Messages.wielka(Messages.add()) %>"/>
               
             </td> 
           </tr>
         </form>
         <tr> <td colspan="2" align="center"> 
                <form id="strz"  onsubmit="dodaj();" action="./moderator_dane.jsp" method="post" target="tresc">
                  <input type="hidden" name="id_s" value="<%= user.getID()%>">
                  <input type="hidden" name="login_s" value="<%= user.getLogin()%>">
                  <input type="hidden" name="imie_s" value="<%= user.getImie() %>">
                  <input type="hidden" name="nazwisko_s" value="<%= user.getNazwisko() %>">
                  <input type="hidden" name="email_s" value="<%= user.getEmail()%>">
                  <input type="hidden" name="gg_s" value="<%= user.getGG()%>">
                  <input type="hidden" name="jabber_s" value="<%= user.getJabber()%>">
                  <input type="hidden" name="lastlog_s" value="<%= user.getLastLog()%>">
              
                  <input align="center" type="submit" value=">>>>"/>
                 
                 </form>
              </td> <td> </td> </tr>
     </table>
     <% } %>
    </body>
</html>
