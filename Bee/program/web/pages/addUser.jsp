<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page language="java" import="pl.ltd.bee.*"%>

<html>
    <head><title>ADD user</title></head>
    <body>
        <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />
        <% Enumeration flds = request.getParameterNames();
            if (!db_con.isConnected()) {
            try {
            db_con.connect("localhost","Bee","pawel",".l");
            db_con.setTablePrefix("Bee");
            } catch (Exception e) {
                out.print("Blad polaczenia z baza!");
            }
        } %>
        
            <%
            //Straszny kawałek kodu, ale nie mam innego pomysłu
            boolean ok = true;
            String nickname=request.getParameter("user");
            if (nickname!=null) {
                if (db_con.getUser(nickname)!=null) ok=false;
                
                if (nickname.compareTo("")==0) ok=false;
            } else { ok=false; }
            String haslo1=request.getParameter("haslo1");
            String haslo2=request.getParameter("haslo2");
            if (haslo1!=null && haslo2!=null) {
                if (haslo1.length() < 5)ok=false;
                if (haslo1.compareTo(haslo2)!=0) ok=false;
            } else ok=false;
            String email=request.getParameter("email");
            if (email==null) ok=false;
            else
                if (email.compareTo("")==0) ok=false;
                
            String nazwisko = request.getParameter("nazwisko");
            if (nazwisko==null) nazwisko="";
            String imie = request.getParameter("imie");
            if (imie==null) imie="";
            String gg = request.getParameter("gg");
            if (gg==null) gg="";
            String jabber = request.getParameter("jabber");
            if (jabber==null) jabber="";
            
            if (ok) {
                if(!db_con.insertUser(nickname,imie,nazwisko,email,gg,jabber,haslo1))
                    out.println("Dupa Blada<BR>");
                else
                    out.println("Uzytkownik: " + nickname + " zostal dodany<BR>"); 
            } else {
        %>
        <form method="POST" action="addUser.jsp">
            Formularz <br><br>
            <%
            ok = true;
            if (nickname!=null) {
                if (db_con.getUser(nickname)!=null) {
                    out.println("<font color=\"red\">Uzytkownik " + nickname + " juz istnieje!<br></font>");
                    ok=false;
                }
                if (nickname.compareTo("")==0) { ok=false;
                    out.println("<font color=\"red\">Pole jest wymagane!<br></font>"); }
            } else { ok=false; nickname=""; } %>    
            <b>nickname*:</b>  <input type="text" size="20" name="user" value="<%out.print(nickname);%>"><br>
            <% 
            if (haslo1!=null && haslo2!=null) {
                if (haslo1.length() < 5) { ok=false;
                    out.println("<font color=\"red\">Haslo musi miec przynajmniej 5 znakow<br></font>"); }
                
                if (haslo1.compareTo(haslo2)!=0) { ok=false;
                    out.println("<font color=\"red\">Hasla s ie nie zgadzaja<br></font>"); }
                
            } else { ok=false; } %>
            <b>haslo*:</b>  <input type="password" size="20" name="haslo1"><br>
            <b>haslo jeszcze raz*:</b>  <input type="password" size="20" name="haslo2"><br>
            imie:  <input type="text" size="30" name="imie" value="<%out.print(imie);%>"><br>
            nazwisko:  <input type="text" size="30" name="nazwisko" value="<%out.print(nazwisko);%>"><br>
            
            <%
            if (email==null) {
                ok=false;
                email="";
            } else {
                if (email.compareTo("")==0) { ok=false;
                    out.println("<font color=\"red\">Pole jest wymagane!<br></font>"); }
                    
            }
            
            %>
            <b>email*:</b>  <input type="text" size="20" name="email" value="<%out.print(email);%>"><br>
            gg:  <input type="text" size="10" name="gg" value="<%out.print(gg);%>"><br>
            jabber:  <input type="text" size="20" name="jabber" value="<%out.print(jabber);%>"><br>
            
            <input type="submit" name="submit" value="submit"/>
            <BR><BR>
            * - pola wymagane
        </form>
        <% }%>
        <br><br>
        <%-- 
        //if(!db_con.insertUser("pawel","sd","sd","ss","234","pawelB@ddd","dupa"))
        //out.println("Dupa Blada<BR>");
        --%>
    </body>
</html>
