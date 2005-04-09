
function resizeMain()
{
		//poprawienie gornej ramki
		if (top.frames[0].location.pathname.indexOf("header.jsp",0) != -1)
		{
			top.document.getElementById('frameHead').height = top.frames[0].document.getElementById('tableHeader').offsetHeight + 30;
			top.document.getElementById('cellHead').height = top.frames[0].document.getElementById('tableHeader').offsetHeight + 40;
		}
		
		//poprawianie dolnej ramki
		if (top.frames[1].document.getElementById('tableForum')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById('frameTresc').height = top.frames[1].document.getElementById('tableForum').offsetHeight +30;
			top.document.getElementById('cellTresc').height = top.frames[1].document.getElementById('tableForum').offsetHeight +40;
		}
		
		if (top.frames[1].document.getElementById('tablePodforum')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById('frameTresc').height = top.frames[1].document.getElementById('tablePodforum').offsetHeight + top.frames[1].document.getElementById('textNadPodforum').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[1].document.getElementById('tablePodforum').offsetHeight + top.frames[1].document.getElementById('textNadPodforum').offsetHeight + 40;
		}

		if (top.frames[1].document.getElementById('tableWatek')) //zabezpieczenie by nie wyszlo zero		
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
                        for(f=0;f<frames.length;f++)
                        {
                            wypowiedzi[f].height = frames[f].document.getElementById('tableWypowiedz').offsetHeight;
                        }
                    	//No to teraz poprawa zewnetrznej ramki   
                    	if (top != window) //zabezpieczenie jesli nie jestesmy potomkiem
                    	{
                		top.document.getElementById('frameTresc').height = document.getElementById('tableWatek').offsetHeight + document.getElementById('textNadWatkiem').offsetHeight + 30;
                                top.document.getElementById('cellTresc').height = document.getElementById('tableWatek').offsetHeight + document.getElementById('textNadWatkiem').offsetHeight + 40;
                	}
		}
}

function resizeDodajW(){
		if (top.frames[1].document.getElementById('tableDodajW')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById('frameTresc').height = top.frames[1].document.getElementById('tableDodajW').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[1].document.getElementById('tableDodajW').offsetHeight + 40;
		}
}


function resizeAddUser(){
		if (top.frames[1].document.getElementById('tableAddUser')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById('frameTresc').height = top.frames[1].document.getElementById('tableAddUser').offsetHeight + 30;
			top.document.getElementById('cellTresc').height = top.frames[1].document.getElementById('tableAddUser').offsetHeight + 40;
		}
}