<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="pl.ltd.bee.*"%>
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
        <title>BeeBB :: <%out.print(Messages.newUser());%></title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>
        <script type="text/javascript" src="./../js/forms.js"></script>
    </head>
    <body onload="resizeAddUser()" onresize="resizeAddUser()">
<%@ include file="servletObjects.jsp" %>
    
        <table id="tableAddUser" align="center" border="0"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr>
                <td> 
        <% Enumeration flds = request.getParameterNames();
            boolean ok = true;
            String nickname=request.getParameter("user");
            if (nickname!=null) {
                if (db_con.getUser(nickname)!=null) ok=false;
                if (nickname.compareTo("")==0) ok=false;
            } else { ok=false; }
            String passwd1=request.getParameter("passwd1");
            String passwd2=request.getParameter("passwd2");
            if (passwd1==null || passwd2==null || (passwd1.compareTo(passwd2)!=0)) ok=false;
            String email=request.getParameter("email");
            if (email==null) ok=false;
            else
                if (email.compareTo("")==0 || !db_con.sprawdzEmail(email)) ok=false;
                
            String nazwisko = request.getParameter("nazwisko");
            if (nazwisko==null) nazwisko="";
            String imie = request.getParameter("imie");
            if (imie==null) imie="";
            String gg = request.getParameter("gg");
            if (gg==null) gg="";
            String jabber = request.getParameter("jabber");
            if (jabber==null) jabber="";
            
            
            
            if (ok) {
                User u = new User(0,nickname,Crypto.crypt(passwd1),imie,nazwisko,DataBase.NIE,email,DataBase.NIE,gg,DataBase.NIE,jabber,DataBase.NIE,DataBase.getDate("1970","01","01","00","00","00"),DataBase.getDate("1970","01","01","00","00","00"),DataBase.NIE,DataBase.NIE,DataBase.NIE);
                if(!db_con.insertUser(u))
                out.println(Messages.makeError(Messages.errorUserCreate()));
                else {
                    if (Config.NEW_USER_MAIL_AUTH) {
                       Random r = new Random();
                       String numer = Long.toHexString(r.nextLong());
                        while (!db_con.sprawdzKluczNewUser(numer))        
                            numer = Long.toHexString(r.nextLong());
                        if(!db_con.insertNewUser(nickname,numer))
                            out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                        else {
                            out.println(Messages.makeInfo(Messages.messageActivEmailSend())+"<BR><br><a href=./main.jsp>" + Messages.back() + "</a><br>"); 
                            SendMail.send(email,Config.REG_MAIL_SUBJECT,Messages.welcome()+" "+nickname + "\n" + Config.REG_MAIL_BODY + Config.URL_FORUM + "/pages/reg/newUser.jsp?id=" + numer);
                        }
                    } else {
                        if(!db_con.setAktywnyUser(nickname))
                            out.println(Messages.makeError(Messages.errorUserCreate()));
                        else
                            out.println(Messages.user() +": " + nickname + " " + Messages.hasBeenAdded()+"<BR><br><a href=./main.jsp>" +Messages.back()+"</a><br>"); 
                    }
                }
            } else {
        %>
                    <form method="POST" action="addUser.jsp" onsubmit="return submitAddUser(this,<% out.print(Config.MIN_PASSWD + ",'" + Messages.wielka(Messages.errorFieldNeeded())+"','"+Messages.wielka(Messages.errorPassNotMatch()) + "','" + Messages.wielka(Messages.errorPassToShort(Config.MIN_PASSWD))); %>')">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                            <th colspan="2">
                                <%out.print(Messages.wielka(Messages.addUser()));%>
                            </th>
                            </tr> <tr>
            <%
            ok = true;
            if (nickname!=null) {
                if (db_con.getUser(nickname)!=null) {
                    out.println("<td colspan=\"2\">"+ Messages.makeError(Messages.errorUserExists(nickname)) +"</td></tr><tr>");
                    ok=false;
                }
                if (nickname.compareTo("")==0) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.makeError(Messages.errorFieldNeeded()) + "</td></tr><tr>"); }
            } else { ok=false; nickname=""; } %>    
                                <td><b><%out.print(Messages.wielka(Messages.nick()));%>*:</b></td><td><input type="text" size="25" name="user" value="<%out.print(nickname);%>" id="user"></td>
                            </tr> <tr>
        <%if(passwd1!=null && passwd2!=null) {
            if (passwd1.compareTo(passwd2)!=0) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.makeError(Messages.passwordNotMatch()) + "</td></tr><tr>"); }
            if (passwd1.length() < Config.MIN_PASSWD) { ok=false;
                out.println("<td colspan=\"2\">" + Messages.makeError(Messages.passwordTooShort()) + "</td></tr><tr>"); }
        }
        %>
                            <td><b><%out.print(Messages.wielka(Messages.password()));%>*:</b></td><td><input type="password" size="25" name="passwd1" id="passwd1"></td>
                            </tr> <tr>
                                <td><b><%out.print(Messages.wielka(Messages.password()));%> (<%out.print(Messages.oneMoreTime());%>)*:</b></td><td><input type="password" size="25" name="passwd2" id="passwd2"></td>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.name()));%>:</td><td><input type="text" size="25" name="imie" value="<%out.print(imie);%>"></td>
                            </tr> <tr>
                                <td><%out.print(Messages.wielka(Messages.surename()));%>:</td><td><input type="text" size="25" name="nazwisko" value="<%out.print(nazwisko);%>"></td>
                            </tr> <tr>
            <%
            if (email==null) {
                ok=false; email="";
            } else {
                if (email.compareTo("")==0) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.makeError(Messages.errorFieldNeeded()) + "</td></tr><tr>"); }
                 if (!db_con.sprawdzEmail(email)) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.makeError(Messages.errorEmailExists()) + "</td></tr><tr>"); }
            }%>
                            <td><b><%out.print(Messages.wielka(Messages.email()));%>*:</b></td><td><input type="text" size="25" name="email" value="<%out.print(email);%>" id="email"></td>
                            </tr> <tr>
                                <td><%out.print(Messages.wielka(Messages.gg()));%>:</td><td><input type="text" size="25" name="gg" value="<%out.print(gg);%>"></td>
                            </tr> <tr>
                            <td><%out.print(Messages.wielka(Messages.jabber()));%>:</td><td><input type="text" size="25" name="jabber" value="<%out.print(jabber);%>"></td>
                            </tr> <tr>
                                <td colspan="2" align="right"><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td>
                            </tr>
                        </table>
                        * - <%out.print(Messages.fieldsObligatory());%>
                    </form>
        <% }%>
                </td>
            </tr>
        </table>
    </body>
</html>
