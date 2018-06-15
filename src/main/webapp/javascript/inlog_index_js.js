document.querySelector("#login").addEventListener("click", login);
 
 function login(event) {
		var formData = new FormData(document.querySelector("#loginform"));
		var encData = new URLSearchParams(formData.entries());

		fetch("restservices/authentication", { method: 'POST', body: encData })
	    .then(function(response) {
	    	if (response.ok) {window.location.href = "http://localhost:8081/prestatiesysteem/homepagina.html";
	    		return response.json();}
	    	else{ throw "Wrong username/password";}
	    	})
	    	.then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
	    	.catch(error => console.log(error));
	}