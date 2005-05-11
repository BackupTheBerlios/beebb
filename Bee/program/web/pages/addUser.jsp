<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>


<% out.println(Commons.htmlHead("./..",Messages.wielka(Messages.newUser())));%>
    <body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);">
        <%@ include file="servletObjects.jsp" %>
    
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
            String www = request.getParameter("www");
            if (www==null) www="";
            String gg = request.getParameter("gg");
            if (gg==null) gg="";
            String jabber = request.getParameter("jabber");
            if (jabber==null) jabber="";
            String tlen = request.getParameter("tlen");
            if (tlen==null) tlen="";
            String wpKontakt = request.getParameter("wpKontakt");
            if (wpKontakt==null) wpKontakt="";
            String icq = request.getParameter("icq");
            if (icq==null) icq="";
            String msn = request.getParameter("msn");
            if (msn==null) msn="";
            String yahoo = request.getParameter("yahoo");
            if (yahoo==null) yahoo="";
            String skype = request.getParameter("skype");
            if (skype==null) skype="";
            String miasto = request.getParameter("miasto");
            if (miasto==null) miasto="";
            String plec = request.getParameter("plec");
            if (plec==null) plec="M";
            String rokUrodzenia = request.getParameter("rokUrodzenia");
            if (rokUrodzenia==null) rokUrodzenia="1900";
            String miesiacUrodzenia = request.getParameter("miesiacUrodzenia");
            if (miesiacUrodzenia==null) miesiacUrodzenia="01";
            String dzienUrodzenia = request.getParameter("dzienUrodzenia");
            if (dzienUrodzenia==null) dzienUrodzenia="01";
            
            nazwisko  = new String(nazwisko.getBytes("8859_1"),"UTF-8");
            imie      = new String(imie.getBytes("8859_1"),"UTF-8");
            gg        = new String(gg.getBytes("8859_1"),"UTF-8");
            jabber    = new String(jabber.getBytes("8859_1"),"UTF-8");
            tlen      = new String(tlen.getBytes("8859_1"),"UTF-8");
            wpKontakt = new String(wpKontakt.getBytes("8859_1"),"UTF-8");
            icq       = new String(icq.getBytes("8859_1"),"UTF-8");
            msn       = new String(msn.getBytes("8859_1"),"UTF-8");
            miasto    = new String(miasto.getBytes("8859_1"),"UTF-8");
            rokUrodzenia = new String(rokUrodzenia.getBytes("8859_1"),"UTF-8");
            miesiacUrodzenia = new String(miesiacUrodzenia.getBytes("8859_1"),"UTF-8");
            dzienUrodzenia = new String(dzienUrodzenia.getBytes("8859_1"),"UTF-8");
            String dataUrodzenia = rokUrodzenia + "-" + miesiacUrodzenia + "-" + dzienUrodzenia;

                if (ok) {
                String aktywny = DataBase.NIE;
                if (!Config.NEW_USER_MAIL_AUTH) { aktywny = DataBase.TAK; } 
                
                User u = new User(0,nickname,Crypto.crypt(passwd1),imie,nazwisko,DataBase.NIE,email,DataBase.NIE,www,gg,DataBase.NIE,
                        jabber,DataBase.NIE,tlen,DataBase.NIE,wpKontakt,DataBase.NIE,icq,DataBase.NIE,msn,DataBase.NIE,yahoo,
                        DataBase.NIE,skype,DataBase.NIE,miasto,DataBase.NIE,plec,"","",DataBase.NIE,"","",DataBase.NIE,dataUrodzenia,DataBase.NIE,"0","0",
                        DataBase.getDate("1970","01","01","00","00","00"),DataBase.getDate("1970","01","01","00","00","00"),
                        aktywny,DataBase.NIE,DataBase.NIE,db_con);
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
                            out.println(Messages.makeInfo(Messages.messageActivEmailSend())+"<br/><br/>"+Commons.aHref(Messages.wielka(Messages.back()), "./main.jsp")+"<br/>"); 
                            SendMail.send(email,Config.REG_MAIL_SUBJECT,Messages.welcome()+" "+nickname + "\n" + Config.REG_MAIL_BODY + Config.URL_FORUM + "/pages/reg/newUser.jsp?id=" + numer);
                        }
                    } else {
                        out.println("<center>" + Messages.makeInfo(Messages.wielka(Messages.user()) + " " + nickname + " " + Messages.hasBeenAdded())+"<br/><br/>"+Commons.aHref(Messages.wielka(Messages.back()), "./main.jsp")+"<br/></center>"); 
                    }
                }
            } else {
        %>
        <form method="post" action="addUser.jsp" onsubmit="return submitAddUser(this,<% out.print(Config.MIN_PASSWD + ",'" + Messages.wielka(Messages.errorFieldNeeded())+"','"+Messages.wielka(Messages.errorPassNotMatch()) + "','" + Messages.wielka(Messages.errorPassToShort(Config.MIN_PASSWD))); %>')">
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
                    <td><b><%out.print(Messages.wielka(Messages.nick()));%>*:</b></td><td><input type="text" size="25" name="user" value="<%out.print(nickname);%>" id="user"/></td>
                </tr> <tr>
        <%if(passwd1!=null && passwd2!=null) {
            if (passwd1.compareTo(passwd2)!=0) { ok=false;
                    out.println("<td colspan=\"2\">" + Messages.makeError(Messages.passwordNotMatch()) + "</td></tr><tr>"); }
            if (passwd1.length() < Config.MIN_PASSWD) { ok=false;
                out.println("<td colspan=\"2\">" + Messages.makeError(Messages.passwordTooShort()) + "</td></tr><tr>"); }
        }
        %>
                <td><b><%out.print(Messages.wielka(Messages.password()));%>*:</b></td><td><input type="password" size="25" name="passwd1" id="passwd1"/></td>
                </tr> <tr>
                    <td><b><%out.print(Messages.wielka(Messages.password()));%> (<%out.print(Messages.oneMoreTime());%>)*:</b></td><td><input type="password" size="25" name="passwd2" id="passwd2"/></td>
                </tr> <tr>
                <td><%out.print(Messages.wielka(Messages.name()));%>:</td><td><input type="text" size="25" name="imie" value="<%out.print(imie);%>"/></td>
                </tr> <tr>
                    <td><%out.print(Messages.wielka(Messages.surname()));%>:</td><td><input type="text" size="25" name="nazwisko" value="<%out.print(nazwisko);%>"/></td>
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
                <td><b><%out.print(Messages.wielka(Messages.email()));%>*:</b></td><td><input type="text" size="25" name="email" value="<%out.print(email);%>" id="email"/></td>
                </tr> <tr>
                <td><%out.print(Messages.wielka(Messages.wwwPage()));%>:</td><td><input type="text" size="25" name="www" value="<%out.print(www);%>" id="www"/></td>
                </tr> <tr>
                    <td><%out.print(Messages.wielka(Messages.gg()));%>:</td><td><input type="text" size="25" name="gg" value="<%out.print(gg);%>"/></td>
                </tr> <tr>
                <td><%out.print(Messages.wielka(Messages.jabber()));%>:</td><td><input type="text" size="25" name="jabber" value="<%out.print(jabber);%>"/></td>
                </tr> <tr>
                    <td><%out.print(Messages.wielka(Messages.tlen()));%>:</td><td><input type="text" size="25" name="tlen" value="<%out.print(tlen);%>"/></td>
                </tr> <tr>
                <td><%out.print(Messages.wielka(Messages.wpKontakt()));%>:</td><td><input type="text" size="25" name="wpKontakt" value="<%out.print(wpKontakt);%>"/></td>
                </tr> <tr>
                    <td><%out.print(Messages.wielka(Messages.icq()));%>:</td><td><input type="text" size="25" name="icq" value="<%out.print(icq);%>"/></td>
                </tr> <tr>
                <td><%out.print(Messages.wielka(Messages.msn()));%>:</td><td><input type="text" size="25" name="msn" value="<%out.print(msn);%>"/></td>
                </tr> <tr>
                <td><%out.print(Messages.wielka(Messages.yahoo()));%>:</td><td><input type="text" size="25" name="yahoo" value="<%out.print(yahoo);%>"/></td>
                </tr> <tr>
                <td><%out.print(Messages.wielka(Messages.skype()));%>:</td><td><input type="text" size="25" name="skype" value="<%out.print(skype);%>"/></td>
                </tr> <tr>
                    <td><%out.print(Messages.wielka(Messages.city()));%>:</td><td><input type="text" size="25" name="miasto" value="<%out.print(miasto);%>"/></td>
                </tr> <tr>
                    <td><%out.print(Messages.wielka(Messages.sex()));%>:</td><td><select name="plec">
                        <option value="<%out.print(DataBase.MEZCZYZNA);%>"><%out.print(Messages.wielka(Messages.men()));%></option>
                        <option value="<%out.print(DataBase.KOBIETA);%>"><%out.print(Messages.wielka(Messages.woman()));%></option>
                        </select>
                    </td>
                </tr> <tr>
                <td><%out.print(Messages.wielka(Messages.birthdate()));%>:</td>
                <td><input type="text" size="4" name="rokUrodzenia" value="<%out.print(rokUrodzenia);%>"/>-<input type="text" size="2" name="miesiacUrodzenia" value="<%out.print(miesiacUrodzenia);%>"/>-<input type="text" size="2" name="dzienUrodzenia" value="<%out.print(dzienUrodzenia);%>"/></td>
                </tr> <tr>
                    <td colspan="2" align="right"><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td>
                </tr>
            </table>
            * - <%out.print(Messages.fieldsObligatory());%>
        </form>
        <% }%>
    </body>
</html>
