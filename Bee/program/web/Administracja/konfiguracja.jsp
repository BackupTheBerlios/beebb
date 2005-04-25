<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="pl.ltd.bee.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="Content-Style-Type" content="text/css"/>
        <meta name="Copyright" content="BeeBB Group &copy; 2005" />
        <meta name="Author" content="BeeBB Group" />
        <meta name="description" content="??" />
        <meta name="keywords" content="??" />
        <title>BeeBB :: Dodawanie podforow</title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/> 
    </head>
    <body> 
 
         <jsp:useBean id="conf" scope="request" class="pl.ltd.bee.Config" />
              
     <% 
       try {
             conf.readConfig(application);
           }
       catch(Exception e) {
           out.println(e);
       }
       
       Enumeration fff = request.getParameterNames();
       if (fff.hasMoreElements()) {
           String pom = (String) fff.nextElement();
          if( pom.compareTo("url_forum")==0 )  {  
              conf.setUrlForum(request.getParameter("url_forum"));
             try{
                    conf.saveConfig(application);
                    out.print(Messages.makeInfo(Messages.changeConfigMain()));
                } catch(Exception e) {
                    out.println(e);
                }
           }
         if((pom.compareTo("host")==0)||(pom.compareTo("user")==0)||(pom.compareTo("haslo")==0)|| 
            (pom.compareTo("haslo2")==0)||(pom.compareTo("bd_nazwa")==0)||(pom.compareTo("prefix_tabel")==0)) {
               String h1=request.getParameter("haslo");
               String h2=request.getParameter("haslo2");
              if((h1.compareTo("")==0)||(h2.compareTo("")==0)||(h1.compareTo(h2)!=0))
              {
                  out.print(Messages.makeError(Messages.errorPassNotMatch()));
                  conf.setPassword("");
              }else
               {  conf.setPassword(h1);
                  conf.setHost(request.getParameter("host"));
                  conf.setUser(request.getParameter("user"));
                  conf.setDatabaseName(request.getParameter("bd_nazwa"));
                  conf.setTablesPrefix(request.getParameter("prefix_tabel"));
                try{
                    conf.saveConfig(application);
                    out.print(Messages.makeInfo(Messages.changeConfigDb()));
                   } catch(Exception e) {
                    out.println(e);
                   }
               }
           }
           
          if((pom.compareTo("gosc")==0)||(pom.compareTo("log_in_max_age")==0)||(pom.compareTo("minimum_pass_length")==0)|| 
            (pom.compareTo("new_user_mail_auth")==0)||(pom.compareTo("gosc_id")==0)) {
        
              conf.setGuestAccount(request.getParameter("gosc"));
              conf.setMinimumPassLength(Integer.decode(request.getParameter("minimum_pass_length")).intValue());
               conf.setGuestId(Integer.decode(request.getParameter("gosc_id")).intValue());
              conf.setLogInMaxAge(Integer.decode(request.getParameter("log_in_max_age")).intValue());
              if( (request.getParameter("new_user_mail_auth")).compareTo("tak")==0) conf.setNewUserMailAuth(true);
                else  conf.setNewUserMailAuth(false);
                
                try{
                    conf.saveConfig(application);
                    out.print(Messages.makeInfo(Messages.changeConfigBehave()));
                 } catch(Exception e) {
                    out.println(e);
                 }
               }
          
         if((pom.compareTo("smtp")==0)||(pom.compareTo("mail")==0)||(pom.compareTo("r_temat")==0)|| 
            (pom.compareTo("r_zawart")==0)||(pom.compareTo("z_temat")==0)||(pom.compareTo("z_zawart")==0)) {
        
              conf.setSmtpServer(request.getParameter("smtp"));
              conf.setMailFrom(request.getParameter("mail"));
              conf.setRegistrationSubject(request.getParameter("r_temat"));
              conf.setRegistrationBody(request.getParameter("r_zawart"));
              conf.setForgetSubject(request.getParameter("z_temat"));
              conf.setForgetBody(request.getParameter("z_zawart"));
          
                try{
                    conf.saveConfig(application);
                    out.print(Messages.makeInfo(Messages.changeConfigMail()));
                 } catch(Exception e) {
                    out.println(e);
                 }
           }
           
            if(pom.compareTo("ustawienia")==0){
            /*******************************************************
                   Tu wstawić kod przeładowania bazy   
             **********************************************************/    
            }
      }
        
   %>
   <p align="center">
     <form action="./konfiguracja.jsp" method="post">
        <input type="hidden" name="ustawienia"/> 
        <input type="submit" value=" <%out.println(Messages.wielka(Messages.changeSettings())); %>"/> 
     </form> 
   </p>
     
    
 <table align="center" cellpadding="2" cellspacing="1" border="1">
   <caption> <font size="5" style="bold"> <%out.print(Messages.wielka(Messages.forumConfiguration())); %> </font> </caption>
       
   <tr>  <th colspan="2">  <%out.print(Messages.wielka(Messages.main())); %> </th> </tr>
   <form action="./konfiguracja.jsp" method="post">
        <tr> <td align="center"> <%out.print(Messages.wielka(Messages.urlForum())); %> </td> <td> <input size="100" type="text" name="url_forum" value="<%=conf.URL_FORUM%>"/>  </td> </tr>
        <tr> <td colspan="2" align="center"> <input type="submit" value=" <%out.print(Messages.wielka(Messages.change())); %>"/> </td> </tr>
   </form>
   <tr>  <th colspan="2">  <%out.print(Messages.wielka(Messages.db())); %> </th> </tr>
   <form action="./konfiguracja.jsp" method="post">
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.host())); %> </td><td> <input size="100" type="text" name="host" value="<%=conf.HOST%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.user())); %> </td> <td> <input size="100" type="text" name="user" value="<%=conf.USER%>"/>  </td> </tr>
       <tr>  <td align="center"> <%out.print(Messages.wielka(Messages.password())); %> </td> <td> <input size="100" type="password" name="haslo" value="<%=conf.PASSWORD%>"/>  </td> </tr>
       <tr>  <td align="center"> <%out.print(Messages.wielka(Messages.password()));%> (<%out.print(Messages.oneMoreTime());%>) </td> <td> <input  size="100"type="password" name="haslo2" value="<%=conf.PASSWORD%>"/>  </td> </tr>
       <tr>  <td align="center">  <%out.print(Messages.wielka(Messages.dbName())); %> </td> <td> <input size="100" type="text" name="bd_nazwa" value="<%=conf.DATABASE%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.tablePrefix())); %>  </td> <td> <input size="100" type="text" name="prefix_tabel" value="<%=conf.DATABASE_PREFIX%>"/>  </td> </tr>
       <tr> <td colspan="2" align="center"> <input type="submit" value=" <%out.println(Messages.wielka(Messages.change())); %>"/> </td> </tr>
   </form>
   <tr>  <th colspan="2">  <%out.print(Messages.wielka(Messages.behave())); %> </th> </tr>
   <form action="./konfiguracja.jsp" method="post">
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.guestAccount())); %>  </td> <td> <input  size="100"type="text" name="gosc" value="<%=conf.GUEST%>"/>  </td> </tr>
        <tr> <td align="center"> <%out.print(Messages.wielka(Messages.guestId())); %>  </td> <td> <input  size="100"type="text" name="gosc_id" value="<%=conf.GUEST_ID%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.maxSession())); %>  </td> <td> <input size="100" type="text" name="log_in_max_age" value="<%=conf.LOG_IN_MAX_AGE%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.minPassLength())); %>  </td> <td> <input  size="100"type="text" name="minimum_pass_length" value="<%=conf.MIN_PASSWD%>"/>  </td> </tr>
       <tr> <td align="center">  <%out.print(Messages.wielka(Messages.newUserMail())); %> </td><td> <%out.print(Messages.wielka(Messages.yes())); %> <input type="radio" name="new_user_mail_auth" value="tak" <%if (conf.NEW_USER_MAIL_AUTH){ %>checked="" <%}%>/>  
                                                            <%out.print(Messages.wielka(Messages.no())); %>  <input type="radio" name="new_user_mail_auth" value="nie" <%if (!conf.NEW_USER_MAIL_AUTH){ %>checked="" <%}%> />   </td> </tr>
       <tr> <td colspan="2" align="center"> <input type="submit" value=" <%out.println(Messages.wielka(Messages.change())); %>"/> </td> </tr>
   </form> 
 
   <tr>  <th colspan="2">  <%out.print(Messages.wielka(Messages.mailing())); %> </th> </tr>
   <form action="./konfiguracja.jsp" method="post">
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.smtpServer())); %> </td> <td> <input size="100" type="text" name="smtp" value="<%=conf.SMTP_SERVER%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.mailFrom())); %> </td> <td> <input size="100" type="text" name="mail" value="<%=conf.MAIL_FROM%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.registrationTopic())); %></td> <td> <input size="100"  type="text" name="r_temat" value="<%=conf.REG_MAIL_SUBJECT%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.registrationMessage())); %></td><td> <input size="100" type="text" name="r_zawart" value="<%=conf.REG_MAIL_BODY%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.forgetTopic())); %></td> <td> <input size="100" type="text" name="z_temat" value="<%=conf.FORGET_MAIL_SUBJECT%>"/>  </td> </tr>
       <tr> <td align="center"> <%out.print(Messages.wielka(Messages.forgetMessage())); %></td><td> <input size="100" type="textarea" name="z_zawart" value="<%=conf.FORGET_MAIL_BODY%>"/>  </td> </tr>
       <tr> <td colspan="2" align="center"> <input type="submit" value=" <%out.println(Messages.wielka(Messages.change())); %>"/> </td> </tr>
   </form> 
       
 </table>
 </body>
</html>