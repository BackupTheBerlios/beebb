<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Content</title>
        <link rel="stylesheet" href="./../styles/temat.css" type="text/css"/>
    </head>
    <body>
 
<table id="tableHeader" width="100%" cellspacing="0" cellpadding="1" border="0">
    <tr>
	<td width="20%" align="top">
		<img class="beePicture" alt="Bee Sign" src="./../images/Bee_logo.gif" align="top"/>
	</td>
    <td>
    <table width="100%" cellspacing="0" cellpadding="2" border="0">
	<tr>
	  <td class="tdForumHeader" bgcolor="white" align="center">
	    <span class="forumTitle">
               <%out.print(Messages.wielka(Messages.adminPanel()));%>
	    </span>
	  </td>
	 </tr>		
	  <tr>
	   <td id="cellMenu" align="center">
	     <table id="tableMenu" border="0" width="600"> 
		<tr>
		  <td class="tdTopLink"> <a class="aTopLink" href="">
                       [] <%= Messages.wielka(Messages.mainPage()) %></a>
		   </td>
		  <td class="tdTopLink"> <a class="aTopLink" href="./uprawnienia.jsp" target="tresc">
		       [] <%out.print(Messages.wielka(Messages.priviliges()));%>    </a>
		   </td>
		   <td class="tdTopLink"><a class="aTopLink" href="./edycja_podforow.jsp" target="tresc">
		       [] <%out.print(Messages.wielka(Messages.edition()));%> </a>
		   </td>
		   <td class="tdTopLink"><a class="aTopLink" href="./konfiguracja.jsp" target="tresc">
		       [] <%out.print(Messages.wielka(Messages.configuration()));%> </a>
		   </td>
		  </tr>
		<tr>
		 <td class="tdTopLink"><a class="aTopLink" href="./edycja_usunietych.jsp" target="tresc">
		       [] <%out.print(Messages.wielka(Messages.removed()));%> </a>
		  </td>
	         <td class="tdTopLink"><a class="aTopLink" href="./backup.jsp" target="tresc">
			[] <%out.print(Messages.wielka(Messages.backup()));%> </a>
		 </td>
		  <td class="tdTopLink"><a class="aTopLink" href="./moderatorzy.jsp" target="tresc">
			[] <%out.print(Messages.wielka(Messages.moderators()));%> </a>
		 </td>
		   <td class="tdTopLink"><a class="aTopLink" href="./konfiguracja_emo.jsp" target="tresc">
			[] <%out.print(Messages.wielka(Messages.emotikons()));%> </a>
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