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
    if (!(top.document.getElementById('frameTresc_1'))) 
            top.location = url;
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
}

function addEmoticon(tag, textarea){
    document.getElementById(textarea).value += tag;
    document.getElementById(textarea).focus();
}


function setResizeFunction(fun){
    window.onresize = fun;
}