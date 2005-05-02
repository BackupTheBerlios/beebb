
function resizeMain()
{
              	if (top == window) return;//zabezpieczenie jesli nie jestesmy potomkiem
                if (!(top.document.getElementById('frameTresc_1'))) return;
                
                var i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
                
		//poprawienie gornej ramki
                if (top.frames[0].document.getElementById('tableHeader'))
		{
			top.document.getElementById('frameHead').height = Math.max(top.frames[0].document.body.offsetHeight,top.frames[0].document.body.scrollHeight) + 30;
			top.document.getElementById('cellHead').height = Math.max(top.frames[0].document.body.offsetHeight,top.frames[0].document.body.scrollHeight) + 40;
		}
		
		//poprawianie dolnej ramki
		if (top.frames[i].document.body) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = Math.max(top.frames[i].document.body.offsetHeight,top.frames[i].document.body.scrollHeight) + 30;
			top.document.getElementById('cellTresc').height = Math.max(top.frames[i].document.body.offsetHeight,top.frames[i].document.body.scrollHeight) + 40;
		}
}		

                
function resizeWatek(){

//TODO Mozilla nie obslugiwana
              	//if (top == window) return;//zabezpieczenie jesli nie jestesmy potomkiem
                if (!(top.document.getElementById('frameTresc_1'))) 
                    {
                        var doc = document;
                    }
                else
                {

                    var i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                    frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';

                    var doc = top.frames[i].document;
                }

                if (doc.getElementById('tableWatek')) //zabezpieczenie by nie wyszlo zero		
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
                        for(var f=0;f<wypowiedzi.length;f++)
                        {
                            if (!opera)
                                wypowiedzi[f].height = Math.max(frames[f].document.body.scrollHeight,frames[f].document.body.offsetHeight) + 1;
                            else
                                wypowiedzi[f].height = Math.max(frames[f].document.body.scrollHeight,frames[f].document.body.offsetHeight) + 25;
                        }

		}
                resizeMain();
}

