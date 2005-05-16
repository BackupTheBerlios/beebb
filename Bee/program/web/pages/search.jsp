<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead(request,"./..","BeeBB :: Content"));
 String css = Commons.getQueryStyle(request);
%>
<body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >    
<table id="tableSearch" border="0" width="100%">
<tr><td>

<form action="search.jsp<% out.print(css.length()>0?"?"+css:"");%>" method="post">
<table class="tableSearch" border="0" align="center">
<tr><th colspan="2"><% out.print(Messages.wielka(Messages.search()));%></th></tr>
<tr><td class="tdSearch"><% out.print(Messages.wielka(Messages.searchingWords()));%></td><td class="tdSearch"><input type="text" name="fraza" /></td></tr>
<tr><td class="tdSearch" colspan="2"><input type="checkbox" name="wypowiedzi" checked="true" /><% out.print(Messages.wielka(Messages.searchInMessages()));%></td></tr>
<tr><td colspan="2" align="center" class="tdSearch"><input type="submit" value="<% out.print(Messages.wielka(Messages.search()));%>" /></td></tr>
</table>
</form>

<%
    String fraza = request.getParameter("fraza");
    if (fraza != null)
    {
    %>
    <%@ include file="servletObjects.jsp" %>
    <%
        //TERAZ DOPIERO MOGE SZUKAC
        ArrayList watki = db_con.searchWatki(fraza,50,0);
        String czy_wyp = request.getParameter("wypowiedzi");
        boolean czy_wypowiedzi = false;
        if (czy_wyp != null) czy_wypowiedzi = czy_wyp.compareTo("on") == 0;
        //if (czy_wypowiedzi)
         //   wypowiedzi = db_con.searchWypowiedzi(fraza,50,0);
        //TODO implementacja
            /* Tego narazie nie bede implementowal bo wypadaloby scalic wyniki, ale zeby to zrobic potrzebny jest operator==
             * co najmniej dla watkow
             */
        out.println("<table id=\"tablePodforum\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\">");
        out.println("<tr>");
        out.println("<th colspan=\"2\" class=\"thTopLCorner\" height=\"30\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.themes()) + "&nbsp;</th>");
        out.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.actions()) + "&nbsp;</th>");
        out.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.answers()) + "&nbsp;</th>");
        out.println("<th width=\"80\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.author()) + "&nbsp;</th>");
        out.println("<th width=\"50\" class=\"thTop\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.showed()) + "&nbsp;</th>");
        out.println("<th class=\"thTopRCorner\" nowrap=\"nowrap\">&nbsp;" + Messages.wielka(Messages.lastPost()) + "&nbsp;</th>");
        out.println("</tr>");

        for(int i=0; i<watki.size(); i++)
        {
            Watek w = (Watek)watki.get(i);
            w.printJSPHeader(request,out);
        }
       %>
        </table>
        <%
    }
%>
</tr></td>
</table>
</body>
</html>
