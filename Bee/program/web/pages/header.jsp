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
        <script type="text/javascript" src="./../js/header.js"></script>
    </head>
    <body>
<%
       DataBase db_con;
       Object o = application.getAttribute(Config.APPLICATION_OBJECT_DATABASE);
       if (o == null)
       {
           DataBase d = new DataBase();
           application.setAttribute(Config.APPLICATION_OBJECT_DATABASE,d);
           db_con = d;
       }
       else db_con = (DataBase)o;
       
       Config konfiguracja;
       Object ob = application.getAttribute(Config.APPLICATION_OBJECT_CONFIG);
       if (ob == null)
       {
           Config c = new Config();
           application.setAttribute(Config.APPLICATION_OBJECT_CONFIG,c);
           konfiguracja = c;
       }
       else konfiguracja = (Config)ob;
       
       Autoryzator auth;
       Object obj = application.getAttribute(Config.APPLICATION_OBJECT_AUTORYZACJA);
       if (obj == null)
       {
           Autoryzator a = new Autoryzator();
           application.setAttribute(Config.APPLICATION_OBJECT_AUTORYZACJA,a);
           auth = a;
       }
       else auth = (Autoryzator)obj;
%>    
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
				Przykładowy wygład forum Bee. W tym miejscu bedzie mozna wpisac co tylko sobie czlowiek zapragnie. W szczegolnosci bedzie mozna napisac cos co nie ma sensu i bedzie bardzo dlugie, a nawet jesli bedzie krotkie to nadal bedzie rownie dobrym kawalkiem tekstu.
			</span>
			</td>
		</tr>		
		<tr>
		<td id="cellMenu" align="center">
			<table id="tableMenu" border="0" width="600"> 
			<tr>
			<td class="tdTopLink"><a class="aTopLink" href="">
				[] FAQ
				</a>
			</td>
			<td class="tdTopLink"><a class="aTopLink" href="">
				[] Szukaj
				</a>
			</td>
			<td class="tdTopLink"><a class="aTopLink" href="">
				[] Użytkownicy
				</a>
			</td>
			<td class="tdTopLink"><a class="aTopLink" href="">
				[] Grupy
				</a>
			</td>
			</tr>
			<tr>
			<td class="tdTopLink"><a class="aTopLink" href="">
				[] Profil
				</a>
			</td>
			<td class="tdTopLink">
				<a class="aTopLink" href="./Administracja/index.htm" target="_blank">[] Panel Administracyjny</a>
			</td>
			<td class="tdTopLink">
				<a  class="aTopLink" href="" onClick="linkClick('./addUser.jsp')">[] Rejestracja</a>
			</td>
			<td class="tdTopLink">
                                <%
                                if (!auth.zalogowany(request,db_con)) 
                                    out.print("<a  class=\"aTopLink\" href=\"\" onClick=\"linkClick('./auth.jsp')\">[] Zaloguj</a>");
                               else
                                    out.print("<a  class=\"aTopLink\" href=\"\" onClick=\"linkClick('./auth.jsp?logout=yes')\">["+auth.user(request)+"] Wyloguj</a>");   
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
