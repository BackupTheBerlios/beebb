
var object_hint;

function showError(ob,kom){
    object_hint = ob;
    showHint(kom,object_hint,0)
    setTimeout('hideHint(object_hint)',3000);
}

function submitAddUser(form,dlugosc_hasla, komunikat_wymagane,komunikat_haslaNiePasuja, komunikat_hasloZaKrotkie){
if ((document.getElementById('user').value.length <=0) || (document.getElementById('passwd1').value.length <=0) || (document.getElementById('passwd2').value.length <=0) || (document.getElementById('email').value.length <=0))
    {
        if (document.getElementById('user').value.length <=0) 
            showError(document.getElementById('user'),komunikat_wymagane);
        else
        if (document.getElementById('passwd1').value.length <=0) 
            showError(document.getElementById('passwd1'),komunikat_wymagane);
        else
        if (document.getElementById('passwd2').value.length <=0) 
            showError(document.getElementById('passwd2'),komunikat_wymagane);
        else
        if (document.getElementById('email').value.length <=0) 
            showError(document.getElementById('email'),komunikat_wymagane);
        return false;
    }
if ( document.getElementById('passwd1').value != document.getElementById('passwd2').value) 
    {
        alert(komunikat_haslaNiePasuja);
        return false;
    }
if ( document.getElementById('passwd1').value.length <dlugosc_hasla)
    {
        alert(komunikat_hasloZaKrotkie);
        return false;
    }
return true;    
}


function submitForgetPasswd(komunikat_wymagane){
if ((document.getElementById('user').value.length <=0) || (document.getElementById('email').value.length <=0))
    {
        if (document.getElementById('user').value.length <=0) 
            showError(document.getElementById('user'),komunikat_wymagane);
        else
        if (document.getElementById('email').value.length <=0) 
            showError(document.getElementById('email'),komunikat_wymagane);
        return false;
    }
return true;
}

function submitDodajW(komunikat_wymagane){
if ( ((document.getElementById('title'))&&(document.getElementById('title').value.length <=0)) || (document.getElementById('text').value.length <=0))
    {
        //alert(komunikat_wymagane);
        if (document.getElementById('title').value.length <=0) 
            showError(document.getElementById('title'),komunikat_wymagane);
        else
        if (document.getElementById('text').value.length <=0)
            showError(document.getElementById('text'),komunikat_wymagane);
        return false;
    }
if (document.getElementById('autor'))
if (document.getElementById('autor').value.length <=0)
    {
        showError(document.getElementById('autor'),komunikat_wymagane);
        return false;
    }
return true;
}

function czyNaPewno(komunikat){
    return confirm(komunikat);
}