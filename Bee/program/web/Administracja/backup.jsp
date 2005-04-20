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
        <title>BeeBB :: Backup </title>
        <link rel="stylesheet" href="../styles/temat.css" type="text/css"/> 
    </head>
    <body> 
         <jsp:useBean id="db_con" scope="session" class="pl.ltd.bee.DataBase" />


         
           <% if (!db_con.isConnected()) {
            try {
                db_con.connect(Config.HOST,Config.DATABASE,Config.USER,Config.PASSWORD);
                db_con.setTablePrefix(Config.DATABASE_PREFIX);
                } catch (Exception e) {
                   out.print(Messages.makeError(Messages.errorDataBaseConnection()));
                out.print(e);
                }
            } 
           
             String napis="";
            Enumeration f = request.getParameterNames();
             if (f.hasMoreElements()) {
                  napis = (String) f.nextElement();
               if( napis.compareTo("zrob_backup")==0 ) {
                   Backup pom=new Backup(db_con);
                   pom.zrobBackup(application); %> 
               <p align="center"> <a href="./backup.xml"> Pobierz plik z backupem. </a> </p>
               <%           
              } 
               if( (napis.compareTo("plik")==0)||(napis.compareTo("odtworz")==0)) {
             
                 out.print((String) request.getParameter("plik"));
                
              }  
                  
             }
           %>
         

     <form  action="./backup.jsp" method="post">
       <table align="center" cellpadding="2" cellspacing="1" border="1">
          <tr>  <th colspan="1"> <%out.println(Messages.wielka(Messages.backup())); %> </th>  </tr>
          <tr> <td> <input type="hidden" name="zrob_backup"/> 
                    <input size="40" type="submit" value="<%out.println(Messages.wielka(Messages.doBackup())); %>"/>
          </td> </tr>
        </table>
     </form>
  
   <br/>
     <form  action="./backup.jsp" method="post">
      <table align="center" cellpadding="2" cellspacing="1" border="1">
       <tr>  <th colspan="2"> <%out.println(Messages.wielka(Messages.chooseFile())); %> </th>  </tr>
       <tr> 
           <td> <input size="70" type="file" name="plik" />  </td> 
           <td> 
               <input type="submit" value="<%out.println(Messages.wielka(Messages.recreateDB())); %>"/> </td>
       </tr>
      </table>
    </form>

         
    </body>
</html>