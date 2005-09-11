<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
	<TITLE>intro</TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=iso-8859-2">
	<LINK rel="stylesheet" HREF="style/style2.css" type="text/css">
	<SCRIPT src="js/redirection.js" type="text/javascript"/>
</HEAD>
<BODY>

<?php
// set default language	
	if (!(isset($lang))) {
		$lang = eng;
	}

	if ($lang == "eng") { 
		include('intro_eng.txt');
	} else {
		include('intro_pl.txt');
	};
?>	 

	<H3>Bee Bulletin Board</H3>
	<TABLE>
	<TR>
		<TD>
			<?php print($INTRO_TEXT); ?>
		</TD>
	</TR>
	</TABLE>
</BODY>
</HTML>