function showOefeningen(){
	 		fetch('http://localhost:8081/prestatiesysteem/restservices/oefening')
	 		.then(function(response){
	 			return response.json();
	 		})
	 		.then(function(myJson){
	 			var table = document.querySelector("table");
	 			for (const value of myJson){
	 				var row = table.insertRow(1);
	 				row.addEventListener("click", function(){
						oefeningInvul(value.oefeningnummer, value.oefeningnaam, value.oefeningtype, value.intensiteit,value.calorie_verbranding, value.afbeelding);
					});
	 				var cell1 = row.insertCell(0);
	 				cell1.innerHTML = value.oefeningnaam;
	 			}
	 		});
	 	}

function oefeningInvul(oefeningnummer,oefeningnaam,oefeningtype,intensiteit,calorie_verbranding,afbeelding){
	function parseJwt (token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    };
    
    var jwt = sessionStorage.getItem("myJWT");
    var jwtun = parseJwt(jwt);
    var usernamejwt = jwtun.sub;
	
	var naamveld = '<h1 id="oefeningnaamh name="oefeningnaamh"">'+oefeningnaam+'</h1><br>';
	var afbeeldingveld = '<img id="oefeningfoto" name="oefeningfoto" src="afbeeldingen/oefeningen/'+afbeelding+'"><br>';
	var typeveld ='<p name="oefeningtype" id ="oefeningtype""> <b>type:</b> '+oefeningtype+'</p><br>';
	var setsveld = '<input type="number" id="sets" name="sets" placeholder="Sets">';
	var repsveld = '<input type="number" id="reps" name = "reps" placeholder="Reps">';
	var volumeveld = '<input type="number" id="volume" name="volume" placeholder="Volume">';
	
	var sessieduurveld = '<input type="number" name="sessieduur" id="sessieduur" placeholder="Sessieduur">';
	var afstandveld = '<input type="number" name="afstand" id="afstand" placeholder="Afstand">';
	var snelheidveld = '<input type="number" name="snelheid" id="snelheid" placeholder="Snelheid">';
	var username = '<input type="text" name="username" value="'+usernamejwt+'" id="username" style="display: none;">';
	var oefeningveld = '<input type="text" name="oefeningnaam" value="'+oefeningnaam+'" id="oefeningnaam" style="display: none;">';
	var submitknop = '<br><button type="button" id="submit">Invullen</button>';
	
 	if(oefeningtype =='cardio'){
 		document.getElementById("prestatie").innerHTML = '<form id="CardioForm">'+naamveld + afbeeldingveld + typeveld + sessieduurveld + afstandveld + oefeningveld + snelheidveld+submitknop+username+'</form>';
 		document.querySelector("#submit").addEventListener("click",function(){
 			var formData = new FormData(document.querySelector("#CardioForm"));
 		    var encData = new URLSearchParams(formData.entries());
 		    
 		    fetch('http://localhost:8081/prestatiesysteem/restservices/oefening/cardiooefening', { method: 'POST', body: encData, headers:{'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")} })
 		    	.then(response => response.json())
 		    	.then(function (myJson) { console.log(myJson); });
 		  document.getElementById('sessieduur').value = '';
 		  document.getElementById('afstand').value = '';
 		  document.getElementById('snelheid').value = '';
		});
 	}else{
 		document.getElementById("prestatie").innerHTML = '<form id="GewichtForm">'+naamveld + afbeeldingveld + typeveld + volumeveld + setsveld + oefeningveld + repsveld+submitknop+username+'</form>';
 		document.querySelector("#submit").addEventListener("click",function(){
 			var formData = new FormData(document.querySelector("#GewichtForm"));
 		    var encData = new URLSearchParams(formData.entries());
 		    
 		    fetch('http://localhost:8081/prestatiesysteem/restservices/oefening/gewichtoefening', { method: 'POST', body: encData, headers:{'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")} })
 		    	.then(response => response.json())
 		    	.then(function (myJson) { console.log(myJson); });
 		  document.getElementById('volume').value = '';
  		  document.getElementById('sets').value = '';
  		  document.getElementById('reps').value = '';
		});
 	}
}

function initPage(){
		showOefeningen();
	}
	
window.onload=initPage();