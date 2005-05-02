
function resizeMain()
{
              	if (top == window) return;//zabezpieczenie jesli nie jestesmy potomkiem

                var  i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';
                
		//poprawienie gornej ramki
		//if (top.frames[0].location.pathname.indexOf("header.jsp",0) != -1)
                if (top.frames[0].document.getElementById('tableHeader'))
		{
			top.document.getElementById('frameHead').height = Math.max(top.frames[0].document.body.offsetHeight,top.frames[0].document.body.scrollHeight) + 30;
			top.document.getElementById('cellHead').height = Math.max(top.frames[0].document.body.offsetHeight,top.frames[0].document.body.scrollHeight) + 40;
                        //top.document.getElementById('frameTresc').
		}
                //else
                //{
                        
                //}
		
		//poprawianie dolnej ramki
		if (top.frames[i].document.body) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById(frameTresc).height = Math.max(top.frames[i].document.body.offsetHeight,top.frames[i].document.body.scrollHeight) + 30;
			top.document.getElementById('cellTresc').height = Math.max(top.frames[i].document.body.offsetHeight,top.frames[i].document.body.scrollHeight) + 40;
		}
}		

                
function resizeWatek(){
              	if (top == window) return;//zabezpieczenie jesli nie jestesmy potomkiem

                var i = top.document.getElementById('frameTresc_1').style.display == 'none' ? 2 : 1;
                frameTresc = top.document.getElementById('frameTresc_1').style.display == 'none' ? 'frameTresc_2' : 'frameTresc_1';

alert(top.window.frames.length);
alert(window.frames[0].document);
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
                        var ramki = window.window.frames;
//alert(ramki);
                        for(var f=0;f<wypowiedzi.length;f++)
                        {
                            if (!opera)
                                wypowiedzi[f].height = Math.max(ramki[f].document.body.scrollHeight,ramki[f].document.body.offsetHeight) + 10; //getElementById('tableWypowiedz').offsetHeight + 10;
                            else
                                wypowiedzi[f].height = Math.max(ramki[f].document.body.scrollHeight,ramki[f].document.body.offsetHeight) + 25; //getElementById('tableWypowiedz').offsetHeight + 25;
                        }

		}
                resizeMain();
}

