<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<% out.println(Commons.htmlHead(request,"./../..",Messages.wielka(Messages.newUser())));%>
    <body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >
<%@ include file="../servletObjects.jsp" %>    
        <br/><br/>
        <% Enumeration flds = request.getParameterNames();
        String klucz=request.getParameter("id");
        if (klucz!=null) {
            String nick = db_con.getLoginNewUser(klucz);
            if (nick!=null)
            {
                 if(!db_con.setAktywnyUser(nick))
                    out.println(Messages.errorUserCreate()); 
                 else {
                    db_con.usunKluczNewUser(klucz);
                    out.println(Messages.wielka(Messages.user()) + " " + nick + " " + Messages.hasBeenAdded() + "<br/><br/><span style=\"cursor: pointer;\" onclick=\"hrefClick('../../index.jsp')\">" + Messages.wielka(Messages.back()) + "</span><br/>"); 
                 }
            } else 
                out.print(Messages.errorKeyNewUser());
            
        } else out.println("<a class=\"aHref\" href=\"../../index.jsp\">"+Messages.wielka(Messages.back())+"</a><br/>"); 
        %>
    </body>
</html>
