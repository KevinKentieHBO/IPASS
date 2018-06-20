document.querySelector("#calorieverbranding").addEventListener("click",function(){mijnUsername("calorie");});
document.querySelector("#repmax").addEventListener("click",function(){mijnUsername("repmax");});

function showCalorie(json){	
		document.querySelector("#calculatorlijst").innerHTML="<button id='calorieverbranding'>Bereken BMR</button><button id='repmax'>Bereken 1 rep max</button>";
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
		});
	}

function showRepMax(json){
	document.querySelector("#calculatorlijst").innerHTML="<button id='calorieverbranding'>Bereken BMR</button><button id='repmax'>Bereken 1 rep max</button>";
	var gewicht = "<br><br><input type='number' id='gewicht' name='gewicht' placeholder='Getilde gewicht in kg'><br>";
	var reps = "<input type='number' id='reps' name='reps' placeholder='Reps'><br>";
	var bereken = "<button type='button' id='bereken' name='bereken'>Bereken</button>";
	document.querySelector("#calculatorlijst").innerHTML+= gewicht+reps+bereken;
	document.querySelector("#calorieverbranding").addEventListener("click",function(){mijnUsername("calorie");});
	document.querySelector("#repmax").addEventListener("click",function(){mijnUsername("repmax");});
	document.querySelector("#bereken").addEventListener("click",function(){
		onerep = (parseInt(document.querySelector("#gewicht").value) * parseInt(document.querySelector("#reps").value) * 1/30)+parseInt(document.querySelector("#gewicht").value);
		document.querySelector("#bereken").parentNode.removeChild(document.querySelector("#bereken"));
		document.querySelector("#calculatorlijst").innerHTML += "<br><p>Uw One Rep Max is: "+onerep+"</p><br>";
		document.querySelector("#calorieverbranding").addEventListener("click",function(){mijnUsername("calorie");});
		document.querySelector("#repmax").addEventListener("click",function(){mijnUsername("repmax");});
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

function Uitloggen(){
	sessionStorage.removeItem('myJWT');
	window.location.href = "index.html"
};


function initPage(){
}

window.onload=initPage();