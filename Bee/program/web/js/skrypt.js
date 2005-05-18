function reloadHeader(url){
		if (top.frames[0].location)
                if (top.frames[0].location.pathname.indexOf('header.jsp',0) != -1)
                           top.frames[0].location.href = url;
}

function wypowiedzLinkClick(url){
    i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
    
    if (top.frames[i].location)
        top.frames[i].location.href = url;
}


function hrefClick(url){
    if (!(top.document.getElementById('frameTresc_1'))){
            top.location = url;
    }
    else 
    {
    var i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 1 : 2;

    if (top.frames[i].location)
        top.frames[i].location.href = url;
    }
}

function swapIframes(){
    if (!(top.document.getElementById('frameTresc_1'))) 
            return;
    if (top.document.getElementById('frameTresc_1').style.display == 'none')
    {
        top.document.getElementById('frameTresc_1').style.display = 'block';
        top.document.getElementById('frameTresc_2').style.display = 'none';
    }
    else
    {
        top.document.getElementById('frameTresc_2').style.display = 'block';
        top.document.getElementById('frameTresc_1').style.display = 'none';
    }

    //zmienie cele forms'ow na stronie
    changeTargetForms();
}

function changeTargetForms(){
var cel = '';
if (top.document.getElementById('frameTresc_1')) 
    cel = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_1' : 'frameTresc_2';

if (document.forms){
    for(var i=0; i<document.forms.length; i++){
            document.forms[i].target=cel;
            }
}
}


function addEmoticon(tag, textarea){
    document.getElementById(textarea).value += tag;
    document.getElementById(textarea).focus();
}


function setResizeFunction(fun){
    window.onresize = fun;
}


function debugIframes(){
 var ramka = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
 if (top.frames[0].location)
     if (top.frames[0].location.pathname.indexOf('header.jsp',0) != -1)
        top.frames[0].document.getElementById('forumTitle').innerHTML=ramka+' ('+top.frames[1].location+' # '+top.frames[2].location;
 setTimeout('debugIframes()',500);
}


function topLink(url){
    top.window.location.href = url;
}


//___________________ Ciasteczka

function getCookieVal (offset) {  
    var endstr = document.cookie.indexOf (";", offset);  
    if (endstr == -1)    
    endstr = document.cookie.length;  
    return unescape(document.cookie.substring(offset, endstr));
}

function getCookie (name) {  
    var arg = name + "=";  
    var alen = arg.length;  
    var clen = document.cookie.length;  
    var i = 0;  
    while (i < clen) {    
        var j = i + alen;    
        if (document.cookie.substring(i, j) == arg)      
            return getCookieVal(j);    
        i = document.cookie.indexOf(" ", i) + 1;    
        if (i == 0) break;   
    }  
    return null;
}


/** Metoda sprawdza czy ktos jest zalogowany na podstawie sprawdzenia czy ustawione jest ciasteczko o podanej nazwei*/
function isLogin(user_cookie){
    var wartosc = getCookie(user_cookie)
    if (wartosc) return true;
        else return false;
}