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
                 <th><%out.println(Messages.wielka(Messages.describe())); %></th> <th><%out.println(Messages.wielka(Messages.edition())); %></th> <th><%out.println(Messages.wielka(Messages.remove())); %></th> 
                 <th><%out.println(Messages.wielka(Messages.add())); %></th> </tr>
       <% for(int i=0; i<lista.size(); i++)
            { Kategoria kkk=(Kategoria) lista.get(i);
              ArrayList lista2 = kkk.getPodfora(true);  
         %><tr bgcolor="gold" ><td align="center"> <button id="plusik<%=i%>" onClick="rozwijanie('<%=i%>'); return false;"> +/- </button> </td> <td><%= i+1 %>  </td> <td> <%=kkk.getNazwa() %> </td> <td><%=kkk.getOpis() %> </td> 
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
             <td><form action="./podfora_form.jsp" method="post">
                 <input name="id" type="hidden" value="<%= kkk.getID() %>"/>
                 <input name="tytul" type="hidden" value="<%= kkk.getNazwa() %>"/>
                 <input name="opis" type="hidden" value="<%= kkk.getOpis() %>"/>
                 <input align="center" size="15"  type="submit" value="<%out.println(Messages.wielka(Messages.add())); %>"/>
             </form> 
             </td>
         </tr>
         <tr> <td colspan="7">
         <p id="podfora<%=i%>" class="podfora"> 
        <table style="" align="center" cellpadding="2" cellspacing="1" border="1">
         
         <tr><th><%out.println(Messages.wielka(Messages.nr())); %></th> <th><%out.println(Messages.wielka(Messages.title())); %></th> 
                 <th><%out.println(Messages.wielka(Messages.describe())); %></th> <th><%out.println(Messages.wielka(Messages.edition())); %></th> <th><%out.println(Messages.wielka(Messages.remove())); %></th> 
         </tr>
            <tr bgcolor="yellow" id="number2" > <td colspan="5" align="center" id="nee"> <%out.println(Messages.wielka(Messages.podKat())); %>: <%=kkk.getNazwa() %> </td></tr> 
         <%     
          for(int j=0; j<lista2.size(); j++)
            { Podforum podf =(Podforum) lista2.get(j);
         %><tr bgcolor="goldenrod"> <td><%=i+1%>.<%=j+1%>  </td> <td> <%=podf.getTytul()%> </td> <td><%=podf.getOpis()%> </td> 
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
     
         </tr>
          <% }%>
          
          </table>
          </p>
          </td>
          </tr>
    
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