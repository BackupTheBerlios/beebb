function linkClick(url){
    i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 1 : 2;
    
    if (top.frames[i].location) //zabezpieczenie by nie wyszlo zero		
        top.frames[i].location.href = url;
}