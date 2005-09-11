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
		include('bb_eng.txt');
	} else {
		include('bb_pl.txt');
	}	
?>
		
	<TABLE class="iframe_table_100" cellspacing="1" cellpadding="1" >
	<TH ALIGN="left" colspan="2"><H3> Bulletin Board </H3></TH>
	<TR>
		<TD class="th" colspan="2" >
		    <?php echo $INTRO; ?>	
		</TD>
	</TR>	
	<TR>
		<TD colspan="2">
		    <?php echo $TEXT1; ?><br><br>
		    
		</TD>
	</TR>
	<TR valign="top">
		<TD>
		    <?php echo $DIC1; ?>
		</TD>
		<TD>
		     <?php echo $DEFINITION1; ?>
		</TD>
	</TR>
	<TR valign="top">
		<TD>
		    <?php echo $DIC2; ?>
		</TD>
		<TD>
		     <?php echo $DEFINITION2; ?>
		</TD>
	</TR>
	<TR>
		<TD colspan="2" class="th"> &nbsp;
		</TD>
	</TR>    
	<TR valign="top">
		<TD colspan="2">
		    <?php echo $TEXT2; ?>
		</TD>
	</TR>    
	<TR valign="top">
		<TD>
		    <?php echo $DIC3; ?>
		</TD>
		<TD>
		     <?php echo $DEFINITION3; ?>
		</TD>
	</TR>
	<TR>
		<TD colspan="2" class="th"> &nbsp;
		</TD>
	</TR>    
	<TR  valign="top">
		<TD colspan="2">
		    <?php echo $TEXT3; ?>
		</TD>
	</TR>    
	<TR  valign="top">
		<TD>
		    <?php echo $DIC4; ?>
		</TD>
		<TD>
		     <?php echo $DEFINITION4; ?>
		</TD>
	</TR>
	<TR  valign="top">
		<TD>
		    <?php echo $DIC5; ?>
		</TD>
		<TD>
		     <?php echo $DEFINITION5; ?>
		</TD>
	</TR>
	<TR>
		<TD colspan="2" class="th"> &nbsp;
		</TD>
	</TR>    
	<TR  valign="top">
		<TD colspan="2">
		    <?php echo $TEXT4; ?>
		</TD>
	</TR>    
	<TR  valign="top">
		<TD>
		    <?php echo $DIC6; ?>
		</TD>
		<TD>
		     <?php echo $DEFINITION6; ?>
		</TD>
	</TR>
	</TABLE>

</BODY>
</HTML>