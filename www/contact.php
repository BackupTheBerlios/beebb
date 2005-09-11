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
		include('contact_eng.txt');
	} else {
		include('contact_pl.txt');
	}	
?>
		
	<TABLE class="iframe_table" cellspacing="1" cellpadding="1" >
	<TH ALIGN="left" colspan="2"><H3> <?php echo $GREATING; ?> </H3></TH>
	<TR>
		<TD class="th" colspan="2">
		    <?php echo $INTRO; ?>	
		</TD>
	</TR>	
	<TR>
		<TD colspan="2"><br>
		    <?php echo $TEXT1; ?><br><br>	
		</TD>
	</TR>
	<TR>
		<TD colspan="2">
		    &bull; <?php echo $TEXT2; ?>	
		</TD>
	</TR>	
	<TR>
		<TD colspan="2">
		    &bull; <?php echo $TEXT3; ?><br><br>	
		</TD>
	</TR>	
	<TR>
		<TD>
		    <?php echo $TEXT4a; ?>
		</TD>
		<TD>
		    <?php echo $TEXT4b; ?>
		</TD>
	</TR>
	<TR>
		<TD>
		    <?php echo $TEXT5a; ?>
		</TD>
		<TD>
		    <?php echo $TEXT5b; ?>
		</TD>
	</TR>
	<TR>
		<TD>
		    <?php echo $TEXT6a; ?>
		</TD>
		<TD>
		    <?php echo $TEXT6b; ?>
		</TD>
	</TR>
	<TR>
		<TD>
		    <?php echo $TEXT7a; ?>
		</TD>
		<TD>
		    <?php echo $TEXT7b; ?>
		</TD>
	</TR>
    	</TABLE>

</BODY>
</HTML>