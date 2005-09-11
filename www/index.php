<!DOCTYPE html PUBLIC "-//w3c//dtd// html 4.0 transitional//en ">
<HTML>
<HEAD>
	<TITLE>Bee BB</TITLE>
	<META name="Description" content="">
	<META name="KeyWords" content="BeeBB, Bee, Bulletin Board">
	<META http-equiv="Content-Type" content="text/html; charset=iso-8859-2">
	<LINK rel="stylesheet" HREF="style/style1.css" type="text/css">
	<SCRIPT src="js/redirection.js" type="text/javascript"/>
</HEAD>
<BODY>
	<FORM id="form1" name="form1" ACTION="" METHOD="POST" ENCTYPE="text" TARGET="">

<TABLE id="main_table" class="main_table" cellspacing="0" cellpadding="0">
<TR>
	<TD class="main_table_left"> 
	</TD>
	<TD>
<!-- Header Table -->
<TABLE id="header_table" class="header_table" cellspacing="0" cellpadding="0">
<TR>
	<TD rowspan="2" WIDTH="351">
		<A HREF="index.php">
			<IMG SRC="img/logo.png" WIDTH="201" HEIGHT="101" BORDER="0" ALT="BeeBB" ALIGN="left">
		</A>
			<IMG SRC="img/top_bar_white.png"  HEIGHT="51" WIDTH="150" BORDER="0" ALT="" ALIGN="left">
	</TD>
	<TD class="header_table_bar_white" > 
				<H3>Bee Bulletin Board</H3>

<?php
	include('php/fun.php');

	if (isset($iframe)) {
    	    echo "<INPUT name=\"iframe\" type=\"hidden\" value=\"".$iframe."\"/>"; 
	} else {
    	    echo "<INPUT name=\"iframe\" type=\"hidden\" value=\"\"/>"; 
	};
	    
// set default language	
	if (!(isset($lang))) {
		$lang = eng;
	}
	
	$intro = rand(0,4);

	if ($lang == "eng") { 
		include('index_eng.txt');
		print($HEAD[$intro]);
		print("		</TD>
	</TR>
	<TR valign=\"top\">
		<TD class=\"header_table_top_menu_bar\">  
			<INPUT name=\"lang\" type=\"hidden\" value=\"eng\"/>
			<SPAN class=\"link\" onClick=\"document.form1.lang.value='pl'; change(document.location);\">");
		print($FLAG);
		print("</SPAN>&nbsp;"); 
	} else {
		include('index_pl.txt');
		print($HEAD[$intro]);
		print("		</TD>
	</TR>
	<TR valign=\"top\">
		<TD class=\"header_table_top_menu_bar\">  
			<INPUT name=\"lang\" type=\"hidden\" value=\"eng\"/>
			<SPAN class=\"link\" onClick=\"document.form1.lang.value='eng'; change(document.location);\">");
		print($FLAG);
		print("</SPAN>&nbsp;"); 
	}	
?>
			<br>
					<A href="javascript: show('ubar/bb.php','<?php echo $lang; ?>');" title="<?php echo $H_LINK1_T; ?>"><?php echo $H_LINK1; ?></A> 
						&nbsp;|&nbsp;
					<A href="javascript: show('ubar/download.php','<?php echo $lang; ?>');" title="<?php echo $H_LINK2_T; ?>"><?php echo $H_LINK2; ?></A> 
						&nbsp;|&nbsp;
					<A href="javascript: show('ubar/evolution.php','<?php echo $lang; ?>');" title="<?php echo $H_LINK3_T; ?>"><?php echo $H_LINK3; ?></A> 
						&nbsp;|&nbsp;
					<A href="javascript: show('ubar/gallery.php','<?php echo $lang; ?>');" title="<?php echo $H_LINK4_T; ?>"><?php echo $H_LINK4; ?></A> 
						&nbsp;|&nbsp;
					<A href="javascript: show('ubar/story.php','<?php echo $lang; ?>');" title="<?php echo $H_LINK5_T; ?>"><?php echo $H_LINK5; ?></A> 		
						&nbsp;|&nbsp;
					<A href="javascript: show('ubar/feedback.php','<?php echo $lang; ?>');" title="<?php echo $H_LINK6_T; ?>"><?php echo $H_LINK6; ?></A> 
						&nbsp;
						
		</TD>
</TR>
</TABLE>
<!-- /Header Table -->
</TD>
<TD  width="25px" class="main_table_right_top" > &nbsp;&nbsp;&nbsp;
</TD>
</TR>
<TR>
<TD colspan="2">
<!-- Body Table -->
<TABLE id="body_table" class="body_table" cellspacing="0"  cellpadding="0" align="left">
<TR>
	<TD class="body_table_left_menu" >
		<!-- Menu Table -->
		<TABLE class="menu_table" cellspacing="0"  cellpadding="0" >
		<TR>
			<TD nowrap="nowrap" >
				<A class="menu" onClick="show('team/team.php','<?php echo $lang; ?>');"><?php echo $TEAM; ?></A>
			</TD>
		</TR>
		<TR>
			<TD nowrap="nowrap" >
				<A class="sub_menu" onClick="show('team/members.php','<?php echo $lang; ?>');"><?php	echo $MEMBERS; ?></A>
			</TD>
		</TR>
		<TR>
			<TD nowrap="nowrap" >
				<A class="menu" onClick="show('project/project.php','<?php echo $lang; ?>');"><?php	echo $PROJECT; ?></A>
			</TD>
		</TR>
		<TR>
			<TD nowrap="nowrap" >
				<A class="sub_menu" onClick="go_http('http://bee.ltd.pl');"><?php echo $DEMO; ?></A>
			</TD>
		</TR>
		<TR>
			<TD nowrap="nowrap" >
				<A class="sub_menu" onClick="show('project/docs.php','<?php echo $lang; ?>');"><?php echo $DOCS; ?></A>
			</TD>
		</TR>
		<TR>
			<TD nowrap="nowrap" >
				<A class="sub_menu" onClick="go_http('http://bee.ltd.pl');"><?php echo $CVS; ?></A>
			</TD>
		</TR>
		<TR>
			<TD nowrap="nowrap" >
				<A class="sub_menu" onClick="go_http('http://developer.berlios.de/bugs/?group_id=4047');"><?php echo $BUGS; ?></A>
			</TD>
		</TR>
		<TR>
			<TD nowrap="nowrap" >
				<A class="menu" onClick="go_http('http://developer.berlios.de');"><?php	echo $BERLIOS; ?></A>
			</TD>
		</TR>
		<TR>
			<TD >
				<IMG src="img/menu-bottom.gif" height="65" width="154" align="left"><BR>
			</TD>
		</TR>
		</TABLE>
		<!-- /Menu Table -->	
	</TD>
	<TD valign="top" class="body_table_iframe" rowspan="2">
		<!--  Iframe Table -->
		<TABLE id="iframe_table" class="iframe_table" cellspacing="0" cellpadding="3">
		<TR>
			<TD> 
				<IFRAME id="iframe1" name="iframe1" class="body_table_iframe" height="300" 
				<?php				
				
				    if ((!(isset($iframe)))||($iframe=="")) {
					echo "src=\"intro.php?lang=".$lang."\"></IFRAME>";
				    } else {	
					echo "src=\"".$iframe."\">".$iframe."</IFRAME>";
				    }
				?>
							<!-- Text space -->
	<BR>
				
				<BR>				
				<!-- /Text space -->
			</TD>
		</TR>
		</TABLE>
		<!-- /Iframe Table -->	
	</TD>	
</TR>
</TABLE>
<!-- /Body Table -->
</TD><TD class="main_table_right_middle" > &nbsp;&nbsp;&nbsp;
</TD>
</TR>
<TR>
<TD class="main_table_left">
</TD>
<TD>
<!-- Bottom Table -->
<TABLE id="bottom_table" class="bottom_table" cellspacing="0" cellpadding="0">
<TR>
	<TD width="85px">
	</TD
	<TD class="bottom_table_bar_gray_middle">
		<A href="javascript: show('lbar/technology.php','<?php echo $lang; ?>');" title=<?php	echo "\"".$B_LINK1_T."\">".$B_LINK1; ?></A> 
		&nbsp;|&nbsp;
		<A href="javascript: show('lbar/faq.php','<?php echo $lang; ?>');" title=<?php	echo "\"".$B_LINK2_T."\">".$B_LINK2; ?></A> 
		&nbsp;|&nbsp;
		<A href="javascript: show('lbar/standards.php','<?php echo $lang; ?>');" title=<?php	echo "\"".$B_LINK3_T."\">".$B_LINK3; ?></A> 
		&nbsp;|&nbsp;
		<A href="javascript: show('temp/temp.html','<?php echo $lang; ?>');" title=<?php	echo "\"".$B_LINK4_T."\">".$B_LINK4; ?></A> 
		&nbsp;|&nbsp;
		<A href="javascript: show('lbar/solutions.php','<?php echo $lang; ?>');" title=<?php	echo "\"".$B_LINK5_T."\">".$B_LINK5; ?></A> 		
	</TD>
	<TD class="bottom_table_bar_gray_right" >
		<IMG SRC="img/bottom_flip.png" WIDTH="15" HEIGHT="29" BORDER="0" ALT="" align="right">
	</TD>
</TR>
<TR>
	<TD class="bottom_table_counter">

<?php 

	/* counter file */

	$counter_file = "counter";
	$visits = file_get_contents($counter_file);
	$fd = fopen($counter_file, "w+");
	fwrite($fd, $visits+1);
	fclose($fd);		
	
	echo $COUNTER.$visits; 
?>			
	</TD>
	<TD class="bottom_table_info"><br> 

<?php 
	echo $LICENSE_INFO;
	echo "<A HREF=\"javascript: go_http('".$LICENSE_LINK."')\">".$LICENSE."</A>";
	echo "<br>";
	echo $LAST_MODIF;
	echo date( "d M Y [ H:i:s ].", filemtime($_SERVER["SCRIPT_FILENAME"])); 
	echo "<br>";
	echo $CONTACT."<A HREF=\"javascript: show('contact.php');\">".$CONTACT_LINK."</A>";
 ?>		
 	<br><br>
	</TD>
	<TD>
	</TD>
</TR>
</TABLE>
<!-- /Bottom Table -->
</TD><TD  width="25px" class="main_table_right_bottom" > &nbsp;&nbsp;&nbsp;
</TD>
</TR>
</TABLE>
<!-- /Main Table -->
</FORM>
</BODY>
</HTML>