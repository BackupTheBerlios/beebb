function linkClick(url){
    
    if (top.frames[1].location) //zabezpieczenie by nie wyszlo zero		
        top.frames[1].location.href = url;
}