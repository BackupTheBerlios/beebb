
function resizeMain()
{
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
                
		//poprawienie gornej ramki
		//if (top.frames[0].location.pathname.indexOf("header.jsp",0) != -1)
                if (top.frames[0].document.getElementById('tableHeader'))
		{
			top.document.getElementById('frameHead').height = top.frames[0].document.getElementById('tableHeader').offsetHeight + 30;
			top.document.getElementById('cellHead').height = top.frames[0].document.getElementById('tableHeader').offsetHeight + 40;
                        //top.document.getElementById('frameTresc').
		}
                //else
                //{
                        
                //}
		
		//poprawianie dolnej ramki
		if (top.frames[i].document.getElementById('tableForum')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableForum').offsetHeight + top.frames[i].document.getElementById('textNadForum').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableForum').offsetHeight + top.frames[i].document.getElementById('textNadForum').offsetHeight + 40;
		}
		
		if (top.frames[i].document.getElementById('tablePodforum')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tablePodforum').offsetHeight + top.frames[i].document.getElementById('textNadPodforum').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tablePodforum').offsetHeight + top.frames[i].document.getElementById('textNadPodforum').offsetHeight + 40;
		}

		if (top.frames[i].document.getElementById('tableWatek')) //zabezpieczenie by nie wyszlo zero		
		{
                	komorki = document.getElementsByTagName('td');
                	wypowiedzi=[];
                	k=0;
                	for(j=0;j<komorki.length;j++)
        		{
            			if  (komorki[j].className == 'tdWypowiedzBox')
                		{
                        		wypowiedzi[k] = komorki[j];
                                	k++;
            			}
            		}
                        var opera = navigator.userAgent.toLowerCase().indexOf("opera",0) != -1;
                        for(f=0;f<frames.length;f++)
                        {
                            if (!opera)
                                wypowiedzi[f].height = frames[f].document.getElementById('tableWypowiedz').offsetHeight + 10;
                            else
                                wypowiedzi[f].height = frames[f].document.getElementById('tableWypowiedz').offsetHeight + 25;
                        }
                    	//No to teraz poprawa zewnetrznej ramki   
                    	if (top != window) //zabezpieczenie jesli nie jestesmy potomkiem
                        if (document.getElementById('tableWatek'))
                    	{
                		top.document.getElementById(frameTresc).height = document.getElementById('tableWatek').offsetHeight + document.getElementById('textNadWatkiem').offsetHeight + 30;
                                top.document.getElementById('cellTresc').height = document.getElementById('tableWatek').offsetHeight + document.getElementById('textNadWatkiem').offsetHeight + 40;
                	}
		}
}

function resizeDodajW(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('tableDodajW')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableDodajW').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableDodajW').offsetHeight + 40;
		}
}


function resizeAddUser(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('tableAddUser')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableAddUser').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableAddUser').offsetHeight + 40;
		}
}

function resizeProfile(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('mainTableProfile')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('mainTableProfile').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('mainTableProfile').offsetHeight + 40;
		}
}


function resizeAuth(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('tableAuth')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableAuth').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableAuth').offsetHeight + 40;
		}
}

function resizeForgetPass(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('tableForgetPass')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableForgetPass').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableForgetPass').offsetHeight + 40;
		}
}

function resizeForget(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('tableForget')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableForget').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableForget').offsetHeight + 40;
		}
}

function resizeNewUser(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('tableNewUser')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableNewUser').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableNewUser').offsetHeight + 40;
		}
}

function resizeSearch(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('tableSearch')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableSearch').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableSearch').offsetHeight + 40;
		}
}

function tableMorderating(){
                i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
		if (top.frames[i].document.getElementById('tableMorderating')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = top.frames[i].document.getElementById('tableMorderating').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[i].document.getElementById('tableMorderating').offsetHeight + 40;
		}
}

