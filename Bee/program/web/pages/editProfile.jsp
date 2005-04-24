<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
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
        <title>BeeBB :: <% out.print(Messages.editProfile());%></title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/>
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>
    </head>
    <body onload="resizeAuth()" onresize="resizeAuth()">
    
        <%@ include file="servletObjects.jsp" %>
        <table width="100%" border="0" id="tableAuth"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
            <tr><td align="center">
                
    <%
                User user = auth.getUser(request,db_con);
                if (user!=null) {
                String chd=request.getParameter("chd");
                if (chd!=null) { 
                    String imie=request.getParameter("imie");
                    String nazwisko=request.getParameter("nazwisko");
                    String email=request.getParameter("email");
                    String gg=request.getParameter("gg");
                    String jabber=request.getParameter("jabber");
                    String imieNazwiskoPrywatne=request.getParameter("imieNazwiskoPrywatne");
                    String emailPrywatny=request.getParameter("emailPrywatny");
                    String ggPrywatne=request.getParameter("ggPrywatne");
                    String jabberPrywatny=request.getParameter("jabberPrywatny");
                    if (imie!=null) user.setImie(new String(imie.getBytes("8859_1"),"UTF-8")); if (nazwisko!=null) user.setNazwisko(new String(nazwisko.getBytes("8859_1"),"UTF-8")); 
                    if (email!=null) user.setEmail(new String(email.getBytes("8859_1"),"UTF-8")); if (gg!=null) user.setGG(new String(gg.getBytes("8859_1"),"UTF-8"));
                    if (jabber!=null) user.setJabber(new String(jabber.getBytes("8859_1"),"UTF-8"));
                    if (imieNazwiskoPrywatne!=null) user.setImieNazwiskoPrywatne(true); else
                        user.setImieNazwiskoPrywatne(false);
                    if (emailPrywatny!=null) user.setEmailPrywatny(true); else
                        user.setEmailPrywatny(false);
                    if (ggPrywatne!=null) user.setGGPrywatne(true); else
                        user.setGGPrywatne(false);
                    if (jabberPrywatny!=null) user.setJabberPrywatny(true); else
                        user.setJabberPrywatny(false);
                    
                    if(!db_con.updateUser(user)) {
                        out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                    } else out.print(Messages.makeInfo(Messages.updated()));
                }
%>
    
                <form method="POST" action="editProfile.jsp">
                    <table align="center" class="tableProfile" border="0">
                        <tr><th><% out.print(Messages.wielka(Messages.nick()));%></th><td class="tdProfileField">&nbsp;<% out.print(new String(user.getLogin().getBytes("8859_1"),"UTF-8"));%></td><td></td></tr>
                        <tr><th><% out.print(Messages.wielka(Messages.name()));%></th><td class="tdProfileField"><input type="text" name="imie" size="35" value="<% out.print(new String(user.getImie().getBytes("8859_1"),"UTF-8"));%>"></td><td class="tdProfileField" rowspan="2" valign="middle"><input type="checkbox" name="imieNazwiskoPrywatne" <%if(!user.ifShowName()) out.print("checked");%>> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                        <tr><th><% out.print(Messages.wielka(Messages.surname()));%></th><td class="tdProfileField"><input type="text" name="nazwisko" size="35" value="<% out.print(new String(user.getNazwisko().getBytes("8859_1"),"UTF-8"));%>"></td><td></td></tr>
                        <tr><th><% out.print(Messages.wielka(Messages.email()));%></th><td class="tdProfileField"><input type="text" name="email" size="35" value="<% out.print(new String(user.getEmail().getBytes("8859_1"),"UTF-8"));%>"></td><td class="tdProfileField"><input type="checkbox" name="emailPrywatny" <%if(!user.ifShowEmail()) out.print("checked");%>> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                        <tr><th><% out.print(Messages.wielka(Messages.number())+ " " + Messages.gg());%></th><td class="tdProfileField"><input type="text" name="gg" size="35" value="<% out.print(new String(user.getGG().getBytes("8859_1"),"UTF-8"));%>"></td><td class="tdProfileField"><input type="checkbox" name="ggPrywatne" <%if(!user.ifShowGG()) out.print("checked");%>> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                        <tr><th><% out.print(Messages.wielka(Messages.jabber()));%></th><td class="tdProfileField"><input type="text" name="jabber" size="35" value="<% out.print(new String(user.getJabber().getBytes("8859_1"),"UTF-8"));%>"></td><td class="tdProfileField"><input type="checkbox" name="jabberPrywatny" <%if(!user.ifShowJabber()) out.print("checked");%>> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                        <tr><td colspan="2" align="right"><input type="hidden" name="chd" value=""><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td></tr>
                    </table>
                </form>
                <br/>
                <%
                String psw=request.getParameter("psw");
                if (psw!=null) { 
                    String oldpasswd = request.getParameter("oldpasswd");
                    String passwd1 = request.getParameter("passwd1");
                    String passwd2 = request.getParameter("passwd2");
                    
                    if (oldpasswd!= null) {
                        if (passwd1!=null && passwd2!=null && passwd1.compareTo(passwd2)==0) {
                            if (passwd1.length() < Config.MIN_PASSWD) out.print(Messages.makeError(Messages.passwordTooShort())); else
                            if (Crypto.matches(user.getHaslo(),oldpasswd)) {
                                user.setHaslo(Crypto.crypt(passwd2));
                                if(!db_con.updateUser(user)) {
                                    out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                                } else { auth.zaloguj(user.getLogin(),user.getHaslo(),db_con,konfiguracja,response);
                                out.print(Messages.makeInfo(Messages.passwordChanged())); }
                            } else out.print(Messages.makeError(Messages.oldPasswordNotMatch()));
                        } else out.print(Messages.makeError(Messages.passwordNotMatch()));
                    } else out.print(Messages.makeError(Messages.oldPasswordNotMatch()));
                    
                }
                %>
                <form method="POST" action="editProfile.jsp">
                    <table align="center" class="tableProfile" border="0">
                        <tr><th><% out.print(Messages.wielka(Messages.old()) + " " + Messages.password());%></th><td class="tdProfileField"><input type="password" name="oldpasswd" size="35"></td></tr>
                        <tr><th><% out.print(Messages.wielka(Messages.password()));%></th><td class="tdProfileField"><input type="password" name="passwd1" size="35"></td></tr>
                        <tr><th><% out.print(Messages.wielka(Messages.password()) + " " + Messages.oneMoreTime()); %></th><td class="tdProfileField"><input type="password" name="passwd2" size="35"></td></tr>
                        <tr><td colspan="2" align="right"><input type="hidden" name="psw" value=""><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td></tr>
                    </table>
                </form>
                <br/>
                
     <%
                } else {
                    out.println(Messages.makeError(Messages.errorNotLoggedIn()));
                    out.println();
                }
      %>
            </td>
            </tr>
        </table>
    </body>
</html>
