/*
	ZALOZENIA: funkcja bedzie wolana z eventu onResize() w elemencie Body z watek.html. 
	Jako parametr zostanie jej podana tablica zaiwerajaca liczbe znakow w poszczegolnych wypowiedziach, liczac od gory do dolu.
*/


function funOnResize(ile_znakow)
{
	//	ile_znakow = [691,691,691,691,691,691,691,691,691,691,691];
	//	alert(document.window.title);
	ramki = document.getElementsByTagName('iframe');
	komorki = document.getElementsByTagName('td');
	textHeight = document.getElementById('przyklad').clientHeight;
	textWidth=document.getElementById('przyklad').clientWidth;
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
	for(i=0;i<ramki.length;i++)
		{
			dlugosc = ile_znakow[i] * textWidth;
			miesci_sie = (wypowiedzi[i].clientWidth  / textWidth) * 20;
			ile_wierszy = (dlugosc / miesci_sie) + 1;
			wypowiedzi[i].height = ile_wierszy * textHeight;
		}
}