<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
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
        <title>BeeBB.com :: Content</title>
        <link rel="stylesheet" href="./styles/temat.css" type="text/css"/>
    </head>
    <body>

    <A href="./Administracja/index.htm" target=main>Panel Administratora</A>
    
    <jsp:useBean id="auth" scope="session" class="pl.ltd.bee.Autoryzator" />
    <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
 
       <%

        if (!db_con.isConnected()) {
            try {
            db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
            db_con.setTablePrefix(Config.DATABASE_PREFIX);
            } catch (Exception e) {
                out.print("Blad polaczenia z baza!");
                out.print(e);
            }
        }
  
        if (!auth.zalogowany()) 
            out.print("[ <a href=\"pages/auth.jsp\">Zaloguj</a> ]");
        else
            out.print("Zalogowany: " + auth.user() + " [ <a href=\"pages/auth.jsp?logout=yes\">Wyloguj</a> ]");   
    
        out.print("<BR><BR><BR>");
        
        Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) {
            pl.ltd.bee.Forum f = db_con.getForum();
                if (f!=null) {
                    //out.print(f.printJSP());
                    Forum.printMainTableJSP(out);
                    f.printJSP(out);
                    Forum.printMainTableCloseJSP(out);
                } else {
                    out.println("Brak polaczenia z baza!<br>");
                }
        } else {
            String field = (String) flds.nextElement();
            String redirectURL;
            
            if (field.compareTo("wid") == 0) {
                pl.ltd.bee.Watek w = db_con.getWatek(Integer.decode(request.getParameter(field)).intValue());
                if (w!=null)
                    out.print(w.printJSP());
                else 
                    out.println("Brak polaczenia z baza!/Brak takiego watku!<br>");
            } else {
                if (field.compareTo("kid") == 0) {
                pl.ltd.bee.Kategoria k = db_con.getKategoria(Integer.decode(request.getParameter(field)).intValue());
                if (k!=null) 
                {
                    //out.print(k.printJSP());
                      Forum.printMainTableJSP(out);
                      k.printJSP(out);
                      Forum.printMainTableCloseJSP(out);
                      }
                     else 
                        out.println("Brak polaczenia z baza!/Brak takiej kategorii!<br>");
                } else {
                    if (field.compareTo("pid") == 0) {
                        pl.ltd.bee.Podforum p = db_con.getPodforum(Integer.decode(request.getParameter(field)).intValue());
                        if (p!=null)
                            out.print(p.printJSP());
                        else
                            out.println("Brak polaczenia z baza!/Brak takiego podforum!<br>");
                        }

                }
            }
        }
    %>
    </body>
</html>
