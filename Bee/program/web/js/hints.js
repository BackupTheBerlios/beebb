var hinting = false;
var hint_text = '';
var hint_object;
var timeout_id;
var hint_height = 100;
var hint_width = 100;


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
	div.style.display = "block";
	div.innerHTML = '<table id="tableHint'+ob.id+'" border="0" width="'+hint_width+'"><tr<td>'+text+'</td></tr></table>';
	div.style.position="absolute";
        var posX = findPosX(ob);
        var posY = findPosY(ob);
	div.style.top = posY + Math.round(ob.offsetHeight / 2) + 10 + 'px';
	div.style.left = posX + 'px';
        if (posX + top.document.getElementById('tableHint'+ob.id).offsetWidth >= screen.width) 
            div.style.left = (screen.width - top.document.getElementById('tableHint'+ob.id).offsetWidth -40) + 'px';
        if (posY + top.document.getElementById('tableHint'+ob.id).offsetHeight >= top.document.body.scrollHeight)
            div.style.top = (top.document.body.scrollHeight - top.document.getElementById('tableHint'+ob.id).offsetHeight -20) + 'px';
	}            
}

function showHint(text,ob,delay,w,h){
        if (timeout_id)
            clearTimeout(timeout_id);
        if (w) hint_width = w; else hint_width = 100;
        if (h) hine_height = h; else hine_height = 100;
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
