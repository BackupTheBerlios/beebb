<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

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
        <title>BeeBB :: Edycja uprawnien grupy</title>
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
           String opis="";
    if (pom.hasMoreElements()) {
           String field = (String) pom.nextElement();
           
       if( (field.compareTo("id_g")==0) || (field.compareTo("nazwa_g")==0) ) {
             grupa.setID( Integer.parseInt(request.getParameter("id_g")) );
             grupa.setNazwa(request.getParameter("nazwa_g"));
       }
       if( (field.compareTo("id_g_dod")==0) || (field.compareTo("nazwa_g_dod")==0)|| (field.compareTo("pod_dod")==0) ) {
             grupa.setID( Integer.parseInt(request.getParameter("id_g_dod")) );
             grupa.setNazwa(request.getParameter("nazwa_g_dod"));
             
             String pods=request.getParameter("pod_dod");
             if(pods.compareTo("")==0) out.print(Messages.makeError(Messages.errorAddPodforum()));
             else {
             String pod= (String) pods.subSequence(pods.lastIndexOf(" ")+1,pods.length());
             String kat= (String) pods.subSequence(0,pods.lastIndexOf(":"));
             int id_kat=db_con.dajIdKategorii(kat);
              if (id_kat == -1) out.print(Messages.makeError(Messages.errorAddPodforum()));
               else {
                  int id_pod =db_con.dajIdPodforum(id_kat,pod);
                  Privilage p= new Privilage(grupa.getID(),-1,id_pod,true,false);
               if ( db_con.insertPrivilage(p) )   out.print(Messages.makeInfo(Messages.addPodforum()));
               else out.print(Messages.makeError(Messages.errorAddPodforum()));
               }
             }
       }
           
       if(  (field.compareTo("usun_kat")==0)||(field.compareTo("id_g_usun")==0) || (field.compareTo("nazwa_g_usun")==0) ) {
             grupa.setID( Integer.parseInt(request.getParameter("id_g_usun")) );
             grupa.setNazwa(request.getParameter("nazwa_g_usun"));
             
              int id_kat=Integer.parseInt(request.getParameter("usun_kat")) ;
              
               ArrayList pod=db_con.getPodforaPrywatne(id_kat, true, true);
                for(int i=0; i<pod.size(); i++) { 
                    Podforum p =(Podforum) pod.get(i);
                    db_con.deletePrivilagePodGroup(grupa.getID(), p.getID());
                 }
              
              if ( db_con.deletePrivilageKatGroup(grupa.getID(),id_kat) ) out.print(Messages.makeInfo(Messages.removeKat()));
                else out.print(Messages.makeError(Messages.errorRemoveKat()));
       }
           
       if(  (field.compareTo("usun_kat_id")==0)||(field.compareTo("id_g_p_u")==0) || (field.compareTo("nazwa_g_p_u")==0)|| (field.compareTo("usun_pod_id")==0) ) {
             grupa.setID( Integer.parseInt(request.getParameter("id_g_p_u")) );
             grupa.setNazwa(request.getParameter("nazwa_g_p_u"));
             
              int id_kat=Integer.parseInt(request.getParameter("usun_kat_id"));
              int id_pod=Integer.parseInt(request.getParameter("usun_pod_id"));
              
              if ( (db_con.deletePrivilageKatGroup(grupa.getID(),id_kat))&&(db_con.deletePrivilagePodGroup(grupa.getID(),id_pod)) ) out.print(Messages.makeInfo(Messages.removePodforum()));
                else out.print(Messages.makeError(Messages.errorRemovePodforum()));
       }
           
       if( (field.compareTo("id_g_d")==0) || (field.compareTo("nazwa_g_d")==0)|| (field.compareTo("kat_d")==0) ) {
             grupa.setID( Integer.parseInt(request.getParameter("id_g_d")) );
             grupa.setNazwa(request.getParameter("nazwa_g_d"));
             
             int id_kat=db_con.dajIdKategorii(request.getParameter("kat_d"));
             if (id_kat==-1 ) out.print(Messages.makeError(Messages.errorAddKat()));
              else {
                Privilage p= new Privilage(grupa.getID(),id_kat,-1,true,false);
                if ( db_con.insertPrivilage(p) ) {
                     ArrayList po=db_con.getPodforaPrywatne(id_kat,true,true);
                    for(int v=0; v<po.size(); v++) { 
                        Podforum pod =(Podforum) po.get(v);
                        db_con.deletePrivilagePodGroup(grupa.getID(),pod.getID());
                         Privilage pp= new Privilage(grupa.getID(),-1,pod.getID(),true,false);
                         db_con.insertPrivilage(pp);
                    }  
                    out.print(Messages.makeInfo(Messages.addKat()));
                }
                 else out.print(Messages.makeError(Messages.errorAddKat()));
              }
       }
           
         if( (field.compareTo("id_g_z")==0) || (field.compareTo("nazwa_g_z")==0)|| (field.compareTo("zmien_kat")==0) 
              || (field.startsWith("czytanie_k"))|| (field.startsWith("pisanie_k"))|| (field.compareTo("zmien_kat_nr")==0)   ) {
             grupa.setID( Integer.parseInt(request.getParameter("id_g_z")) );
             grupa.setNazwa(request.getParameter("nazwa_g_z"));
             
             int id_kat=Integer.parseInt(request.getParameter("zmien_kat"));
             String nr=request.getParameter("zmien_kat_nr");
             boolean p="tak".compareTo(request.getParameter("pisanie_k"+nr))==0;
             boolean c="tak".compareTo(request.getParameter("czytanie_k"+nr))==0;
             
            Privilage pp= new Privilage(grupa.getID(),id_kat,-1,c,p);
               if ( db_con.updatePrivilageKat(pp) )   out.print(Messages.makeInfo(Messages.changeKat()));
               else out.print(Messages.makeError(Messages.errorChangeKat()));
            
            ArrayList podfora=db_con.getPodforaPrywatneGrupy(grupa.getID(),id_kat,true,true);
             for(int j=0; j<podfora.size(); j++){ 
                Podforum podf =(Podforum) podfora.get(j);
                Privilage ppp= new Privilage(grupa.getID(),-1,podf.getID(),c,p);
                db_con.updatePrivilagePod(ppp);
             }  
             
         }
           
          if( (field.compareTo("id_g_z_p")==0) || (field.compareTo("nazwa_g_z_p")==0)|| (field.compareTo("zmien_pod")==0) 
              || (field.startsWith("czytanie_p"))|| (field.startsWith("pisanie_p"))|| (field.compareTo("zmien_pod_nr")==0)   ) {
             grupa.setID( Integer.parseInt(request.getParameter("id_g_z_p")) );
             grupa.setNazwa(request.getParameter("nazwa_g_z_p"));
             
             int id_pod=Integer.parseInt(request.getParameter("zmien_pod"));
             String nr=request.getParameter("zmien_pod_nr");
             boolean p="tak".compareTo(request.getParameter("pisanie_p"+nr))==0;
             boolean c="tak".compareTo(request.getParameter("czytanie_p"+nr))==0;
             
            Privilage pp= new Privilage(grupa.getID(),-1,id_pod,c,p);
               if ( db_con.updatePrivilagePod(pp) )   out.print(Messages.makeInfo(Messages.changePod()));
               else out.print(Messages.makeError(Messages.errorChangePod()));
             
         }
    }
           %>
   
 
   <br/>
        
     <% ArrayList identkat= new ArrayList();
        ArrayList identpod= new ArrayList();
        ArrayList katlist= new ArrayList();
        int licz=0;
        ArrayList lista=db_con.getKategoriePrywatne(true,true); %>  
        <table style="" align="center" cellpadding="2" cellspacing="1" border="1">
            <caption> <font size="5" style="bold"></font> </caption>
            <tr> <th><%out.println(Messages.wielka(Messages.rozwin())); %></th> <th><%out.println(Messages.wielka(Messages.nr())); %></th> <th><%out.println(Messages.wielka(Messages.title())); %></th> 
                 <th><%out.println(Messages.wielka(Messages.describe())); %></th>   <th><%out.println(Messages.wielka(Messages.read())); %></th>  <th><%out.println(Messages.wielka(Messages.write())); %></th> 
                 <th><%out.println(Messages.wielka(Messages.change())); %></th>  <th><%out.println(Messages.wielka(Messages.remove())); %></th> </tr>
       <% for(int i=0; i<lista.size(); i++)
            { Kategoria kkk=(Kategoria) lista.get(i);
              Privilage p=db_con.getPrivilageKat(grupa.getID(), kkk.getID() );
              if (p==null)   identkat.add(new Integer(kkk.getID()));
              ArrayList podfora=db_con.getPodforaPrywatneGrupy(grupa.getID(), kkk.getID(),true,true);
             
              if( (p!=null) || (podfora.size()  > 0 ) ) { licz++;
         %><tr bgcolor="gold" ><td align="center"> <button id="plusik<%=i%>" onClick="rozwijanie('<%=i%>'); return false;"> +/- </button> </td> <td><%=licz %>.  </td> <td> <%=kkk.getNazwa() %> </td> <td><%=kkk.getOpis() %> </td> 
            <form action="./grupy_upr.jsp" method="post">
             
              <input type="hidden" name="id_g_z" value="<%=grupa.getID()%>">
              <input type="hidden" name="nazwa_g_z" value="<%=grupa.getNazwa()%>">
             <td>
              <%out.print(Messages.wielka(Messages.yes())); %><input type="radio" name="czytanie_k<%=i%>" value="tak" <%if (p==null) { %> disabled="" <% } else if (p.czytanie()){ %>checked="" <%}%>/>  
               <%out.print(Messages.wielka(Messages.no())); %><input type="radio" name="czytanie_k<%=i%>" value="nie"  <%if (p==null) { %> disabled="" <% } else if (!p.czytanie()){ %>checked="" <%}%>/>   
             </td>
              <td>
               <%out.print(Messages.wielka(Messages.yes())); %><input type="radio" name="pisanie_k<%=i%>" value="tak" <%if (p==null) { %> disabled="" <% } else if (p.pisanie()){ %>checked="" <%}%>/>  
               <%out.print(Messages.wielka(Messages.no())); %><input type="radio" name="pisanie_k<%=i%>" value="nie"  <%if (p==null) { %> disabled="" <% } else if (!p.pisanie()){ %>checked="" <%}%>/>   
             </td>
             <td>
                    <input type="hidden" name="zmien_kat" value="<%= kkk.getID() %>"/>
                    <input type="hidden" name="zmien_kat_nr" value="<%=i%>"/>
                   <input <%if (p==null) { %>disabled="" <%}%>  align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.change())); %>"/>
             </td>
            </form>
              <td>
              <form action="./grupy_upr.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemoveKat())+"');" %>">
                 <input type="hidden" name="id_g_usun" value="<%=grupa.getID()%>">
                 <input type="hidden" name="nazwa_g_usun" value="<%=grupa.getNazwa()%>">
                 <input type="hidden" name="usun_kat" value="<%= kkk.getID() %>"/>
                 <input  align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.remove())); %>"/>
             </form> 
             </td> 
  
         </tr>
     
            <tr class="w<%=i%>" bgcolor="yellow" > <td colspan="8" align="center" id="nee"> <%out.println(Messages.wielka(Messages.podKat())); %>: <%=kkk.getNazwa() %> </td></tr> 
         <%     
          for(int j=0; j<podfora.size(); j++){ 
               Podforum podf =(Podforum) podfora.get(j);
               Privilage pp=db_con.getPrivilagePod(grupa.getID(), podf.getID() );
               identpod.add(new Integer(podf.getID()));
         %><tr class="w<%=i%>" bgcolor="goldenrod"> <td> </td> <td><%=licz%>.<%=j+1%>  </td> <td> <%=podf.getTytul()%> </td> <td><%=podf.getOpis()%> </td> 
               <form action="./grupy_upr.jsp" method="post">
              <input type="hidden" name="id_g_z_p" value="<%=grupa.getID()%>">
              <input type="hidden" name="nazwa_g_z_p" value="<%=grupa.getNazwa()%>">
              <td>
              <%out.print(Messages.wielka(Messages.yes())); %><input type="radio" name="czytanie_p<%=j%><%=i%>" value="tak" <% if (pp.czytanie()){ %>checked="" <%}%>/>  
               <%out.print(Messages.wielka(Messages.no())); %><input type="radio" name="czytanie_p<%=j%><%=i%>" value="nie"  <%if (!pp.czytanie()){ %>checked="" <%}%>/>   
             </td>
              <td>
               <%out.print(Messages.wielka(Messages.yes())); %><input type="radio" name="pisanie_p<%=j%><%=i%>" value="tak" <%if (pp.pisanie()){ %>checked="" <%}%>/>  
               <%out.print(Messages.wielka(Messages.no())); %><input type="radio" name="pisanie_p<%=j%><%=i%>" value="nie"  <%if (!pp.pisanie()){ %>checked="" <%}%>/>   
             </td>
             <td> <input type="hidden" name="zmien_pod" value="<%= podf.getID() %>"/>
                    <input type="hidden" name="zmien_pod_nr" value="<%=j%><%=i%>"/>
                   
                   <input  <%if (p!=null) { %>disabled="" <%}%>  align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.change())); %>"/>
                 </form> 
             </td>
              <td>
              <form action="./grupy_upr.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemovePod())+"');" %>">
                 <input type="hidden" name="usun_kat_id" value="<%= kkk.getID() %>"/>
                 <input type="hidden" name="usun_pod_id" value="<%= podf.getID() %>"/>
                  <input type="hidden" name="id_g_p_u" value="<%=grupa.getID()%>">
                  <input type="hidden" name="nazwa_g_p_u" value="<%=grupa.getNazwa()%>">
                 <input  align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.remove())); %>"/>
             </form> 
             </td> 
           </tr>
          <% }%>
   
    
       <% }}%>
        </table>

        <br/> <br/>
        <form action="./grupy_upr.jsp" method="post">
            <table align="center" cellpadding="2" cellspacing="1" border="1">
                <th colspan="2" > <%= Messages.wielka(Messages.addingKat()) %> </th>
                <tr> 
                  <td>  <select name="kat_d" > 
                  <% int ll=0;
                         for (int i=0; i<lista.size(); i++) { 
                               Kategoria k=(Kategoria) lista.get(i);
                              int id= k.getID();
                             if ( identkat.contains(new Integer(id)) ) { ll++;      %>
                               <option><%= k.getNazwa()%></option>
                           <%    katlist.add(k);   } 
                           } %>      
                        </select>
                  </td>
                     
                <td>   
                  <input type="hidden" name="id_g_d" value="<%=grupa.getID()%>">
                  <input type="hidden" name="nazwa_g_d" value="<%=grupa.getNazwa()%>">
                  <input   <%if (ll==0) { %>disabled="" <%}%>  align="center" size="20"  type="submit" value="<%out.println(Messages.wielka(Messages.add())); %>"/> 
                </td> </tr>
            </table>       
        </form>
         <br/>
        <form action="./grupy_upr.jsp" method="post">
            <table align="center" cellpadding="2" cellspacing="1" border="1">
                <th colspan="2" > <%= Messages.wielka(Messages.addingPod()) %> </th>
                <tr> 
                  <td>  <select name="pod_dod" > 
                  <% int l=0;
                         for (int i=0; i<katlist.size(); i++) { 
                               Kategoria k=(Kategoria) katlist.get(i);
                              int id= k.getID();
                            ArrayList poo=db_con.getPodforaPrywatne(id,true,true);
                            for(int j=0; j<poo.size(); j++) {
                                Podforum ppp= (Podforum) poo.get(j);
                             if ( !identpod.contains(new Integer(ppp.getID())) ) { l++;      %>
                               <option><%= k.getNazwa()%>: <%= ppp.getTytul() %></option>
                           <% }      } 
                           } %>      
                        </select>
                  </td>          
                <td>   
                  <input type="hidden" name="id_g_dod" value="<%=grupa.getID()%>">
                  <input type="hidden" name="nazwa_g_dod" value="<%=grupa.getNazwa()%>">
                  <input   <%if (l==0) { %>disabled="" <%}%>   align="center" size="20"  type="submit" value="<%out.println(Messages.wielka(Messages.add())); %>"/> 
                </td> </tr>
            </table>       
        </form>
     <% } %>
    </body>
</html>