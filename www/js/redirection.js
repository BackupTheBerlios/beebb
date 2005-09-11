function change(action) {

	if (document.getElementById("iframe1")) {
	    src = "" + document.getElementById("iframe1").src;
	} else {
	    src = document.all['iframe1'].src;
	}
	t_array = src.split(/=/);
	src = t_array[0]+'='+document.form1.lang.value;

	    document.form1.iframe.value=src;
	    document.form1.action="";
	    document.form1.submit();
	
	

}

function go(action, target) {

	document.form1.action = action;
	document.form1.target = target;
	document.form1.submit();
	
}
  

function go_http (url) {

	this.open(url);	

}
      
function show(src,lang) {

	
	if (document.getElementById("iframe1")) {
		document.getElementById("iframe1").src = src+'?lang='+lang;
	} else
		document.all['iframe1'].src = src+'?lang='+lang;	

	
}
