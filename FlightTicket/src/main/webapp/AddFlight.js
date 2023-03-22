/**
 * 
 */

window.onload=function(){
    document.getElementById("middle-shrink").style.display="none";
    document.getElementById("mid-see").style.display="none";
    document.getElementById("mid-book").style.display="none";
    document.getElementById("mid-update").style.display="none";
	document.getElementById("UpdateFields").style.display="none";
}
function AddFlight() {
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState);	
			if(this.readyState==4 && this.status==200){
				alert("Flight Added SuccessFully");
				
				}

	}
	var flightId=document.getElementById("FlightId").value;
	var flightName=document.getElementById("flightName").value;
    var departure=document.getElementById("departure").value;
    var arrival=document.getElementById("arrival").value;
    var depTime=document.getElementById("depTime").value;
    var arrTime=document.getElementById("arrTime").value;
    var seats=document.getElementById("seats").value;
    var Price=document.getElementById("Price").value;

console.log(departure,arrival,depTime,arrTime,seats,Price);

var FlightObj={};
FlightObj.FlightId=flightId;
FlightObj.Name=flightName;
FlightObj.Depart=departure;
FlightObj.Arrive=arrival;
FlightObj.DepartTime=depTime;
FlightObj.ArriveTime=arrTime;
FlightObj.Seats=seats;
FlightObj.Price=Price;
console.log(FlightObj);

	xhr.open("POST","./Admin/AddFlight");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send(JSON.stringify(FlightObj));

}
function add() {
    document.getElementById("middle-shrink").style.display="block";
    document.getElementById("mid-see").style.display="none";
    document.getElementById("mid-book").style.display="none";
        document.getElementById("mid-update").style.display="none";

}
var FlightNumber,FlightName,DepartureCity,ArrivalCity,DepartureTime,ArrivalTime,FlightCapacity,Price;
function see() {
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		console.log(this.readyState);
		if(this.readyState==4 && this.status==200){
			console.log("success");
			var ResObj=JSON.parse(xhr.responseText);
			for(let i=0;i<ResObj.length;i++){
				FlightNumber=ResObj[i].FlightNumber;
			FlightName=ResObj[i].Flight;
			DepartureCity=ResObj[i].Departure;
			ArrivalCity=ResObj[i].Arrival;
			DepartureTime=ResObj[i].DepartureTime;
			ArrivalTime=ResObj[i].ArrivalTime;
			FlightCapacity=ResObj[i].NoOfSeats;
			Price=ResObj[i].Price;
			seeFlight("mid-see");
			}
			
		}
	}
    document.getElementById("middle-shrink").style.display="none";
    document.getElementById("mid-book").style.display="none";
    document.getElementById("mid-see").style.display="block";
    document.getElementById("mid-see").innerHTML="";
       document.getElementById("mid-update").style.display="none";
xhr.open("GET","./Admin/SeeFlight");
xhr.setRequestHeader("Content-Type","application/json");
xhr.send();
    

}

function seeFlight(e){
	var flightinfo=document.createElement("div");
    flightinfo.setAttribute("id","FlightInfo");
    flightinfo.setAttribute("class","FlightInfo");
    document.getElementById(e).appendChild(flightinfo);

    var NameNumber=document.createElement("div");
    NameNumber.setAttribute("id","NameNumber");
    flightinfo.appendChild(NameNumber);

    var Name=document.createElement("div");
    Name.setAttribute("id","Name");
    NameNumber.appendChild(Name);
    Name.innerHTML=FlightName;

    var Numbers=document.createElement("div");
    Numbers.setAttribute("id","Number");
    NameNumber.appendChild(Numbers);
    Numbers.innerHTML=FlightNumber;

    var fromTtime=document.createElement("div");
    fromTtime.setAttribute("id","fromfTime");
    flightinfo.appendChild(fromTtime);

    var from=document.createElement("div");
    from.setAttribute("id","from");
    fromTtime.appendChild(from);
    from.innerHTML=DepartureCity;

    var fTime=document.createElement("div");
    fTime.setAttribute("id","ftime");
    fromTtime.appendChild(fTime);
    fTime.innerHTML=DepartureTime;

    var noOfSeats=document.createElement("div");
    noOfSeats.setAttribute("id","noOfSeat");
    flightinfo.appendChild(noOfSeats);
    if(FlightCapacity=="small"){
	noOfSeats.innerHTML="72 Seater";
}
else if(FlightCapacity=="medium"){
	noOfSeats.innerHTML="144 Seater";
}
else if(FlightCapacity=="large"){
	noOfSeats.innerHTML="224 seater";
}
    

    var ToTime=document.createElement("div");
    ToTime.setAttribute("id","ToTtime");
    flightinfo.appendChild(ToTime);

    var To=document.createElement("div");
    To.setAttribute("id","To");
    ToTime.appendChild(To);
    To.innerHTML=ArrivalCity;

    var Ttime=document.createElement("div");
    Ttime.setAttribute("id","Ttime");
    ToTime.appendChild(Ttime);
    Ttime.innerHTML=ArrivalTime;

    var PriceInfo=document.createElement("div");
    PriceInfo.setAttribute("id","Priceinfo");
    flightinfo.appendChild(PriceInfo);
    PriceInfo.innerHTML=Price;
}

function logout(){
	var currentUser=document.cookie.split("=");
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var respobj=JSON.parse(xhr.responseText);
			if(respobj.Message=="User Logged Out"){
				document.cookie="SessionId=";
				window.location.href="./login.html";
			}
		}
	}
	xhr.open("POST","./Admin/Logout");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("SessionId="+currentUser[1]);
	
	}






function update(){
	document.getElementById("mid-see").style.display="none";
    document.getElementById("middle-shrink").style.display="none";
    document.getElementById("mid-book").style.display="none";
        document.getElementById("mid-update").style.display="block";
	document.getElementById("Update").style.display="none";

}







function UpdateFlightInfo(type){
	document.getElementById("Update").style.display="flex";
	if(type=="Departure"){
		document.getElementById("UpdateBox").innerHTML="";
		document.getElementById("UpdateBox").innerHTML='  <label for="Departure">Departure: </label>'+
 ' <select id="Departure" class="departure" required="required">'+
                						'<option value="Chennai">Chennai</option>'+
                                        '<option value="Thoothukudi">Thoothukudi</option>'+
                                        '<option value="Madurai">Madurai</option>'+
                                        '<option value="Kayathar">Kayathar</option>'+
                                        '<option value="Coimbatore">Coimbatore</option>'+
                                        '<option value="Salem">Salem</option>'+
                                        '<option value="Vellore">Vellore</option>'+
                                        '<option value="Puducherry">Puducherry</option>'+
               ' </select>';
    document.getElementById("Update").setAttribute("onclick","UpdateFlightDetailWithCurrentField('Departure')");
	}
	else if(type=="Arrival"){
		document.getElementById("UpdateBox").innerHTML="";
		document.getElementById("UpdateBox").innerHTML=' <label for="Arrival">Arrival: </label>'+
		 ' <select id="Arrival" class="departure" required="required">'+
                						'<option value="Chennai">Chennai</option>'+
                                        '<option value="Thoothukudi">Thoothukudi</option>'+
                                        '<option value="Madurai">Madurai</option>'+
                                        '<option value="Kayathar">Kayathar</option>'+
                                        '<option value="Coimbatore">Coimbatore</option>'+
                                        '<option value="Salem">Salem</option>'+
                                        '<option value="Vellore">Vellore</option>'+
                                        '<option value="Puducherry">Puducherry</option>'+
               ' </select>';
		document.getElementById("Update").setAttribute("onclick","UpdateFlightDetailWithCurrentField('Arrival')");

	}
	else if(type=="DepartureTime"){
		document.getElementById("UpdateBox").innerHTML="";
		document.getElementById("UpdateBox").innerHTML=' <label for="DepartureTime">Departure Time: </label>'+
		'<input type="Time" id="DepartureTime" class="UpdateLableWithValueBox"/> ';
		document.getElementById("Update").setAttribute("onclick","UpdateFlightDetailWithCurrentField('DepartureTime')");

	}
	else if(type=="ArriveTime"){
		document.getElementById("UpdateBox").innerHTML="";
		document.getElementById("UpdateBox").innerHTML=' <label for="ArrivalTime">Arrival Time: </label>'+
		'<input type="Time" id="ArrivalTime" class="UpdateLableWithValueBox"/>';
		document.getElementById("Update").setAttribute("onclick","UpdateFlightDetailWithCurrentField('ArrivalTime')");

	}
	else if(type=="Price"){
		document.getElementById("UpdateBox").innerHTML="";
		document.getElementById("UpdateBox").innerHTML=' <label for="PriceForSeat">Price: </label>'+
		'<input type="Number" id="PriceForSeat" placeholder="Enter the Price" class="UpdateLableWithValueBox"/>';
		document.getElementById("Update").setAttribute("onclick","UpdateFlightDetailWithCurrentField('PriceForSeat')");

	}
	else if(type=="FlightCapacity"){
		document.getElementById("UpdateBox").innerHTML="";
		document.getElementById("UpdateBox").innerHTML=' <label for="selectSeat">select Seat: </label>'+
			'<select id="selectSeat" class="UpdateLableWithValueBox">'+
				'<option value="small">Small (72 seats)</option>'+
               '<option value="medium">Medium (174 seats)</option>'+
                '<option value="large">large (224 seats)</option>'+
				'</select>';
		document.getElementById("Update").setAttribute("onclick","UpdateFlightDetailWithCurrentField('selectSeat')");


	}
}


var FlightId;
function submit(){
	var len=document.getElementsByClassName("FlightInfo").length;
	for(let i=0;i<len;i++){
				document.getElementById("FlightInfo").remove();
			}
	FlightId=document.getElementById("getFlightId").value;
	var xhr=new XMLHttpRequest();
	
	if(FlightId!=null && FlightId!=""){
		var Flight={};
		Flight.FlightId=FlightId;
		xhr.open("POST","./Admin/UpdateFlightDetails");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send(JSON.stringify(Flight));
		
		
	xhr.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var ResObj=JSON.parse(xhr.responseText);
			console.log(ResObj)
			if(ResObj.FlightId!="null"){
				FlightNumber=ResObj.FlightId;
			FlightName=ResObj.Flight;
			DepartureCity=ResObj.Departure;
			ArrivalCity=ResObj.Arrival;
			DepartureTime=ResObj.DepartureTime;
			ArrivalTime=ResObj.ArrivalTime;
			FlightCapacity=ResObj.NoOfSeats;
			Price=ResObj.Price;
			document.getElementById("UpdateFields").style.display="flex";
			document.getElementById("Update").style.display="flex";

			seeFlight("mid-update");
			}
			else{
				document.getElementById("UpdateFields").style.display="none";

			}
		}
		
	}
	}
	
}


function UpdateFlightDetailWithCurrentField(ID){
	var xhr=new XMLHttpRequest();
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			
			
			submit();
		}
	}
	var FieldObj={};
	FieldObj.FlightId=FlightId;
	FieldObj.Name=ID;
	FieldObj.Value=document.getElementById(ID).value;
	xhr.open("POST","./Admin/UpdateFlightWithFields");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send(JSON.stringify(FieldObj));
}
//var FlightNumber,FlightName,DepartureCity,ArrivalCity,Price;DepartureTime,ArrivalTime

		var PassengerName,PassengerNumber,BookedUserNumber,UserName,DepartureDate,ArrivalDate,SelectedSeat;
function book() {
	document.getElementById("mid-book").innerHTML="";
    document.getElementById("mid-see").style.display="none";
    document.getElementById("middle-shrink").style.display="none";
    document.getElementById("mid-book").style.display="block";
    document.getElementById("mid-update").style.display="none";
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
	console.log(this.readyState)
	
	if(this.readyState==4 && this.status==200){
		var respObject=JSON.parse(xhr.responseText);
		for(let i=0;i<respObject.length;i++){
			FlightNumber=respObject[i].FlightId;
		FlightName=respObject[i].FlightName;
		DepartureCity=respObject[i].Departure;
		ArrivalCity=respObject[i].Arrival;
		Price=respObject[i].Price;
		PassengerName=respObject[i].PassengerName;
		PassengerNumber=respObject[i].PassengerNumber;
		BookedUserNumber=respObject[i].BookedUserNumber;
		UserName=respObject[i].UserName;
		DepartureDate=respObject[i].DepartureDate;
		ArrivalDate=respObject[i].ArrivalDate;
		DepartureTime=respObject[i].DepartureTime;
		ArrivalTime=respObject[i].ArrivalTime;
		SelectedSeat=respObject[i].SelectedSeat;
		Book();

		}
		
	}
	
}
xhr.open("GET","./Admin/SeeBookedDetails");
	xhr.send();

}

function Book(){
    document.getElementById("mid-book").innerHTML+=
    '<div id="middleDiv1">'+
     '<div id="NameNumber1">'+
       ' <div id="Name1" class="Box1">'+PassengerName+'</div>'+
       ' <div id="Number1" class="Box1">'+PassengerNumber+'</div>'+
       '</div>'+
     ' <div id="NameNumber1">'+
       ' <div id="FlightName1" class="Box1">'+FlightName+'</div>'+
       ' <div id="FlightNumber1" class="Box1">'+FlightNumber+'</div>'+
       '</div>'+
     ' <div id="NameNumber1">'+
       ' <div id="DepartureCity1" class="Box2">'+DepartureCity+'</div>'+
       ' <div id="DepDate1" class="Box2">'+DepartureDate+'</div>'+
        ' <div id="DepTime" class="Box2">'+DepartureTime+'</div>'+
       ' </div>'+
     ' <div id="NameNumber1">'+
       ' <div id="ArrivalCity1" class="Box2">'+ArrivalCity+'</div>'+
       ' <div id="arrDate1" class="Box2">'+ArrivalDate+'</div>'+
              '<div id="arrTime" class="Box2">'+ArrivalTime+'</div>'+
       ' </div>'+
     ' <div id="NameNumber1">'+
       ' <div id="PassengerName1" class="Box1">'+UserName+'</div>'+
       ' <div id="PassengerNumber1" class="Box1">'+BookedUserNumber+'</div>'+
       '</div>'+
     ' <div id="NameNumber1">'+
     ' <div id="Seat1">'+SelectedSeat+'</div>'+
     '</div>'+
   ' </div>';
}










