function reloadHeader(url){
		if (top.frames[0].location)
                if (top.frames[0].location.pathname.indexOf('header.jsp',0) != -1)
                           top.frames[0].location.href = url;
}

function wypowiedzLinkClick(url){
    
    if (top.frames[1].location)
        top.frames[1].location.href = url;
}
