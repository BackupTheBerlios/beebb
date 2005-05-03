var hinting = false;
var hint_text = '';
var hint_object;


function findPosX(obj)
{
	var curleft = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curleft += obj.offsetLeft
			obj = obj.offsetParent;
		}
	}
	else if (obj.x)
		curleft += obj.x;
	return curleft;
}

function findPosY(obj)
{
	var curtop = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curtop += obj.offsetTop
			obj = obj.offsetParent;
		}
	}
	else if (obj.y)
		curtop += obj.y;
	return curtop;
}
function popupHint(text, ob){	
	if (hinting)
        if(ob)
        {	
	var div = document.createElement('div');
	document.body.appendChild(div);
	div.id = "hint"+ob.id;
	var c = document.createAttribute('class');
	c.value="hint";
	div.setAttributeNode(c);
//	div.style.visibility = "show";
	div.style.display="block";
	div.innerHTML = text;
	div.style.position="absolute";
	div.style.top = findPosY(ob) + Math.round(ob.offsetHeight / 2) + 'px';
	div.style.left = findPosX(ob) + Math.round(ob.offsetWidth / 2) + 'px';
	}
}

function showHint(text,ob,delay){
	hinting = true;
	hint_object = ob;
	hint_text = text;
	setTimeout('popupHint(hint_text,hint_object)',delay);
}

function hideHint(ob){
	hinting=false;
	//kasuj obiekt
	var div = document.getElementById('hint'+ob.id);
	if (div)
		document.body.removeChild(div);	
}
