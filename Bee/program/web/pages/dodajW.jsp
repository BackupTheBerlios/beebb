<%@page contentType="text/html;"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page import="java.util.*"%>
<%@ page session="false" %>

<% out.println(Commons.htmlHead(request,"./..",Messages.wielka(Messages.add())));
 String css = Commons.getQueryStyle(request);
%>
    <body onload="swapIframes();changeTargetForms();resizeMain();setResizeFunction(resizeMain);" >
        <%@ include file="servletObjects.jsp" %>
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
                
                public void dodajWypowiedz(Watek wt, String ID_Usera, String Nazwa_Usera, String text, javax.servlet.http.HttpServletRequest pytanie, Autoryzator auth) throws Exception {
                    if (wt!=null) { 
                    if ((!wt.czyZablokowany()) && (!wt.czyZamkniety()))
                        {
                        String prywatne=DataBase.NIE;
                        boolean dodaj = false;
                        if(wt.czyPrywatny()) {
                            prywatne=DataBase.TAK;
                            User user = auth.getUser(pytanie,db_con);
                            if (user != null)
                                if (user.hasWriteWatekRight(wt.getID()))
                                    dodaj = true;
                        }else dodaj = true;
                        if (dodaj){
                        Wypowiedz wp = new Wypowiedz("0",ID_Usera,Nazwa_Usera,db_con.getDateToInsert(),text,prywatne,DataBase.TAK,db_con);
                        if (!db_con.insertWypowiedz(String.valueOf(wt.getID()),wp))
                            out.print("<center>" + Messages.makeError(Messages.errorDataBaseConnection()) + "<center>");
                            else out.print("<center>" + Messages.makeSuccess(Messages.addedMessage()  + "<center>"+ "<br/>")); 
                        }
                        else out.println("<center>" + Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())) + "<center>");
                     } else out.println("<center>" + Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())) + "<center>");
                    } else out.print("<center>" + Messages.makeError(Messages.errorDataBaseConnection()) + "<center>");
                  }
                
                
                public Watek dodajWatek(String podforum,String ID_Usera,String Nazwa_Usera,String title, javax.servlet.http.HttpServletRequest pytanie, Autoryzator auth) throws Exception {
                    Podforum pf = db_con.getPodforum(Integer.decode(podforum).intValue());
                    Watek wt;
                    String prywatne=DataBase.NIE;
                    boolean dodaj = false;
                    if(pf.czyPrywatne()) {
                            prywatne=DataBase.TAK;
                            User user = auth.getUser(pytanie,db_con);
                            if (user != null)
                                if (user.hasWritePodforumRight(pf.getID()))
                                    dodaj = true;
                    }
                    else dodaj = true;
                    if (dodaj){
                    String Nazwa_Usera2=Nazwa_Usera;
                    if ((Integer.decode(ID_Usera).intValue())==Config.GUEST_ID)
                        Nazwa_Usera2="~" + Nazwa_Usera;
                    wt = new Watek("0",ID_Usera,Nazwa_Usera,title,db_con.getDateToInsert(),db_con.getDateToInsert(),Nazwa_Usera2,prywatne,DataBase.TAK,DataBase.NIE,DataBase.NIE,"0","0",db_con);
                    wt = db_con.insertWatek(podforum,wt);
                    if (wt==null) out.print("<center>" + Messages.makeError(Messages.errorDataBaseConnection()) + "<center>"); 
                            else out.print("<center>" + Messages.makeSuccess(Messages.addedThread())+ "<center>");
                    return wt;
                    }else{
                        out.println("<center>" + Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())) + "<center>");
                        return null;
                    }
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

                    if (!db_con.updateWatek(wt)) out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                    pf.zwiekszLiczbeAktywnychWatkow();
                    pf.zwiekszLiczbeAktywnychWypowiedzi();
                    if (!db_con.updatePodforum(pf)) out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                }
                
                public void incrAddWypowiedz(Watek wt,String ID_Usera, String Nazwa_Usera) throws Exception {
                    Podforum pf = db_con.getPodforumbyWatek(wt.getID());
                    wt.zwiekszLiczbeAktywnychWypowiedzi();
                    String Nazwa_Usera2=Nazwa_Usera;
                    if ((Integer.decode(ID_Usera).intValue())==Config.GUEST_ID)
                        Nazwa_Usera2="~" + Nazwa_Usera;

                    wt.setDataOstWypowiedzi(DataBase.getDate());
                    wt.setAutorOstWypowiedzi(Nazwa_Usera2);
                    pf.setDataOstWypowiedzi(DataBase.getDate());
                    pf.setAutorOstWypowiedzi(Nazwa_Usera2);
                    if (!db_con.updateWatek(wt)) out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                    pf.zwiekszLiczbeAktywnychWypowiedzi();
                    if (!db_con.updatePodforum(pf)) out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                }
                
                public boolean canCreate(String podforum, String watek, javax.servlet.http.HttpServletRequest pytanie, Autoryzator auth){
                    if (podforum != null)
                    {
                        Podforum p = db_con.getPodforum(Integer.decode(podforum).intValue());
                        if (p.czyPrywatne()){
                            User user = auth.getUser(pytanie,db_con);
                            if (user != null)
                                if (user.hasWritePodforumRight(p.getID()))
                                    return true;
                                else return false;
                            else return false;
                        }
                        else return true;
                    }
                    if (watek != null)
                    {
                       Watek wt = null;
                        wt = db_con.getWatek(Integer.decode(watek).intValue());
                        if (wt.czyZablokowany() || wt.czyZamkniety()) return false;
                        if(wt.czyPrywatny()) {
                            User user = auth.getUser(pytanie,db_con);
                            if (user != null)
                                if (user.hasWriteWatekRight(wt.getID()))
                                    return true;
                                else return false;
                            else return false;
                        }else return true;
                    }
                    return true;
                }
                
            }
        
            ////
            Dodaj d = new Dodaj(out,db_con);

            
            String watek=request.getParameter("w");
            String podforum=request.getParameter("p");
            if (watek==null && podforum==null) {
                out.print("<br/><center>" + Messages.makeError(Messages.formError()) + "</center>");
            } else { /// watek || podforum!=null
            ////////////////
            String text=request.getParameter("text");
            if (text!=null) { // znaczy że dodaje coś
                text = new String(text.getBytes("8859_1"),"UTF-8");
                out.print("<br/><br/>");
                text = Commons.wypowiedzDoBazy(text);
                ////////////////

                //// user ////
                String ID_Usera = new String().valueOf(Config.GUEST_ID); 
                String Nazwa_Usera= Config.GUEST;
                if (auth.zalogowany(request,db_con)) {
                    ID_Usera = String.valueOf(auth.getUser(request,db_con).getID());
                    Nazwa_Usera = String.valueOf(auth.getUser(request,db_con).getLogin());
                } else {
                    String autor=request.getParameter("autor");
                    if (autor!=null) {
                      autor = new String(autor.getBytes("8859_1"),"UTF-8");
                      autor = Commons.wypowiedzDoBazy(autor);
                      Nazwa_Usera=autor;
                    }
                }
                //////////// tutaj dodaje wypowiedź (i watek)
                if (podforum!=null) {
                        String title=request.getParameter("title");
                        title = new String(title.getBytes("8859_1"),"UTF-8");
                        title = Commons.wypowiedzDoBazy(title);
                        //dodaj Watek
                        Watek wat = d.dodajWatek(podforum,ID_Usera,Nazwa_Usera,title,request,auth);
                        //dodaj Wypowiedz
                        if (wat != null){
                            d.dodajWypowiedz(wat,ID_Usera,Nazwa_Usera,text,request,auth);
                            d.incrAddWatek(wat,ID_Usera,Nazwa_Usera);
                        }
                    } else { //znaczy że dodaje wypowiedz
                        Watek wt = db_con.getWatek(Integer.decode(watek).intValue());
                        //dodaj Wypowiedz
                        d.dodajWypowiedz(wt,ID_Usera,Nazwa_Usera,text,request,auth);
                        d.incrAddWypowiedz(wt,ID_Usera,Nazwa_Usera);
                    }
                    out.print("<center><br/><br/>"+Commons.aHref(request,Messages.wielka(Messages.back()),"./main.jsp"+ ((watek!=null)?("?wid="+watek):("?pid="+podforum)))+"</center>");
                } // tekst!=null ; koniec dodawania
                else 
                {
                String ID_Usera = new String().valueOf(Config.GUEST_ID); 
                if (auth.zalogowany(request,db_con))
                    ID_Usera = String.valueOf(auth.getUser(request,db_con).getID());
                int podf_id = -1;
                if (podforum==null) podf_id=db_con.getPodforumbyWatek(Integer.decode(watek).intValue()).getID(); else podf_id = Integer.decode(podforum).intValue();
                if (d.canCreate(podforum, watek,request,auth) && !db_con.isUserBanned(Integer.decode(ID_Usera).intValue(),podf_id)) {
        %>
                    <table align="center">
                    <tr>
                                <th colspan="2" align="center">
                                    <%out.print(Messages.wielka(Messages.add())+" "); if (watek!=null) out.print(Messages.message()); else out.print(Messages.thread()); %> 
                                </th>
                    </tr>
                    <tr>
                            <td class="tdImgEmotikona">
                                <%
                                    int k = 0;
                                    Enumeration i = Config.SMILES.keys();
                                    while (i.hasMoreElements()){
                                        Object o_tag = i.nextElement();
                                        String tag = (String)o_tag;
                                        String url = (String)Config.SMILES.get(o_tag);
                                        out.println(Commons.makeEmotikonLink(url,tag,"style=\"cursor:pointer;\" onclick=\"addEmoticon('"+Config.SMILE_TAG_OPEN+tag+Config.SMILE_TAG_CLOSE+"','text')\""));
                                        k++;
                                        if (k == 5) 
                                        {
                                            k=0;
                                            out.println("<br/>");
                                        }
                                    }
                                %>
                            </td>
                        <td class="tdDodawanie">
                    <form method="post" action="dodajW.jsp<%if (watek!=null) out.print("?w="+watek); else out.print("?p="+podforum); out.print(css.length()>0?"&amp;"+css:"");%>" onsubmit="return submitDodajW('<% out.print(Messages.wielka(Messages.errorFieldNeeded())); %>')">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <% if (watek==null) { %>
                            <tr>
                            <td><%out.print(Messages.wielka(Messages.title()) + ":");%></td></tr>
                            <tr><td><input type="text" size="50" style="width:450px" name="title" id="title"/></td>
                            <% } %>
                            </tr> <tr>
                                <td valign="top"><%out.print(Messages.wielka(Messages.tresc()) + ":");%></td></tr>
                                <tr><td><textarea cols="50" style="width:450px" rows="20" name="text" id="text"></textarea></td>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.author()) + ":");%></td></tr>
                            <tr><td><% if(!auth.zalogowany(request,db_con)){%><input type="text" size="50" style="width:450px" name="autor" id="autor"/><%}else{out.print(auth.user(request));}%></td>
                            </tr> <tr>
                                <td align="right"><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td>
                            </tr>
                        </table>
                    </form>
                    </td>
                    </tr>
                    </table>
                    <br/><br/>
                    <center><% Commons.aHref(request,Messages.wielka(Messages.back()),"main.jsp"+ ((watek!=null)?("?wid="+watek):("?pid="+podforum)));%></center>
   <% }
                else {//to znaczy, ze canCreate zwrocilo FALSE
                    out.println("<center>"+Messages.makeError(Messages.wielka(Messages.errorPermissionDenied()))+ "</center>");
                    out.println("<center>"+Commons.aHref(request,Messages.wielka(Messages.back()),"main.jsp"+ ((watek!=null)?("?wid="+watek):("?pid="+podforum)))+"</center>");
                } }
    } /// watek && podforum!=null%>
    </body>
</html>
