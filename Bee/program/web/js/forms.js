

function submitAddUser(form,dlugosc_hasla, komunikat_wymagane,komunikat_haslaNiePasuja, komunikat_hasloZaKrotkie){
if ((document.getElementById('user').value.length <=0) || (document.getElementById('haslo1').value.length <=0) || (document.getElementById('haslo2').value.length <=0) || (document.getElementById('email').value.length <=0))
    {
        alert(komunikat_wymagane);
        return false;
    }
if ( document.getElementById('haslo1').value != document.getElementById('haslo2').value) 
    {
        alert(komunikat_haslaNiePasuja);
        return false;
    }
if ( document.getElementById('haslo1').value.length <dlugosc_hasla)
    {
        alert(komunikat_hasloZaKrotkie);
        return false;
    }
return true;    
}