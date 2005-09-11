<HTML>
<HEAD>
	<TITLE>Bee BB</TITLE>
	<META name="Description" content="">
	<META name="KeyWords" content="BeeBB, Bee, Buletin Board">
	<META http-equiv="Content-Type" content="text/html; charset=iso-8859-2">
	<LINK rel="stylesheet" HREF="../style/style2.css" type="text/css">
	<SCRIPT src="../js/redirection.js" type="text/javascript"/>
</HEAD>
<BODY>
<?php
// set default language	
	if (!(isset($lang))) {
		$lang = eng;
	}

	if ($lang == "eng") { 
		include('team_eng.txt');
	} else {
		include('team_pl.txt');
	}	
?>
		
	<TABLE class="iframe_table" cellspacing="0" cellpadding="0" >
	<TH ALIGN="left" ><H3> <?php echo $MEMBERS; ?> </H3></TH>
	<TR>
		<TD class="th">
			pawelb
		</TD>
	</TR>	
	<TR>
		<TD> <?php echo $M_Pawelb; ?><br><br>
		</TD>
	</TR>
	<TR>
		<TD class="th">
			wilk
		</TD>
	</TR>	
	<TR>
		<TD> <?php echo $M_Wilk; ?><br><br>
		</TD>
	</TR>
	<TR>
		<TD class="th">
			etna
		</TD>
	</TR>	
	<TR>
		<TD> <?php echo $M_Etna; ?><br><br>
		</TD>
	</TR>
	</TABLE>

</BODY>
</HTML>