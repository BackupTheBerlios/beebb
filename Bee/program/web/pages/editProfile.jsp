<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<% out.println(Commons.htmlHead("./..",Messages.wielka(Messages.editProfile())));%>
    <body onload="<% if ((request.getParameter("psw") == null) && (request.getParameter("chd") == null )) out.print("swapIframes();");%>resizeMain();setResizeFunction(resizeMain);" >
    
        <%@ include file="servletObjects.jsp" %>
                
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
                    
                    if (imie!=null) user.setImie(new String(imie.getBytes("8859_1"),"UTF-8")); if (nazwisko!=null) user.setNazwisko(new String(nazwisko.getBytes("8859_1"),"UTF-8")); 
                    if (email!=null) user.setEmail(new String(email.getBytes("8859_1"),"UTF-8")); if (gg!=null) user.setGG(new String(gg.getBytes("8859_1"),"UTF-8"));
                    if (jabber!=null) user.setJabber(new String(jabber.getBytes("8859_1"),"UTF-8"));
                    
                    String imieNazwiskoPrywatne=request.getParameter("imieNazwiskoPrywatne");
                    if (imieNazwiskoPrywatne!=null) user.setImieNazwiskoPrywatne(true); else
                        user.setImieNazwiskoPrywatne(false);
                    
                    String emailPrywatny=request.getParameter("emailPrywatny");
                    if (emailPrywatny!=null) user.setEmailPrywatny(true); else
                        user.setEmailPrywatny(false);
                    
                    String ggPrywatne=request.getParameter("ggPrywatne");
                    if (ggPrywatne!=null) user.setGGPrywatne(true); else
                        user.setGGPrywatne(false);
                    
                    String jabberPrywatny=request.getParameter("jabberPrywatny");
                    if (jabberPrywatny!=null) user.setJabberPrywatny(true); else
                        user.setJabberPrywatny(false);
                    
                    
                    String tlenPrywatny=request.getParameter("tlenPrywatny");
                    if (tlenPrywatny!=null) user.setTlenPrywatny(true); else
                        user.setTlenPrywatny(false);
                    
                    String wpKontaktPrywatny=request.getParameter("wpKontaktPrywatny");
                    if (wpKontaktPrywatny!=null) user.setWPKontaktPrywatny(true); else
                        user.setWPKontaktPrywatny(false);
                    
                    String icqPrywatne=request.getParameter("icqPrywatne");
                    if (icqPrywatne!=null) user.setICQPrywatne(true); else
                        user.setICQPrywatne(false);
                    
                    
                    if(!db_con.updateUser(user)) {
                        out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                    } else out.print(Messages.makeInfo(Messages.updated()));
                }
%>
    
        <form method="post" action="editProfile.jsp">
            <table align="center" class="tableProfile" border="0">
                <tr><th><% out.print(Messages.wielka(Messages.nick()));%></th><td class="tdProfileField">&nbsp;<% out.print(new String(user.getLogin().getBytes("8859_1"),"UTF-8"));%></td><td></td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.name()));%></th><td class="tdProfileField"><input type="text" name="imie" size="35" value="<% out.print(new String(user.getImie().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField" rowspan="2" valign="middle"><input type="checkbox" name="imieNazwiskoPrywatne" <%if(!user.ifShowName()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.surname()));%></th><td class="tdProfileField"><input type="text" name="nazwisko" size="35" value="<% out.print(new String(user.getNazwisko().getBytes("8859_1"),"UTF-8"));%>"/></td><td></td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.email()));%></th><td class="tdProfileField"><input type="text" name="email" size="35" value="<% out.print(new String(user.getEmail().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="emailPrywatny" <%if(!user.ifShowEmail()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.number())+ " " + Messages.gg());%></th><td class="tdProfileField"><input type="text" name="gg" size="35" value="<% out.print(new String(user.getGG().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="ggPrywatne" <%if(!user.ifShowGG()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.jabber()));%></th><td class="tdProfileField"><input type="text" name="jabber" size="35" value="<% out.print(new String(user.getJabber().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="jabberPrywatny" <%if(!user.ifShowJabber()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.tlen()));%></th><td class="tdProfileField"><input type="text" name="tlen" size="35" value="<% out.print(new String(user.getTlen().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="tlenPrywatny" <%if(!user.ifShowTlen()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.wpKontakt()));%></th><td class="tdProfileField"><input type="text" name="wpKontakt" size="35" value="<% out.print(new String(user.getWPKontakt().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="wpKontaktPrywatny" <%if(!user.ifShowWPKontakt()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.icq()));%></th><td class="tdProfileField"><input type="text" name="icq" size="35" value="<% out.print(new String(user.getICQ().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="icqPrywatne" <%if(!user.ifShowICQ()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.msn()));%></th><td class="tdProfileField"><input type="text" name="msn" size="35" value="<% out.print(new String(user.getMSN().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="msnPrywatny" <%if(!user.ifShowMSN()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.city()));%></th><td class="tdProfileField"><input type="text" name="miasto" size="35" value="<% out.print(new String(user.getCity().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="miastoPrywatne" <%if(!user.ifShowCity()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.sex()));%></th><td class="tdProfileField"><input type="text" name="plec" size="35" value="<% out.print(new String(user.getSex().getBytes("8859_1"),"UTF-8"));%>"/></td><td></td></tr>                        
                <tr><th><% out.print(Messages.wielka(Messages.birthdate()));%></th><td class="tdProfileField"><input type="text" name="rokUrodzenia" size="35" value="<% out.print(new String(user.getBirthDate().getBytes("8859_1"),"UTF-8"));%>"/></td><td class="tdProfileField"><input type="checkbox" name="rokUrodzeniaPrywatny" <%if(!user.ifShowBirthDate()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td></tr>                        
                <tr><td colspan="2" align="right"><input type="hidden" name="chd" value=""/><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td></tr>
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
        <form method="post" action="editProfile.jsp">
            <table align="center" class="tableProfile" border="0">
                <tr><th><% out.print(Messages.wielka(Messages.old()) + " " + Messages.password());%></th><td class="tdProfileField"><input type="password" name="oldpasswd" size="35"/></td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.password()));%></th><td class="tdProfileField"><input type="password" name="passwd1" size="35"/></td></tr>
                <tr><th><% out.print(Messages.wielka(Messages.password()) + " " + Messages.oneMoreTime()); %></th><td class="tdProfileField"><input type="password" name="passwd2" size="35"/></td></tr>
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
    </body>
</html>
