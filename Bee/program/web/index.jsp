<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>
<html>
    <head><title>JSP Page</title></head>
    <body>
    <%! pl.ltd.bee.DataBase db_con = null; %>
    
        <%
        if (db_con == null) {
            db_con = new pl.ltd.bee.DataBase();
            db_con.connect("wilk.waw.pl","Bee","pawelb","asd");
            db_con.setTablePrefix("Bee");
        }
  
        Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) {
           out.print("Nic");
        } else {
            String field = (String) flds.nextElement();
            String redirectURL;
            
            if (field.compareTo("wid") == 0) {
                
                pl.ltd.bee.Watek w = db_con.getWatek(Integer.decode(request.getParameter(field)).intValue());
                if (w!=null) {
                    out.print(w.printJSP());
                } else {
                    out.println("Brak polaczenia z baza!/Brak takiego watku!<br>");
                }
                
            }      
        }
    %>
    </body>
</html>
