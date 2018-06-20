document.querySelector("#registreer").addEventListener("click",function(){registreer()});
document.querySelector("#back").addEventListener("click",function(){window.location.href = "index.html";});
function registreer(){
			var formData = new FormData(document.querySelector("#registratieform"));
 		    var encData = new URLSearchParams(formData.entries());
 		    alert("u bent geregistreerd!");
 		    fetch('restservices/sporter', { method: 'POST', body: encData})
 		    	.then(response => response.json())
 		    	.then(function (myJson) { console.log(myJson); });
 }