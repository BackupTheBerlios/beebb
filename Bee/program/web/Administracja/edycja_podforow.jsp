<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>

        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
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
        <title>BeeBB :: Edycja Kategorii</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="../js/podfora.js"></script>
     <%!
           String takNie(boolean param) {
            if ( param ) return "TAK";
               else return "NIE";
            }
           
            boolean tN(String param) {
            if ( param.compareTo("true")==0 ) return false;
               return true;
            }
            
            boolean zmienNaPrywatne(DataBase db_con, int id, boolean tf) {
                
              if ( !db_con.zmienPrywatnoscPodforum(id,tf) ) return false;
              ArrayList wat=db_con.getWatkiPodforum(id);
               for(int i=0;i<wat.size();i++) {
                  if ( !db_con.zmienPrywatnoscWatku( ((Integer)wat.get(i)).intValue() ,tf ) ) return false;
                  
                    ArrayList wyp=db_con.getWypowiedziWatku( ((Integer)wat.get(i)).intValue() );
                    for(int j=0;j<wyp.size();j++) {
                       if ( !db_con.zmienPrywatnoscWypowiedzi( ((Integer)wyp.get(j)).intValue() ,tf ) ) return false;
                    }
               }
              return true;
            }
           
     %>
    </head>
    
    <body> 
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
        
        
   <% Enumeration pom = request.getParameterNames();
           String nazwa="";
           String opis="";
    if (pom.hasMoreElements()) {
           String field = (String) pom.nextElement();
           
       if( (field.compareTo("nazwa_kat")==0) || (field.compareTo("opis_kat")==0) ) {  
           nazwa=request.getParameter("nazwa_kat");
           opis=request.getParameter("opis_kat");
    
            if( nazwa.compareTo("")== 0 ) out.print(Messages.makeError(Messages.errorFieldNameKat()));
                else  {
             if ( db_con.dajIdKategorii(nazwa)!= -1 ) out.print(Messages.makeError(Messages.errorNameKat()));
                else
                { Kategoria k= new Kategoria("0",nazwa,opis,db_con.TAK,db_con.NIE,db_con);
                 if (db_con.insertKategoria(0,k) ) {
                  out.print(Messages.makeInfo(Messages.addKat()));
                  nazwa="";
                  opis="";
                }
                  else out.print(Messages.makeError(Messages.errorAddKat()));
               }
            }
        }
             
       if(field.compareTo("usun_kat")==0 )
           {
             String nr=request.getParameter("usun_kat");
             if ( db_con.zmienAktywnoscKategorii(Integer.parseInt(nr), false)) out.print(Messages.makeInfo(Messages.removeKat()));
                else out.print(Messages.makeError(Messages.errorRemoveKat()));
           }
        if (field.compareTo("usun_pod")==0 )
           {
             String nr=request.getParameter("usun_pod");
             if ( db_con.zmienAktywnoscPodforum(Integer.parseInt(nr), false)) out.print(Messages.makeInfo(Messages.removePodforum()));
                else  out.print(Messages.makeError(Messages.errorRemovePodforum()));
           }
           
        if ( (field.compareTo("upr_kat")==0)||( field.compareTo("upr_kat_bool")==0 ) )
           {
             int id= Integer.parseInt(request.getParameter("upr_kat"));
             boolean tf=tN(request.getParameter("upr_kat_bool"));
            if ( !db_con.zmienPrywatnoscKategorii(id,tf) ) out.print(Messages.makeError(Messages.errorChangePrivates()));
                else {
                 ArrayList pod=db_con.getPodforaKategorii(id);
                 boolean ok=true;
                 for(int i=0;i<pod.size();i++) {
                   if ( !zmienNaPrywatne(db_con, ((Integer)pod.get(i)).intValue(), tf) ) { ok=false; break; }
                 }
                 if ( ok ) out.print(Messages.makeInfo(Messages.changePrivates()));
                  else  out.print(Messages.makeError(Messages.errorChangePrivates()));
                }
           }
           
        if ( (field.compareTo("upr_pod")==0)||( field.compareTo("upr_pod_bool")==0 ) )
           {
             int id= Integer.parseInt(request.getParameter("upr_pod"));
             boolean tf=tN(request.getParameter("upr_pod_bool"));
             if ( zmienNaPrywatne(db_con, id, tf) ) out.print(Messages.makeInfo(Messages.changePrivates()));
                else  out.print(Messages.makeError(Messages.errorChangePrivates()));
           }
        }
      %> 
      
      <% for(int i=0; i<wiad.size(); i++) {
          out.print((String) wiad.get(i));
        }
      %>
   
 
   <br/>
     <% ArrayList lista=db_con.getKategorie(true); %>  
        <table style="" align="center" cellpadding="2" cellspacing="1" border="1">
            <caption> <font size="5" style="bold"></font> </caption>
            <tr> <th><%out.println(Messages.wielka(Messages.rozwin())); %></th> <th><%out.println(Messages.wielka(Messages.nr())); %></th> <th><%out.println(Messages.wielka(Messages.title())); %></th> 
                 <th><%out.println(Messages.wielka(Messages.describe())); %></th> <th><%out.println(Messages.wielka(Messages.privates())); %></th>  <th><%out.println(Messages.wielka(Messages.edition())); %></th> 
                 <th><%out.println(Messages.wielka(Messages.remove())); %></th> <th><%out.println(Messages.wielka(Messages.privates())); %></th>  <th><%out.println(Messages.wielka(Messages.add())); %></th> </tr>
       <% for(int i=0; i<lista.size(); i++)
            { Kategoria kkk=(Kategoria) lista.get(i);
              ArrayList lista2 = kkk.getPodfora(true);  
         %><tr bgcolor="gold" ><td align="center"> <button id="plusik<%=i%>" onClick="rozwijanie('<%=i%>'); return false;"> +/- </button> </td> <td><%= i+1 %>.  </td> <td> <%=kkk.getNazwa() %> </td> <td><%=kkk.getOpis() %> </td> 
                               <td align="center" ><%= takNie(kkk.czyPrywatna()) %> </td> 
             <td><form action="./edycja_kat.jsp" method="post">
                 <input name="id_kat" type="hidden" value="<%= kkk.getID() %>"/>
                 <input name="tytul" type="hidden" value="<%= kkk.getNazwa() %>"/>
                 <input name="opis" type="hidden" value="<%= kkk.getOpis() %>"/>
                 <input align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.edition())); %>"/>
             </form> 
             </td> 
             <td><form action="./edycja_podforow.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemoveKat())+"');" %>">
                 <input type="hidden" name="usun_kat" value="<%= kkk.getID() %>"/>
                 <input  align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.remove())); %>"/>
             </form> 
             </td> 
             </td> 
             <td><form action="./edycja_podforow.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemoveKat())+"');" %>">
                   <input type="hidden" name="upr_kat" value="<%= kkk.getID() %>"/>
                   <input type="hidden" name="upr_kat_bool" value="<%= kkk.czyPrywatna() %>"/>
                   <input  align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.change())); %>"/>
                 </form> 
             </td> 
             <td><form action="./podfora_form.jsp" method="post">
                 <input name="id" type="hidden" value="<%= kkk.getID() %>"/>
                 <input name="tytul" type="hidden" value="<%= kkk.getNazwa() %>"/>
                 <input name="opis" type="hidden" value="<%= kkk.getOpis() %>"/>
                 <input align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.add())); %>"/>
             </form> 
             </td>
         </tr>
     
            <tr class="w<%=i%>" bgcolor="yellow" > <td colspan="9" align="center" id="nee"> <%out.println(Messages.wielka(Messages.podKat())); %>: <%=kkk.getNazwa() %> </td></tr> 
         <%     
          for(int j=0; j<lista2.size(); j++)
            { Podforum podf =(Podforum) lista2.get(j);
         %><tr class="w<%=i%>" bgcolor="goldenrod"> <td> </td> <td><%=i+1%>.<%=j+1%>  </td> <td> <%=podf.getTytul()%> </td> <td><%=podf.getOpis()%> </td> 
           <td align="center" ><%= takNie(podf.czyPrywatne()) %> </td> 
             <td><form action="./edycja_pod.jsp" method="post">
                 <input name="id_kat" type="hidden" value="<%= kkk.getID() %>"/>
                 <input name="id_pod" type="hidden"  value="<%= podf.getID() %>"/>
                 <input name="tytul"  type="hidden"  value="<%= podf.getTytul() %>"/>
                 <input name="opis"   type="hidden"  value="<%= podf.getOpis() %>"/>
                 <input align="center" size="20"  type="submit" value="<%out.println(Messages.wielka(Messages.edition())); %>"/>
             </form> 
             </td> 
            <td><form  action="./edycja_podforow.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemovePod())+"');" %>">
                   <input type="hidden" name="usun_pod" value="<%= podf.getID() %>"/>
                   <input align="center" size="20"  type="submit" value="<%out.println(Messages.wielka(Messages.remove())); %>"/>
                 </form> 
             </td> 
             <td><form action="./edycja_podforow.jsp" method="post" onsubmit="<%= "return Info('"+Messages.wielka(Messages.isRemoveKat())+"');" %>">
                   <input type="hidden" name="upr_pod" value="<%= podf.getID() %>"/>
                   <input type="hidden" name="upr_pod_bool" value="<%= podf.czyPrywatne() %>"/>
                   <input  align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.change())); %>"/>
                 </form> 
             </td> 
             <td> </td>
         </tr>
          <% }%>
   
    
       <% }%>
        </table>

        <br/> <br/>
        <form action="./edycja_podforow.jsp" method="post">
            <table align="center" cellpadding="2" cellspacing="1" border="1">
                <caption> <font size="5" style="bold"> <%out.println(Messages.wielka(Messages.addingKat())); %> </font> </caption>
                <tr> <td><%out.println(Messages.wielka(Messages.title())); %>: </td>  <td> <input  size="50" type="text" name="nazwa_kat" value="<%=nazwa%>"/> </td> </tr>
                <tr> <td><%out.println(Messages.wielka(Messages.describe())); %>:  </td>  <td> <input  size="50" type="text" name="opis_kat" value="<%=opis%>"/>  </td>  </tr>
                <tr> <td></td> <td> <input align="center" size="20"  type="submit" value="<%out.println(Messages.wielka(Messages.add())); %>"/> </td> </tr>
            </table>       
        </form>
   
    </body>
</html>