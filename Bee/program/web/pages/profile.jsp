<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>


<% out.println(Commons.htmlHead(request,"./..","BeeBB :: Content"));%>
    <body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >    
        <table border="0" id="mainTableProfile" width="100%">
            <tr><td>
                <%@ include file="servletObjects.jsp" %>
<%
//       User user = auth.getUser(requestdb_con);
//       if (user == null)
//       {
//           out.println(Messages.errorNotLoggedIn());
//       }
//       else
//       {
            String uid = request.getParameter("uid");
            if (uid == null) out.println(Messages.errorUnknown());
            else
            {   
                User user = db_con.getUser(Integer.parseInt(uid));
                if (user==null) out.println(Messages.errorUnknown()); else {
                
                %>
                <table align="center" class="tableProfile" border="0">
                    <tr><th><% out.print(Messages.login());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getLogin());%>&nbsp;</td></tr>
                <% 
                    if(user.ifShowName()) {
                        out.print("<tr><th>"+Messages.wielka(Messages.name())+"</th><td class=\"tdProfileField\">&nbsp;"+user.getImie()+"&nbsp;</td></tr>");
                        out.print("<tr><th>"+Messages.wielka(Messages.surname())+"</th><td class=\"tdProfileField\">&nbsp;"+user.getNazwisko()+"&nbsp;</td></tr>");
                      }  
                    if ((user.ifShowEmail())&&(user.getEmail().length()>0)) {
                        out.println("<tr><th>"+Messages.wielka(Messages.email())+"</th>");
                        out.println("<td align=\"center\" class=\"tdProfileField\"><a href=\"mailto:"+user.getEmail()+"\" class=\"aHref\">"+user.getEmail()+"</a></td>");
                        out.println("</tr>");
                      }
                    out.println("<tr><th>"+Messages.wielka(Messages.wwwPage())+"</th>");
                    out.println("<td align=\"center\" class=\"tdProfileField\">&nbsp;"+(user.getWWW().length()>0?"<a href=\""+user.getWWW()+"\" class=\"aHref\" target=\"_blank\"><img src=\"../images/www.gif\" border=\"0\" alt=\""+user.getWWW()+"\"/></a>":"")+"</td>");
                    out.println("</tr>");
                    if (user.ifShowCity()){
                        out.println("<tr><th>"+Messages.wielka(Messages.city())+"</th>");
                        out.println("<td class=\"tdProfileField\">&nbsp;"+user.getCity()+"</td>");
                        out.println("</tr>");
                      }
                    if (user.ifShowBirthDate()){
                        out.println("<tr><th>"+Messages.wielka(Messages.birthdate())+"</th>");
                        out.println("<td align=\"center\" class=\"tdProfileField\">&nbsp;"+user.getBirthDate()+"&nbsp;</td>");
                        out.println("</tr>");
                      }                      
                    if ((user.ifShowGG())&&(user.getGG().length()>0)){
                        out.print("<tr><th>"+Messages.wielka(Messages.number())+ " " + Messages.gg()+"</th>");
                        out.println("<td class=\"tdProfileField\">&nbsp;<a class=\"aHref\" href=\"gg:"+user.getGG()+"\"><img border=\"0\" src=\"http://www.gadu-gadu.pl/users/status.asp?id="+user.getGG()+"&amp;style=1\" alt=\""+user.getGG()+"\" onerror=\"this.onerror=null;this.src='../images/ggunknown.gif';\"></a></td>");
                        out.println("</tr>");
                    }
                    if ((user.ifShowTlen()) && (user.getTlen().length()>0)){
                        String tlen = user.getTlen();
                        String[] kawalki = tlen.split("@");
                        tlen = kawalki.length>0?kawalki[0]:"";
                        out.println("<tr><th>"+Messages.wielka(Messages.tlen())+"</th>");
                        out.println("<td align=\"center\"class=\"tdProfileField\"><a href=\"http://ludzie.tlen.pl/"+tlen+"/\" target=\"_blank\"><img src=\"http://status.tlen.pl/?u="+tlen+"&amp;t=1\" width=\"18\" alt=\""+user.getTlen()+"\" height=\"18\" border=\"0\"  onerror=\"this.onerror=null;this.src='../images/tlenunknown.png';\"></a></td>");
                        out.println("</tr>");
                      }                      
                    if ((user.ifShowICQ())&&(user.getICQ().length()>0)){
                        out.println("<tr><th>"+Messages.wielka(Messages.icq())+"</th>");
                        out.println("<td align=\"center\" class=\"tdProfileField\"><img src=\"http://status.icq.com/online.gif?icq="+user.getICQ()+"&amp;img=5\" alt=\""+user.getICQ()+"\" onerror=\"this.onerror=null;this.src='../images/icqunknown.png';\"></td>");
                        out.println("</tr>");
                      }                      
                    if (user.ifShowMSN() && user.getMSN().length()>0)
                    {
                        out.println("<tr><th>"+Messages.wielka(Messages.msn())+"</th><td align=\"center\" class=\"tdProfileField\">");
                        out.println("<!-- http://www.onlinestatus.org/ -->");
                        out.println("<td><a href=\"http://checker.tdknights.com:1337/message/msn/"+user.getMSN()+"\">");
                        out.println("<img src=\"http://checker.tdknights.com:1337/msn/"+user.getMSN()+"\" align=\"center\" border=\"0\" alt=\""+user.getMSN()+"\" onerror=\"this.onerror=null;this.src='../images/msnunknown.gif';\"></a>");
                        out.println("</td></tr>");
                    }
                    if (user.ifShowJabber() && user.getJabber().length()>0)
                    {
                        out.print("<tr><th>"+Messages.wielka(Messages.jabber())+"</th><td align=\"center\" class=\"tdProfileField\">");
                        out.println("<!-- http://www.onlinestatus.org/ -->");
                        out.println("<a href=\"xmpp:"+user.getJabber()+"\" onmouseover=\"showHint('"+Messages.wielka(Messages.hintJabberStatus())+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");
                        out.println("<img src=\"http://osi.lostinspacehosting.com:81/jabber/"+user.getJabber()+"/onurl=tdknights.com/checker/jabberonline.gif/offurl=tdknights.com/checker/jabberoffline.gif/unknownurl=tdknights.com/checker/jabberunknown.gif\""+
                                    "align=\"middle\" border=\"0\" alt=\""+user.getJabber()+"\""+
                                    "onerror=\"this.onerror=null;this.src='../images/jabberunknown.gif';\"></a>");
                       out.println("</td></tr>");
                    }
                    if ((user.ifShowWPKontakt())&&(user.getWPKontakt().length()>0)){
                        out.println("<tr><th>"+Messages.wielka(Messages.wpKontakt())+"</th>");
                        out.println("<td align=\"center\" class=\"tdProfileField\"><img src=\"http://kontakt.wp.pl/status.html?login="+user.getWPKontakt()+"&styl=0\" alt=\""+user.getWPKontakt()+"\"></td>");
                        out.println("</tr>");
                      }                      
                    out.println("<tr><th>"+Messages.wielka(Messages.lastLogged())+"</th>");
                    out.println("<td align=\"center\" class=\"tdProfileField\">&nbsp;"+user.getLastLog()+"&nbsp;</td>");
                    out.println("</tr>");
%>                   
                </table>
                <% }
            }
    %>
            </td></tr>
        </table>
    </body>
</html>
