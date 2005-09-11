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
		include('faq_eng.txt');
	} else {
		include('faq_pl.txt');
	}	
?>
		
	<TABLE class="iframe_table" cellspacing="1" cellpadding="1" >
	<TH ALIGN="left" colspan="2"><H3> <?php echo $FAQ; ?> </H3></TH>
	<TR>
		<TD class="th" colspan="2" >
		    <?php echo $INTRO; ?>	
		</TD>
	</TR>	
	<TR>
		<TD>
		    <?php ?>	
		</TD>
		<TD>
		    <?php ?>	
		</TD>
	</TR>	
	</TABLE>

</BODY>
</HTML>