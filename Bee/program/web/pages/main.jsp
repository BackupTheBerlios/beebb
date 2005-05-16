<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>
<%@ page session="false" %>
<%@ page errorPage="true"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>


<% 
    out.println(Commons.htmlHead(request,"./..","BeeBB :: Content"));
%>

<%@ include file="servletObjects.jsp" %>

<%            
            
           if (request.getParameter("wpid") != null) {
                pl.ltd.bee.Wypowiedz wp = db_con.getWypowiedz(Integer.decode(request.getParameter("wpid")).intValue());
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
           else
            if (request.getParameter("wid") != null) {
                pl.ltd.bee.Watek w = db_con.getWatek(Integer.decode(request.getParameter("wid")).intValue());
                if (w!=null){
                    Commons.setCachingNever(response);
                    out.println("<body onload=\"swapIframes();resizeWatek();setResizeFunction(resizeWatek);true;\" >");    
                    if (w.czyPrywatny()){
                            User user = auth.getUser(request,db_con);
                            if (user != null)
                            if (user.hasReadWatekRight(w.getID()))
                                w.printJSP(request, out);
                            else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                            else out.println(Messages.makeError(Messages.wielka(Messages.errorPermissionDenied())));
                        }
                        else
                            w.printJSP(request,out);
                }
                else 
                    out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorThreadNotExists()+"<br/>");
            } 
            else
                if (request.getParameter("kid") != null) {
                pl.ltd.bee.Kategoria k = db_con.getKategoria(Integer.decode(request.getParameter("kid")).intValue());
                if (k!=null) {
                      Commons.setCachingNever(response);
                      out.println("<body onload=\"swapIframes();resizeMain();setResizeFunction(resizeMain);true;\" >");    
                      k.printMainTableJSP(request,out);
                      k.printJSP(out,request,auth);
                      k.printMainTableCloseJSP(out);
                }
                else 
                      out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorCategoryNotExists()+"<br/>");
                } 
                else
                    if (request.getParameter("pid") != null) {
                        Commons.setCachingNever(response);
                        pl.ltd.bee.Podforum p = db_con.getPodforum(Integer.decode(request.getParameter("pid")).intValue());
                        if (p!=null) {
                            out.println("<body onload=\"swapIframes();resizeMain();setResizeFunction(resizeMain);true;\" >");    
                            p.printJSP(out,request,auth);
                        }
                        else
                            out.println(Messages.errorDataBaseConnection()+Messages.or()+Messages.errorSubForumNotExists()+"<br/>");
            }
                    else
                    {
                        pl.ltd.bee.Forum f = db_con.getForum();
                        if (f!=null) {
                            out.println("<body onload=\"swapIframes();resizeMain();setResizeFunction(resizeMain);true;\" >");    
                            Forum.printMainTableJSP(out);
                            f.printJSP(out,request,auth);
                            Forum.printMainTableCloseJSP(out);
                        } else {
                                out.println(Messages.errorDataBaseConnection()+"<br/>");
                            }
                    }
    %>
    </body>
</html>
