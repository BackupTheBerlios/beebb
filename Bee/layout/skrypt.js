

function resizeMain()
{
		//poprawienie gornej ramki
		if (top.frames[0].location.pathname.indexOf("naglowek.html",0) != -1)
		{
			alert(top.document.getElementById('frameHead').document);
			//alert(top.frames[0].clientHeight);//document.getElementById('cellMenu').clientHeight);
			//alert(top.document.getElementById('frameHead').getElementById('cellMenu'));
		}
		//alert(window.frames['frameHead']);
		//top.document.getElementById('frameHead').height = ;
}