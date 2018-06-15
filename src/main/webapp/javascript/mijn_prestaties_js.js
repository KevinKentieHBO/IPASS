document.querySelector("#bekijkCardio").addEventListener("click",function(){mijnUsername("cardio");});
document.querySelector("#bekijkGewicht").addEventListener("click",function(){mijnUsername("gewicht");});

function showMijnCardioPrestaties(json){
	var id = json.sportersnummer;

	fetch('http://localhost:8081/prestatiesysteem/restservices/prestatie/cardio' + id)
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
 				
 				document.querySelector("#verwijder").addEventListener("click",function(){verijderCardioPrestatie(value.prestatienummer)});
				
			}
	});
}

function showMijnGewichtPrestaties(json){
	var id = json.sportersnummer;
	
	fetch('http://localhost:8081/prestatiesysteem/restservices/prestatie/gewicht' + id,{method: 'GET'})
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
 				
 				document.querySelector("#verwijder").addEventListener("click",function(){verijderGewichtPrestatie(value.prestatienummer)});
 				console.log(value.prestatienummer);
			}
	});
}

function verijderGewichtPrestatie(prestatienummer){
	fetch('http://localhost:8081/prestatiesysteem/restservices/prestatie/gewicht' + prestatienummer,{method: 'DELETE'})
	.then(function(response){
		return response.json();
	})
	.then(function(myJson){
		console.log(myJson);
	});
}

function verijderCardioPrestatie(prestatienummer){
	fetch('http://localhost:8081/prestatiesysteem/restservices/prestatie/cardio' + prestatienummer,{method: 'DELETE'})
	.then(function(response){
		return response.json();
	})
	.then(function(myJson){
		console.log(myJson);
	});
}

function mijnUsername(type){
	function parseJwt (token) {
		var base64Url = token.split('.')[1];
		var base64 = base64Url.replace('-', '+').replace('_', '/');
		return JSON.parse(window.atob(base64));
	};

	var jwt = sessionStorage.getItem("myJWT");
	var jwtun = parseJwt(jwt);
	var username = jwtun.sub;

	fetch('http://localhost:8081/prestatiesysteem/restservices/sporter/' + username)
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


function initPage(){
}

window.onload=initPage();