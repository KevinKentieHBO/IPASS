document.querySelector("#registreer").addEventListener("click",function(){registreer()});
document.querySelector("#back").addEventListener("click",function(){window.location.href = "index.html";});

//stuurt de ingevulde informatie naar de back-end en voegt een sporter toe
function registreer(){
	if (validateForm()){
			var formData = new FormData(document.querySelector("#registratieform"));
 		    var encData = new URLSearchParams(formData.entries());
 		    alert("u bent geregistreerd!");
 		    fetch('restservices/sporter', { method: 'POST', body: encData})
 		    	.then(response => response.json())
 		    	.then(function (myJson) { console.log(myJson); });
	}
 }

//kijkt of de velden niet leeg zijn
function validateForm()
{
    var a=document.forms["registratieform"]["voornaam"].value;
    var b=document.forms["registratieform"]["tussenvoegsel"].value;
    var c=document.forms["registratieform"]["achternaam"].value;
    var d=document.forms["registratieform"]["email"].value;
    var e=document.forms["registratieform"]["wachtwoord"].value;
    var f=document.forms["registratieform"]["telefoonnummer"].value;
    var g=document.forms["registratieform"]["geboortedatum"].value;
    var h=document.forms["registratieform"]["gewicht"].value;
    if (a==null || a=="" || b==null || b=="" || c==null || c==""|| d==null || d==""|| e==null || e==""|| f==null || f==""|| g==null || g==""|| h==null || h=="")
    {
        alert("Vul alle velden in!");
        return false;
    } else{
    	return true;
    }
}