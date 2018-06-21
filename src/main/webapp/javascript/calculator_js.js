//geeft een eventlistner aan de button "calorieverbranding" mee
document.querySelector("#calorieverbranding").addEventListener("click",function(){mijnUsername("calorie");});
//geeft een eventlistner aan de button "repmax" mee
document.querySelector("#repmax").addEventListener("click",function(){mijnUsername("repmax");});

//Deze functie weergeeft velden die ingevult moeten worden! Wanneer deze velden ingevult zijn en er op de knop
//gedrukt wordt, wordt er een berekening uitgevoerd die de BMR bepaalt.
function showCalorie(json){	
		document.querySelector("#calculatorlijst").innerHTML="<button id='calorieverbranding'>Bereken BMR</button><button id='repmax'>Bereken 1 rep max</button>";
		document.querySelector("#calculatorlijst").innerHTML+='<br><h1>Wat is je BMR (Basal Metabolic Rate)?</h1><br><p>De BMR is de standaard meting van het metabolisme. Dit is het aantal calorieen dat je lichaam nodig heeft om je lichaam werkend te houden. Het aantal calorieen is dan gelijk aan het verbruik van het lichaam in rust. Het gaat hier alleen om de werking van het lichaam zoals ademhalen, verteren en het regelen van de hartslag. De BMR verschilt per persoon. Iedereen heeft een ander metabolisme. Het metabolisme bepaalt voor een groot deel hoeveel iemand aankomt of afvalt. Een sneller metabolisme zorgt ervoor dat je makkelijker afvalt. Een langzaam metabolisme zorgt ervoor dat je makkelijker aankomt. </p>'
		var geslacht = "<br><br><input type='radio' id='man' name='gender' value='man'> Man <input type='radio' name= gender' value='vrouw'> Vrouw<br>";
		var leeftijd = "<input type='number' id='leeftijd' name='leeftijd' placeholder='Leeftijd'><br>";
		var lengte = "<input type='number' id='lengte' name='lengte' placeholder='Lengte in cm'><br>";
		var gewicht = "<p>uw gewicht volgends uw profiel is: "+json.gewicht+"</p><br>";
		var bereken = "<button type='button' id='bereken' name='bereken'>Bereken</button>";
		document.querySelector("#calculatorlijst").innerHTML+= geslacht+leeftijd+lengte+gewicht+bereken;
		document.querySelector("#calorieverbranding").addEventListener("click",function(){mijnUsername("calorie");});
		document.querySelector("#repmax").addEventListener("click",function(){mijnUsername("repmax");});
		document.querySelector("#bereken").addEventListener("click",function(){
			var g = parseInt(json.gewicht);
			var l = parseInt(document.querySelector("#leeftijd").value);
			var leng = parseInt(document.querySelector("#lengte").value);
			if(document.querySelector("#leeftijd").value==null || document.querySelector("#leeftijd").value==""|| document.querySelector("#lengte").value==null || document.querySelector("#lengte").value==""){alert("Vul alle velden in!");}else{
			document.querySelector("#bereken").parentNode.removeChild(document.querySelector("#bereken"));
			if ((document.getElementById('man').checked)){
				console.log(g);
				BMRm = 66 + (13.7 * g) + (5 * leng) - (6.8 * l);
				document.querySelector("#calculatorlijst").innerHTML += "<br><p>Uw BMR is: "+BMRm+"</p><br>";
			}
			else{
				BMRw = 655 + (9.6 * g) + (1.8 * leng) - (4.7 * l);
				document.querySelector("#calculatorlijst").innerHTML += "<br><p id='w'>Uw BMR is: "+BMRw+"</p><br>";
			}
			document.querySelector("#calorieverbranding").addEventListener("click",function(){mijnUsername("calorie");});
			document.querySelector("#repmax").addEventListener("click",function(){mijnUsername("repmax");});
			}});
	}

//Deze functie weergeeft velden die ingevult moeten worden! Wanneer deze velden ingevult zijn en er op de knop
//gedrukt wordt, wordt er een berekening uitgevoerd die de one rep max bepaalt.
function showRepMax(json){
	document.querySelector("#calculatorlijst").innerHTML="<button id='calorieverbranding'>Bereken BMR</button><button id='repmax'>Bereken 1 rep max</button>";
	document.querySelector("#calculatorlijst").innerHTML+='<br><h1>Wat is One rep max?</h1><br><p>One rep maximum (1RM) bij krachttraining, betekend de maximale hoeveelheid gewicht dat men kan tillen, met een herhaling voor een bepaalde oefening. De One Repetition Maximum wordt dan gebruikt, om te bepalen wat de maximale sterkte van een individu is en de methode voor het bepalen van de winnaar aan evenementen, zoals powerlifting en gewichtheffen wedstrijden. One repetition maximum kan ook gebruikt worden, als bovengrens om de gewenste load (als een percentage van de 1RM) voor een oefening te kunnen bepalen.</p>'
	var gewicht = "<br><br><input type='number' id='gewicht' name='gewicht' placeholder='Getilde gewicht in kg'><br>";
	var reps = "<input type='number' id='reps' name='reps' placeholder='Reps'><br>";
	var bereken = "<button type='button' id='bereken' name='bereken'>Bereken</button>";
	document.querySelector("#calculatorlijst").innerHTML+= gewicht+reps+bereken;
	document.querySelector("#calorieverbranding").addEventListener("click",function(){mijnUsername("calorie");});
	document.querySelector("#repmax").addEventListener("click",function(){mijnUsername("repmax");});
	document.querySelector("#bereken").addEventListener("click",function(){
		a = document.querySelector("#gewicht").value;
		b = document.querySelector("#reps").value;
		if(a==null || a=="" || b==null || b==""){alert("Vul alle velden in!");}else{
		onerep = (parseInt(document.querySelector("#gewicht").value) * parseInt(document.querySelector("#reps").value) * 1/30)+parseInt(document.querySelector("#gewicht").value);
		document.querySelector("#bereken").parentNode.removeChild(document.querySelector("#bereken"));
		document.querySelector("#calculatorlijst").innerHTML += "<br><p>Uw One Rep Max is: "+onerep+"</p><br>";
		document.querySelector("#calorieverbranding").addEventListener("click",function(){mijnUsername("calorie");});
		document.querySelector("#repmax").addEventListener("click",function(){mijnUsername("repmax");});
		}});
}


//De username van de ingelogde gebruiker wordt opgezocht en er wordt gekeken op welke knop er geklikt is
//doormiddel van het type. als het type repmax als waarde heeft dan wordt de methode showRepMax uitgevoerd
//is dit niet het geval dan wordt de methode showCalorie uitgevoerd. met beide methodes word een JSON waarin informatie
//van de ingelogde sporter te vinden is
function mijnUsername(type){
	function parseJwt (token) {
		var base64Url = token.split('.')[1];
		var base64 = base64Url.replace('-', '+').replace('_', '/');
		return JSON.parse(window.atob(base64));
	};

	var jwt = sessionStorage.getItem("myJWT");
	var jwtun = parseJwt(jwt);
	var username = jwtun.sub;

	fetch('restservices/sporter/' + username)
	.then(function(response){
		return response.json();
	})
	.then(function(myJson){
		if(type=="repmax"){
			showRepMax(myJson);
		}else{
			showCalorie(myJson);}
		});
}

//uitlog functie
function Uitloggen(){
	sessionStorage.removeItem('myJWT');
	window.location.href = "index.html"
};


function initPage(){
}

window.onload=initPage();