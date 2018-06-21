//geeft een eventlistner aan de button "login" mee
document.querySelector("#login").addEventListener("click", login);
//geeft een eventlistner aan de button "registreren" mee
document.querySelector("#registreren").addEventListener("click", registreren);
 
//deze methode handeld het inloggen op de website en zorgt voor een myJWT token
 function login(event) {
		var formData = new FormData(document.querySelector("#loginform"));
		var encData = new URLSearchParams(formData.entries());

		fetch("restservices/authentication", { method: 'POST', body: encData })
	    .then(function(response) {
	    	if (response.ok) {window.location.href = "homepagina.html";
	    		return response.json();}
	    	else{ throw "Wrong username/password";}
	    	})
	    	.then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
	    	.catch(error => console.log(error));
	}
 
//Verwijst naar de pagina registreren.html
 function registreren(){
	 window.location.href = "registreren.html";
 }