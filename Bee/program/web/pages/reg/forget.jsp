<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Nowy użytkownik</title>
        <link rel="stylesheet" href="../../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="./../../js/iframe_resize.js"></script>
    </head>
    <body onload="resizeForget()" onresize="resizeForget()">
<%
       DataBase db_con;
       Object o = application.getAttribute(Config.APPLICATION_OBJECT_DATABASE);
       if (o == null)
       {
           DataBase d = new DataBase();
           application.setAttribute(Config.APPLICATION_OBJECT_DATABASE,d);
           db_con = d;
       }
       else db_con = (DataBase)o;
       
%>
        <table align="center" border="0" id="tableForget"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr>
                <td> 
        <br/><br/>
        <% Enumeration flds = request.getParameterNames();
        if (!db_con.isConnected()) {
        try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
        } catch (Exception e) {
            out.print(Messages.errorDataBaseConnection());
        } }
        String klucz=request.getParameter("id");
        if (klucz!=null) {
            String email = db_con.getEmailForgetPasswd(klucz);
            if (email!=null)
            {
              %>
                    <form method="post" action="forget.jsp">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                            <th colspan="2"><%out.println(Messages.newPassword());%>:</th>
                            </tr> <tr>
                                <td><%out.println(Messages.wielka(Messages.password()));%>:</td><td><input type="password" size="20" name="haslo1"/></td>
                            </tr> <tr>
                            <td><%out.println(Messages.wielka(Messages.password()) + " (" + Messages.oneMoreTime()+ ")");%>:</td><td><input type="password" size="20" name="haslo2"/></td>
                            </tr> <tr>        
                                <td align="right" colspan="2"><input type="hidden" size="20" name="email" value="<%out.print(email);%>"/><input type="submit" name="submit" value="<%out.println(Messages.wielka(Messages.ok()));%>"/></td>
                            </tr>
                        </table>
                    </form>
            <%            
            } 
        } else 
        {
            String haslo=request.getParameter("haslo1");
            String email=request.getParameter("email");
            if (haslo!=null && email!=null) {
                if(db_con.zmienHasloByEmail(email,haslo)) {
                    db_con.usunZapomnianeHaslo(email);
                    out.print(Messages.passwordChanged() + "<br>"); }
                else
                    out.print(Messages.errorChangePasswd());
            }
            out.println("<a href=./../main.jsp>" + Messages.back() + "</a><br>"); 
        }
        %>
                </td>
            </tr>
        </table>
    </body>
</html>
