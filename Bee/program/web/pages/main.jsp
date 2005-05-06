<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>


<% out.println(Commons.htmlHead("./..","BeeBB :: Content"));%>

<%@ include file="servletObjects.jsp" %>

<%
        Enumeration flds = request.getParameterNames();
        if (!flds.hasMoreElements()) {
            pl.ltd.bee.Forum f = db_con.getForum();
                if (f!=null) {
                    out.println("<body onload=\"swapIframes();resizeMain();setResizeFunction(resizeMain);true;\" >");    
                    Forum.printMainTableJSP(out);
                    f.printJSP(out,request,auth);
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
                    out.println("<body style=\"mrgin:0pt;\" class=\"bodyWypowiedz\" >");
                    if (wp.czyPrywatna()){
                            Commons.setCachingNever(response);
                            Watek w = db_con.getWatekByWypowiedz(wp.getID());
                            if (w != null){
                            User user = auth.getUser(request,db_con);
                            if (user != null)
                            if (user.hasReadWatekRight(w.getID()))
                                wp.printJSP(out);
                            else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                            else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                            } else out.println(Messages.makeError(Messages.wielka(Messages.errorUnknown())));
                        }
                        else{
                            Commons.setCachingForever(response);
                            wp.printJSP(out);
                        }
                }
                else 
                    out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorThreadNotExists()+"<br/>");
            } 
            if (field.compareTo("wid") == 0) {
                pl.ltd.bee.Watek w = db_con.getWatek(Integer.decode(request.getParameter(field)).intValue());
                if (w!=null){
                    Commons.setCachingNever(response);
                    out.println("<body onload=\"swapIframes();resizeWatek();setResizeFunction(resizeWatek);true;\" >");    
                    if (w.czyPrywatny()){
                            User user = auth.getUser(request,db_con);
                            if (user != null)
                            if (user.hasReadWatekRight(w.getID()))
                                w.printJSP(out);
                            else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                            else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                        }
                        else
                            w.printJSP(out);
                }
                else 
                    out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorThreadNotExists()+"<br/>");
            } 
                if (field.compareTo("kid") == 0) {
                pl.ltd.bee.Kategoria k = db_con.getKategoria(Integer.decode(request.getParameter(field)).intValue());
                if (k!=null) {
                      Commons.setCachingNever(response);
                      out.println("<body onload=\"swapIframes();resizeMain();setResizeFunction(resizeMain);true;\" >");    
                      k.printMainTableJSP(out);
                      k.printJSP(out,request,auth);
                      k.printMainTableCloseJSP(out);
                }
                else 
                      out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorCategoryNotExists()+"<br/>");
                } 
                    if (field.compareTo("pid") == 0) {
                        Commons.setCachingNever(response);
                        pl.ltd.bee.Podforum p = db_con.getPodforum(Integer.decode(request.getParameter(field)).intValue());
                        if (p!=null) {
                            out.println("<body onload=\"swapIframes();resizeMain();setResizeFunction(resizeMain);true;\" >");    
                            p.printJSP(out,request,auth);
                        }
                        else
                            out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorSubForumNotExists()+"<br/>");
            }
        }
    %>
    </body>
</html>
