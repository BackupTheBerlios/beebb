<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>


<%@ include file="./pages/servletObjects.jsp" %>
<% 
String css = request.getParameter("style");
if (css == null){
    User user = auth.getUser(request,db_con);
    if (user != null){
        if (user.getStyle().length() > 0)
            css = user.getStyle();
        else css = Config.DEFAULT_STYLE;
    }
    else
    css = Config.DEFAULT_STYLE;
}
out.println(Commons.htmlHead(request, ".","BeeBB :: Content",css));

String header = request.getParameter("header");
String content = request.getParameter("content");
%>
    <body>
        <table width="100%" border="0"> 
            <tr>
                <td id="cellHead"> 
                    <iframe id="frameHead" name="frameHead" width="100%" src="<%
                    if (header != null) out.print(header);// zabezpieczyc przed zrobieniem index.jsp?header=http://www.onet.pl Mozna dac Config.FORUM_URL
                    else {
                            out.print("./pages/header.jsp");
                            if (css.length() > 0) out.print("?style="+css);
                    }
                    %>" scrolling="no" frameborder="0"></iframe>
                </td>
            </tr>
            <tr>
                <td id="cellTresc">
                    <iframe id="frameTresc_1" name="frameTresc_1" width="100%" height="5000" src="<%
                        if (content != null) out.print(content);
                        else{
                                out.print("./pages/main.jsp");
                                if (css.length() > 0) out.print("?style="+css);
                        }
                        %>" scrolling="no" frameborder="0" style=" display : none;" ></iframe>
                    <iframe id="frameTresc_2" name="frameTresc_2" width="100%" height="5000" src="" scrolling="no" frameborder="0" style=" display : block;" ></iframe>
                </td>
            </tr>
        </table>    
    </body>
</html>
