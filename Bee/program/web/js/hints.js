var hinting = false;
var hint_text = '';
var hint_object;
var timeout_id;


function findPosXShallow(obj)
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

function findPosYShallow(obj){
	var curtop = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curtop += obj.offsetTop;
			obj = obj.offsetParent;
                        //alert(obj.offsetParent);
		}
	}
	else if (obj.y)
		curtop += obj.y;
	return curtop;
}

function findPosX(obj)
{
	var curtop = findPosXShallow(obj);
        //przejscie po iframkach
        var win = window;
        while (win != top)
        {
            var adres = win.location;
            win = win.parent;
            for(var i=0;i < win.frames.length;i++)
                if (win.frames[i].location == adres)
                {
                    var ramka = win.document.getElementById(win.frames[i].name);
                    if (ramka)
                        curtop += findPosXShallow(ramka);
                }
        }
	return curtop;
}

function findPosY(obj)
{
	var curtop = findPosYShallow(obj);
        //przejscie po iframkach
        var win = window;
        while (win != top)
        {
            var adres = win.location;
            win = win.parent;
            for(var i=0;i < win.frames.length;i++)
                if (win.frames[i].location == adres)
                {
                    var ramka = win.document.getElementById(win.frames[i].name);
                    if (ramka)
                        curtop += findPosYShallow(ramka);
                }
        }
	return curtop;
}
function popupHint(text, ob){	
	if (hinting)
        if(ob)
        {	
	var div = top.document.createElement('div');
	top.document.body.appendChild(div);
	div.id = "hint"+ob.id;
	var c = top.document.createAttribute('class');
	c.value="hint";
	div.setAttributeNode(c);
//	div.style.visibility = "show";
	div.style.display="block";
	div.innerHTML = text;
	div.style.position="absolute";
	div.style.top = findPosY(ob) + Math.round(ob.offsetHeight / 2) + 10 + 'px';
	div.style.left = findPosX(ob) + 'px';
        if (div.style.left + div.offsetWidth > screen.width) 
            div.style.left = (screen.width - div.offsetWidth -10) + 'px';
        if (div.style.top + ob.offsetHeight > screen.height)
            div.style.top = (screen.height - div.offsetHeight -10) + 'px';
	}            
}

function showHint(text,ob,delay){
        hideHint(hint_object);
	hinting = true;
	hint_object = ob;
	hint_text = text;
	timeout_id = setTimeout('popupHint(hint_text,hint_object)',delay);
}

function hideHint(ob){
        if (timeout_id)
            clearTimeout(timeout_id);
        if (ob){
	hinting=false;
	//kasuj obiekt
	var div = top.document.getElementById('hint'+ob.id);
	if (div)
		top.document.body.removeChild(div);	
        }
}
