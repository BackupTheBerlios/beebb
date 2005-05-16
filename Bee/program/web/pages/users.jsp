<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<% out.println(Commons.htmlHead("./..","BeeBB :: Content"));%>
<body onload="swapIframes();resizeMain();setResizeFunction(resizeMain);" >    
<%@ include file="servletObjects.jsp" %>
<table id="tableSearch" border="0" width="100%">
<tr><th><% 
        Commons.setCachingNever(response);
        out.println(Messages.wielka(Messages.login())+"</th>"+
        "<th>"+Messages.wielka(Messages.name())+" "+Messages.and()+" "+Messages.wielka(Messages.surname())+"</th>"+
        "<th>"+Messages.wielka(Messages.email())+"</th>"+
        "<th>"+Messages.wielka(Messages.wwwPage())+"</th>"+
        "<th>"+Messages.wielka(Messages.city())+"</th>"+
        "<th>"+Messages.wielka(Messages.birthdate())+"</th>"+
        "<th>"+Messages.wielka(Messages.gg())+"</th>"+
        "<th>"+Messages.wielka(Messages.tlen())+"</th>"+
        "<th>"+Messages.wielka(Messages.icq())+"</th>"+
        "<th>"+Messages.wielka(Messages.msn())+"</th>"+
        "<th>"+Messages.wielka(Messages.jabber())+"</th>"+
        "<th>"+Messages.wielka(Messages.wpKontakt())+"</th>"+
        "<th>"+Messages.wielka(Messages.lastLogged())+"</th>"+
        "</tr>");

        ArrayList users = db_con.getUsersAktywni(true);
        for(int i=0; i<users.size(); i++)
        {
            User u = (User)users.get(i);
            if (u.getID() != Config.GUEST_ID)
            {
                out.println("<tr>");
                out.println("<td align=\"center\" class=\"tdUsersList\"><table width=\"100%\" border=\"0\"><tr><td align=\"left\"><img align=\"middle\" src=\""+(u.ifMale()?"../images/male.gif\" alt=\""+Messages.wielka(Messages.men()):"../images/female.gif\" alt=\""+Messages.wielka(Messages.woman()))+"\"></td><td>&nbsp;"+Commons.aHref(request,u.getLogin(),"./profile.jsp?uid="+u.getID())+"</td></tr></table></td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowName()?u.getImie()+"<br/>"+u.getNazwisko():"")+"</td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowEmail()?"<a href=\"mailto:"+u.getEmail()+"\" class=\"aHref\">"+u.getEmail()+"</a>":"")+"</td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.getWWW().length()>0?"<a href=\""+u.getWWW()+"\" class=\"aHref\" target=\"_blank\"><img src=\"../images/www.gif\" border=\"0\" alt=\""+u.getWWW()+"\"/></a>":"")+"</td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowCity()?u.getCity():"")+"</td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowBirthDate()?u.getBirthDate():"")+"</td>");

                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowGG()&&(u.getGG().length()>0)?"<a class=\"aHref\" href=\"gg:"+u.getGG()+"\"><img border=\"0\" src=\"http://www.gadu-gadu.pl/users/status.asp?id="+u.getGG()+"&amp;style=1\" alt=\""+u.getGG()+"\" onerror=\"this.onerror=null;this.src='../images/ggunknown.gif';\"></a>":"")+"</td>");
            String tlen = u.getTlen();
            String[] kawalki = tlen.split("@");
            tlen = kawalki.length>0?kawalki[0]:"";
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowTlen()&&(u.getTlen().length()>0)?"<a href=\"http://ludzie.tlen.pl/"+tlen+"/\" target=\"_blank\"><img src=\"http://status.tlen.pl/?u="+tlen+"&amp;t=1\" width=\"18\" alt=\""+u.getTlen()+"\" height=\"18\" border=\"0\"  onerror=\"this.onerror=null;this.src='../images/tlenunknown.png';\"></a>":"")+"</td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowICQ()&&(u.getICQ().length()>0)?"<img src=\"http://status.icq.com/online.gif?icq="+u.getICQ()+"&amp;img=5\" alt=\""+u.getICQ()+"\" onerror=\"this.onerror=null;this.src='../images/icqunknown.png';\">":"")+"</td>");
                out.print("<td align=\"center\"class=\"tdUsersList\">&nbsp;");
                if (u.ifShowMSN() && u.getMSN().length()>0)
                {
                    out.println("<!-- http://www.onlinestatus.org/ -->");
                    out.println("<a href=\"http://checker.tdknights.com:1337/message/msn/"+u.getMSN()+"\">");
                    out.println("<img src=\"http://checker.tdknights.com:1337/msn/"+u.getMSN()+"\" align=\"center\" border=\"0\" alt=\""+u.getMSN()+"\" onerror=\"this.onerror=null;this.src='../images/msnunknown.gif';\"></a>");
                }

                out.print("<td align=\"center\"class=\"tdUsersList\">&nbsp;");
                if (u.ifShowJabber() && u.getJabber().length()>0)
                {
                    out.println("<!-- http://www.onlinestatus.org/ -->");
                    out.println("<a href=\"xmpp:"+u.getJabber()+"\" onmouseover=\"showHint('"+Messages.wielka(Messages.hintJabberStatus())+"',this,"+Config.HINT_DELAY+",400,400)\" onmouseout=\"hideHint(this)\">");
                    out.println("<img src=\"http://osi.lostinspacehosting.com:81/jabber/"+u.getJabber()+"/onurl=tdknights.com/checker/jabberonline.gif/offurl=tdknights.com/checker/jabberoffline.gif/unknownurl=tdknights.com/checker/jabberunknown.gif\""+
                                "align=\"middle\" border=\"0\" alt=\""+u.getJabber()+"\""+
                                "onerror=\"this.onerror=null;this.src='../images/jabberunknown.gif';\"></a>");
                }
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+(u.ifShowWPKontakt()&&(u.getWPKontakt().length()>0)?"<img src=\"http://kontakt.wp.pl/status.html?login="+u.getWPKontakt()+"&styl=0\" alt=\""+u.getWPKontakt()+"\">":"")+"</td>");
                out.println("<td align=\"center\"class=\"tdUsersList\">&nbsp;"+u.getLastLog()+"</td>");
                out.println("</tr>");
            }
        }
    
%>

</table>
</body>
</html>
