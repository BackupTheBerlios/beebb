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
		include('docs_eng.txt');
	} else {
		include('docs_pl.txt');
	}	
?>
		
	<TABLE class="iframe_table" cellspacing="0" cellpadding="0" >
	<TH ALIGN="left" ><H3> <?php echo $DOCS; ?> </H3></TH>
	<TR>
		<TD class="th">
			<?php echo $FILE; ?>
		</TD>
		<TD class="th">
			<?php echo $DESC; ?>
		</TD>
	</TR>	
	<?php
		foreach ($FILES as $entry) {
			echo "<TR>
						<TD>
							<A HREF=\"".$entry[1]."\" TARGET=\"_new\">".$entry[0]."</A>	
						</TD>
						<TD>
							".$entry[2]."
						</TD>
					</TR>";			
		};
	?>
	</TABLE>

</BODY>
</HTML>