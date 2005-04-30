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

<%@ include file="servletObjects.jsp" %>

<%
        Thread.sleep(1000);
        Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) {
            pl.ltd.bee.Forum f = db_con.getForum();
                if (f!=null) {
                    out.println("<body onload=\"swapIframes();resizeMain();true;\" onresize=\"resizeMain()\">");    
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
                    Commons.setCachingForever(response);
                    out.println("<body style=\"mrgin:0pt;\" class=\"bodyWypowiedz\" >");
                    wp.printJSP(out);
                }
                else 
                    out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorThreadNotExists()+"<br/>");
            } 
            if (field.compareTo("wid") == 0) {
                pl.ltd.bee.Watek w = db_con.getWatek(Integer.decode(request.getParameter(field)).intValue());
                if (w!=null){
                    out.println("<body onload=\"swapIframes();resizeMain();true;\" onresize=\"resizeMain()\">");    
                    w.printJSP(out);
                }
                else 
                    out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorThreadNotExists()+"<br/>");
            } 
                if (field.compareTo("kid") == 0) {
                pl.ltd.bee.Kategoria k = db_con.getKategoria(Integer.decode(request.getParameter(field)).intValue());
                if (k!=null) {
                      out.println("<body onload=\"swapIframes();resizeMain();true;\" onresize=\"resizeMain()\">");    
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
                            out.println("<body onload=\"swapIframes();resizeMain();true;\" onresize=\"resizeMain()\">");    
                            p.printJSP(out);
                        }
                        else
                            out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorSubForumNotExists()+"<br/>");
            }
        }
    %>
    </body>
</html>
