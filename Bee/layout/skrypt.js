

function resizeMain()
{
		//poprawienie gornej ramki
		if (top.frames[0].location.pathname.indexOf("naglowek.html",0) != -1)
		{
			top.document.getElementById('frameHead').height = top.frames[0].document.getElementById('tableHeader').offsetHeight +30;
			top.document.getElementById('cellHead').height = top.frames[0].document.getElementById('tableHeader').offsetHeight +40;
		}
}