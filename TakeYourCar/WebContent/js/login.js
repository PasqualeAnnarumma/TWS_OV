function getXmlHttpRequest() {
	var xml = false;
	var activeXoptions = new Array("Microsoft.XmlHttp", "MSXML4.XmlHttp",
	"MSXML3.XmlHttp", "MSXML2.XmlHttp", "MSXML.XmlHttp");
	try {
		xml = new XMLHttpRequest();
	} catch (e) {
		console.log(e);
	}
	if (!xml) {
		var created = false;
		for (var i = 0; i < activeXoptions.length && !created; i++) {
			try {
				xml = new ActiveXObject(activeXoptions[i]);
				created = true
			} catch (e) {
				console.log(e);
			}
		}
	}
	return xml;
}

function validateLogin(username, password, handler, admin, utente) {
	//rimuovo la classe che serve per fare lo shake della form
	$("#contenitore").removeClass("shake");
	var ruolo = "admin";
	if (!admin.checked) ruolo = "utente";
	//ottengo l'oggetto XMLHttpRequest
	var xml = new getXmlHttpRequest();
	//creo i parametri
	var params = "username=" + username + "&password=" + password + "&ruolo=" + ruolo;
	//controllo che l'oggetto XMLHttpRequest non sia nullo
	if (xml) {
		xml.onreadystatechange = function() {handler(xml)};
		xml.open("POST", "fastLogin", true);
		xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xml.send(params);
	}
}