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
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>

    </head>
<body onload="resizeProfile()" onresize="resizeProfile()">    
<table border="0" id="mainTableProfile" width="100%">
<tr><td>
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

       try{
           konfiguracja.readConfig(application);
       }
       catch(Exception e) {
           out.println(e);
       }
       
        if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print(Messages.errorDataBaseConnection());
            }
        }
        
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
                %>
                <table align="center" class="tableProfile" border="0">
                <tr><th><% out.print(Messages.nick());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getLogin());%></td></tr>
                <tr><th><% out.print(Messages.name());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getImie());%></td></tr>
                <tr><th><% out.print(Messages.subname());%></th><td class="tdProfileField">&nbsp;<% out.print(user.getNazwisko());%></td></tr>
                <tr><th><% out.print(Messages.email(true));%></th><td class="tdProfileField">&nbsp;<% out.print(user.getEmail());%></td></tr>
                <tr><th><% out.print(Messages.number(true)+" Gadu-Gadu");%></th><td class="tdProfileField">&nbsp;<% out.print(user.getGG());%></td></tr>
                <tr><th><% out.print("Jabber");%></th><td class="tdProfileField">&nbsp;<% out.print(user.getJabber());%></td></tr>
                </table>
                <%
            }
    %>
    </td></tr>
    </table>
    </body>
</html>