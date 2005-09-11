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
/*
	if ($lang == "eng") { 
		include('docs_eng.txt');
	} else {
		include('docs_pl.txt');
	}
*/		
?>
		
	<TABLE class="iframe_table" cellspacing="0" cellpadding="0" >
	<TH ALIGN="left" ><H3> <?php echo $DOCS; ?> </H3></TH>
	<TR>
<!--		<TD class="th">
			<?php echo $FILE; ?>
		</TD>
		<TD class="th">
			<?php echo $DESC; ?>
		</TD>
-->		<TD>
		<ul>
		<li>RING BBSow- http://x.webring.com/navcgi?ring=bbs&list
		<li> spis najwiekszych for : http://www.big-boards.com/<br><br>
		<li>txtBB
		<li>phpBB
		<li>Invision Power Board
		<li>vBulletin</li>
		<li>IdealBB</li>
		<li>PunBB</li>
		<li>YaBB</li>
		<li>YaBB SE</li>
		<li>Webplus</li>
		<li>free4web</li>
		</ul>
		</TD>
		
	</TR>	
	</TABLE>

</BODY>
</HTML>