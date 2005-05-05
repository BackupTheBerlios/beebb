      window.onload = function() {
                 var x = document.getElementsByTagName('tr');
	           for (var i=1; i <= x.length; i++) { 
	            if ((x[i-1].className).charAt(0) == 'w')
	              x[i-1].style.display ='none';
	             } 
	        }         
        
            function Info(param){
               return confirm(param);
            }
            
     
                
       function rozwijanie(nr) {
              var x = document.getElementsByTagName('tr');
	        for (var i=1; i <= x.length; i++) { 
	            if ( x[i-1].className == 'w'+nr )
	              if (x[i-1].style.display =='table-row') x[i-1].style.display='none';
                          else x[i-1].style.display='table-row';
	             } 
         } 