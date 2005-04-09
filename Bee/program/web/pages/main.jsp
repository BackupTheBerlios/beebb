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
        <script type="text/javascript" src="./../js/skrypt.js"></script>
        <script type="text/javascript" src="./../js/iframe_resize.js"></script>

    </head>
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
                out.print(e);
            }
        }
        
        Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) {
            pl.ltd.bee.Forum f = db_con.getForum();
                if (f!=null) {
                    out.println("<body onload=\"reloadHeader('./header.jsp');resizeMain()\" onresize=\"resizeMain()\">");    
                    Forum.printMainTableJSP(out);
                    f.printJSP(out);
                    Forum.printMainTableCloseJSP(out);
                } else {
                    out.println(Messages.errorDataBaseConnection()+"<br/>");
                }
        } else {
            String field = (String) flds.nextElement();
            String redirectURL;
           if (field.compareTo("wpid") == 0) {
                pl.ltd.bee.Wypowiedz wp = db_con.getWypowiedz(Integer.decode(request.getParameter(field)).intValue());
                if (wp!=null) {
                    out.println("<body class=\"bodyWypowiedz\" >");
                    wp.printJSP(out);
                }
                else 
                    out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorThreadNotExists()+"<br/>");
            } 
            if (field.compareTo("wid") == 0) {
                pl.ltd.bee.Watek w = db_con.getWatek(Integer.decode(request.getParameter(field)).intValue());
                if (w!=null){
                    out.println("<body onload=\"reloadHeader('./header.jsp');resizeMain()\" onresize=\"resizeMain()\">");    
                    w.printJSP(out);
                }
                else 
                    out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorThreadNotExists()+"<br/>");
            } 
                if (field.compareTo("kid") == 0) {
                pl.ltd.bee.Kategoria k = db_con.getKategoria(Integer.decode(request.getParameter(field)).intValue());
                if (k!=null) {
                      out.println("<body onload=\"reloadHeader('./header.jsp');resizeMain()\" onresize=\"resizeMain()\">");    
                      k.printMainTableJSP(out);
                      k.printJSP(out);
                      k.printMainTableCloseJSP(out);
                }
                else 
                      out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorCategoryNotExists()+"<br/>");
                } 
                    if (field.compareTo("pid") == 0) {
                        pl.ltd.bee.Podforum p = db_con.getPodforum(Integer.decode(request.getParameter(field)).intValue());
                        if (p!=null) {
                            out.println("<body onload=\"reloadHeader('./header.jsp');resizeMain()\" onresize=\"resizeMain()\">");    
                            p.printJSP(out);
                        }
                        else
                            out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorSubForumNotExists()+"<br/>");
            }
        }
    %>
    </body>
</html>
