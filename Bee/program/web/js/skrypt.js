function reloadHeader(url){
		if (top.frames[0].location)
                           top.frames[0].location.href = url;
}

function wypowiedzLinkClick(url){
    
    if (top.frames[1].location)
        top.frames[1].location.href = url;
}