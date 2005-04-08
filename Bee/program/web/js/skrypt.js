

function resizeMain()
{
		//poprawienie gornej ramki
		if (top.frames[0].location.pathname.indexOf("header.jsp",0) != -1)
		{
			top.document.getElementById('frameHead').height = top.frames[0].document.getElementById('tableHeader').offsetHeight +30;
			top.document.getElementById('cellHead').height = top.frames[0].document.getElementById('tableHeader').offsetHeight +40;
		}
		
		//poprawianie dolnej ramki
//UWAGA: to liczy rozmaiar tylko na podstawie wielkosci tabeli, trzeba wziac pod uwage jeszcze napisy ponad nia
                //if ((top.frames[1].location.pathname.indexOf("wpid=",0) == -1) && (top.frames[1].location.pathname.indexOf("wid=",0) == -1))
		//if (top.frames[1].location.pathname.indexOf("main.jsp",0) != -1) //oczywiscie w JSP bedziemy szukac czegos na wzor show_forum= , a w tej chwili jest to np kid=
		if (top.frames[1].document.getElementById('tableForum')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById('frameTresc').height = top.frames[1].document.getElementById('tableForum').offsetHeight +50;
			top.document.getElementById('cellTresc').height = top.frames[1].document.getElementById('tableForum').offsetHeight +60;
		}
		
		//if (top.frames[1].location.pathname.indexOf("podforum.html",0) != -1) 
		if (top.frames[1].document.getElementById('tablePodforum')) //zabezpieczenie by nie wyszlo zero		
		{
			top.document.getElementById('frameTresc').height = top.frames[1].document.getElementById('tablePodforum').offsetHeight +50;
			top.document.getElementById('cellTresc').height = top.frames[1].document.getElementById('tablePodforum').offsetHeight +60;
		}
		
}