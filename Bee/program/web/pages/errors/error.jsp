<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<%-- Te obiekty wczytuję ze względu na Config --%>
<%@ include file="../servletObjects.jsp" %>

<%
        String s_error = request.getParameter("code");
        int error_code = 404;
        if (s_error == null) s_error = "404";
        try{
            error_code = Integer.parseInt(s_error);
        }catch (NumberFormatException e)
        {
            error_code = 404;
        }
        String style = request.getParameter("style");
        if (style == null) style = Config.DEFAULT_STYLE;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="Content-Style-Type" content="text/css"/>
<meta name="Copyright" content="BeeBB Group &copy; 2005" />
                <meta name="Author" content="BeeBB Group" />
                <meta name="description" content="??" />
                <meta name="keywords" content="??" />
                <title>Bee :: <% out.print(error_code); %></title>
                <link href="<% out.print(Config.URL_FORUM);%>/images/bee_icon.jpg" rel="SHORTCUT ICON" />
                <link rel="stylesheet" href="<% out.print(Config.URL_FORUM);%>/styles/<% out.print(style); %>.css" type="text/css"/>
                <script type="text/javascript" src="<% out.print(Config.URL_FORUM);%>/js/skrypt.js"></script>
                </head>
<%
    String css = Commons.getQueryStyle(request);
%>
    
<%@ include file="../servletObjects.jsp" %>
    <body>
        <center><%
        String message = Messages.errorUnknown();
        switch (error_code){
            case 404: message = Messages.error404(); break;
            case 403: message = Messages.error403(); break;
            case 500: message = Messages.error500(); break;
        }
        out.println(Messages.makeError(message)); 
        %></center>
        <center><span class="aHref" style="cursor: pointer;" onclick="topLink('<% out.print(Config.URL_FORUM+"/index.jsp"+(css.length()>0?"?"+css:"")); %>')"><% out.print(Messages.wielka(Messages.back())); %></span></center>
    </body>
</html>
