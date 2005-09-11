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
	<TH ALIGN="left" ><H3> <?php echo $TEAM; ?> </H3></TH>
	<TR>
		<TD class="th">
			<?php echo $NICK; ?>
		</TD>
		<TD class="th">
			<?php echo $NAME; ?>
		</TD>
		<TD class="th">
			<?php echo $MAIL; ?>
		</TD>
		<TD class="th">
			<?php echo $ROLE; ?>
		</TD>
	</TR>	
	<TR>
		<TD> pawelb
		</TD>
		<TD> Pawel Boguszewski
		</TD>
		<TD> pawelb [at] bee.ltd.pl
		</TD>
		<TD> <?php echo $R_PROGRAMMER; ?>
		</TD>
	</TR>
	<TR>
		<TD> wilk
		</TD>
		<TD> Marcin Pruszczyński
		</TD>
		<TD> wilk [at] bee.ltd.pl
		</TD>
		<TD> <?php echo $R_PROGRAMMER."/".$R_MANAGER; ?>
		</TD>
	</TR>
	<TR>
		<TD> etna
		</TD>
		<TD> Anna Hauzer
		</TD>
		<TD> etna [at] bee.ltd.pl
		</TD>
		<TD> <?php echo $R_PROGRAMMER."/".$R_DESIGNER; ?>
		</TD>
	</TR>
	</TABLE>

</BODY>
</HTML>