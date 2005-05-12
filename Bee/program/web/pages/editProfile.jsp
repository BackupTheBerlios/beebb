<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page session="false" %>

<% out.println(Commons.htmlHead("./..",Messages.wielka(Messages.editProfile())));%>
    <body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >
    
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
                    String tlen=request.getParameter("tlen");
                    String wpKontakt=request.getParameter("wpKontakt");
                    String icq=request.getParameter("icq");
                    String msn=request.getParameter("msn");
                    String miasto=request.getParameter("miasto");
                    String plec=request.getParameter("plec");
                    String rokUrodzenia=request.getParameter("rokUrodzenia");
                    String www=request.getParameter("www");
                    
                    if (imie!=null) user.setImie(new String(imie.getBytes("8859_1"),"UTF-8")); 
                    if (nazwisko!=null) user.setNazwisko(new String(nazwisko.getBytes("8859_1"),"UTF-8")); 
                    if (email!=null) user.setEmail(new String(email.getBytes("8859_1"),"UTF-8")); 
                    if (www!=null) user.setWWW(new String(www.getBytes("8859_1"),"UTF-8")); 
                    if (gg!=null) user.setGG(new String(gg.getBytes("8859_1"),"UTF-8"));
                    if (jabber!=null) user.setJabber(new String(jabber.getBytes("8859_1"),"UTF-8"));
                    if (tlen!=null) user.setTlen(new String(tlen.getBytes("8859_1"),"UTF-8"));
                    if (wpKontakt!=null) user.setWPKontakt(new String(wpKontakt.getBytes("8859_1"),"UTF-8"));
                    if (icq!=null) user.setICQ(new String(icq.getBytes("8859_1"),"UTF-8"));
                    if (msn!=null) user.setMSN(new String(msn.getBytes("8859_1"),"UTF-8"));
                    if (miasto!=null) user.setCity(new String(miasto.getBytes("8859_1"),"UTF-8"));
                    if (plec!=null) user.setSex(new String(plec.getBytes("8859_1"),"UTF-8"));
                    if (rokUrodzenia!=null) user.setBirthDate(new String(rokUrodzenia.getBytes("8859_1"),"UTF-8"));
                    
                    String imieNazwiskoPrywatne=request.getParameter("imieNazwiskoPrywatne");
                    if (imieNazwiskoPrywatne!=null) user.setImieNazwiskoPrywatne(true); else
                        user.setImieNazwiskoPrywatne(false);
                    
                    String emailPrywatny=request.getParameter("emailPrywatny");
                    if (emailPrywatny!=null) user.setEmailPrivate(true); else
                        user.setEmailPrivate(false);
                    
                    String ggPrywatne=request.getParameter("ggPrywatne");
                    if (ggPrywatne!=null) user.setGGPrivate(true); else
                        user.setGGPrivate(false);
                    
                    String jabberPrywatny=request.getParameter("jabberPrywatny");
                    if (jabberPrywatny!=null) user.setJabberPrivate(true); else
                        user.setJabberPrivate(false);
                    
                    
                    String tlenPrywatny=request.getParameter("tlenPrywatny");
                    if (tlenPrywatny!=null) user.setTlenPrivate(true); else
                        user.setTlenPrivate(false);
                    
                    String wpKontaktPrywatny=request.getParameter("wpKontaktPrywatny");
                    if (wpKontaktPrywatny!=null) user.setWPKontaktPrivate(true); else
                        user.setWPKontaktPrivate(false);
                    
                    String icqPrywatne=request.getParameter("icqPrywatne");
                    if (icqPrywatne!=null) user.setICQPrivate(true); else
                        user.setICQPrivate(false);

                    String msnPrywatny=request.getParameter("msnPrywatny");
                    if (msnPrywatny!=null) user.setMSNPrivate(true); else
                        user.setMSNPrivate(false);

                    String miastoPrywatne=request.getParameter("miastoPrywatne");
                    if (miastoPrywatne!=null) user.setCityPrivate(true); else
                        user.setCityPrivate(false);

                    
                    if(!db_con.updateUser(user)) {
                        out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                    } else out.print(Messages.makeInfo(Messages.updated()));
                }
%>
    
        <form method="post" action="editProfile.jsp">
            <table align="center" class="tableProfile" border="0">
                <tr>
                    <th><% out.print(Messages.wielka(Messages.nick()));%></th>
                    <td class="tdProfileField">&nbsp;<% out.print(new String(user.getLogin().getBytes("8859_1"),"UTF-8"));%></td>
                    <td></td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.name()));%></th>
                    <td class="tdProfileField"><input type="text" name="imie" size="35" style="width:230px" value="<% out.print(new String(user.getImie().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField" rowspan="2" valign="middle"><input type="checkbox" name="imieNazwiskoPrywatne" <%if(!user.ifShowName()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.surname()));%></th>
                    <td class="tdProfileField"><input type="text" name="nazwisko" size="35" style="width:230px" value="<% out.print(new String(user.getNazwisko().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td></td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.email()));%></th>
                    <td class="tdProfileField"><input type="text" name="email" size="35" style="width:230px" value="<% out.print(new String(user.getEmail().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="emailPrywatny" <%if(!user.ifShowEmail()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.wwwPage()));%></th>
                    <td class="tdProfileField"><input type="text" name="www" size="35" style="width:230px" value="<% out.print(new String(user.getWWW().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td></td>
                    </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.number())+ " " + Messages.gg());%></th>
                    <td class="tdProfileField"><input type="text" name="gg" size="35" style="width:230px" value="<% out.print(new String(user.getGG().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="ggPrywatne" <%if(!user.ifShowGG()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.jabber()));%></th>
                    <td class="tdProfileField"><input type="text" name="jabber" size="35" style="width:230px" value="<% out.print(new String(user.getJabber().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="jabberPrywatny" <%if(!user.ifShowJabber()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                    </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.tlen()));%></th>
                    <td class="tdProfileField"><input type="text" name="tlen" size="35" style="width:230px" value="<% out.print(new String(user.getTlen().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="tlenPrywatny" <%if(!user.ifShowTlen()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.wpKontakt()));%></th>
                    <td class="tdProfileField"><input type="text" name="wpKontakt" size="35" style="width:230px" value="<% out.print(new String(user.getWPKontakt().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="wpKontaktPrywatny" <%if(!user.ifShowWPKontakt()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.icq()));%></th>
                    <td class="tdProfileField"><input type="text" name="icq" size="35" style="width:230px" value="<% out.print(new String(user.getICQ().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="icqPrywatne" <%if(!user.ifShowICQ()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.msn()));%></th>
                    <td class="tdProfileField"><input type="text" name="msn" size="35" style="width:230px" value="<% out.print(new String(user.getMSN().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="msnPrywatny" <%if(!user.ifShowMSN()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.city()));%></th>
                    <td class="tdProfileField"><input type="text" name="miasto" size="35" style="width:230px" value="<% out.print(new String(user.getCity().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="miastoPrywatne" <%if(!user.ifShowCity()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka(Messages.sex()));%></th>
                    <td class="tdProfileField">
                    <select name="plec" style="width:230px">
                        <option value="<%out.print(DataBase.MEZCZYZNA);%>" <% if(user.getSex().compareTo(DataBase.MEZCZYZNA)==0) out.print("selected");%>><%out.print(Messages.wielka(Messages.men()));%></option>
                        <option value="<%out.print(DataBase.KOBIETA);%>" <% if(user.getSex().compareTo(DataBase.KOBIETA)==0) out.print("selected");%>><%out.print(Messages.wielka(Messages.woman()));%></option>
                        </select>
                    </td>
                    <td></td>
                </tr>                        
                <tr>
                    <th><% out.print(Messages.wielka(Messages.birthdate()));%></th>
                    <td class="tdProfileField"><input type="text" name="rokUrodzenia" size="35" style="width:230px" value="<% out.print(new String(user.getBirthDate().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"><input type="checkbox" name="rokUrodzeniaPrywatny" <%if(!user.ifShowBirthDate()) out.print("checked");%>/> <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>                        
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
