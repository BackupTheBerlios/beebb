<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>


<% out.println(Commons.htmlHead("./..","BeeBB :: Content"));%>
<%@ include file="servletObjects.jsp" %>
<body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >

<%
        User user = auth.getUser(request,db_con);
        if (user == null)
        {
            out.println(Messages.makeError(Messages.wielka(Messages.errorNotLoggedIn())));
        }
        else
        {        
        Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) 
            out.println(Messages.makeError(Messages.wielka(Messages.errorUnknown())));
        else
        {
            String s_wid = request.getParameter("wid");
            String s_wpid = request.getParameter("wpid");
            String op = request.getParameter("op");
            if ( ((s_wpid !=null) || (s_wid != null)) && (op != null) )
            {
                //DataBase db_con;
                Wypowiedz wyp = null;
                Watek wat = null;
                int wpid = 0;
                
                if (s_wid != null)
                {
                    wat = db_con.getWatek(Integer.parseInt(s_wid));
                }
                else
                {
                    wpid = Integer.parseInt(s_wpid);
                    wyp = db_con.getWypowiedz(wpid);
                    wat = db_con.getWatekByWypowiedz(wpid);
                }
                Podforum pod = db_con.getPodforumbyWatek(wat.getID());
                if (((wyp == null) && (s_wpid != null))|| (wat == null) || (pod == null))
                {
                    out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    return;//TODO Jesli to dziala to na pewno nie wygeneruje poprawnego xhtml :D
                }
                if ((op.compareTo("edit") == 0) && (s_wpid != null))
                {
                    //edycja wypowiedzi
                    if ((user.moderator(pod.getID())) || (user.getID() == wyp.getIDAutora()) || (user.admin()))
                    {
                    %>
                        <form action="moderating.jsp" method="get">
                            <table border="0" class="tableEditWypowiedz" align="center">
                            <tr><th colspan="2"><%out.print(Messages.wielka(Messages.edition()));%></th></tr>
                            <tr>
                            <td class="tdImgEmotikona">
                                <%
                                    int k = 0;
                                    Enumeration i = Config.SMILES.keys();
                                    while (i.hasMoreElements()){
                                        Object o_tag = i.nextElement();
                                        String tag = (String)o_tag;
                                        String url = (String)Config.SMILES.get(o_tag);
                                        out.println(Commons.makeEmotikonLink(url,tag,"style=\"cursor:pointer;\" onclick=\"addEmoticon('"+Config.SMILE_TAG_OPEN+tag+Config.SMILE_TAG_CLOSE+"','newText')\""));
                                        k++;
                                        if (k == 5) 
                                        {
                                            k=0;
                                            out.println("<br/>");
                                        }
                                    }
                                %>
                            </td>
                            <td class="tdEditWypowiedz" align="center">
                            <textarea cols="60" rows="8" name="newText" id="newText"><%out.print(Commons.wypowiedzDoTekst(wyp.getTekst()));%></textarea>
                            <input type="hidden" name="wpid" value="<%out.print(s_wpid);%>" />
                            <input type="hidden" name="op" value="edited" />
                            </td></tr>
                            <tr><td colspan="2" align="center" class="tdEditWypowiedz"><input type="submit" value="<% out.print(Messages.wielka(Messages.save()));%>"</td></tr>
                            </table>
                        </form>
                    <%
                    }
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                }
                if ((op.compareTo("edited") == 0) && (s_wpid != null))
                {
                    String text = request.getParameter("newText");
                    if (text != null)
                    if ((user.moderator(pod.getID())) || (user.getID() == wyp.getIDAutora()) || (user.admin()))
                    {
                        text = new String(text.getBytes("8859_1"),"UTF-8");
                        if (db_con.zmienTekstWypowiedzi(wyp,wat.getID(),Commons.wypowiedzDoBazy(text)))
                            out.println(Messages.makeSuccess(Messages.wielka(Messages.actionDone())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    }
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorUnknown())));
                }
                if((op.compareTo("delete") == 0) && (s_wpid != null))
                {
                    //kasowanie wypowiedzi jesli ma sie prawo
                    if (user.moderator(pod.getID()) || (user.admin()))
                        if (db_con.zmienAktywnoscWypowiedzi(wpid,false))
                            out.println(Messages.makeSuccess(Messages.wielka(Messages.actionDone())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                }
                if (op.compareTo("ban") == 0)
                {
                    //banowanie user'a
                    String s_autor = request.getParameter("id_autor");
                    if (s_autor == null) out.println(Messages.makeError(Messages.wielka(Messages.errorUnknown())));
                    else
                    {
                        int id_autor = Integer.parseInt(s_autor);
                        if (user.moderator(pod.getID()) || (user.admin()))
                            if (db_con.banUser(id_autor,pod.getID(),true))
                                out.println(Messages.makeSuccess(Messages.wielka(Messages.actionDone())));
                            else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                    }
                }
                if (op.compareTo("move") == 0)
                {
                    //przesuwanie watku
                    if (user.moderator(pod.getID()) || (user.admin()))
                    {
                    %>
                        <form action="moderating.jsp" method="get">
                            <table border="0" class="tableMovePodforum" align="center">
                            <tr><th><%out.print(Messages.wielka(Messages.moveThread())+" ('"+ wat.getTemat() +"')");%></th></tr>
                            <tr><td class="tdMoveTo" align="center">
                            <select name="moveto">
                            <%
                                ArrayList podfora = db_con.getModerowanePodfora(user.getID());
                                for(int i=0;i<podfora.size();i++)
                                {
                                    Podforum p = (Podforum)podfora.get(i);
                                    out.println("<option value=\""+p.getID()+"\">" + p.getTytul()+" ("+ p.getOpis() +")</option>");
                                }
                            %>
                            </select>
                            <input type="hidden" name="<% if (s_wpid != null) out.print("wpid\" value=\""+s_wpid+"\"");else out.print("wid\" value=\""+wat.getID()+"\"");%> />
                            <input type="hidden" name="op" value="moveto" />
                            </td></tr>
                            <tr><td align="center" class="tdMoveTo"><input type="submit" value="<% out.print(Messages.wielka(Messages.move()));%>"</td></tr>
                            </table>
                        </form>
                    <%
                    }
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                }
                if (op.compareTo("moveto") == 0)
                {
                    //przesuwanie watku na dobre
                    String s_moveto = request.getParameter("moveto");
                    if (s_moveto == null) out.println(Messages.makeError(Messages.wielka(Messages.errorUnknown())));
                    else
                    {
                        int moveto = Integer.parseInt(s_moveto);
                        if (user.moderator(pod.getID()) || (user.admin()))
                            if (db_con.moveWatek(wat,pod.getID(),moveto))
                                    out.println(Messages.makeSuccess(Messages.wielka(Messages.actionDone())));
                                else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                    }
                }                
                if (op.compareTo("block") == 0)
                {
                    //blokowanie watku
                    if (user.moderator(pod.getID()) || (user.admin()))
                        if (db_con.blokowanieWatku(wat.getID(),true))
                            out.println(Messages.makeSuccess(Messages.wielka(Messages.actionDone())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                }
                if (op.compareTo("close") == 0)
                {
                    //zamkniecie watku
                    if (user.moderator(pod.getID()) || (user.admin()))
                        if (db_con.zamykanieWatku(wat.getID(),true))
                            out.println(Messages.makeSuccess(Messages.wielka(Messages.actionDone())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                }
                
             out.print("<br/>"+Commons.aHref(Messages.wielka(Messages.back()), "./main.jsp?"+ ((s_wpid != null)?("wid="+wat.getID()):("pid="+pod.getID()))));
            }
            else out.println(Messages.makeError(Messages.wielka(Messages.errorUnknown())));
        }
        }
%>

</body>
</html>