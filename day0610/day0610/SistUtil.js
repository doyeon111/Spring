var sumAll = function(){
	var tot = 0;
	for(i=0; i< arguments.length; i++){
		tot += arguments[i];
	}
	return tot;
}

var getSum = function(n){
		tot = 0;
		for(i=1; i<=n; i++){
			tot += i;
		}
		return tot;
}

var getMax = function(a,b){
	if(b>a){
		a =b;
	}
	return a;
}