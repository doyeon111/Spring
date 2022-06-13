var d = {};
function loadParameter(){
	search = location.search;
	data = search.substr(1);
	arr = data.split("&");
	for(var i in arr) {
		var a = arr[i].split("=");
		var key = a[0];
		var value = a[1];
		d[key] = value;
	}
}

function getParameter(key){
	return d[key];
}