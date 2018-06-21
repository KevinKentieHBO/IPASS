//geeft een eventlistner aan de button "bekijkcardio" mee
document.querySelector("#bekijkCardio").addEventListener("click",function(){mijnUsername("cardio");});
//geeft een eventlistner aan de button "bekijkgewicht" mee
document.querySelector("#bekijkGewicht").addEventListener("click",function(){mijnUsername("gewicht");});

//laat alle cardioprestaties zien van de ingelogde gebruikter door de GET methode aan te roepen in de rescource klasse
function showMijnCardioPrestaties(json){
	var id = json.sportersnummer;

	fetch('restservices/prestatie/cardio' + id)
	.then(function(response){
		return response.json();
	})
	.then(function(myJson){
		console.log(myJson);
		document.querySelector("#mijnprestatielijst").innerHTML="<button id='bekijkCardio'>Cardio prestaties</button><button id='bekijkGewicht'>Gewicht prestaties</button>";
		document.querySelector("#mijnprestatielijst").innerHTML+= "<table id='groot'><tr><th id='afbeelding'>Afbeelding</th><th id='datum'>Datum</th><th id='informatie'>informatie</th><th id='verwijderwijzig'>Verwijder/Wijzig</th></tr></table>";
		document.querySelector("#bekijkCardio").addEventListener("click",function(){mijnUsername("cardio");});
		document.querySelector("#bekijkGewicht").addEventListener("click",function(){mijnUsername("gewicht");});
		
		var table = document.querySelector("table");
		
			for (const value of myJson){
				var row = table.insertRow(1);
 				var cell1 = row.insertCell(0);
 				var cell2 = row.insertCell(1);
 				var cell3 = row.insertCell(2);
 				var cell4 = row.insertCell(3);
 				
 				var afbeelding = '<img id="oefeningfoto" name="oefeningfoto" height="100" width="100" src="afbeeldingen/oefeningen/'+value.oefeningafbeelding+'">';
 				var datum = '<p name="datum" id ="datum""> <b>datum:</b> '+value.datum+'</p>';
 				var sessieduur = '<p name="sessieduur" id ="sessieduur""> <b>sessieduur:</b> '+value.sessieduur+'</p>';
 				var afstand = '<p name="afstand" id ="afstand""> <b>afstand:</b> '+value.afstand+'</p>';
 				var snelheid = '<p name="snelheid" id ="snelheid""> <b>snelheid:</b> '+value.snelheid+'</p>';
 				var wijzig='<button id="wijzig">Wijzig</button>';
 				var verwijder='<button id="verwijder">Verwijder</button>';
 				cell1.innerHTML = afbeelding;
 				cell2.innerHTML = datum;
 				cell3.innerHTML = sessieduur+afstand+snelheid;
 				cell4.innerHTML = wijzig+verwijder;
 				document.querySelector("#verwijder").addEventListener("click",function(){verijderCardioPrestatie(value.prestatienummer); mijnUsername("cardio");});
 				document.querySelector("#wijzig").addEventListener("click",function(){wijzigCardioFunc(value.prestatienummer)});
				
			}
	});
}

//laat alle gewichtprestaties zien van de ingelogde gebruikter door de GET methode aan te roepen in de rescource klasse
function showMijnGewichtPrestaties(json){
	var id = json.sportersnummer;
	
	fetch('restservices/prestatie/gewicht' + id,{method: 'GET'})
	.then(function(response){
		return response.json();
	})
	.then(function(myJson){
		console.log(myJson);
		document.querySelector("#mijnprestatielijst").innerHTML="<button id='bekijkCardio'>Cardio prestaties</button><button id='bekijkGewicht'>Gewicht prestaties</button>";
		document.querySelector("#mijnprestatielijst").innerHTML+= "<table id='groot'><tr><th id='afbeelding'>Afbeelding</th><th id='datum'>Datum</th><th id='informatie'>informatie</th><th id='verwijderwijzig'>Verwijder/Wijzig</th></tr></table>";
		document.querySelector("#bekijkCardio").addEventListener("click",function(){mijnUsername("cardio");});
		document.querySelector("#bekijkGewicht").addEventListener("click",function(){mijnUsername("gewicht");});
		var table = document.querySelector("table");
			for (const value of myJson){
				var row = table.insertRow(1);
 				var cell1 = row.insertCell(0);
 				var cell2 = row.insertCell(1);
 				var cell3 = row.insertCell(2);
 				var cell4 = row.insertCell(3);
 				
 				var afbeelding = '<img id="oefeningfoto" name="oefeningfoto" height="100" width="100" src="afbeeldingen/oefeningen/'+value.oefeningafbeelding+'">';
 				var datum = '<p name="datum" id ="datum""> <b>datum:</b> '+value.datum+'</p>';
 				var volume = '<p name="volume" id ="volume""> <b>volume:</b> '+value.volume+'</p>';
 				var sets = '<p name="sets" id ="sets""> <b>sets:</b> '+value.sets+'</p>';
 				var reps = '<p name="reps" id ="reps""> <b>reps:</b> '+value.reps+'</p>';
 				var wijzig='<button id="wijzig">Wijzig</button>';
 				var verwijder='<button id="verwijder">Verwijder</button>';
 				cell1.innerHTML = afbeelding;
 				cell2.innerHTML = datum;
 				cell3.innerHTML = volume+sets+reps;
 				cell4.innerHTML = wijzig+verwijder;
 				
 				document.querySelector("#verwijder").addEventListener("click",function(){verijderGewichtPrestatie(value.prestatienummer); mijnUsername("gewicht");});
 				document.querySelector("#wijzig").addEventListener("click",function(){wijzigGewichtFunc(value.prestatienummer)});
 				console.log(value.prestatienummer);
			}
	});
}

//Weergeeft het pop-up scherm om een gewichtprestatie te wijzigen
function wijzigGewichtFunc(prestatienummer){
		modal.style.display = "block";
		fetch("restservices/prestatie/eengewicht" + prestatienummer)
		.then(response => response.json())
		.then(function(myJson){
			document.getElementById("wijzigGegevens").innerHTML = '<input type="number" name="gewichtprestatienummer" id="gewichtprestatienummer" value="'+myJson.prestatienummer+'"style="display: none;">';
			document.getElementById("wijzigGegevens").innerHTML += '<input name="reps" type="number" value="'+ myJson.reps +  '">Reps<br><br>';
			document.getElementById("wijzigGegevens").innerHTML += '<input name="volume" type="number" value="'+ myJson.volume +  '">Volume<br><br>';
			document.getElementById("wijzigGegevens").innerHTML += '<input name="sets" type="number" value="'+ myJson.sets +  '">Sets<br><br>';
			document.getElementById("wijzigGegevens").innerHTML += '<input id="put" type="button" value="Verzend"><br><br>';
			document.querySelector("#put").addEventListener("click", putGewichtHandler);
		});
	}

//Weergeeft het pop-up scherm om een cardioprestatie te wijzigen
function wijzigCardioFunc(prestatienummer){
	modal.style.display = "block";
	fetch("restservices/prestatie/eencardio" + prestatienummer)
	.then(response => response.json())
	.then(function(myJson){
		document.getElementById("wijzigGegevens").innerHTML = '<input type="number" name="cardioprestatienummer" id="cardioprestatienummer" value="'+myJson.prestatienummer+'"style="display: none;">';
		document.getElementById("wijzigGegevens").innerHTML += '<input name="afstand" type="number" value="'+ myJson.afstand +  '">Afstand<br><br>';
		document.getElementById("wijzigGegevens").innerHTML += '<input name="sessieduur" type="number" value="'+ myJson.sessieduur +  '">Sessieduur<br><br>';
		document.getElementById("wijzigGegevens").innerHTML += '<input name="snelheid" type="number" value="'+ myJson.snelheid +  '">Snelheid<br><br>';
		document.getElementById("wijzigGegevens").innerHTML += '<input id="put" type="button" value="Verzend"><br><br>';
		document.querySelector("#put").addEventListener("click", putCardioHandler);
	});
}

//roept in de rescource klasse de PUT methode aan voor een gewicht prestatie
var putGewichtHandler = function() {
		if(validateGewichtForm()){
		var id = document.getElementById("gewichtprestatienummer").value;
	    var formData = new FormData(document.querySelector("#wijzigGegevens"));
	    var encData = new URLSearchParams(formData.entries());

	    fetch("restservices/prestatie/eengewicht" + id, { method: 'PUT' , body: encData} )
	    .then(response => Promise.all([response.status, response.json()]))
	 	  
	 	  .then(function([status, myJson]) {
	 		 mijnUsername("gewicht");
	 	    if (status == 200)
	 	      console.log(myJson.resultaat);
	 	    else {
	 	      console.log(myJson.error);
	 	    } 
	 	  })

	 	  .catch(error => console.log(error.message));
		}};
	 	
//roept in de rescource klasse de PUT methode aan voor een cardioprestatie
var putCardioHandler = function() {
	 		if(validateCardioForm()){
			var id = document.getElementById("cardioprestatienummer").value;
		    var formData = new FormData(document.querySelector("#wijzigGegevens"));
		    var encData = new URLSearchParams(formData.entries());

		    fetch("restservices/prestatie/eencardio" + id, { method: 'PUT' , body: encData} )
		    .then(response => Promise.all([response.status, response.json()]))
		 	  
		 	  .then(function([status, myJson]) {
		 	    if (status == 200)
		 	      console.log(myJson.resultaat);
		 	    else {
		 	      console.log(myJson.error);
		 	    }
		 	   mijnUsername("cardio");
		 	  })

		 	  .catch(error => console.log(error.message));
	 		}};
	 	
//roept in de rescource klasse de delete methode aan voor een gewichtprestatie
function verijderGewichtPrestatie(prestatienummer){
	fetch('restservices/prestatie/gewicht' + prestatienummer,{method: 'DELETE'})
	.then(function(response){
		return response.json();
	})
	.then(function(myJson){
		console.log(myJson);
	});
}

//roept in de rescource klasse de delete methode aan voor een carioprestatie
function verijderCardioPrestatie(prestatienummer){
	fetch('restservices/prestatie/cardio' + prestatienummer,{method: 'DELETE'})
	.then(function(response){
		return response.json();
	})
	.then(function(myJson){
		console.log(myJson);
	});
}

//De username van de ingelogde gebruiker wordt opgezocht en er wordt gekeken op welke knop er geklikt is
//doormiddel van het type. als het type cardio als waarde heeft dan wordt de methode ShowMijnCardioPrestaties uitgevoerd
//is dit niet het geval dan wordt de methode ShowMijnGewichtPrestaties uitgevoerd. met beide methodes word een JSON waarin informatie
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
		if(type=="cardio"){
			showMijnCardioPrestaties(myJson);
		}else{
			showMijnGewichtPrestaties(myJson);}
		});
}

//checkt of alle velden wel ingevult zijn in het Gewicht formulier
function validateGewichtForm()
{
    var a=document.forms["Form"]["sets"].value;
    var b=document.forms["Form"]["reps"].value;
    var c=document.forms["Form"]["volume"].value;
    if (a==null || a=="" || b==null || b=="" || c==null || c=="")
    {
        alert("Vul alle velden in!");
        return false;
    } else{
    	return true;
    }
}

//checkt of alle velden wel ingevult zijn in het Cardio formulier
function validateCardioForm()
{
    var a=document.forms["Form"]["sessieduur"].value;
    var b=document.forms["Form"]["afstand"].value;
    var c=document.forms["Form"]["snelheid"].value;
    if (a==null || a=="" || b==null || b=="" || c==null || c=="")
    {
        alert("Vul alle velden in!");
        return false;
    } else{
    	return true;
    }
}

//functie voor het uitloggen
function Uitloggen(){
	sessionStorage.removeItem('myJWT');
	window.location.href = "index.html";
}


function initPage(){
}

window.onload=initPage();

//Get the modal
	var modal = document.getElementById('myModal');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}


	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }}