<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="pl.ltd.bee.*"%>
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
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
    </head>
    <body>
        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
        <br><br>
        <table align="center" border="0">
            <tr>
                <td> 
        <% Enumeration flds = request.getParameterNames();
        if (!db_con.isConnected()) {
        try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
        } catch (Exception e) {
            out.print(Messages.errorDataBaseConnection());
        } }
            boolean ok = true;
            String nickname=request.getParameter("user");
            if (nickname!=null) {
                if (db_con.getUser(nickname)!=null) ok=false;
                if (nickname.compareTo("")==0) ok=false;
            } else { ok=false; }
            String haslo1=request.getParameter("haslo1");
            String haslo2=request.getParameter("haslo2");
            if (haslo1!=null && haslo2!=null) {
                if (haslo1.length() < 5)ok=false;
                if (haslo1.compareTo(haslo2)!=0) ok=false;
            } else ok=false;
            String email=request.getParameter("email");
            if (email==null) ok=false;
            else
                if (email.compareTo("")==0) ok=false;
                
            String nazwisko = request.getParameter("nazwisko");
            if (nazwisko==null) nazwisko="";
            String imie = request.getParameter("imie");
            if (imie==null) imie="";
            String gg = request.getParameter("gg");
            if (gg==null) gg="";
            String jabber = request.getParameter("jabber");
            if (jabber==null) jabber="";
            
            
            
            if (ok) {
                if(!db_con.insertUser(nickname,haslo1,imie,nazwisko,email,gg,jabber,"1970-01-01 00:00:00"))
                out.println(Messages.errorUserCreate());
                else {
                    if (Config.NEW_USER_MAIL_AUTH) {
                       Random r = new Random();
                       String numer = Long.toHexString(r.nextLong());
                        while (!db_con.sprawdzKluczNewUser(numer))        
                            numer = Long.toHexString(r.nextLong());
                        if(!db_con.wstawNewUser(nickname,numer))
                            out.print(Messages.errorDataBaseConnection());
                        else {
                            out.println("Email z linkiem aktywacyjnym został wysłany.<BR><br><a href=../index.jsp>powrot</a><br>"); 
                            SendMail.send(email,Config.REG_MAIL_SUBJECT,"Witaj "+ nickname + "\n" + Config.REG_MAIL_BODY + Config.URL_FORUM + "/pages/reg/newUser?id=" + numer);
                        }
                    } else {
                        if(!db_con.setAktywnyUser(nickname))
                            out.println(Messages.errorUserCreate());
                        out.println("Uzytkownik: " + nickname + " zostal dodany<BR><br><a href=../index.jsp>powrot</a><br>"); 
                    }
                }
            } else {
        %>
                    <form method="POST" action="addUser.jsp">
                        <table align="center" cellpadding="2" cellspacing="1" border="0">
                            <tr>
                            <th colspan="2">
                                Dodaj użytkownika 
                            </th>
                            </tr> <tr>
            <%
            ok = true;
            if (nickname!=null) {
                if (db_con.getUser(nickname)!=null) {
                    out.println("<td colspan=\"2\">"+ Messages.errorUserExists(nickname) +"</td></tr><tr>");
                    ok=false;
                }
                if (nickname.compareTo("")==0) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.errorFieldNeeded() + "</td></tr><tr>"); }
            } else { ok=false; nickname=""; } %>    
                                <td><b>Nick*:</b></td><td><input type="text" size="25" name="user" value="<%out.print(nickname);%>"></td>
                            </tr> <tr>
            <% 
            if (haslo1!=null && haslo2!=null) {
                if (haslo1.length() < Config.MIN_PASSWD) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.errorPassToShort(Config.MIN_PASSWD) + "</td></tr><tr>"); }
                if (haslo1.compareTo(haslo2)!=0) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.errorPassNotMatch() + "</td></tr><tr>"); }
            } else { ok=false; } %>
                            <td><b>Haslo*:</b></td><td><input type="password" size="25" name="haslo1"></td>
                            </tr> <tr>
                                <td><b>Haslo (jeszcze raz)*:</b></td><td><input type="password" size="25" name="haslo2"></td>
                            </tr> <tr>
                            <td>Imie:</td><td><input type="text" size="25" name="imie" value="<%out.print(imie);%>"></td>
                            </tr> <tr>
                                <td>Nazwisko:</td><td><input type="text" size="25" name="nazwisko" value="<%out.print(nazwisko);%>"></td>
                            </tr> <tr>
            <%
            if (email==null) {
                ok=false; email="";
            } else {
                if (email.compareTo("")==0) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.errorFieldNeeded() + "</td></tr><tr>"); }
            }%>
                            <td><b>E-mail*:</b></td><td><input type="text" size="25" name="email" value="<%out.print(email);%>"></td>
                            </tr> <tr>
                                <td>Gadu-Gadu:</td><td><input type="text" size="25" name="gg" value="<%out.print(gg);%>"></td>
                            </tr> <tr>
                            <td>Jabber:</td><td><input type="text" size="25" name="jabber" value="<%out.print(jabber);%>"></td>
                            </tr> <tr>
                                <td colspan="2" align="right"><input type="submit" name="submit" value="Wyślij"/></td>
                            </tr>
                        </table>
                        * - pola wymagane
                    </form>
        <% }%>
                </td>
            </tr>
        </table>
    </body>
</html>
