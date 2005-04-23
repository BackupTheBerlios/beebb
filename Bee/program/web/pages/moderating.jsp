<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Content</title>
        <link rel="stylesheet" href="./../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>

    </head>
<%@ include file="servletObjects.jsp" %>
<body>
<table width="100%" border="0" id="tableModerating">
<tr><td>

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
            String s_wpid = request.getParameter("wpid");
            String op = request.getParameter("op");
            if ( (s_wpid !=null) && (op != null) )
            {
                //DataBase db_con;
                int wpid = Integer.parseInt(s_wpid);
                Wypowiedz wyp = db_con.getWypowiedz(wpid);
                Watek wat = db_con.getWatekByWypowiedz(wpid);
                Podforum pod = db_con.getPodforumbyWatek(wat.getID());
                if ((wyp == null) || (wat == null) || (pod == null))
                {
                    out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    return;
                }
                if (op.compareTo("edit") == 0)
                {
                    //edycja wypowiedzi
                }
                if(op.compareTo("delete") == 0)
                {
                    //kasowanie wypowiedzi jesli ma sie prawo
                    if (user.moderator(pod.getID()))
                        if (db_con.zmienAktywnoscWypowiedzi(wpid,false))
                            out.println(Messages.makeError(Messages.wielka(Messages.actionDone())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                }
                if (op.compareTo("ban") == 0)
                {
                    //banowanie user'a
                }
                if (op.compareTo("move") == 0)
                {
                    //przesuwanie watku
                }
                if (op.compareTo("block") == 0)
                {
                    //blokowanie watku
                    if (user.moderator(pod.getID()))
                        if (db_con.blokowanieWatku(wat.getID(),true))
                            out.println(Messages.makeError(Messages.wielka(Messages.actionDone())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                }
                if (op.compareTo("close") == 0)
                {
                    //zamkniecie watku
                    if (user.moderator(pod.getID()))
                        if (db_con.zamykanieWatku(wat.getID(),true))
                            out.println(Messages.makeError(Messages.wielka(Messages.actionDone())));
                        else out.println(Messages.makeError(Messages.wielka(Messages.errorDataBaseConnection())));
                    else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                }
            }
            else out.println(Messages.makeError(Messages.wielka(Messages.errorUnknown())));
        }
        }
%>

</td></tr>
</table>
</body>
</html>