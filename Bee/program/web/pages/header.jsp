<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>


<%
 out.println(Commons.htmlHead(request,"./..","BeeBB :: Content"));
 String css = Commons.getQueryStyle(request);
%>
    <body>
<%@ include file="servletObjects.jsp" %>
   
<table id="tableHeader" width="100%" cellspacing="0" cellpadding="1" border="0"><!-- Aby dobrze sie skalowalo wszystko musi byc zwarte w tej tabeli -->
<tr>
	<td width="20%" valign="top">
		<img class="beePicture" alt="Bee Sign" src="./../images/Bee.gif" align="top" onclick="linkClick('./main.jsp')" style="cursor: pointer;" />
	</td>
<td>
	<table width="100%" cellspacing="0" cellpadding="2" border="0">
		<tr>
			<td class="tdForumHeader" bgcolor="white" align="center"  onclick="linkClick('./main.jsp')">
			<span class="forumTitle" id="forumTitle">
                                <% 
                                    Forum f = db_con.getForum();
                                    if (f != null){
                                        String opis = f.getOpis();
                                        if (opis != null) out.println(opis);
                                           else out.println(f.getNazwa());
                                    }
                                    else out.println(Messages.errorUnknown());
                                   %>
			</span>
			</td>
		</tr>		
		<tr>
		<td id="cellMenu" align="center">
			<table id="tableMenu" border="0" width="600"> 
			<tr>
			<td class="tdTopLink" align="left"><span class="aTopLink">
				[] <%out.print(Messages.wielka(Messages.help()));%>
				</span>
			</td>
			<td class="tdTopLink" align="left"><span class="aTopLink" onclick="linkClick('./search.jsp<% out.print(css.length()>0?"?"+css:"");%>')">
				[] <%out.print(Messages.wielka(Messages.search()));%>
				</span>
			</td>
			<td class="tdTopLink" align="left"><span class="aTopLink" onclick="linkClick('./users.jsp<% out.print(css.length()>0?"?"+css:"");%>')">
				[] <%out.print(Messages.wielka(Messages.users()));%>
				</span>
			</td>
			<td class="tdTopLink" align="left"><span class="aTopLink">
				[] <%out.print(Messages.wielka(Messages.groups()));%>
				</span>
			</td>
			</tr>
			<tr>
			<td class="tdTopLink" align="left"><span class="aTopLink"
                                <%
                                if (auth.zalogowany(request,db_con)) 
                                    out.print(" onclick=\"linkClick('./editProfile.jsp"+(css.length()>0?"?"+css:"")+"')\"");   
                                out.print(">[] " +Messages.wielka(Messages.profile()));%>
				</span>
			</td>
			<td class="tdTopLink" align="left">
				<span class="aTopLink" onclick="top.open('../Administracja/index.jsp<% out.print(css.length()>0?"?"+css:"");%>','Bee')">[] <% out.print(Messages.wielka(Messages.adminPanel()));%></span>
			</td>
			<td class="tdTopLink" align="left">
				<span  class="aTopLink" onclick="linkClick('./addUser.jsp<% out.print(css.length()>0?"?"+css:"");%>')">[] <% out.print(Messages.wielka(Messages.registration()));%></span>
			</td>
			<td class="tdTopLink" align="left">
                                <%
                                if (!auth.zalogowany(request,db_con)) 
                                    out.print("<span  class=\"aTopLink\" onclick=\"linkClick('./auth.jsp"+(css.length()>0?"?"+css:"")+"')\">[] " + Messages.wielka(Messages.logIn()) +"</span>");
                               else
                                    out.print("<span  class=\"aTopLink\" onclick=\"linkClick('./auth.jsp?logout=yes"+(css.length()>0?"&amp;"+css:"")+"')\">["+auth.user(request)+"] " + Messages.wielka(Messages.logOut()) + "</span>");   
                                %>
			</td>
			</tr>
			</table>
		</td>
		</tr>
	</table>
</td>
</tr>
</table>    
    </body>
</html>
