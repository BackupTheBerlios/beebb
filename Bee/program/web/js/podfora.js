      window.onload = function() {
                 var x = document.getElementsByTagName('p');
	           for (var i=1; i <= x.length; i++) { 
	            if (x[i-1].className == 'podfora')
	              x[i-1].style.display ='none';
	             } 
	        }         
        
            function Info(param){
               return confirm(param);
            }
            
     
                
       function rozwijanie(nr) {
           if (document.getElementById) {       
	        var pom=document.getElementById('podfora'+nr);
	        var plusik=document.getElementById('plusik'+nr);

		if (pom.style.display == 'none') { 
                     v='block'
                 
                   } 
                  else { 
                    v='none';
           
                   }
		pom.style.display = v;
	    }
         } 