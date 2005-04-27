<%@page contentType="text/html;"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead("./..",Messages.wielka(Messages.add())));%>
    <body onLoad="resizeDodajW()" onresize="resizeDodajW()">
        <%@ include file="servletObjects.jsp" %>
        <table align="center" border="0" id="tableDodajW"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr>
                <td> 
        <% Enumeration flds = request.getParameterNames();
        /* Validacja za pomocą JS */
/*
 To powinno dzialac :
        if (request.getCharacterEncoding() == null)
               request.setCharacterEncoding("UTF-8");
 ale nie dziala, za to dziala to:
      autor = new String(autor.getBytes("8859_1"),"UTF-8");
*/
        
            class Dodaj {
                
                javax.servlet.jsp.JspWriter out;
                DataBase db_con;
                
                public Dodaj(javax.servlet.jsp.JspWriter out,DataBase db_con) {
                    this.out=out;
                    this.db_con=db_con;
                }
                
                public void dodajWypowiedz(Watek wt, String ID_Usera, String Nazwa_Usera, String text) throws Exception {
                    if (wt!=null) { 
                        String prywatne=DataBase.NIE;
                        if(wt.czyPrywatny()) prywatne=DataBase.TAK;
                        Wypowiedz wp = new Wypowiedz("0",ID_Usera,Nazwa_Usera,db_con.getDateToInsert(),text,prywatne,DataBase.TAK,db_con);
                        if (!db_con.insertWypowiedz(String.valueOf(wt.getID()),wp))
                            out.print(Messages.errorDataBaseConnection());
                        else out.print(Messages.addedMessage() + "<br/>"); 
                    } else out.print(Messages.errorDataBaseConnection());
                  }
                
                
                public Watek dodajWatek(String podforum,String ID_Usera,String Nazwa_Usera,String title) throws Exception {
                    Podforum pf = db_con.getPodforum(Integer.decode(podforum).intValue());
                    Watek wt;
                    String prywatne=DataBase.NIE;
                    if(pf.czyPrywatne()) prywatne=DataBase.TAK;
                    String Nazwa_Usera2=Nazwa_Usera;
                    if ((Integer.decode(ID_Usera).intValue())==Config.GUEST_ID)
                        Nazwa_Usera2="~" + Nazwa_Usera;
                    wt = new Watek("0",ID_Usera,Nazwa_Usera,title,db_con.getDateToInsert(),db_con.getDateToInsert(),Nazwa_Usera2,prywatne,DataBase.TAK,DataBase.NIE,DataBase.NIE,"0","0",db_con);
                    wt = db_con.insertWatek(podforum,wt);
                    if (wt==null) out.print(Messages.errorDataBaseConnection()); else out.print(Messages.addedThread()  + "<br/>");
                    return wt;
                }
                
                public void incrAddWatek(Watek wt,String ID_Usera, String Nazwa_Usera) throws Exception {
                    Podforum pf = db_con.getPodforumbyWatek(wt.getID());
                    wt.zwiekszLiczbeAktywnychWypowiedzi();
                    String Nazwa_Usera2=Nazwa_Usera;
                    if ((Integer.decode(ID_Usera).intValue())==Config.GUEST_ID)
                        Nazwa_Usera2="~" + Nazwa_Usera;

                    wt.setDataOstWypowiedzi(DataBase.getDate());
                    wt.setAutorOstWypowiedzi(Nazwa_Usera2);
                    pf.setDataOstWypowiedzi(DataBase.getDate());
                    pf.setAutorOstWypowiedzi(Nazwa_Usera2);
                    
                    
                    if (!db_con.updateWatek(wt)) out.print(Messages.errorDataBaseConnection());
                    pf.zwiekszLiczbeAktywnychWatkow();
                    pf.zwiekszLiczbeAktywnychWypowiedzi();
                    if (!db_con.updatePodforum(pf)) out.print(Messages.errorDataBaseConnection());
                }
                
                public void incrAddWypowiedz(Watek wt,String ID_Usera, String Nazwa_Usera) throws Exception {
                    Podforum pf = db_con.getPodforumbyWatek(wt.getID());
                    wt.zwiekszLiczbeAktywnychWypowiedzi();
                    String Nazwa_Usera2=Nazwa_Usera;
                    if ((Integer.decode(ID_Usera).intValue())==Config.GUEST_ID)
                        Nazwa_Usera2="~" + Nazwa_Usera;

                    wt.setDataOstWypowiedzi(DataBase.getDate());
                    wt.setAutorOstWypowiedzi(Nazwa_Usera2);
                    if (!db_con.updateWatek(wt)) out.print(Messages.errorDataBaseConnection());
                    pf.zwiekszLiczbeAktywnychWypowiedzi();
                    if (!db_con.updatePodforum(pf)) out.print(Messages.errorDataBaseConnection());
                }
                
            }
        
            ////
            Dodaj d = new Dodaj(out,db_con);

            
            String watek=request.getParameter("w");
            String podforum=request.getParameter("p");
            if (watek==null && podforum==null) {
                out.print(Messages.formError());
            } else {
            
                
            String ID_Usera = new String().valueOf(Config.GUEST_ID); 
            String Nazwa_Usera= Config.GUEST;
            
            ////////////////
            String text=request.getParameter("text");
            if (text!=null) {
            text = new String(text.getBytes("8859_1"),"UTF-8");
            out.print("<br/><br/>");
            text=text.replaceAll("\r\n","<br/>");
            text=text.replaceAll("\n","<br/>");
            ////////////////
            
            
            //// user ////
            if (auth.zalogowany(request,db_con)) {
                ID_Usera = String.valueOf(auth.getUser(request,db_con).getID());
                Nazwa_Usera = String.valueOf(auth.getUser(request,db_con).getLogin());
            } else {
                String autor=request.getParameter("autor");
                if (autor!=null) {
                      autor = new String(autor.getBytes("8859_1"),"UTF-8");
                      Nazwa_Usera=autor;
                }
            }
            ////////////
            
            if (watek!=null) {
                Watek wt = db_con.getWatek(Integer.decode(watek).intValue());
                //dodaj Wypowiedz
                d.dodajWypowiedz(wt,ID_Usera,Nazwa_Usera,text);
                d.incrAddWypowiedz(wt,ID_Usera,Nazwa_Usera);
                } else
                if (podforum!=null && text!=null) {
                    String title=request.getParameter("title");
                    title = new String(title.getBytes("8859_1"),"UTF-8");
                    //dodaj Watek
                    Watek wt = d.dodajWatek(podforum,ID_Usera,Nazwa_Usera,title);
                    //dodaj Wypowiedz
                    d.dodajWypowiedz(wt,ID_Usera,Nazwa_Usera,text);
                    d.incrAddWatek(wt,ID_Usera,Nazwa_Usera);
                }
                    out.print("<center><br/><br/><a href=\"./main.jsp"); 
                    if(watek!=null) out.print("?wid="+watek); else out.print("?pid="+podforum); out.print("\">" + Messages.back() + "</a></center>");
                }
                else {
        %>
                    <form method="POST" action="dodajW.jsp<%if (watek!=null) out.print("?w="+watek); else out.print("?p="+podforum); %>" onsubmit="return submitDodajW('<% out.print(Messages.wielka(Messages.errorFieldNeeded())); %>')">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                                <th colspan="2">
                                    <%out.print(Messages.wielka(Messages.add())+" "); if (watek!=null) out.print(Messages.message()); else out.print(Messages.thread()); %> 
                                </th>
                            <% if (watek==null) { %>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.title()) + ":");%></td><td><input type="text" size="50" name="title" id="title"></td>
                            <% } %>
                            </tr> <tr>
                                <td valign="top"><%out.print(Messages.wielka(Messages.tresc()) + ":");%></td><td><textarea cols="50" rows="5" name="text" id="text"></textarea></td>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.author()) + ":");%></td><td><% if(!auth.zalogowany(request,db_con)){%><input type="text" size="50" name="autor" id="autor"><%}else{out.print(auth.user(request));}%></td>
                            </tr> <tr>
                                <td colspan="2" align="right"><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td>
                            </tr>
                        </table>
                    </form>
                    <br><br>
                    <center><a href="main.jsp<% if(watek!=null) out.print("?wid="+watek); else out.print("?pid="+podforum); %>"><%out.print(Messages.back());%></a></center>
   <% } }%>
                </td>
            </tr>
        </table>
    </body>
</html>
