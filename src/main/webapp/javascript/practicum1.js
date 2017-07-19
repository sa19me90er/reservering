$(document).ready(function() {
	
	function getDestinations(){
		$.getJSON("/restservices/reserveringen",function (result){
			var table = $("#destination-items");
			var body = table.find("tbody");
			body.empty();
			for(var item in result){
				var value = result[item];
				body.append("<tr class='country' id='"+value.code+"' data-lat='"+value.lat+"' data-long='"+value.lng+"'><td>"+value.name+"</td><td>"+value.capital+"</td><td>"+value.surface+"</td><td>"+value.population+"</td></tr>")
			}
			
			addListeners();
		});
		
	}
	
	function addListeners(){
		$(".country").on("click",function (){
			var item = $(this);
			var lat = item.data("lat");
			var long = item.data("long");
			showWeather(lat,long);
		});
	}

});

/** $(document).ready(function() {
	setInterval(function() {
		writeToConsole($("#textarea").val());
	}, 5000);
	
	var last = "";
	function writeToConsole(content) {
		if (last == content)
			return;
		debugger;
		last = content;
		console.log(content)
	}
});**/