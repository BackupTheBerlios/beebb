<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="pl.ltd.bee.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="javax.swing.ImageIcon"%>
<%@ page session="false" %>
    
<%@ include file="servletObjects.jsp" %>

<% out.println(Commons.htmlHead(request,"./..",Messages.wielka(Messages.editProfile())));
 String css = Commons.getQueryStyle(request);
%>
    <body onload="<% 
                User user = auth.getUser(request,db_con);
                String styl = request.getParameter("styl");
                if ((styl != null) && (user != null))
                {
                    if (user.getStyle().compareTo(styl) != 0)
                        out.print("topLink('../index.jsp?style="+styl+"&amp;header=pages/header.jsp?style="+styl+"&amp;content=pages/editProfile.jsp?style="+styl+"')");
                    else out.print("swapIframes();resizeMain();setResizeFunction(resizeMain);");
                }
                else out.print("swapIframes();resizeMain();setResizeFunction(resizeMain);");

        %>" >
        
    <%
                if (user!=null) { %>
           
        <% String avt=request.getParameter("avt");
            if (FileUpload.isMultipartContent(request)) {
            DiskFileUpload upload = new DiskFileUpload();
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            if (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    try {
                        ImageIcon icon = new ImageIcon(item.get());
                        if((item.getContentType().compareTo("image/png") == 0 || item.getContentType().compareTo("image/jpeg") == 0 )
                                && item.getSize() > 0 && item.getSize() <= Config.AVATAR_SIZE
                                && icon.getIconHeight() > 0 && icon.getIconHeight() <= Config.AVATAR_HEIGHT
                                && icon.getIconWidth() > 0 && icon.getIconWidth() <= Config.AVATAR_WIDTH  ) {
                                 String filetype="";
                                 if ( item.getContentType().compareTo("image/png") == 0 ) filetype = ".png";
                                 if ( item.getContentType().compareTo("image/jpeg") == 0 ) filetype = ".jpg";
                                    File outputFile = new File(application.getRealPath("/data/avatars/" + user.getLogin()  + filetype));
                                    item.write(outputFile);
                                    user.setAvatar(user.getLogin()  + filetype);
                                    out.println("<center>");
                                    if(!db_con.updateUser(user))
                                        out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                                    else out.print(Messages.makeInfo(Messages.updated()));
                                        out.println("</center><hr/><br/>");
                        }
                        else out.println(Messages.makeError(Messages.avatarError()));
                    } catch (Exception e) {
                     //out.println("blad!" + e);   
                    }
                }
            }
         }
        %>        
        
        <form enctype="multipart/form-data" method="post" action="editProfile.jsp<% out.print(css.length()>0?"?"+css:"");%>">
            <table align="center" class="tableProfile" border="0">
                <tr><td><% if(user.getAvatar().compareTo("")!=0) out.println("<img src=\"../data/avatars/" + user.getAvatar() + "\" alt=\"AVATAR\"/>"); %></td></tr>
                <tr><td class="tdProfileField"><input type="file" name="avatar-file" size="35"/></td></tr>
                <tr><td colspan="2" align="right"><input type="submit" name="submit" value="<%out.print(Messages.wielka(Messages.send()));%>"/></td></tr>
            </table>
        </form>
        <br/>
        
        
        <%
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
                    String yahoo=request.getParameter("yahoo");
                    String skype=request.getParameter("skype");
                    String miasto=request.getParameter("miasto");
                    String plec=request.getParameter("plec");
                    String rokUrodzenia=request.getParameter("rokUrodzenia");
                    String miesiacUrodzenia=request.getParameter("miesiacUrodzenia");
                    String dzienUrodzenia=request.getParameter("dzienUrodzenia");
                    String www=request.getParameter("www");
                    //String styl=request.getParameter("styl");
                    
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
                    if (yahoo!=null) user.setYahoo(new String(yahoo.getBytes("8859_1"),"UTF-8"));
                    if (skype!=null) user.setSkype(new String(skype.getBytes("8859_1"),"UTF-8"));
                    if (miasto!=null) user.setCity(new String(miasto.getBytes("8859_1"),"UTF-8"));
                    if (plec!=null) user.setSex(new String(plec.getBytes("8859_1"),"UTF-8"));
                    if (styl!=null) user.setStyle(styl);
                    String dataUrodzenia;
                    try {
                    if (rokUrodzenia!=null && miesiacUrodzenia!=null && dzienUrodzenia!=null) {
                        if (Integer.decode(rokUrodzenia).intValue()>=1000 && Integer.decode(rokUrodzenia).intValue()<2100 &&  
                                Integer.decode(miesiacUrodzenia).intValue()>=1 && Integer.decode(miesiacUrodzenia).intValue()<=12 && 
                                Integer.decode(dzienUrodzenia).intValue()>=1 && Integer.decode(dzienUrodzenia).intValue()<=31) {
                        dataUrodzenia=rokUrodzenia + "-";
                        if (Integer.decode(miesiacUrodzenia).intValue() < 10) dataUrodzenia+="0";
                        dataUrodzenia+=miesiacUrodzenia + "-";
                        if (Integer.decode(dzienUrodzenia).intValue() < 10) dataUrodzenia+="0";
                        dataUrodzenia+= dzienUrodzenia;
                        
                        user.setBirthDate(new String(dataUrodzenia.getBytes("8859_1"),"UTF-8"));
                        }
                    }
                    } catch (Exception e) {
                        //jak ktoś jest głupi i nie umie poprawnie wpisać swojej daty urodzenia to tylko pogratulować
                    }
                    
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
                        
                    String yahooPrywatne=request.getParameter("yahooPrywatne");
                    if (yahooPrywatne!=null) user.setYahooPrivate(true); else
                        user.setYahooPrivate(false);
                    
                    String skypePrywatny=request.getParameter("skypePrywatny");
                    if (skypePrywatny!=null) user.setSkypePrivate(true); else
                        user.setSkypePrivate(false);
                        
                    String miastoPrywatne=request.getParameter("miastoPrywatne");
                    if (miastoPrywatne!=null) user.setCityPrivate(true); else
                        user.setCityPrivate(false);
                        
                    String dataUrodzeniaPrywatna=request.getParameter("dataUrodzeniaPrywatna");
                    if (dataUrodzeniaPrywatna!=null) user.setBirthDatePrivate(true); else
                        user.setBirthDatePrivate(false);
                    
                    out.println("<center>");
                    if(!db_con.updateUser(user)) {
                        out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                    } else out.print(Messages.makeInfo(Messages.updated()));
                    out.println("</center><hr/><br/>");
                }
%>
    
        <form method="post" action="editProfile.jsp<% out.print(css.length()>0?"?"+css:"");%>">
            <table align="center" class="tableProfile" border="0">
                <tr>
                    <th><% out.print(Messages.wielka(Messages.nick()));%></th>
                    <td class="tdProfileField">&nbsp;<% out.print(new String(user.getLogin().getBytes("8859_1"),"UTF-8"));%></td>
                    <td class="tdProfileField" align="center">&nbsp; <%out.print(Messages.hide());%> &nbsp;</td>
                </tr>
                <tr>
                    <th><%out.print("<span onmouseover=\"showHint('"+Messages.hintName()+"',this,"+Config.HINT_DELAY+",100,100)\" onmouseout=\"hideHint(this)\">" +  Messages.wielka(Messages.name()) + "</span>");%></th>
                    <td class="tdProfileField"><%out.print("<span onmouseover=\"showHint('"+Messages.hintName()+"',this,"+Config.HINT_DELAY+",100,100)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="imie" size="35" style="width:230px" value="<% out.print(new String(user.getImie().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" rowspan="2" valign="middle" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="imieNazwiskoPrywatne" <%if(!user.ifShowName()) out.print("checked");%>/>&nbsp;</span></td>
                </tr>
                <tr>
                    <th><%out.print("<span onmouseover=\"showHint('"+Messages.hintSurname()+"',this,"+Config.HINT_DELAY+",100,100)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.surname())+ "</span>");%></th>
                    <td class="tdProfileField"  align="center"><%out.print("<span onmouseover=\"showHint('"+Messages.hintSurname()+"',this,"+Config.HINT_DELAY+",100,100)\" onmouseout=\"hideHint(this)\">");%>
                       <input type="text" name="nazwisko" size="35" style="width:230px" value="<% out.print(new String(user.getNazwisko().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td></td>
                </tr>
                <tr>
                    <th><%out.print("<span onmouseover=\"showHint('"+Messages.hintEmail()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.email())+ "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintEmail()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="email" size="35" style="width:230px" value="<% out.print(new String(user.getEmail().getBytes("8859_1"),"UTF-8"));%>"/></td>
                    <td class="tdProfileField"  align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="emailPrywatny" <%if(!user.ifShowEmail()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintWWW()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.wwwPage())+ "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintWWW()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="www" size="35" style="width:230px" value="<% out.print(new String(user.getWWW().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td></td>
                </tr>
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintGG()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.number())+ " " + Messages.gg() + "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintGG()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="gg" size="35" style="width:230px" value="<% out.print(new String(user.getGG().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="ggPrywatne" <%if(!user.ifShowGG()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintJabber()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.jabber()) + "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintJabber()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="jabber" size="35" style="width:230px" value="<% out.print(new String(user.getJabber().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="jabberPrywatny" <%if(!user.ifShowJabber()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintTlen()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.tlen()) + "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintTlen()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="tlen" size="35" style="width:230px" value="<% out.print(new String(user.getTlen().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="tlenPrywatny" <%if(!user.ifShowTlen()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><%out.print("<span onmouseover=\"showHint('"+Messages.hintWPKontakt()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.wpKontakt()) + "</span>");%></th>
                    <td class="tdProfileField">
                            <%out.print("<span onmouseover=\"showHint('"+Messages.hintWPKontakt()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                            <input type="text" name="wpKontakt" size="35" style="width:230px" value="<% out.print(new String(user.getWPKontakt().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="wpKontaktPrywatny" <%if(!user.ifShowWPKontakt()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintICQ()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.icq())+ "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintICQ()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="icq" size="35" style="width:230px" value="<% out.print(new String(user.getICQ().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="icqPrywatne" <%if(!user.ifShowICQ()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka("<span onmouseover=\"showHint('"+Messages.hintMSN()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.msn())+ "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintMSN()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="msn" size="35" style="width:230px" value="<% out.print(new String(user.getMSN().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="msnPrywatny" <%if(!user.ifShowMSN()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintYahoo()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.yahoo()) + "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintYahoo()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="yahoo" size="35" style="width:230px" value="<% out.print(new String(user.getYahoo().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="yahooPrywatne" <%if(!user.ifShowYahoo()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintSkype()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.skype()) + "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintSkype()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="skype" size="35" style="width:230px" value="<% out.print(new String(user.getSkype().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="skypePrywatny" <%if(!user.ifShowSkype()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print(Messages.wielka("<span onmouseover=\"showHint('"+Messages.hintCity()+"',this,"+Config.HINT_DELAY+",200,200)\" onmouseout=\"hideHint(this)\">" + Messages.city()) + "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintCity()+"',this,"+Config.HINT_DELAY+",200,200)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="text" name="miasto" size="35" style="width:230px" value="<% out.print(new String(user.getCity().getBytes("8859_1"),"UTF-8"));%>"/></span></td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="miastoPrywatne" <%if(!user.ifShowCity()) out.print("checked");%>/></span></td>
                </tr>
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintSex()+"',this,"+Config.HINT_DELAY+",100,100)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.sex()) + "</span>");%></th>
                    <td class="tdProfileField">
                                            <%out.print("<span onmouseover=\"showHint('"+Messages.hintSex()+"',this,"+Config.HINT_DELAY+",100,100)\" onmouseout=\"hideHint(this)\">");%>
                        <select name="plec" style="width:230px">
                            <option value="<%out.print(DataBase.MEZCZYZNA);%>" <% if(user.getSex().compareTo(DataBase.MEZCZYZNA)==0) out.print("selected");%>><%out.print(Messages.wielka(Messages.men()));%></option>
                            <option value="<%out.print(DataBase.KOBIETA);%>" <% if(user.getSex().compareTo(DataBase.KOBIETA)==0) out.print("selected");%>><%out.print(Messages.wielka(Messages.woman()));%></option>
                        </select>
                        </span>
                    </td>
                    <td></td>
                </tr>        
                <tr>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintTheme()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.theme()) + "</span>");%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintTheme()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <select name="styl" style="width:230px">
                        <%
                            String stylesDir = application.getRealPath("/styles/");
                            java.io.File katalog = new java.io.File(stylesDir);
                            if (katalog.isDirectory()){
                            File[] pliki = katalog.listFiles();
                            String user_temat = user.getStyle();
                            if (user_temat.length() == 0) user_temat = Config.DEFAULT_STYLE;
                            for(int i=0; i<pliki.length; i++)
                                if (pliki[i].isFile()){
                                    String temat = pliki[i].getName().substring(0,pliki[i].getName().indexOf('.'));
                                    out.println("<option"+(user_temat.compareTo(temat)==0?" selected":"")+">"+temat+"</option>");
                                }
                            }
                        %>
                        </select>
                        </span>
                    </td>
                    <td></td>
                </tr>        
                <tr><% try { %>
                    <th><% out.print("<span onmouseover=\"showHint('"+Messages.hintBirthDate()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">" + Messages.wielka(Messages.birthdate()));%></th>
                    <td class="tdProfileField">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintBirthYear()+"',this,"+Config.HINT_DELAY+",40,40)\" onmouseout=\"hideHint(this)\">");%>                    
                        <select name="rokUrodzenia">
                        <% for (int i=1930; i<=2005; i++)  {
                            out.print("<option value=\""+i+"\"");
                            if (i==Integer.decode(user.getBirthYear()).intValue()) out.print(" selected"); 
                            out.println(">" + i + "</option>");
                        } %>
                        </select>
                        </span>
                        -
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintBirthMonth()+"',this,"+Config.HINT_DELAY+",40,40)\" onmouseout=\"hideHint(this)\">");%>                        
                        <select name="miesiacUrodzenia">
                        <% for (int i=1; i<=12; i++) {
                            out.print("<option value=\""+i+"\"");
                            String month = user.getBirthMonth();
                            if (month.startsWith("0")) month=month.substring(1); 
                            if (i==Integer.decode(month).intValue()) out.print(" selected"); 
                            out.println(">"+ i +"</option>");
                        } %>
                        </select>
                        </span>
                        -
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintBirthDay()+"',this,"+Config.HINT_DELAY+",40,40)\" onmouseout=\"hideHint(this)\">");%>                        
                        <select name="dzienUrodzenia">
                        <% for (int i=1; i<=31; i++) {
                            out.print("<option value=\""+i+"\"");
                            String day = user.getBirthDay();
                            if (day.startsWith("0")) day=day.substring(1);
                            if (i==Integer.decode(day).intValue()) out.print(" selected"); 
                            out.println(">"+ i +"</option>");
                        } %>
                        </select>
                        </span>
                        <% } catch (Exception e) { out.println(e); } %>
                    </td>
                    <td class="tdProfileField" align="center">
                        <%out.print("<span onmouseover=\"showHint('"+Messages.hintHide()+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");%>
                        <input type="checkbox" name="dataUrodzeniaPrywatna" <%if(!user.ifShowBirthDate()) out.print("checked");%>/></span></td>
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
        <form method="post" action="editProfile.jsp<% out.print(css.length()>0?"?"+css:"");%>">
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
