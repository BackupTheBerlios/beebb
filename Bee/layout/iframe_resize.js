/*
	ZALOZENIA: funkcja bedzie wolana z eventu onResize() w elemencie Body z watek.html. 
	Jako parametr zostanie jej podana tablica zaiwerajaca liczbe znakow w poszczegolnych wypowiedziach, liczac od gory do dolu.
*/


function funOnResize(ile_znakow)
{
	//	ile_znakow = [691,691,691,691,691,691,691,691,691,691,691];
	ramki = document.getElementsByTagName('iframe');
	komorki = document.getElementsByTagName('td');
	textHeight = document.getElementById('napis').offsetHeight;
	textWidth=document.getElementById('napis').offsetWidth;
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
	wysokosc_wszystkich = 0;		
	for(i=0;i<ramki.length;i++)
		{
			dlugosc = ile_znakow[i] * textWidth;
			miesci_sie = (wypowiedzi[i].offsetWidth  / textWidth) * 10;
			ile_wierszy = (dlugosc / miesci_sie) + 1;
			wypowiedzi[i].height = ile_wierszy * textHeight;
			wysokosc_wszystkich += wypowiedzi[i].height; 
		}
		
	//No to teraz poprawa zewnetrznej ramki, to juz nie sa przelewki :D
	if (top != window) //zabezpieczenie jesli nie jestesmy potomkiem
	{
		top.document.getElementById('frameTresc').height = document.getElementById('tableWatek').offsetHeight +50;
		top.document.getElementById('cellTresc').height = document.getElementById('tableWatek').offsetHeight +60;
	}
	
}